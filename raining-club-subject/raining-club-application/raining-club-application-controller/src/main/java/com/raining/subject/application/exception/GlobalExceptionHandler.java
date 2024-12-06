package com.raining.subject.application.exception;

import com.raining.subject.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> handleGlobalAdviceException(Exception e) {
        log.error("handleGlobalAdviceException.error:{}", e.getMessage(), e);
        return Result.fail("系统繁忙");
    }
}
