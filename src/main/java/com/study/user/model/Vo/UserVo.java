package com.study.user.model.Vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo {
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
