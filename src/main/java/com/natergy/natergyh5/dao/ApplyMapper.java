package com.natergy.natergyh5.dao;

import com.natergy.natergyh5.entity.Apply;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 杨枕戈
 * @Date: 2019/11/27 13:21
 */
@Repository
@Mapper
public interface ApplyMapper {

    @Select("select * from 国内申请单 where 状态!='删除' and (业务经理 like CONCAT('%',#{uname},'%') or 可阅人 like CONCAT('%',#{uname},'%')) order by Id desc limit 10")
    @Results({
            @Result(property = "status",column = "状态"),
            @Result(property = "date",column = "申请日期"),
            @Result(property = "applyNo",column = "申请单编号"),
            @Result(property = "type",column = "分类"),
            @Result(property = "customerNo",column = "客户编码"),
            @Result(property = "customerName",column = "客户名称"),
            @Result(property = "user",column = "业务经理"),
            @Result(property = "content",column = "申请内容"),
            @Result(property = "refundAmount",column = "退款金额"),
            @Result(property = "refundAccount",column = "退款账户"),
            @Result(property = "adjustmentAmount",column = "调账金额"),
            @Result(property = "balance",column = "调后余额"),
            @Result(property = "costs",column = "费用金额"),
            @Result(property = "originalPrice",column = "原单价"),
            @Result(property = "presentPrice",column = "现单价"),
            @Result(property = "givingType",column = "赠品赠送方式"),
            @Result(property = "conversionPrice",column = "折合单价"),
            @Result(property = "approvalComments",column = "销售审批"),
    })
    List<Apply> getApplysByUser(String uname);

    @Select("select * from 国内申请单 where 状态!='删除' and  (业务经理 like CONCAT('%',#{uname},'%') or 可阅人 like CONCAT('%',#{uname},'%'))  order by Id desc limit #{limit},5")
    @Results({
            @Result(property = "status",column = "状态"),
            @Result(property = "date",column = "申请日期"),
            @Result(property = "applyNo",column = "申请单编号"),
            @Result(property = "type",column = "分类"),
            @Result(property = "customerNo",column = "客户编码"),
            @Result(property = "customerName",column = "客户名称"),
            @Result(property = "user",column = "业务经理"),
            @Result(property = "content",column = "申请内容"),
            @Result(property = "refundAmount",column = "退款金额"),
            @Result(property = "refundAccount",column = "退款账户"),
            @Result(property = "adjustmentAmount",column = "调账金额"),
            @Result(property = "balance",column = "调后余额"),
            @Result(property = "costs",column = "费用金额"),
            @Result(property = "originalPrice",column = "原单价"),
            @Result(property = "presentPrice",column = "现单价"),
            @Result(property = "givingType",column = "赠品赠送方式"),
            @Result(property = "conversionPrice",column = "折合单价"),
            @Result(property = "approvalComments",column = "销售审批"),
    })
    List<Apply> reloadApply(String uname,Integer limit);

    @Insert("insert into 国内申请单 set 状态=#{status},申请人=#{user},申请日期=#{date},申请单编号=#{applyNo},分类=#{type},客户编码=#{customerNo},客户名称=#{customerName},业务经理=#{user},申请内容=#{content},退款金额=#{refundAmount},退款账户=#{refundAccount},调账金额=#{adjustmentAmount},调后余额=#{balance},费用金额=#{costs},原单价=#{originalPrice},现单价=#{presentPrice},赠品赠送方式=#{givingType},折合单价=#{conversionPrice}")
    Integer applySave(Apply apply);

    @Select("select count(*) from 国内申请单 where 状态!='删除' and 申请单编号 like CONCAT('%',#{str},'%')")
    Integer applyCount(String str);

    @Update("update 国内申请单 set 状态='删除' where id =#{id}")
    Integer applyDelete(String id);

    @Select("select 申请单编号 from 国内申请单 where Id = #{id}")
    String selectApplyNo(String id);

    @Select("select 申请日期 from 国内申请单 where Id =#{id}")
    String selectDate(String id);

    @Select("select 客户名称 from 国内申请单 where 状态!='删除' and (业务经理 like CONCAT('%',#{uname},'%') or 可阅人 like CONCAT('%',#{uname},'%')) ")
    List<String> getCustomerNames(String uname);

    @Select("select * from 国内申请单 where 状态!='删除' and  (业务经理 like CONCAT('%',#{uname},'%') or 可阅人 like CONCAT('%',#{uname},'%'))  and 客户名称=#{customerName} order by Id desc ")
    @Results({
            @Result(property = "status",column = "状态"),
            @Result(property = "date",column = "申请日期"),
            @Result(property = "applyNo",column = "申请单编号"),
            @Result(property = "type",column = "分类"),
            @Result(property = "customerNo",column = "客户编码"),
            @Result(property = "customerName",column = "客户名称"),
            @Result(property = "user",column = "业务经理"),
            @Result(property = "content",column = "申请内容"),
            @Result(property = "refundAmount",column = "退款金额"),
            @Result(property = "refundAccount",column = "退款账户"),
            @Result(property = "adjustmentAmount",column = "调账金额"),
            @Result(property = "balance",column = "调后余额"),
            @Result(property = "costs",column = "费用金额"),
            @Result(property = "originalPrice",column = "原单价"),
            @Result(property = "presentPrice",column = "现单价"),
            @Result(property = "givingType",column = "赠品赠送方式"),
            @Result(property = "conversionPrice",column = "折合单价"),
            @Result(property = "approvalComments",column = "销售审批"),
    })
    List<Apply> getApplyByAjax(String customerName, String uname);

    @Update("update 国内申请单 set 销售审批=#{approvalComments} where Id=#{id}")
    Integer saveApproval(Apply apply);
}
