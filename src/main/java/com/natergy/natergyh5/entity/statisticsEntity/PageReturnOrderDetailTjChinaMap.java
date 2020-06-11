package com.natergy.natergyh5.entity.statisticsEntity;


import com.natergy.natergyh5.utils.statisticsUtils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageReturnOrderDetailTjChinaMap {

    private static final Logger LOG = LoggerFactory.getLogger(PageReturnOrderDetailTjChinaMap.class);

    private String manager;

    private List<ReturnBackOrderDetail> returnBackOrderDetails;

    public List<ReturnBackOrderDetail> getReturnBackOrderDetails() {
        return returnBackOrderDetails;
    }

    public void setReturnBackOrderDetails(List<ReturnBackOrderDetail> returnBackOrderDetails) {
        this.returnBackOrderDetails = returnBackOrderDetails;
    }

    private BigDecimal totalWeight = new BigDecimal(0d); //总重

    private BigDecimal totalPrice = new BigDecimal(0d);  //总价

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }


    private Map<String, SaleInfo> provinceMap = new HashMap<>();//用于省份情况

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }


    public Map<String, SaleInfo> getProvinceMap() {
        return provinceMap;
    }

    public void setProvinceMap(Map<String, SaleInfo> provinceMap) {
        this.provinceMap = provinceMap;
    }

    //    核心操作：添加了订单明细才可以用来计算统计信息
    public void addReturnBackOrderDetail(ReturnBackOrderDetail obj) {
        if (this.returnBackOrderDetails == null || this.returnBackOrderDetails.size() == 0) {
            this.returnBackOrderDetails = new ArrayList<>();
        }
        this.returnBackOrderDetails.add(obj);
    }

    public void addReturnBackOrderDetails(List<ReturnBackOrderDetail> objs) {
        if (this.returnBackOrderDetails == null || this.returnBackOrderDetails.size() == 0) {
            this.returnBackOrderDetails = objs;
        } else {
            this.returnBackOrderDetails.addAll(objs);
        }

    }


    //    核心操作：累加 各个省份的 总重 以及 总价
    public void computeDatas() {

        if (this.returnBackOrderDetails == null || this.returnBackOrderDetails.size() == 0)
            return;

        BigDecimal totalWeightItem = new BigDecimal(0d);
        BigDecimal totalPriceItem = new BigDecimal(0d);
        BigDecimal price = new BigDecimal(0d);
        String province = "";

        for (ReturnBackOrderDetail obj : returnBackOrderDetails) {

            if (StringUtil.isEmptyString(obj.getNetWeight()) || StringUtil.isEmptyString(obj.getPrice())) {
                LOG.warn(String.format("退货系统编号%s 价格或者总重不能为空", obj.getSystemNo()));
                continue;
            }

            price = new BigDecimal(obj.getPrice());
            totalWeightItem = new BigDecimal(obj.getNetWeight());
            totalPriceItem = totalWeightItem.multiply(price);

            this.totalPrice = this.totalPrice.add(totalPriceItem);
            this.totalWeight = this.totalWeight.add(totalWeightItem);


            //按照省份统计
            province = obj.getProvince();
            if (StringUtil.isEmptyString(province)) {
//                assert StringUtil.isEmptyString(province) : "退货订单号" + obj.getSystemNo() + "省份不能为空";
//                System.out.println( "退货订单号" + obj.getSystemNo() + "省份不能为空");
                LOG.warn(String.format("退货系统编号%s 省份不能为空", obj.getSystemNo()));
                continue;
            }
            int index = province.indexOf("省");
            if (index != -1) {
                province = province.substring(0, index);
            }

            if (this.provinceMap.get(province) == null) {
                SaleInfo saleInfo = new SaleInfo();
                saleInfo.setTotalNetWeight(totalWeightItem);
                saleInfo.setTotalPrice(totalPriceItem);
                this.provinceMap.put(province, saleInfo);
            } else {
                this.provinceMap.get(province).add(totalWeightItem, totalPriceItem);
            }

        }

    }

}
