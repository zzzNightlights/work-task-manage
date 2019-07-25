package com.example.demo.service;


import com.example.demo.entity.Notice;
import com.github.pagehelper.PageInfo;


public interface NoticeService {
    //获取最新公告
    Notice findNewNotice();
    //获取公告列表
    PageInfo<Notice> findNoticeList(int pageIndex,int pageSize);
    //新增公告
    boolean addNotice(Notice notice);
    boolean removeNotice(int noticeId);
    //统计公告数量
    int getNoticeNum();
}
