package com.wxw.cloud.rpc;

import com.wxw.cloud.api.BrandAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("cloud-bs-service")
public interface BrandClient extends BrandAPI {
}
