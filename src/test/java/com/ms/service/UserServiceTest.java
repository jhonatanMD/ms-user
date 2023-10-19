package com.ms.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.ms.mapper.UserMapper;
import com.ms.model.UserEntity;
import com.ms.model.UserResponseDto;
import com.ms.model.UserSaveResponseDto;
import com.ms.model.dto.UserRequestDto;
import com.ms.repository.IUserRepository;
import com.ms.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private IUserRepository repository;

    @Mock
    private UserMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testSaveUser() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setEmail("test@example.com");
        UserEntity savedUser = new UserEntity();
        savedUser.setId(1L);
        savedUser.setEmail(userRequestDto.getEmail());

        UserSaveResponseDto userResponseDto = new UserSaveResponseDto();;

        when(repository.save(any(UserEntity.class))).thenReturn(savedUser);
        when(mapper.to(any(UserRequestDto.class))).thenReturn(savedUser);
        when(mapper.toResponseSave(any(UserEntity.class))).thenReturn(userResponseDto);

        UserSaveResponseDto savedUserResponse = userService.save(userRequestDto);
        assertNotNull(savedUserResponse);
    }

    @Test
    void testFindAllUsers() {
        UserEntity user1 = createUser(1L);
        UserEntity user2 = createUser(2L);
        List<UserEntity> userList = Arrays.asList(user1, user2);

        when(repository.findAll()).thenReturn(userList);
        when(mapper.toResponse(user1)).thenReturn(new UserResponseDto());
        when(mapper.toResponse(user2)).thenReturn(new UserResponseDto());
        List<UserResponseDto> allUsers = userService.findAll();

        assertNotNull(allUsers);
        assertFalse(allUsers.isEmpty());
        verify(repository).findAll();
        verify(mapper, times(2)).toResponse(any(UserEntity.class));
    }

    @Test
    void testExistUserByEmail() {
        String email = "test@example.com";
        when(repository.findByEmail(email)).thenReturn(Optional.of(createUser(1L)));
        boolean userExists = userService.existUserByEmail(email);
        assertTrue(userExists);
        verify(repository).findByEmail(email);
    }

    private UserEntity createUser(Long id) {
        UserEntity user = new UserEntity();
        user.setId(id);
        return user;
    }
}
