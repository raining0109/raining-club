package com.raining.subject.application.ratelimiter.exception;

import lombok.*;

@Getter
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public final class RateLimiterException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    /**
     * 错误明细，内部调试错误
     */
    @Getter
    private String detailMessage;

    public RateLimiterException(String message) {
        this.message = message;
    }

    public RateLimiterException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public RateLimiterException setMessage(String message) {
        this.message = message;
        return this;
    }

    public RateLimiterException setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }
}

