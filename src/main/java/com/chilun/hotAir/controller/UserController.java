package com.chilun.hotAir.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chilun.hotAir.Utils.ResultUtils;
import com.chilun.hotAir.Utils.ThrowUtils;
import com.chilun.hotAir.aop.annotation.UserAuthCheck;
import com.chilun.hotAir.constant.SessionKey;
import com.chilun.hotAir.constant.UserRoleValue;
import com.chilun.hotAir.exception.ErrorCode;
import com.chilun.hotAir.model.Masked.UserMasked;
import com.chilun.hotAir.model.dto.BaseResponse;
import com.chilun.hotAir.model.dto.DeleteRequest;
import com.chilun.hotAir.model.dto.User.*;
import com.chilun.hotAir.model.entity.User;
import com.chilun.hotAir.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author 齿轮
 * @date 2024-01-25-18:41
 * <p>
 * 目前提供：注册、登录、退出登录、查、改、注销接口
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户服务")
public class UserController {
    @Resource
    UserService userService;

    //注册
    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest registerRequest) {
        //一、数据校验
        //1DTO对象是否为空
        ThrowUtils.throwIf(registerRequest == null, ErrorCode.PARAMS_ERROR, "注册请求为空");
        //2DTO参数是否异常
        ThrowUtils.throwIf(StringUtils.isAnyBlank(registerRequest.getUserAccount(), registerRequest.getUserPassword(), registerRequest.getCheckPassword()),
                ErrorCode.PARAMS_ERROR,
                "注册请求参数为空");
        //二、进行注册
        long registerRet = userService.userRegister(registerRequest.getUserAccount(), registerRequest.getUserPassword(), registerRequest.getCheckPassword());
        //三、返回注册结果
        return ResultUtils.success(registerRet);
    }

    //登录
    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public BaseResponse<UserMasked> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        //一、数据校验
        //1DTO对象是否为空
        ThrowUtils.throwIf(userLoginRequest == null || request == null,
                ErrorCode.PARAMS_ERROR,
                "登录请求为空");
        //2DTO参数是否异常
        ThrowUtils.throwIf(StringUtils.isAnyBlank(userLoginRequest.getUserAccount(), userLoginRequest.getUserPassword()),
                ErrorCode.PARAMS_ERROR,
                "登录请求参数为空");
        //二、进行登录
        UserMasked userMasked = userService.userLogin(userLoginRequest.getUserAccount(), userLoginRequest.getUserPassword(), request);
        //三、返回登录结果
        return ResultUtils.success(userMasked);
    }

    //退出登录
    @PostMapping("/logout")
    @Operation(summary = "退出登录")
    public BaseResponse<Void> userLogout(HttpServletRequest request) {
        //一、实参检验
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR, "退出登录请求为空");
        //二、退出登录
        userService.userLogout(request);
        //三、返回结果
        return ResultUtils.success(null);
    }

    //废除账号
    @PostMapping("/abolish")
    @Operation(summary = "删除用户")
    public BaseResponse<Void> userAbolish(HttpServletRequest request) {
        //一、请求检验，获得脱敏对象
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR, "请求为空");
        UserMasked userMasked = (UserMasked) request.getSession().getAttribute(SessionKey.USER_IN_SESSION_KEY);
        ThrowUtils.throwIf(userMasked == null, ErrorCode.NOT_LOGIN_ERROR, "用户未登录，废除失败，请重新登陆");
        //二、废除账号
        boolean b = userService.removeById(userMasked.getId());
        ThrowUtils.throwIf(!b, ErrorCode.SYSTEM_ERROR, "删除账号失败");
        //三、返回结果
        return ResultUtils.success(null);
    }

    //更改账号信息
    @PostMapping("/update")
    @Operation(summary = "用户信息更改")
    public BaseResponse<UserMasked> updateSelf(@RequestBody @Valid UserSelfUpdateRequest updateRequest, HttpServletRequest request) {
        //一、数据校验
        //1DTO对象不为空——@RequestBody注解要求不为空
        //2DTO对象参数长度校验——@Valid接口实现
        //3DTO对象参数不全为空校验
        ThrowUtils.throwIf(StringUtils.isEmpty(updateRequest.getIntroduction())
                        && StringUtils.isEmpty(updateRequest.getUserNickname())
                        && StringUtils.isEmpty(updateRequest.getPassword()),
                ErrorCode.PARAMS_ERROR,
                "无有效更改参数！");
        //4请求对象是否为空
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR, "请求为空");

        //二、获得当前账号（脱敏）
        UserMasked userMasked = (UserMasked) request.getSession().getAttribute(SessionKey.USER_IN_SESSION_KEY);
        ThrowUtils.throwIf(userMasked == null, ErrorCode.NOT_LOGIN_ERROR, "用户未登录，修改失败，请重新登陆");

        //三、获得更新参数User
        User user = new User();
        user.setId(userMasked.getId());
        if (StringUtils.isNotEmpty(updateRequest.getPassword())) {
            user.setPassword(userService.passwordDigest(updateRequest.getPassword()));
        }
        if (StringUtils.isNotEmpty(updateRequest.getUserNickname()))
            user.setUserNickname(updateRequest.getUserNickname());
        if (StringUtils.isNotEmpty(updateRequest.getIntroduction()))
            user.setIntroduction(updateRequest.getIntroduction());

        //四、更新数据库内用户
        boolean b = userService.updateById(user);
        ThrowUtils.throwIf(!b, ErrorCode.SYSTEM_ERROR, "更新失败");

        //五、获得数据库内用户，脱敏
        User byId = userService.getById(user.getId());
        UserMasked newUserMasked = userService.getUserMasked(byId);

        //六、更新session内用户
        request.getSession().removeAttribute(SessionKey.USER_IN_SESSION_KEY);
        request.getSession().setAttribute(SessionKey.USER_IN_SESSION_KEY, newUserMasked);

        //七、返回脱敏用户
        return ResultUtils.success(newUserMasked);
    }

    //管理员添加账号
    @PostMapping("/admin/add")
    @UserAuthCheck(mustRole = UserRoleValue.ADMIN)
    @Operation(summary = "管理员添加用户")
    public BaseResponse<UserMasked> addUser(@RequestBody @Valid UserAddRequest addRequest) {
        //一、数据校验（已省略，通过Java Bean Validation实现）
        //1DTO对象是否为空——@RequestBody注解要求不为空
        //2DTO参数是否异常：必要参数是否存在
        //3DTO参数是否异常：长度校验
        //4DTO参数是否异常：数值校验

        //二、添加用户
        //1检查是否已经存在
        ThrowUtils.throwIf(userService.getBaseMapper().selectCount(
                        new QueryWrapper<User>().eq("username", addRequest.getUsername())
                ) != 0,
                ErrorCode.PARAMS_ERROR,
                "已存在");
        //2生成添加的用户类
        User user = new User();
        BeanUtils.copyProperties(addRequest, user);
        user.setPassword(userService.passwordDigest(addRequest.getPassword()));
        //3通过service添加用户
        boolean saved = userService.save(user);
        ThrowUtils.throwIf(!saved, ErrorCode.SYSTEM_ERROR, "添加失败");

        //三、返回脱敏后的添加结果
        return ResultUtils.success(userService.getUserMasked(user));
    }

    //管理员删除账号
    @PostMapping("/admin/delete")
    @UserAuthCheck(mustRole = UserRoleValue.ADMIN)
    @Operation(summary = "管理员删除用户")
    public BaseResponse<Void> deleteUser(@RequestBody @Valid DeleteRequest deleteRequest) {
        //一、数据校验（已省略，通过Java Bean Validation实现）
        //1DTO对象是否为空——@RequestBody注解要求不为空
        //2DTO参数是否异常：必要参数是否存在——@Valid注解要求不为空

        //二、删除用户
        boolean b = userService.removeById(deleteRequest.getId());
        ThrowUtils.throwIf(!b, ErrorCode.SYSTEM_ERROR, "删除用户失败");

        //三、返回结果
        return ResultUtils.success(null);
    }

    //管理员更新账号
    @PostMapping("/admin/update")
    @UserAuthCheck(mustRole = UserRoleValue.ADMIN)
    @Operation(summary = "管理员更新用户信息")
    public BaseResponse<UserMasked> updateUser(@RequestBody @Valid UserUpdateRequest updateRequest) {
        //一、数据校验
        //1DTO对象是否为空——@RequestBody注解要求不为空
        //2DTO参数是否异常：必要参数是否存在——@Valid注解要求不为空
        //3DTO参数是否异常：长度校验——@Valid注解实现
        //4DTO参数是否异常：数值校验——@Valid注解实现

        //二、修改用户
        //1查看用户是否存在
        ThrowUtils.throwIf(userService.getBaseMapper().selectById(updateRequest.getId()) == null,
                ErrorCode.PARAMS_ERROR,
                "用户不存在");
        //2获得修改后用户
        User user = new User();
        BeanUtils.copyProperties(updateRequest, user);
        //3更新用户
        boolean b = userService.updateById(user);
        ThrowUtils.throwIf(!b, ErrorCode.SYSTEM_ERROR, "更新失败");

        //三、返回结果
        UserMasked userMasked = userService.getUserMasked(userService.getById(user.getId()));
        return ResultUtils.success(userMasked);
    }

    //管理员查询\筛选账号：ByID、ByUsername
    @GetMapping("/admin/query/id/{id}")
    @UserAuthCheck(mustRole = UserRoleValue.ADMIN)
    @Operation(summary = "管理员查询用户ByID")
    public BaseResponse<User> getUserByID(@PathVariable Long id) {
        //一、数据校验
        //1id是否为空——@PathVariable注解要求不为空

        //二、查询结果并返回
        return ResultUtils.success(userService.getById(id));
    }

    @GetMapping("/admin/query/username/{username}")
    @UserAuthCheck(mustRole = UserRoleValue.ADMIN)
    @Operation(summary = "管理员查询用户ByName")
    public BaseResponse<User> getUserByUsername(@PathVariable String username) {
        //一、数据校验
        //1username是否为空——@PathVariable注解要求不为空

        //二、查询结果
        User user = userService.getBaseMapper().selectOne(new QueryWrapper<User>().eq("username", username));

        //三、返回查询
        return ResultUtils.success(user);
    }

    //管理员查询\筛选账号：（all）、likeNickname、likeIntroduce、inRole、inStatus、ge/leTotalBalance
    @PostMapping("/admin/filter")
    @UserAuthCheck(mustRole = UserRoleValue.ADMIN)
    @Operation(summary = "管理员筛选用户")
    public BaseResponse<Page<User>> listUser(@RequestBody @Valid UserQueryRequest queryRequest) {
        //一、数据校验
        //1DTO对象是否为空——@RequestBody注解要求不为空
        //2获得DTO对象分页参数
        long current = queryRequest.getCurrent();
        long pageSize = queryRequest.getPageSize();

        //二、构建筛选条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(queryRequest.getUserNickname()), "user_nickname", queryRequest.getUserNickname());
        wrapper.like(StringUtils.isNotBlank(queryRequest.getIntroduction()), "introduction", queryRequest.getIntroduction());
        if (queryRequest.getRole() != null)
            wrapper.in("role", List.of(queryRequest.getRole()));
        if (queryRequest.getStatus() != null)
            wrapper.in("status", List.of(queryRequest.getStatus()));
        wrapper.le(queryRequest.getHighTotalBalance() != null, "total_balance", queryRequest.getHighTotalBalance());
        wrapper.ge(queryRequest.getLowTotalBalance() != null, "total_balance", queryRequest.getLowTotalBalance());

        //三、进行筛选
        Page<User> page = userService.page(new Page<>(current, pageSize), wrapper);

        //四、返回结果
        return ResultUtils.success(page);
    }

}
