package com.zykj.forum.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostsDto {
    @NotBlank
    String title;
    @NotBlank
    String content;
    Long userId;
    Long subjectId;
}
