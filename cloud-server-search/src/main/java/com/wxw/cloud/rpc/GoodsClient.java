package com.wxw.cloud.rpc;

import com.wxw.cloud.api.GoodsAPI;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: wxw
 * @create: 2020-04-11-18:43
 */
@FeignClient("cloud-bs-service")
public interface GoodsClient extends GoodsAPI {

}
