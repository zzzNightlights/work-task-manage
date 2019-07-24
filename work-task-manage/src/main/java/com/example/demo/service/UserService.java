package com.example.demo.service;

import com.example.demo.entity.Result;
import com.example.demo.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    //验证用户登录信息
    User checkUserLogin(String username, String password);
    //插入用户信息
    boolean addUser(User user);
    //根据用户名查询
    User findUserByUserName(String userName);
    //查询所有用户信息
    List<User> findAllUserInfo();
    //根据用户Id删除用户信息
    boolean removeUserById(int userId);
    //修改用户信息
    boolean modifyUserInfo(User user);
    //根据用户ID查询
    User findUserById(int userId);
    //查询需要邮件提醒的用户信息
    List<Result> findMailUser();
}
