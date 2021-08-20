package com.cn.enums;

public enum HouseStatusEnum {
    SALE("售","1");

    private String desc;
    private String value;

    HouseStatusEnum(String desc, String value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    HouseStatusEnum() {
    }
}
