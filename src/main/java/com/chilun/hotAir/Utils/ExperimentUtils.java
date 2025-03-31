package com.chilun.hotAir.Utils;

import com.chilun.hotAir.model.TCNInParam;
import com.chilun.hotAir.model.TCNOutParam;
import com.chilun.hotAir.model.entity.ExperimentData;
import org.springframework.beans.BeanUtils;

import java.util.Deque;
import java.util.List;

/**
 * @author 齿轮
 * @date 2025-03-31-17:33
 */
public class ExperimentUtils {
    public static List<TCNInParam> getTCNInParam(Deque<ExperimentData> experimentDataList) {
        return experimentDataList.stream().map(ExperimentData::getTcnInParam).toList();
    }

    public static List<TCNOutParam> getTCNOutParam(Deque<ExperimentData> experimentDataList) {
        return experimentDataList.stream().map(ExperimentData::getTcnOutParam).toList();
    }
}
