package com.common.codege.datasource;

import lombok.Data;

/**
 * 数据源bean构建器
 */
@Data
public class DataSourceBean {

    private final String beanName;
    private final String driverClassName = "com.mysql.cj.jdbc.Driver";
    private final String jdbcUrl;
    private final String username;
    private final String password;

    public DataSourceBean(String beanName, String jdbcUrl,
                          String username, String password) {
        this.beanName = beanName;
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }
}

