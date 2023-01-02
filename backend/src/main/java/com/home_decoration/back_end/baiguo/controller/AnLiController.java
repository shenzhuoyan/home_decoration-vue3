package com.home_decoration.back_end.baiguo.controller;

import com.home_decoration.back_end.baiguo.config.Result;
import com.home_decoration.back_end.baiguo.domain.AnLi;
import com.home_decoration.back_end.baiguo.service.AnLiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnLiController {
    @Autowired
    AnLiService anLiService;


    @RequestMapping("/anLi/all")
    public Result all(){
        return Result.success(anLiService.getAll());
    }


    @RequestMapping("/anLi/get")
    public Result get(Integer id){
        if (id == null) return Result.fail(400, "id为空");
        return Result.success(anLiService.get(id));
    }


    @RequestMapping("/admin/anLi/add")
    public Result get(@RequestBody AnLi anLi){
        return anLiService.add(anLi)?Result.success():Result.fail(400,"失败");
    }


    @RequestMapping("/admin/anLi/del")
    public Result del(int id){
        return anLiService.del(id)?Result.success():Result.fail(400,"失败");
    }


    @RequestMapping("/admin/anLi/all")
    public Result adminAll(){
        return Result.success(anLiService.adminAll());
    }

    @RequestMapping("/anLi/six")
    public Result six(){
        return Result.success(anLiService.get6());
    }
}
