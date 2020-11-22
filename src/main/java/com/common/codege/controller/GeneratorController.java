package com.common.codege.controller;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.codege.entity.GeneratorConf;
import com.common.codege.service.GeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangchao
 * @description: 生成代码
 * @date 2020/11/1911:55
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/generator")
@Api(value = "generator", tags = "生成代码")
public class GeneratorController {
    private final GeneratorService generatorService;

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/index")
    public String index() {
        return "/generator/index";
    }

    /**
     * @param page
     * @param tableName 表名
     * @param dsName    数据源名
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    @ResponseBody
    public ResponseEntity getGenDatasourceConfPage(Page page, String tableName, @RequestParam(defaultValue = "test") String dsName) {
        IPage<List<Map<String, Object>>> page1 = generatorService.page(page, tableName, dsName);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", page1.getTotal());
        result.put("rows", page1.getRecords());
        result.put("dsName", dsName);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/codePage")
    public String codePage(String dsName, String tableName, ModelMap modelMap) {
        modelMap.put("dsName", dsName);
        modelMap.put("tableName", tableName);
        return "/generator/generator";
    }


    @ApiOperation(value = "生成代码", notes = "生成代码")
    @PostMapping("/code")
    public void code(@RequestBody GeneratorConf generatorConf, HttpServletResponse response) throws IOException {
        byte[] data = generatorService.code(generatorConf);
        response.reset();
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                String.format("attachment; filename=%s.zip", generatorConf.getTableName()));
        response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(data.length));
        response.setContentType("application/octet-stream; charset=UTF-8");
        IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);

    }


}
