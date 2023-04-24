package com.politechnika.transport.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.*;

@Setter
@Getter
public class UserCreateDto {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private List<String> roles;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}
