package com.natergy.natergyh5.entity.statisticsEntity;

public class OrderDetail {

    private int id;
    private String goodsReach;
    private String orderNo;
    private String productCode;
    private String productName;
    private String productStandards;//规格
    private String pack;//包装
    private String netWeight;//净重
    private String price;//价格
    private String totalPrice;
    private String hasReadTime;

//    public OrderDetail(int id, String goodsReach, String orderNo, String productCode, String productName, String productStandards, String pack, String netWeight, String price, String totalPrice, String hasReadTime) {
//        this.id = id;
//        this.goodsReach = goodsReach;
//        this.orderNo = orderNo;
//        this.productCode = productCode;
//        this.productName = productName;
//        this.productStandards = productStandards;
//        this.pack = pack;
//        this.netWeight = netWeight;
//        this.price = price;
//        this.totalPrice = totalPrice;
//        this.hasReadTime = hasReadTime;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getGoodsReach() {
        return goodsReach;
    }

    public void setGoodsReach(String goodsReach) {
        this.goodsReach = goodsReach;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductStandards() {
        return productStandards;
    }

    public void setProductStandards(String productStandards) {
        this.productStandards = productStandards;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getHasReadTime() {
        return hasReadTime;
    }

    public void setHasReadTime(String hasReadTime) {
        this.hasReadTime = hasReadTime;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", goodsReach='" + goodsReach + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", productStandards='" + productStandards + '\'' +
                ", pack='" + pack + '\'' +
                ", netWeight='" + netWeight + '\'' +
                ", price='" + price + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", hasReadTime='" + hasReadTime + '\'' + '}';
    }
}
