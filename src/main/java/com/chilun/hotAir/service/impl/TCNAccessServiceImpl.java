package com.chilun.hotAir.service.impl;

import com.chilun.hotAir.Utils.JsonUtils;
import com.chilun.hotAir.Utils.OkHttpUtils;
import com.chilun.hotAir.model.MachineAdjustableParam;
import com.chilun.hotAir.model.TCNInParam;
import com.chilun.hotAir.model.TCNOutParam;
import com.chilun.hotAir.service.TCNAccessService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.chilun.hotAir.constant.TCNCommonConstant.MODEL_URL;

/**
 * @author 齿轮
 * @date 2025-03-23-21:10
 */

@Service
public class TCNAccessServiceImpl implements TCNAccessService {
//    @Override
//    public TCNOutParam getNextOutParameterMock(List<TCNInParam> inParamList) {
//        TCNOutParam param = new TCNOutParam();
//        param.setActualHotAirTemp(BigDecimal.valueOf(100));
//        param.setOutletMoisture(BigDecimal.valueOf(100));
//        param.setOutletMoistureFeedback(BigDecimal.valueOf(100));
//        param.setExhaustOpeningActual(BigDecimal.valueOf(100));
//        return param;
//    }

    @Override
    public TCNOutParam getNextOutParameter(List<TCNInParam> inParamList) {
        Map<String, List> map = new HashMap<>();
        map.put("input_data", inParamList);
        String body = OkHttpUtils.getPostBody(MODEL_URL + "predict", map);
        return JsonUtils.fromJson(body,TCNOutParam.class);
    }

    @Override
    public List<TCNOutParam> getPredictOutParameter(List<TCNInParam> inParamList) {
        return List.of();
    }
}
