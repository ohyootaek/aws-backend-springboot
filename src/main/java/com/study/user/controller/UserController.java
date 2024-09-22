package com.study.user.controller;

import com.study.user.mapstruct.UserMapstruct;
import com.study.user.model.Vo.UserInVo;
import com.study.user.model.Vo.UserOutVo;
import com.study.user.model.dto.UserInDto;
import com.study.user.model.dto.UserResponseDto;
import com.study.user.service.UserService;
import com.study.utils.HashUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
   *
   * @param dto
   * @return UserDto 사용자 정보
   */
  @PostMapping("/login")
  public UserResponseDto postLogin(@RequestBody UserInDto dto) {
    UserInVo vo = userMapstruct.toInUserDto(dto);
    String pwd = vo.getPwd();
    String digest = HashUtil.hashSHA256(pwd);
    vo.setPwd(digest);
    UserOutVo userOutVo = userService.selectUserInfo(vo);
    UserResponseDto responseDto = new UserResponseDto();
    responseDto.setUserInfo(userOutVo);
    return responseDto;
  }
}
