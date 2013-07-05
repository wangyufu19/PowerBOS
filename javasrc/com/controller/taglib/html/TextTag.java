package com.controller.taglib.html;
import javax.servlet.jsp.JspException;

import com.controller.taglib.InputTag;
import com.controller.taglib.TagUtil;

public class TextTag extends InputTag{
	
	public int doStartTag()throws JspException{	
		StringBuffer buf=new StringBuffer();
		buf.append(formatValue(label));
		buf.append("<input ")
		   .append(" type=\"text\"")
		   .append(" name=\""+property+"\"")
		   .append(" value=\""+formatValue(value)+"\"")
		   .append(getCssClass())
		   .append(getSize())
		   .append(getMaxlength())
		   .append(">");
		TagUtil.getInstance().write(pageContext, buf.toString());		
		return 2;
	}
	public String getCssClass(){
		StringBuffer buf=new StringBuffer();
		if(cssClass==null) return buf.toString();
		buf.append(" class=\""+cssClass+"\"");
		return buf.toString();
	}
	public String getSize(){		
		StringBuffer buf=new StringBuffer();
		if(size==null) return buf.toString();
		buf.append(" size=\""+size+"\"");
		return buf.toString();
	}
	public String getMaxlength(){
		StringBuffer buf=new StringBuffer();
		if(maxlength==null) return buf.toString();
		buf.append(" maxlength=\""+maxlength+"\"");
		return buf.toString();
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
