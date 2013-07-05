package com.controller.taglib.data;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.controller.taglib.TagUtil;
import com.controller.util.ValueStack;

public class IteratorTag extends BodyTagSupport{
	private String id;
	private String name;
	private String status;
	private int index;
	private Iterator iterator;
	
	public IteratorTag(){
		id=null;
		iterator=null;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int doStartTag()throws JspException{		
		Object obj=TagUtil.getInstance().lookup(pageContext, name, null);		
		if(obj==null){
			 JspException e = new JspException("iterate.collection is null");	         
	         throw e;
		}
		if(obj instanceof Collection){				
			iterator = ((Collection)obj).iterator();		
		}else if(obj instanceof Iterator){
			iterator = (Iterator) obj;			
		} else if (obj instanceof Map) {
            iterator = ((Map) obj).entrySet().iterator();
        }
		if(index>0)
			index=0;
		
		//存储第一个对象,如果iterator为空,就跑过该标签本体内容
		if(iterator.hasNext()){
			Object element=iterator.next();			
			if(element==null)
				ValueStack.vs.remove(id);
			else
				ValueStack.vs.put(id, element);		
		    index++;
		    if(index%2==0)
				ValueStack.vs.put(status, "odd");
			else
				ValueStack.vs.put(status, "even");
			return (EVAL_BODY_AGAIN);
				
		}else
			return (SKIP_BODY);		
	}
	public int doAfterBody() throws JspException {
		//输出集合主体内容
        if (bodyContent != null) {        	
            TagUtil.getInstance().writePrevious(pageContext, bodyContent.getString());
            bodyContent.clearBody();
        }
        if(iterator.hasNext()){
			Object element=iterator.next();			
			if(element==null)
				ValueStack.vs.remove(id);
			else
				ValueStack.vs.put(id, element);			
			index++;
			if(index%2==0)
				ValueStack.vs.put(status, "odd");
			else
				ValueStack.vs.put(status, "even");
			return (EVAL_BODY_AGAIN);				
		}else
			return (SKIP_BODY);		
	}
	public int doEndTag()throws JspException{
		  return (EVAL_PAGE);
	}	
	
	public void release(){
		super.release();
		iterator=null;
		id=null;
		name=null;
		status=null;
		index=0;
	}
}
