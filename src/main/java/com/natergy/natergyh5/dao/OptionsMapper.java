package com.natergy.natergyh5.dao;

import com.natergy.natergyh5.entity.Option;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 附件Dao
 * @author 杨枕戈
 */
@Repository
@Mapper
public interface OptionsMapper {
    @Insert("<script>insert into 附件(名称,位置) values " +
            "<foreach collection='list' item='c' separator=','>(#{c.name},#{c.pos})</foreach></script>")
    @Options(useGeneratedKeys=true,keyColumn="Id",keyProperty="id")
    void saveOptions(@Param("list") List<Option> filename);

    @Insert("insert into 附件(名称,位置) values(#{name},#{pos})")
    @Options(useGeneratedKeys=true,keyColumn="Id",keyProperty="id")
    void saveOption(Option option);

    @Select("select 名称 from 附件 where Id=#{id}")
    String queryOption(String id);
}
