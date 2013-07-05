package com.sqlMap.property;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.sqlMap.property.Setter;
/**
 * Setter实现类
 * @author Administrator
 *
 */
public class BaseSetter implements Setter{	
	private Class cls;
	private String propertyName;
	private String methodName;	
	public BaseSetter(Class cls,String propertyName){
		this.cls=cls;
		this.propertyName=propertyName;
	}
	/**
	 * 设置对象或字段值
	 * @param obj
	 * @param value
	 */
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
	private String getMethodName(){
		StringBuffer buf=new StringBuffer();		
		for(int i=0;i<propertyName.length();i++){
			if(i==0){
				buf.append(Character.toUpperCase(propertyName.charAt(i)));
			}else
				buf.append(propertyName.charAt(i));
		}
		methodName="set"+buf.toString();						
		return this.methodName;
	}
	
	public static void main(String[] args){
		
	}

}
