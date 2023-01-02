package com.home_decoration.back_end.baiguo.service.impl;

import com.home_decoration.back_end.baiguo.dao.AnLiDao;
import com.home_decoration.back_end.baiguo.domain.AnLi;
import com.home_decoration.back_end.baiguo.service.AnLiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnLiServiceImpl implements AnLiService {

    @Autowired
    private AnLiDao anLiDao;

    @Override
    public List<AnLi> getAll() {
        return anLiDao.selectAllSimple();
    }

    @Override
    public AnLi get(int id) {
        return anLiDao.selectById(id);
    }

    @Override
    public boolean add(AnLi anLi) {
        return anLiDao.insert(anLi)==1;
    }

    @Override
    public boolean del(int id) {
        return anLiDao.delete(id)==1;
    }

    @Override
    public List<AnLi> adminAll() {
        return anLiDao.selectAll();
    }

    @Override
    public List<AnLi> get6() {
        return anLiDao.select6Simple();
    }
}
