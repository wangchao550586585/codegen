
package com.common.codege.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 数据源表
 *
 * @author wangchao
 * @date 2020-11-19 11:17:34
 */
@Mapper
public interface GeneratorMapper {

    IPage<List<Map<String, Object>>> list(Page page, @Param("tableName") String tableName);

    @Select({"<script>", " SELECT " +
            " table_name tableName, " +
            " table_comment tableComment  " +
            " FROM " +
            " information_schema.TABLES  " +
            " WHERE " +
            " table_schema = ( " +
            " SELECT DATABASE " +
            " ())  " +
            " AND table_name = #{tableName} " +
            " ORDER BY " +
            " create_time DESC ",
            "</script>"})
    @ResultType(Map.class)
    List<Map<String, Object>> getTableByTableName(String tableName);


    @Select({"<script>", "SELECT " +
            " COLUMN_NAME colName, " +
            " DATA_TYPE dataType, " +
            " COLUMN_KEY keyStatus, " +
            " EXTRA keyType, " +
            " column_comment colComment  " +
            " FROM " +
            " information_schema.COLUMNS  " +
            " WHERE " +
            " table_schema = ( " +
            " SELECT DATABASE " +
            " ())  " +
            " AND table_name = #{tableName} " +
            " ORDER BY " +
            " ORDINAL_POSITION ASC ",
            "</script>"})
    @ResultType(Map.class)
    List<Map<String, Object>> getColumnByTableName(String tableName);
}
