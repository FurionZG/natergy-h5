package com.natergy.natergyh5.entity.statisticsEntity;

public class Order {
    private int id;
    private String goodsReach;//货物已抵达
    private String manager;
    private String orderNo;
    private String sendNo;//发货编号
    private String orderDate;//订单日期
    private String sendDate;//发货日期
    private String province;
    private String city;
    private String customer;
    private String customerCode;
    private String deliveryAddress;//收获地址
    private String deliveryPeople;//收货人
    private String newCustomer;
    private String contractCode;//合同编号
    private String attention;
    private String remark;
    private String financeRemark;//财务备注
    private String wareHouse;//发货仓库
    private String producer;//生产商

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

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSendNo() {
        return sendNo;
    }

    public void setSendNo(String sendNo) {
        this.sendNo = sendNo;
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

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryPeople() {
        return deliveryPeople;
    }

    public void setDeliveryPeople(String deliveryPeople) {
        this.deliveryPeople = deliveryPeople;
    }

    public String getNewCustomer() {
        return newCustomer;
    }

    public void setNewCustomer(String newCustomer) {
        this.newCustomer = newCustomer;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFinanceRemark() {
        return financeRemark;
    }

    public void setFinanceRemark(String financeRemark) {
        this.financeRemark = financeRemark;
    }

    public String getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(String wareHouse) {
        this.wareHouse = wareHouse;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", goodsReach='" + goodsReach + '\'' +
                ", manager='" + manager + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", sendNo='" + sendNo + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", sendDate='" + sendDate + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", customer='" + customer + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", deliveryPeople='" + deliveryPeople + '\'' +
                ", newCustomer='" + newCustomer + '\'' +
                ", contractCode='" + contractCode + '\'' +
                ", attention='" + attention + '\'' +
                ", remark='" + remark + '\'' +
                ", financeRemark='" + financeRemark + '\'' +
                ", wareHouse='" + wareHouse + '\'' +
                ", producer='" + producer + '\'' +
                '}';
    }
}
