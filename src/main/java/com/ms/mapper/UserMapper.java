package com.ms.mapper;

import com.ms.model.UserEntity;
import com.ms.model.UserResponseDto;
import com.ms.model.UserSaveResponseDto;
import com.ms.model.dto.UserRequestDto;
import com.ms.util.Util;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring" , imports = {Util.class} , uses = PhoneMapper.class)
public interface UserMapper {
    @Mapping(target = "uuid", expression = "java(Util.getUUID(user.getEmail()))")
    @Mapping(target = "isActive", expression = "java(user.isActive())")
    UserEntity toEntity(UserRequestDto user);

    default UserEntity to(UserRequestDto user){
        var entity = toEntity(user);
        entity.setPhones(entity.getPhones().stream().peek(p -> p.setUser(entity)).collect(Collectors.toList()));
        return entity;
    }

    @Mapping(target = "isActive", expression = "java(user.isActive())")
    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "created", source = "createdDate")
    @Mapping(target = "modified", source = "updatedDate")
    UserSaveResponseDto toResponseSave(UserEntity user);

    @Mapping(target = "isActive", expression = "java(user.isActive())")
    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "created", source = "createdDate")
    @Mapping(target = "modified", source = "updatedDate")
    UserResponseDto toResponse(UserEntity user);

}
