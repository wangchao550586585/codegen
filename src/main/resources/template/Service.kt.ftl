package ${packageName}.service

import com.fasterxml.jackson.databind.ObjectMapper
import ${packageName}.param.${className}Param
import ${packageName}.entity.${className}
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
* ${tableComment}
*
* @author ${author}
* @date ${datetime}
*/
@Service
class ${className}Service {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var objectMapper: ObjectMapper


    fun findById(id: Long): ${className}? {
        return ${className}.byId(id)
    }


    fun save(param: ${className}Param): Boolean {
        val ${classVarName} = create${className}(param)
        ${className}.db().save(${classVarName})
        // logger.info("新增${tableComment}数据", objectMapper.writeValueAsString(param))
        return true
    }
    fun update(param: ${className}Param): Boolean? {
        ${className}.db().update(create${className}(param))
        // logger.info("修改${tableComment}数据", objectMapper.writeValueAsString(param))-->
        return true
    }


    fun delete(id: Long): Boolean {
        ${className}.deleteById(id)
        // logger.info("删除${tableComment}数据contentId:{}", id)
        return true
    }

    private fun create${className}(param: ${className}Param): ${className} {
/*      return ${className}().apply {
            this.id = param.id
            this.contentId = param.contentId
            this.contentTitle = param.contentTitle
            this.num = param.num
            this.snapId = param.snapId
            this.startTime = param.startTime
            this.endTime = param.endTime
        }*/
        return ${className}()
    }
}