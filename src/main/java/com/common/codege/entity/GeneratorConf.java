package com.common.codege.entity;

import lombok.Data;

/**
 * @author wangchao
 * @description: TODO
 * @date 2020/11/1914:35
 */
@Data
public class GeneratorConf {
    private String dsName;
    private String tableName;

    /**
     *    生成信息
     */
    private String author;

}
