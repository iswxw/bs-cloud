package com.wxw.cloud.rpc;

import com.wxw.cloud.client.UserClient;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: wxw
 * @create: 2020-04-19-12:46
 */
@FeignClient("cloud-user-service")
public interface UserRpc extends UserClient {
}
