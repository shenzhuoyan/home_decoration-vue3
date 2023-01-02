package com.home_decoration.back_end.baiguo.service;

import com.home_decoration.back_end.baiguo.domain.Notice;

import java.util.List;

public interface NoticeService {
    boolean add(Notice notice);

    List<Notice> getAll(int page);

    boolean setUp(int id);

    boolean setTop(int id);

    boolean resume(int id);

    boolean del(int id);

    int pageAll();

//    List<Notice> getDel(int page);
//
//    int pageDel();

    Notice get(int id);

    List<Notice> getNew();

    List<Notice> important();

    List<Notice> adminGetAll(int page);

    int adminGetPageNumber();
}
