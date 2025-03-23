package com.chilun.hotAir.utils;

import com.chilun.hotAir.Utils.OkHttpUtils;
import com.chilun.hotAir.constant.TCNCommonConstant;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 齿轮
 * @date 2025-03-24-0:20
 */
@SpringBootTest
public class httpUtilsTest {
    @Test
    public void test() {
        System.out.println(OkHttpUtils.getPostBody(TCNCommonConstant.MODEL_URL + "predict", new Object()));
    }
}
