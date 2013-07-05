package com.powerbosframework.util;
/**
 * 类加载器
 * @author youfu.wang
 * @version 1.0
 */
public class ClassLoaderUtil {	
	static Class com$powerbos$framework$util$ClassLoaderUtil;
	
	public Class loadClass(String s) throws ClassNotFoundException{
		ClassLoader classLoader=Thread.currentThread().getContextClassLoader();		
		if(classLoader==null){
			classLoader=(com$powerbos$framework$util$ClassLoaderUtil!=null?com$powerbos$framework$util$ClassLoaderUtil:(com$powerbos$framework$util$ClassLoaderUtil=class$("com.powerbos.framework.util.ClassLoaderUtil"))).getClassLoader();
		}
		return classLoader.loadClass(s);
	}
	static Class class$(String s){
		try{
			return Class.forName(s);
	    }catch(ClassNotFoundException classnotfoundexception){
	    	throw new NoClassDefFoundError(classnotfoundexception.getMessage());
	    }
	}

}
