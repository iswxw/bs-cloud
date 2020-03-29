package com.wxw.cloud.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: wxw
 * @create: 2020-03-29-22:44
 *  添加分页结果集
 */
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 4304677954417910784L;
    // 总条数
    private Long total;
    // 总页数
    private Long totalPage;
    // 当前页数据
    private List<T> items;

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Long totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }

}
