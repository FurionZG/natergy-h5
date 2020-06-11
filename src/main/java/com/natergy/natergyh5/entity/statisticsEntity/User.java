package com.natergy.natergyh5.entity.statisticsEntity;

public class User {
    private Integer id;
    private String name;
    private String department;
    private String jobposition;//职位
    private String classify;//类别

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    private String sex;
    private String education;
    private String sfzh;
    private String mobilephone;
    private String familyPlace;
    private String joinDate;//入职日期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobposition() {
        return jobposition;
    }

    public void setJobposition(String jobposition) {
        this.jobposition = jobposition;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getFamilyPlace() {
        return familyPlace;
    }

    public void setFamilyPlace(String familyPlace) {
        this.familyPlace = familyPlace;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", jobposition='" + jobposition + '\'' +
                ", classify='" + classify + '\'' +
                ", sex='" + sex + '\'' +
                ", education='" + education + '\'' +
                ", sfzh='" + sfzh + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", familyPlace='" + familyPlace + '\'' +
                ", joinDate='" + joinDate + '\'' +
                '}';
    }
}