package com.example.demo.controller;

import com.example.demo.entity.Mail;
import com.example.demo.entity.Notice;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.service.NoticeService;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    NoticeService noticeService;
    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @RequestMapping("/admin-index")
    public String adminIndex(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<Task> newTaskList = taskService.getNewTask();
        PageInfo<Task> myTaskList = taskService.getTaskByUserId(user.getUserId(),1,5);
        List<User> userList = userService.findAllUserInfo();
        Notice notice = noticeService.findNewNotice();
        model.addAttribute("notice",notice);
        model.addAttribute("userListSize",userList.size());
        model.addAttribute("newTaskListSize",newTaskList.size());
        model.addAttribute("myTaskListSize",myTaskList.getList().size());
        model.addAttribute("user",user);
        return "admin-index";
    }
    @RequestMapping("/admin-user")
    public String adminUser(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        Notice notice = noticeService.findNewNotice();
        model.addAttribute("notice",notice);
        return "admin-user";
    }
    @RequestMapping("/admin-notice")
    public String adminNotice(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        Notice notice = noticeService.findNewNotice();
        model.addAttribute("notice",notice);
        return "admin-notice";
    }
    @RequestMapping("/add-notice.html")
    public String addNotice(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        Notice notice = noticeService.findNewNotice();
        model.addAttribute("notice",notice);
        return "admin-add-notice";
    }
    @RequestMapping("/add-user.html")
    public String addUser(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        Notice notice = noticeService.findNewNotice();
        model.addAttribute("notice",notice);
        return "admin-add-user";
    }

    @RequestMapping("/modify-user")
    public String modifyUser(int userId,Model model,HttpSession session){
        User userinfo = userService.findUserById(userId);
        User user = (User) session.getAttribute("user");
        model.addAttribute("userInfo",userinfo);
        model.addAttribute("user",user);
        Notice notice = noticeService.findNewNotice();
        model.addAttribute("notice",notice);
        return "admin-modify-user";
    }
    @ResponseBody
    @RequestMapping("/user-list")
    private Map<String,Object> ListUser(){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        List<User> list = userService.findAllUserInfo();
        modelMap.put("userList",list);
        return modelMap;
    }
    @ResponseBody
    @RequestMapping("/delete-user")
    public String deleteTask(int userId){
        boolean flag = userService.removeUserById(userId);
        if (flag){
            return "success";
        }
        return "error";
    }
    @ResponseBody
    @RequestMapping("/update-user")
    public String updateUser(User user){
        User userInfo = userService.findUserById(user.getUserId());
        user.setPassword(userInfo.getPassword());
        boolean flag = userService.modifyUserInfo(user);
        if (flag){
            return "success";
        }
        return "error";
    }
    @ResponseBody
    @RequestMapping("/add-user")
    public String addUser(User user){
        User userInfo = userService.findUserByUserName(user.getUsername());
        if (userInfo!=null){
            return "duplicate";
        }
        boolean flag = userService.addUser(user);
        if (flag){
            return "success";
        }
        return "error";
    }
    @ResponseBody
    @RequestMapping("/notice-list")
    private Map<String,Object> noticeList(@RequestParam(value="pageIndex",defaultValue="1")int pageIndex, @RequestParam(value="pageSize",defaultValue="5")int pageSize){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        PageInfo<Notice> list = noticeService.findNoticeList(pageIndex,pageSize);
        modelMap.put("noticeList",list.getList());
        return modelMap;
    }
    @ResponseBody
    @RequestMapping("/noticeList-size")
    private int getNoticeNum(){
        return  noticeService.getNoticeNum();
    }
    @ResponseBody
    @RequestMapping("/delete-notice")
    public String deleteNotice(int noticeId){
        boolean flag = noticeService.removeNotice(noticeId);
        if (flag){
            return "success";
        }
        return "error";
    }
    @ResponseBody
    @RequestMapping("/add-notice")
    public String addTask(Notice notice, HttpSession session){
        User user = (User)session.getAttribute("user");
        notice.setUserId(user.getUserId());
        boolean flag = noticeService.addNotice(notice);
        if (flag){
            return "success";
        }
        return "error";
    }
    @RequestMapping(value = "/logout")
    public String logOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:../login";
    }
    @RequestMapping("/admin-message")
    public String adminMsg(HttpSession session,Model model,int taskId){
        Task task = taskService.getTaskById(taskId);
        User user = (User) session.getAttribute("user");
        model.addAttribute("task",task);
        model.addAttribute("user",user);
        Notice notice = noticeService.findNewNotice();
        model.addAttribute("notice",notice);
        return "admin-message";
    }
    @ResponseBody
    @RequestMapping("/send-message")
    public String sendMsg(Mail mail, int userId, HttpSession session){
        User user = userService.findUserById(userId);
        User admin = (User)session.getAttribute("user");
        mail.setAddress(user.getMail());
        mail.setSubject(mail.getSubject()+"-【"+admin.getName()+"】");
        rabbitTemplate.convertAndSend("exchange.work-task","work-task",mail);
        return "success";
    }
}
