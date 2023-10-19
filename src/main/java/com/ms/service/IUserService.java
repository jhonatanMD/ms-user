package com.ms.service;

import com.ms.model.UserResponseDto;
import com.ms.model.UserSaveResponseDto;
import com.ms.model.dto.UserRequestDto;

import java.util.List;

public interface IUserService {

    UserSaveResponseDto save(UserRequestDto userRequestDto);
    List<UserResponseDto> findAll();
    boolean existUserByEmail(String email);
}
