package com.natergy.natergyh5.service.statisticsServices;


import com.natergy.natergyh5.dao.statisticsMapper.OrderStatisticsMapper;
import com.natergy.natergyh5.entity.statisticsEntity.Order;
import com.natergy.natergyh5.entity.statisticsEntity.ReturnBackOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * Created by wj on 2018/5/5.
 */
@Service
public class OrderStatisticsService implements Serializable {
    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    public List<Order> selectOrders(Map<String, Object> params) {
        List<Order> orders = orderStatisticsMapper.selectOrders(params);
        return orders;
    }


    List<ReturnBackOrder> selectReturnBackOrders(Map<String, Object> params) {
        List<ReturnBackOrder> returnBackOrders = orderStatisticsMapper.selectReturnBackOrders(params);
        return returnBackOrders;
    }
}
