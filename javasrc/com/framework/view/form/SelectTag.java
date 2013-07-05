package com.framework.view.form;
import java.util.List;
import java.util.Map;

import com.framework.view.form.BaseTag;
import com.framework.view.form.property.EventProperty;
import com.framework.view.form.property.ValidateProperty;
import com.framework.view.util.StringFormat;
/**
 * 下拉选择框元素类
 * @author wangyf
 * @version 1.0
 */
public class SelectTag extends BaseTag{
	private List dataset;
	private ValidateProperty validateProperty;	
	private EventProperty eventProperty;
	private String maxLength;
	private String classStyle;
	
	public List getDataset() {
		return dataset;
	}
	public void setDataset(List dataset) {
		this.dataset = dataset;
	}
	public ValidateProperty getValidateProperty() {
		return validateProperty;
	}
	public void setValidateProperty(ValidateProperty validateProperty) {
		this.validateProperty = validateProperty;
	}
	public EventProperty getEventProperty() {
		return eventProperty;
	}
	public void setEventProperty(EventProperty eventProperty) {
		this.eventProperty = eventProperty;
	}
	public String getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}
	public String getClassStyle() {
		return classStyle;
	}
	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}	
	public String render(){
		StringBuilder buf=new StringBuilder();
		buf.append("<select ");
		buf.append("name="+"\""+StringFormat.replaceNull(this.getName())+"\" ");
		buf.append("id="+"\""+StringFormat.replaceNull(this.getId())+"\" ");		
		if(!"".equals(StringFormat.replaceNull(this.getWidth()))) 
			buf.append("style="+"\""+"width:"+StringFormat.replaceNull(this.getWidth())+"px"+"\" ");
		if(validateProperty!=null)
			buf.append(validateProperty.render());
		if(eventProperty!=null)
			buf.append(eventProperty.render());
		buf.append(">\n");
		buf.append("<option ");
		buf.append("value="+"\""+"\">");
		buf.append("请选择");
		buf.append("</option>\n");			
		if(dataset==null) return buf.toString();
		for(int i=0;i<dataset.size();i++){
			Map type=(Map)dataset.get(i);
			String key=StringFormat.replaceNull(String.valueOf((type.keySet().toArray())[0]));
			String value=StringFormat.replaceNull(String.valueOf(type.get(key)));		
			buf.append("<option ");
			if(StringFormat.replaceNull(StringFormat.replaceNull(this.getValue())).equals(key))
				buf.append("value="+"\""+key+"\" selected>");
			else
				buf.append("value="+"\""+key+"\">");		
			buf.append(value);
			buf.append("</option>\n");					
		}
		buf.append("</select>\n");	
		buf.append("&nbsp;"+StringFormat.replaceNull(this.getExtend()));
		return buf.toString();
	}
}
