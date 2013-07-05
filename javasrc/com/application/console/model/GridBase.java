package com.application.console.model;
/**
 * 网格基本属性实体类
 * @author wangyf
 * @version 1.0
 */
public class GridBase {	
	private String gridTitle;
	private String loadDataSetStyle;
	private String loadLinkToolbar;
	private String loadHandleToolbar;
	private String loadPageToolbar;
	private String pageFetchSize;
	
	public String getGridTitle() {
		return gridTitle;
	}
	public void setGridTitle(String gridTitle) {
		this.gridTitle = gridTitle;
	}
	public String getLoadLinkToolbar() {
		return loadLinkToolbar;
	}
	public void setLoadLinkToolbar(String loadLinkToolbar) {
		this.loadLinkToolbar = loadLinkToolbar;
	}
	public String getLoadHandleToolbar() {
		return loadHandleToolbar;
	}
	public void setLoadHandleToolbar(String loadHandleToolbar) {
		this.loadHandleToolbar = loadHandleToolbar;
	}
	public String getLoadPageToolbar() {
		return loadPageToolbar;
	}
	public void setLoadPageToolbar(String loadPageToolbar) {
		this.loadPageToolbar = loadPageToolbar;
	}
	public String getPageFetchSize() {
		return pageFetchSize;
	}
	public void setPageFetchSize(String pageFetchSize) {
		this.pageFetchSize = pageFetchSize;
	}
	public String getLoadDataSetStyle() {
		return loadDataSetStyle;
	}
	public void setLoadDataSetStyle(String loadDataSetStyle) {
		this.loadDataSetStyle = loadDataSetStyle;
	}

}
