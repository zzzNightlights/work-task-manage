package com.example.demo.service;




import com.example.demo.entity.Notice;

import java.util.List;

public interface NoticeService {
    //获取最新公告
    Notice findNewNotice();
    //获取公告列表
    List<Notice> findNoticeList();
    //新增公告
    boolean addNotice(Notice notice);
    boolean removeNotice(int noticeId);
}
