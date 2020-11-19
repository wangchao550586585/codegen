
package com.common.codege.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.codege.entity.GenDatasourceConf;
import com.common.codege.service.GenDatasourceConfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 数据源表
 *
 * @author wangchao
 * @date 2020-11-19 11:17:34
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/gendatasourceconf" )
@Api(value = "gendatasourceconf", tags = "数据源表管理")
public class GenDatasourceConfController {

    private final  GenDatasourceConfService genDatasourceConfService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param genDatasourceConf 数据源表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getGenDatasourceConfPage(Page page, GenDatasourceConf genDatasourceConf) {
        return R.ok(genDatasourceConfService.page(page, Wrappers.query(genDatasourceConf)));
    }


    /**
     * 通过id查询数据源表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(genDatasourceConfService.getById(id));
    }

    /**
     * 新增数据源表
     * @param genDatasourceConf 数据源表
     * @return R
     */
    @ApiOperation(value = "新增数据源表", notes = "新增数据源表")
    @PostMapping
    public R save(@RequestBody GenDatasourceConf genDatasourceConf) {
        return R.ok(genDatasourceConfService.save(genDatasourceConf));
    }

    /**
     * 修改数据源表
     * @param genDatasourceConf 数据源表
     * @return R
     */
    @ApiOperation(value = "修改数据源表", notes = "修改数据源表")
    @PutMapping
    public R updateById(@RequestBody GenDatasourceConf genDatasourceConf) {
        return R.ok(genDatasourceConfService.updateById(genDatasourceConf));
    }

    /**
     * 通过id删除数据源表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除数据源表", notes = "通过id删除数据源表")
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(genDatasourceConfService.removeById(id));
    }

}
