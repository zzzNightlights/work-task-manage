package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/task")
@RestController
public class TaskController {
    @Autowired
    TaskService taskService;
    @RequestMapping("/task-list")
    public Map<String,Object> getTaskList(int pageIndex, int pageSize){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        List<Task> list =taskService.getTaskList();
        List<Task> taskList = new ArrayList<>();
        int n = (pageIndex - 1) * pageSize;
        int size = n + pageSize;
        if (size>list.size()){
            size = list.size();
        }
        for (int i = n; i < size; i++)
        {
            Task task = list.get(i);
            taskList.add(task);
        }
        modelMap.put("taskList",taskList);
        return modelMap;
    }
    @RequestMapping("/getListSize")
    public int getListSize(){
        List<Task> list =taskService.getTaskList();
        return list.size();
    }
    @RequestMapping("/my-task-list")
    public Map<String,Object> getMyTaskList(int userId){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        List<Task> list =taskService.getTaskByUserId(userId);
        modelMap.put("taskList",list);
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
