package com.zykj.forum.controller;

import com.zykj.forum.dto.UserInfo;
import com.zykj.forum.entity.RestResult;
import com.zykj.forum.entity.user.User;
import com.zykj.forum.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.ParseException;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/createUser")
    public RestResult createUser(@Valid UserInfo userInfo) throws ParseException {
        RestResult restResult = new RestResult();

        User newUser = userService.createUser(userInfo);
        if (newUser == null)
            restResult.setCode("no");
        else
            restResult.setCode("ok");

        return restResult;
    }

    @PostMapping("/updateUser")
    public RestResult updateUser(@Valid UserInfo userInfo) throws ParseException {
        RestResult restResult = new RestResult();

        User updateUser = userService.updateData(userInfo);
        if (updateUser == null)
            restResult.setCode("no");
        else
            restResult.setCode("ok");

        return restResult;
    }

    @GetMapping("/User/{id}")
    public RestResult queryUser(@PathVariable long id) {
        RestResult restResult = new RestResult();
        User user = userService.queryUser(id);
        if(user == null)
            restResult.setCode("no");
        else
            restResult.setCode("ok");
            restResult.setMsg(user);
        return restResult;
    }

    @PostMapping("/updateAvatar")
    public RestResult updateAvatar(long id, String picUrl) {
        RestResult restResult = new RestResult();
        int i = userService.updateAvatar(id, picUrl);
        if (i > 0)
            restResult.setCode("ok");
        else
            restResult.setCode("error");

        return restResult;
    }

}
