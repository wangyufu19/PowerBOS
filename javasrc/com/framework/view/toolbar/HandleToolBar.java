package com.framework.view.toolbar;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;

import com.framework.common.util.XMLUtil;
import com.framework.view.util.StringFormat;

/**
 * 操作工具条组件类
 * @author Administrator
 *
 */
public class HandleToolBar {
	private String handleToolbarDoc;
	
	public HandleToolBar(String handleToolbarDoc){
		this.handleToolbarDoc=handleToolbarDoc;
	}
	public String render(){		
		StringBuilder buf=new StringBuilder();
		if(handleToolbarDoc==null) return "网格组件操作工具条文档格式出错!";
		XMLUtil XMLUtil=new XMLUtil();
		Document doc=null;						
		doc=XMLUtil.parse(handleToolbarDoc);
		if(doc==null) return "网格组件操作工具条格式出错!";
		Element rootE=doc.getRootElement();
		if(rootE==null) return "网格组件操作工具条文档格式出错!";
		List list=null;
		list=rootE.getChildren("toolbar");
		if(list==null) return "网格组件操作工具条文档格式出错!";
		if(list.size()<1) return "网格组件操作工具条文档格式出错!";	
		for(int i=0;i<list.size();i++){
			Element toolbarE=(Element)list.get(i);
			buf.append("<a ");      	
	  		buf.append("value="+"\""+StringFormat.replaceNull(toolbarE.getAttributeValue("name"))+"\" ");
	  		buf.append("href="+"\""+StringFormat.replaceNull(toolbarE.getAttributeValue("href"))+"\">");     	  	
	  		buf.append(StringFormat.replaceNull(toolbarE.getAttributeValue("value")));  		
	  		buf.append("</a>");
	  		buf.append("&nbsp;"); 	
		}			   
		return buf.toString();
	}
}
