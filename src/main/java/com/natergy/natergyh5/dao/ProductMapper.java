package com.natergy.natergyh5.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductMapper {

    @Select("select DISTINCT 规格 from 销售产品列表")
    List<String> getSize();

    @Select("select DISTINCT `外包装（箱号）`  from 销售产品列表 where 规格=#{size} ")
    List<String> getOutWrapper(String size);

    @Select("select DISTINCT `内包装（公斤每包）`  from 销售产品列表 where 规格=#{size} and 外包装（箱号）=#{outWrapper}")
    List<String> getInnerWrapper(String size,String outWrapper);

    /**
     * 查询产品规格编号的方法
     * @param size 规格
     * @param innerWrapper 内包装
     * @param outwrapper 外包装
     * @return 该内包装+外包装+规格对应的产品编号
     */
    @Select("select 产品编号 from 销售产品列表 where 规格=#{size} and 内包装（公斤每包）=#{innerWrapper} and 外包装（箱号）=#{outwrapper}")
    String getProductNo(String size, String innerWrapper, String outwrapper);
}
