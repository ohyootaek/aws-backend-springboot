package com.study.user.model.Vo;

import lombok.Getter;
import lombok.Setter;

/**
 * userVo 사용자정보 조회 outVo
 */
@Getter
@Setter
public class UserOutVo {

  private String id;
  private String pwd;
  private String phone;
  private String name;
  private String address;
  private String email;
  private String image_path;
  private String role;
}
