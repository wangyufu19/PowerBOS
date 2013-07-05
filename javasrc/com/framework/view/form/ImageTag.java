package com.framework.view.form;
import com.framework.view.form.BaseTag;
import com.framework.view.util.StringFormat;
/**
 * 图片元素类
 * @author wangyf
 * @version 1.0
 */
public class ImageTag extends BaseTag{
	private String alt;
	
	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}
	public String render(){
		StringBuilder buf=new StringBuilder();
		buf.append("<img ");
		buf.append("name="+"\""+StringFormat.replaceNull(this.getName())+"\" ");
		buf.append("id="+"\""+StringFormat.replaceNull(this.getId())+"\" ");
		buf.append("src="+"\""+StringFormat.replaceNull(this.getValue())+"\" ");	
		if(!"".equals(StringFormat.replaceNull(this.getWidth())))
			buf.append("width="+"\""+StringFormat.replaceNull(this.getWidth())+"\" ");
		if(!"".equals(StringFormat.replaceNull(this.getHeight())))
			buf.append("height="+"\""+StringFormat.replaceNull(this.getHeight())+"\" ");	
		if(!"".equals(StringFormat.replaceNull(this.getAlt())))
			buf.append("alt="+"\""+StringFormat.replaceNull(this.getAlt())+"\" ");	
		buf.append("/>");		
		return buf.toString();
	}
}
