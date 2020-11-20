package com.common.codege.entity;

import lombok.Data;

import java.util.List;

/**
 * @author wangchao
 * @description: TODO
 * @date 2020/11/1916:17
 */
@Data
public class ParseName {
    private String tableComment;
    private String author;
    private String packageName;
    private String tableName;
    private String className;
    private String classVarName;
    private List<Cols> cols;
    private String datetime;
    private String url;

}

