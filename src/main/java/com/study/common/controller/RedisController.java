package com.study.common.controller;

import com.study.common.model.dto.RedisInDto;
import com.study.common.model.vo.RedisInVo;
import com.study.common.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {

  private final RedisService redisService;

  /**
   * redis 조회
   *
   * @param key
   * @return Object
   */
  @GetMapping("/{key}")
  public Object getRedis(@PathVariable String key) {
    try {
      Object value = redisService.getRedis(key);
      if (value != null) {
        return ResponseEntity.ok(value); // 200 OK, 값이 존재할 경우
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(null); // 500 Internal Server Error
    }
  }

  /**
   * redis 저장
   *
   * @param dto
   */
  @PutMapping("/save")
  public ResponseEntity<Void> putRedis(@RequestBody RedisInDto dto) {
    try {
      RedisInVo vo = new RedisInVo();
      vo.setKey(dto.getKey());
      vo.setValue(dto.getValue());
      redisService.saveRedis(vo.getKey(), vo.getValue());

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500
    }
  }

  @DeleteMapping("/clear")
  public ResponseEntity<Void> deleteRedis() {
    try {
      redisService.allClearRedis();

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500
    }
  }

  @PostMapping("/publish")
  public String publishMessage(@RequestParam String message) {
    redisService.publish(message);
    return message;
  }
}
