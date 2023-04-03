package com.example.recommendations.mapper;

import com.example.recommendations.dto.SignUpDto;
import com.example.recommendations.dto.UserDto;
import com.example.recommendations.entities.UserCredentials;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(UserCredentials userCredentials);

    @Mapping(target = "password", ignore = true)
    UserCredentials signUpToUser(SignUpDto signUpDto);

}