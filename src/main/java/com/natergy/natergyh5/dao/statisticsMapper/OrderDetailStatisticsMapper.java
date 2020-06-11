package com.natergy.natergyh5.dao.statisticsMapper;


import com.natergy.natergyh5.entity.statisticsEntity.OrderDetail;
import com.natergy.natergyh5.entity.statisticsEntity.ReturnBackOrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wj on 2018/5/5.
 */
@Mapper
@Repository
public interface OrderDetailStatisticsMapper {

    List<OrderDetail> selectOrderDetails(Map<String, Object> params);

    List<ReturnBackOrderDetail> selectReturnBackOrderDetails(Map<String, Object> params);//销售退货订单明细
}
