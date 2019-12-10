package com.zykj.forum.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SigninDto {
    @NotBlank
    private String account;
    @NotBlank
    private String password;
    private Integer type;
}
