package com.cn.dao;


import com.cn.pojo.TUser1;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUser1Mapper {
    int deleteByPrimaryKey(Long userId);

    int insert(TUser1 record);

    int insertSelective(TUser1 record);

    TUser1 selectByPrimaryKey(Long userId);

    List<TUser1> selectByUserName(@Param("username") String username);

    int updateByPrimaryKeySelective(TUser1 record);

    int updateByPrimaryKey(TUser1 record);
}