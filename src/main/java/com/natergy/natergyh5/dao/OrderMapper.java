package com.natergy.natergyh5.dao;

import com.natergy.natergyh5.entity.Order;
import com.natergy.natergyh5.entity.OrderDetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    List<Order> queryOrders(String uname);


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
    List<OrderDetail> queryOrderDetails(String Id);

    @Select("select 客户编码,合同编号,合同方式,省,市 from 销售客户资料 where 客户名称=#{customerName} and 状态!='删除' and 状态!='撤销' and 状态!=''")
    Map<String,String> getCustomerInfo(String customerName);


    @Insert("insert into 销售订单(状态,业务经理,订单号,客户编码,客户名称,收货地址,收货人,注意事项,生产商,订单日期,合同编号,合同付款方式,订单员,发票注意事项,收票人,收票地址,所属省,所属市,来源) values('草稿',#{uname},#{order.orderNumber},#{customerInfo.客户编码},#{order.customerName},#{order.receivingAddress},#{order.consignee},#{order.attention},#{order.producer},#{order.orderTime},#{customerInfo.合同编号},#{customerInfo.合同方式},#{uname},#{order.invoiceAttention},#{order.collector},#{order.invoiceAddress},#{customerInfo.省},#{customerInfo.市},'微信公众号')")
    @Options(useGeneratedKeys = true, keyProperty = "order.id",keyColumn="Id")
    Integer saveOrder(Order order, String uname,Map customerInfo);


    @Insert("insert into 销售订单明细(状态,订单号,产品编号,品名,规格mm,包装,外包装,件数,净重kg,单价,是否含税,金额,回扣,客户编码,order_id) values('草稿',#{od.orderNumber},#{productNo},'3A分子筛',#{od.size},#{od.innerWrapper},#{od.outwrapper},#{od.count},#{od.totalWeight},#{od.price},#{od.tax},#{od.totalPrice},#{od.rebate},#{customerNo},#{orderId})")
    Integer saveOrderDetail(OrderDetail od, String productNo, String customerNo,String orderId);


    @Select("select * from 销售订单 where 业务经理 =#{uname} and 状态 !='删除' order by id DESC limit #{limit},5")
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
    List<Order> queryOrdersLimit(String uname, Integer limit);
}
