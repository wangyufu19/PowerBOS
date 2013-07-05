package com.framework.view.form;
import com.framework.view.form.BaseTag;
import com.framework.view.util.StringFormat;
/**
 * 复选框元素类
 * @author wangyf
 * @version 1.0
 */
public class CheckBoxTag extends BaseTag{
	private String maxLength;
	private String classStyle;
	
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
		buf.append("type="+"\"checkbox\" ");
		buf.append("value="+"\""+StringFormat.replaceNull(this.getValue())+"\" ");	
		if(!"".equals(StringFormat.replaceNull(this.getWidth())))
			buf.append("size="+"\""+StringFormat.replaceNull(this.getWidth())+"\" ");
		if(!"".equals(StringFormat.replaceNull(this.getMaxLength())))
			buf.append("maxlength="+"\""+StringFormat.replaceNull(this.getMaxLength())+"\" ");
		if(!"".equals(StringFormat.replaceNull(classStyle)))		
			buf.append("class="+"\""+StringFormat.replaceNull(classStyle)+"\" ");		
		buf.append("/>");		
		return buf.toString();
	}
}
