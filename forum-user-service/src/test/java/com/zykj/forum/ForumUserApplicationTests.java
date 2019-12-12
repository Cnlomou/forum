package com.zykj.forum;

import com.zykj.forum.entity.user.User;
import com.zykj.forum.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest(classes = ForumUserApplication.class)
public class ForumUserApplicationTests {

    @Resource
    private UserService userService;

    @Test
    public void contextLoads(){
        User user = new User();

//        user.setName("zykj");
//        user.setRealName("卓越科技");
//        user.setPhone("1008611");
//        user.setBirthday(new Date());
//        user.setAddress("卓越科技路");
//        user.setEmail("1454013857@qq.com");
//        user.setPicUrl("http://");
//        userService.createUser(user);
        //User user = userService.queryUser(2);
        //System.out.println(user);
        long id = 1;
        String picUrl = "123456";
        int i = userService.updateAvatar(id, picUrl);
        if(i >0){
            System.out.println("ok");
        }
        System.out.println("error");
    }
}
