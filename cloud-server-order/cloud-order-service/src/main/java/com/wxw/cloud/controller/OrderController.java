package com.wxw.cloud.controller;


import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxw.cloud.domain.Order;
import com.wxw.cloud.domain.OrderDetail;
import com.wxw.cloud.domain.OrderStatus;
import com.wxw.cloud.result.PageResult;
import com.wxw.cloud.result.Result;
import com.wxw.cloud.service.AlipayService;
import com.wxw.cloud.service.IOrderDetailService;
import com.wxw.cloud.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

/**
 *
 * @author WXW
 * @since 2020-04-27
 */
@Slf4j
@RestController
@Api(tags = "OrderController",description = "订单微服务")
public class OrderController {

    @Resource
    private IOrderService orderService;

    @Resource
    private IOrderDetailService orderDetailService;

    @Resource
    private AlipayService alipayService;

    @PostMapping
    @ApiOperation(value = "创建订单接口，返回订单编号",notes = "创建订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "order",required = true,value = "订单的json对象，包含订单条目和物流信息"),
            @ApiImplicitParam(name = "seck",required = true,value = "是否是秒杀订单")
    })
    public ResponseEntity<List<Long>> createOrder(@RequestParam("seck") String seck, @RequestBody @Valid Order order){
        // 查询库存
        List<Long> skuId = this.orderService.queryStock(seck,order);
        if (skuId.size() != 0){
            //库存不足
            return new ResponseEntity<>(skuId, HttpStatus.OK);
        }

        Long id = this.orderService.createOrder(seck,order);
        return new ResponseEntity<>(Arrays.asList(id), HttpStatus.CREATED);
    }

    @ApiOperation("根据订单编号查询订单")
    @GetMapping("{id}")
    public ResponseEntity<Order> queryOrderById(@PathVariable("id")Long id){
        Order order = this.orderService.queryOrderById(id);
        if (order == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(order);
    }

    @ApiOperation("分页查询当前用户订单")
    @GetMapping("list")
    public ResponseEntity<PageResult<Order>> queryUserOrderList(
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "status",required = false)Integer status
    ){

        PageResult<Order> result = this.orderService.queryUserOrderList(page,rows,status);
        if (result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @ApiOperation("更新订单状态")
    @PutMapping("{id}/{status}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "订单编号",type = "Long"),
            @ApiImplicitParam(name = "status",value = "订单状态：1未付款，" +
                    "2已付款未发货，" +
                    "3已发货未确认，" +
                    "4已确认未评价，" +
                    "5交易关闭，" +
                    "6交易成功，已评价",defaultValue = "1",type = "Integer")
    })
    public ResponseEntity<Boolean> updateOrderStatus(@PathVariable("id") Long id,@PathVariable("status") Integer status){
        Boolean result = this.orderService.updateOrderStatus(id,status);
        if (result == null){
            //返回400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //返回204
        return new ResponseEntity<>(result,HttpStatus.NO_CONTENT);
    }

    @ApiOperation("前往支付宝进行支付")
    @GetMapping( "goAlipay")
    public ResponseEntity<String> goAliPay(@RequestParam("orderId") String orderId){
            //1. 查询订单
            Order order = this.orderService.queryOrderById(Long.valueOf(orderId));
            //2. 查询订单详情
            QueryWrapper<OrderDetail> wrapper = new QueryWrapper<>();
            wrapper.eq("order_id", orderId);
            OrderDetail orderDetail = this.orderDetailService.getOne(wrapper);
            String result = this.alipayService.getAliPayClient(order, orderDetail);
            if (StringUtils.isBlank(result)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(result);
    }

    @ApiOperation("根据订单id查询其包含的skuId")
    @GetMapping("skuId/{id}")
    public ResponseEntity<List<Long>> querySkuIdByOrderId(@PathVariable("id") Long id){
        List<Long> longList = this.orderService.querySkuIdByOrderId(id);
        if (longList == null || longList.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(longList);
    }

    @ApiOperation("根据订单id查询订单状态")
    @GetMapping("status/{id}")
    public ResponseEntity<OrderStatus> queryOrderStatusById(@PathVariable("id") Long id){
        OrderStatus orderStatus = this.orderService.queryOrderStatusById(id);
        if (orderStatus == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(orderStatus);
    }

    @ApiOperation("交易查询")
    @PostMapping("tradeQuery")
    public Result<String> tradeQuery(@RequestParam("orderId") String orderId) {
        Result<String> result = new Result<>();
        try {
            String query = this.alipayService.query(orderId);
            return result.success(JSON.toJSONString(query),"交易查询成功");
        } catch (Exception e) {
            log.error("查询交易异常");
            return result.fail(e.getMessage());
        }
    }

    @ApiOperation("退款")
    @GetMapping("refund")
    public Result refund(
            @RequestParam("orderId")String orderId,
            @RequestParam("refundReason")String refundReason,
            @RequestParam(value = "reqNo", required = false)String reqNo,
            @RequestParam(value = "refundAmount", required = false)String refundAmount
    ){
        Result<String> result = new Result<>();
        try {
            log.info("退款入参4：{}",refundAmount);
            String refund = this.alipayService.refund(orderId, refundReason, refundAmount, reqNo);
            return result.success(JSON.toJSONString(refund),"退款成功");
        } catch (AlipayApiException e) {
            log.error("退款失败：{}", e);
            return result.fail(e.getMessage());
        }
    }

    @ApiOperation("退款查询")
    @PostMapping("refundQuery")
    public Result<String> refundQuery(
            @RequestParam("orderId")String orderId,
            @RequestParam(value = "reqNo", required = false)String reqNo
    ){
        Result<String> result = new Result<>();
        try {
            String refundQuery = this.alipayService.refundQuery(orderId, reqNo);
            return result.success(refundQuery,"退款查询成功");
        } catch (AlipayApiException e) {
            log.info("退款查询发生异常：{}", e);
            return result.fail("退款查询失败");
        }
    }

    @ApiOperation("交易关闭")
    @PostMapping("alipayClose")
    public Result<String>alipayClose(@RequestParam("orderId")String orderId){
        Result<String> result = new Result<>();
        try {
            String close = this.alipayService.close(orderId);
            return result.success(close,"交易关闭成功");
        } catch (AlipayApiException e) {
            log.info("交易关闭时出现异常",e);
            return result.fail("交易关闭失败");
        }
    }
    @ApiIgnore
    //@ApiOperation("支付宝同步通知页面")
    @RequestMapping("ReturnNotice")
    @ResponseBody
    public Result<String> alipayReturnNotice(HttpServletRequest request,HttpServletResponse response){
       log.info("支付成功，进入同步通知接口");
        Result<String> result = new Result<>();
        try {
            //获取支付宝GET过来反馈信息
            Map<String,String> params = new HashMap<String,String>();
            Map<String,String[]> requestParams = request.getParameterMap();
            Boolean returnUrl = this.alipayService.getReturnUrl(request, params, requestParams);
            if (!returnUrl){
                return result.fail("同步通知回调失败");
            }
            return result.success();
        } catch (Exception e) {
            log.error("异步回调发生异常：{}", e);
            return result.fail(e.getMessage());
        }
    }
    @ApiIgnore
    //@ApiOperation("支付宝异步 通知页面")
    @ResponseBody
    @RequestMapping("NotifyNotice")
    public Result<String> alipayNotify(HttpServletRequest request, HttpServletResponse response){
        log.info("支付成功, 进入异步通知接口...");
        Result<String> result = new Result<>();
        try {
            //获取支付宝POST过来反馈信息
            Map<String,String> params = new HashMap<String,String>();
            Map<String,String[]> requestParams = request.getParameterMap();
            Boolean notifyUrl = this.alipayService.getNotifyUrl(request, params, requestParams);
            if (notifyUrl){
                return result.success();
            }
            return result.fail("异步回调失败");
        } catch (Exception e) {
            log.error("异步回调发生异常：{}", e);
            return result.fail(e.getMessage());
        }
    }



}

