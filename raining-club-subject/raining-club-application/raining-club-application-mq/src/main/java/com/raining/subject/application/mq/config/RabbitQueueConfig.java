package com.raining.subject.application.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitQueueConfig{

    @Bean
    public Queue subjectLikedQueue(){
        return new Queue("subject-liked");
    }

    @Bean
    public Queue testQueue(){
        return new Queue("test.queue");
    }
}