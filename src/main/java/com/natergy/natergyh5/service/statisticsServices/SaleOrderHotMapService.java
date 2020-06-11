package com.natergy.natergyh5.service.statisticsServices;


import com.natergy.natergyh5.dao.statisticsMapper.OrderDetailStatisticsMapper;
import com.natergy.natergyh5.dao.statisticsMapper.OrderStatisticsMapper;
import com.natergy.natergyh5.dao.statisticsMapper.UserStatisticsMapper;
import com.natergy.natergyh5.entity.statisticsEntity.PageOrderDetail;
import com.natergy.natergyh5.entity.statisticsEntity.PageOrderDetailTj;
import com.natergy.natergyh5.entity.statisticsEntity.PageOrderDetailTjChinaMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleOrderHotMapService {
    @Autowired
    private OrderDetailStatisticsMapper orderDetailStatisticsMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    @Autowired
    private UserStatisticsMapper userStatisticsMapper;

    @Autowired
    private OrderDetailStatisticsService orderDetailStatisticsService;

    /*****
     *对每个人 按照单价、规格、地区统计 总量|总价
     *  params  按照    单价、规格、地区  统计 总量|总价
     *                 Map<String, Object> params = new HashMap<>();
     *                 params.put("department", "国内销售部");
     *                 params.put("jobposition", "国内业务经理");
     *                 params.put("orderDateStart", "2018年01月01日 09时35分46秒");
     *                 params.put("orderDateEnd", "2020年04月22日 15时02分42秒");
     *                 params.put("orderByType", "desc");
     *                 params.put("filterGift", "y");
     *                 params.put("filterSample", "y");
     */
    public List<PageOrderDetailTj> orderStaticsByPriceStandardsRegion(Map<String, Object> params) {


        List<PageOrderDetail> pageOrderDetails = orderDetailStatisticsService.orderDetails(params);
        String manager = null;

        Map<String, PageOrderDetailTj> tjMap = new HashMap<>();
        for (PageOrderDetail obj : pageOrderDetails) {
            manager = obj.getManager().replaceAll(" ", "");
            String[] names = manager.split("/");
            if (names.length > 0) {
                manager = names[names.length - 1];
            }

            PageOrderDetailTj tjObj = tjMap.get(manager);
            if (tjObj == null) {
                tjObj = new PageOrderDetailTj();
                tjObj.setManager(manager);
                tjObj.addPageOrderDetail(obj);
                tjMap.put(manager, tjObj);
            } else {
                tjObj.addPageOrderDetail(obj);
            }

        }

        List<PageOrderDetailTj> results = new ArrayList<>();
        for (Map.Entry<String, PageOrderDetailTj> tjEntry : tjMap.entrySet()) {
            if (tjEntry.getValue() == null)
                continue;
            else {
                tjEntry.getValue().computeDatas();
                results.add(tjEntry.getValue());
            }

        }

//        for (Map.Entry<String, PageObjectDetailTj> tjEntry : tjMap.entrySet()) {
//            System.out.println("---------------------------------------------------------------------------------------");
//            System.out.println(tjEntry.getKey());
//            System.out.println("/////////////////////价格统计//////////////////");
//            System.out.println(tjEntry.getValue().getUnitpriceMap());
//            System.out.println("/////////////////////包装统计//////////////////");
//            System.out.println(tjEntry.getValue().getPackMap());
//            System.out.println("/////////////////////省份统计//////////////////");
//            System.out.println(tjEntry.getValue().getProvinceMap());
//        }
        return results;
    }


    /****
     * 组装销售订单热力图数据
     * @param params
     * @return
     */
    public PageOrderDetailTjChinaMap saleChinaMap(Map<String,Object> params){
        List<PageOrderDetail> pageOrderDetails = orderDetailStatisticsService.orderDetails(params);

        PageOrderDetailTjChinaMap chinaMapData = new PageOrderDetailTjChinaMap();
        for (PageOrderDetail obj : pageOrderDetails) {
            chinaMapData.addPageOrderDetail(obj);
        }

        //后台组装统计数据
        chinaMapData.computeDatas();

        return chinaMapData;
    }
}
