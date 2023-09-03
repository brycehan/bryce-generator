package com.brycehan.generator.handler;


import com.brycehan.generator.core.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * 服务器异常处理
 *
 * @author Bryce Han
 * @since 2022/5/6
 */
@Slf4j
@RestControllerAdvice
public class ServerExceptionHandler {

    /**
     * 数据绑定异常处理
     *
     * @param ex 数据绑定异常
     * @return 响应结果
     */
    @ExceptionHandler(BindException.class)
    public ResponseResult<Void> bindException(BindException ex) {
        String message = ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
        log.error("参数绑定异常", ex);
        return ResponseResult.error(message);
    }


    /**
     * 通用异常处理
     *
     * @param ex 异常
     * @return 响应结果
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult<Void> exceptionHandler(Exception ex) {
        log.error("系统内部异常", ex);
        return ResponseResult.error(ex.getMessage());
    }

}
