package com.chilun.hotAir.service;

import com.chilun.hotAir.model.MachineAdjustableParam;
import com.chilun.hotAir.model.ModelHyperParam;
import com.chilun.hotAir.model.TCNInParam;

import java.util.List;

/**
 * PSO优化模块，输入入参参数和最优参数，返回对入参参数的修改
 *
 * @author 齿轮
 * @date 2025-03-20-17:54
 */
public interface PSOAccessService {
    //提供历史入参和最优出口目标，让PSO进行参数优化，出参为接下来list.length长度时间步的机器可调整参数的时间序列
    MachineAdjustableParam getBetterParameter(List<TCNInParam> inParamList);

    MachineAdjustableParam getBetterParameterWithDetail(List<TCNInParam> inParamList, ModelHyperParam modelHyperParam);
}
