package com.zykj.forum;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class RestResult {
    private String code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object msg;
}
