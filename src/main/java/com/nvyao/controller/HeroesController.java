package com.nvyao.controller;

import com.nvyao.pojo.Hero;
import com.nvyao.pojo.PageBean;
import com.nvyao.pojo.Result;
import com.nvyao.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@RestController
public class HeroesController {

    @Autowired
    private HeroesService heroesService;

    /**
     * 分页查询英雄接口
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/heroes")
    public Result getHeroesByPage(@RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  String name, Short gender, Short level,
                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("查询接口：{}, {}, {}, {}, {}, {}", page, pageSize, name, gender, begin, end);
        //传统分页做法
        //PageBean pageBean = heroesService.getHeroesByPage(page, pageSize, name, gender, begin, end);
        //下面是使用分页插件
        PageBean pageBean = heroesService.getHeroesByPagePlugin(page, pageSize, name, gender, level, begin, end);
        return Result.success(pageBean);
    }

    /**
     * 根据id批量删除英雄数据，也可以删除单条数据
     * @param ids
     * @return
     */
    @DeleteMapping("/heroes/{ids}")
    public Result deleteHeroesByIds(@PathVariable ArrayList<Integer> ids){
        log.info("批量删除员工：{}", ids);
        heroesService.deleteHeroesByIds(ids);
        return Result.success();
    }

    /**
     * 新增英雄接口
     * @param hero
     * @return
     */
    @PostMapping("/heroes")
    public Result addHero(@RequestBody Hero hero){
        log.info("新增英雄：{}", hero);
        heroesService.addHero(hero);
        return Result.success();
    }

    /**
     * 根据英雄id查询英雄接口
     * @param id
     * @return
     */
    @GetMapping("/heroes/{id}")
    public Result selectHeroById(@PathVariable Integer id){
        log.info("根据英雄id查询：{}", id);
        Hero hero = heroesService.selectHeroById(id);
        return Result.success(hero);
    }

    @PutMapping("/heroes")
    public Result updateHero(@RequestBody Hero hero){
        log.info("修改员工：{}", hero);
        heroesService.updateHero(hero);
        return Result.success();
    }

}
