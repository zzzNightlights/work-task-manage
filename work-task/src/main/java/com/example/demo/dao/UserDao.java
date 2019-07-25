package com.example.demo.dao;

import com.example.demo.entity.Result;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    //查询用户登录信息
    User queryUserLogin(@Param("username") String username, @Param("password") String password);
    //插入用户信息
    int insertUser(User user);
    //根据用户名查询
    User queryUserByName(@Param("username") String username);
    //查询所有用户信息
    List<User> queryAllUserInfo();
    //根据用户ID删除用户信息
    int deleteUserById(int userId);
    //修改用户信息
    int updateUserInfo(User user);
    //根据用户Id查询用户
    User queryUserById(int userId);
    //查询邮件发送信息
    List<Result> queryMailUser();
}
