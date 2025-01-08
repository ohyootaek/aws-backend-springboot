package com.study.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

  private final RedisTemplate<String, Object> template;
  private final ChannelTopic topic;

  public void saveRedis(String key, Object value) {
    template.opsForValue().set(key, value);
  }

  public Object getRedis(String key) {
    return template.opsForValue().get(key);
  }

  public void allClearRedis() {
    template.getConnectionFactory().getConnection().flushAll();
  }

  public void handleMessage(String message) {
    System.out.println("Received message: " + message);
  }

  // 메시지를 Redis Pub/Sub 채널에 발행
  public void publish(String message) {
    template.convertAndSend(topic.getTopic(), message);
  }
}
