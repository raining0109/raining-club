package com.raining.practice.server.config.interceptor;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new FeignRequestInterceptor();
    }

}
