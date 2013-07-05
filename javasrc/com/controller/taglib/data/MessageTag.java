package com.controller.taglib.data;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.controller.action.GlobalMessage;
import com.controller.taglib.TagUtil;
public class MessageTag extends BodyTagSupport{

	private static final long serialVersionUID = 772236613482139709L;	
	public int doStartTag()throws JspException{				
		Object obj=TagUtil.getInstance().lookup(pageContext, GlobalMessage.GLOBAL_KEY, null);
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
    }

}
