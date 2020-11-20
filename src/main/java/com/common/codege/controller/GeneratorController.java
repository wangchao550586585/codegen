package com.common.codege.controller;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.codege.entity.GeneratorConf;
import com.common.codege.service.GeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangchao
 * @description: 生成代码
 * @date 2020/11/1911:55
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/generator")
@Api(value = "generator", tags = "生成代码")
public class GeneratorController {
    private final GeneratorService generatorService;

    /**
     * @param page
     * @param tableName 表名
     * @param dsName    数据源名
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public R getGenDatasourceConfPage(Page page, String tableName, @RequestParam(defaultValue = "test")String dsName) {
        return R.ok(generatorService.page(page, tableName, dsName));
    }

    @ApiOperation(value = "生成代码", notes = "生成代码")
    @PostMapping("/code")
    public void code(@RequestBody GeneratorConf generatorConf, HttpServletResponse response) throws IOException {

/*
        response.setHeader("Content-Encoding", "gzip");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                String.format("attachment; filename=%s.zip", generatorConf.getTableName()));

        */
        byte[] data =  generatorService.code(generatorConf);
        response.reset();
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                String.format("attachment; filename=%s.zip", generatorConf.getTableName()));
        response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(data.length));
        response.setContentType("application/octet-stream; charset=UTF-8");

        IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);

    }


}
