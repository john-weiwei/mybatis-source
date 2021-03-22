package com.cn.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("Area")
public class ConsultConfigArea implements Serializable {

    private static final long serialVersionUID = 1369616264151612932L;
    public String areaCode;

    public String areaName;

    public String state;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
