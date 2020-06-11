package com.natergy.natergyh5.dao;

import com.natergy.natergyh5.entity.BusinessApply;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BusinessApplyMapper {

    @Select("select * from 销售出差申请 where 申请人=#{uname} and 状态 !='删除' order by 申请日期 desc limit 10")
    @Results({
            @Result(property = "status",column = "状态"),
            @Result(property = "user",column = "申请人"),
            @Result(property = "date",column = "申请日期"),
            @Result(property = "no",column = "申请编号"),
            @Result(property = "pBusinessStartTime",column = "预计本次出发日期"),
            @Result(property = "pBusinessEndTime",column = "预计本次结束日期"),
            @Result(property = "pDays",column = "预计天数"),
            @Result(property = "pCosts",column = "预计花费"),
            @Result(property = "pPos",column = "计划地点"),
            @Result(property = "pWay",column = "计划路线"),
            @Result(property = "plan",column = "本次出差拜访计划详情"),
            @Result(property = "businessNo",column = "对应出差报告编号"),
            @Result(property = "directorOpinion",column = "部门主管意见"),
            @Result(property = "presidentOpinion",column = "总经理意见"),

    })
    List<BusinessApply> getBusinessApplyList(String uname);



    @Select("select count(*) from 销售出差申请 where 申请人=#{uname} and 申请编号 like CONCAT('%',#{nowYear},'%')")
    String getYearCount(String uname, String nowYear);

    @Insert("insert into 销售出差申请 set 状态=#{status},申请人=#{user},申请日期=#{date},申请编号=#{no},预计本次出发日期=#{pBusinessStartTime},预计本次结束日期=#{pBusinessEndTime},预计天数=#{pDays},预计花费=#{pCosts},计划地点=#{pPos},计划路线=#{pWay},对应出差报告编号=#{businessNo},本次出差拜访计划详情=#{plan}")
    Integer save(BusinessApply businessApply);

    @Select("select * from 销售出差申请 where 申请人=#{uname} and 状态 !='删除' order by 申请日期 desc limit #{limit},5")
    @Results({
            @Result(property = "status",column = "状态"),
            @Result(property = "user",column = "申请人"),
            @Result(property = "date",column = "申请日期"),
            @Result(property = "no",column = "申请编号"),
            @Result(property = "pBusinessStartTime",column = "预计本次出发日期"),
            @Result(property = "pBusinessEndTime",column = "预计本次结束日期"),
            @Result(property = "pDays",column = "预计天数"),
            @Result(property = "pCosts",column = "预计花费"),
            @Result(property = "pPos",column = "计划地点"),
            @Result(property = "pWay",column = "计划路线"),
            @Result(property = "plan",column = "本次出差拜访计划详情"),
            @Result(property = "businessNo",column = "对应出差报告编号"),
            @Result(property = "directorOpinion",column = "部门主管意见"),
            @Result(property = "presidentOpinion",column = "总经理意见"),

    })
    List<BusinessApply> reloadBusinessApply(Integer limit, String uname);

    @Select("select * from 销售出差申请 where 申请人=#{uname} and 状态 !='删除' order by 申请日期 desc limit 10")
    @Results({
            @Result(property = "status",column = "状态"),
            @Result(property = "user",column = "申请人"),
            @Result(property = "date",column = "申请日期"),
            @Result(property = "no",column = "申请编号"),
            @Result(property = "pBusinessStartTime",column = "预计本次出发日期"),
            @Result(property = "pBusinessEndTime",column = "预计本次结束日期"),
            @Result(property = "pDays",column = "预计天数"),
            @Result(property = "pCosts",column = "预计花费"),
            @Result(property = "pPos",column = "计划地点"),
            @Result(property = "pWay",column = "计划路线"),
            @Result(property = "plan",column = "本次出差拜访计划详情"),
            @Result(property = "businessNo",column = "对应出差报告编号"),
            @Result(property = "directorOpinion",column = "部门主管意见"),
            @Result(property = "presidentOpinion",column = "总经理意见"),

    })
    List<BusinessApply> refreshBusinessApply(String uname);

    @Update("update 销售出差申请 set 预计天数=#{pDays},预计本次出发日期=#{pBusinessStartTime},预计本次结束日期=#{pBusinessEndTime},预计天数=#{pDays},预计花费=#{pCosts},计划地点=#{pPos},计划路线=#{pWay},对应出差报告编号=#{businessNo},本次出差拜访计划详情=#{plan} where Id = #{id}")
    Integer update(BusinessApply businessApply);
}
