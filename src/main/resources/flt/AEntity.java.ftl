

package flt;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.baomidou.mybatisplus.annotation.IdType;

/**
 * ${tableComment}
 *
 * @author ${author}
 * @date 2020-11-19 11:17:34
 */
@Data
@TableName("${tableName}")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "${tableComment}")
public class ${className} extends Model<${className}> {
private static final long serialVersionUID = 1L;

  <#list cols as col>

    /**
     * ${col.colComment}
     */
    <#if col.keyStatus == "PRI" >
    @TableId <#if col.keyType == "auto_increment" >(type = IdType.AUTO) </#if>
    </#if>
    @ApiModelProperty(value="${col.colComment}")
    private String ${col.colName};
  </#list>


}
