package com.nvyao.service;

import com.nvyao.pojo.Hero;
import com.nvyao.pojo.PageBean;

import java.time.LocalDate;
import java.util.ArrayList;

public interface HeroesService {

    /**
     * 传统的分页做法
     *
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    PageBean getHeroesByPage(Integer page, Integer pageSize, String name, Short gender, Short level, LocalDate begin, LocalDate end);

    /**
     * 使用PageHelper分页插件
     *
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param kungfu
     * @param begin
     * @param end
     * @return
     */
    PageBean getHeroesByPagePlugin(Integer page, Integer pageSize, String name, Short gender, Short level, LocalDate begin, LocalDate end);

    void deleteHeroesByIds(ArrayList<Integer> ids);

    void addHero(Hero hero);

    Hero selectHeroById(Integer id);

    void updateHero(Hero hero);

    Hero login(Hero hero);

}