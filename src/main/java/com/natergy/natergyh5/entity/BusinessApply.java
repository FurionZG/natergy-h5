package com.natergy.natergyh5.entity;/**
 * 
 * @Author 杨枕戈
 * @Date 2020-03-28 9:33
 * @Version 1.0
 * 
 */
public class BusinessApply {

    private String Id;

    private String status;

    private String user;

    private String date;

    private String no;

    private String pBusinessStartTime;

    private String pBusinessEndTime;

    private String pDays;

    private String pCosts;

    private String pPos;

    private String pWay;

    private String businessNo;

    private String plan;

    private String directorOpinion;

    private String presidentOpinion;

    @Override
    public String toString() {
        return "businessApply{" +
                "Id='" + Id + '\'' +
                ", status='" + status + '\'' +
                ", user='" + user + '\'' +
                ", date='" + date + '\'' +
                ", no='" + no + '\'' +
                ", pBusinessStartTime='" + pBusinessStartTime + '\'' +
                ", pBusinessEndTime='" + pBusinessEndTime + '\'' +
                ", pDays='" + pDays + '\'' +
                ", pCosts='" + pCosts + '\'' +
                ", pPos='" + pPos + '\'' +
                ", pWay='" + pWay + '\'' +
                ", businessNo='" + businessNo + '\'' +
                ", plan='" + plan + '\'' +
                ", directorOpinion='" + directorOpinion + '\'' +
                ", presidentOpinion='" + presidentOpinion + '\'' +
                '}';
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getpBusinessStartTime() {
        return pBusinessStartTime;
    }

    public void setpBusinessStartTime(String pBusinessStartTime) {
        this.pBusinessStartTime = pBusinessStartTime;
    }

    public String getpBusinessEndTime() {
        return pBusinessEndTime;
    }

    public void setpBusinessEndTime(String pBusinessEndTime) {
        this.pBusinessEndTime = pBusinessEndTime;
    }

    public String getpDays() {
        return pDays;
    }

    public void setpDays(String pDays) {
        this.pDays = pDays;
    }

    public String getpCosts() {
        return pCosts;
    }

    public void setpCosts(String pCosts) {
        this.pCosts = pCosts;
    }

    public String getpPos() {
        return pPos;
    }

    public void setpPos(String pPos) {
        this.pPos = pPos;
    }

    public String getpWay() {
        return pWay;
    }

    public void setpWay(String pWay) {
        this.pWay = pWay;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getDirectorOpinion() {
        return directorOpinion;
    }

    public void setDirectorOpinion(String directorOpinion) {
        this.directorOpinion = directorOpinion;
    }

    public String getPresidentOpinion() {
        return presidentOpinion;
    }

    public void setPresidentOpinion(String presidentOpinion) {
        this.presidentOpinion = presidentOpinion;
    }
}
