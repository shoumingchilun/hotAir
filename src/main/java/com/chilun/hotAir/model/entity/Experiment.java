package com.chilun.hotAir.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 实验记录表，记录实验的基本信息
 * @TableName experiment
 */
@TableName(value ="experiment")
@Data
public class Experiment implements Serializable {
    /**
     * 实验 ID，唯一标识实验
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 实验名称
     */
    private String experimentName;

    /**
     * 实验状态：0-未开始，1-进行中，2-已结束
     */
    private Integer state;

    /**
     * 实验开始时间（现实时间）
     */
    private LocalDateTime startTime;

    /**
     * 实验描述，存储额外信息，JSON格式
     */
    private String info;

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
        Experiment other = (Experiment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getExperimentName() == null ? other.getExperimentName() == null : this.getExperimentName().equals(other.getExperimentName()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getInfo() == null ? other.getInfo() == null : this.getInfo().equals(other.getInfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getExperimentName() == null) ? 0 : getExperimentName().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getInfo() == null) ? 0 : getInfo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", experimentName=").append(experimentName);
        sb.append(", state=").append(state);
        sb.append(", startTime=").append(startTime);
        sb.append(", info=").append(info);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}