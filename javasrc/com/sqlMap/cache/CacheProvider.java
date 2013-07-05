package com.sqlMap.cache;
import com.sqlMap.cache.CacheManager;
import com.sqlMap.cache.CacheListener;
import com.sqlMap.log.LogFactory;
import com.sqlMap.log.Logger;
/**
 * 数据缓存供应商
 * @author youfu.wang
 * @version 1.0
 */
public class CacheProvider {
	private static Logger log=LogFactory.getInstance();	
	/**
	 * 初始化一个缓存供应商
	 * @param always
	 * @param maxElements
	 * @param keepSeconds
	 * @param freeSeconds
	 */
	public void init(boolean always,int maxElements,long freeSeconds){
		
		CacheManager caheManager=CacheManager.getInstance();
		//初始化缓存管理
		caheManager.init(always, maxElements, freeSeconds);
		//启动缓存监听器
		CacheListener cacheListener=new CacheListener();
		cacheListener.start();
	}
}
