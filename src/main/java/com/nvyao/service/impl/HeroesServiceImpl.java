package com.nvyao.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nvyao.mapper.HeroesMapper;
import com.nvyao.pojo.Hero;
import com.nvyao.pojo.PageBean;
import com.nvyao.service.HeroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HeroesServiceImpl implements HeroesService {

    @Autowired
    private HeroesMapper heroesMapper;

    @Override
    public PageBean getHeroesByPage(Integer page, Integer pageSize, String name, Short gender, Short level, LocalDate begin, LocalDate end) {
        //1.获取总记录数
        Long count = heroesMapper.count();

        //2.分页公式
        /**
         * select * from heroes limit 0,5; -- 1-5条
         * select * from heroes limit 5,5; -- 6-10条
         * select * from heroes limit 10,5; -- 11-15条
         * 总条数：18    ---   index：(page - 1) * pageSize
         */

        Integer start = (page - 1) * pageSize;
        List<Hero> heroes = heroesMapper.page(start, pageSize, name, gender, level, begin, end);

        //3.封装PageBean结果集
        PageBean pageBean = new PageBean(count, heroes);
        return pageBean;
    }

    /**
     * 使用分页插件PageHelpder
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param kungfu
     * @param begin
     * @param end
     * @return
     */
    @Override
    public PageBean getHeroesByPagePlugin(Integer page, Integer pageSize, String name, Short gender, Short level, LocalDate begin, LocalDate end) {
        //1.设置分页参数
        PageHelper.startPage(page, pageSize);

        //2.执行查询
        List<Hero> heroes = heroesMapper.page(name, level, gender, begin, end);
        Page<Hero> p = (Page<Hero>) heroes;

        //3.封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void deleteHeroesByIds(ArrayList<Integer> ids) {
        heroesMapper.delete(ids);
    }

    @Override
    public void addHero(Hero hero) {
        hero.setCreateTime(LocalDateTime.now());
        hero.setUpdateTime(LocalDateTime.now());
        heroesMapper.insert(hero);
    }

    @Override
    public Hero selectHeroById(Integer id) {
        Hero hero = heroesMapper.select(id);
        return hero;
    }

    @Override
    public void updateHero(Hero hero) {
        hero.setUpdateTime(LocalDateTime.now());
        heroesMapper.update(hero);
    }

    @Override
    public Hero login(Hero hero) {
        Hero h = heroesMapper.selectByUsernameAndPassword(hero);
        return h;
    }
}
