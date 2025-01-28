package com.study.common.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.common.handler.MyWebSocketHandler;
import com.study.common.model.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

  private final RedisTemplate<String, Object> template;
  private final ChannelTopic topic;
  private final ObjectMapper objectMapper;

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
    MyWebSocketHandler.sendToAllClients(message);
    System.out.println("Received message: " + message);
  }

  // 메시지를 Redis Pub/Sub 채널에 발행
  public void publish(ChatMessageDto chatMessageDto) {
    try {
      String message = objectMapper.writeValueAsString(chatMessageDto);
      template.convertAndSend(topic.getTopic(), message);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
