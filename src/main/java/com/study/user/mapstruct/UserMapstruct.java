package com.study.user.mapstruct;

import com.study.user.model.Vo.UserVo;
import com.study.user.model.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapstruct {
  UserDto toUserDto(UserVo userVo);
}
