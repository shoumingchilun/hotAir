package com.chilun.hotAir.facade;

import com.chilun.hotAir.Utils.JsonUtils;
import com.chilun.hotAir.Utils.ThrowUtils;
import com.chilun.hotAir.constant.TCNCommonConstant;
import com.chilun.hotAir.exception.ErrorCode;
import com.chilun.hotAir.model.MachineAdjustableParam;
import com.chilun.hotAir.model.ModelHyperParam;
import com.chilun.hotAir.model.SystemInitParam;
import com.chilun.hotAir.model.TCNInParam;
import com.chilun.hotAir.model.entity.Experiment;
import com.chilun.hotAir.model.entity.ExperimentData;
import com.chilun.hotAir.service.ExperimentService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    ModelHyperParam modelHyperParam;
    Deque<ExperimentData> cacheExperimentData;
    Experiment experiment;

    //系统初始化接口
    public void init(SystemInitParam initParam) {
        modelHyperParam = initParam.getModelHyperParam();
        //TODO: 该模型参数应该可以为空，可以补充默认超参数
        ThrowUtils.throwIf(Objects.isNull(modelHyperParam), PARAMS_ERROR, "模型超参数异常");

        //TODO: 此处可以优化为使用尾部数据填充指所需长度，也可以补充默认配置
        List<ExperimentData> experimentDataList = initParam.getSystemInitParam();
        ThrowUtils.throwIf(CollectionUtils.isEmpty(experimentDataList) ||
                experimentDataList.size() < TCNCommonConstant.SEQUENCE_LENGTH, PARAMS_ERROR, "模型预测入参异常");

        //TODO: 数据校验方式可以优化

        cacheExperimentData = new ArrayDeque<>(experimentDataList);

        Experiment experiment = new Experiment();
        experiment.setState(0);
        experiment.setInfo(JsonUtils.toJson(initParam));
        experiment.setStartTime(LocalDateTime.now());
        experiment.setExperimentName(initParam.getExperimentName());

        boolean saved = experimentService.save(experiment);
        ThrowUtils.throwIf(!saved, SYSTEM_ERROR, "创建实验失败");
    }

    //查询系统提供建议的可调整参数
    public MachineAdjustableParam getSuggest() {
        return null;
    }

    //设置机器的可调整参数
    public void setMachineAdjustableParam(MachineAdjustableParam param, boolean isSuggest) {

    }

    //查询系统当前时间步下的参数信息，包含TCN出参
    public Map<String, Object> getCurrentInfo() {
        return null;
    }

    //查询系统历史信息，提供current-length~current-1这段时间的时间序列数据，格式类似模型训练数据集
    public Map<String, Object> getHistoryInfo(int length) {
        return null;
    }

    //让系统进入下一个时间步
    public void nextStep() {

    }

    //本次模拟结束
    public void end() {

    }

    //复制机器，用于处理出现分支的情况，如工人自定义参数与系统推荐参数运行下的不同分支
    public ClosedLoopControlFacade copy() {
        return null;
    }
}
