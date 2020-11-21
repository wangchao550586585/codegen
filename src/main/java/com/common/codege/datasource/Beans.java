package com.common.codege.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author WangChao
 * @create 2020/11/21 8:01
 */
@Configuration
public class Beans {
    @Bean
    public HikariDataSource masterDataSource() {
        HikariDataSource bean = new HikariDataSource();
        bean.setJdbcUrl("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false");
        bean.setUsername("root");
        bean.setPassword("root");
        bean.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return bean;
    }

    @Bean
    public DynamicDataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource dataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> objectHashMap = new HashMap<>();
        objectHashMap.put("master", dataSource);
        dynamicDataSource.setTargetDataSources(objectHashMap);
        dynamicDataSource.setDefaultTargetDataSource("master");
        return dynamicDataSource;
    }
}
