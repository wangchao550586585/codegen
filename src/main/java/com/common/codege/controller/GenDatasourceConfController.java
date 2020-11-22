
package com.common.codege.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.codege.entity.GenDatasourceConf;
import com.common.codege.service.GenDatasourceConfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 数据源表
 *
 * @author wangchao
 * @date 2020-11-19 11:17:34
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/gendatasourceconf" )
@Api(value = "gendatasourceconf", tags = "数据源表管理")
public class GenDatasourceConfController {

    private final  GenDatasourceConfService genDatasourceConfService;


    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/index" )
    public String index() {
        return "/gendatasourceconf/index";
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param genDatasourceConf 数据源表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @ResponseBody
    public ResponseEntity getGenDatasourceConfPage(Page page, GenDatasourceConf genDatasourceConf) {
        Page page1 = genDatasourceConfService.page(page,Wrappers.lambdaQuery(genDatasourceConf).orderByAsc(GenDatasourceConf::getId) );
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", page1.getTotal());
        result.put("rows", page1.getRecords());
        return  ResponseEntity.ok(result);
    }



    /**
     * 通过id查询数据源表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @ResponseBody
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(genDatasourceConfService.getById(id));
    }


    @GetMapping("/save")
    public String save() {
        return "/gendatasourceconf/gendatasourceconf";
    }

    /**
     * 新增数据源表
     * @param genDatasourceConf 数据源表
     * @return R
     */
    @ApiOperation(value = "新增数据源表", notes = "新增数据源表")
    @PostMapping
    @ResponseBody
    public R save(@RequestBody GenDatasourceConf genDatasourceConf) {
        return R.ok(genDatasourceConfService.save(genDatasourceConf));
    }
    /**
     * 修改设备页面
     *
     * @param modelMap
     * @return
     */
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.put("gendatasourceconf", genDatasourceConfService.getById(id));
        return "/gendatasourceconf/gendatasourceconf";
    }

    /**
     * 修改数据源表
     * @param genDatasourceConf 数据源表
     * @return R
     */
    @ApiOperation(value = "修改数据源表", notes = "修改数据源表")
    @PutMapping
    @ResponseBody
    public R updateById(@RequestBody GenDatasourceConf genDatasourceConf) {
        return R.ok(genDatasourceConfService.updateById(genDatasourceConf));
    }

    /**
     * 通过id删除数据源表
     * @param ids ids
     * @return R
     */
    @ApiOperation(value = "通过id删除数据源表", notes = "通过id删除数据源表")
    @DeleteMapping("/{ids}" )
    @ResponseBody
    public R removeById(@PathVariable String ids) {
        String[] idList = ids.split("_");
        return R.ok(genDatasourceConfService.removeByIds(Arrays.asList(idList)));
    }

}
