package com.cn.pojo;

public class ZgGoods {
    private String goodcode;

    private String goodname;

    private Integer count;

    public String getGoodcode() {
        return goodcode;
    }

    public void setGoodcode(String goodcode) {
        this.goodcode = goodcode;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", goodcode=").append(goodcode);
        sb.append(", goodname=").append(goodname);
        sb.append(", count=").append(count);
        sb.append("]");
        return sb.toString();
    }
}