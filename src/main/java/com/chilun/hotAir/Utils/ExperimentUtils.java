package com.chilun.hotAir.Utils;

import com.chilun.hotAir.model.TCNInParam;
import com.chilun.hotAir.model.TCNOutParam;
import com.chilun.hotAir.model.entity.ExperimentData;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
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

    public static <E> E getSecondLast(Deque<E> deque) {
        if (deque == null || deque.size() < 2) {
            return null; // 不足两个元素，无法获取尾部前一个
        }

        E last = deque.pollLast();      // 移除并保存尾部元素
        E secondLast = deque.peekLast(); // 查看现在的尾部（即原尾部前一个）
        deque.addLast(last);            // 恢复尾部元素

        return secondLast;
    }

    public static List<ExperimentData> expandToLength(List<ExperimentData> originalList, int targetSize) {
        int n = originalList.size();
        if (n == 0 || n > targetSize) {
            throw new IllegalArgumentException("Original list size must be between 1 and targetSize - 1");
        }

        int baseRepeat = targetSize / n;
        int extra = targetSize % n;

        List<ExperimentData> result = new ArrayList<>(targetSize);
        for (int i = 0; i < n; i++) {
            int repeatCount = baseRepeat + (i < extra ? 1 : 0);
            for (int j = 0; j < repeatCount; j++) {
                ExperimentData data = originalList.get(i);
                ExperimentData newData = new ExperimentData();
                BeanUtils.copyProperties(data, newData);
                result.add(newData);
            }
        }

        return result;
    }

}
