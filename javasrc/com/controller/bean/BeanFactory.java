package com.controller.bean;
import com.controller.impl.PageParamImpl;
import com.controller.property.PageParam;
public class BeanFactory {
	private static PageParam pageParam;
	
	public static PageParam getPageParamInstance(){
		if(pageParam==null)
			pageParam=new PageParamImpl();
		return pageParam;
	}

}
