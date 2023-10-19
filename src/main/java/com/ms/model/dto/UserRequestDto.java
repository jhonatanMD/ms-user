package com.ms.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.model.PhoneDto;
import com.ms.util.anotation.Email;
import com.ms.util.anotation.Password;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank
    @ApiModelProperty(value = "Nombre de usuario", example = "Jhonatan Mallqui")
    private String name;
    @NotBlank
    @Email
    @ApiModelProperty(value = "Email de usuario", example = "jmd@nisum.cl")
    private String email;
    @NotBlank
    @Password
    @ApiModelProperty(value = "Password de usuario", example = "A1234567")
    private String password;
    @JsonIgnore
    @ApiModelProperty(value = "Token de usuario")
    private String token;
    @JsonIgnore
    @ApiModelProperty(value = "Activo de usuario", example = "true")
    private boolean isActive;
    @JsonIgnore
    @ApiModelProperty(value = "Ultimo inicio de sesion de usuario")
    private LocalDateTime lastLogin;
    @ApiModelProperty(value = "Teléfonos del usuario", notes = "Lista de teléfonos del usuario.")
    private List<PhoneDto> phones = new ArrayList<>();
}
