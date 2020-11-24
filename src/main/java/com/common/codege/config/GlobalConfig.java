package com.common.codege.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wangchao
 * @description: TODO
 * @date 2020/11/24 10:25
 */

@Component
@Data
@ConfigurationProperties(prefix = "config")
public class GlobalConfig {
    private String username;
    private String password;
    private String driverClassName;
    private String jdbcUrl;
}
