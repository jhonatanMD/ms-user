package com.ms.service.impl;

import com.ms.mapper.UserMapper;
import com.ms.model.UserResponseDto;
import com.ms.model.UserSaveResponseDto;
import com.ms.model.dto.UserRequestDto;
import com.ms.repository.IUserRepository;
import com.ms.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository repository;
    private final UserMapper mapper;
    @Override
    public UserSaveResponseDto save(UserRequestDto userRequestDto) {
        return mapper.toResponseSave(repository.save(mapper.to(userRequestDto)));
    }

    @Override
    public List<UserResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existUserByEmail(String email) {
        return repository.findByEmail(email).isPresent();
    }
}
