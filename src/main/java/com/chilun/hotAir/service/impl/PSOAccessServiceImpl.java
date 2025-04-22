package com.chilun.hotAir.service.impl;

import com.chilun.hotAir.Utils.JsonUtils;
import com.chilun.hotAir.Utils.OkHttpUtils;
import com.chilun.hotAir.model.MachineAdjustableParam;
import com.chilun.hotAir.model.ModelHyperParam;
import com.chilun.hotAir.model.TCNInParam;
import com.chilun.hotAir.service.PSOAccessService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.chilun.hotAir.constant.TCNCommonConstant.MODEL_URL;

/**
 * @author 齿轮
 * @date 2025-03-23-21:11
 */
@Service
public class PSOAccessServiceImpl implements PSOAccessService {
//    @Override
//    public MachineAdjustableParam getBetterParameterMock(List<TCNInParam> inParamList) {
//        MachineAdjustableParam param = new MachineAdjustableParam();
//        param.setCirculationFanFreq(BigDecimal.valueOf(50));
//        param.setHotAirTemp(BigDecimal.valueOf(70));
//        param.setExhaustFanFreq(BigDecimal.valueOf(50));
//        param.setSteamPressure(BigDecimal.valueOf(70));
//        param.setSteamValveOpening(BigDecimal.valueOf(70));
//        return param;
//    }

    @Override
    public MachineAdjustableParam getBetterParameter(List<TCNInParam> inParamList) {
        Map<String, List> map = new HashMap<>();
        map.put("input_data", inParamList);
        String body = OkHttpUtils.getPostBody(MODEL_URL + "optimize_parameters", map);
        return JsonUtils.fromJson(body, MachineAdjustableParam.class);
    }

    @Override
    public MachineAdjustableParam getBetterParameterWithDetail(List<TCNInParam> inParamList, ModelHyperParam modelHyperParam) {
        Map<String, Object> map = new HashMap<>();
        map.put("input_data", inParamList);
        map.put("hyper_param", modelHyperParam);
        String body = OkHttpUtils.getPostBody(MODEL_URL + "optimize_parameters", map);
        return JsonUtils.fromJson(body, MachineAdjustableParam.class);
    }
}