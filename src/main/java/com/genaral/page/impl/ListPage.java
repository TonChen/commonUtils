package com.genaral.page.impl;


import com.genaral.page.Page;
import com.genaral.page.PageRequest;

import java.util.List;

/**
 * 处理List的分页
 * @author badqiu
 * @see Page
 */
@Deprecated
public class ListPage extends Page {
    /**
     * 构建ListPage对象，完成List数据的分页处理
     *
     * @param elements   List数据源
     * @param pageNumber 当前页编码，从1开始，如果传的值为Integer.MAX_VALUE表示获取最后一页。
     *                   如果你不知道最后一页编码，传Integer.MAX_VALUE即可。如果当前页超过总页数，也表示最后一页。
     *                   这两种情况将重新更改当前页的页码，为最后一页编码。
     * @param pageSize   每一页显示的条目数
     */
    public ListPage(List elements, int pageNumber, int pageSize){
    	super(pageNumber,pageSize,elements.size());
    	List subList = ((List)elements).subList(getThisPageFirstElementNumber() - 1, getThisPageLastElementNumber());
    	setResult(subList);
    }

    public ListPage(List elements, PageRequest p){
    	this(elements,p.getPageNumber(),p.getPageSize());
    }
}

