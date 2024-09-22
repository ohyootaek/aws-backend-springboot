package com.study.user.mapstruct;

import com.study.user.model.Vo.UserInVo;
import com.study.user.model.Vo.UserOutVo;
import com.study.user.model.dto.UserInDto;
import com.study.user.model.dto.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapstruct {

  UserInVo toInUserDto(UserInDto dto);

}
