package com.study.user.controller;

import com.study.user.mapstruct.UserMapstruct;
import com.study.user.model.Vo.JwtTokenVo;
import com.study.user.model.Vo.UserInVo;
import com.study.user.model.Vo.UserOutVo;
import com.study.user.model.dto.UserInDto;
import com.study.user.model.dto.UserResponseDto;
import com.study.user.service.UserService;
import com.study.utils.HashUtil;
import com.study.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  private final JwtUtil jwtUtil;

  /**
   * 사용자 정보 조회
   *
   * @param dto
   * @return UserDto 사용자 정보
   */
  @PostMapping("/login")
  public ResponseEntity<UserResponseDto> postLogin(@RequestBody UserInDto dto) {
    /*vo 변환*/
    UserInVo vo = userMapstruct.toInUserDto(dto);

    /*암호화 후 사용자 정보 조회*/
    String pwd = vo.getPwd();
    String digest = HashUtil.hashSHA256(pwd);
    vo.setPwd(digest);
    UserOutVo userOutVo = userService.selectUserInfo(vo);

    /*jwt token 생성*/
    JwtTokenVo jwtTokenVo = new JwtTokenVo();
    if (userOutVo != null) {
      String accessToken = jwtUtil.generateAccessToken(userOutVo.getId());
      String refleshToken = jwtUtil.generateRefleshToken(userOutVo.getId());
      jwtTokenVo.setAccessToken(accessToken);
      jwtTokenVo.setRefreshToken(refleshToken);

      UserResponseDto responseDto = new UserResponseDto();
      responseDto.setUserInfo(userOutVo);
      responseDto.setJwtToken(jwtTokenVo);

      return ResponseEntity.ok(responseDto);
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
  }

  @PostMapping("/refresh")
  public ResponseEntity<JwtTokenVo> postRefreshToken(@RequestBody String refreshToken) {

    String token = jwtUtil.refleshAccessToken(refreshToken);
    if(token != null) {
      String userId = jwtUtil.validateToken(token);
      JwtTokenVo tokenVo = new JwtTokenVo();
      String accessToken = jwtUtil.generateAccessToken(userId);
      String refleshToken = jwtUtil.generateRefleshToken(userId);
      tokenVo.setAccessToken(accessToken);
      tokenVo.setRefreshToken(refleshToken);
      return ResponseEntity.ok(tokenVo);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
  }

}
