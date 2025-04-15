package com.chilun.hotAir.service;

import com.alibaba.fastjson2.JSON;
import com.chilun.hotAir.Utils.JsonUtils;
import com.chilun.hotAir.model.MachineAdjustableParam;
import com.chilun.hotAir.model.TCNInParam;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author 齿轮
 * @date 2025-04-15-23:55
 */
@SpringBootTest
public class PSOAccessServiceTest {
    @Resource
    PSOAccessService psoAccessService;

    @Test
    public void test() {
        String json = "    {\n" +
                "        \"brand\": \"Edward\",\n" +
                "        \"batchNumber\": \"2002-10-15 AM 07:13:14\",\n" +
                "        \"inletMoisture\": 21.74,\n" +
                "        \"tobaccoFlow\": 21.971,\n" +
                "        \"dryHeadWeight\": 21.855,\n" +
                "        \"dryTailWeight\": 21.675,\n" +
                "        \"envTemp\": 25.238,\n" +
                "        \"envHumidity\": 24.136,\n" +
                "        \"hotAirTemp\": 20.722,\n" +
                "        \"exhaustFanFreq\": 24.92,\n" +
                "        \"circulationFanFreq\": 24.773,\n" +
                "        \"steamValveOpening\": 23.31,\n" +
                "        \"steamPressure\": 20.36\n" +
                "    }";
        TCNInParam tcnInParam = JSON.parseObject(json, TCNInParam.class);
        List<TCNInParam> list = Arrays.asList(tcnInParam);
        MachineAdjustableParam betterParameter = psoAccessService.getBetterParameter(list);
        System.out.println(JsonUtils.toJson(betterParameter));
    }
}
