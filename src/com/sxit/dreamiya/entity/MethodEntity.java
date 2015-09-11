package com.sxit.dreamiya.entity;

public class MethodEntity {

    private int methodCode;
    private String methodUrl;

    public MethodEntity() {

    }

    public MethodEntity(int methodCode, String methodUrl) {
        this.methodCode = methodCode;
        this.methodUrl = methodUrl;
    }

    public int getMethodCode() {
        return methodCode;
    }

    public void setMethodCode(int methodCode) {
        this.methodCode = methodCode;
    }

    public String getMethodUrl() {
        return methodUrl;
    }

    public void setMethodUrl(String methodUrl) {
        this.methodUrl = methodUrl;
    }

}
