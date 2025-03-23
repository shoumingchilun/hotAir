package com.chilun.hotAir.Utils;

import com.alibaba.fastjson2.JSON;

/**
 * @author 齿轮
 * @date 2025-03-23-17:34
 */
public class JsonUtils {
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static  <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }
}
