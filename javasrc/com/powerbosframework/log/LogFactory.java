package com.powerbosframework.log;

/**
 * 日志工厂类
 * @author wangyf
 * @version 1.0
 */
public class LogFactory {
	
	private static Logger instance=null;
	
	public static Logger getInstance(){
		if(instance==null)
			instance=new Logger();
		return instance;
	}	
}
