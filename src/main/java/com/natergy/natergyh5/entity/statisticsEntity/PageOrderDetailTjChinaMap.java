package com.natergy.natergyh5.entity.statisticsEntity;



import com.natergy.natergyh5.utils.statisticsUtils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****
 * 封装地图数据
 */
public class PageOrderDetailTjChinaMap {
    private static final Logger LOG = LoggerFactory.getLogger(PageOrderDetailTjChinaMap.class);

    private List<PageOrderDetail> pageOrderDetails;

    private BigDecimal totalWeight = new BigDecimal(0d); //总重

    private BigDecimal totalPrice = new BigDecimal(0d);  //总价

    private int low = 0;

    private int high = 0;

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

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

    private Map<String, SaleInfo> provinceMap = new HashMap<>(); //存放各个省的销售情况

    public Map<String, SaleInfo> getProvinceMap() {
        return provinceMap;
    }

    public void setProvinceMap(Map<String, SaleInfo> provinceMap) {
        this.provinceMap = provinceMap;
    }

    private List<PageOrderDetail> getPageOrderDetails() {
        return pageOrderDetails;
    }

    private void setPageOrderDetails(List<PageOrderDetail> pageOrderDetails) {
        this.pageOrderDetails = pageOrderDetails;
    }


    //    核心操作：添加了订单明细才可以用来计算统计信息
    public void addPageOrderDetail(PageOrderDetail obj) {
        if (this.pageOrderDetails == null || this.pageOrderDetails.size() == 0) {
            this.pageOrderDetails = new ArrayList<>();
        }
        this.pageOrderDetails.add(obj);
    }

    public void addPageOrderDetail(List<PageOrderDetail> objs) {
        if (this.pageOrderDetails == null || this.pageOrderDetails.size() == 0) {
            this.pageOrderDetails = objs;
        }else{
            this.pageOrderDetails.addAll(objs);
        }

    }


    //    核心操作：直到所有的
    public void computeDatas() {

        if (this.pageOrderDetails == null || this.pageOrderDetails.size() == 0)
            return;

        BigDecimal totalWeightItem = new BigDecimal(0d);
        BigDecimal totalPriceItem = new BigDecimal(0d);
        double unitPrice = 0d;
        String province = "";


        for (PageOrderDetail obj : pageOrderDetails) {

            if (StringUtil.isEmptyString(obj.getNetWeight()) || StringUtil.isEmptyString(obj.getTotalPrice()) || StringUtil.isEmptyString(obj.getPrice())) {
                LOG.warn(String.format("销售订单系统编号%s价格和总价不能为空",obj.getOrderNo() ));
                continue;
            }

            totalWeightItem = new BigDecimal(obj.getNetWeight());
            totalPriceItem = new BigDecimal(obj.getTotalPrice());


            this.totalPrice = this.totalPrice.add(totalPriceItem);
            this.totalWeight = this.totalWeight.add(totalWeightItem);

            province = obj.getProvince().replaceAll(" ", "");

            if (StringUtil.isEmptyString(province)) {
                LOG.warn(String.format("销售订单系统编号%s省份不能为空",obj.getOrderNo() ));
                continue;
            }

            //山东省 需要截取山东，因为echart地图省份没有“省”这个字
            int suffixIndex = province.indexOf("省");
            if (suffixIndex != -1) {
                province = province.substring(0, suffixIndex);
            }

            suffixIndex = province.indexOf("市");//重庆市 天津等直辖市
            if (suffixIndex != -1) {
                province = province.substring(0, suffixIndex);
            }

            suffixIndex = province.indexOf("区");//维吾尔族自治区等
            if (suffixIndex != -1) {
                province = province.substring(0, suffixIndex);
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

//        SaleInfo minSaleInfo = MapUtil.getMinValue(this.getProvinceMap());
//        setLow(minSaleInfo.getTotalNetWeight().compareTo(minSaleInfo.getTotalPrice()) > 0 ? minSaleInfo.getTotalPrice().intValue() : minSaleInfo.getTotalNetWeight().intValue());
//
//        SaleInfo maxSaleInfo = MapUtil.getMaxValue(this.getProvinceMap());
//        setHigh(maxSaleInfo.getTotalPrice().compareTo(maxSaleInfo.getTotalNetWeight()) > 0 ? maxSaleInfo.getTotalPrice().intValue() : maxSaleInfo.getTotalNetWeight().intValue());

    }

}