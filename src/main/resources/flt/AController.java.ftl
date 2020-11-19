
package flt;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.codege.test.ftl.generator.entity.${className};
import com.common.codege.test.ftl.generator.service.${className}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * ${tableComment}
 *
 * @author ${author}
 * @date 2020-11-19 11:17:34
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/gendatasourceconf" )
@Api(value = "gendatasourceconf", tags = "${tableComment}管理")
public class AController {

    private final ${className}Service ${classVarName}Service;

    /**
     * 分页查询
     * @param page 分页对象
     * @param ${classVarName} ${tableComment}
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getGenDatasourceConfPage(Page page, ${className} ${classVarName}) {
        return R.ok(${classVarName}Service.page(page, Wrappers.query(${classVarName})));
    }


    /**
     * 通过id查询${tableComment}
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(${classVarName}Service.getById(id));
    }

    /**
     * 新增${tableComment}
     * @param ${classVarName} ${tableComment}
     * @return R
     */
    @ApiOperation(value = "新增${tableComment}", notes = "新增${tableComment}")
    @PostMapping
    public R save(@RequestBody ${className} ${classVarName}) {
        return R.ok(${classVarName}Service.save(${classVarName}));
    }

    /**
     * 修改${tableComment}
     * @param ${classVarName} ${tableComment}
     * @return R
     */
    @ApiOperation(value = "修改${tableComment}", notes = "修改${tableComment}")
    @PutMapping
    public R updateById(@RequestBody ${className} ${classVarName}) {
        return R.ok(${classVarName}Service.updateById(${classVarName}));
    }

    /**
     * 通过id删除${tableComment}
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除${tableComment}", notes = "通过id删除${tableComment}")
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(${classVarName}Service.removeById(id));
    }

}
