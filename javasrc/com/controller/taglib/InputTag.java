package com.controller.taglib;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class InputTag extends BodyTagSupport{
	protected String label;
	protected String id;
	protected String property;
    protected String value;
    protected String maxlength;
    protected String size;
    protected String cssClass;
    protected String cssStype;
	
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getCssStype() {
		return cssStype;
	}
	public void setCssStype(String cssStype) {
		this.cssStype = cssStype;
	}
	public InputTag(){   	
    	
    }
	public String getProperty() {
		return property;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public void setProperty(String property) {
		this.property = property;
	}

    public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getMaxlength() {
		return maxlength;
	}
	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
    
	public void release(){
    	super.release();    
    	label=null;
    	property=null;    	
    	value=null;
    	maxlength=null;
    	
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	

}
