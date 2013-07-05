package com.controller.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.controller.property.Getter;
public class GetterImpl implements Getter{
	private Class cls;
	private String name;
	private String methodName;
	
	public GetterImpl(Class cls,String name){
		this.cls=cls;
		this.name=name;
	}
	public Method getMethod(){			
		Method method=null;
		String methodName="";
		try {
			methodName=this.getMethodName();					
			method = cls.getMethod(methodName);
		} catch (SecurityException e) {			
			e.printStackTrace();
		} catch (NoSuchMethodException e) {			
			e.printStackTrace();
		}
		return method;
	}
	public Object get(Object obj){
		Object value="";
		Method method=this.getMethod();
		try {				
			
			value=method.invoke(obj);				
		} catch (IllegalArgumentException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {			
			e.printStackTrace();
		} catch (InvocationTargetException e) {			
			e.printStackTrace();
		}
		return value;
	}
	public String getMethodName(){
		StringBuffer buf=new StringBuffer();		
		for(int i=0;i<name.length();i++){
			if(i==0){
				buf.append(Character.toUpperCase(name.charAt(i)));
			}else
				buf.append(name.charAt(i));
		}
		methodName="get"+buf.toString();		
		return this.methodName;
	}
	public Class getReturnType(){
		Method method=this.getMethod();
		return method.getReturnType();
	}

}
