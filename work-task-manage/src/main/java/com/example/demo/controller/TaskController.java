package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.service.TaskService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/task")
@RestController
public class TaskController {
    @Autowired
    TaskService taskService;
    @RequestMapping("/task-list")
    public Map<String,Object> getTaskList(@RequestParam(value="pageIndex",defaultValue="1")int pageIndex, @RequestParam(value="pageSize",defaultValue="5")int pageSize){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        PageInfo<Task> pageInfo =taskService.getTaskList(pageIndex,pageSize);
        modelMap.put("taskList", pageInfo.getList());
        return modelMap;
    }
    @RequestMapping("/getListSize")
    public int getListSize(){
        int count =taskService.getTaskCount();
        return count;
    }
    @RequestMapping("/getMyListSize")
    public int getMyListSize(int userId){
        int count =taskService.getMyTaskCount(userId);
        return count;
    }
    @RequestMapping("/my-task-list")
    public Map<String,Object> getMyTaskList(int userId,@RequestParam(value="pageIndex",defaultValue="1")int pageIndex,@RequestParam(value="pageSize",defaultValue="5")int pageSize){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        PageInfo<Task> pageInfo =taskService.getTaskByUserId(userId,pageIndex,pageSize);
        modelMap.put("taskList",pageInfo.getList());
        return modelMap;
    }
    @RequestMapping("/add-task")
    public String addTask(Task task, HttpSession session){
        User user = (User)session.getAttribute("user");
        task.setUserId(user.getUserId());
        boolean flag = taskService.addTask(task);
        if (flag){
            return "success";
        }
        return "error";
    }
    @RequestMapping("/update-task")
    public String updateTask(Task task){
        boolean flag = taskService.modifyTaskInfo(task);
        if (flag){
            return "success";
        }
        return "error";
    }
    @RequestMapping("/delete-task")
    public String deleteTask(int taskId){
        boolean flag = taskService.removeTaskById(taskId);
        if (flag){
            return "success";
        }
        return "error";
    }
    @RequestMapping("/finish-task")
    public String finishTask(int taskId){
        boolean flag = taskService.finishTaskById(taskId);
        if (flag){
            return "success";
        }
        return "error";
    }
}
