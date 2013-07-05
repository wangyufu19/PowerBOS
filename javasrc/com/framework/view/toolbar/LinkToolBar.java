package com.framework.view.toolbar;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;

import com.framework.common.util.XMLUtil;
import com.framework.view.util.StringFormat;
/**
 * 链接工具条组件类
 * @author wangyf
 * @version 1.0
 */
public class LinkToolBar {
	private String toolbarDoc;
	
	public LinkToolBar(String toolbarDoc){
		this.toolbarDoc=toolbarDoc;
	}
	public String render(){		
		StringBuilder buf=new StringBuilder();
		if(toolbarDoc==null) return "链接工具条文档格式出错!";
		XMLUtil XMLUtil=new XMLUtil();
		Document doc=null;					
		doc=XMLUtil.parse(toolbarDoc);
		if(doc==null) return "链接工具条文档格式出错!";
		Element rootE=doc.getRootElement();
		if(rootE==null) return "链接工具条文档格式出错!";
		List list=null;
		list=rootE.getChildren("toolbar");
		if(list==null) return "链接工具条文档格式出错!";
		if(list.size()<1) return buf.toString();
		buf.append("<table class=\"operation\" cellSpacing=0 cellPadding=0 width=\"100%\" align=center border=0>\n")
		   .append("<tbody>\n")
		   .append("<tr>\n")
		   .append("<td class=\"operationL\"></td>\n")
		   .append("<td class=\"operationC\" align=center>&nbsp;</td>\n")
		   .append("<td class=\"operationR\" width=\"20%\" align=right>\n")
		   .append("<ul>\n");
		for(int i=0;i<list.size();i++){
			Element toolbarE=(Element)list.get(i);
		  	buf.append("<li>\n");
      		buf.append("<a ");      	
      		buf.append("value="+"\""+StringFormat.replaceNull(toolbarE.getAttributeValue("name"))+"\" ");	  	
	  		buf.append("href="+"\""+StringFormat.replaceNull(toolbarE.getAttributeValue("href"))+"\">");   
      		buf.append("<span>");
      		buf.append(StringFormat.replaceNull(toolbarE.getAttributeValue("name")));
      		buf.append("</span>");
      		buf.append("</a>\n");
      		buf.append("</li>\n");	
//      		buf.append("&nbsp;");      	
		}		
	    buf.append("</ul>\n")
	       .append("</td>\n")
		   .append("</tr>\n")
		   .append("</tbody>\n")
		   .append("</table>\n");	   
		return buf.toString();
	}
}
