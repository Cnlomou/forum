package com.zykj.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String realName;
    @NotBlank
    private String phone;
    @NotBlank
    private String date;
    @NotBlank
    private String address;
    @NotBlank
    private String email;

}
