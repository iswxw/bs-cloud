package com.wxw.cloud.api;


import com.wxw.cloud.domain.SpecGroup;
import com.wxw.cloud.domain.SpecParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WXW
 * @since 2020-04-03
 */
@RequestMapping("/spec")
public interface SpecParamAPI {

    //@ApiOperation("根据条件查询规格参数")
    @GetMapping("params")
    public List<SpecParam> queryParams(
            @RequestParam(value = "gid",required = false)Long gid,
            @RequestParam(value = "cid",required = false)Long cid,
            @RequestParam(value = "generic",required = false)Boolean generic,
            @RequestParam(value = "searching",required = false)Boolean searching
    );

    //@ApiOperation("根据分类Id查询组下规格参数")
    @GetMapping("group/param/{cid}")
    public List<SpecGroup> queryGroupswithParam(@PathVariable("cid")Long cid);


}

