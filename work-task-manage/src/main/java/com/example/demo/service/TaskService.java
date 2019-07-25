package com.example.demo.service;


import com.example.demo.entity.Task;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TaskService {
    //获取工作任务列表
    PageInfo<Task> getTaskList(int pageIndex, int pageSize);
    //发布新任务
    boolean addTask(Task task);
    //获取个人任务列表
    PageInfo<Task> getTaskByUserId(int userId, int pageIndex, int pageSize);
    //根据任务ID删除任务
    boolean removeTaskById(int taskId);
    //根据任务ID完成任务
    boolean finishTaskById(int taskId);
    //修改任务信息
    boolean modifyTaskInfo(Task task);
    //根据任务ID查询任务
    Task getTaskById(int taskId);
    //获取当天任务
    List<Task> getNewTask();
    //获取任务总数
    int getTaskCount();
    //获取我的任务总数
    int getMyTaskCount(int userId);
}
