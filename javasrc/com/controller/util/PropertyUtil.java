package com.controller.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class PropertyUtil {	
	private static PropertyUtil instance=null;	
	
	public PropertyUtil(){
		
	}
	public static PropertyUtil getInstance(){
		if(instance==null)
			instance=new PropertyUtil();
		return instance;
	}
	
	public String getProperty(Object obj,String s) throws SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException{	
		String s1=String.valueOf(invokeMethod(obj,s));
		if(s1==null) return "";
		if(s1.equals("null")) return "";
		return s1;	
	}
	
	public Object invokeMethod(Object obj,String s)throws IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		if(obj==null){
			throw new IllegalArgumentException("No object specified");
		}
		if(s==null){
			throw new IllegalArgumentException("No method specified");
		}			
		Class clazz=obj.getClass();		
		Method method=clazz.getMethod(getMethod(s));			
		obj=method.invoke(obj,new Object[0]);			
		return obj;		
	}
	public String getMethod(String s){
		return formatMethod(s);
	}
	public String formatMethod(String s){
		StringBuffer buf=new StringBuffer();
		buf.append("get");
		for(int i=0;i<s.length();i++){
			if(i==0){
				buf.append(new Character(s.charAt(i)).toUpperCase(s.charAt(i)));
			}else
				buf.append(s.charAt(i));
		}
		return buf.toString();
	}
}
