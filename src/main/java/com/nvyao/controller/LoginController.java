package com.nvyao.controller;

import com.nvyao.pojo.Hero;
import com.nvyao.pojo.Result;
import com.nvyao.service.HeroesService;
import com.nvyao.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private HeroesService heroesService;

    @PostMapping("/login")
    public Result login(@RequestBody Hero hero){
        log.info("{} 正在访问/login接口", hero.getUsername());
        Hero h = heroesService.login(hero);

        //登录成功的时候
        if(h != null){
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("id", h.getId());
            claims.put("username", h.getUsername());
            claims.put("name", h.getName());

            String jwttoken = JwtUtils.generateJwt(claims);
            log.info("{} 英雄登录成功，分配的jwt是：{}", hero.getUsername(), jwttoken);
            return Result.success(jwttoken);
        }
        //登录失败
        log.info("登录失败，用户名：{} 密码：{}", hero.getUsername(), hero.getPassword());
        return Result.error("用户名或密码错误");

    }

}
