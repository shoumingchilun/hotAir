package com.chilun.hotAir.facade;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chilun.hotAir.Utils.ExperimentUtils;
import com.chilun.hotAir.Utils.JsonUtils;
import com.chilun.hotAir.Utils.ThrowUtils;
import com.chilun.hotAir.constant.TCNCommonConstant;
import com.chilun.hotAir.model.MachineAdjustableParam;
import com.chilun.hotAir.model.SystemInitParam;
import com.chilun.hotAir.model.TCNOutParam;
import com.chilun.hotAir.model.context.ExperimentContext;
import com.chilun.hotAir.model.entity.Experiment;
import com.chilun.hotAir.model.entity.ExperimentAdjustRecord;
import com.chilun.hotAir.model.entity.ExperimentData;
import com.chilun.hotAir.model.entity.ExperimentDataRecord;
import com.chilun.hotAir.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static com.chilun.hotAir.exception.ErrorCode.PARAMS_ERROR;
import static com.chilun.hotAir.exception.ErrorCode.SYSTEM_ERROR;

/**
 * 闭环控制外观层
 *
 * @author 齿轮
 * @date 2025-03-22-10:08
 */
@Component
public class ClosedLoopControlFacade {

    @Resource
    ExperimentService experimentService;

    @Resource
    ExperimentDataRecordService experimentDataRecordService;

    @Resource
    ExperimentAdjustRecordService experimentAdjustRecordService;

    @Resource
    PSOAccessService psoAccessService;

    @Resource
    TCNAccessService tcnAccessService;

    //系统初始化接口
    public ExperimentContext init(SystemInitParam initParam) {
        ExperimentContext experimentContext = new ExperimentContext();

        //TODO: 该模型参数应该可以为空，可以补充默认超参数
        ThrowUtils.throwIf(Objects.isNull(initParam.getModelHyperParam()), PARAMS_ERROR, "模型超参数异常");
        experimentContext.setModelHyperParam(initParam.getModelHyperParam());

        //TODO: 此处可以优化为使用尾部数据填充指所需长度，也可以补充默认配置
        List<ExperimentData> experimentDataList = initParam.getSystemInitParam();
//        ThrowUtils.throwIf(CollectionUtils.isEmpty(experimentDataList) ||
//                experimentDataList.size() < TCNCommonConstant.SEQUENCE_LENGTH, PARAMS_ERROR, "模型预测入参异常");
        experimentContext.setOldDataCache(new ArrayDeque<>(experimentDataList));

        Experiment experiment = new Experiment();
        experiment.setState(1);
        experiment.setInfo(JsonUtils.toJson(initParam));
        experiment.setStartTime(LocalDateTime.now());
        experiment.setExperimentName(initParam.getExperimentName());
        boolean saved = experimentService.save(experiment);
        ThrowUtils.throwIf(!saved, SYSTEM_ERROR, "创建实验失败");

        experimentContext.setExperimentId(experiment.getId());
        return experimentContext;
    }

    //让系统进入下一个时间步
    public void nextStep(ExperimentContext context) {
        //填充oldCache的最终一个<入参:null>的出参，并在尾部新增一个<入参:null>
        Deque<ExperimentData> oldDataCache = context.getOldDataCache();
        ExperimentData oldData = oldDataCache.peekLast();
        TCNOutParam out = tcnAccessService.getNextOutParameter(ExperimentUtils.getTCNInParam(oldDataCache));
        oldData.fill(out);
        ExperimentData newExpData = new ExperimentData();
        newExpData.fill(oldData.getTcnInParam());
        oldDataCache.addLast(newExpData);
        oldDataCache.pollFirst();
        //持久化
        ExperimentDataRecord record = new ExperimentDataRecord();
        BeanUtils.copyProperties(oldData, record);
        record.setExperimentId(context.getExperimentId());
        record.setDataTime(context.getTime());
        record.setListType(0);
        boolean saved = experimentDataRecordService.save(record);
        ThrowUtils.throwIf(!saved, SYSTEM_ERROR, "保存实验数据记录失败");

        if (Objects.nonNull(context.getPsoDataCache())) {
            //填充psoCache的最终一个<入参:null>的出参，并在尾部新增一个<入参:null>
            Deque<ExperimentData> psoDataCache = context.getPsoDataCache();
            ExperimentData psoData = psoDataCache.peekLast();
            TCNOutParam psoOut = tcnAccessService.getNextOutParameter(ExperimentUtils.getTCNInParam(psoDataCache));
            psoData.fill(psoOut);
            ExperimentData newExpData2 = new ExperimentData();
            newExpData2.fill(psoData.getTcnInParam());
            psoDataCache.addLast(newExpData2);
            psoDataCache.pollFirst();
            //持久化
            ExperimentDataRecord record2 = new ExperimentDataRecord();
            BeanUtils.copyProperties(psoData, record2);
            record2.setExperimentId(context.getExperimentId());
            record2.setDataTime(context.getTime());
            record2.setListType(2);
            boolean saved2 = experimentDataRecordService.save(record2);
            ThrowUtils.throwIf(!saved2, SYSTEM_ERROR, "保存实验数据记录失败");
        }
        if (Objects.nonNull(context.getManualDataCache())) {
            //填充manualCache的最终一个<入参:null>的出参，并在尾部新增一个<入参:null>
            Deque<ExperimentData> manualDataCache = context.getManualDataCache();
            ExperimentData manualData = manualDataCache.peekLast();
            TCNOutParam manualOut = tcnAccessService.getNextOutParameter(ExperimentUtils.getTCNInParam(manualDataCache));
            manualData.fill(manualOut);
            ExperimentData newExpData2 = new ExperimentData();
            newExpData2.fill(manualData.getTcnInParam());
            manualDataCache.addLast(newExpData2);
            manualDataCache.pollFirst();
            //持久化
            ExperimentDataRecord record2 = new ExperimentDataRecord();
            BeanUtils.copyProperties(manualData, record2);
            record2.setExperimentId(context.getExperimentId());
            record2.setDataTime(context.getTime());
            record2.setListType(1);
            boolean saved2 = experimentDataRecordService.save(record2);
            ThrowUtils.throwIf(!saved2, SYSTEM_ERROR, "保存实验数据记录失败");
        }
        context.plusOneStep();
    }


    //查询系统提供建议的可调整参数
    public MachineAdjustableParam getSuggest(ExperimentContext context) {
        //先获得原型数据，如果context中有manual，就是manual；否则有pso就是pso；否则是old
        Deque<ExperimentData> oldCache = context.getManualDataCache();
        if (Objects.isNull(oldCache)) {
            oldCache = context.getPsoDataCache();
        }
        if (Objects.isNull(oldCache)) {
            oldCache = context.getOldDataCache();
        }
        return psoAccessService.getBetterParameter(ExperimentUtils.getTCNInParam(oldCache));
    }

    //设置机器的可调整参数
    public void setMachineAdjustableParam(MachineAdjustableParam psoParam,
                                          MachineAdjustableParam manualParam, ExperimentContext context) {
        //先获得原型数据，如果context中有manual，就是manual；否则有pso就是pso；否则是old
        Deque<ExperimentData> oldCache = context.getManualDataCache();
        if (Objects.isNull(oldCache)) {
            oldCache = context.getPsoDataCache();
        }
        if (Objects.isNull(oldCache)) {
            oldCache = context.getOldDataCache();
        }
        context.setOldDataCache(oldCache);
        //填充manual
        if (Objects.isNull(manualParam)) {
            //manualParam为空说明使用pso参数，将context的manualCache设置为空
            context.setManualDataCache(null);
        } else {
            context.setManualDataCache(new ArrayDeque<>(oldCache));
            ExperimentData manualData = new ExperimentData();
            BeanUtils.copyProperties(oldCache.peekLast(), manualData);
            manualData.fill(manualParam);
            Deque<ExperimentData> manualDataCache = context.getManualDataCache();
            manualDataCache.pollLast();
            manualDataCache.addLast(manualData);
        }
        //填充pso
        if (Objects.isNull(psoParam)) {
            context.setPsoDataCache(null);
        } else {
            context.setPsoDataCache(new ArrayDeque<>(oldCache));
            ExperimentData psoData = new ExperimentData();
            BeanUtils.copyProperties(oldCache.peekLast(), psoData);
            psoData.fill(psoParam);
            Deque<ExperimentData> psoDataCache = context.getPsoDataCache();
            psoDataCache.pollLast();
            psoDataCache.addLast(psoData);
        }
        ExperimentAdjustRecord record = new ExperimentAdjustRecord();
        record.setExperimentId(context.getExperimentId());
        record.setAdjustTime(context.getTime());
        record.setOldValue(JsonUtils.toJson(context.getOldDataCache().peekLast().getMachineAdjustableParam()));
        if (!CollectionUtils.isEmpty(context.getPsoDataCache())) {
            record.setPsoValue(JsonUtils.toJson(context.getPsoDataCache().peekLast().getMachineAdjustableParam()));
        }
        if (!CollectionUtils.isEmpty(context.getManualDataCache())) {
            record.setManualValue(JsonUtils.toJson(context.getManualDataCache().peekLast().getMachineAdjustableParam()));
        }
        boolean saved = experimentAdjustRecordService.save(record);
        ThrowUtils.throwIf(!saved, SYSTEM_ERROR, "保存实验参数的修改失败");
    }

    //查询系统当前时间步-1下应该显示的参数信息
    public ExperimentDataRecord getCurrentInfo(ExperimentContext context) {
        //先获得原型数据，如果context中有manual，就是manual；否则有pso就是pso；否则是old
        Deque<ExperimentData> oldCache = context.getManualDataCache();

        ExperimentDataRecord record = new ExperimentDataRecord();
        record.setExperimentId(context.getExperimentId());
        record.setDataTime(context.getTime().plusSeconds(-1));
        record.setListType(1);
        if (Objects.isNull(oldCache)) {
            oldCache = context.getPsoDataCache();
            record.setListType(2);
        }
        if (Objects.isNull(oldCache)) {
            oldCache = context.getOldDataCache();
            record.setListType(0);
        }
        ExperimentData secondLast = ExperimentUtils.getSecondLast(oldCache);
        BeanUtils.copyProperties(secondLast, record);
        return record;
    }

    //查询系统历史信息，提供begin~end这段时间的时间序列数据
    public List<ExperimentDataRecord> getHistoryInfo(ExperimentContext context, LocalTime begin, LocalTime end) {
        long experimentId = context.getExperimentId();
        BaseMapper<ExperimentDataRecord> baseMapper = experimentDataRecordService.getBaseMapper();
        QueryWrapper<ExperimentDataRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("experiment_id", experimentId);
        wrapper.between("data_time", begin, end);
        wrapper.orderByAsc("data_time");
        return baseMapper.selectList(wrapper);
    }

    //本次模拟结束并存档
    public void end(ExperimentContext context) {
        Experiment experiment = experimentService.getById(context.getExperimentId());
        experiment.setState(2);
        boolean updated = experimentService.updateById(experiment);
        ThrowUtils.throwIf(!updated, SYSTEM_ERROR, "保存实验状态为“结束”失败");
    }

}
