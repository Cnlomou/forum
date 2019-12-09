package com.zykj.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @GetMapping("/hello")
    public String helo(){
        return "index";
    }
}
