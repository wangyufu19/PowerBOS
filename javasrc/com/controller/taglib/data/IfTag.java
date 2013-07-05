package com.controller.taglib.data;
import java.util.regex.Pattern;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.controller.util.ValueStack;

public class IfTag extends BodyTagSupport{
	private String condition;	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public int doStartTag() throws JspException {
        if (compare())
            return (EVAL_BODY_INCLUDE);
        else
            return (SKIP_BODY);

    }
	public int doEndTag() throws JspException {

        return (EVAL_PAGE);

    }		
	public boolean compare(){		
		int i=0;
		int j=0;
		int k=0;
		String s="";
		String s1="";
		if(condition==null)
			return false;
		if(condition.indexOf("$")!=-1){
			i=condition.indexOf("$")+1;
			k=condition.length();
			if(condition.indexOf("!=")!=-1){
				j=condition.indexOf("!=");
				s1=condition.substring(j+2, k);
				s=condition.substring(i, j);	
				return compare(s,s1,"!=");
			}else if(condition.indexOf("=")!=-1){
				j=condition.indexOf("=");
				s1=condition.substring(j+1, k);
				s=condition.substring(i, j);					
				return compare(s,s1,"=");
			}
		}
		
		if("".equals(s)||"".equals(s1)){
			ValueStack.vs.put("if.condition", "false");
			return false;
		}			
		ValueStack.vs.put("if.condition", "false");
		return false;
	}
	public boolean compare(String s,String s1,String operator){			
		if(ValueStack.vs.containsKey(s))
			s=String.valueOf(ValueStack.vs.get(s));		
		if("!=".equals(operator)){			
			if(isNumericByMatch(s)&&isNumericByMatch(s1)){
				;
			}else{
				if(!s.equals(s1)){					
					ValueStack.vs.containsKey("if.condition");
					ValueStack.vs.put("if.condition", "true");
					return true;
				}else{
					ValueStack.vs.containsKey("if.condition");
					ValueStack.vs.put("if.condition", "false");
				}
			}
		}else if("=".equals(operator)){			
			if(isNumericByMatch(s)&&isNumericByMatch(s1)){
				;
			}else{
				if(s.equals(s1)){					
					ValueStack.vs.containsKey("if.condition");
					ValueStack.vs.put("if.condition", "true");
					return true;
				}else{
					ValueStack.vs.containsKey("if.condition");
					ValueStack.vs.put("if.condition", "false");
				}
			}
		}
		ValueStack.vs.put("if.condition", "false");
		return false;
	}
	public boolean isNumericByMatch(String s){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(s).matches();    
	} 
	public void release() {
        super.release();     
        condition=null;
    }
}
