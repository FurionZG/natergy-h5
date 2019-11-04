package com.natergy.natergyh5.entity;

import java.util.List;

public class Working {
    private String id;
    private String status;
    private String date;
    private String todayWorking;
    private String tomorrowWorking;
    private String marks;
    private String options;
    private String user;
    private String reader;
    private String approved;
    private List<String> images;

    @Override
    public String toString() {
        return "Working{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", todayWorking='" + todayWorking + '\'' +
                ", tomorrowWorking='" + tomorrowWorking + '\'' +
                ", marks='" + marks + '\'' +
                ", options='" + options + '\'' +
                ", user='" + user + '\'' +
                ", reader='" + reader + '\'' +
                ", approved='" + approved + '\'' +
                ", images=" + images +
                '}';
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
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

    public String getTodayWorking() {
        return todayWorking;
    }

    public void setTodayWorking(String todayWorking) {
        this.todayWorking = todayWorking;
    }

    public String getTomorrowWorking() {
        return tomorrowWorking;
    }

    public void setTomorrowWorking(String tomorrowWorking) {
        this.tomorrowWorking = tomorrowWorking;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }
}
