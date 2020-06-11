package com.natergy.natergyh5.entity.infactory;/**
 * 
 * @Author 杨枕戈
 * @Date 2020-02-24 10:22
 * @Version 1.0
 * 
 */
public class Cook {

    private String id;
    private String status;
    private String user;
    private String department;
    private String date;
    private String breakfast;
    private String lunch;
    private String supper;
    private String nightSnack;
    private String team;
    private String remarks;
    private String lMantou;
    private String sMantou;
    private String nMantou;
    private String toCook;
    private String q;


    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    @Override
    public String toString() {
        return "Cook{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", user='" + user + '\'' +
                ", department='" + department + '\'' +
                ", date='" + date + '\'' +
                ", breakfast='" + breakfast + '\'' +
                ", lunch='" + lunch + '\'' +
                ", supper='" + supper + '\'' +
                ", nightSnack='" + nightSnack + '\'' +
                ", team='" + team + '\'' +
                ", remarks='" + remarks + '\'' +
                ", lMantou='" + lMantou + '\'' +
                ", sMantou='" + sMantou + '\'' +
                ", nMantou='" + nMantou + '\'' +
                ", toCook='" + toCook + '\'' +
                '}';
    }

    public String getToCook() {
        return toCook;
    }

    public void setToCook(String toCook) {
        this.toCook = toCook;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getSupper() {
        return supper;
    }

    public void setSupper(String supper) {
        this.supper = supper;
    }

    public String getNightSnack() {
        return nightSnack;
    }

    public void setNightSnack(String nightSnack) {
        this.nightSnack = nightSnack;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getlMantou() {
        return lMantou;
    }

    public void setlMantou(String lMantou) {
        this.lMantou = lMantou;
    }

    public String getsMantou() {
        return sMantou;
    }

    public void setsMantou(String sMantou) {
        this.sMantou = sMantou;
    }

    public String getnMantou() {
        return nMantou;
    }

    public void setnMantou(String nMantou) {
        this.nMantou = nMantou;
    }
}
