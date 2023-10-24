package com.spring.training.mapping;

import com.spring.training.entity.UserEntity;
import com.spring.training.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User toUser(UserEntity userEntity);

}
