package com.zykj.forum.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignUpDto {
    @NotBlank
    private String acc;
    @NotBlank
    private String pword;
    private Integer type;
    private String salt;
}
