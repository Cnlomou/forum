package com.zykj.forum.controller;

import com.zykj.forum.dto.SignUpDto;
import com.zykj.forum.dto.SigninDto;
import com.zykj.forum.RestResult;
import com.zykj.forum.entity.Account;
import com.zykj.forum.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.sql.ResultSet;
import java.util.HashMap;

@RestController
public class AccountController {

    private static final String SERVICE_PREFIX="http://user-service";

    @Resource
    AccountService accountService;
    @Autowired
    RestTemplate restTemplate;

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
        final HashMap<String, Object> uriVariables = new HashMap<>();
        final ResponseEntity<RestResult> restResultResponseEntity = restTemplate.postForEntity(SERVICE_PREFIX + "/createUser",
                null, RestResult.class, uriVariables);
        if(restResultResponseEntity.getStatusCode().is2xxSuccessful()){
            final RestResult body = restResultResponseEntity.getBody();
        }
        return null;
    }
    private RestResult singUpAction(SignUpDto signUpDto){
        Account account = accountService.singUp(signUpDto);
        RestResult restResult = new RestResult();
        if(account!=null&&account.getId()!=null)
            restResult.setCode("okar");
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
