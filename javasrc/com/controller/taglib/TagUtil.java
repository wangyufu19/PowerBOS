package com.controller.taglib;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.controller.util.PropertyUtil;
import com.controller.util.ValueStack;

public class TagUtil {	
	private static final Log log;
	private static TagUtil instance=null;
	static Class class$com$controller$taglib$TagUtil;
	
	public TagUtil(){
		
	}
	public static TagUtil getInstance(){
		if(instance==null)
			instance=new TagUtil();
		return instance;
	}
	public static String filter(String s)
    {
        if(s == null || s.length() == 0)
            return s;
        StringBuilder buf = null;
        Object obj = null;
        for(int i = 0; i < s.length(); i++){
            String s1 = null;
            switch(s.charAt(i)){
            case 60: // '<'
                s1 = "&lt;";
                break;

            case 62: // '>'
                s1 = "&gt;";
                break;

            case 38: // '&'
                s1 = "&amp;";
                break;

            case 34: // '"'
                s1 = "&quot;";
                break;

            case 39: // '\''
                s1 = "&#39;";
                break;
            }
            if(buf == null){
                if(s1 != null){
                    buf = new StringBuilder(s.length() + 50);
                    if(i > 0)
                        buf.append(s.substring(0, i));
                    buf.append(s1);
                }
            } else
            if(s1 == null)
                buf.append(s.charAt(i));
            else
                buf.append(s1);
        }

        return buf != null ? buf.toString() : s;
    }
	public void write(PageContext pageContext,String s)throws JspException{
		JspWriter jspwriter=pageContext.getOut();
		try {			
			jspwriter.print(s);			
		} catch (IOException e) {		
			log.info("%The tag write error%");
			e.printStackTrace();			
		}
	}
	public void writePrevious(PageContext pagecontext, String s)throws JspException{
		JspWriter jspwriter = pagecontext.getOut();
		if(jspwriter instanceof BodyContent)
		    jspwriter = ((BodyContent)jspwriter).getEnclosingWriter();
		try {
		    jspwriter.print(s);
		}catch(IOException ioexception){
			log.info("%The tag write bodycontent error%");
		}	 
	}

	public Object lookup(PageContext pageContext,String s){
		if(s==null) return null;		
		ValueStack  vs=(ValueStack)pageContext.getRequest().getAttribute("valueStack");	
		return vs.get(s);
	}
	
	public Object lookup(PageContext pageContext,String s,String s1){
		Object obj=this.lookup(pageContext, s1);		
		if(s==null){
			if(obj instanceof String){
				return obj;
			}
		}
		try {						
			obj=this.lookup(pageContext, s);			
			if(s1==null)
				return obj;
			else{				
				if(obj instanceof Map){
					String key=((Map)obj).keySet().iterator().next().toString();				  
					String value=String.valueOf(((Map)obj).get(key));				
					if(s1.equals("key"))
						return key;
					else if(s1.equals("value")){
						return value;
					}
				}else
					return PropertyUtil.getInstance().getProperty(obj, s1);
			}
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
		return obj;
	}
	static Class class$(String s) {
		try{
			return Class.forName(s);
		}catch(ClassNotFoundException classnotfoundexception){
			throw new NoClassDefFoundError(classnotfoundexception.getMessage());
		}	    
	}
	static {
		log = LogFactory.getLog(class$com$controller$taglib$TagUtil != null ? class$com$controller$taglib$TagUtil : (class$com$controller$taglib$TagUtil = class$("com.controller.taglib.TagUtil")));
	}

}
