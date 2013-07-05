package com.controller.taglib.data;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.JspException;

import com.controller.taglib.TagUtil;

public class WriteTag extends BodyTagSupport{	
	private String name;
	private String property;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public WriteTag(){
		
	}
	public int doStartTag()throws JspException{		
		Object obj=TagUtil.getInstance().lookup(pageContext, name, property);
		String s=formatValue(obj);
		TagUtil.getInstance().write(pageContext, s);
		return 2;
	}
	public int doEndTag()throws JspException{
		return 6;
	}	
	public String formatValue(Object obj){
		if(obj==null) return "";
		
		if(obj instanceof String){
			return String.valueOf(obj);
		}
		return String.valueOf(obj);
	}
	public void release(){
    	super.release();       
    	name=null;
    	property=null;        	
    }

}
