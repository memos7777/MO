package com.mialab.healthbutler.manager.domain.system;

public class SystemCity {
    private Integer cityId;

    private String cityName;

    private String cityStationId;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityStationId() {
        return cityStationId;
    }

    public void setCityStationId(String cityStationId) {
        this.cityStationId = cityStationId;
    }

}