package com.chilun.hotAir.controller;

import com.chilun.hotAir.Utils.ResultUtils;
import com.chilun.hotAir.facade.ClosedLoopControlFacade;
import com.chilun.hotAir.model.MachineAdjustableParam;
import com.chilun.hotAir.model.ModelHyperParam;
import com.chilun.hotAir.model.SystemInitParam;
import com.chilun.hotAir.model.context.ExperimentContext;
import com.chilun.hotAir.model.dto.BaseResponse;
import com.chilun.hotAir.model.dto.experiment.ExperimentAdjustRecordRequest;
import com.chilun.hotAir.model.entity.ExperimentDataRecord;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.List;

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
    public BaseResponse<Long> init(HttpSession session, @RequestBody SystemInitParam initParam) {
        ExperimentContext context = facade.init(initParam);
        session.setAttribute("context", context);
        return ResultUtils.success(context.getExperimentId());
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

    //TODO: 补充调整环境参数接口

    @PostMapping("/end")
    @Operation(summary = "结束实验")
    public BaseResponse<Void> end(HttpSession session) {
        ExperimentContext context = (ExperimentContext) session.getAttribute("context");
        facade.end(context);
        return ResultUtils.success(null);
    }

    @GetMapping("/now")
    @Operation(summary = "获得当前时间戳的入参出参")
    public BaseResponse<ExperimentDataRecord> now(HttpSession session) {
        ExperimentContext context = (ExperimentContext) session.getAttribute("context");
        return ResultUtils.success(facade.getCurrentInfo(context));
    }

    @GetMapping("/suggest")
    @Operation(summary = "获得PSO推荐的修改入参")
    public BaseResponse<MachineAdjustableParam> suggest(HttpSession session) {
        ExperimentContext context = (ExperimentContext) session.getAttribute("context");
        return ResultUtils.success(facade.getSuggest(context));
    }

    @GetMapping("/history")
    @Operation(summary = "查询当前实验的历史数据")
    public BaseResponse<List<ExperimentDataRecord>> history(HttpSession session, @RequestParam String start, @RequestParam String end) {
        LocalTime startTime = LocalTime.parse(start);
        LocalTime endTime = LocalTime.parse(end);
        ExperimentContext context = (ExperimentContext) session.getAttribute("context");
        return ResultUtils.success(facade.getHistoryInfo(context, startTime, endTime));
    }

    @PostMapping("/config")
    @Operation(summary = "更改设置")
    public BaseResponse<Void> config(HttpSession session, @RequestBody ModelHyperParam modelHyperParam) {
        ExperimentContext context = (ExperimentContext) session.getAttribute("context");
        ModelHyperParam currentParam = context.getModelHyperParam();
        BeanUtils.copyProperties(modelHyperParam, currentParam);
        return ResultUtils.success(null);
    }
}
