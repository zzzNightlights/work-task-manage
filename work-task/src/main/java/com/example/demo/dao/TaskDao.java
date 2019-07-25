package com.example.demo.dao;

import com.example.demo.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao {
    //查询所有工作任务
    List<Task> queryTaskList();
    //List<Task> queryTaskList(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
    //根据ID查询任务
    Task queryTaskById(int taskId);
    //新增工作任务
    int insertTask(Task task);
    //根据当前用户所有任务
    List<Task> queryTaskByUserId(int userId);
    //根据任务ID删除任务
    int deleteTaskById(int taskId);
    //根据任务Id完成任务
    int updateTaskById(int taskId);
    //修改任务信息
    int updateTaskInfo(Task task);
    //查询当日任务
    List<Task> queryNewTask();
    //统计任务条数
    int queryTaskCount();
}
