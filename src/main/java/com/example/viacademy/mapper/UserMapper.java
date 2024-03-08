package com.example.viacademy.mapper;

import com.example.viacademy.entities.User;
import com.example.viacademy.web.responses.CreateUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    CreateUserResponse userToCreateUserResponse(User user);
}
