package com.chilun.hotAir.service;

import com.chilun.hotAir.Utils.JsonUtils;
import com.chilun.hotAir.Utils.ThrowUtils;
import com.chilun.hotAir.model.entity.Experiment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static com.chilun.hotAir.exception.ErrorCode.SYSTEM_ERROR;

/**
 * @author 齿轮
 * @date 2024-01-25-12:15
 */
@SpringBootTest
class ExperimentServiceTest {

    @Autowired
    ExperimentService service;

    @Test
    void experimentRegister() {
        Experiment experiment = new Experiment();
        experiment.setState(0);
        experiment.setInfo(JsonUtils.toJson(new Object()));
        experiment.setStartTime(LocalDateTime.now());
        experiment.setExperimentName("test1");

        boolean saved = service.save(experiment);
        ThrowUtils.throwIf(!saved, SYSTEM_ERROR, "创建实验失败");
    }
}