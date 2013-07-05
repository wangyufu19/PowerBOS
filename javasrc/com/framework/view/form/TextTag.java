package com.framework.view.form;
import com.framework.view.form.BaseTag;
import com.framework.view.form.property.EventProperty;
import com.framework.view.form.property.ValidateProperty;
import com.framework.view.util.StringFormat;
/**
 * 文本输入框元素类
 * @author wangyf
 * @version 1.0
 */
public class TextTag extends BaseTag{	
	private ValidateProperty validateProperty;	
	private EventProperty eventProperty;
	
	
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
	public String render(){
		StringBuilder buf=new StringBuilder();
		buf.append("<input ");
		buf.append("name="+"\""+StringFormat.replaceNull(this.getName())+"\" ");
		buf.append("id="+"\""+StringFormat.replaceNull(this.getId())+"\" ");
		buf.append("type="+"\"text\" ");
		buf.append("value="+"\""+StringFormat.replaceNull(this.getValue())+"\" ");	
		if(!"".equals(StringFormat.replaceNull(this.getWidth())))
			buf.append("size="+"\""+StringFormat.replaceNull(this.getWidth())+"\" ");
		if(!"".equals(StringFormat.replaceNull(this.getMaxLength())))
			buf.append("maxlength="+"\""+StringFormat.replaceNull(this.getMaxLength())+"\" ");
		if(!"".equals(StringFormat.replaceNull(this.getClassStyle())))		
			buf.append("class="+"\""+StringFormat.replaceNull(this.getClassStyle())+"\" ");
		if("true".equals(this.getReadonly()))
			buf.append("readonly=\"readonly\" ");	
		if(validateProperty!=null)
			buf.append(validateProperty.render());
		if(eventProperty!=null)
			buf.append(eventProperty.render());
		buf.append("/>");				
		buf.append(StringFormat.replaceNull(this.getExtend()));
		return buf.toString();
	}
}
