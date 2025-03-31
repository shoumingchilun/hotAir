package com.chilun.hotAir.model;

import lombok.Data;

/**
 * 模型超参数，包含TCN部分参数，包含PSO算法参数
 *
 * @author 齿轮
 * @date 2025-03-23-17:07
 */
@Data
public class ModelHyperParam {
    //预期指标
    //可调整参数上下界
    //粒子群大小
    private int numParticles;
    //最大迭代次数
    private int maxIter;
    //惯性权重
    private int inertiaWeight;
    //认知权重
    private int cognitiveWeight;
    //社会权重
    private int socialWeight;
}
