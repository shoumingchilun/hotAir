package com.chilun.hotAir.model;

import com.chilun.hotAir.model.entity.ExperimentData;
import lombok.Data;

import java.util.List;

/**
 * 系统初始化参数接口，包含：
 * 1. 预测模型相关超参数
 * 2. 预设的模型预测所需参数
 *
 * @author 齿轮
 * @date 2025-03-22-10:09
 */
@Data
public class SystemInitParam {
    //模型相关超参数
    private ModelHyperParam modelHyperParam;
    //模型预测所需参数
    private List<ExperimentData> systemInitParam;
    //实验名
    private String experimentName;
}
