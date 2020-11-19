package com.common.codege.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangchao
 * @description: TODO
 * @date 2020/11/1918:34
 */
public class Demo {
    public static void main(String[] args) {
        String tableName = "user_name";
        StringBuffer stringbf = new StringBuffer();
        Matcher m = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(tableName);
        while (m.find()) {
            System.out.println(m.toString());
            m.appendReplacement(stringbf, m.group(1).toUpperCase() + m.group(2).toLowerCase());
        }
        System.out.println(m.appendTail(stringbf).toString());
    }
}
