package com.common.codege;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.common.codege.config.GlobalConfig;
import com.common.codege.datasource.DynamicDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

@SpringBootApplication
@Configuration
public class App {
    @Autowired
    GlobalConfig globalConfig;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(globalConfig.getUsername());
        dataSource.setPassword(globalConfig.getPassword());
        dataSource.setDriverClassName(globalConfig.getDriverClassName());
        dataSource.setJdbcUrl(globalConfig.getJdbcUrl());

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("defaultDataSource", dataSource);
        dynamicDataSource.setTargetDataSources(objectObjectHashMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSource);

        return dynamicDataSource;
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}


