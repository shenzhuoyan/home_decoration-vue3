package com.home_decoration.back_end.baiguo.service.impl;

import com.home_decoration.back_end.baiguo.dao.InfoDao;
import com.home_decoration.back_end.baiguo.domain.Info;
import com.home_decoration.back_end.baiguo.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private InfoDao infoDao;

    private boolean addInfo(String content,int type){
        Info info = new Info();
        info.setContent(content);
        info.setType(type);
        return infoDao.insert(info)==1;
    }
    @Override
    public boolean addDescribe(String describe) {
        return addInfo(describe,1);
    }

    @Override
    public boolean addHotLine(String hotLine) {
        return addInfo(hotLine,2);
    }

    @Override
    public boolean addBeiAnNumber(String beiAnNumber) {
        return addInfo(beiAnNumber,3);
    }

    @Override
    public boolean addAddress(String address) {
        return addInfo(address,4);
    }

    @Override
    public String getDescribe() {
        return infoDao.select(1);
    }

    @Override
    public String getHotLine() {
        return infoDao.select(2);
    }

    @Override
    public String getBeiAnNumber() {
        return infoDao.select(3);
    }

    @Override
    public String getAddress() {
        return infoDao.select(4);
    }

    @Override
    public List<HashMap<String,String>> getBanner() {
        List<Info> banners = infoDao.selectAllByType(5);
        List<HashMap<String,String>> bannerList= new ArrayList<>();
        for (Info info:
             banners) {
            HashMap<String,String> banner=new HashMap<>();
            banner.put("name",String.valueOf(info.getId()));
            banner.put("url",info.getContent());
            bannerList.add(banner);
        }
        return bannerList;
    }

    @Override
    public boolean delBanner(int id) {
        return infoDao.delete(id)==1;
    }

    @Override
    public boolean addBanner(String picture) {
        return addInfo(picture,5);
    }

}
