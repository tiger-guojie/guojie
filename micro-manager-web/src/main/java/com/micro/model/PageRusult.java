package com.micro.model;

import java.util.List;

import com.github.pagehelper.PageInfo;

public class PageRusult<T> extends PageInfo<T> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6852836166983749281L;

	public PageRusult() {
    }

    public PageRusult(List<T> list) {
        super(list, 8);
    }

    private Integer code;//layui框架列表模块返回参数中必须包含code状态字段

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
