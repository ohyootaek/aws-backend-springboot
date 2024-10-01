package com.study.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  @Value("${jwt.secret_key}")
  private String SECRET_KEY;

  @Value("${jwt.access_expiration_time}")
  private Long ACCESS_EXPIRATION_TIME;

  @Value("${jwt.reflesh_expiration_time}")
  private Long REFLESH_EXPIRATION_TIME;

  public String generateAccessToken(String userName) {
    return JWT.create()
        .withSubject(userName) // 사용자 이름을 포함한 페이로드
        .withIssuedAt(new Date()) // 토큰 발행 시간
        .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME)) // 만료 시간
        .sign(Algorithm.HMAC256(SECRET_KEY)); // 서명
  }

  public String generateRefleshToken(String userName) {
    return JWT.create()
        .withSubject(userName) // 사용자 이름을 포함한 페이로드
        .withIssuedAt(new Date()) // 토큰 발행 시간
        .withExpiresAt(new Date(System.currentTimeMillis() + REFLESH_EXPIRATION_TIME)) // 만료 시간
        .sign(Algorithm.HMAC256(SECRET_KEY)); // 서명
  }

  public String validateToken(String token) {
    try {
      return JWT.require(Algorithm.HMAC256(SECRET_KEY))
          .build()
          .verify(token)
          .getSubject();
    } catch (JWTVerificationException e) {
      e.getMessage();
      return null;
    }
  }

  public String refleshAccessToken(String refleshToken) {
    try {
      String userName = JWT.require(Algorithm.HMAC256(SECRET_KEY))
          .build()
          .verify(refleshToken)
          .getSubject();
      return generateAccessToken(userName);
    } catch (JWTVerificationException e) {
      e.getMessage();
      return null;
    }
  }

}
