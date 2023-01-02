package com.home_decoration.back_end.baiguo.service;

import com.home_decoration.back_end.baiguo.domain.Info;

import java.util.HashMap;
import java.util.List;

public interface InfoService {
    boolean addDescribe(String describe);
    boolean addHotLine(String hotLine);
    boolean addBeiAnNumber(String beiAnNumber);
    boolean addAddress(String address);

    String getDescribe();
    String getHotLine();
    String getBeiAnNumber();
    String getAddress();

    List<HashMap<String,String>> getBanner();
    boolean delBanner(int id);

    boolean addBanner(String picture);
}
