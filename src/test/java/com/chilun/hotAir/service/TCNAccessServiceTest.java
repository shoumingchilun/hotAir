package com.chilun.hotAir.service;

import com.chilun.hotAir.Utils.JsonUtils;
import com.chilun.hotAir.model.TCNInParam;
import com.chilun.hotAir.model.TCNOutParam;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author 齿轮
 * @date 2025-04-15-23:50
 */
@SpringBootTest
public class TCNAccessServiceTest {

    @Resource
    TCNAccessService service;

    @Test
    public void test() {
        String json =
                "    {\n" +
                "        \"brand\": \"Larry\",\n" +
                "        \"batchNumber\": \"2012-10-06 AM 01:10:32\",\n" +
                "        \"inletMoisture\": 21.685,\n" +
                "        \"tobaccoFlow\": 25.487,\n" +
                "        \"dryHeadWeight\": 22,\n" +
                "        \"dryTailWeight\": 24.7275,\n" +
                "        \"envTemp\": 20.226072815856114,\n" +
                "        \"envHumidity\": 24.412768110638147,\n" +
                "        \"hotAirTemp\": 21.666843045724313,\n" +
                "        \"exhaustFanFreq\": 24,\n" +
                "        \"circulationFanFreq\": 21.515262137418723,\n" +
                "        \"steamValveOpening\": 24.4477559749967,\n" +
                "        \"steamPressure\": 23.83395126\n" +
                "    }";
        TCNInParam tcnInParam = JsonUtils.fromJson(json, TCNInParam.class);
        TCNOutParam outParameter = service.getNextOutParameter(Arrays.asList(tcnInParam));
        System.out.println(JsonUtils.toJson(outParameter));
    }
}
