package com.controller.taglib.html;
import javax.servlet.jsp.JspException;

import com.controller.taglib.BaseHandleTag;
import com.controller.taglib.TagUtil;
public class ButtonTag extends BaseHandleTag{
	
	public ButtonTag(){
	
	}
	public int doStartTag() throws JspException{
		StringBuffer buf=new StringBuffer();
		buf.append("<button ")
		   .append(this.getButtonCssClass())
		   .append(this.getButtonOnclick())
		   .append(">")
		   .append(this.getButtonValue())
		   .append("</button>");
		TagUtil.getInstance().write(pageContext, buf.toString());
		return (EVAL_BODY_TAG);
	}
	public String getButtonCssClass(){
		if(cssClass==null) 
			return "";
		else
			return " class=\""+cssClass+"\"";
	}
	public String getButtonOnclick(){
		if(onclick==null) 
			return "";
		else
			return " onclick=\""+onclick+"\"";
	}
	public String getButtonValue(){
		if(value==null)
			return "";
		else
			return value;
	}
	public int doEndTag(){
		return (EVAL_PAGE);
	}

}
