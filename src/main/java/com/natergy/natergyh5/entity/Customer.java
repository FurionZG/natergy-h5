package com.natergy.natergyh5.entity;

public class Customer {
    private String customerName ;
    private String user;
    private String province;
    private String city;
    private String type;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", user='" + user + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                '}';
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
