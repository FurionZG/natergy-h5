package com.natergy.natergyh5.dao;

import com.natergy.natergyh5.entity.Customer;
import com.natergy.natergyh5.entity.ResultOfAddress;
import com.natergy.natergyh5.entity.ResultOfSelectCustomerInfoByName;
import com.natergy.natergyh5.entity.Visit;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 客户Dao
 * @author 杨枕戈
 */
@Repository
@Mapper
public interface CustomerMapper {

    @Select("select 客户名称 from 销售客户资料 where 状态 !='删除' and 业务经理 like CONCAT('%',#{uname},'%')")
    List<String> getCustomersByUser(String uname);

    @Select("select * from 销售客户资料 where 状态!='删除' and 状态!='撤销' and 客户名称 =#{customerName} and 业务经理 like CONCAT('%',#{uname},'%')")
    @Results({
            @Result(property = "customerName",column = "客户名称"),
            @Result(property = "consignee",column = "收货人"),
            @Result(property = "receivingAddress",column = "地址"),
            @Result(property = "collector",column = "收票人"),
            @Result(property = "invoiceAddress",column = "收票地址"),
            @Result(property = "attention",column = "发货注意事项"),
            @Result(property = "invoiceAttention",column = "开票资料"),
    })
    ResultOfSelectCustomerInfoByName getCustomerInfoByName(String customerName,String uname);


    @Select("select 省,市,地址 from 销售客户资料 where 状态!='删除' and 状态!='撤销' and 客户名称 =#{companyName} and 业务经理 like CONCAT('%',#{uname},'%')")
    @Results({
            @Result(property = "province",column = "省"),
            @Result(property = "city",column = "市"),
            @Result(property = "address",column = "地址"),
    })
    ResultOfAddress getAddress(String companyName, String uname);

    @Insert("insert into 销售客户资料(状态,业务经理,省,市,地址,客户类别,客户名称) values('新',#{uname},#{customer.province},#{customer.city},#{customer.address},#{customer.type},#{customer.customerName})")
    Integer saveCustomer(Customer customer, String uname);


    @Update("update 销售客户资料 set 干燥剂类型=#{visit.productType},玻璃企业规模=#{visit.consumption},客户类别=#{visit.customerType},联系人姓名=#{visit.contacts1},联系人2姓名=#{visit.contacts2},联系人3姓名=#{visit.contacts3},联系人手机=#{visit.tel1},联系人2手机=#{visit.tel2},联系人3手机=#{visit.tel3} where Id=#{visit.customerId} ")
    Integer updateVisit(Visit visit, String uname);
}
