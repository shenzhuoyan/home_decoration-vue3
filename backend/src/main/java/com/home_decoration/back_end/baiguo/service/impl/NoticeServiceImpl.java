package com.home_decoration.back_end.baiguo.service.impl;

import com.home_decoration.back_end.baiguo.dao.NoticeDao;
import com.home_decoration.back_end.baiguo.domain.Notice;
import com.home_decoration.back_end.baiguo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Override
    public boolean add(Notice notice) {
        return noticeDao.insert(notice)==1;
    }

    @Override
    public List<Notice> getAll(int page) {//只包括发布的，置顶的，最顶的
        List<Notice> notices = noticeDao.selectByStatus(3);
        notices.addAll(noticeDao.selectByStatus(2));
        notices.addAll(noticeDao.selectAllByStatus(1,(page - 1) * 10,10));
        return notices;
    }

    @Override
    public boolean setUp(int id) {
        return noticeDao.updateStatus(id,2)==1;
    }

    @Override
    public boolean setTop(int id) {
        noticeDao.updateTo1();
        return noticeDao.updateStatus(id,3)==1;
    }

    @Override
    public boolean resume(int id) {
        return noticeDao.updateStatus(id,1)==1;
    }

    @Override
    public boolean del(int id) {
        return noticeDao.updateStatus(id,0)==1;
    }

    @Override
    public int pageAll() {
        int count = noticeDao.count(1);
        return count % 10 > 0 ? count / 10 + 1 : count / 10;
    }

//    @Override
//    public List<Notice> getDel(int page) {
//        return noticeDao.selectAllByStatus(0,(page - 1)*10,10);
//    }
//
//    @Override
//    public int pageDel() {
//        int count = noticeDao.count(0);
//        return count % 10 > 0 ? count / 10 + 1 : count / 10;
//    }

    @Override
    public Notice get(int id) {
        return noticeDao.selectById(id);
    }

    @Override
    public List<Notice> getNew() {
        return noticeDao.select(0);
    }

    @Override
    public List<Notice> important() {
        return noticeDao.select(1);
    }

    /**
     * 管理员获取所有通知
     * @param page 页码
     * @return 一页20个
     */
    @Override
    public List<Notice> adminGetAll(int page){
        return noticeDao.selectAll((page - 1) * 20,20);
    }

    /**
     * 管理员获取通知的总页数
     * @return 页数
     */
    @Override
    public int adminGetPageNumber(){
        int count = noticeDao.count(1);
        count+= noticeDao.count(2);
        count+=noticeDao.count(3);
        return count % 20 > 0 ? count / 20 + 1 : count / 20;
    }
}
