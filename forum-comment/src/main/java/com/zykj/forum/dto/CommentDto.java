package com.zykj.forum.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentDto {
    private Long uid; //User id
    private Long pid; //posts id
    @NotBlank
    private String txt;//content text
}
