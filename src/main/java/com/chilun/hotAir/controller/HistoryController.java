package com.chilun.hotAir.controller;

import com.chilun.hotAir.Utils.ResultUtils;
import com.chilun.hotAir.facade.HistoryRunRecodeFacade;
import com.chilun.hotAir.model.dto.BaseResponse;
import com.chilun.hotAir.model.entity.Experiment;
import com.chilun.hotAir.model.entity.ExperimentAdjustRecord;
import com.chilun.hotAir.model.entity.ExperimentDataRecord;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 齿轮
 * @date 2025-04-06-21:17
 */
@RestController
@RequestMapping("/history")
@Api(tags = "历史实验查询")
public class HistoryController {
    @Resource
    HistoryRunRecodeFacade historyRunRecodeFacade;

    @GetMapping("/experiment")
    @Operation(summary = "获得全部实验元信息")
    public BaseResponse<List<Experiment>> getAllExperiment() {
        List<Experiment> allExperiment = historyRunRecodeFacade.getAllExperiment();
        return ResultUtils.success(allExperiment);
    }

    @GetMapping("/experimentData/{id}")
    @Operation(summary = "获得指定实验全部数据记录")
    public BaseResponse<List<ExperimentDataRecord>> getExperimentData(@PathVariable long id) {
        List<ExperimentDataRecord> records = historyRunRecodeFacade.getExperimentDataRecordByExperiment(id);
        return ResultUtils.success(records);
    }

    @GetMapping("/experimentAdjust/{id}")
    @Operation(summary = "获得指定实验全部参数调整记录")
    public BaseResponse<List<ExperimentAdjustRecord>> getExperimentAdjust(@PathVariable long id) {
        List<ExperimentAdjustRecord> adjusts = historyRunRecodeFacade.getExperimentAdjustRecordByExperiment(id);
        return ResultUtils.success(adjusts);
    }
}
