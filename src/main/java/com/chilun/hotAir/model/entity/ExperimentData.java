package com.chilun.hotAir.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * 实验数据记录表，存储实验的每次输入和输出数据
 *
 * @TableName experiment_data_record
 */
@Data
public class ExperimentData implements Serializable {
    /**
     * 品牌
     */
    private String brand;

    /**
     * 批次号
     */
    private String batchNumber;

    /**
     * 时间戳（时间序列中的时间戳）
     */
    private Date dataTime;

    /**
     * 入口物料水份（%）
     */
    private BigDecimal inletMoisture;

    /**
     * 出口物料水份（%）
     */
    private BigDecimal outletMoisture;

    /**
     * 烟丝瞬时流量
     */
    private BigDecimal tobaccoFlow;

    /**
     * 干头重量
     */
    private BigDecimal dryHeadWeight;

    /**
     * 干尾重量
     */
    private BigDecimal dryTailWeight;

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

    /**
     * 出口水分仪反馈值
     */
    private BigDecimal outletMoistureFeedback;

    /**
     * 热风实际温度
     */
    private BigDecimal actualHotAirTemp;

    /**
     * 排潮开度实际值
     */
    private BigDecimal exhaustOpeningActual;

    /**
     * 环境温度
     */
    private BigDecimal envTemp;

    /**
     * 环境湿度
     */
    private BigDecimal envHumidity;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "ExperimentData{" +
                "brand='" + brand + '\'' +
                ", batchNumber='" + batchNumber + '\'' +
                ", dataTime=" + dataTime +
                ", inletMoisture=" + inletMoisture +
                ", outletMoisture=" + outletMoisture +
                ", tobaccoFlow=" + tobaccoFlow +
                ", dryHeadWeight=" + dryHeadWeight +
                ", dryTailWeight=" + dryTailWeight +
                ", steamPressure=" + steamPressure +
                ", hotAirTemp=" + hotAirTemp +
                ", exhaustFanFreq=" + exhaustFanFreq +
                ", circulationFanFreq=" + circulationFanFreq +
                ", steamValveOpening=" + steamValveOpening +
                ", outletMoistureFeedback=" + outletMoistureFeedback +
                ", actualHotAirTemp=" + actualHotAirTemp +
                ", exhaustOpeningActual=" + exhaustOpeningActual +
                ", envTemp=" + envTemp +
                ", envHumidity=" + envHumidity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ExperimentData that)) return false;
        return Objects.equals(brand, that.brand) && Objects.equals(batchNumber, that.batchNumber) && Objects.equals(dataTime, that.dataTime) && Objects.equals(inletMoisture, that.inletMoisture) && Objects.equals(outletMoisture, that.outletMoisture) && Objects.equals(tobaccoFlow, that.tobaccoFlow) && Objects.equals(dryHeadWeight, that.dryHeadWeight) && Objects.equals(dryTailWeight, that.dryTailWeight) && Objects.equals(steamPressure, that.steamPressure) && Objects.equals(hotAirTemp, that.hotAirTemp) && Objects.equals(exhaustFanFreq, that.exhaustFanFreq) && Objects.equals(circulationFanFreq, that.circulationFanFreq) && Objects.equals(steamValveOpening, that.steamValveOpening) && Objects.equals(outletMoistureFeedback, that.outletMoistureFeedback) && Objects.equals(actualHotAirTemp, that.actualHotAirTemp) && Objects.equals(exhaustOpeningActual, that.exhaustOpeningActual) && Objects.equals(envTemp, that.envTemp) && Objects.equals(envHumidity, that.envHumidity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, batchNumber, dataTime, inletMoisture, outletMoisture, tobaccoFlow, dryHeadWeight, dryTailWeight, steamPressure, hotAirTemp, exhaustFanFreq, circulationFanFreq, steamValveOpening, outletMoistureFeedback, actualHotAirTemp, exhaustOpeningActual, envTemp, envHumidity);
    }
}