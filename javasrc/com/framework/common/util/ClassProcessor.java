package com.framework.common.util;
import java.lang.Class;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.framework.common.base.BaseBiz;
import com.framework.common.servlet.http.RequestHash;
/**
 * 接口调用类
 * @author wangyf
 * @version 1.0
 */
public class ClassProcessor {
	private RequestHash reh;
	
	public ClassProcessor(){		
		
	}
	public ClassProcessor(RequestHash reh){		
		this.reh=reh;
	}
	public void loadClass(String className,String methodName) throws ClassNotFoundException, SecurityException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		ClassLoader classLoader=Thread.currentThread().getContextClassLoader();				
		Class<?> clazz=classLoader.loadClass(className);		
		Method method=clazz.getMethod(methodName);			
		Object instance=clazz.newInstance();				
		method.invoke(instance);				
	}
	public String process(String className,String methodName) throws ClassNotFoundException, SecurityException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String ret="成功";
		Object obj;
		ClassLoader classLoader=Thread.currentThread().getContextClassLoader();				
		Class clazz=classLoader.loadClass(className);		
		Method method=clazz.getMethod(methodName);			
		Object instance=clazz.newInstance();		
		((BaseBiz)instance).setReh(reh);		
		obj=method.invoke(instance);			
		ret=String.valueOf(obj);		
		return ret;
	}
	public String process(String className,String methodName,Object args){
		String ret="";
		Object obj;
		try {
			ClassLoader classLoader=Thread.currentThread().getContextClassLoader();	
			Class clazz=classLoader.loadClass(className);
			Method method=clazz.getMethod(methodName, new String().getClass());
			obj=method.invoke(clazz.newInstance(), args);			
			ret=String.valueOf(obj);
		} catch (ClassNotFoundException e) {	
			ret="系统环境没有该类";
			e.printStackTrace();
		} catch (SecurityException e) {		
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			ret=className+"类没有该方法";
			e.printStackTrace();
		} catch (IllegalArgumentException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {		
			e.printStackTrace();
		} catch (InvocationTargetException e) {			
			e.printStackTrace();
		} catch (InstantiationException e) {			
			e.printStackTrace();
		}
		return ret;
	}
	public String process(String className,String methodName,Object[] args){
		String ret="";
		Object obj;
		Class[] parameterTypes=null;
		try {
			ClassLoader classLoader=Thread.currentThread().getContextClassLoader();	
			Class clazz=classLoader.loadClass(className);
			if(args!=null){
				parameterTypes=new Class[args.length];
				for(int i=0;i<parameterTypes.length;i++){
					parameterTypes[i]=new String().getClass();					
				}
			}
			Method method=clazz.getMethod(methodName, parameterTypes);
			obj=method.invoke(clazz.newInstance(),args);
			ret=String.valueOf(obj);
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
			ret="系统环境没有该类";
		} catch (SecurityException e) {		
			e.printStackTrace();
		} catch (NoSuchMethodException e) {		
			e.printStackTrace();
			ret=className+"类没有该方法";
		} catch (IllegalArgumentException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {		
			e.printStackTrace();
		} catch (InvocationTargetException e) {			
			e.printStackTrace();
		} catch (InstantiationException e) {			
			e.printStackTrace();
		}
		return ret;
	}


}
