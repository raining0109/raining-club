package com.raining.subject.domain.cache.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "doublecache")
public class DoubleCacheConfig {
    private Boolean allowNull = true;
    private Integer init = 100;
    private Integer max = 1000;
    private Long expireAfterWrite ;
    private Long expireAfterAccess;
    private Long refreshAfterWrite;
    private Long redisExpire;
}
