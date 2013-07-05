package com.controller.forward;
import java.io.Serializable;
public class ActionForward implements Serializable{
	private static final long serialVersionUID = 9072341849247505347L;
	private String name;
	private String value;
	public ActionForward(){
		
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
