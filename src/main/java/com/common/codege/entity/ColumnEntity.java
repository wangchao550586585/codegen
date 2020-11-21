package com.common.codege.entity;

import lombok.Data;

/**
 * @author wangchao
 * @description: TODO
 * @date 2020/11/1916:23
 */
@Data
public class ColumnEntity {
    String colComment;
    String fieldName;
    String colName;
    String dataType;
    String keyStatus;
    String keyType;
}
