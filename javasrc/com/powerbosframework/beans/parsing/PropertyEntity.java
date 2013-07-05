package com.powerbosframework.beans.parsing;
/**
 * POJO属性实体类
 * @author youfu.wang
 * @version 1.0
 */
public class PropertyEntity {
	
	private String name;
	private String value;
	private String ref;
	
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
