package ${packageName}.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

import ${packageName}.entity.${className};
import ${packageName}.service.${className}Service;

import com.yokea.common.jwt.util.JWTUtil;
import com.yokea.common.base.wrapper.BaseResponseWrapMapper;
import com.yokea.common.base.wrapper.BaseResponseWrapper;
/**
 * ${tableComment}
 *
 * @author ${author}
 * @date ${datetime}
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/${url}" )
@Api(value = "${url}", tags = "${tableComment}管理")
public class ${className}Controller {

    private final ${className}Service ${classVarName}Service;

    /**
     * 分页查询
     * @param page 分页对象
     * @param ${classVarName} ${tableComment}
     * @return BaseResponseWrapper
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public BaseResponseWrapper getGenDatasourceConfPage(@RequestHeader(required = true, name = "token") String token,Page page, ${className} ${classVarName}) {
       // Long userId = JWTUtil.getUserId(token);
        return BaseResponseWrapMapper.ok(${classVarName}Service.page(page, Wrappers.query(${classVarName})));
    }


    /**
     * 通过id查询${tableComment}
     * @param id id
     * @return BaseResponseWrapper
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public BaseResponseWrapper getById(@PathVariable("id" ) Integer id) {
        return BaseResponseWrapMapper.ok(${classVarName}Service.getById(id));
    }

    /**
     * 新增${tableComment}
     * @param ${classVarName} ${tableComment}
     * @return BaseResponseWrapper
     */
    @ApiOperation(value = "新增${tableComment}", notes = "新增${tableComment}")
    @PostMapping
    public BaseResponseWrapper save(@RequestBody ${className} ${classVarName}) {
      //  ${classVarName}.setCreateTime(LocalDateTime.now());
        return BaseResponseWrapMapper.ok(${classVarName}Service.save(${classVarName}));
    }

    /**
     * 修改${tableComment}
     * @param ${classVarName} ${tableComment}
     * @return BaseResponseWrapper
     */
    @ApiOperation(value = "修改${tableComment}", notes = "修改${tableComment}")
    @PutMapping
    public BaseResponseWrapper updateById(@RequestBody ${className} ${classVarName}) {
    //    ${classVarName}.setUpdateTime(LocalDateTime.now());
        return BaseResponseWrapMapper.ok(${classVarName}Service.updateById(${classVarName}));
    }

    /**
     * 通过id删除${tableComment}
     * @param id id
     * @return BaseResponseWrapper
     */
    @ApiOperation(value = "通过id删除${tableComment}", notes = "通过id删除${tableComment}")
    @DeleteMapping("/{id}" )
    public BaseResponseWrapper removeById(@PathVariable Integer id) {
        return BaseResponseWrapMapper.ok(${classVarName}Service.removeById(id));
    }

}
