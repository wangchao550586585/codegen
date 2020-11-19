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
    String tableComment;
    String author;
    String tableName;
    String className;
    String classVarName;
    List<Cols> cols;

}

