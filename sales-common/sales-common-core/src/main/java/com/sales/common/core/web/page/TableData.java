package com.sales.common.core.web.page;

import lombok.Data;

/**
 * @description: 分页方法
 * @author: wuyingqiang
 * @date: 2021-12-18 15:22
 */


@Data
public class TableData<T> {

    private static final long serialVersionUID = 6593205888444622221L;

    /**
     * 总条数
     */
    private long total;
    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 每页条数
     */
    private int pageSize;
    /**
     * 列表数据
     */
    private T list;

    public TableData(){

    }

    /*public TableData(T data){
        if(data instanceof List){
            PageInfo pageInfo = new PageInfo((List)data);
            this.total = pageInfo.getTotal();
            this.pageNum = pageInfo.getPageNum();
            this.pageSize = pageInfo.getPageSize();
        }
        this.list = data;
    }*/


}