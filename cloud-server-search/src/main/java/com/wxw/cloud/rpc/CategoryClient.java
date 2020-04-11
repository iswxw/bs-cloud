package com.wxw.cloud.rpc;

import com.wxw.cloud.api.CategoryAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("cloud-bs-service")
public interface CategoryClient extends CategoryAPI {
}
