package com.study.user.controller;

import com.study.user.mapstruct.UserMapstruct;
import com.study.user.model.dto.UserDto;
import com.study.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private UserMapstruct userMapstruct;

  @GetMapping("/{id}")
  public UserDto getUserInfo(@PathVariable String id) {
   return userMapstruct.toUserDto(userService.selectUserInfo(id));
  }

}
