package com.wxw.cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: wxw
 * @create: 2020-04-16-15:45
 */
@Controller
public class GoodsController {

    @GetMapping("item/{id}.html")
    public String toItenPage(@PathVariable("id")Long id, Model model){

        return "item";
    }
}
