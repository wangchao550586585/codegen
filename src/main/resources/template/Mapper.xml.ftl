<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName}.mapper.${className}Mapper">
    <resultMap id="${classVarName}Map" type="${packageName}.entity.${className}">
        <#list cols as col>
            <#if col.keyStatus == "PRI" >
                <id property="${col.fieldName}" column="${col.colName}"/>
            <#else>
                <result property="${col.fieldName}" column="${col.colName}"/>
            </#if>
        </#list>
</resultMap>



<sql id="sql">
    <#list cols as col>
        <#if col_index != 0>
            ,
        </#if>
        ${col.colName} as ${col.fieldName}
    </#list>
</sql>

<sql id="sql2">
    <#list cols as col>
        <#if col_index != 0>
            ,
        </#if>
        ${col.colName}
    </#list>
</sql>

</mapper>
