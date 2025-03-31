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
     * 出口物料水份（%）
     */
    private BigDecimal outletMoisture;
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
}
