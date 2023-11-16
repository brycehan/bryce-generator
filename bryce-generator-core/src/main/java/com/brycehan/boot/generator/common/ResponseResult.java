package com.brycehan.boot.generator.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 响应结果
 *
 * @author Bryce Han
 * @since 2021/12/31
 */
@Slf4j
@Data
@Builder
@AllArgsConstructor
public class ResponseResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 响应编码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime time;

    /**
     * 正常响应结果
     */
    public static final ResponseResult<Object> ok = ResponseResult.builder()
            .code(200).message("操作成功").build();

    /**
     * 系统内部错误响应结果
     */
    public static final ResponseResult<Object> error = ResponseResult.builder()
            .code(500).message("服务器异常，请稍后再试").build();

    public ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseResult<T> ok() {
        return new ResponseResult<>(DataConstants.ok, "操作成功");
    }

    public static <T> ResponseResult<T> ok(T data) {
        return new ResponseResult<>(DataConstants.ok, "操作成功", data);
    }

    public static <T> ResponseResult<T> error(String message) {
        return new ResponseResult<>(DataConstants.error, message);
    }
}
