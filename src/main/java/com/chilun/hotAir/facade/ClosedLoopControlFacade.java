package com.chilun.hotAir.facade;

import com.chilun.hotAir.model.MachineAdjustableParam;
import com.chilun.hotAir.model.SystemInitParam;

import java.util.Map;

/**
 * 闭环控制外观层
 *
 * @author 齿轮
 * @date 2025-03-22-10:08
 */
public interface ClosedLoopControlFacade {
    //系统初始化接口
    void init(SystemInitParam initParam);

    //查询系统提供建议的可调整参数
    MachineAdjustableParam getSuggest();

    //设置机器的可调整参数
    void setMachineAdjustableParam(MachineAdjustableParam param, boolean isSuggest);

    //查询系统当前时间步下的参数信息，包含TCN出参
    Map<String, Object> getCurrentInfo();

    //查询系统历史信息，提供current-length~current-1这段时间的时间序列数据，格式类似模型训练数据集
    Map<String, Object> getHistoryInfo(int length);

    //让系统进入下一个时间步
    void nextStep();

    //本次模拟结束
    void end();

    //复制机器，用于处理出现分支的情况，如工人自定义参数与系统推荐参数运行下的不同分支
    ClosedLoopControlFacade copy();
}
