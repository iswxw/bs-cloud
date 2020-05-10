package com.wxw.cloud.vo;

import com.wxw.cloud.domain.Sku;
import lombok.Data;

/**
 * @Author: wxw
 * @create: 2020-05-10-19:14
 */
@Data
public class SkuVO extends Sku {

    // 副标题
    private String subtitle;
}
