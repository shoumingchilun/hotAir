package com.chilun.hotAir.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 机器可调整参数
 *
 * @author 齿轮
 * @date 2025-03-22-9:53
 */
@Data
public class MachineAdjustableParam {
    /**
     * 蒸汽压力设定值
     */
    private BigDecimal steamPressure;

    /**
     * 热风温度设定值
     */
    private BigDecimal hotAirTemp;

    /**
     * 排潮风机频率
     */
    private BigDecimal exhaustFanFreq;

    /**
     * 循环风机频率
     */
    private BigDecimal circulationFanFreq;

    /**
     * 蒸汽阀开度
     */
    private BigDecimal steamValveOpening;
}
