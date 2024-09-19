package com.study.user.mapper;

import com.study.user.model.Vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 사용자정보 조회
     * @param id
     * @return UserVo 사용자 정보
     */
    UserVo selectUserInfo(String id);
}
