package com.study.user.mapper;

import com.study.user.model.Vo.UserInVo;
import com.study.user.model.Vo.UserOutVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  /**
   * 사용자정보 조회
   *
   * @param vo
   * @return UserVo 사용자 정보
   */
  UserOutVo selectUserInfo(UserInVo vo);
}
