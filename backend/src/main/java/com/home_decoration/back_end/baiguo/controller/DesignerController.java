package com.home_decoration.back_end.baiguo.controller;

import com.home_decoration.back_end.baiguo.config.Result;
import com.home_decoration.back_end.baiguo.domain.Designer;
import com.home_decoration.back_end.baiguo.service.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DesignerController {

    @Autowired
    private DesignerService designerService;

    @RequestMapping("/designerIntroduce")
    public Result index(){
        return Result.success(designerService.getAll());
    }

    @RequestMapping("/designerIntroduce/get")
    public Result get(int id){
        return Result.success(designerService.get(id));
    }

    @RequestMapping("/admin/designerIntroduce/update")
    public Result update(@RequestBody Designer designer){
        return designerService.update(designer)?Result.success():Result.fail(400,"失败");
    }
}
