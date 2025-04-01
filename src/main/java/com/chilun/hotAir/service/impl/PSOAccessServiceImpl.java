package com.chilun.hotAir.service.impl;

import com.chilun.hotAir.model.MachineAdjustableParam;
import com.chilun.hotAir.model.TCNInParam;
import com.chilun.hotAir.service.PSOAccessService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 齿轮
 * @date 2025-03-23-21:11
 */
@Service
public class PSOAccessServiceImpl implements PSOAccessService {
    @Override
    public MachineAdjustableParam getBetterParameter(List<TCNInParam> inParamList) {
        MachineAdjustableParam param = new MachineAdjustableParam();
        param.setCirculationFanFreq(BigDecimal.valueOf(50));
        param.setHotAirTemp(BigDecimal.valueOf(70));
        param.setExhaustFanFreq(BigDecimal.valueOf(50));
        param.setSteamPressure(BigDecimal.valueOf(70));
        param.setSteamValveOpening(BigDecimal.valueOf(70));
        return param;
    }
}