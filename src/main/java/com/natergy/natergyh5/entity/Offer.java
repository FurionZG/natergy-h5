package com.natergy.natergyh5.entity;

import java.util.List;

/**
 * @Author: 杨枕戈
 * @Date: 2019/12/5 16:54
 */
public class Offer {
    private String id;
    private String status;
    private String user;
    private String companyName;
    private List<Entry> entryList;
    private String date;

    @Override
    public String toString() {
        return "Offer{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", user='" + user + '\'' +
                ", companyName='" + companyName + '\'' +
                ", entryList=" + entryList +
                ", date='" + date + '\'' +
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
