package com.common.codege.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.codege.entity.GeneratorConf;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author wangchao
 * @description: TODO
 * @date 2020/11/1911:56
 */
public interface GeneratorService {
    IPage<List<Map<String, Object>>> page(Page page,  String tableName, String dsName);

    byte[] code(GeneratorConf generatorConf);
}
