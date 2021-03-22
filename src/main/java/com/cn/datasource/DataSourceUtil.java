package com.cn.datasource;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Classname DataSourceUtil
 * @Description TODO
 * @Author Jack
 * Date 2021/2/26 16:52
 * Version 1.0
 */
public class DataSourceUtil {
    public static DataSource getDs1(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(PropertiesReader.get("mybatis.druid.driver-class-name"));
        druidDataSource.setUrl(PropertiesReader.get("mybatis.druid.url"));
        druidDataSource.setUsername(PropertiesReader.get("mybatis.druid.username"));
        druidDataSource.setPassword(PropertiesReader.get("mybatis.druid.password"));
        druidDataSource.setMaxActive(PropertiesReader.getInteger("mybatis.druid.maxActive"));
        druidDataSource.setInitialSize(PropertiesReader.getInteger("mybatis.druid.initialSize"));
        druidDataSource.setMinEvictableIdleTimeMillis(PropertiesReader.getInteger("mybatis.druid.minEvictableIdleTimeMillis"));
        druidDataSource.setValidationQuery(PropertiesReader.get("mybatis.druid.validationQuery"));
        druidDataSource.setTestWhileIdle(PropertiesReader.getBoolean("mybatis.druid.testWhileIdle"));
        druidDataSource.setTestOnBorrow(PropertiesReader.getBoolean("mybatis.druid.testOnBorrow"));
        druidDataSource.setTestOnReturn(PropertiesReader.getBoolean("mybatis.druid.testOnReturn"));
        druidDataSource.setPoolPreparedStatements(PropertiesReader.getBoolean("mybatis.druid.poolPreparedStatements"));
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(PropertiesReader.getInteger("mybatis.druid.maxPoolPreparedStatementPerConnectionSize"));

        try {
            druidDataSource.setFilters(PropertiesReader.get("mybatis.druid.filters"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return druidDataSource;
    }
}
