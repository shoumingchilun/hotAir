package com.chilun.hotAir.service.impl;

import com.chilun.hotAir.model.TCNInParam;
import com.chilun.hotAir.model.TCNOutParam;
import com.chilun.hotAir.service.TCNAccessService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 齿轮
 * @date 2025-03-23-21:10
 */

@Service
public class TCNAccessServiceImpl implements TCNAccessService {
    @Override
    public TCNOutParam getNextOutParameter(List<TCNInParam> inParamList) {
        TCNOutParam param = new TCNOutParam();
        param.setActualHotAirTemp(BigDecimal.valueOf(100));
        param.setOutletMoisture(BigDecimal.valueOf(100));
        param.setOutletMoistureFeedback(BigDecimal.valueOf(100));
        param.setExhaustOpeningActual(BigDecimal.valueOf(100));
        return param;
    }

    @Override
    public List<TCNOutParam> getPredictOutParameter(List<TCNInParam> inParamList) {
        return List.of();
    }
}
