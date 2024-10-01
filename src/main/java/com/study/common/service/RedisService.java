package com.study.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

  private final RedisTemplate<String, Object> template;

  public void saveRedis(String key, Object value) {
    template.opsForValue().set(key, value);
  }

  public Object getRedis(String key) {
    return template.opsForValue().get(key);
  }

  public void allClearRedis() {
    template.getConnectionFactory().getConnection().flushAll();
  }
}
