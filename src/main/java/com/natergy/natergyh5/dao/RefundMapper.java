package com.natergy.natergyh5.dao;


import com.natergy.natergyh5.entity.Refund;
import com.natergy.natergyh5.entity.RefundDetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
@Repository
public interface RefundMapper {

    @Select("select * from 销售退货 where 状态 !='删除' and 业务经理 like CONCAT('%',#{uname},'%')  order by id DESC limit 10 ")
    @Results({
            @Result(property = "id", column = "Id"),
            @Result(property = "user", column = "业务经理"),
            @Result(property = "refundTime", column = "退货日期"),
            @Result(property = "carriage", column = "运费"),
            @Result(property = "customerName", column = "客户名称"),
            @Result(property = "status", column = "状态"),
            @Result(property = "systemRefundNo", column = "系统编号"),
            @Result(property = "saleRefundNo", column = "退货编号"),
            @Result(property = "refundAddress", column = "退货地址"),
            @Result(property = "refundContacts", column = "退货联系人"),
            @Result(property = "remarks1", column = "备注1"),
            @Result(property = "customerNo", column = "客户编码"),
            @Result(property = "toDepot",column = "退至仓库"),
            @Result(property = "refundDetails", javaType = List.class, column = "系统编号", many = @Many(select = "com.natergy.natergyh5.dao.RefundMapper.queryOrderDetails"))
    })
    List<Refund> queryRefunds(String uname);

    @Select("select 客户名称 from 销售退货 where 状态 !='删除' and 业务经理 like CONCAT('%',#{uname},'%')")
    Set<String> queryCustomerName(String uname);

    @Select("select * from 销售退货明细 where 系统编号=#{systemRefundNo}")
    @Results({
            @Result(property = "id", column = "Id"),
            @Result(property = "status", column = "状态"),
            @Result(property = "systemRefundNo", column = "系统编号"),
            @Result(property = "productNo", column = "产品编号"),
            @Result(property = "size", column = "规格mm"),
            @Result(property = "innerWrapper", column = "内包装"),
            @Result(property = "outWrapper", column = "外包装"),
            @Result(property = "price", column = "单价"),
            @Result(property = "totalPrice", column = "总价"),
            @Result(property = "totalWeight", column = "退货数量kg"),
            @Result(property = "tax", column = "是否含税"),
            @Result(property = "remark", column = "备注")
    })
    List<RefundDetail> queryOrderDetails(String systemRefundNo);

    @Select("select * from 销售退货 where  状态 !='删除' and 业务经理 like CONCAT('%',#{uname},'%') and 客户名称=#{customerName} order by Id DESC limit 10")
    @Results({
            @Result(property = "id", column = "Id"),
            @Result(property = "user", column = "业务经理"),
            @Result(property = "refundTime", column = "退货日期"),
            @Result(property = "carriage", column = "运费"),
            @Result(property = "customerName", column = "客户名称"),
            @Result(property = "status", column = "状态"),
            @Result(property = "systemRefundNo", column = "系统编号"),
            @Result(property = "saleRefundNo", column = "退货编号"),
            @Result(property = "refundAddress", column = "退货地址"),
            @Result(property = "refundContacts", column = "退货联系人"),
            @Result(property = "remarks1", column = "备注1"),
            @Result(property = "customerNo", column = "客户编码"),
            @Result(property = "toDepot",column = "退至仓库"),
            @Result(property = "refundDetails", javaType = List.class, column = "系统编号", many = @Many(select = "com.natergy.natergyh5.dao.RefundMapper.queryOrderDetails")),
    })
    List<Refund> getOrdersInfoByAjax(String customerName, String uname);

    @Select("select * from 销售退货 where 状态 !='删除' and 业务经理 like CONCAT('%',#{uname},'%')  order by id DESC limit #{limit},5 ")
    @Results({
            @Result(property = "id", column = "Id"),
            @Result(property = "user", column = "业务经理"),
            @Result(property = "refundTime", column = "退货日期"),
            @Result(property = "carriage", column = "运费"),
            @Result(property = "customerName", column = "客户名称"),
            @Result(property = "status", column = "状态"),
            @Result(property = "systemRefundNo", column = "系统编号"),
            @Result(property = "saleRefundNo", column = "退货编号"),
            @Result(property = "refundAddress", column = "退货地址"),
            @Result(property = "refundContacts", column = "退货联系人"),
            @Result(property = "remarks1", column = "备注1"),
            @Result(property = "customerNo", column = "客户编码"),
            @Result(property = "toDepot",column = "退至仓库"),
            @Result(property = "refundDetails", javaType = List.class, column = "系统编号", many = @Many(select = "com.natergy.natergyh5.dao.RefundMapper.queryOrderDetails")),
    })
    List<Refund> queryOrdersLimit(String uname, Integer limit);

    @Select("select * from 销售退货 where  状态 !='删除' and 业务经理 like CONCAT('%',#{salesmanName},'%')   order by id DESC limit 50")
    @Results({
            @Result(property = "id", column = "Id"),
            @Result(property = "user", column = "业务经理"),
            @Result(property = "refundTime", column = "退货日期"),
            @Result(property = "carriage", column = "运费"),
            @Result(property = "customerName", column = "客户名称"),
            @Result(property = "status", column = "状态"),
            @Result(property = "systemRefundNo", column = "系统编号"),
            @Result(property = "saleRefundNo", column = "退货编号"),
            @Result(property = "refundAddress", column = "退货地址"),
            @Result(property = "refundContacts", column = "退货联系人"),
            @Result(property = "remarks1", column = "备注1"),
            @Result(property = "customerNo", column = "客户编码"),
            @Result(property = "toDepot",column = "退至仓库"),
            @Result(property = "refundDetails", javaType = List.class, column = "系统编号", many = @Many(select = "com.natergy.natergyh5.dao.RefundMapper.queryOrderDetails"))
    })
    List<Refund> getRefundInfoBySalesman(String salesmanName);

    @Insert("insert into 销售退货 set 状态=#{status},系统编号=#{systemRefundNo},退货日期=#{refundTime},业务经理=#{user},退货地址=#{refundAddress},退货联系人=#{refundContacts},备注1=#{remarks1},客户名称=#{customerName},客户编码=#{customerNo}")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn="Id")
    Integer saveRefund(Refund refund);

    @Insert("insert into 销售退货明细 set 状态=#{status},系统编号=#{systemRefundNo},产品编号=#{productNo},规格mm=#{size},内包装=#{innerWrapper},外包装=#{outWrapper},退货数量kg=#{totalWeight},单价=#{price},是否含税=#{tax},总价=#{totalPrice},件数=#{count}")
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn="Id")
    Integer saveRefundDetail(RefundDetail refundDetail);
}
