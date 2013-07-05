package com.controller.util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class RequestUtil {
	private static Log log;
	static Class com$controller$util$RequestUtil;/* synthetic field */
	
    public static Class applicationClass(String s)
      throws ClassNotFoundException {
      ClassLoader classloader = Thread.currentThread().getContextClassLoader();
      if(classloader == null)
          classloader = (com$controller$util$RequestUtil != null ? com$controller$util$RequestUtil : (com$controller$util$RequestUtil = class$("com.controller.util.RequestUtil"))).getClassLoader();
      return classloader.loadClass(s);
    }

	public static Object applicationInstance(String s)
      throws ClassNotFoundException, IllegalAccessException, InstantiationException{
		return applicationClass(s).newInstance();
	}
	public static String redirectURL(String s){
		if(s==null){
			return s;
		}		
		if(s.startsWith("/")){		
			int i=s.lastIndexOf("/");
			int j=s.lastIndexOf(".");		
		    if(j!=0&j>i){
		    	s=s.substring(1, j);		    	
		    }
		}else{
			int i=s.lastIndexOf(".");
			if(i>0){
				s=s.substring(0, i);
			}			
		}			
		return s;
	}
	static Class class$(String s){
		try {
			return Class.forName(s);
		} catch (ClassNotFoundException classnotfoundexception) {			
			throw new NoClassDefFoundError(classnotfoundexception.getMessage());
		}
	}
	static{
		log=LogFactory.getLog(com$controller$util$RequestUtil!=null?com$controller$util$RequestUtil:(com$controller$util$RequestUtil=class$("com.controller.util.RequestUtil")));
	}
	

}
