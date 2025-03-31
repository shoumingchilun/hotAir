package com.chilun.hotAir.model.dto.experiment;

import com.chilun.hotAir.model.MachineAdjustableParam;
import lombok.Data;

/**
 * @author 齿轮
 * @date 2025-03-31-21:01
 */
@Data
public class ExperimentAdjustRecordRequest {
    private MachineAdjustableParam psoParam;
    private MachineAdjustableParam manualParam;
}
