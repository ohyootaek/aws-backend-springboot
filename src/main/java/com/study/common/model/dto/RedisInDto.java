package com.study.common.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisInDto {
  private String key;
  private Object value;
}
