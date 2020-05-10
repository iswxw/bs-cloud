package com.wxw.cloud.tools;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListPageUtil<T> {
     
    /**
     * 每页显示条数
     */
    private int pageSize;
     
    /**
     * 总页数
     */
    private int pageCount;
     
    /**
     * 原集合
     */
    private List<T> data;

    //List分页
    public ListPageUtil(List<T> data, int pageSize) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("data must be not empty!");
        }
 
        this.data = data;
        this.pageSize = pageSize;
        this.pageCount = data.size()/pageSize;
        if(data.size()%pageSize!=0){
            this.pageCount++;
        }
    }
 
    /**
     * 得到分页后的数据
     *
     * @param pageNum 页码
     * @return 分页后结果
     */
    public List<T> getPagedList(int pageNum) {
        int fromIndex = (pageNum - 1) * pageSize;
        if (fromIndex >= data.size()) {
            return Collections.emptyList();
        }
 
        int toIndex = pageNum * pageSize;
        if (toIndex >= data.size()) {
            toIndex = data.size();
        }
        return data.subList(fromIndex, toIndex);
    }
 
    public int getPageSize() {
        return pageSize;
    }
 
    public List<T> getData() {
        return data;
    }
 
    public int getPageCount() {
        return pageCount;
    }
 
//    public static void main(String[] args) {
//        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
//        List<Integer> list = Arrays.asList(array);
//
//        ListPageUtil<Integer> pager = new ListPageUtil<Integer>(list, 5);
//
//        System.out.println("总页数："+pager.getPageCount());
//        List<Integer> page1 = pager.getPagedList(1);
//        System.out.println("第一页"+page1);
//
//        List<Integer> page2 = pager.getPagedList(2);
//        System.out.println("第二页"+page2);
//
//        List<Integer> page3 = pager.getPagedList(3);
//        System.out.println("第三页"+page3);
//    }
}