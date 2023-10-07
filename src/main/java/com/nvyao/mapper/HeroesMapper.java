package com.nvyao.mapper;

import com.nvyao.pojo.Hero;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface HeroesMapper {

    @Select("select count(*) from heroes")
    Long count();

    //@Select("select * from heroes limit #{page},#{pageSize}")  //移步到HeroesMapper.xml配置文件中
    //传统分页做法
    List<Hero> page(Integer page, Integer pageSize, String name, Short gender, Short level, LocalDate begin, LocalDate end);
    //分页插件做法
    List<Hero> page(String name, Short level, Short gender, LocalDate begin, LocalDate end);

    void delete(ArrayList<Integer> ids);

    @Insert("INSERT INTO heroes(username, name, kungfu, gender, image, level, birthdate,sect_id, create_time, update_time)" +
            " VALUES (#{username},#{name},#{kungfu} ,#{gender},#{image},#{level},#{birthdate},#{sectId},#{createTime},#{updateTime})")
    void insert(Hero hero);

    @Select("select * from heroes where id = #{id}")
    Hero select(Integer id);

    //更新不能用注解，因为并不是每次更新都会更新全部字段，需要用动态sql
    /*@Update("update heroes set " +
            "username = #{username}, " +
            "name = #{name}, " +
            "kungfu = #{kungfu}, " +
            "gender = #{gender}, " +
            "image = #{image}, " +
            "level = #{level}, " +
            "birthdate = #{birthdate}, " +
            "sect_id = #{sectId}, " +
            "update_time = #{updateTime} " +
            "where id=#{id}")*/
    void update(Hero hero);

    @Select("select * from heroes where username = #{username} and password = #{password}")
    Hero selectByUsernameAndPassword(Hero hero);
}