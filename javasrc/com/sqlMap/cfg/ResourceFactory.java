package com.sqlMap.cfg;
import com.sqlMap.cfg.Resource;
import com.sqlMap.impl.ResourceImpl;
/**
 * SqlMap资源工厂类
 * @author youfu.wang
 * @version 1.0
 */
public class ResourceFactory {
	
	private static Resource instance=null;
	
	public ResourceFactory(){
		
	}
	/**
	 * 返回一个SqlMap资源对象单例
	 * @return
	 */
	public static Resource getInstance(){
		if(instance==null)
			instance=new ResourceImpl();
		return instance;			
	}	
}
