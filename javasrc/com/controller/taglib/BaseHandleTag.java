package com.controller.taglib;

import javax.servlet.jsp.tagext.BodyTagSupport;

public class BaseHandleTag extends BodyTagSupport{
	
	public String name;
	public String value;
	public String cssClass;
	public String onclick;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getOnclick() {
		return onclick;
	}
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
	
	public void release(){
		super.release();
		name=null;
		cssClass=null;
		onclick=null;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
