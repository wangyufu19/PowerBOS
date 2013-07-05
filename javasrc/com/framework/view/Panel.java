package com.framework.view;
/**
 * 面板组件基类
 * @author wangyf
 * @version 1.0
 */
public class Panel {
	protected String title;//面板标题
	protected String loadDataSetStyle="statement";//加载数据集方式
	protected String loadColumnSetStyle="single";//加载字段集方式
	protected String loadLinkToolbar="false";//加载链接工具条
	protected String loadHandleToolbar="false";//加载操作工具条
	protected String loadPageToolbar="false";//加载分页工具条
	protected String pagingFetchSize="8";//分页加载记录数
	protected String filterable="false";//可否过滤列模型
	protected String sortable="false";//可否排序列模型	

	public Panel(){
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLoadDataSetStyle() {
		return loadDataSetStyle;
	}

	public void setLoadDataSetStyle(String loadDataSetStyle) {
		if("".equals(loadDataSetStyle)||loadDataSetStyle==null) return;
		this.loadDataSetStyle = loadDataSetStyle;
	}
	public String getLoadColumnSetStyle() {
		return loadColumnSetStyle;
	}

	public void setLoadColumnSetStyle(String loadColumnSetStyle) {
		if("".equals(loadColumnSetStyle)||loadColumnSetStyle==null) return;
		this.loadColumnSetStyle = loadColumnSetStyle;
	}
	public String getLoadLinkToolbar() {
		return loadLinkToolbar;
	}

	public void setLoadLinkToolbar(String loadLinkToolbar) {
		if("".equals(loadLinkToolbar)||loadLinkToolbar==null) return;
		this.loadLinkToolbar = loadLinkToolbar;
	}

	public String getLoadHandleToolbar() {
		return loadHandleToolbar;
	}

	public void setLoadHandleToolbar(String loadHandleToolbar) {
		if("".equals(loadHandleToolbar)||loadHandleToolbar==null) return;
		this.loadHandleToolbar = loadHandleToolbar;
	}

	public String getLoadPageToolbar() {
		return loadPageToolbar;
	}

	public void setLoadPageToolbar(String loadPageToolbar) {
		if("".equals(loadPageToolbar)||loadPageToolbar==null) return;
		this.loadPageToolbar = loadPageToolbar;
	}

	public String getPagingFetchSize() {
		return pagingFetchSize;
	}

	public void setPagingFetchSize(String pagingFetchSize) {
		if("".equals(pagingFetchSize)||pagingFetchSize==null) return;
		this.pagingFetchSize = pagingFetchSize;
	}

	public String getFilterable() {
		return filterable;
	}

	public void setFilterable(String filterable) {
		this.filterable = filterable;
	}

	public String getSortable() {
		return sortable;
	}

	public void setSortable(String sortable) {
		this.sortable = sortable;
	}	
}
