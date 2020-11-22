package com.common.codege;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.common.codege.datasource.DynamicDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

@SpringBootApplication
@Configuration
public class App
{
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }


    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setPassword("root");
        dataSource.setUsername("root");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false");

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("defaultDataSource",dataSource);
        dynamicDataSource.setTargetDataSources(objectObjectHashMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSource);

        return dynamicDataSource;
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}


