package com.natergy.natergyh5.entity;

public class Business {
    private String Id;
    private String status;
    private String uname;
    private String businessNo;
    private String startDate;
    private String endDate;
    private String time;
    private String visitCustomerCount;
    private String visitNewCustomerCount;
    private String startMileage;
    private String startImage;
    private String endMileage;
    private String endImage;
    private String Mileage;
    private String roadToll;
    private String fuelCosts;
    private String fuelVolume;
    private String oilConsumption;
    private String ticket;
    private String specialSubsidies;
    private String specialSubsidiesDescription;
    private String mealAllowance;
    private String accommodation;
    private String totalCosts;
    private String customerFee;
    private String trip;
    private String summary;
    private String proposal;

    @Override
    public String toString() {
        return "Business{" +
                "Id='" + Id + '\'' +
                ", status='" + status + '\'' +
                ", uname='" + uname + '\'' +
                ", businessNo='" + businessNo + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", time='" + time + '\'' +
                ", visitCustomerCount='" + visitCustomerCount + '\'' +
                ", visitNewCustomerCount='" + visitNewCustomerCount + '\'' +
                ", startMileage='" + startMileage + '\'' +
                ", startImage='" + startImage + '\'' +
                ", endMileage='" + endMileage + '\'' +
                ", endImage='" + endImage + '\'' +
                ", Mileage='" + Mileage + '\'' +
                ", roadToll='" + roadToll + '\'' +
                ", fuelCosts='" + fuelCosts + '\'' +
                ", fuelVolume='" + fuelVolume + '\'' +
                ", oilConsumption='" + oilConsumption + '\'' +
                ", ticket='" + ticket + '\'' +
                ", specialSubsidies='" + specialSubsidies + '\'' +
                ", specialSubsidiesDescription='" + specialSubsidiesDescription + '\'' +
                ", mealAllowance='" + mealAllowance + '\'' +
                ", accommodation='" + accommodation + '\'' +
                ", totalCosts='" + totalCosts + '\'' +
                ", customerFee='" + customerFee + '\'' +
                ", trip='" + trip + '\'' +
                ", summary='" + summary + '\'' +
                ", proposal='" + proposal + '\'' +
                '}';
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVisitCustomerCount() {
        return visitCustomerCount;
    }

    public void setVisitCustomerCount(String visitCustomerCount) {
        this.visitCustomerCount = visitCustomerCount;
    }

    public String getVisitNewCustomerCount() {
        return visitNewCustomerCount;
    }

    public void setVisitNewCustomerCount(String visitNewCustomerCount) {
        this.visitNewCustomerCount = visitNewCustomerCount;
    }

    public String getStartMileage() {
        return startMileage;
    }

    public void setStartMileage(String startMileage) {
        this.startMileage = startMileage;
    }

    public String getStartImage() {
        return startImage;
    }

    public void setStartImage(String startImage) {
        this.startImage = startImage;
    }

    public String getEndMileage() {
        return endMileage;
    }

    public void setEndMileage(String endMileage) {
        this.endMileage = endMileage;
    }

    public String getEndImage() {
        return endImage;
    }

    public void setEndImage(String endImage) {
        this.endImage = endImage;
    }

    public String getMileage() {
        return Mileage;
    }

    public void setMileage(String mileage) {
        Mileage = mileage;
    }

    public String getRoadToll() {
        return roadToll;
    }

    public void setRoadToll(String roadToll) {
        this.roadToll = roadToll;
    }

    public String getFuelCosts() {
        return fuelCosts;
    }

    public void setFuelCosts(String fuelCosts) {
        this.fuelCosts = fuelCosts;
    }

    public String getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(String fuelVolume) {
        this.fuelVolume = fuelVolume;
    }

    public String getOilConsumption() {
        return oilConsumption;
    }

    public void setOilConsumption(String oilConsumption) {
        this.oilConsumption = oilConsumption;
    }

    public String getSpecialSubsidies() {
        return specialSubsidies;
    }

    public void setSpecialSubsidies(String specialSubsidies) {
        this.specialSubsidies = specialSubsidies;
    }

    public String getSpecialSubsidiesDescription() {
        return specialSubsidiesDescription;
    }

    public void setSpecialSubsidiesDescription(String specialSubsidiesDescription) {
        this.specialSubsidiesDescription = specialSubsidiesDescription;
    }

    public String getMealAllowance() {
        return mealAllowance;
    }

    public void setMealAllowance(String mealAllowance) {
        this.mealAllowance = mealAllowance;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public String getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(String totalCosts) {
        this.totalCosts = totalCosts;
    }

    public String getCustomerFee() {
        return customerFee;
    }

    public void setCustomerFee(String customerFee) {
        this.customerFee = customerFee;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getProposal() {
        return proposal;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }
}
