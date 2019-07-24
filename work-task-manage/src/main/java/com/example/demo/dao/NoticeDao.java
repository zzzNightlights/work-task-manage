package com.example.demo.dao;

import com.example.demo.entity.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeDao {
    //获取最新的公告
    Notice queryNewNotice();
    //获取公告列表
    List<Notice> queryNoticeList();
    //删除公告
    int deleteNotice(int noticeId);
    //新增公告
    int insertNotice(Notice notice);
}
