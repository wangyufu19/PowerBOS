package com.framework.view.form;
import com.framework.view.util.StringFormat;
/**
 * 表单元素类
 * @author wangyf
 * @version 1.0
 */
public class BaseForm {
	private String name;
	private String action;
	private String mimeType;
	
	public BaseForm(){
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}	
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String doStart(){
		StringBuilder buf=new StringBuilder();
		name=StringFormat.replaceNull(name);
		action=StringFormat.replaceNull(action);
		mimeType=StringFormat.replaceNull(mimeType);
		if("normal".equals(mimeType)){
	    	buf.append("<form ")
			   .append("name=\""+name+"\" ")
			   .append("id=\""+name+"\" ")
			   .append("action=\""+action+"\" ")
			   .append("method=\"post\"")			
			   .append(">\n");
	    }else if("multipart/form-data".equals(mimeType)){
	    	buf.append("<form ")
			   .append("name=\""+name+"\" ")
			   .append("id=\""+name+"\" ")
			   .append("action=\""+action+"\" ")
			   .append("method=\"post\" ")
			   .append("enctype=\"multipart/form-data\"")
			   .append(">\n");
	    }
		return buf.toString();
	}
	public String doEnd(){
		return "</form>\n";
	}
	

}
