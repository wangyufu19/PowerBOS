package com.sqlMap.util;
/**
 * SqlMap类加载器
 * @author youfu.wang
 * @version 1.0
 */
public class SqlMapClassLoader {	
	static Class com$sqlMap$util$SqlMapClassLoader;
	
	public Class loadClass(String s) throws ClassNotFoundException{
		ClassLoader classLoader=Thread.currentThread().getContextClassLoader();		
		if(classLoader==null){
			classLoader=(com$sqlMap$util$SqlMapClassLoader!=null?com$sqlMap$util$SqlMapClassLoader:(com$sqlMap$util$SqlMapClassLoader=class$("com.sqlMap.util.SqlMapClassLoader"))).getClassLoader();
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
