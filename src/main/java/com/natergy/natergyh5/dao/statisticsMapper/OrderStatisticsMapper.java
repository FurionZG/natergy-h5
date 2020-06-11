package com.natergy.natergyh5.dao.statisticsMapper;


import com.natergy.natergyh5.entity.statisticsEntity.Order;
import com.natergy.natergyh5.entity.statisticsEntity.ReturnBackOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface OrderStatisticsMapper {

    List<Order> selectOrders(Map<String, Object> params);


    List<ReturnBackOrder> selectReturnBackOrders(Map<String, Object> params);//销售退货订单
}
