package com.zykj.forum.service;

import com.zykj.forum.dto.UserInfo;
import com.zykj.forum.entity.User;

import java.text.ParseException;

public interface UserService {

    //创建用户
    User createUser(UserInfo userInfo) throws ParseException;

    //更新数据
    User updateData(UserInfo userInfo) throws ParseException;

    //更新头像
    int updateAvatar(long id,String picUrl);

    //查询用户
    User queryUser(long id);
}
