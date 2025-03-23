package com.chilun.hotAir.service.impl;

import com.chilun.hotAir.model.MachineAdjustableParam;
import com.chilun.hotAir.model.TCNInParam;
import com.chilun.hotAir.service.PSOAccessService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 齿轮
 * @date 2025-03-23-21:11
 */
@Service
public class PSOAccessServiceImpl implements PSOAccessService {
    @Override
    public List<MachineAdjustableParam> getBetterParameter(List<TCNInParam> inParamList) {
        return List.of();
    }
}