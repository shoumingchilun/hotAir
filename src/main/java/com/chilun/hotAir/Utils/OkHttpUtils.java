package com.chilun.hotAir.Utils;

import com.chilun.hotAir.exception.ErrorCode;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author 齿轮
 * @date 2025-03-24-0:03
 */
public class OkHttpUtils {

    // Define timeout constants (good practice for configurability)
    private static final long CONNECT_TIMEOUT = 10; // Connection timeout in seconds
    private static final long READ_TIMEOUT = 120;    // Read timeout in seconds
    private static final long WRITE_TIMEOUT = 120;   // Write timeout in seconds
    private static final TimeUnit TIMEOUT_UNIT = TimeUnit.SECONDS; // Unit for timeouts
    static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TIMEOUT_UNIT) // Set connection timeout
            .readTimeout(READ_TIMEOUT, TIMEOUT_UNIT)       // Set read timeout
            .writeTimeout(WRITE_TIMEOUT, TIMEOUT_UNIT)      // Set write timeout
            // You can add other configurations here (e.g., connection pool, interceptors)
            .build();

    public static String getPostBody(String url, Object jsonObject) {
        // 1. 创建 OkHttpClient 实例

        // 2. 定义 JSON 请求体
        String json = JsonUtils.toJson(jsonObject);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(json, JSON);

        // 3. 构建 Request 对象
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Content-Type", "application/json") // 明确设置 Content-Type
                .build();

        // 4. 发送请求（同步方式）
        try (Response response = client.newCall(request).execute()) {
            // 检查响应是否成功
            ThrowUtils.throwIf(!response.isSuccessful(), ErrorCode.SYSTEM_ERROR, "远程调用失败（检查网络情况或下游服务是否启动）");
            // 获取响应内容
            ThrowUtils.throwIf(Objects.isNull(response.body()), ErrorCode.SYSTEM_ERROR, "远程调用响应为空");
            return response.body().string();
        } catch (IOException e) {
            ThrowUtils.throwIf(true, ErrorCode.SYSTEM_ERROR, e.getMessage());
        }
        return null;
    }
}
