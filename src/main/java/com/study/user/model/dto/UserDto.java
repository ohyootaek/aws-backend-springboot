package com.study.user.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * userDto 사용자 정보 조회 response data
 */
@Getter
@Setter
public class UserDto {
    private String id;
    private String pwd;
    private String phone;
    private String name;
    private String address1;
    private String address2;
    private String email1;
    private String email2;
    private String image_path;
    private String role;
}
