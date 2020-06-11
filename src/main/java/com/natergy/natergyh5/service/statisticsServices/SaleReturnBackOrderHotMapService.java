package com.natergy.natergyh5.service.statisticsServices;


import com.natergy.natergyh5.dao.statisticsMapper.OrderDetailStatisticsMapper;
import com.natergy.natergyh5.dao.statisticsMapper.OrderStatisticsMapper;
import com.natergy.natergyh5.dao.statisticsMapper.UserStatisticsMapper;
import com.natergy.natergyh5.entity.statisticsEntity.ReturnBackOrder;
import com.natergy.natergyh5.entity.statisticsEntity.ReturnBackOrderDetail;
import com.natergy.natergyh5.utils.statisticsUtils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleReturnBackOrderHotMapService {
    @Autowired
    private OrderDetailStatisticsMapper orderDetailStatisticsMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    @Autowired
    private UserStatisticsMapper userStatisticsMapper;

    @Autowired
    private OrderDetailStatisticsService orderDetailStatisticsService;

    /**********
     * 获取退货订单所有明细数据
     * @param params
     * @return
     */
    public List<ReturnBackOrderDetail> returnBackOrderDetails(Map<String, Object> params) {

//        Map<String, Object> params = new HashMap<>();
//        params.put("department", "国内销售部");
//        params.put("jobposition", "国内业务经理");
//        params.put("start", "2019年01月22日 09时35分46秒");
//        params.put("end", "2020年04月22日 15时02分42秒");
        List<ReturnBackOrder> orders = orderStatisticsMapper.selectReturnBackOrders(params);


        List<String> systemNos = new ArrayList<>();
        Map<String, ReturnBackOrder> returnBackMap = new HashMap<>();
        for (ReturnBackOrder order : orders) {
            systemNos.add(order.getSystemNo());
            returnBackMap.put(order.getSystemNo().trim().replaceAll(" ", ""), order);
        }

        Map<String, Object> returnBackParam = new HashMap<>();
        returnBackParam.put("returnBackOrderNos", systemNos);


        List<ReturnBackOrderDetail> returnBackOrderDetails = orderDetailStatisticsMapper.selectReturnBackOrderDetails(returnBackParam);

        String systemCode = null;
        for (ReturnBackOrderDetail item : returnBackOrderDetails) {
            systemCode = item.getSystemNo();
            if (StringUtil.isEmptyString(systemCode)) continue;
            else {
                systemCode = systemCode.trim().replaceAll(" ", "");
            }
            ReturnBackOrder returnBackOrder = returnBackMap.getOrDefault(systemCode, null);

//            assert returnBackOrder == null : "异常数据，订单明细存在而订单丢失";
            if (returnBackOrder == null) {
                System.out.println("异常数据，订单明细存在而订单不存在" + item.getSystemNo());
                continue;
            }
            item.setManager(returnBackOrder.getManager());
            item.setProvince(returnBackOrder.getProvince());
        }

        return returnBackOrderDetails;
    }

}
