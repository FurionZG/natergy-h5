package com.natergy.natergyh5.dao;

import com.natergy.natergyh5.entity.ResultOfSelectCustomerInfoByName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CustomerMapper {

    @Select("select 客户名称 from 销售客户资料 where 状态 !='删除' and 业务经理 like CONCAT('%',#{uname},'%')")
    public List<String> getCustomersByUser(String uname);

    @Select("select * from 销售客户资料 where 状态!='删除' and 状态!='撤销' and 客户名称 =#{customerName}")
    @Results({
            @Result(property = "customerName",column = "客户名称"),
            @Result(property = "consignee",column = "收货人"),
            @Result(property = "receivingAddress",column = "地址"),
            @Result(property = "collector",column = "收票人"),
            @Result(property = "invoiceAddress",column = "收票地址"),
            @Result(property = "attention",column = "发货注意事项"),
            @Result(property = "invoiceAttention",column = "开票资料"),
    })
    public ResultOfSelectCustomerInfoByName getCustomerInfoByName(String customerName);
}
