package com.ymxx.jweb.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ymxx.jweb.exception.YmxxException;
import com.ymxx.jweb.util.JacksonUtil;

public class Pagination<T> {

	/**
	 * 一页显示的记录数
	 */
	private Integer pageSize = 20;
	/**
	 * 页号
	 */
	private Integer pageNo = 1;
	/**
	 * 总记录数
	 */
	private Long totalCount = 0l;
	
	private List<T> pageItems;
	
	
	public Pagination() {
		super();
	}
	
	
	public Pagination(Integer pageNumber,Integer pageSize ) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNumber;
	}

	
	
	public Pagination(HttpServletRequest request) {
		
		if(request == null){
			
			throw new YmxxException("can't construct pagination by request");
		}
		try{
			int rows = Integer.valueOf(request.getParameter("rows"));
			int page = Integer.valueOf(request.getParameter("page"));
			this.pageNo = page;
			this.pageSize = rows;
		}catch(NumberFormatException e){
			
			this.pageNo = 1;
			this.pageSize = 20;
		}
	}


	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Integer getPageNo() {
		return pageNo;
	}


	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}


	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}


	public List<T> getPageItems() {
		return pageItems;
	}


	public void setPageItems(List<T> pageItems) {
		this.pageItems = pageItems;
	}
	public String toTableJson(){
		
		Map<String, Object> table = new HashMap<String, Object>();
		table.put("rows", this.pageItems);
		table.put("total", totalCount);
		String json = JacksonUtil.jsonUtil().formatReadAsString(table);
		return json;
	}
}
