package com.study.user.controller;

import com.study.user.mapstruct.UserMapstruct;
import com.study.user.model.dto.UserDto;
import com.study.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final UserMapstruct userMapstruct;

  /**
   * 사용자 정보 조회
   * @param id
   * @return UserDto 사용자 정보
   */
  @GetMapping("/{id}")
  public UserDto getUserInfo(@PathVariable String id) {
   return userMapstruct.toUserDto(userService.selectUserInfo(id));
  }

}
