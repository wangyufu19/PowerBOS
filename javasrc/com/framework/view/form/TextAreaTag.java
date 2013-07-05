package com.framework.view.form;
import com.framework.view.form.BaseTag;
import com.framework.view.form.property.ValidateProperty;
import com.framework.view.util.StringFormat;
/**
 * 多行文本框元素类
 * @author wangyf
 * @version 1.0
 */
public class TextAreaTag extends BaseTag{
	private ValidateProperty validateProperty;	
	private String rows;
	private String cols;
	private boolean readonly=false;
	
	public ValidateProperty getValidateProperty() {
		return validateProperty;
	}
	public void setValidateProperty(ValidateProperty validateProperty) {
		this.validateProperty = validateProperty;
	}
	public boolean isReadonly() {
		return readonly;
	}
	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getCols() {
		return cols;
	}
	public void setCols(String cols) {
		this.cols = cols;
	}
	public String render(){
		StringBuilder buf=new StringBuilder();
		buf.append("<textarea ");
		buf.append("name="+"\""+StringFormat.replaceNull(this.getName())+"\" ");
		buf.append("id="+"\""+StringFormat.replaceNull(this.getId())+"\" ");
		buf.append("rows="+"\""+StringFormat.replaceNull(this.getRows())+"\" ");		
		if(readonly)
			buf.append("readonly=\"readonly\" ");	
		if(validateProperty!=null)
			buf.append(validateProperty.render());	
		buf.append(">");	
		buf.append(StringFormat.replaceNull(this.getValue()));	
		buf.append("</textarea>");
		buf.append("&nbsp;"+StringFormat.replaceNull(this.getExtend()));
		return buf.toString();
	}
}
