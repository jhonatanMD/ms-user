package com.ms.business;

import com.ms.business.impl.UserBusiness;
import com.ms.configuration.security.JwtService;
import com.ms.model.UserResponseDto;
import com.ms.model.UserSaveResponseDto;
import com.ms.model.dto.UserRequestDto;
import com.ms.service.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CONFLICT;


class UserBusinessTest {

    @InjectMocks
    private UserBusiness userBusiness;

    @Mock
    private IUserService userService;

    @Mock
    private JwtService jwtService;

    UserRequestDto userRequestDto = new UserRequestDto();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        userRequestDto.setEmail("test@example.com");
        userRequestDto.setName("test-name");
    }

    @Test
    void testSaveUserOk() {

        when(jwtService.generateJwt(anyString(), anyString())).thenReturn("jwt-token");

        when(userService.existUserByEmail(anyString())).thenReturn(false);
        when(userService.save(any(UserRequestDto.class))).thenReturn(new UserSaveResponseDto());

        UserSaveResponseDto savedUser = userBusiness.save(userRequestDto);
        assertNotNull(savedUser);
    }

    @Test
    void testGetUserOk() {
        when(userService.findAll()).thenReturn(List.of(new UserResponseDto()));
        var response = userBusiness.findAll();
        Assertions.assertEquals(1,response.size());
    }

    @Test
    void testSaveUserError() {
        when(userService.existUserByEmail(anyString())).thenReturn(true);
        var error = Assertions.assertThrows(ResponseStatusException.class , () -> userBusiness.save(userRequestDto));
        assertEquals(CONFLICT,error.getStatus());
    }
}