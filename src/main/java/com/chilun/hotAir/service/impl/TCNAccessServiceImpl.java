package com.chilun.hotAir.service.impl;

import com.chilun.hotAir.model.TCNInParam;
import com.chilun.hotAir.model.TCNOutParam;
import com.chilun.hotAir.service.TCNAccessService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 齿轮
 * @date 2025-03-23-21:10
 */

@Service
public class TCNAccessServiceImpl implements TCNAccessService {
    @Override
    public TCNOutParam getNextOutParameter(List<TCNInParam> inParamList) {
        return null;
    }

    @Override
    public List<TCNOutParam> getPredictOutParameter(List<TCNInParam> inParamList) {
        return List.of();
    }
}
