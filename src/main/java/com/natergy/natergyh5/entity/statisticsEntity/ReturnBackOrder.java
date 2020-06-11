package com.natergy.natergyh5.entity.statisticsEntity;

/****
 * 退货订单表
 *    returnStatus 退货状态
 *     manager 业务经理
 *    systemNo 系统订单
 *    returnDate 退货日期
 *    customerCode 客户编码
 *    province 省份
 */
public class ReturnBackOrder {

    private String returnStatus;
    private String manager;
    private String systemNo;
    private String returnDate;
    private String customerCode;
    private String province;

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getSystemNo() {
        return systemNo;
    }

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "ReturnBackOrder{" +
                "returnStatus='" + returnStatus + '\'' +
                ", manager='" + manager + '\'' +
                ", systemNo='" + systemNo + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
