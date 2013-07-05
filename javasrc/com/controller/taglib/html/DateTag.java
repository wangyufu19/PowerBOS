package com.controller.taglib.html;
import javax.servlet.jsp.JspException;
import javax.servlet.http.HttpServletRequest;

import com.controller.taglib.InputTag;
import com.controller.taglib.TagUtil;

public class DateTag extends InputTag{
	
	public int doStartTag() throws JspException{
		StringBuffer buf=new StringBuffer();
		buf.append(this.getDateLabel());
		buf.append("<input ")
		   .append(" type=\"text\"")
		   .append(" name=\""+property+"\"")
		   .append(this.getDateId())
		   .append(this.getDateValue())
		   .append(this.getDateCssClass())
		   .append("/>")
		   .append(this.getDateImage());
		TagUtil.getInstance().write(pageContext, buf.toString());
		return (EVAL_BODY_TAG);
	}
	public String getDateLabel(){
		if(label==null)
			return "";
		else 
			return label;
	}
	public String getDateId(){
		if(id==null)
			return " id=\""+property+"\"";
		else
			return " id=\""+id+"\"";
	}
	public String getDateValue(){
		if(value==null)
			return " value=\"\"";
		else
			return " value=\""+value+"\"";
	}
	public String getDateCssClass(){
		if(cssClass==null)
			return "";
		else
			return " class=\""+cssClass+"\"";
	}
	public String getDateImage(){
		StringBuffer buf=new StringBuffer();
		String contextPath=((HttpServletRequest)pageContext.getRequest()).getContextPath();
		String imageNPath=contextPath+"/lib/default/images/bt_calendar_n.gif";
		String imageOPath=contextPath+"/lib/default/images/bt_calendar_o.gif";
		//插入日期图片	
		buf.append("<img ");
		buf.append("src=\""+imageNPath+"\" ");
		buf.append("name=\""+"bt_calendar\" ");
		buf.append("align=\""+"absmiddle\" ");
		buf.append("border=\""+"0\" ");
		buf.append("style=\""+"cursor:hand; \" ");
		buf.append("onMouseOut=\""+"MM_swapImgRestore()\" ");
		buf.append("onMouseOver=\""+"MM_swapImage('bt_calendar','','"+imageOPath+"',1)\" ");
		if(id==null)
			buf.append("onclick=\""+"return showCalendar('"+property+"','%Y-%m-%d','24');\"");	
		else
			buf.append("onclick=\""+"return showCalendar('"+id+"','%Y-%m-%d','24');\"");	
		buf.append(">");
		return buf.toString();
	}
	public int doEndTag(){
		return (EVAL_PAGE);
	}

}
