package com.framework.common.util;

public class ThreadParam {
	private int sleep;
	private String code;
	private String name;
	private String cls;
	private String auto;
	public String getAuto() {
		return auto;
	}
	public void setAuto(String auto) {
		this.auto = auto;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
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
	public ThreadParam(){
		
	}
	public int getSleep() {
		return sleep;
	}
	public void setSleep(int sleep) {
		this.sleep = sleep;
	}
	

}
