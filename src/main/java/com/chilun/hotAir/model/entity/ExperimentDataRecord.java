package com.chilun.hotAir.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实验数据记录表，存储实验的每次输入和输出数据
 * @TableName experiment_data_record
 */
@TableName(value ="experiment_data_record")
@Data
public class ExperimentDataRecord implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 关联到 experiment 表
     */
    private Long experimentId;

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

    /**
     * 实验数据类型：0-无调整序列，1-手动调整序列，2-PSO优化调整序列
     */
    private Integer listType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ExperimentDataRecord other = (ExperimentDataRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getExperimentId() == null ? other.getExperimentId() == null : this.getExperimentId().equals(other.getExperimentId()))
            && (this.getBrand() == null ? other.getBrand() == null : this.getBrand().equals(other.getBrand()))
            && (this.getBatchNumber() == null ? other.getBatchNumber() == null : this.getBatchNumber().equals(other.getBatchNumber()))
            && (this.getDataTime() == null ? other.getDataTime() == null : this.getDataTime().equals(other.getDataTime()))
            && (this.getInletMoisture() == null ? other.getInletMoisture() == null : this.getInletMoisture().equals(other.getInletMoisture()))
            && (this.getOutletMoisture() == null ? other.getOutletMoisture() == null : this.getOutletMoisture().equals(other.getOutletMoisture()))
            && (this.getTobaccoFlow() == null ? other.getTobaccoFlow() == null : this.getTobaccoFlow().equals(other.getTobaccoFlow()))
            && (this.getDryHeadWeight() == null ? other.getDryHeadWeight() == null : this.getDryHeadWeight().equals(other.getDryHeadWeight()))
            && (this.getDryTailWeight() == null ? other.getDryTailWeight() == null : this.getDryTailWeight().equals(other.getDryTailWeight()))
            && (this.getSteamPressure() == null ? other.getSteamPressure() == null : this.getSteamPressure().equals(other.getSteamPressure()))
            && (this.getHotAirTemp() == null ? other.getHotAirTemp() == null : this.getHotAirTemp().equals(other.getHotAirTemp()))
            && (this.getExhaustFanFreq() == null ? other.getExhaustFanFreq() == null : this.getExhaustFanFreq().equals(other.getExhaustFanFreq()))
            && (this.getCirculationFanFreq() == null ? other.getCirculationFanFreq() == null : this.getCirculationFanFreq().equals(other.getCirculationFanFreq()))
            && (this.getSteamValveOpening() == null ? other.getSteamValveOpening() == null : this.getSteamValveOpening().equals(other.getSteamValveOpening()))
            && (this.getOutletMoistureFeedback() == null ? other.getOutletMoistureFeedback() == null : this.getOutletMoistureFeedback().equals(other.getOutletMoistureFeedback()))
            && (this.getActualHotAirTemp() == null ? other.getActualHotAirTemp() == null : this.getActualHotAirTemp().equals(other.getActualHotAirTemp()))
            && (this.getExhaustOpeningActual() == null ? other.getExhaustOpeningActual() == null : this.getExhaustOpeningActual().equals(other.getExhaustOpeningActual()))
            && (this.getEnvTemp() == null ? other.getEnvTemp() == null : this.getEnvTemp().equals(other.getEnvTemp()))
            && (this.getEnvHumidity() == null ? other.getEnvHumidity() == null : this.getEnvHumidity().equals(other.getEnvHumidity()))
            && (this.getListType() == null ? other.getListType() == null : this.getListType().equals(other.getListType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getExperimentId() == null) ? 0 : getExperimentId().hashCode());
        result = prime * result + ((getBrand() == null) ? 0 : getBrand().hashCode());
        result = prime * result + ((getBatchNumber() == null) ? 0 : getBatchNumber().hashCode());
        result = prime * result + ((getDataTime() == null) ? 0 : getDataTime().hashCode());
        result = prime * result + ((getInletMoisture() == null) ? 0 : getInletMoisture().hashCode());
        result = prime * result + ((getOutletMoisture() == null) ? 0 : getOutletMoisture().hashCode());
        result = prime * result + ((getTobaccoFlow() == null) ? 0 : getTobaccoFlow().hashCode());
        result = prime * result + ((getDryHeadWeight() == null) ? 0 : getDryHeadWeight().hashCode());
        result = prime * result + ((getDryTailWeight() == null) ? 0 : getDryTailWeight().hashCode());
        result = prime * result + ((getSteamPressure() == null) ? 0 : getSteamPressure().hashCode());
        result = prime * result + ((getHotAirTemp() == null) ? 0 : getHotAirTemp().hashCode());
        result = prime * result + ((getExhaustFanFreq() == null) ? 0 : getExhaustFanFreq().hashCode());
        result = prime * result + ((getCirculationFanFreq() == null) ? 0 : getCirculationFanFreq().hashCode());
        result = prime * result + ((getSteamValveOpening() == null) ? 0 : getSteamValveOpening().hashCode());
        result = prime * result + ((getOutletMoistureFeedback() == null) ? 0 : getOutletMoistureFeedback().hashCode());
        result = prime * result + ((getActualHotAirTemp() == null) ? 0 : getActualHotAirTemp().hashCode());
        result = prime * result + ((getExhaustOpeningActual() == null) ? 0 : getExhaustOpeningActual().hashCode());
        result = prime * result + ((getEnvTemp() == null) ? 0 : getEnvTemp().hashCode());
        result = prime * result + ((getEnvHumidity() == null) ? 0 : getEnvHumidity().hashCode());
        result = prime * result + ((getListType() == null) ? 0 : getListType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", experimentId=").append(experimentId);
        sb.append(", brand=").append(brand);
        sb.append(", batchNumber=").append(batchNumber);
        sb.append(", dataTime=").append(dataTime);
        sb.append(", inletMoisture=").append(inletMoisture);
        sb.append(", outletMoisture=").append(outletMoisture);
        sb.append(", tobaccoFlow=").append(tobaccoFlow);
        sb.append(", dryHeadWeight=").append(dryHeadWeight);
        sb.append(", dryTailWeight=").append(dryTailWeight);
        sb.append(", steamPressure=").append(steamPressure);
        sb.append(", hotAirTemp=").append(hotAirTemp);
        sb.append(", exhaustFanFreq=").append(exhaustFanFreq);
        sb.append(", circulationFanFreq=").append(circulationFanFreq);
        sb.append(", steamValveOpening=").append(steamValveOpening);
        sb.append(", outletMoistureFeedback=").append(outletMoistureFeedback);
        sb.append(", actualHotAirTemp=").append(actualHotAirTemp);
        sb.append(", exhaustOpeningActual=").append(exhaustOpeningActual);
        sb.append(", envTemp=").append(envTemp);
        sb.append(", envHumidity=").append(envHumidity);
        sb.append(", listType=").append(listType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}