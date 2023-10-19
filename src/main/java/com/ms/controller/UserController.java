package com.ms.controller;

import com.ms.business.IUserBusiness;
import com.ms.model.UserResponseDto;
import com.ms.model.UserSaveResponseDto;
import com.ms.model.dto.UserRequestDto;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "User" , tags = "User")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserBusiness userBusiness;

    @ApiOperation(value = "Obtener Usuarios", nickname = "get user", notes = "Obtener Usuarios", response = UserResponseDto[].class, tags="User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = UserResponseDto[].class)})
    @GetMapping(
            value = "",
            produces = "application/json"
    )
    public List<UserResponseDto> getAllUser(){
        return userBusiness.findAll();
    }


    @ApiOperation(value = "Guardar Usuario", nickname = "save user", notes = "Guardar Usuarios", response = UserResponseDto.class, tags="User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = UserResponseDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 409, message = "Conflict")})
    @PostMapping(
            value = "/save",
            produces = "application/json" ,
            consumes = "application/json"
    )
    public UserSaveResponseDto saveUser(@RequestBody @Valid UserRequestDto user){
       return userBusiness.save(user);
    }
}
