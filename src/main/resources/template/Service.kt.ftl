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

    /**
     * 同步新增直播数据
     */
    fun save(param: ${className}Param): Boolean {
        val demo = create${className}(param)
        Demo.db().save(demo)
        logger.info("同步新增直播数据", objectMapper.writeValueAsString(demo))
        return true
    }
    fun update(param: ${className}Param): Boolean? {
        Demo.db().update(create${className}(param))
        logger.info("同步修改直播数据", objectMapper.writeValueAsString(param))
        return true
    }

    /**
     * 同步删除or取消数据
     */
    fun delete(id: Long): Boolean {
        Demo.deleteById(id)
        logger.info("同步删除直播数据contentId:{}", id)
        return true
    }

    private fun create${className}(param: ${className}Param): ${className} {
        return ${className}().apply {
            this.id = param.id
            this.contentId = param.contentId
            this.contentTitle = param.contentTitle
            this.num = param.num
            this.snapId = param.snapId
            this.startTime = param.startTime
            this.endTime = param.endTime
        }
    }
}