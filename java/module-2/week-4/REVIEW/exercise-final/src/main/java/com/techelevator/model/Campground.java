package com.techelevator.model;

import java.math.BigDecimal;

public class Campground {

    private int campgroundId;
    private int parkId;
    private String name;
    private int openFromMonth;
    private int openToMonth;
    private BigDecimal dailyFee;

    public int getCampgroundId() {
        return campgroundId;
    }

    public void setCampgroundId(int campgroundId) {
        this.campgroundId = campgroundId;
    }

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOpenFromMonth() {
        return openFromMonth;
    }

    public void setOpenFromMonth(int openFromMonth) {
        this.openFromMonth = openFromMonth;
    }

    public int getOpenToMonth() {
        return openToMonth;
    }

    public void setOpenToMonth(int openToMonth) {
        this.openToMonth = openToMonth;
    }

    public BigDecimal getDailyFee() {
        return dailyFee;
    }

    public void setDailyFee(BigDecimal dailyFee) {
        this.dailyFee = dailyFee;
    }

    @Override
    public String toString() {
        return "Campground{" +
                "campgroundId=" + campgroundId +
                ", parkId=" + parkId +
                ", name='" + name + '\'' +
                ", openFromMonth=" + openFromMonth +
                ", openToMonth=" + openToMonth +
                ", dailyFee=" + dailyFee +
                '}';
    }
}
