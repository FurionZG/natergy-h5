package com.natergy.natergyh5.entity;/**
 * 
 * @Author 杨枕戈
 * @Date 2020-05-29 8:28
 * @Version 1.0
 * 
 */
public class RefundDetail {

    private String id;
    private String status;
    private String systemRefundNo;
    private String productNo;
    private String size;
    private String innerWrapper;
    private String outWrapper;
    private String price;
    private String totalPrice;
    private String totalWeight;
    private String count;
    private String tax;
    private String remark;
    private String realIncome;

    @Override
    public String toString() {
        return "RefundDetail{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", systemRefundNo='" + systemRefundNo + '\'' +
                ", productNo='" + productNo + '\'' +
                ", size='" + size + '\'' +
                ", innerWrapper='" + innerWrapper + '\'' +
                ", outWrapper='" + outWrapper + '\'' +
                ", price='" + price + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", totalWeight='" + totalWeight + '\'' +
                ", count='" + count + '\'' +
                ", tax='" + tax + '\'' +
                ", remark='" + remark + '\'' +
                ", realIncome='" + realIncome + '\'' +
                '}';
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getInnerWrapper() {
        return innerWrapper;
    }

    public void setInnerWrapper(String innerWrapper) {
        this.innerWrapper = innerWrapper;
    }

    public String getOutWrapper() {
        return outWrapper;
    }

    public void setOutWrapper(String outWrapper) {
        this.outWrapper = outWrapper;
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

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRealIncome() {
        return realIncome;
    }

    public void setRealIncome(String realIncome) {
        this.realIncome = realIncome;
    }
}
