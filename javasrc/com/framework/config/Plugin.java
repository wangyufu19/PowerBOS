package com.framework.config;

/**
 * 系统插件加载类
 * @author youfu.wang
 * @version 1.0
 */
public abstract class Plugin {
	
	public Plugin(){
		
	}
	/**
	 * 插件加载抽象方法
	 * @return
	 */
	public abstract void load();
	
}
