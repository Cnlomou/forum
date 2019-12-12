package com.zykj.forum.controller;

import com.zykj.forum.dto.SignUpDto;
import com.zykj.forum.dto.SigninDto;
import com.zykj.forum.entity.RestResult;
import com.zykj.forum.entity.account.Account;
import com.zykj.forum.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
public class AccountController {

    @Resource
    AccountService accountService;

    @GetMapping("/authoricationInfo")
    public RestResult doAuthorcation(@NotBlank String account,Integer type){
        RestResult restResult = new RestResult();
        Account byAcount = accountService.findByAcount(account, type == 0);
        if(byAcount==null)
            restResult.setCode("no");
        else
            restResult.setCode("ok");
        restResult.setMsg(byAcount);
        return  restResult;
    }

    @PostMapping("/signup")
    public RestResult doSiggUp(@Valid SignUpDto signUpDto){
        Account account = accountService.singUp(signUpDto);
        RestResult restResult = new RestResult();
        if(account!=null&&account.getId()!=null)
            restResult.setCode("ok");
        else
            restResult.setCode("no");
        restResult.setMsg(account);
        return restResult;
    }

    @PostMapping("/updatepassword")
    public RestResult doUpdatePassword(@NotBlank String account ,@NotBlank String password,@NotBlank String newpassword, Integer type){
        Account account1 = accountService.updatePassword(account, password, newpassword, type == 0);
        RestResult restResult = new RestResult();
        if(account1==null)
            restResult.setCode("no");
        else
            restResult.setCode("ok");
        restResult.setMsg(account1);
        return restResult;
    }
    @PostMapping("/signin")
    public RestResult doSingin(@Valid SigninDto signinDto){
        RestResult restResult = new RestResult();
        if(accountService.signin(signinDto))
            restResult.setCode("ok");
        else
            restResult.setCode("no");
        return restResult;
    }
}
