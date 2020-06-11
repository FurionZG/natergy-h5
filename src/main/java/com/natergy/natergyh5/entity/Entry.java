package com.natergy.natergyh5.entity;

/**
 * @Author: 杨枕戈
 * @Date: 2019/12/5 16:48
 */
public class Entry {
    private String id;
    private String status;
    private String offerId;
    private String size;
    private String wrapper;
    private String price;


    @Override
    public String toString() {
        return "Entry{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", offerId='" + offerId + '\'' +
                ", size='" + size + '\'' +
                ", wrapper='" + wrapper + '\'' +
                ", price='" + price + '\'' +
                '}';
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

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWrapper() {
        return wrapper;
    }

    public void setWrapper(String wrapper) {
        this.wrapper = wrapper;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
