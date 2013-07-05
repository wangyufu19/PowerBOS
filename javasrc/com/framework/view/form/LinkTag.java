package com.framework.view.form;
import com.framework.view.form.BaseTag;
import com.framework.view.form.property.EventProperty;
import com.framework.view.util.StringFormat;
/**
 * 链接元素类
 * @author wangyf
 * @version 1.0
 */
public class LinkTag extends BaseTag{
	private EventProperty eventProperty;
	private String href;

	public EventProperty getEventProperty() {
		return eventProperty;
	}

	public void setEventProperty(EventProperty eventProperty) {
		this.eventProperty = eventProperty;
	}
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	public String render(){
		StringBuilder buf=new StringBuilder();		
		if(eventProperty!=null)
			buf.append("<a href=\""+StringFormat.replaceNull(href)+"\""+eventProperty.render()+">"+StringFormat.replaceNull(this.getValue())+"</a>&nbsp;");
		else
			buf.append("<a href=\""+StringFormat.replaceNull(href)+"\">"+StringFormat.replaceNull(this.getValue())+"</a>&nbsp;");
		return buf.toString();
	}
}
