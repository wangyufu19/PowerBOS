package com.framework.view.form;
/**
 * 表单元素基本属性类
 * @author wangyf
 * @version 1.0
 */
public class BaseTag {
	private String id;
	private String name;	
	private String width;
	private String height;
	private String value;
	private String maxLength;
	private String classStyle;
	private String readonly="false";
	private String extend;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}	
	public String getClassStyle() {
		return classStyle;
	}
	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}
	public String getReadonly() {
		return readonly;
	}
	public void setReadonly(String readonly){	
		if("".equals(readonly)) return;
		this.readonly = readonly;
	}
	public String getExtend() {
		return extend;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}
}
