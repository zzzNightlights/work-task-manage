package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.Result;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public User checkUserLogin(String username, String password) {
        return userDao.queryUserLogin(username,password);
    }

    @Override
    public boolean addUser(User user) {
        if (user!=null){
            try{
                int effectedNum = userDao.insertUser(user);
                if (effectedNum>0){
                    return true;
                }
                else{
                    throw new RuntimeException("新增用户信息失败！");
                }
            }catch (Exception e) {
                throw new RuntimeException("新增用户信息失败！"+e.toString());
            }
        }
        else {
            throw new RuntimeException("用户信息不能为空！");
        }
    }

    @Override
    public User findUserByUserName(String username) {
        if(username!=null&&!"".equals(username)){
            try{
                return userDao.queryUserByName(username);
            }catch (Exception e){
                throw new RuntimeException("验证登录信息失败！"+e.getMessage());
            }
        }
        else{
            throw new RuntimeException("账号密码不能为空！");
        }
    }

    @Override
    public List<User> findAllUserInfo() {
        return userDao.queryAllUserInfo();
    }

    @Override
    public boolean removeUserById(int userId) {
        if (userId!=0){
            try{
                int effectedNum = userDao.deleteUserById(userId);
                if (effectedNum>0){
                    return true;
                }
                else{
                    throw new RuntimeException("删除用户信息失败！");
                }
            }catch (Exception e) {
                throw new RuntimeException("删除用户信息失败！"+e.toString());
            }
        }
        else {
            throw new RuntimeException("用户id不能为空！");
        }
    }

    @Override
    public boolean modifyUserInfo(User user) {
        if (user!=null){
            try{
                int effectedNum = userDao.updateUserInfo(user);
                if (effectedNum>0){
                    return true;
                }
                else{
                    throw new RuntimeException("修改用户信息失败！");
                }
            }catch (Exception e) {
                throw new RuntimeException("修改用户信息失败！"+e.toString());
            }
        }
        else {
            throw new RuntimeException("用户信息不能为空！");
        }
    }

    @Override
    public User findUserById(int userId) {
        return userDao.queryUserById(userId);
    }

    @Override
    public List<Result> findMailUser() {
        return userDao.queryMailUser();
    }
}
