package com.framework.view.adapter;
/**
 * 工具条对象类
 * @author wangyf
 * @version 1.0
 */
public class ToolbarObject {
	private String code;
	private String name;
	private String value;
	private String href;
	private String onclick;	
	private boolean flag;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getOnclick() {
		return onclick;
	}
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}	
	public void setFlag(boolean flag){
		this.flag=flag;
	}
	public boolean getFlag(){
		return this.flag;
	}
	
	
	
}
