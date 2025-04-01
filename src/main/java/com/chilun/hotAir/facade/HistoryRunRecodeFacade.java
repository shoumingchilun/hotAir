package com.chilun.hotAir.facade;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chilun.hotAir.model.entity.Experiment;
import com.chilun.hotAir.model.entity.ExperimentAdjustRecord;
import com.chilun.hotAir.model.entity.ExperimentDataRecord;
import com.chilun.hotAir.service.ExperimentAdjustRecordService;
import com.chilun.hotAir.service.ExperimentDataRecordService;
import com.chilun.hotAir.service.ExperimentService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 历史记录查看外观层
 *
 * @author 齿轮
 * @date 2025-03-22-10:50
 */
@Component
public class HistoryRunRecodeFacade {
    @Resource
    ExperimentService experimentService;

    @Resource
    ExperimentDataRecordService experimentDataRecordService;

    @Resource
    ExperimentAdjustRecordService experimentAdjustRecordService;

    public List<Experiment> getAllExperiment() {
        BaseMapper<Experiment> mapper = experimentService.getBaseMapper();
        return mapper.selectList(new QueryWrapper<>());
    }

    public List<ExperimentAdjustRecord> getExperimentAdjustRecordByExperiment(long experimentId) {
        BaseMapper<ExperimentAdjustRecord> mapper = experimentAdjustRecordService.getBaseMapper();
        QueryWrapper<ExperimentAdjustRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("experiment_id", experimentId);
        return mapper.selectList(wrapper);
    }

    public List<ExperimentDataRecord> getExperimentDataRecordByExperiment(long experimentId) {
        BaseMapper<ExperimentDataRecord> mapper = experimentDataRecordService.getBaseMapper();
        QueryWrapper<ExperimentDataRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("experiment_id", experimentId);
        return mapper.selectList(wrapper);
    }

}
