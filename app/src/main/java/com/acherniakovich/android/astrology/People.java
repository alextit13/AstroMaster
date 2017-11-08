package com.acherniakovich.android.astrology;

import java.io.Serializable;

public class People implements Serializable{
    private String name;
    private String dateOfBirdth;
    private String country;
    private String city;
    private String differentTime;
    private String sex;

    public People(String name, String dateOfBirdth, String country, String city, String differentTime, String sex) {
        this.name = name;
        this.dateOfBirdth = dateOfBirdth;
        this.country = country;
        this.city = city;
        this.differentTime = differentTime;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirdth() {
        return dateOfBirdth;
    }

    public void setDateOfBirdth(String dateOfBirdth) {
        this.dateOfBirdth = dateOfBirdth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDifferentTime() {
        return differentTime;
    }

    public void setDifferentTime(String differentTime) {
        this.differentTime = differentTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
