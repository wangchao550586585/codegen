<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.common.codege.mapper.GeneratorMapper">
    <select id="list" resultType="map">
        SELECT
        table_name tableName,
        ENGINE,
        table_comment tableComment,
        table_collation tableCollation,
        create_time createTime
        FROM
        information_schema.TABLES
        WHERE
        table_schema = (
        SELECT DATABASE
        ())
        <if test="tableName != null and tableName.trim() != ''">
            AND table_name LIKE concat 	('%',#{tableName}, '%')
        </if>
        ORDER BY
        create_time DESC
    </select>
</mapper>
