package com.chilun.hotAir.controller;

import com.chilun.hotAir.Utils.ResultUtils;
import com.chilun.hotAir.facade.ClosedLoopControlFacade;
import com.chilun.hotAir.model.SystemInitParam;
import com.chilun.hotAir.model.context.ExperimentContext;
import com.chilun.hotAir.model.dto.BaseResponse;
import com.chilun.hotAir.model.dto.experiment.ExperimentAdjustRecordRequest;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 齿轮
 * @date 2025-03-31-20:53
 */
@RestController
@RequestMapping("/experiment")
@Api(tags = "实验服务")
public class ExperimentController {
    @Resource
    ClosedLoopControlFacade facade;

    @PostMapping("/init")
    @Operation(summary = "实验初始化")
    public BaseResponse<Void> init(HttpSession session, @RequestBody SystemInitParam initParam) {
        ExperimentContext context = facade.init(initParam);
        session.setAttribute("context", context);
        return ResultUtils.success(null);
    }

    @PostMapping("/next")
    @Operation(summary = "实验进入下一步")
    public BaseResponse<Void> next(HttpSession session) {
        ExperimentContext context = (ExperimentContext) session.getAttribute("context");
        facade.nextStep(context);
        return ResultUtils.success(null);
    }

    @PostMapping("/adjust")
    @Operation(summary = "调整参数")
    public BaseResponse<Void> adjust(HttpSession session, @RequestBody ExperimentAdjustRecordRequest request) {
        ExperimentContext context = (ExperimentContext) session.getAttribute("context");
        facade.setMachineAdjustableParam(request.getPsoParam(), request.getManualParam(), context);
        return ResultUtils.success(null);
    }

}
