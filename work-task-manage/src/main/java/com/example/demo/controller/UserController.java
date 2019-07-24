package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/do-login")
    public String findUserInfo(HttpSession session, String username, String password){
        User user = userService.checkUserLogin(username,password);
        if (user!=null){
            session.setAttribute("user",user);
            if (user.getType()==1)
            {
                return "admin";
            }
            return "success";
        }
        return "error";
    }

    @RequestMapping("/modify-pwd")
    public String modifyPwd(String oldPassword,String newPassword,HttpSession session){
        User user = (User)session.getAttribute("user");
        String username = user.getUsername();
        User userInfo = userService.checkUserLogin(username,oldPassword);
        if (userInfo==null){
            return "mismatch";
        }
        user.setPassword(newPassword);
        boolean flag = userService.modifyUserInfo(user);
        if (flag){
            return "success";
        }
        return "error";
    }

    @RequestMapping("/user-info")
    public Map<String,Object> getUserInfo(){
        Map<String,Object> map = new HashMap<>();
        Map data = new HashMap();
        List<User> list = userService.findAllUserInfo();
        data.put("userList",list);
        data.put("count",list.size());
        map.put("data",data);
        map.put("success",true);
        map.put("errorMsg","");
        return map;
    }
}
