package com.common.codege.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.codege.datasource.DataSourceBean;
import com.common.codege.datasource.DynamicDataSource;
import com.common.codege.entity.ColumnEntity;
import com.common.codege.entity.GenDatasourceConf;
import com.common.codege.entity.GeneratorConf;
import com.common.codege.entity.TableEntity;
import com.common.codege.mapper.GeneratorMapper;
import com.common.codege.service.GenDatasourceConfService;
import com.common.codege.service.GeneratorService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author wangchao
 * @description: TODO
 * @date 2020/11/19 11:56
 */
@Service
@AllArgsConstructor
@Slf4j
public class GeneratorServiceImpl implements GeneratorService {
    private final GeneratorMapper generatorMapper;
    private final GenDatasourceConfService genDatasourceConfService;
    private static String path = GeneratorServiceImpl.class.getClass().getResource("/ftl").getPath();
    private static final Pattern pattern = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE);
    @Override
    public IPage<List<Map<String, Object>>> page(Page page, String tableName, String dsName) {
        GenDatasourceConf genDatasourceConf = genDatasourceConfService.lambdaQuery().in(GenDatasourceConf::getName, dsName).one();

        DataSourceBean dataSourceBean = new DataSourceBean(genDatasourceConf.getName(), genDatasourceConf.getUrl(), genDatasourceConf.getUsername(), genDatasourceConf.getPassword());
        DynamicDataSource.DataSourceContext.setDataSource(dataSourceBean);

        IPage<List<Map<String, Object>>> list = generatorMapper.list(page, tableName);

        DynamicDataSource.DataSourceContext.toDefault();
        return list;
    }

    @SneakyThrows
    @Override
    public byte[] code(GeneratorConf generatorConf) {
        //解析生成root
        TableEntity root = getRoot(generatorConf);

        //获取Configuration
        Configuration cfg = getConfiguration();

        //生成文件
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        genFile(new File(path), root, cfg, zip);
//        byte[] bytes = outputStream.toByteArray();
//        IoUtil.close(outputStream);

        IoUtil.close(zip);
        return outputStream.toByteArray();
    }

    private void genFile(File file, TableEntity root, Configuration cfg, ZipOutputStream zip) throws IOException, TemplateException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                genFile(files[i], root, cfg, zip);
            }
        } else {
            String fileName = file.getName();
            String s = fileName.split("\\.")[0];
            String genFileName = fileName.equals("Entity.java.ftl") ? ".java" : fileName.replaceAll(".ftl", "");

            String path = root.getPackageName().concat("." + s.toLowerCase());
            path = path.replace(".", File.separator).concat(File.separator + root.getClassName() + genFileName);
            path = path.replace("serviceimpl", "service" + File.separator + "impl");
            //获取模板
            Template temp = cfg.getTemplate(fileName);
            //生成文件
            StringWriter sw = new StringWriter();
            temp.process(root, sw);

            temp.process(root, new FileWriter(new File("D:/mywork/新建文件夹/roseWood/yokea-provider/yokea-pro/yokea-pro-crm/src/main/java/com/yokea/pro/crm/feign/" + genFileName).getCanonicalFile()));

            zip.putNextEntry(new ZipEntry(Objects.requireNonNull(path)));
            IoUtil.write(zip, StandardCharsets.UTF_8, false, sw.toString());
            IoUtil.close(sw);
            zip.closeEntry();

        }
    }

    private Configuration getConfiguration() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(new File(path));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return cfg;
    }

    @SneakyThrows
    private TableEntity getRoot(GeneratorConf generatorConf) {
        Properties properties = new Properties();
        InputStream in = GeneratorServiceImpl.class.getClassLoader().getResourceAsStream("generator.properties");
        properties.load(in);

        TableEntity root = new TableEntity();
        root.setAuthor(Objects.isNull(generatorConf.getAuthor()) ? properties.getProperty("author") : generatorConf.getAuthor());
        root.setPackageName(Objects.isNull(generatorConf.getPackageName()) ? properties.getProperty("packageName") : generatorConf.getPackageName());
        root.setDatetime(DateUtil.now());

        //切换数据源
        String dsName = generatorConf.getDsName();
        GenDatasourceConf genDatasourceConf = genDatasourceConfService.lambdaQuery().in(GenDatasourceConf::getName, dsName).one();
        DataSourceBean dataSourceBean = new DataSourceBean(genDatasourceConf.getName(), genDatasourceConf.getUrl(), genDatasourceConf.getUsername(), genDatasourceConf.getPassword());
        DynamicDataSource.DataSourceContext.setDataSource(dataSourceBean);


        //解析表
        List<Map<String, Object>> tables = generatorMapper.getTableByTableName(generatorConf.getTableName());

        //解析列
        List<Map<String, Object>> columns = generatorMapper.getColumnByTableName(generatorConf.getTableName());

        //恢复默认数据源
        DynamicDataSource.DataSourceContext.toDefault();

        root.setTableName(String.valueOf(tables.get(0).get("tableName")));
        root.setTableComment(String.valueOf(tables.get(0).get("tableComment")));
        root.setClassName(toV(String.valueOf(tables.get(0).get("tableName")), false));
        root.setUrl(root.getClassName().toLowerCase());
        root.setClassVarName(toV(String.valueOf(tables.get(0).get("tableName")), true));
        List<ColumnEntity> cols = new ArrayList<>();

        columns.forEach(col -> {
            ColumnEntity cols1 = new ColumnEntity();
            //驼峰命名小写
            cols1.setFieldName(toV(String.valueOf(col.get("colName")), true));
            cols1.setColName(String.valueOf(col.get("colName")));

            cols1.setDataType(properties.getProperty(String.valueOf(col.get("dataType"))));

            cols1.setKeyType(String.valueOf(col.get("keyType")));
            cols1.setColComment(String.valueOf(col.get("colComment")));
            cols1.setKeyStatus(String.valueOf(col.get("keyStatus")));
            cols.add(cols1);
        });
        root.setCols(cols);
        return root;
    }

    private static String toV(String tableName, boolean flag) {
        StringBuffer stringbf = new StringBuffer();
        Matcher m = pattern.matcher(tableName);
        while (m.find()) {
            m.appendReplacement(stringbf, m.group(1).toUpperCase() + m.group(2).toLowerCase());
        }
        String s = stringbf.toString().replaceAll("_", "");
        if (flag) {
            s = s.substring(0, 1).toLowerCase().concat(s.substring(1));
        }
        return s;
    }

}
