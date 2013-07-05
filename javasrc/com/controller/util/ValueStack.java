package com.controller.util;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.HashMap;

public class ValueStack {	
	public static Map vs=new HashMap();
	
	public ValueStack(){
		
	}	
	public static Object get(String key,String s1){
		if(vs.containsKey(key)){
			try {
				return PropertyUtil.getInstance().getProperty(vs.get(key), s1);
			} catch (SecurityException e) {			
				e.printStackTrace();
			} catch (IllegalArgumentException e) {			
				e.printStackTrace();
			} catch (IllegalAccessException e) {				
				e.printStackTrace();
			} catch (NoSuchMethodException e) {				
				e.printStackTrace();
			} catch (InvocationTargetException e) {				
				e.printStackTrace();
			} catch (InstantiationException e) {			
				e.printStackTrace();
			}
		}
		return null;
	}
	public Object get(String key){
		if(vs.containsKey(key))
			return vs.get(key);
		return null;
	}
	public void  set(String key,Object value){
		if(vs.containsKey(key))
			vs.remove(key);
		vs.put(key, value);		
	}

}
