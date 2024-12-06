package com.raining.subject.domain.cache.config;

import com.raining.subject.domain.cache.cache.DoubleCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfig {
    @Autowired
    DoubleCacheConfig doubleCacheConfig;

    @Bean
    public DoubleCacheManager cacheManager(RedisTemplate<String,Object> redisTemplate,
                                           DoubleCacheConfig doubleCacheConfig){
        return new DoubleCacheManager(redisTemplate,doubleCacheConfig);
    }
}
