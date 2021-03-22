package com.cn.dao;


import com.cn.pojo.ZgGoods;

public interface ZgGoodsMapper {
    int deleteByPrimaryKey(String goodcode);

    int insert(ZgGoods record);

    int insertSelective(ZgGoods record);

    ZgGoods selectByPrimaryKey(String goodcode);

    int updateByPrimaryKeySelective(ZgGoods record);

    int updateByPrimaryKey(ZgGoods record);
}