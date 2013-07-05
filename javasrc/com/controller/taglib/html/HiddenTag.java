package com.controller.taglib.html;
import javax.servlet.jsp.JspException;

import com.controller.taglib.InputTag;
import com.controller.taglib.TagUtil;
public class HiddenTag extends InputTag{
	public int doStartTag()throws JspException{	
		StringBuffer buf=new StringBuffer();	
		buf.append("<input ")
		   .append(" type=\"hidden\"")
		   .append(" name=\""+property+"\"")
		   .append(" value=\""+formatValue(value)+"\"")		 		  
		   .append(">")
		   .append(formatValue(value)+"&nbsp");
		TagUtil.getInstance().write(pageContext, buf.toString());		
		return 2;
	}	

	public String formatValue(String s){
		if(s==null)
			return "";
		return s;
	}
	public int doEndTag()throws JspException{
		return 6;
	}	

}
