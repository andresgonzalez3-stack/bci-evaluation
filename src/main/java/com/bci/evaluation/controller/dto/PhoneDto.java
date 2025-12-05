package com.bci.evaluation.controller.dto;

public class PhoneDto {

    private long number;
    private int citycode;
    private String countrycode;

    public PhoneDto(long number, int citycode, String countrycode) {
        this.number = number;
        this.citycode = citycode;
        this.countrycode = countrycode;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getCitycode() {
        return citycode;
    }

    public void setCitycode(int citycode) {
        this.citycode = citycode;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }
}
