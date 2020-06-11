package com.natergy.natergyh5.entity.statisticsEntity;



import com.natergy.natergyh5.utils.statisticsUtils.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageOrderDetailTj {

    private String manager;

//    private List<String> tongjiDimensions;//统计维度 ，除了统计总重，总价之外，看是否统计单价、包装、省份个自卖了多少

    private List<PageOrderDetail> pageOrderDetails;

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

    private Map<Double, SaleInfo> unitpriceMap = new HashMap<>(); //用于单价统计

    private Map<String, SaleInfo> packMap = new HashMap<>(); //用于包装情况

    private Map<String, SaleInfo> provinceMap = new HashMap<>();//用于省份情况

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public List<PageOrderDetail> getPageOrderDetails() {
        return pageOrderDetails;
    }

    public void setPageOrderDetails(List<PageOrderDetail> pageOrderDetails) {
        this.pageOrderDetails = pageOrderDetails;
    }

    public Map<Double, SaleInfo> getUnitpriceMap() {
        return unitpriceMap;
    }

    public void setUnitpriceMap(Map<Double, SaleInfo> unitpriceMap) {
        this.unitpriceMap = unitpriceMap;
    }

    public Map<String, SaleInfo> getPackMap() {
        return packMap;
    }

    public void setPackMap(Map<String, SaleInfo> packMap) {
        this.packMap = packMap;
    }

    public Map<String, SaleInfo> getProvinceMap() {
        return provinceMap;
    }

    public void setProvinceMap(Map<String, SaleInfo> provinceMap) {
        this.provinceMap = provinceMap;
    }

    //    核心操作：添加了订单明细才可以用来计算统计信息
    public void addPageOrderDetail(PageOrderDetail obj) {
        if (this.pageOrderDetails == null || this.pageOrderDetails.size() == 0) {
            this.pageOrderDetails = new ArrayList<>();
        }
        this.pageOrderDetails.add(obj);
    }


    //    核心操作：计算所有的
    public void computeDatas() {

        if (this.pageOrderDetails == null || this.pageOrderDetails.size() == 0)
            return;

        BigDecimal totalWeightItem = new BigDecimal(0d);
        BigDecimal totalPriceItem = new BigDecimal(0d);
        double unitPrice = 0d;
        String pack = "";
        String province = "";

        for (PageOrderDetail obj : pageOrderDetails) {

            if (StringUtil.isEmptyString(obj.getNetWeight()) || StringUtil.isEmptyString(obj.getTotalPrice()) || StringUtil.isEmptyString(obj.getPrice())) {
                continue;
            }

            totalWeightItem = new BigDecimal(obj.getNetWeight());
            totalPriceItem = new BigDecimal(obj.getTotalPrice());

            this.totalPrice=this.totalPrice.add(totalPriceItem);
            this.totalWeight=this.totalWeight.add(totalWeightItem);

            // 按照单价统计
            try{
                unitPrice = Double.valueOf(obj.getPrice());
            }
            catch (Exception e){
              continue;
            }

            if (this.unitpriceMap.get(unitPrice) == null) {
                SaleInfo saleInfo = new SaleInfo();
                saleInfo.setTotalNetWeight(totalWeightItem);
                saleInfo.setTotalPrice(totalPriceItem);
                this.unitpriceMap.put(unitPrice, saleInfo);
            } else {
                this.unitpriceMap.get(unitPrice).add(totalWeightItem, totalPriceItem);
            }


            //按照包装统计
            pack = obj.getPack();
            if (this.packMap.get(pack) == null) {
                SaleInfo saleInfo = new SaleInfo();
                saleInfo.setTotalNetWeight(totalWeightItem);
                saleInfo.setTotalPrice(totalPriceItem);
                this.packMap.put(pack, saleInfo);
            } else {
                this.packMap.get(pack).add(totalWeightItem, totalPriceItem);
            }


            //按照省份统计
            province = obj.getProvince();
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
