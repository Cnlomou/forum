package com.example;

import com.zykj.forum.ForumAccountServiceApplication;
import com.zykj.forum.entity.user.User;
import com.zykj.forum.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = ForumAccountServiceApplication.class)
class ForumAccountServiceApplicationTests {
    @Resource
    AccountRepository accountRepository;
    @Test
    void contextLoads() {
        User user = new User();
        user.setName("卓越科技");
        user.setRealName("孙俊华");
        User save = accountRepository.save(user);
        System.out.println(save);
        List<User> all = accountRepository.findAll();
        System.out.println(all);
    }

}
