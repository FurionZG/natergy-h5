package com.natergy.natergyh5.service.statisticsServices;


import com.natergy.natergyh5.dao.statisticsMapper.OrderDetailStatisticsMapper;
import com.natergy.natergyh5.dao.statisticsMapper.OrderStatisticsMapper;
import com.natergy.natergyh5.dao.statisticsMapper.UserStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummationOrderHotMapService {
    @Autowired
    private OrderDetailStatisticsMapper orderDetailStatisticsMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    @Autowired
    private UserStatisticsMapper userStatisticsMapper;

    @Autowired
    private OrderDetailStatisticsService orderDetailStatisticsService;


    //TODO 合计订单热力图
}
