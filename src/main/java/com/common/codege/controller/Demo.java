package com.common.codege.controller;

import com.common.codege.service.impl.GeneratorServiceImpl;

import java.io.File;
import java.io.IOException;

/**
 * @author wangchao
 * @description: TODO
 * @date 2020/11/1918:34
 */
public class Demo {
    private static String path = GeneratorServiceImpl.class.getClass().getResource("/flt").getPath();

    public static void main(String[] args) throws IOException {
        String s="Mapper.java.ftl";
        String[] split = s.split("\\.");
        File file = new File(path);
        fun(file);
    }

    private static void fun(File file) throws IOException {
/*        System.out.println("1------------------------"+file.getAbsolutePath());
        System.out.println("3------------------------"+file.getPath());*/
        //遍历path目录下所有文件
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                fun(files[i]);
            }
        } else {
            System.out.println(file.getName());
        }
    }


}
