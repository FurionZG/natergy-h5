package com.natergy.natergyh5.entity;

import com.natergy.natergyh5.annotations.NatergyColumnName;

public class Customer {
    private String id;
    private String customerName ;
    private String user;
    @NatergyColumnName("省")
    private String province;
    @NatergyColumnName("市")
    private String city;
    private String type;
    @NatergyColumnName("地址")
    private String address;
    private String customerNo;
    @NatergyColumnName("固话")
    private String tel;
    @NatergyColumnName("收货人")
    private String consignee;
    @NatergyColumnName("地址")
    private String receivingAddress;
    @NatergyColumnName("收票人")
    private String collector;
    @NatergyColumnName("收票地址")
    private String invoiceAddress;
    @NatergyColumnName("联系人姓名")
    private String contact;
    @NatergyColumnName("联系人手机")
    private String contactPhoneNum;
    @NatergyColumnName("联系人座机")
    private String contactTelNum;
    @NatergyColumnName("联系人职务")
    private String post;

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", customerName='" + customerName + '\'' +
                ", user='" + user + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", customerNo='" + customerNo + '\'' +
                ", tel='" + tel + '\'' +
                ", consignee='" + consignee + '\'' +
                ", receivingAddress='" + receivingAddress + '\'' +
                ", collector='" + collector + '\'' +
                ", invoiceAddress='" + invoiceAddress + '\'' +
                ", contact='" + contact + '\'' +
                ", contactPhoneNum='" + contactPhoneNum + '\'' +
                ", contactTelNum='" + contactTelNum + '\'' +
                ", post='" + post + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getReceivingAddress() {
        return receivingAddress;
    }

    public void setReceivingAddress(String receivingAddress) {
        this.receivingAddress = receivingAddress;
    }

    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPhoneNum() {
        return contactPhoneNum;
    }

    public void setContactPhoneNum(String contactPhoneNum) {
        this.contactPhoneNum = contactPhoneNum;
    }

    public String getContactTelNum() {
        return contactTelNum;
    }

    public void setContactTelNum(String contactTelNum) {
        this.contactTelNum = contactTelNum;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getCustomerName() {
        return customerName;
    }

    public String getUser() {
        return user;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getType() {
        return type;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setType(String type) {
        this.type = type;
    }

}
