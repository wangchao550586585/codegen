
package com.common.codege.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 数据源表
 *
 * @author wangchao
 * @date 2020-11-19 11:17:34
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping("/index")
    public String index() {
        return "/index";
    }
}
