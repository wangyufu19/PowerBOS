package com.controller.property;

public interface PageParam {
	public void setFirstResult(int firstResult);
	public void setMaxResult(int maxResult);
	public void setPageSize(int pageSize);
	public void setCurrentPage(int currentPage);
	public void setMaxPage(int maxPage);
	public int getFirstResult();
	public int getMaxResult();
	public int getPageSize();
	public int getCurrentPage();
	public int getMaxPage();

}
