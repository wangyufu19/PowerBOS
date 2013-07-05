package com.sqlMap.cache;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import com.sqlMap.log.LogFactory;
import com.sqlMap.log.Logger;
import com.sqlMap.cache.CacheObject;

/**
 * 数据缓存管理
 * @author youfu.wang
 * @version 1.0
 */
public class CacheManager {
	private static Logger log=LogFactory.getInstance();	
	private static CacheManager instance=null;
	public static Map cacheObjects=null;
	static public boolean cacheAlways;//缓存是否一直驻入内存
	private int maxElements;//缓存最大存储容量
	static public long freeTimes;//缓存驻入内存空闲时间(毫秒)
	
	public CacheManager(){		
		cacheAlways=true;
		this.maxElements=10000;
		freeTimes=300*1000;
	}
	/**
	 * 返回一个缓存管理实例
	 * @return
	 */
	public static CacheManager getInstance(){
		if(instance==null){
			instance=new CacheManager();
		}
		return instance;
	}
	/**
	 * 初始化一个缓存管理
	 * @param always
	 * @param maxElements
	 * @param keepSeconds
	 * @param freeSeconds
	 */
	public void init(boolean always,int maxElements,long freeSeconds){
		cacheAlways=always;
		this.maxElements=maxElements;
		freeTimes=freeSeconds*1000;
		cacheObjects=Collections.synchronizedMap(new HashMap(this.maxElements));
	}
	/**
	 * 插入一个缓存对象
	 * @param key
	 * @param obj
	 */
	public synchronized void putCacheObject(String key,Object obj){
		if(cacheObjects!=null){
			CacheObject cacheObject=new CacheObject();	
			cacheObject.setUseQueryTimes(System.currentTimeMillis());
			cacheObject.setObj(obj);				
			cacheObjects.put(key, cacheObject);			
		}			
	}
	/**
	 * 得到一个缓存对象
	 * @param key
	 */
	public synchronized Object getCacheObject(String key){
		if(cacheObjects!=null){			
			if(cacheObjects.containsKey(key)){
				CacheObject cacheObject=(CacheObject)cacheObjects.get(key);
				cacheObject.setUseQueryTimes(System.currentTimeMillis());				
				return cacheObject.getObj();
			}
		}
		return null;
	}
	/**
	 * 删除空闲缓存对象
	 */
	public void removeCacheObject(){
		Iterator iter = CacheManager.cacheObjects.entrySet().iterator(); 
		while (iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 		   
		    Object key = entry.getKey(); 
		    CacheObject cacheObject=(CacheObject)entry.getValue(); 		    
			cacheObject.setRunPassTimes(System.currentTimeMillis());
			//删除空闲时间缓存对象
			if((cacheObject.getRunPassTimes()-cacheObject.getUseQueryTimes())>freeTimes){				
				iter.remove();
				cacheObjects.remove(key);
			}
		} 		
	}
	/**
	 * 删除一个空闲缓存对象 
	 * @param key
	 */
	public void removeCacheObject(String key){
		if(cacheObjects!=null)
			cacheObjects.remove(key);
	}
	/**
	 * 释放缓存数据内存
	 */
	public void free(){
		if(cacheObjects!=null){
			cacheObjects.clear();
			cacheObjects=null;
		}
	}
	
}
