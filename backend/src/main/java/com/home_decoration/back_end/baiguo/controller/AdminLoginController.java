package com.home_decoration.back_end.baiguo.controller;

import com.home_decoration.back_end.baiguo.config.Result;
import com.home_decoration.back_end.baiguo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminLoginController {

    @Value("${admin.name}")
    private String name;

    @Value("${admin.password}")
    private String password;

    @RequestMapping("/adminLogin")
    public Result adminLogin(String name, String password) {
        if (this.name.equals(name) && this.password.equals(password)) {
            return Result.success(JwtUtil.createToken());
        }
        return Result.fail(400,"账户或密码错误");
    }
}
