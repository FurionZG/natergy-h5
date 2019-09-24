package com.natergy.natergyh5.dao;

import com.natergy.natergyh5.entity.Order;
import com.natergy.natergyh5.entity.OrderDetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper {

    @Select("select * from 销售订单 where 业务经理 =#{uname} and 状态 !='删除' order by id DESC limit 10")
    @Results({
            @Result(property = "id", column = "Id"),
            @Result(property = "orderTime", column = "订单日期"),
            @Result(property = "orderNumber", column = "订单号"),
            @Result(property = "customerName", column = "客户名称"),
            @Result(property = "consignee", column = "收货人"),
            @Result(property = "receivingAddress", column = "收货地址"),
            @Result(property = "collector", column = "收票人"),
            @Result(property = "invoiceAddress", column = "收票地址"),
            @Result(property = "attention", column = "注意事项"),
            @Result(property = "invoiceAttention", column = "发票注意事项"),
            @Result(property = "producer", column = "生产商"),
            @Result(property = "state", column = "状态"),
            @Result(property = "orderDetails", javaType = List.class, column = "Id", many = @Many(select = "com.natergy.natergyh5.dao.OrderMapper.queryOrderDetails")),
    })
    public List<Order> queryOrders(String uname);


    @Select("select * from 销售订单明细 where order_id=#{Id}")
    @Results({

            @Result(property = "size", column = "规格mm"),
            @Result(property = "innerWrapper", column = "包装"),
            @Result(property = "outwrapper", column = "外包装"),
            @Result(property = "count", column = "件数"),
            @Result(property = "tax", column = "是否含税"),
            @Result(property = "orderNumber", column = "订单号"),
            @Result(property = "price", column = "单价"),
            @Result(property = "rebate", column = "回扣"),
            @Result(property = "totalPrice", column = "金额"),
            @Result(property = "totalWeight", column = "净重kg"),
    })
    public List<OrderDetail> queryOrderDetails(String Id);
}
