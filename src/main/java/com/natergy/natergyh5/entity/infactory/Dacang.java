package com.natergy.natergyh5.entity.infactory;/**
 * 
 * @Author 杨枕戈
 * @Date 2020-04-01 16:09
 * @Version 1.0
 * 
 */
public class Dacang {
    private String id;
    private String status;
    private String no;
    private String operateTime;

    @Override
    public String toString() {
        return "Dacang{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", no='" + no + '\'' +
                ", operateTime='" + operateTime + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
