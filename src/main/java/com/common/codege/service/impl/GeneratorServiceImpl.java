package com.common.codege.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.codege.entity.Cols;
import com.common.codege.entity.GenDatasourceConf;
import com.common.codege.entity.GeneratorConf;
import com.common.codege.entity.ParseName;
import com.common.codege.mapper.GeneratorMapper;
import com.common.codege.service.GenDatasourceConfService;
import com.common.codege.service.GeneratorService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author wangchao
 * @description: TODO
 * @date 2020/11/1911:56
 */
@Service
@AllArgsConstructor
public class GeneratorServiceImpl implements GeneratorService {
    private final GeneratorMapper generatorMapper;
    private final GenDatasourceConfService genDatasourceConfService;

    @Override
    public IPage<List<Map<String, Object>>> page(Page page, String tableName, String dsName) {
        GenDatasourceConf genDatasourceConf = genDatasourceConfService.lambdaQuery().in(GenDatasourceConf::getName, dsName).one();
        String datasourceUrl = genDatasourceConf.getUrl();
        //todo 切换数据源

        return generatorMapper.list(page, tableName);
    }

    @SneakyThrows
    @Override
    public byte[] code(GeneratorConf generatorConf) {
        Properties properties = new Properties();
        InputStream in = GeneratorServiceImpl.class.getClassLoader().getResourceAsStream("generator.properties");
        properties.load(in);

        //解析表
        List<Map<String, Object>> tables = generatorMapper.getTableByTableName(generatorConf.getTableName());

        //解析列
        List<Map<String, Object>> columns = generatorMapper.getColumnByTableName(generatorConf.getTableName());

        //解析生成root
        ParseName root = getRoot(generatorConf, tables, columns);

     /*   ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);*/

        //获取Configuration
        Configuration cfg = getConfiguration();

        //获取模板
        Template temp = cfg.getTemplate("AEntity.java.ftl");

        //合并数据
/*        String path = this.getClass().getResource("/flt").getPath();///D:/mywork/codegen/target/classes/flt
        String path2 = this.getClass().getResource("/").getPath();///D:/mywork/codegen/target/classes/
        String path3 = this.getClass().getResource(".").getPath();///D:/mywork/codegen/target/classes/com/common/codege/service/impl/
        String path4 = this.getClass().getResource("").getPath();///D:/mywork/codegen/target/classes/com/common/codege/service/impl/
        String iipath = new File("/entity.java").getCanonicalPath();//D:\entity.java
        String iipath2 = new File("entity.java").getAbsolutePath();//D:\mywork\codegen\entity.java
        String iipath3 =new File("./entity.java").getPath();//.\entity.java*/


        temp.process(root, new FileWriter(new File("D:/mywork/新建文件夹/roseWood/yokea-provider/yokea-pro/yokea-pro-crm/src/main/java/com/yokea/pro/crm/beanConfig/orderentity.java").getCanonicalFile()));

        //返回zip流
//        IoUtil.close(zip);
        return null;
    }

    private Configuration getConfiguration() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        String path = this.getClass().getResource("/flt").getPath();
        cfg.setDirectoryForTemplateLoading(new File(path));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return cfg;
    }

    private ParseName getRoot(GeneratorConf generatorConf, List<Map<String, Object>> tables, List<Map<String, Object>> columns) {
        ParseName root = new ParseName();
        root.setAuthor(generatorConf.getAuthor());
        root.setTableName(String.valueOf(tables.get(0).get("tableName")));
        root.setTableComment(String.valueOf(tables.get(0).get("tableComment")));
        // TODO: 2020/11/19  驼峰命名大写

        root.setClassName(String.valueOf(tables.get(0).get("tableName")));
        // TODO: 2020/11/19  驼峰命名小写
        root.setClassVarName(String.valueOf(tables.get(0).get("tableName")));

        List<Cols> cols = new ArrayList<>();
        columns.forEach(col -> {
            Cols cols1 = new Cols();

            // TODO: 2020/11/19  驼峰命名小写
            cols1.setColName(String.valueOf(col.get("colName")));
            cols1.setDataType(String.valueOf(col.get("dataType")));
            cols1.setKeyType(String.valueOf(col.get("keyType")));
            cols1.setColComment(String.valueOf(col.get("colComment")));
            cols1.setKeyStatus(String.valueOf(col.get("keyStatus")));
            cols.add(cols1);
        });
        root.setCols(cols);
        return root;
    }
}
