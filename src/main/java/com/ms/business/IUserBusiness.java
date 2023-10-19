package com.ms.business;

import com.ms.model.UserResponseDto;
import com.ms.model.UserSaveResponseDto;
import com.ms.model.dto.UserRequestDto;

import java.util.List;

public interface IUserBusiness {
    UserSaveResponseDto save(UserRequestDto userRequestDto);
    List<UserResponseDto> findAll();
}
