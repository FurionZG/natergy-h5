package com.natergy.natergyh5.entity.statisticsEntity;

import com.natergy.natergyh5.utils.statisticsUtils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageSummationSaleHotChinaMap {
    private static final Logger LOG = LoggerFactory.getLogger(PageSummationSaleHotChinaMap.class);

    private String manager;

    private List<PageOrderDetail> orderDetails;//销售订单详情列表

    private List<ReturnBackOrderDetail> returnBackOrderDetails; //退货订单详情列表

    public List<PageOrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<PageOrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

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

    public void addOrderDetail(PageOrderDetail obj) {
        if (this.orderDetails == null || this.orderDetails.size() == 0) {
            this.orderDetails = new ArrayList<>();
        }
        this.orderDetails.add(obj);
    }

    public void addOrderDetails(List<PageOrderDetail> objs) {
        if (this.orderDetails == null || this.orderDetails.size() == 0) {
            this.orderDetails = objs;
        } else {
            this.orderDetails.addAll(objs);
        }

    }


    //    核心操作：累加各个省的 总重 以及 总价
    public void computeDatas() {


        if (this.orderDetails != null && this.orderDetails.size() > 0) {

            String orderprovince = "";
            BigDecimal orderTotalWeightItem = new BigDecimal(0d);
            BigDecimal orderTotalPriceItem = new BigDecimal(0d);
            for (PageOrderDetail orderDetail : orderDetails) {

                if (StringUtil.isEmptyString(orderDetail.getNetWeight()) || StringUtil.isEmptyString(orderDetail.getPrice())) {
                    LOG.warn(String.format("销售订单%s 价格或者总重不能为空", orderDetail.getOrderNo()));
                    continue;
                }

                orderTotalWeightItem = new BigDecimal(orderDetail.getNetWeight());
                orderTotalPriceItem = new BigDecimal(orderDetail.getTotalPrice());

                this.totalPrice = this.totalPrice.add(orderTotalPriceItem);
                this.totalWeight = this.totalWeight.add(orderTotalWeightItem);


                //按照省份统计
                orderprovince = orderDetail.getProvince();
                if (StringUtil.isEmptyString(orderprovince)) {
//                assert StringUtil.isEmptyString(province) : "退货订单号" + obj.getSystemNo() + "省份不能为空";
                    LOG.warn(String.format("销售订单%s 省份不能为空", orderDetail.getOrderNo()));
                    continue;
                }
                int index = orderprovince.indexOf("省");
                if (index != -1) {
                    orderprovince = orderprovince.substring(0, index).replaceAll(" ", "");
                }


                if (this.provinceMap.get(orderprovince) == null) {
                    SaleInfo saleInfo = new SaleInfo();
                    saleInfo.setTotalNetWeight(orderTotalWeightItem);
                    saleInfo.setTotalPrice(orderTotalPriceItem);
                    this.provinceMap.put(orderprovince, saleInfo);
                } else {
                    this.provinceMap.get(orderprovince).add(orderTotalWeightItem, orderTotalPriceItem);
                }


            }
        }


        if (this.returnBackOrderDetails != null && this.returnBackOrderDetails.size() > 0) {
            BigDecimal returnTotalWeightItem = new BigDecimal(0d);
            BigDecimal returnTotalPriceItem = new BigDecimal(0d);
            BigDecimal price = new BigDecimal(0d);
            String province = "";
            for (ReturnBackOrderDetail obj : returnBackOrderDetails) {

                if (StringUtil.isEmptyString(obj.getNetWeight()) || StringUtil.isEmptyString(obj.getPrice())) {
                    LOG.warn(String.format("退货订单%s 总重或价格不能为空", obj.getSystemNo()));
                    continue;
                }

                price = new BigDecimal(obj.getPrice());
                returnTotalWeightItem = new BigDecimal(obj.getNetWeight());
                returnTotalPriceItem = returnTotalWeightItem.multiply(price);

                this.totalPrice = this.totalPrice.subtract(returnTotalPriceItem);
                this.totalWeight = this.totalWeight.subtract(returnTotalWeightItem);


                //按照省份统计
                province = obj.getProvince();
                if (StringUtil.isEmptyString(province)) {
//                assert StringUtil.isEmptyString(province) : "退货订单号" + obj.getSystemNo() + "省份不能为空";
                    LOG.warn(String.format("退货订单%s 省份不能为空", obj.getSystemNo()));
                    continue;
                }
                int index = province.indexOf("省");
                if (index != -1) {
                    province = province.substring(0, index).replaceAll(" ", "");
                }

                if (this.provinceMap.get(province) == null) {
                    SaleInfo saleInfo = new SaleInfo();
                    saleInfo.setTotalNetWeight(returnTotalWeightItem);
                    saleInfo.setTotalPrice(returnTotalPriceItem);
                    this.provinceMap.put(province, saleInfo);
                } else {
                    this.provinceMap.get(province).subtract(returnTotalWeightItem, returnTotalPriceItem);
                }

            }
        }


    }

}
