package com.natergy.natergyh5.entity;

/**
 * @Author: 杨枕戈
 * @Date: 2019/11/27 13:23
 */
public class Apply {
    private String id;
    private String status;
    private String date;
    private String applyNo;
    private String type;
    private String customerNo;
    private String customerName;
    private String user;
    private String content;
    private String refundAmount;
    private String refundAccount;
    private String adjustmentAmount;
    private String balance;
    private String costs;
    private String originalPrice;
    private String presentPrice;
    private String givingType;
    private String conversionPrice;
    private String approvalComments;

    @Override
    public String toString() {
        return "Apply{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", applyNo='" + applyNo + '\'' +
                ", type='" + type + '\'' +
                ", customerNo='" + customerNo + '\'' +
                ", customerName='" + customerName + '\'' +
                ", user='" + user + '\'' +
                ", content='" + content + '\'' +
                ", refundAmount='" + refundAmount + '\'' +
                ", refundAccount='" + refundAccount + '\'' +
                ", adjustmentAmount='" + adjustmentAmount + '\'' +
                ", balance='" + balance + '\'' +
                ", costs='" + costs + '\'' +
                ", originalPrice='" + originalPrice + '\'' +
                ", presentPrice='" + presentPrice + '\'' +
                ", givingType='" + givingType + '\'' +
                ", conversionPrice='" + conversionPrice + '\'' +
                ", approvalComments='" + approvalComments + '\'' +
                '}';
    }

    public String getApprovalComments() {
        return approvalComments;
    }

    public void setApprovalComments(String approvalComments) {
        this.approvalComments = approvalComments;
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

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }

    public String getAdjustmentAmount() {
        return adjustmentAmount;
    }

    public void setAdjustmentAmount(String adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCosts() {
        return costs;
    }

    public void setCosts(String costs) {
        this.costs = costs;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPresentPrice() {
        return presentPrice;
    }

    public void setPresentPrice(String presentPrice) {
        this.presentPrice = presentPrice;
    }

    public String getGivingType() {
        return givingType;
    }

    public void setGivingType(String givingType) {
        this.givingType = givingType;
    }

    public String getConversionPrice() {
        return conversionPrice;
    }

    public void setConversionPrice(String conversionPrice) {
        this.conversionPrice = conversionPrice;
    }
}
