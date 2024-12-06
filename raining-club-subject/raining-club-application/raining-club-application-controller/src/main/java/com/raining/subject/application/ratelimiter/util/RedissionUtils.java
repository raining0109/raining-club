package com.raining.subject.application.ratelimiter.util;

import com.raining.subject.domain.cache.util.SpringContextUtil;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;

public class RedissionUtils {
  
    private static final RedissonClient CLIENT = SpringContextUtil.getBean(RedissonClient.class);
  
    /**  
     * 限流  
     *  
     * @param key          限流key  
     * @param rateType     限流类型  
     * @param rate         速率  
     * @param rateInterval 速率间隔  
     * @return -1 表示失败  
     */  
    public static long rateLimiter(String key, RateType rateType, int rate, int rateInterval) {
        RRateLimiter rateLimiter = CLIENT.getRateLimiter(key);
        rateLimiter.trySetRate(rateType, rate, rateInterval, RateIntervalUnit.SECONDS);
        if (rateLimiter.tryAcquire()) {  
            return rateLimiter.availablePermits();  
        } else {  
            return -1L;  
        }  
    }

    /**
     * 获取客户端实例
     */
    public static RedissonClient getClient() {
        return CLIENT;
    }
}