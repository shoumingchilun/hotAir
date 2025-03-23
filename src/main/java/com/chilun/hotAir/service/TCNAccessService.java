package com.chilun.hotAir.service;

import com.chilun.hotAir.model.TCNInParam;
import com.chilun.hotAir.model.TCNOutParam;

import java.util.List;

/**
 * 模拟热风润叶机。提供环境参数，输出下一秒机器的执行结果
 *
 * @author 齿轮
 * @date 2025-03-20-17:52
 */
public interface TCNAccessService {
    //根据历史入参，模拟机器运行，获得下一个时间步的机器的出口参数
    TCNOutParam getNextOutParameter(List<TCNInParam> inParamList);

    //根据历史入参，调用TCN模型，获得接下来一段时间的机器出口参数
    List<TCNOutParam> getPredictOutParameter(List<TCNInParam> inParamList);
}
