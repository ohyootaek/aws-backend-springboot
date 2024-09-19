package com.study.user.service;

import com.study.user.mapper.UserMapper;
import com.study.user.model.Vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
  private final UserMapper userMapper;

  /**
   * 사용자정보 조회
   * @param id 아이디
   * @return UserVo 사용자 정보
   */
  public UserVo selectUserInfo(String id) {
    return userMapper.selectUserInfo(id);
  }
}
