package com.zykj.forum.repository;

import com.zykj.forum.entity.account.Account;
import com.zykj.forum.entity.user.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountEqualsAndIsPhoneEquals(String account,Boolean isphone);
    boolean existsByAccountEqualsAndIsPhoneEquals(String account,Boolean isPhone);
    Account findByAccountEqualsAndPasswordEqualsAndIsPhone(String account,String password,Boolean isphone);
    boolean existsByAccountEqualsAndPasswordEqualsAndIsPhoneEquals(String account,String password,Boolean isphone);
}
