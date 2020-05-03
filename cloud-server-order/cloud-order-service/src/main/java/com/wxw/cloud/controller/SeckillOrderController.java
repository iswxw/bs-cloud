package com.wxw.cloud.controller;


import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WXW
 * @since 2020-04-27
 */
@Controller
@Api(tags = "SeckillOrderController",description = "秒杀平台微服务")
@RequestMapping("/seckill-order")
public class SeckillOrderController {

}

