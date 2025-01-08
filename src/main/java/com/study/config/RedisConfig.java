package com.study.config;

import com.study.common.service.RedisService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(factory);
    //StringRedisSerializer를 설정하면 가독성이 높은 문자열 형태로 key를 저장할 수 있습니다.
    template.setKeySerializer(new StringRedisSerializer());
    template.setHashValueSerializer(new StringRedisSerializer());

    return template;
  }

  @Bean
  public RedisMessageListenerContainer redisMessageListenerContainer(
      RedisConnectionFactory connectionFactory,
      MessageListenerAdapter messageListenerAdapter
  ) {
    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.addMessageListener(messageListenerAdapter, topic());
    return container;
  }

  @Bean
  public MessageListenerAdapter messageListenerAdapter(RedisService redisSubscriber) {
    return new MessageListenerAdapter(redisSubscriber, "handleMessage"); // handleMessage 메서드로 메시지 처리
  }

  @Bean
  public ChannelTopic topic() {
    return new ChannelTopic("chatroom");
  }
}
