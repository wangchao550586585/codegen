package ${packageName}.param

import io.swagger.annotations.ApiModelProperty
import java.time.Instant
import javax.validation.constraints.NotNull

/**
* ${tableComment}
*
* @author ${author}
* @date ${datetime}
*/
class ${className}Param {
<#list cols as col>
    /**
    * ${col.colComment}
    */
    @ApiModelProperty(value="${col.colComment}")
    var ${col.fieldName}:  ${col.dataType} ? = null
</#list>

}