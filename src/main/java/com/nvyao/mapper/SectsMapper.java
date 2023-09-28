package com.nvyao.mapper;

import com.nvyao.pojo.Sect;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface SectsMapper {

    /**
     * 查询所有门派
     * @return
     */
    @Select("select * from sects")
    List<Sect> selectAll();

    /**
     * 新增门派，新增字段introduction，门派介绍
     * @param sect
     */
    @Insert("insert into sects(name, introduction, create_time, update_time) value (#{name}, #{introduction}, #{createTime}, #{updateTime})")
    void insert(Sect sect);

    /**
     * 根据门派id删除门派
     * @param id
     */
    @Delete("delete from sects where id = #{id}")
    void delete(Integer id);

    /**
     * 根据门派id查询门派信息
     * @param id
     * @return
     */
    @Select("select * from sects where id = #{id}")
    Sect selectById(Integer id);

    /**
     * 根据门派id修改门派信息
     * @param sect
     */
    @Update("update sects set name = #{name}, introduction = #{introduction}, update_time = #{updateTime} where id = #{id}")
    void update(Sect sect);

}