package com.home_decoration.back_end.baiguo.service;

import com.home_decoration.back_end.baiguo.domain.AnLi;

import java.util.List;

public interface AnLiService {
    List<AnLi> getAll();

    AnLi get(int id);

    boolean add(AnLi anLi);

    boolean del(int id);

    List<AnLi> adminAll();

    List<AnLi> get6();
}
