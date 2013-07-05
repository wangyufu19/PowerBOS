package com.application.console.model;
/**
 * 页面组件实体类
 * @author wangyf
 * @version 1.0
 */
public class PageWidget {
	private String key;
	private String pageCode;
	private String pageLocal;
	private String mimeType;
	private String loadSearchWidget;
	private String loadGridWidget;
	private String loadFormWidget;
	private String loadScriptWidget;
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getPageCode() {
		return pageCode;
	}
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}
	public String getPageLocal() {
		return pageLocal;
	}
	public void setPageLocal(String pageLocal) {
		this.pageLocal = pageLocal;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getLoadSearchWidget() {
		return loadSearchWidget;
	}
	public void setLoadSearchWidget(String loadSearchWidget) {
		this.loadSearchWidget = loadSearchWidget;
	}
	public String getLoadGridWidget() {
		return loadGridWidget;
	}
	public void setLoadGridWidget(String loadGridWidget) {
		this.loadGridWidget = loadGridWidget;
	}
	public String getLoadFormWidget() {
		return loadFormWidget;
	}
	public void setLoadFormWidget(String loadFormWidget) {
		this.loadFormWidget = loadFormWidget;
	}
	public String getLoadScriptWidget() {
		return loadScriptWidget;
	}
	public void setLoadScriptWidget(String loadScriptWidget) {
		this.loadScriptWidget = loadScriptWidget;
	}
}
