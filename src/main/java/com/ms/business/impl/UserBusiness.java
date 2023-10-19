package com.ms.business.impl;

import com.ms.business.IUserBusiness;
import com.ms.configuration.security.JwtService;
import com.ms.model.UserResponseDto;
import com.ms.model.UserSaveResponseDto;
import com.ms.model.dto.UserRequestDto;
import com.ms.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static com.ms.util.Constant.Message.EXIST_EMAIL_ERROR;
import static java.lang.Boolean.TRUE;
import static org.springframework.http.HttpStatus.CONFLICT;

@Component
@RequiredArgsConstructor
public class UserBusiness implements IUserBusiness {

    private final IUserService userService;
    private final JwtService jwtService;
    @Override
    public UserSaveResponseDto save(UserRequestDto userRequestDto) {
        return userService.save(buildUserForSave(userRequestDto));
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userService.findAll();
    }

    private UserRequestDto buildUserForSave(UserRequestDto userRequestDto) {
        if (userService.existUserByEmail(userRequestDto.getEmail())) {
            throw new ResponseStatusException(CONFLICT,EXIST_EMAIL_ERROR);
        }

        userRequestDto.setActive(TRUE);
        userRequestDto.setToken(jwtService.generateJwt(userRequestDto.getName(), userRequestDto.getEmail()));
        userRequestDto.setLastLogin(LocalDateTime.now());

        return userRequestDto;
    }
}
