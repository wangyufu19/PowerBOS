package com.sqlMap.cache;

import java.lang.Thread;
import com.sqlMap.cache.CacheManager;
import com.sqlMap.log.LogFactory;
import com.sqlMap.log.Logger;

/**
 * 数据缓存监听器
 * 
 * @author youfu.wang
 * @version 1.0
 */
public class CacheListener extends Thread {
	private static Logger log = LogFactory.getInstance();
	private long period = 50;// 执行连接池定时器任务的时间间隔，单位是毫秒。

	public void run() {
		CacheManager cacheManager = CacheManager.getInstance();
		
		if (!CacheManager.cacheAlways && CacheManager.cacheObjects != null) {
			while (true) {
				// 删除空闲缓存对象				
				cacheManager.removeCacheObject();
				try {
					sleep(period);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
