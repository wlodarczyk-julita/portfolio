package com.politechnika.transport.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRoleCreateDto {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

}
