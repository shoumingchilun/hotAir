package com.chilun.hotAir.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * TCN模型出参接口
 *
 * @author 齿轮
 * @date 2025-03-22-9:40
 */
@Data
public class TCNOutParam {
    /**
     * 出口水分仪反馈值
     */
    private BigDecimal outletMoistureFeedback;

    /**
     * 出口温度
     */
    private BigDecimal actualHotAirTemp;
}
