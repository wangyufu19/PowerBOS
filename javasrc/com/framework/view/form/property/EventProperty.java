package com.framework.view.form.property;
import com.framework.view.util.StringFormat;
/**
 * 表单事件属性类
 * @author wangyf
 * @version 1.0
 */
public class EventProperty {
	private String onclick;
	private String onblur;	
	private String onchange;

	public String getOnclick() {
		return onclick;
	}
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
	public String getOnblur() {
		return onblur;
	}
	public void setOnblur(String onblur) {
		this.onblur = onblur;
	}		
	public String getOnchange() {
		return onchange;
	}
	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}
	public String render(){
		StringBuilder buf=new StringBuilder();
		if(!"".equals(StringFormat.replaceNull(onclick)))
			buf.append("onclick="+"\""+StringFormat.replaceNull(onclick)+"\" ");
		if(!"".equals(StringFormat.replaceNull(onblur)))
			buf.append("onblur="+"\""+StringFormat.replaceNull(onblur)+"\" ");
		if(!"".equals(StringFormat.replaceNull(onchange)))
			buf.append("onchange="+"\""+StringFormat.replaceNull(onchange)+"\"");
		return buf.toString();
	}
}
