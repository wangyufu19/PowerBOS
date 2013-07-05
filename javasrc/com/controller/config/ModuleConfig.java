package com.controller.config;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.controller.exception.ActionException;
import com.controller.forward.GlobalForward;

/**
 * 模块配置处理类
 * @author wangyf
 * @version 1.0
 */
public class ModuleConfig{
	protected Map actionsconfig;	
	protected Map globalsforward;
	private String prefix;
	
	public ModuleConfig(){
		actionsconfig=new HashMap();		
		globalsforward=new HashMap();
	}
	/**
	 * 得到模块配置对象
	 * @param httpservletrequest
	 * @param servletcontext
	 * @return
	 */
	public static ModuleConfig getModuleConfig(HttpServletRequest httpservletrequest, ServletContext servletContext){
		ModuleConfig moduleConfig=(ModuleConfig)httpservletrequest.getAttribute("com.controller.config.module");
		if(moduleConfig==null){
			moduleConfig=(ModuleConfig)servletContext.getAttribute("com.controller.config.module");
		}
		return moduleConfig;
	}
	/**
	 * 增加action配置
	 * @param actionconfig
	 */
	public void addActionConfig(ActionConfig actionconfig){		
		actionsconfig.put(actionconfig.getPath(), actionconfig);
	}
	/**
	 * 查找action配置
	 * @param s
	 */
	public ActionConfig findActionConfig(String s) throws ActionException{
		ActionConfig actionconfig=null;
		String path=s+".com.controller.config";			
		if(actionsconfig.containsKey(path)){
			actionconfig=(ActionConfig)actionsconfig.get(path);
		}
		return actionconfig;
	}	
	/**
	 * 增加global配置
	 * @param s
	 * @param globalconfig
	 */
	public void addGlobalForward(GlobalForward globalforward){
		globalsforward.put(globalforward.getName(),globalforward);
	}
	/**
	 * 查找全局配置
	 * @param s
	 */
	public GlobalForward findGlobalForward(String s){
		GlobalForward globalforward=null;	
		if(globalsforward.containsKey(s)){			
			globalforward=(GlobalForward)globalsforward.get(s);
		}
		return globalforward;
	}
	/**
	 * 释放配置内存
	 */
	public void freeze(){
		actionsconfig.clear();
		globalsforward.clear();
	}
	

}
