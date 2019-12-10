package com.zykj.forum.service;

import com.zykj.forum.dto.SignUpDto;
import com.zykj.forum.dto.SigninDto;
import com.zykj.forum.entity.account.Account;
import com.zykj.forum.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account findByAcount(String account,boolean isphone){
        return accountRepository.findByAccountEqualsAndIsPhoneEquals(account,isphone);
    }

    @Transactional
    public Account singUp(SignUpDto signUpDto){
        if(accountRepository.existsByAccountEqualsAndIsPhoneEquals(signUpDto.getAcc(),signUpDto.getType()==0))
            return null;
        Account save = accountRepository.save(new Account(null, signUpDto.getAcc(), signUpDto.getPword()
                , signUpDto.getSalt(), null, signUpDto.getType() == 0));
        return save;
    }

    @Transactional
    public Account updatePassword(String account,String password,String newPassword,boolean isPhone){
        Account byAccountEquals = accountRepository.findByAccountEqualsAndPasswordEqualsAndIsPhone(account,password,isPhone);
        if(byAccountEquals==null)
            return null;
        byAccountEquals.setPassword(newPassword);
        return accountRepository.save(byAccountEquals);
    }
    public boolean signin(SigninDto signinDto){
        return accountRepository.existsByAccountEqualsAndPasswordEqualsAndIsPhoneEquals(signinDto.getAccount(),
                signinDto.getPassword(),signinDto.getType()==0);
    }
}
