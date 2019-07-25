package com.example.demo.service.impl;

import com.example.demo.dao.TaskDao;
import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskDao taskDao;
    @Override
    @Cacheable(cacheNames = "taskList")
    public PageInfo<Task> getTaskList(int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<Task> taskList = taskDao.queryTaskList();
        PageInfo<Task> pageInfo = new PageInfo<>(taskList);
        return pageInfo;
    }

    @Override
    @CacheEvict(cacheNames = "taskList",allEntries=true)
    public boolean addTask(Task task) {
        if (task!=null){
            try{
                int effectedNum = taskDao.insertTask(task);
                if (effectedNum>0){
                    return true;
                }
                else {
                    throw new RuntimeException("插入任务信息失败");
                }
            }catch (Exception e){
                throw new RuntimeException("插入任务信息失败"+e.toString());
            }
        }
        else {
            throw new RuntimeException("任务信息不能为空");
        }
    }

    @Override
    public List<Task> getTaskByUserId(int userId) {
        return taskDao.queryTaskByUserId(userId);
    }

    @Override
    @CacheEvict(cacheNames = "taskList",allEntries=true)
    public boolean removeTaskById(int taskId) {
        if (taskId!=0){
            try{
                int effectedNum = taskDao.deleteTaskById(taskId);
                if (effectedNum>0){
                    return true;
                }
                else{
                    throw new RuntimeException("删除任务信息失败");
                }
            }catch (Exception e){
                throw new RuntimeException("删除任务信息失败"+e.toString());
            }
        }else{
            throw new RuntimeException("任务ID不能为空");
        }
    }

    @Override
    @CacheEvict(cacheNames = "taskList",allEntries=true)
    public boolean finishTaskById(int taskId) {
        if (taskId!=0){
            try{
                int effectedNum = taskDao.updateTaskById(taskId);
                if (effectedNum>0){
                    return true;
                }
                else{
                    throw new RuntimeException("完成任务失败");
                }
            }catch (Exception e){
                throw new RuntimeException("完成任务失败"+e.toString());
            }
        }else{
            throw new RuntimeException("任务ID不能为空");
        }
    }

    @Override
   @CacheEvict(cacheNames = "taskList",allEntries=true)
    public boolean modifyTaskInfo(Task task) {
        if (task!=null){
            try{
                int effectedNum = taskDao.updateTaskInfo(task);
                if (effectedNum>0){
                    return true;
                }
                else{
                    throw new RuntimeException("修改任务失败");
                }
            }catch (Exception e){
                throw new RuntimeException("修改任务失败"+e.toString());
            }
        }else{
            throw new RuntimeException("任务信息不能为空");
        }
    }

    @Override
    public Task getTaskById(int taskId) {
        return taskDao.queryTaskById(taskId);
    }

    @Override
    public List<Task> getNewTask() {
        return taskDao.queryNewTask();
    }

    @Override
    public int getTaskCount() {
        return taskDao.queryTaskCount();
    }
}
