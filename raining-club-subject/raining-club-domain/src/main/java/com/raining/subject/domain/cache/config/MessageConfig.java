package com.raining.subject.domain.cache.config;

import com.raining.subject.domain.cache.msg.RedisMessageReceiver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class MessageConfig {
    public static final String TOPIC="cache.msg";

    @Bean
    RedisMessageListenerContainer container(MessageListenerAdapter listenerAdapter,
                                            RedisConnectionFactory redisConnectionFactory){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic(TOPIC));
        return container;
    }

    @Bean
    MessageListenerAdapter adapter(RedisMessageReceiver receiver){
        return new MessageListenerAdapter(receiver,"receive");
    }
}
