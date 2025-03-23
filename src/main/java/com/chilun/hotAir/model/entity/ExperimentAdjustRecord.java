package com.chilun.hotAir.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实验参数调整记录表，记录实验过程中参数的变化情况
 * @TableName experiment_adjust_record
 */
@TableName(value ="experiment_adjust_record")
@Data
public class ExperimentAdjustRecord implements Serializable {
    /**
     * 调整记录 ID，唯一标识每次参数调整
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 关联的实验 ID，指明本次调整属于哪个实验
     */
    private Long experimentId;

    /**
     * 调整前的参数值，JSON格式
     */
    private String oldValue;

    /**
     * PSO调整后的参数值，JSON格式
     */
    private String psoValue;

    /**
     * 手动调整后的参数值，可为空，JSON格式
     */
    private String manualValue;

    /**
     * 参数调整的时间戳（时间序列中的时间戳）
     */
    private Date adjustTime;

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
        ExperimentAdjustRecord other = (ExperimentAdjustRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getExperimentId() == null ? other.getExperimentId() == null : this.getExperimentId().equals(other.getExperimentId()))
            && (this.getOldValue() == null ? other.getOldValue() == null : this.getOldValue().equals(other.getOldValue()))
            && (this.getPsoValue() == null ? other.getPsoValue() == null : this.getPsoValue().equals(other.getPsoValue()))
            && (this.getManualValue() == null ? other.getManualValue() == null : this.getManualValue().equals(other.getManualValue()))
            && (this.getAdjustTime() == null ? other.getAdjustTime() == null : this.getAdjustTime().equals(other.getAdjustTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getExperimentId() == null) ? 0 : getExperimentId().hashCode());
        result = prime * result + ((getOldValue() == null) ? 0 : getOldValue().hashCode());
        result = prime * result + ((getPsoValue() == null) ? 0 : getPsoValue().hashCode());
        result = prime * result + ((getManualValue() == null) ? 0 : getManualValue().hashCode());
        result = prime * result + ((getAdjustTime() == null) ? 0 : getAdjustTime().hashCode());
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
        sb.append(", oldValue=").append(oldValue);
        sb.append(", psoValue=").append(psoValue);
        sb.append(", manualValue=").append(manualValue);
        sb.append(", adjustTime=").append(adjustTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}