package ${packageName}.controller

import cn.ilanxin.common.type.v2.Result
import cn.ilanxin.common.type.v2.ok
import com.fasterxml.jackson.databind.ObjectMapper
import com.tenclass.common.annotation.NoAuth
import com.tenclass.common.annotation.Simple
import ${packageName}.service.${className}Service
import ${packageName}.param.${className}Param
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
* ${tableComment}
*
* @author ${author}
* @date ${datetime}
*/
@Simple
@Api(tags = ["${className}Controller"], description = "${tableComment}管理")
@RestController()
class ${className}Controller {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var ${classVarName}Service : ${className}Service

    /**
    * 新增${tableComment}
    * @param ${classVarName} ${tableComment}
    * @return Result<Boolean>
    */
    @NoAuth
    @ApiOperation(value = "新增${tableComment}", notes = "新增${tableComment}")
    @PostMapping("/api/v1/${classVarName}/save")
    fun save(@Valid @RequestBody param: ${className}Param): Result<Boolean> {
        logger.info("/api/v1/demo/save; param [{}]", objectMapper.writeValueAsString(param))
        return ok(${classVarName}Service.save(param))
    }

    /**
    * 修改${tableComment}
    * @param ${classVarName} ${tableComment}
    * @return Result<Boolean>
    */
    @NoAuth
    @ApiOperation(value = "修改${tableComment}", notes = "修改${tableComment}")
    @PutMapping("/api/v1/${classVarName}/update")
    fun update(@Valid @RequestBody param: ${className}Param): Result<Boolean> {
        logger.info("/api/v1/demo/save; param [{}]", objectMapper.writeValueAsString(param))
        return ok(${classVarName}Service.update(param))
    }

    /**
    * 通过id删除${tableComment}
    * @param id id
    * @return Result<Boolean>
    */
    @NoAuth
    @ApiOperation(value = "通过id删除${tableComment}", notes = "通过id删除${tableComment}")
    @DeleteMapping("/api/v1/${classVarName}/{id}")
    fun del(@PathVariable("id") id: Long): Result<Boolean> {
        logger.info("/api/v1/demo/del; param [{}]", objectMapper.writeValueAsString(id))
        return ok(${classVarName}Service.delete(id))
    }

}