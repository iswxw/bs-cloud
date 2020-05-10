package com.wxw.cloud.bo;

import com.wxw.cloud.domain.Sku;
import com.wxw.cloud.domain.Spu;
import com.wxw.cloud.result.PageResult;
import com.wxw.cloud.vo.SkuVO;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @Author: wxw
 * @create: 2020-05-10-11:30
 */
@Data
public class SkuBO {

   // 三级类目ID
    private Set<Long> cid3s;
    // 三级类目名称
    private Set<String> cname;
    // sku集合
    private PageResult<SkuVO> skus;

}
