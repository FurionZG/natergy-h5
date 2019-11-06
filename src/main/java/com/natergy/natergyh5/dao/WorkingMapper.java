package com.natergy.natergyh5.dao;

import com.natergy.natergyh5.entity.Working;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 销售工作进程Dao
 * @author 杨枕戈
 */
@Repository
@Mapper
public interface WorkingMapper {


    @Select("select * from 销售工作汇报 where 汇报人=#{uname} or 可阅人 like CONCAT('%',#{uname},'%') order by Id desc limit 10")
    @Results({
            @Result(property = "id",column = "Id"),
            @Result(property = "status",column = "状态"),
            @Result(property = "date",column = "日期"),
            @Result(property = "todayWorking",column = "今日工作"),
            @Result(property = "tomorrowWorking",column = "明日工作"),
            @Result(property = "marks",column = "备注"),
            @Result(property = "options",column = "附件"),
            @Result(property = "user",column = "汇报人"),
            @Result(property = "reader",column = "可阅人"),
            @Result(property = "approved",column = "已阅人"),
    })
    List<Working> getWorkings(String uname);

    @Insert("insert into 销售工作汇报(状态,日期,今日工作,明日工作,备注,附件,汇报人,可阅人) values('待审',#{date},#{todayWorking},#{tomorrowWorking},#{marks},#{options},#{user},#{reader})")
    Integer saveWorking(Working working);

    @Update("update 销售工作汇报 set 日期=#{date},今日工作=#{todayWorking},明日工作=#{tomorrowWorking},备注=#{marks},附件=#{options} where Id=#{id}")
    Integer updateWorking(Working working);

    @Select("select * from 销售工作汇报 where 汇报人=#{uname} or 可阅人 like CONCAT('%',#{uname},'%') order by Id desc limit #{limit},5")
    @Results({
            @Result(property = "id",column = "Id"),
            @Result(property = "status",column = "状态"),
            @Result(property = "date",column = "日期"),
            @Result(property = "todayWorking",column = "今日工作"),
            @Result(property = "tomorrowWorking",column = "明日工作"),
            @Result(property = "marks",column = "备注"),
            @Result(property = "options",column = "附件"),
            @Result(property = "user",column = "汇报人"),
            @Result(property = "reader",column = "可阅人"),
            @Result(property = "approved",column = "已阅人"),
    })
    List<Working> reloadWorkings(Integer limit, String uname);

    @Select("select 汇报人 from 销售工作汇报 where 汇报人=#{uname} or 可阅人 like CONCAT('%',#{uname},'%')")
    List<String> getWorkingsName(String uname);

    @Select("select * from 销售工作汇报 where 汇报人=#{name} order by Id desc limit 30")
    @Results({
            @Result(property = "id",column = "Id"),
            @Result(property = "status",column = "状态"),
            @Result(property = "date",column = "日期"),
            @Result(property = "todayWorking",column = "今日工作"),
            @Result(property = "tomorrowWorking",column = "明日工作"),
            @Result(property = "marks",column = "备注"),
            @Result(property = "options",column = "附件"),
            @Result(property = "user",column = "汇报人"),
            @Result(property = "reader",column = "可阅人"),
            @Result(property = "approved",column = "已阅人"),
    })
    List<Working> getWorkingsByName(String uname,String name,String salesExecutive);

    @Select("select * from 销售工作汇报 where 汇报人=#{name} order by Id desc limit #{limit},5")
    @Results({
            @Result(property = "id",column = "Id"),
            @Result(property = "status",column = "状态"),
            @Result(property = "date",column = "日期"),
            @Result(property = "todayWorking",column = "今日工作"),
            @Result(property = "tomorrowWorking",column = "明日工作"),
            @Result(property = "marks",column = "备注"),
            @Result(property = "options",column = "附件"),
            @Result(property = "user",column = "汇报人"),
            @Result(property = "reader",column = "可阅人"),
            @Result(property = "approved",column = "已阅人"),
    })
    List<Working> reloadWorkingsByName(Integer limit, String uname, String name);
}
