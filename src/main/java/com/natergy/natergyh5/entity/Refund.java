package com.natergy.natergyh5.entity;

import java.util.List;

/**
 * 
 * @Author 杨枕戈
 * @Date 2020-05-26 13:59
 * @Version 1.0
 * 
 */
public class Refund {
    private String id;
    private String status;
    private String systemRefundNo;
    private String saleRefundNo;
    private String refundTime;
    private String user;
    private String refundAddress;
    private String refundContacts;
    private String remarks1;
    private String customerName;
    private String customerNo;
    private String carriage;
    private List<RefundDetail> refundDetails;
    private String toDepot;

    @Override
    public String toString() {
        return "Refund{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", systemRefundNo='" + systemRefundNo + '\'' +
                ", saleRefundNo='" + saleRefundNo + '\'' +
                ", refundTime='" + refundTime + '\'' +
                ", user='" + user + '\'' +
                ", refundAddress='" + refundAddress + '\'' +
                ", refundContacts='" + refundContacts + '\'' +
                ", remarks1='" + remarks1 + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerNo='" + customerNo + '\'' +
                ", carriage='" + carriage + '\'' +
                ", refundDetails=" + refundDetails +
                ", toDepot='" + toDepot + '\'' +
                '}';
    }

    public String getToDepot() {
        return toDepot;
    }

    public void setToDepot(String toDepot) {
        this.toDepot = toDepot;
    }

    public String getCarriage() {
        return carriage;
    }

    public void setCarriage(String carriage) {
        this.carriage = carriage;
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

    public String getSystemRefundNo() {
        return systemRefundNo;
    }

    public void setSystemRefundNo(String systemRefundNo) {
        this.systemRefundNo = systemRefundNo;
    }

    public String getSaleRefundNo() {
        return saleRefundNo;
    }

    public void setSaleRefundNo(String saleRefundNo) {
        this.saleRefundNo = saleRefundNo;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRefundAddress() {
        return refundAddress;
    }

    public void setRefundAddress(String refundAddress) {
        this.refundAddress = refundAddress;
    }

    public String getRefundContacts() {
        return refundContacts;
    }

    public void setRefundContacts(String refundContacts) {
        this.refundContacts = refundContacts;
    }

    public String getRemarks1() {
        return remarks1;
    }

    public void setRemarks1(String remarks1) {
        this.remarks1 = remarks1;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public List<RefundDetail> getRefundDetails() {
        return refundDetails;
    }

    public void setRefundDetails(List<RefundDetail> refundDetails) {
        this.refundDetails = refundDetails;
    }
}
