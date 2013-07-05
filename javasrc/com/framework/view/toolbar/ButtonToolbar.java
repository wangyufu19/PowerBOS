package com.framework.view.toolbar;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;

import com.framework.common.util.XMLUtil;
import com.framework.view.util.StringFormat;

/**
 * 按钮工具条组件类
 * @author wangyf
 * @version 1.0
 */
public class ButtonToolbar {
	private String toolbarDoc;
	
	public ButtonToolbar(String toolbarDoc){
		this.toolbarDoc=toolbarDoc;
	}
	public String render(){
		StringBuilder buf=new StringBuilder();
		if(toolbarDoc==null) return "按钮工具条文档格式出错!";
		XMLUtil XMLUtil=new XMLUtil();
		Document doc=null;				
		doc=XMLUtil.parse(toolbarDoc);
		if(doc==null) return "按钮工具条文档格式出错!";
		Element rootE=doc.getRootElement();
		if(rootE==null) return "按钮工具条文档格式出错!";
		List list=null;
		list=rootE.getChildren("toolbar");
		if(list==null) return "按钮工具条文档格式出错!";
		if(list.size()<1) return buf.toString();
		buf.append("<table cellspacing=0 cellpadding=0 width=\"100%\" align=center border=0 class=\"btnbar\">")
		   .append("<tr>\n")
		   .append("<td class=\"btnbarL\"></td>\n")
		   .append("<td class=\"btnbarC\">");
		for(int i=0;i<list.size();i++){
			Element toolbarE=(Element)list.get(i);
	      	buf.append("<input ");
			buf.append("type="+"\"button\" ");
			buf.append("onClick="+"\""+StringFormat.replaceNull(toolbarE.getAttributeValue("onclick"))+"\" ");
			buf.append("class="+"\"button\" ");
			buf.append("value="+"\""+StringFormat.replaceNull(toolbarE.getAttributeValue("name"))+"\" ");
			buf.append(">");
			buf.append("&nbsp;");	
		}					  
		buf.append("</td>\n")
		   .append("<td class=\"btnbarR\"></td>\n")
		   .append("</tr>\n")
		   .append("</table>\n");
		return buf.toString();
	}
}
