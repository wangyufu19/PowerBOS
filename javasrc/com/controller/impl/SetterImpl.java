package com.controller.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.controller.property.Setter;
public class SetterImpl implements Setter{
	private Class cls;
	private String name;
	private String methodName;	
	
	public SetterImpl(Class cls,String name){
		this.cls=cls;
		this.name=name;
	}
	public String getMethodName(){
		StringBuffer buf=new StringBuffer();		
		for(int i=0;i<name.length();i++){
			if(i==0){
				buf.append(Character.toUpperCase(name.charAt(i)));
			}else
				buf.append(name.charAt(i));
		}
		methodName="set"+buf.toString();						
		return this.methodName;
	}
	public void set(Object obj, Object value) {				
		if(value==null) return;
		methodName=this.getMethodName();
		Method method = null;
		try {
			Class[] parameterTypes=new Class[1];			
			parameterTypes[0]=value.getClass();						
			method = cls.getMethod(methodName,parameterTypes);
		} catch (SecurityException e1) {			
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {			
			e1.printStackTrace();
		}
		try {			
			method.invoke(obj, value);
		} catch (IllegalArgumentException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {			
			e.printStackTrace();
		} catch (InvocationTargetException e) {			
			e.printStackTrace();
		}			
		
	}

}
