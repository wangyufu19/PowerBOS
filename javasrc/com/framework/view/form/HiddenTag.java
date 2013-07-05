package com.framework.view.form;
import com.framework.view.form.BaseTag;
import com.framework.view.util.StringFormat;
/**
 * 隐藏框元素类
 * @author wangyf
 * @version 1.0
 */
public class HiddenTag extends BaseTag{		
	
	public String render(){
		StringBuilder buf=new StringBuilder();
		buf.append("<input ");
		buf.append("name="+"\""+StringFormat.replaceNull(this.getName())+"\" ");
		buf.append("id="+"\""+StringFormat.replaceNull(this.getId())+"\" ");
		buf.append("type="+"\"hidden\" ");
		buf.append("value="+"\""+StringFormat.replaceNull(this.getValue())+"\"");	
		buf.append("/>");
		return buf.toString();
	}
	public static void main(String[] args){
		
	}
}
