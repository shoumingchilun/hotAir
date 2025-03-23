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
 * 
 * @TableName production_data
 */
@TableName(value ="production_data")
@Data
public class ProductionData implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String brand;

    /**
     * 
     */
    private String batchNumber;

    /**
     * 
     */
    private Date timestamp;

    /**
     * 
     */
    private BigDecimal inletMoisture;

    /**
     * 
     */
    private BigDecimal outletMoisture;

    /**
     * 
     */
    private BigDecimal tobaccoFlow;

    /**
     * 
     */
    private BigDecimal dryHeadWeight;

    /**
     * 
     */
    private BigDecimal dryTailWeight;

    /**
     * 
     */
    private BigDecimal steamPressure;

    /**
     * 
     */
    private BigDecimal hotAirTemp;

    /**
     * 
     */
    private BigDecimal exhaustFanFreq;

    /**
     * 
     */
    private BigDecimal circulationFanFreq;

    /**
     * 
     */
    private BigDecimal steamValveOpening;

    /**
     * 
     */
    private BigDecimal outletMoistureFeedback;

    /**
     * 
     */
    private BigDecimal actualHotAirTemp;

    /**
     * 
     */
    private BigDecimal exhaustOpeningActual;

    /**
     * 
     */
    private BigDecimal envTemp;

    /**
     * 
     */
    private BigDecimal envHumidity;

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
        ProductionData other = (ProductionData) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBrand() == null ? other.getBrand() == null : this.getBrand().equals(other.getBrand()))
            && (this.getBatchNumber() == null ? other.getBatchNumber() == null : this.getBatchNumber().equals(other.getBatchNumber()))
            && (this.getTimestamp() == null ? other.getTimestamp() == null : this.getTimestamp().equals(other.getTimestamp()))
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
            && (this.getEnvHumidity() == null ? other.getEnvHumidity() == null : this.getEnvHumidity().equals(other.getEnvHumidity()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBrand() == null) ? 0 : getBrand().hashCode());
        result = prime * result + ((getBatchNumber() == null) ? 0 : getBatchNumber().hashCode());
        result = prime * result + ((getTimestamp() == null) ? 0 : getTimestamp().hashCode());
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
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", brand=").append(brand);
        sb.append(", batchNumber=").append(batchNumber);
        sb.append(", timestamp=").append(timestamp);
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}