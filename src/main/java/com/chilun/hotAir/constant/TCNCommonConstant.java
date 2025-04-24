package com.chilun.hotAir.constant;

/**
 * TCN模型常见参数
 *
 * @author 齿轮
 * @date 2025-03-22-9:41
 */
public class TCNCommonConstant {
    //预测出参所需的入参时间序列长度
    public static int SEQUENCE_LENGTH = 300;

    //预测出参时间序列长度
    public static int PREDICTION_STEPS = 1;

    //模型服务URL
    public static String MODEL_URL = "http://127.0.0.1:8000/";
//    public static String MODEL_URL = "http://127.0.0.1:4523/m1/6226598-0-default/";
}
