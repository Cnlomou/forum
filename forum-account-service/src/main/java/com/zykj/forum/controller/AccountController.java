package com.zykj.forum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }
}
