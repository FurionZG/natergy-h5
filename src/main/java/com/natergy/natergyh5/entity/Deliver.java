package com.natergy.natergyh5.entity;/**
 * 
 * @Author 杨枕戈
 * @Date 2020-06-10 8:55
 * @Version 1.0
 * 
 */
public class Deliver {
    private String size;
    private Integer weight;
    private String status;


    @Override
    public String toString() {
        return "Deliver{" +
                "size='" + size + '\'' +
                ", weight=" + weight +
                ", status='" + status + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
