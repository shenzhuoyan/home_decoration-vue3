package com.home_decoration.back_end.baiguo.service;

import com.home_decoration.back_end.baiguo.domain.Designer;

import java.util.List;

public interface DesignerService {
    boolean update(Designer designer);

    Designer get(int id);

    List<Designer> getAll();
}
