package com.study.user.mapper;

import com.study.user.model.Vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserVo selectUserInfo(String id);
}
