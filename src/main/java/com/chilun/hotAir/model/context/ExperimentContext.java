package com.chilun.hotAir.model.context;

import com.chilun.hotAir.model.ModelHyperParam;
import com.chilun.hotAir.model.entity.ExperimentData;
import lombok.Data;

import java.time.LocalTime;
import java.util.Deque;

/**
 * @author 齿轮
 * @date 2025-03-31-17:07
 */
@Data
public class ExperimentContext {
    private Deque<ExperimentData> oldDataCache;
    private Deque<ExperimentData> psoDataCache;
    private Deque<ExperimentData> manualDataCache;
    private LocalTime time = LocalTime.of(0, 0, 0);
    private long experimentId;
    private ModelHyperParam modelHyperParam;

    public void plusOneStep() {
        time = time.plusSeconds(1);
    }


}
