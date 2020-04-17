package com.wxw.cloud.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: wxw
 * @create: 2020-04-18-0:16
 */
@Slf4j
@Component
public class MsgListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "WXW.SMS.QUEUE", durable = "true"),
            exchange = @Exchange(value = "WXW.SMS.EXCHANGE",
                    ignoreDeclarationExceptions = "true"),
            key = {"verifycode.mq"}))
    public void listenSms(Map<String, String> msg) throws Exception {
        if (msg == null || msg.size() <= 0) {
            // 放弃处理
            return;
        }
        // 消费消息
        String code = msg.get("code");
        if (StringUtils.isBlank(code)) {
            // 放弃处理
            return;
        }
        // 发送消息 给用户
        log.info("消费MQ消息：{}", code);
    }
}
