package com.natergy.natergyh5.entity;

import java.util.List;

public class Visit {

    private String id;
    private String status;
    private String date;
    private String user;
    private String province;
    private String city;
    private String nowAddress;
    private String customerName;
    private String address;
    private String contacts1;
    private String contacts2;
    private String contacts3;
    private String tel1;
    private String tel2;
    private String tel3;
    private String record;
    private String productBrand;
    private String productType;
    private String consumption;
    private String customerType;
    private String price;
    private List<String> images;
    private String imgStr;
    private String customerNo;
    private String customerId;
    private String businessNo;
    private String longitude;
    private String latitude;
    private String district;
    private String street;

    @Override
    public String toString() {
        return "Visit{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", user='" + user + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", nowAddress='" + nowAddress + '\'' +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                ", contacts1='" + contacts1 + '\'' +
                ", contacts2='" + contacts2 + '\'' +
                ", contacts3='" + contacts3 + '\'' +
                ", tel1='" + tel1 + '\'' +
                ", tel2='" + tel2 + '\'' +
                ", tel3='" + tel3 + '\'' +
                ", record='" + record + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", productType='" + productType + '\'' +
                ", consumption='" + consumption + '\'' +
                ", customerType='" + customerType + '\'' +
                ", price='" + price + '\'' +
                ", images=" + images +
                ", imgStr='" + imgStr + '\'' +
                ", customerNo='" + customerNo + '\'' +
                ", customerId='" + customerId + '\'' +
                ", businessNo='" + businessNo + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public String getNowAddress() {
        return nowAddress;
    }

    public void setNowAddress(String nowAddress) {
        this.nowAddress = nowAddress;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContacts1() {
        return contacts1;
    }

    public void setContacts1(String contacts1) {
        this.contacts1 = contacts1;
    }

    public String getContacts2() {
        return contacts2;
    }

    public void setContacts2(String contacts2) {
        this.contacts2 = contacts2;
    }

    public String getContacts3() {
        return contacts3;
    }

    public void setContacts3(String contacts3) {
        this.contacts3 = contacts3;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getTel3() {
        return tel3;
    }

    public void setTel3(String tel3) {
        this.tel3 = tel3;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getImgStr() {
        return imgStr;
    }

    public void setImgStr(String imgStr) {
        this.imgStr = imgStr;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
