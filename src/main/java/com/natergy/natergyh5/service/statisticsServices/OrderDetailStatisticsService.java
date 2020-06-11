package com.natergy.natergyh5.service.statisticsServices;


import com.natergy.natergyh5.dao.statisticsMapper.OrderDetailStatisticsMapper;
import com.natergy.natergyh5.dao.statisticsMapper.OrderStatisticsMapper;
import com.natergy.natergyh5.dao.statisticsMapper.UserStatisticsMapper;
import com.natergy.natergyh5.entity.statisticsEntity.*;
import com.natergy.natergyh5.utils.statisticsUtils.StringUtil;
import com.natergy.natergyh5.utils.statisticsUtils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wj on 2020/4/28.
 */
@Service
public class OrderDetailStatisticsService implements Serializable {
    @Autowired
    private OrderDetailStatisticsMapper orderDetailStatisticsMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    @Autowired
    private UserStatisticsMapper userStatisticsMapper;

    private static final Logger LOG = LoggerFactory.getLogger(OrderDetailStatisticsService.class);

    /***
     * 获取销售订单明细
     * @param params
     *         Map<String, Object> params = new HashMap<>();
     *         params.put("department", "国内销售部");
     *         params.put("jobposition", "国内业务经理");
     *         params.put("orderDateStart", "2018年01月01日 09时35分46秒");
     *         params.put("orderDateEnd", "2020年04月22日 15时02分42秒");
     *         params.put("orderByType", "desc");
     *         params.put("filterGift","y");
     *
     * @return
     */
    public List<PageOrderDetail> orderDetails(Map<String, Object> params) {

        List<Order> orders = orderStatisticsMapper.selectOrders(params);
        List<User> users = userStatisticsMapper.selectUsers(params);

        Map<String, User> userMap = new HashMap<>();
        for (User user : users) {
            userMap.put(user.getName().replaceAll(" ", ""), user);
        }

        List<String> orderNos = new ArrayList<>();
        Map<String, PageOrderDetail> pageOrderDetailMap = new HashMap<>();
        for (Order order : orders) {
            orderNos.add(order.getOrderNo().replaceAll(" ", ""));
            PageOrderDetail pageOrderDetail = new PageOrderDetail();
            pageOrderDetail.setCity(order.getCity());
            pageOrderDetail.setGoodsReach(order.getGoodsReach());
            pageOrderDetail.setCustomer(order.getCustomer());

            String manager = order.getManager();
            if (!StringUtil.isEmptyString(manager) && manager.indexOf("/") > 0) {

                String[] names = order.getManager().replaceAll(" ", "").split("/");
                manager = names[names.length - 1];
            }

            pageOrderDetail.setManager(order.getManager());
            User user = userMap.get(manager);
            if (user != null) {
                pageOrderDetail.setManagerDepartMent(user.getDepartment());
                pageOrderDetail.setManagerPosition(user.getJobposition());
            }else{
                LOG.error("OrderDetailService.java的orderDetails方法，查询订单有误");
            }

            pageOrderDetail.setProvince(order.getProvince());
            pageOrderDetail.setCity(order.getCity());
            pageOrderDetail.setOrderDate(TimeUtil.getEngDateTime(order.getOrderDate()));
            pageOrderDetail.setOrderNo(order.getOrderNo());
            pageOrderDetailMap.put(order.getOrderNo().replaceAll(" ", ""), pageOrderDetail);
        }


        Map<String, Object> orderDetailParam = new HashMap<>();


        orderDetailParam.put("orderNos", orderNos);

        if (null != params.get("filterGift")) {
            orderDetailParam.put("filterGift", params.get("filterGift"));
        }

        if (null != params.get("filter_sample")) {
            orderDetailParam.put("filter_sample", params.get("filter_sample"));
        }
        List<OrderDetail> orderDetails = orderDetailStatisticsMapper.selectOrderDetails(orderDetailParam);

        List<PageOrderDetail> result = new ArrayList<>();
        PageOrderDetail pageOrderDetail = null;
        for (OrderDetail item : orderDetails) {
            if (item.getOrderNo() == null || "".equals(item.getOrderNo())) {
                continue;
            }
            if (!pageOrderDetailMap.containsKey(item.getOrderNo().trim().replaceAll(" ", ""))) {
                System.out.println(item.getOrderNo().trim() + "不存在订单");
            } else {
                pageOrderDetail = pageOrderDetailMap.get(item.getOrderNo().replaceAll(" ", ""));

                pageOrderDetail.setId(item.getId());
                pageOrderDetail.setGoodsReach(item.getGoodsReach());
                pageOrderDetail.setOrderNo(item.getOrderNo());
                pageOrderDetail.setProductCode(item.getProductCode());
                pageOrderDetail.setProductName(item.getProductName());
                pageOrderDetail.setProductStandards(item.getProductStandards());
                pageOrderDetail.setPack(item.getPack());
                pageOrderDetail.setNetWeight(item.getNetWeight());
                pageOrderDetail.setPrice(item.getPrice());
                pageOrderDetail.setTotalPrice(item.getTotalPrice());
                pageOrderDetail.setHasReadTime(item.getHasReadTime());
                result.add(pageOrderDetail);

            }
        }

        return result;

    }


    public List<ReturnBackOrderDetail> selectReturnBackOrderDetails(Map<String, Object> params) {

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
