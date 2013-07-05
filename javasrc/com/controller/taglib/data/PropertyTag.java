package com.controller.taglib.data;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.controller.taglib.TagUtil;
public class PropertyTag extends BodyTagSupport{
	private String name;
	private String value;	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public PropertyTag(){
		
	}
	public int doStartTag()throws JspException{		
		Object obj=TagUtil.getInstance().lookup(pageContext,name,value);		
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
    	value=null;        	
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
