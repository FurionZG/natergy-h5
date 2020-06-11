package com.natergy.natergyh5.entity.statisticsEntity;

/****
 * 销售退货
 */
public class ReturnBackOrderDetail {


    private String id;
    private String returnStatus;
    private String systemNo;
    private String productStandard;
    private String innerPack;
    private String outerPack;
    private String netWeight;
    private String price;
    private String manager;
    private String province;

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getSystemNo() {
        return systemNo;
    }

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo;
    }

    public String getProductStandard() {
        return productStandard;
    }

    public void setProductStandard(String productStandard) {
        this.productStandard = productStandard;
    }

    public String getInnerPack() {
        return innerPack;
    }

    public void setInnerPack(String innerPack) {
        this.innerPack = innerPack;
    }

    public String getOuterPack() {
        return outerPack;
    }

    public void setOuterPack(String outerPack) {
        this.outerPack = outerPack;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ReturnBackOrderDetail{" +
                "id='" + id + '\'' +
                ", returnStatus='" + returnStatus + '\'' +
                ", systemNo='" + systemNo + '\'' +
                ", productStandard='" + productStandard + '\'' +
                ", innerPack='" + innerPack + '\'' +
                ", outerPack='" + outerPack + '\'' +
                ", netWeight='" + netWeight + '\'' +
                ", price='" + price + '\'' +
                ", manager='" + manager + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
