package com.home_decoration.back_end.baiguo.service.impl;

import com.home_decoration.back_end.baiguo.dao.DesignerDao;
import com.home_decoration.back_end.baiguo.domain.Designer;
import com.home_decoration.back_end.baiguo.service.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignerServiceImpl implements DesignerService {
    @Autowired
    private DesignerDao designerDao;


    @Override
    public boolean update(Designer designer) {
        return designerDao.update(designer)==1;
    }

    @Override
    public Designer get(int id) {
        return designerDao.selectById(id);
    }

    @Override
    public List<Designer> getAll() {
        return designerDao.selectAll();
    }
}
