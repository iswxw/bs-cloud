package com.wxw.cloud.listener;

import com.wxw.cloud.service.SearchService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author: wxw
 * @create: 2020-04-16-23:24
 */
@Component
public class GoodsListener {

    @Resource
    private SearchService searchService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "WXW.SEARCH.SAVE.QUEUE",durable = "true"),
            exchange = @Exchange(value = "WXW.ITEM.EXCHANGE",ignoreDeclarationExceptions = "true",type = ExchangeTypes.TOPIC),
            key = {"item.insert","item.update"}
    ))
    public void save(Long id) throws IOException {
        if (id== null){
          return;
        }
       this.searchService.saveMQ(id);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "WXW.SEARCH.DELETE.QUEUE",durable = "true"),
            exchange = @Exchange(value = "WXW.ITEM.EXCHANGE",ignoreDeclarationExceptions = "true",type = ExchangeTypes.TOPIC),
            key = {"item.delete"}
    ))
    public void delete(Long id) throws IOException {
        if (id== null){
            return;
        }
        this.searchService.deleteMQ(id);
    }
}
