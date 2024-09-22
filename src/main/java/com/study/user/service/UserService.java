package com.study.user.service;

import com.study.user.mapper.UserMapper;
import com.study.user.model.Vo.UserInVo;
import com.study.user.model.Vo.UserOutVo;
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
   *
   * @param vo id 아이디, pwd 비번
   * @return UserVo 사용자 정보
   */
  public UserOutVo selectUserInfo(UserInVo vo) {
    return userMapper.selectUserInfo(vo);
  }
}
