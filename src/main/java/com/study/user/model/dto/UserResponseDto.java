package com.study.user.model.dto;

import com.study.user.model.Vo.UserOutVo;
import lombok.Getter;
import lombok.Setter;

/**
 * userDto 사용자 정보 조회 response data
 */
@Getter
@Setter
public class UserResponseDto {

  private UserOutVo userInfo;
}
