package com.wxw.cloud.bo;

import com.wxw.cloud.domain.Spu;
import lombok.Data;

/**
 * @Author: wxw
 * @create: 2020-04-04-16:53
 * 扩展spu对象
 */
@Data
public class SpuBO extends Spu {

    private String cname;

    private String bname;

}