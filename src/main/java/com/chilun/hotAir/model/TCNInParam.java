package com.chilun.hotAir.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * TCN模型入参接口
 *
 * @author 齿轮
 * @date 2025-03-22-9:40
 */
public class TCNInParam {
    private String brand;
    private String batchNumber;
    private BigDecimal inletMoisture;
    private BigDecimal tobaccoFlow;
    private BigDecimal dryHeadWeight;
    private BigDecimal dryTailWeight;
    private BigDecimal envTemp;
    private BigDecimal envHumidity;
    private BigDecimal hotAirTemp;
    private BigDecimal exhaustFanFreq;
    private BigDecimal circulationFanFreq;
    private BigDecimal steamValveOpening;
    private BigDecimal steamPressure;
}
