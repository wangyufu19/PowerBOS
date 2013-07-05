package com.framework.view;
import java.io.IOException;

import org.jdom.Element;

import com.framework.common.servlet.http.RequestHash;
import com.framework.common.util.SysConstants;
import com.framework.config.XMLData;
import com.framework.view.form.BaseForm;
import com.framework.view.form.FormPanel;
import com.framework.view.form.FormScript;
import com.framework.view.form.SearchPanel;
import com.framework.view.form.property.HiddenProperty;
import com.framework.view.grid.GridPanel;
import com.framework.view.util.DataFormat;
import com.framework.view.util.StringFormat;
import com.framework.view.util.WidgetException;
import com.powerbosframework.log.LogFactory;
/**
 * 视图组件类
 * @author wangyf
 * @version 1.0
 */
public class Viewport {	
	private RequestHash reh;	
	private HiddenProperty hiddenProperty;
	private String script;

	public Viewport(RequestHash reh){
		this.reh=reh;
		hiddenProperty=new HiddenProperty(reh);
		if("true".equals(SysConstants.runtime_mode)){
			XMLData XMLData=new XMLData();
			XMLData.clear();
			XMLData.load();			
		}			
		if(!XMLData.getXMLData().containsKey(reh.get("CODE"))){
			String altMsg="对不起，代码:<br><b><font color=red>"+reh.get("CODE")+"</font></b><br>不存在，请联系管理员！";			
			try {				
				reh.getResponse().sendRedirect(reh.getRequestContextPath()+SysConstants.alert+"?msg="+DataFormat.getURLEncode(altMsg));
			} catch (IOException e) {				
				e.printStackTrace();
			}				
		}	
		reh.getSessionHash().put("FORM", reh.get("CODE")+"_FORM");
		reh.getSessionHash().put("PATH", reh.getReqeust().getContextPath());
		reh.getSessionHash().put("DYNPAGE", SysConstants.dynamic_page);
		reh.getSessionHash().put(SysConstants.show_type, reh.get("SHOW_TYPE"));		
	}
	public String getLocal() {
		StringBuilder buf=new StringBuilder();
		buf.append("<div class=\"title\"><span class=\"titletxt\">您现在的位置：</span>\n")
		   .append("<ul>\n")
		   .append("<li class=\"root\">首页</li>\n");	
		Element codeE=(Element)XMLData.getXMLData().get(reh.get("CODE"));			
		if(codeE==null) return buf.toString();
		
		String[] arr=StringFormat.split(StringFormat.replaceNull(codeE.getAttributeValue("pageLocal")), '|');
		if(arr!=null){
			for(int i=0;i<arr.length;i++){
				buf.append("<li>")
				   .append(arr[i])
				   .append("</li>\n");
			}		
		}
		buf.append("</ul>\n")
		   .append("</div>\n");
		return StringFormat.replaceNull(buf.toString());
	}	
	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}
	public String render(){
		StringBuilder buf=new StringBuilder();
		String code=reh.get("CODE");					
		Element codeE=(Element)XMLData.getXMLData().get(code);				
		if(codeE==null) return buf.toString();		
		hiddenProperty.addFilterProperty("pid", reh.get("pid"));
		hiddenProperty.addHiddenProperty("contextPath", reh.getReqeust().getContextPath());
		hiddenProperty.addHiddenProperty("xmlResource", StringFormat.replaceNull(XMLData.getXMLResources().get(code)));
		//渲染表单元素
		BaseForm baseForm=new BaseForm();
		baseForm.setName(StringFormat.replaceNull(reh.getSessionHash().get("FORM")));
		baseForm.setAction(StringFormat.replaceNull(reh.getSessionHash().get("DYNPAGE")));
		baseForm.setMimeType(StringFormat.replaceNull(codeE.getAttributeValue("mimeType")));
		buf.append("<div id=\"maincontent_inner\">\n");
		buf.append(this.getLocal());
		buf.append("<div class=\"form\">\n");
		buf.append(baseForm.doStart());					
		try {			
			//渲染表单面板		
			buf.append(this.loadFormPanel(codeE));
			//渲染查询面板
			buf.append(this.loadSearchPanel(codeE));
			//渲染网格面板			
			buf.append(this.loadGridPanel(codeE));
		} catch (WidgetException e) {					
			LogFactory.getInstance().addLogFile(e);
			e.printStackTrace();
		}		
		//渲染表单全局隐藏元素
		buf.append(hiddenProperty.render());
		buf.append(baseForm.doEnd());	
		//渲染表单脚本元素		
		FormScript formScript=new FormScript(reh);
		Element scriptE=codeE.getChild("SCRIPT");				
		buf.append(formScript.render(scriptE));
		buf.append("</div>\n");
		buf.append("</div>\n");
		return buf.toString();
	}
	public String loadFormPanel(Element codeE) throws WidgetException{
		String loadFormWidget="false";		
		if(codeE==null) return "";		
		loadFormWidget=StringFormat.replaceNull(codeE.getAttributeValue("loadFormWidget"));
		if("true".equals(loadFormWidget)){
			FormPanel formPanel=new FormPanel(reh);
			Element formE=codeE.getChild("FORM");
			if(formE==null) return "";
			formPanel.setTitle(StringFormat.replaceNull(formE.getAttributeValue("formTitle")));	
			formPanel.setLoadDataSetStyle(StringFormat.replaceNull(formE.getAttributeValue("loadDataSetStyle")));
			formPanel.setLoadColumnSetStyle(StringFormat.replaceNull(formE.getAttributeValue("loadColumnSetStyle")));
			formPanel.load(formE);
			hiddenProperty.addFilterProperty(formPanel.getColumnModels());
			return formPanel.render();
		}		
		return "";
	}	
	public String loadSearchPanel(Element codeE) throws WidgetException{
		String loadSearchWidget="false";	
		if(codeE==null) return "";		
		loadSearchWidget=StringFormat.replaceNull(codeE.getAttributeValue("loadSearchWidget"));
		if("true".equals(loadSearchWidget)){
			SearchPanel searchPanel=new SearchPanel(reh);
			Element searchE=codeE.getChild("SEARCH");
			if(searchE==null) return "";
			searchPanel.setTitle(StringFormat.replaceNull(searchE.getAttributeValue("searchTitle")));
			searchPanel.setLoadColumnSetStyle(StringFormat.replaceNull(searchE.getAttributeValue("loadColumnSetStyle")));
			searchPanel.load(searchE);
			hiddenProperty.addFilterProperty(searchPanel.getColumnModels());	
			return searchPanel.render();
		}
		return "";
	}	
	public String loadGridPanel(Element codeE) throws WidgetException{
		String loadGridWidget="false";
		if(codeE==null) return "";			
		loadGridWidget=StringFormat.replaceNull(codeE.getAttributeValue("loadGridWidget"));
	    if("true".equals(loadGridWidget)){
	    	GridPanel gridPanel=new GridPanel(reh);			
			Element gridE=codeE.getChild("GRID");		
			if(gridE==null) return "";
			gridPanel.setTitle(StringFormat.replaceNull(gridE.getAttributeValue("gridTitle")));
			gridPanel.setLoadDataSetStyle(StringFormat.replaceNull(gridE.getAttributeValue("loadDataSetStyle")));
			gridPanel.setLoadLinkToolbar(StringFormat.replaceNull(gridE.getAttributeValue("loadLinkToolbar")));
			gridPanel.setLoadHandleToolbar(StringFormat.replaceNull(gridE.getAttributeValue("loadHandleToolbar")));	
			gridPanel.setLoadPageToolbar(StringFormat.replaceNull(gridE.getAttributeValue("loadPageToolbar")));
			gridPanel.setPagingFetchSize(StringFormat.replaceNull(gridE.getAttributeValue("pageFetchSize")));
			gridPanel.load(gridE);
			hiddenProperty.addFilterProperty(gridPanel.getColumnModels());
			return gridPanel.render();
	    }
		return "";
	}		
}
