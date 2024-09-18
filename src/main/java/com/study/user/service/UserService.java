package com.study.user.service;

import com.study.user.mapper.UserMapper;
import com.study.user.model.Vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserMapper userMapper;

  public UserVo selectUserInfo(String id) {
    return userMapper.selectUserInfo(id);
  }
}
