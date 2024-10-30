package com.study.user.model.Vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtTokenVo {
  private String accessToken;
  private String refreshToken;
}
