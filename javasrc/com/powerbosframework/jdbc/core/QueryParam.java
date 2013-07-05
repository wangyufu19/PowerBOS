package com.powerbosframework.jdbc.core;
/**
 * 分面查询参数类
 * @author youfu.wang
 * @version 1.0
 */
public class QueryParam {
	private String primary;	//表主键
	private int firstResult=0;//起始记录行
	private int maxResult=0;//最大记录行
	private int pageSize=10;//每页大小
	private int currentPage;//当面页号
	private int maxPage;//最大页号
	
	public QueryParam(){
		
	}	
	/**
	 * 返回起始记录行
	 * @return
	 */
	public int getFirstResult() {
		return firstResult;
	}
	/**
	 * 设置起始记录行
	 * @param firstResult
	 */
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}
	/**
	 * 返回最大记录行
	 * @return
	 */
	public int getMaxResult() {
		return maxResult;
	}
	/**
	 * 设置最大记录行
	 * @param maxResult
	 */
	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	/**
	 * 返回页面大小
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * 设置页面大小 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * 返回当前页号
	 * @return
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	/**
	 * 设置当前页号
	 * @param currentPage
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * 返回最大页号
	 * @return
	 */
	public int getMaxPage() {
		return maxPage;
	}
	/**
	 * 设置最大页号
	 * @param maxPage
	 */
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	/**
	 * 返回表主键
	 * @return
	 */
	public String getPrimary() {
		return primary;
	}
	/**
	 * 设置表主键
	 * @param primary
	 */
	public void setPrimary(String primary) {
		this.primary = primary;
	}
}
