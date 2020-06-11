package com.natergy.natergyh5.entity.statisticsEntity;


public class PageOrderDetail extends OrderDetail {

    private String manager;
    private String managerPosition;
    private String managerDepartMent;
    private String province;
    private String city;
    private String customer;
    private String orderDate;
    private String sendDate;

    public PageOrderDetail() {

    }

    public PageOrderDetail(int id, String goodsReach, String orderNo, String productCode, String productName, String productStandards, String pack, String netWeight, String price, String totalPrice, String hasReadTime) {
        super.setId(id);
        super.setGoodsReach(goodsReach);
        super.setOrderNo(orderNo);
        super.setProductCode(productCode);
        super.setProductName(productName);
        super.setProductStandards(productStandards);
        super.setPack(pack);
        super.setNetWeight(netWeight);
        super.setPrice(price);
        super.setTotalPrice(totalPrice);
        super.setHasReadTime(hasReadTime);
    }


    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManagerPosition() {
        return managerPosition;
    }

    public void setManagerPosition(String managerPosition) {
        this.managerPosition = managerPosition;
    }

    public String getManagerDepartMent() {
        return managerDepartMent;
    }

    public void setManagerDepartMent(String managerDepartMent) {
        this.managerDepartMent = managerDepartMent;
    }


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    @Override
    public String toString() {
        return "{" +
                "manager='" + manager + '\'' +
                ", managerPosition='" + managerPosition + '\'' +
                ", managerDepartMent='" + managerDepartMent + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", customer='" + customer + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", sendDate='" + sendDate + '\'' +
                super.toString();
    }
}
