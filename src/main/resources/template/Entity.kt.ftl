package ${packageName}.entity

import io.ebean.Finder
import io.ebean.annotation.WhenCreated
import io.ebean.annotation.WhenModified
import io.swagger.annotations.ApiModelProperty
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
* ${tableComment}
*
* @author ${author}
* @date ${datetime}
*/
@Entity
@Table(name = "${tableName}")
class ${className} {

<#list cols as col>
    /**
    * ${col.colComment}
    */
    <#if col.keyStatus == "PRI" >
        @Id
    </#if>
   @ApiModelProperty(value="${col.colComment}")
   var ${col.fieldName}:  ${col.dataType} ? = null
</#list>


companion object Find : Finder<Long, ${className}>(${className}::class.java, "bardDatabase")
}
