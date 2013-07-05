package com.framework.view.form;
import com.framework.common.util.SysConstants;
import com.framework.view.form.BaseTag;
import com.framework.view.form.property.EventProperty;
import com.framework.view.form.property.ValidateProperty;
import com.framework.view.util.StringFormat;
/**
 * 日期选择元素类
 * @author wangyf
 * @version 1.0
 */
public class DateTag extends BaseTag{
	private ValidateProperty validateProperty;	
	private EventProperty eventProperty;
	private String maxLength;
	private String classStyle;
	private boolean readonly=false;
	
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
	public boolean isReadonly() {
		return readonly;
	}
	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
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
		if(!"".equals(StringFormat.replaceNull(classStyle)))		
			buf.append("class="+"\""+StringFormat.replaceNull(classStyle)+"\" ");
		if(readonly)
			buf.append("readonly=\"readonly\" ");	
		if(validateProperty!=null)
			buf.append(validateProperty.render());
		if(eventProperty!=null)
			buf.append(eventProperty.render());
		buf.append("/>");	
		buf.append(this.renderImg());
		return buf.toString();
	}
	public String renderImg(){
		StringBuffer buf=new StringBuffer();
		String imageNPath=SysConstants.context_path+"/lib/default/images/bt_calendar_n.gif";
		String imageOPath=SysConstants.context_path+"/lib/default/images/bt_calendar_o.gif";
		//插入日期图片		
		buf.append("<img ");
		buf.append("src=\""+imageNPath+"\" ");
		buf.append("name=\""+"bt_calendar\" ");
		buf.append("align=\""+"absmiddle\" ");
		buf.append("border=\""+"0\" ");
		buf.append("style=\""+"cursor:hand; \" ");
		buf.append("onMouseOut=\""+"MM_swapImgRestore()\" ");
		buf.append("onMouseOver=\""+"MM_swapImage('bt_calendar','','"+imageOPath+"',1)\" ");
		buf.append("onclick=\""+"return showCalendar('"+StringFormat.replaceNull(this.getName())+"','%Y-%m-%d','24');\"");	
		buf.append(">");
		return buf.toString();
	}
}
