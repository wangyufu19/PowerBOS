package com.application.console.model;

/**
 * 系统自定义插件实体类
 * @author youfu.wang
 * @version 1.0
 */
public class SysPlugin {
	private String key;
	private String name;
	private String clazz;
	private String desc;
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
