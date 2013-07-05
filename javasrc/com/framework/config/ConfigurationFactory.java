package com.framework.config;

import com.framework.config.Configuration;
/**
 * 配置工厂类
 * @author youfu.wang
 * @version 1.0
 */
public class ConfigurationFactory {
	
	private static Configuration instance=null;
	
	public static Configuration newInstance(){
		if(instance==null){
			instance=new Configuration();
		}
		return instance;
	}

}
