package com.sxit.dreamiya.entity.http.request;

public class TestEntity {
    private String cityCode;

    public TestEntity() {

    }

    public TestEntity(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

}
