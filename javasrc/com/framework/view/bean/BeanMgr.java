package com.framework.view.bean;
/**
 * JAVABEAN管理类
 * @author wangyf
 * @version 1.0
 */
public class BeanMgr {
	
	public void setBean(Object obj,String name,Object value){
		Setter setter=BeanFactory.getSetter(obj.getClass(), name);		
		setter.set(obj, value);
		
	}
	public Object getBean(Object obj,String name){
		Getter getter=BeanFactory.getGetter(obj.getClass(), name);	
		return getter.get(obj);
	}
	public Class getReturnType(Object obj,String name){
		Getter getter=BeanFactory.getGetter(obj.getClass(), name);			
		return getter.getReturnType();
	}

}
