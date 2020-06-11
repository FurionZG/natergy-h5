package com.natergy.natergyh5.entity;

public class ResultOfAddress {
    private String province;
    private String city;
    private String address;
    private String companyName;

    private String contacts1;
    private String contacts2;
    private String contacts3;
    private String tel1;
    private String tel2;
    private String tel3;

    public String getContacts1() {
        return contacts1;
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

    public void setContacts1(String contacts1) {
        this.contacts1 = contacts1;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "ResultOfAddress{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
