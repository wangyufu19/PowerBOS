package com.framework.view.form;
import com.framework.view.form.BaseTag;
import com.framework.view.form.property.EventProperty;
import com.framework.view.util.StringFormat;
/**
 * 单选框元素类
 * @author wangyf
 * @version 1.0
 */
public class RadioTag extends BaseTag{
	private EventProperty eventProperty;
	private String maxLength;
	private String classStyle;
	
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
		buf.append("<input ");
		buf.append("name="+"\""+StringFormat.replaceNull(this.getName())+"\" ");
		buf.append("id="+"\""+StringFormat.replaceNull(this.getId())+"\" ");
		buf.append("type="+"\"radio\" ");
		buf.append("value="+"\""+StringFormat.replaceNull(this.getValue())+"\" ");	
		if(!"".equals(StringFormat.replaceNull(this.getWidth())))
			buf.append("size="+"\""+StringFormat.replaceNull(this.getWidth())+"\" ");
		if(!"".equals(StringFormat.replaceNull(this.getMaxLength())))
			buf.append("maxlength="+"\""+StringFormat.replaceNull(this.getMaxLength())+"\" ");
		if(!"".equals(StringFormat.replaceNull(classStyle)))		
			buf.append("class="+"\""+StringFormat.replaceNull(classStyle)+"\" ");	
		buf.append(eventProperty.render());		
		buf.append("/>");		
		return buf.toString();
	}
}
