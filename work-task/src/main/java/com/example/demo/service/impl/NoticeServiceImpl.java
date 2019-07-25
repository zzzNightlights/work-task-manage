package com.example.demo.service.impl;

import com.example.demo.dao.NoticeDao;
import com.example.demo.entity.Notice;
import com.example.demo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeDao noticeDao;
    @Override
    public Notice findNewNotice() {
        return noticeDao.queryNewNotice();
    }

    @Override
    public List<Notice> findNoticeList() {
        return noticeDao.queryNoticeList();
    }

    @Override
    public boolean addNotice(Notice notice) {
        if (notice!=null){
            try{
                int effectedNum = noticeDao.insertNotice(notice);
                if (effectedNum>0){
                    return true;
                }
                else {
                    throw new RuntimeException("插入公告信息失败");
                }
            }catch (Exception e){
                throw new RuntimeException("插入公告信息失败"+e.toString());
            }
        }
        else {
            throw new RuntimeException("公告信息不能为空");
        }
    }

    @Override
    public boolean removeNotice(int noticeId) {
        if (noticeId!=0){
            try{
                int effectedNum = noticeDao.deleteNotice(noticeId);
                if (effectedNum>0){
                    return true;
                }
                else{
                    throw new RuntimeException("删除公告信息失败");
                }
            }catch (Exception e){
                throw new RuntimeException("删除公告信息失败"+e.toString());
            }
        }else{
            throw new RuntimeException("公告ID不能为空");
        }
    }
}
