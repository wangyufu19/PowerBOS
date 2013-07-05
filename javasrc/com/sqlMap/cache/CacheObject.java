package com.sqlMap.cache;
/**
 * 缓存对象属性类
 * @author youfu.wang
 * @version 1.0
 */
public class CacheObject {
	
	private long useQueryTimes;//使用查询缓存对象时间(毫秒)
	private long runPassTimes;//缓存对象运行经过时间(毫秒)
	private Object obj;//缓存对象
	
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public long getUseQueryTimes() {
		return useQueryTimes;
	}
	public void setUseQueryTimes(long useQueryTimes) {
		this.useQueryTimes = useQueryTimes;
	}
	public long getRunPassTimes() {
		return runPassTimes;
	}
	public void setRunPassTimes(long runPassTimes) {
		this.runPassTimes = runPassTimes;
	}	
}
