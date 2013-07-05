package com.controller.config;
import java.util.HashMap;
import java.util.Map;

import com.controller.forward.ActionForward;
public class ActionConfig {
	private Map actionforwards;	
	private String path;
	private String prefix;
	private String name;
	private String clazz;
	private String method;
	
	public ActionConfig(){
		actionforwards=new HashMap();
	}
	public void addActionForward(ActionForward actionforward){
		actionforwards.put(actionforward.getName(), actionforward);
	}
	public ActionForward findActionForward(String s){
		ActionForward actionforward=null;
		if(actionforwards.containsKey(s)){
			actionforward=(ActionForward)actionforwards.get(s);
		}
		return actionforward;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}

}
