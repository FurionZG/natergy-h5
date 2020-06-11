package com.natergy.natergyh5.dao.infactory;

import com.natergy.natergyh5.entity.infactory.Cook;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CookMapper {

    @Select("select * from 食堂订餐 where 状态='新' and 用户=#{uname} ORDER BY 时间 desc limit 0,1")
    @Results({
            @Result(property = "status", column = "状态"),
            @Result(property = "user", column = "用户"),
            @Result(property = "department", column = "部门"),
            @Result(property = "date", column = "时间"),
            @Result(property = "breakfast", column = "早餐"),
            @Result(property = "lunch", column = "午餐"),
            @Result(property = "supper", column = "晚餐"),
            @Result(property = "nightSnack", column = "夜餐"),
            @Result(property = "team", column = "班组"),
            @Result(property = "remarks", column = "备注"),
            @Result(property = "lMantou", column = "午餐馒头"),
            @Result(property = "sMantou", column = "晚餐馒头"),
            @Result(property = "nMantou", column = "夜餐馒头")
    })
    Cook queryYesterdayCook(String uname);

    @Insert("insert into 食堂订餐 set 状态='新',用户=#{user},时间=#{date},早餐=#{breakfast},午餐=#{lunch},晚餐=#{supper},夜餐=#{nightSnack},备注=#{remarks},午餐馒头=#{lMantou},班组=#{team},晚餐馒头=#{sMantou},夜餐馒头=#{nMantou},q=#{q}")
    Integer addCook(Cook cook);

    @Select("select * from 食堂订餐 where 状态='新' and 用户=#{uname} ORDER BY 时间 desc limit 10")
    @Results({
            @Result(property = "status", column = "状态"),
            @Result(property = "user", column = "用户"),
            @Result(property = "department", column = "部门"),
            @Result(property = "date", column = "时间"),
            @Result(property = "breakfast", column = "早餐"),
            @Result(property = "lunch", column = "午餐"),
            @Result(property = "supper", column = "晚餐"),
            @Result(property = "nightSnack", column = "夜餐"),
            @Result(property = "team", column = "班组"),
            @Result(property = "remarks", column = "备注"),
            @Result(property = "lMantou", column = "午餐馒头"),
            @Result(property = "sMantou", column = "晚餐馒头"),
            @Result(property = "nMantou", column = "夜餐馒头")
    })
    List<Cook> queryCookList(String uname);


    @Select("select * from 食堂订餐 where 状态='新' and 用户=#{uname} ORDER BY 时间 desc limit #{limit},5")
    @Results({
            @Result(property = "status", column = "状态"),
            @Result(property = "user", column = "用户"),
            @Result(property = "department", column = "部门"),
            @Result(property = "date", column = "时间"),
            @Result(property = "breakfast", column = "早餐"),
            @Result(property = "lunch", column = "午餐"),
            @Result(property = "supper", column = "晚餐"),
            @Result(property = "nightSnack", column = "夜餐"),
            @Result(property = "team", column = "班组"),
            @Result(property = "remarks", column = "备注"),
            @Result(property = "lMantou", column = "午餐馒头"),
            @Result(property = "sMantou", column = "晚餐馒头"),
            @Result(property = "nMantou", column = "夜餐馒头")
    })
    List<Cook> queryCookListLimit(String uname, Integer limit);

    @Update("update 食堂订餐 set 早餐=#{breakfast},午餐=#{lunch},晚餐=#{supper},夜餐=#{nightSnack},备注=#{remarks},午餐馒头=#{lMantou},班组=#{team},晚餐馒头=#{sMantou},夜餐馒头=#{nMantou} where id = #{id}")
    Integer updateCook(Cook cook);

    @Select("select * from 食堂订餐 where 时间=#{date} and 班组=#{workspace} and 状态='新'")
    @Results({
            @Result(property = "status", column = "状态"),
            @Result(property = "user", column = "用户"),
            @Result(property = "department", column = "部门"),
            @Result(property = "date", column = "时间"),
            @Result(property = "breakfast", column = "早餐"),
            @Result(property = "lunch", column = "午餐"),
            @Result(property = "supper", column = "晚餐"),
            @Result(property = "nightSnack", column = "夜餐"),
            @Result(property = "team", column = "班组"),
            @Result(property = "remarks", column = "备注"),
            @Result(property = "lMantou", column = "午餐馒头"),
            @Result(property = "sMantou", column = "晚餐馒头"),
            @Result(property = "nMantou", column = "夜餐馒头")
    })
    List<Cook> countCook(String date, String workspace);
}
