package com.chilun.hotAir.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chilun.hotAir.model.MachineAdjustableParam;
import com.chilun.hotAir.model.TCNInParam;
import com.chilun.hotAir.model.TCNOutParam;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * TCN入参+出参
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
                ", envTemp=" + envTemp +
                ", envHumidity=" + envHumidity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ExperimentData that)) return false;
        return Objects.equals(brand, that.brand) && Objects.equals(batchNumber, that.batchNumber) && Objects.equals(dataTime, that.dataTime) && Objects.equals(inletMoisture, that.inletMoisture) && Objects.equals(tobaccoFlow, that.tobaccoFlow) && Objects.equals(dryHeadWeight, that.dryHeadWeight) && Objects.equals(dryTailWeight, that.dryTailWeight) && Objects.equals(steamPressure, that.steamPressure) && Objects.equals(hotAirTemp, that.hotAirTemp) && Objects.equals(exhaustFanFreq, that.exhaustFanFreq) && Objects.equals(circulationFanFreq, that.circulationFanFreq) && Objects.equals(steamValveOpening, that.steamValveOpening) && Objects.equals(outletMoistureFeedback, that.outletMoistureFeedback) && Objects.equals(actualHotAirTemp, that.actualHotAirTemp) &&  Objects.equals(envTemp, that.envTemp) && Objects.equals(envHumidity, that.envHumidity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, batchNumber, dataTime, inletMoisture, tobaccoFlow, dryHeadWeight, dryTailWeight, steamPressure, hotAirTemp, exhaustFanFreq, circulationFanFreq, steamValveOpening, outletMoistureFeedback, actualHotAirTemp, envTemp, envHumidity);
    }

    public void fill(TCNOutParam outParam) {
        BeanUtils.copyProperties(outParam, this);
    }

    public void fill(TCNInParam inParam) {
        BeanUtils.copyProperties(inParam, this);
    }

    public void fill(MachineAdjustableParam param) {
        BeanUtils.copyProperties(param, this);
    }

    public TCNInParam getTcnInParam() {
        TCNInParam inParam = new TCNInParam();
        BeanUtils.copyProperties(this, inParam);
        return inParam;
    }

    public TCNOutParam getTcnOutParam() {
        TCNOutParam outParam = new TCNOutParam();
        BeanUtils.copyProperties(this, outParam);
        return outParam;
    }

    public MachineAdjustableParam getMachineAdjustableParam() {
        MachineAdjustableParam param = new MachineAdjustableParam();
        BeanUtils.copyProperties(this, param);
        return param;
    }
}