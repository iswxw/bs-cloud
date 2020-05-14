package com.wxw.cloud.service;

import cn.hutool.json.JSONUtil;
import com.wxw.cloud.domain.Cart;
import com.wxw.cloud.domain.Sku;
import com.wxw.cloud.domain.UserInfo;
import com.wxw.cloud.interceptor.LoginInterceptor;
import com.wxw.cloud.rpc.GoodsRpc;
import com.wxw.cloud.tools.JsonUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wxw
 * @create: 2020-04-23-14:35
 */
@Service
public class CartService {

//    @Resource
//    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<Object,Object> template;

    @Resource
    private GoodsRpc goodsRpc;

    private static final String KEY_PREFIX = "user:cart:";

    /**
     * 添加购物车
     *
     * @param cart
     */
    public void addCart(Cart cart) {
        // 获取用户信息
        UserInfo userinfo = LoginInterceptor.getUserinfo();
        // 查询购物车记录
        BoundHashOperations<Object, Object, Object> hashOps = template.boundHashOps(KEY_PREFIX + userinfo.getId());
        String key = cart.getSkuId().toString();
        Integer num = cart.getNum();
        // 判断当前的商品是否在购物车中
        if (hashOps.hasKey(key)) {
            // 在：更新数据
            String cartJson = hashOps.get(key).toString();
            cart = JsonUtils.parse(cartJson, Cart.class);
            cart.setNum(cart.getNum()+num);
        } else {
            // 不在： 新增购物车
            Sku sku = this.goodsRpc.querySkuBySkuId(cart.getSkuId());
            cart.setUserId(userinfo.getId());
            cart.setTitle(sku.getTitle());
            cart.setOwnSpec(sku.getOwnSpec());
            cart.setImage(StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(),",")[0]);
            cart.setPrice(sku.getPrice());
            hashOps.put(key,cart);
        }
        hashOps.put(key,JsonUtils.serialize(cart));
    }

    /**
     * 查询购物车
     *
     * @return
     */
    public List<Cart> queryCarts() {
        UserInfo userinfo = LoginInterceptor.getUserinfo();
        // 判断用户是否有购物车记录
        if (!this.template.hasKey(KEY_PREFIX + userinfo.getId())){
            // 不存在，直接返回
            return null;
        }
        // 获取用户的购物车记录
        BoundHashOperations<Object, Object, Object> hashOps = template.boundHashOps(KEY_PREFIX + userinfo.getId());
        // 获取购物车所有cart集合
        List<Object> cartsJson = hashOps.values();
        // 如果购物车集合为空，直接返回null
        if (CollectionUtils.isEmpty(cartsJson)){
            return null;
        }
        // 把List<Object> 集合转换为List<Cart>集合
        return cartsJson.stream().map(cartJson -> JsonUtils.parse(cartJson.toString(), Cart.class)).collect(Collectors.toList());
    }

    /**
     * 修改购物车数量
     */
    public void updateNum(Cart cart) {
        UserInfo userinfo = LoginInterceptor.getUserinfo();
        // 判断用户是否有购物车记录
        if (this.template.hasKey(KEY_PREFIX + userinfo.getId())){
            return;
        }
        Integer num = cart.getNum();
        // 获取用户的购物车记录
        BoundHashOperations<Object, Object, Object> hashOps = template.boundHashOps(KEY_PREFIX + userinfo.getId());

        String cartJson = hashOps.get(cart.getSkuId().toString()).toString();

        cart = JsonUtils.parse(cartJson, Cart.class);
        cart.setNum(num);
        hashOps.put(cart.getSkuId().toString(),JsonUtils.serialize(cart));
    }

    /**
     * 删除购物车
     * @param skuId
     */
    public void deleteCart(String skuId) {
        // 获取登录用户
        UserInfo userinfo = LoginInterceptor.getUserinfo();
        // 获取用户的购物车记录
        BoundHashOperations<Object, Object, Object> hashOps = this.template.boundHashOps(KEY_PREFIX + userinfo.getId());
        hashOps.delete(skuId);
    }
}
