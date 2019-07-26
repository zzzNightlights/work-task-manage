package com.example.demo.controller;

import com.example.demo.entity.Notice;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.service.NoticeService;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PageController {
    @Autowired
    NoticeService noticeService;
    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<Task> newTaskList = taskService.getNewTask();
        PageInfo<Task> myTaskList = taskService.getTaskByUserId(user.getUserId(),1,5);
        List<User> userList = userService.findAllUserInfo();
//        List<Task> myTaskList = taskService.getTaskByUserId(user.getUserId());
        int userSize = userService.getUserCount();
        Notice notice = noticeService.findNewNotice();
        model.addAttribute("notice",notice);
        model.addAttribute("userListSize",userSize);
        model.addAttribute("newTaskListSize",newTaskList.size());
        model.addAttribute("myTaskListSize",myTaskList.getList().size());
        model.addAttribute("user",user);
        return "index";
    }

    @RequestMapping("/new-task")
    public String newTask(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        Notice notice = noticeService.findNewNotice();
        model.addAttribute("notice",notice);
        return "new-task";
    }

    @RequestMapping("/my-task")
    public String myTask(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        Notice notice = noticeService.findNewNotice();
        model.addAttribute("notice",notice);
        return "my-task";
    }
    @RequestMapping("modify-task")
    public String modifyTask(int taskId,Model model,HttpSession session){
        Task task = taskService.getTaskById(taskId);
        User user = (User) session.getAttribute("user");
        model.addAttribute("task",task);
        model.addAttribute("user",user);
        Notice notice = noticeService.findNewNotice();
        model.addAttribute("notice",notice);
        return "modify-task";
    }

    @RequestMapping("/modify-pwd")
    public String modifyPwd(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        Notice notice = noticeService.findNewNotice();
        model.addAttribute("notice",notice);
        return "modify-pwd";
    }

    @RequestMapping(value = "/logout")
    public String logOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:login";
    }
}
