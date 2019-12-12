package com.zykj.forum.service.impl;

import com.zykj.forum.dto.UserInfo;
import com.zykj.forum.entity.user.User;
import com.zykj.forum.respository.UserRepository;
import com.zykj.forum.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Transactional
    @Override
    public User createUser(UserInfo userInfo) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-DD");
        Date parseDate = dateFormat.parse(userInfo.getDate());
        return userRepository.save(new User(userInfo.getId(),userInfo.getName(),userInfo.getRealName(),userInfo.getPhone(),parseDate,userInfo.getAddress(),userInfo.getEmail(),"https://zijieke.com/semantic-ui/images/avatar2/large/kristy.png",new Date(),new Date()));
    }

    @Transactional
    @Override
    public User updateData(UserInfo userInfo) throws ParseException {
        User user = queryUser(userInfo.getId());
        user.setName(userInfo.getName());
        user.setRealName(user.getRealName());
        user.setPhone(userInfo.getPhone());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-DD");
        Date parseDate = dateFormat.parse(userInfo.getDate());
        user.setBirthday(parseDate);
        user.setAddress(userInfo.getAddress());
        user.setEmail(userInfo.getEmail());
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public int updateAvatar(long id, String picUrl) {
        return userRepository.updateAvatar(id,picUrl);
    }

    @Override
    public User queryUser(long id) {
        return userRepository.findById(id).orElse(null);
    }
}
