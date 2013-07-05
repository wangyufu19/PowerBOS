package com.controller.taglib.data;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.controller.util.ValueStack;
public class ElseTag extends BodyTagSupport{
	
	public int doStartTag() throws JspException {
        if (compare())
            return (EVAL_BODY_INCLUDE);
        else
            return (SKIP_BODY);

    }
	public boolean compare(){
		String s="";
		if(ValueStack.vs.containsKey("if.condition")){
			s=String.valueOf(ValueStack.vs.get("if.condition"));
			ValueStack.vs.remove("if.condition");
		}	
		if("true".equals(s)){
			return false;
		}
		return true;	
	}
	public int doEndTag() throws JspException {

        return (EVAL_PAGE);

    }

}
