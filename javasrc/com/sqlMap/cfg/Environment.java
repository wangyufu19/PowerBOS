package com.sqlMap.cfg;
import java.util.Map;
import java.util.HashMap;
/**
 * 数据库配置环境类
 * @author youfu.wang
 * @version 1.0
 */
public class Environment {
	public static String dialect="";
	public static String dialect_version="";

	protected static Map defaultDatasource=new HashMap();
	protected static Map datasources=new HashMap();
	/**
	 * 返回一个默认数据源配置环境
	 * @return
	 */
	public static Map getDefaultDatasource(){
		return defaultDatasource;
	}
	/**
	 * 根据标识符返回一个数据源配置环境
	 * @param identifer
	 * @return
	 */
	public static Map getDatasource(String identifer){
		if(datasources.containsKey(identifer))
			return (Map)datasources.get(identifer);
		return null;
	}
	
}
