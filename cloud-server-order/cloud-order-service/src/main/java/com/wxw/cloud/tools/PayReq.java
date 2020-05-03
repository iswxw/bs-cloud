package com.wxw.cloud.tools;

import lombok.Data;

/**
 * 支付请求入参
 * @Author: wxw
 * @create: 2020-05-03-10:25
 */
@Data
public class PayReq {
  
    /**
     * 商品的标题/交易标题/订单标题/订单关键字等
     */
    private String subject;

    /**
     * 商户网站唯一订单号
     */
    private String out_trade_no;

    // 总金额
    private String total_amount;
    
    //用户付款中途退出返回商户网站的地址
    private String quit_url;

    //销售产品码，商家和支付宝签约的产品码
    private String product_code;

    //商品的编号
    private String goods_id;

    // 商品名称
    private String goods_name;

    //商品数量
    private String quantity;

    // 商品单价，单位为元
    private String price;

    // 商品描述
    private String body;
    
    // 最晚付款时间
    private String timeout_express;
}
