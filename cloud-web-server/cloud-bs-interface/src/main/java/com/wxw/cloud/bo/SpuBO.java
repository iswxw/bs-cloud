package com.wxw.cloud.bo;

import com.wxw.cloud.domain.Sku;
import com.wxw.cloud.domain.Spu;
import com.wxw.cloud.domain.SpuDetail;
import lombok.Data;

import java.util.List;

/**
 * @Author: wxw
 * @create: 2020-04-04-16:53
 * 扩展spu对象
 */
@Data
public class SpuBO extends Spu {

    private String cname;

    private String bname;

    private SpuDetail spuDetail;

    private List<Sku> skus;


}