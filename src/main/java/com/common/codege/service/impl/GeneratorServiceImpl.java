package com.common.codege.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
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
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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
 * @date 2020/11/1911:56
 */
@Service
@AllArgsConstructor
public class GeneratorServiceImpl implements GeneratorService {
    private final GeneratorMapper generatorMapper;
    private final GenDatasourceConfService genDatasourceConfService;
    private static String path = GeneratorServiceImpl.class.getClass().getResource("/flt").getPath();

    @Override
    public IPage<List<Map<String, Object>>> page(Page page, String tableName, String dsName) {
        GenDatasourceConf genDatasourceConf = genDatasourceConfService.lambdaQuery().in(GenDatasourceConf::getName, dsName).one();
        String datasourceUrl = genDatasourceConf.getUrl();
        //todo 切换数据源

        return generatorMapper.list(page, tableName);
    }

    @Override
    public byte[] code(GeneratorConf generatorConf) {
        //解析生成root
        ParseName root = getRoot(generatorConf);

        //获取Configuration
        Configuration cfg = null;
        try {
            cfg = getConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //生成文件
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        try {
            genFile(new File(path), root, cfg, zip);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        IoUtil.close(zip);
        return outputStream.toByteArray();
    }

    private void genFile(File file, ParseName root, Configuration cfg, ZipOutputStream zip) throws IOException, TemplateException {
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
            path = path.replace("serviceimpl", "service.impl");
            //获取模板
            Template temp = cfg.getTemplate(fileName);
            //生成文件
            StringWriter sw = new StringWriter();
            temp.process(root, sw);

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
    private ParseName getRoot(GeneratorConf generatorConf) {
        Properties properties = new Properties();
        InputStream in = GeneratorServiceImpl.class.getClassLoader().getResourceAsStream("generator.properties");
        properties.load(in);

        ParseName root = new ParseName();
        root.setAuthor(Objects.isNull(generatorConf.getAuthor()) ? properties.getProperty("author") : generatorConf.getAuthor());
        root.setPackageName(Objects.isNull(generatorConf.getPackageName()) ? properties.getProperty("packageName") : generatorConf.getPackageName());
        root.setDatetime(DateUtil.now());

        //解析表
        List<Map<String, Object>> tables = generatorMapper.getTableByTableName(generatorConf.getTableName());

        //解析列
        List<Map<String, Object>> columns = generatorMapper.getColumnByTableName(generatorConf.getTableName());

        root.setTableName(String.valueOf(tables.get(0).get("tableName")));
        root.setTableComment(String.valueOf(tables.get(0).get("tableComment")));
        root.setClassName(toV(String.valueOf(tables.get(0).get("tableName")), false));
        root.setUrl(root.getClassName().toLowerCase());
        root.setClassVarName(toV(String.valueOf(tables.get(0).get("tableName")), true));
        List<Cols> cols = new ArrayList<>();

        columns.forEach(col -> {
            Cols cols1 = new Cols();
            //驼峰命名小写
            cols1.setColName(toV(String.valueOf(col.get("colName")), true));

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
        Matcher m = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(tableName);
        while (m.find()) {
            m.appendReplacement(stringbf, m.group(1).toUpperCase() + m.group(2).toLowerCase());
        }
        String s = stringbf.toString().replaceAll("_", "");
        if (flag) {
            s = s.substring(0, 1).toLowerCase().concat(s.substring(1));
        }
        return s;
    }


    /*        Template temp = cfg.getTemplate("Entity.java.ftl");
        Template temp = cfg.getTemplate("Service.java.ftl");
        Template temp = cfg.getTemplate("Controller.java.ftl");
        Template temp = cfg.getTemplate("ServiceImpl.java.ftl");*/

    //合并数据
/*        String path = this.getClass().getResource("/flt").getPath();///D:/mywork/codegen/target/classes/flt
        String path2 = this.getClass().getResource("/").getPath();///D:/mywork/codegen/target/classes/
        String path3 = this.getClass().getResource(".").getPath();///D:/mywork/codegen/target/classes/com/common/codege/service/impl/
        String path4 = this.getClass().getResource("").getPath();///D:/mywork/codegen/target/classes/com/common/codege/service/impl/
        String iipath = new File("/entity.java").getCanonicalPath();//D:\entity.java
        String iipath2 = new File("entity.java").getAbsolutePath();//D:\mywork\codegen\entity.java
        String iipath3 =new File("./entity.java").getPath();//.\entity.java*/
}
