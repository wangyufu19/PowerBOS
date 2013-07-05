package com.controller.impl;
import com.controller.property.PageParam;
/**
 * 分页参数类
 * @author wangyf
 * @version 1.0
 */
public class PageParamImpl implements PageParam{		
	private int firstResult=0;
	private int maxResult=0;
	private int pageSize=10;
	private int currentPage;
	private int maxPage;
	
	
	public PageParamImpl(){
		
	}
	
	public int getFirstResult() {
		return firstResult;
	}


	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}


	public int getMaxResult() {
		return maxResult;
	}


	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

}
