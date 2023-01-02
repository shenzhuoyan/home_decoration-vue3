package com.home_decoration.back_end.baiguo.controller;

import com.home_decoration.back_end.baiguo.config.Result;
import com.home_decoration.back_end.baiguo.domain.Info;
import com.home_decoration.back_end.baiguo.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class InfoController {
    @Autowired
    private InfoService infoService;

    //公司介绍
    @RequestMapping("/describe")
    public Result describe(){
        return Result.success(infoService.getDescribe());
    }

    @RequestMapping("/admin/describe/add")
    public Result newDescribe(@RequestBody Map<String, String> map){
        if(map.get("describe")!=null)
            return Result.success(infoService.addDescribe(map.get("describe")));
        return Result.fail(400,"字数过多");
    }


    //热线电话
    @RequestMapping("/hotLine")
    public Result hotLine(){
        return Result.success(infoService.getHotLine());
    }

    @RequestMapping("/admin/hotLine/add")
    public Result newHotLine(@RequestBody Map<String, String> map){
        if(map.get("hotLine")!=null)
            return Result.success(infoService.addHotLine(map.get("hotLine")));
        return Result.fail(400,"失败");
    }

    //备案号
    @RequestMapping("/beiAnNumber")
    public Result beiAnNumber(){
        return Result.success(infoService.getBeiAnNumber());
    }
    @RequestMapping("/admin/beiAnNumber/add")
    public Result newBeiAnNumber(@RequestBody Map<String, String> map){
        if(map.get("beiAnNumber")!=null)
            return Result.success(infoService.addBeiAnNumber(map.get("beiAnNumber")));
        return Result.fail(400,"失败");
    }

    //地址
    @RequestMapping("/address")
    public Result address(){
        return Result.success(infoService.getAddress());
    }

    @RequestMapping("/admin/address/add")
    public Result newAddress(@RequestBody Map<String, String> map){
        if(map.get("address")!=null)
            return Result.success(infoService.addAddress(map.get("address")));
        return Result.fail(400,"失败");
    }


    @RequestMapping("/banner")
    public Result banner(){
        return Result.success(infoService.getBanner());
    }

    @RequestMapping("/admin/banner/add")
    public Result addBanner(String picture){
        return Result.success(infoService.addBanner(picture));
    }

    @RequestMapping("/admin/banner/del")
    public Result delBanner(int id){
        return Result.success(infoService.delBanner(id));
    }
}
