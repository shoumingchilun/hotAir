package com.chilun.hotAir.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实验对比结果表，存储 PSO 与人工优化的对比数据
 * @TableName comparison_result
 */
@TableName(value ="comparison_result")
@Data
public class ComparisonResult implements Serializable {
    /**
     * 对比结果 ID，唯一标识每次实验对比
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 实验 ID，表示原始实验
     */
    private Long experimentId;

    /**
     * 评估结果（如误差、能耗等），JSON格式
     */
    private String metricResult;

    /**
     * 执行对比的时间戳（时间序列中的时间戳）
     */
    private Date compareTime;

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
        ComparisonResult other = (ComparisonResult) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getExperimentId() == null ? other.getExperimentId() == null : this.getExperimentId().equals(other.getExperimentId()))
            && (this.getMetricResult() == null ? other.getMetricResult() == null : this.getMetricResult().equals(other.getMetricResult()))
            && (this.getCompareTime() == null ? other.getCompareTime() == null : this.getCompareTime().equals(other.getCompareTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getExperimentId() == null) ? 0 : getExperimentId().hashCode());
        result = prime * result + ((getMetricResult() == null) ? 0 : getMetricResult().hashCode());
        result = prime * result + ((getCompareTime() == null) ? 0 : getCompareTime().hashCode());
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
        sb.append(", metricResult=").append(metricResult);
        sb.append(", compareTime=").append(compareTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}