package com.framework.view.form.property;
import com.framework.view.util.StringFormat;
/**
 * 表单验证属性类
 * @author wangyf
 * @version 1.0
 */
public class ValidateProperty {
	private String dataType;	
	private String maxLength;
	private String isNull;
	private String alterMsg;
	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}
	public String getIsNull() {
		return isNull;
	}
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	public String getAlterMsg() {
		return alterMsg;
	}
	public void setAlterMsg(String alterMsg) {
		this.alterMsg = alterMsg;
	}
	public String render(){
		StringBuilder buf=new StringBuilder();	
		if("true".equals(isNull))
			isNull="nn";
		else
			isNull="null";
		if(!"".equals(StringFormat.replaceNull(dataType)))
			buf.append("check=\""+dataType+"("+maxLength+"),"+isNull+","+StringFormat.replaceNull(alterMsg)+"\" ");
		return buf.toString();
	}
}
