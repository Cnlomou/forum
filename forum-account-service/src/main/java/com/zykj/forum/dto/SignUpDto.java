package com.zykj.forum.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SignUpDto {
    @NotBlank
    private String acc;
    @NotBlank
    private String pword;
    @NotNull
    private Integer type;
    @NotNull
    private String salt;
}
