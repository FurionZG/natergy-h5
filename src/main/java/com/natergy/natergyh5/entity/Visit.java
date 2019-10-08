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
    private String contacts_1;
    private String contacts_2;
    private String contacts_3;
    private String tel_1;
    private String tel_2;
    private String tel_3;
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
                ", contacts_1='" + contacts_1 + '\'' +
                ", contacts_2='" + contacts_2 + '\'' +
                ", contacts_3='" + contacts_3 + '\'' +
                ", tel_1='" + tel_1 + '\'' +
                ", tel_2='" + tel_2 + '\'' +
                ", tel_3='" + tel_3 + '\'' +
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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



    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getContacts_1() {
        return contacts_1;
    }

    public void setContacts_1(String contacts_1) {
        this.contacts_1 = contacts_1;
    }

    public String getContacts_2() {
        return contacts_2;
    }

    public void setContacts_2(String contacts_2) {
        this.contacts_2 = contacts_2;
    }

    public String getContacts_3() {
        return contacts_3;
    }

    public void setContacts_3(String contacts_3) {
        this.contacts_3 = contacts_3;
    }

    public String getTel_1() {
        return tel_1;
    }

    public void setTel_1(String tel_1) {
        this.tel_1 = tel_1;
    }

    public String getTel_2() {
        return tel_2;
    }

    public void setTel_2(String tel_2) {
        this.tel_2 = tel_2;
    }

    public String getTel_3() {
        return tel_3;
    }

    public void setTel_3(String tel_3) {
        this.tel_3 = tel_3;
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
}
