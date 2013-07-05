package com.sqlMap.log;

import com.sqlMap.log.Logger;
/**
 * 日志工厂类
 * @author youfu.wang
 * @version1 .0
 */
public class LogFactory {
	
	private static Logger instance=null;

	public LogFactory(){
		
	}
	/**
	 * 返回一个日志单例
	 * @return
	 */
	public static Logger getInstance(){
		if(instance==null)
			instance=new Logger();
		return instance;
	}
	

}
