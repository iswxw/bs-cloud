package com.wxw.cloud.rpc;

import com.wxw.cloud.api.GoodsAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("cloud-bs-service")
public interface GoodsRpc extends GoodsAPI {
}
