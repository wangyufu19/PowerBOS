package com.application.console.biz;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import org.jdom.Element;

import com.application.console.model.PageModule;
import com.application.console.model.PageWidget;
import com.framework.common.base.BaseBiz;
import com.framework.common.util.SysConstants;
import com.framework.common.xmldoc.DocFactory;
import com.framework.common.xmldoc.DocObject;
import com.framework.view.util.StringFormat;

/**
 * 页面定制业务类
 * @author wangyf
 * @version 1.0
 */
public class PageCustomizeBiz extends BaseBiz{
	
	
	public String getPageModuleDocScript(){
		StringBuilder out=new StringBuilder();
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"config"+File.separator+"module_configuration.xml";
		File file=new File(fullPath);
		if(!file.exists()) return out.toString();		
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element rootE=doc.getDocRootElement();
		if(rootE==null) return out.toString();
		List list=rootE.getChildren("subSys");
		if(list==null) return out.toString();
		for(int i=0;i<list.size();i++){
			Element subSysE=(Element)list.get(i);
			out.append("var "+StringFormat.replaceNull(subSysE.getAttributeValue("code"))+"=new WebFXTreeItem("+StringFormat.replaceNull(subSysE.getAttributeValue("name"))+",\"\",pageCustomize,\"\",\"\",\"mainboard\");");			
			List list1=subSysE.getChildren("module");
			if(list1!=null){
				for(int j=0;j<list1.size();j++){
					Element firstLevelModule=(Element)list1.get(i);
					
				}
			}
		}
		return out.toString();		
	}

	
	
	public List getPageWidgetList(String resourceName){
		List pageWidgets=new ArrayList();
		List list=null;
		
		if("".equals(resourceName)) return pageWidgets;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return pageWidgets;
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element rootE=doc.getDocRootElement();
		if(rootE==null) return pageWidgets;
		list=rootE.getChildren();
		if(list==null) return pageWidgets;
		for(int i=0;i<list.size();i++){
			Element widgetE=(Element)list.get(i);
			PageWidget pageWidget=new PageWidget();
			pageWidget.setKey(StringFormat.replaceNull(widgetE.getAttributeValue("EID")));
			pageWidget.setPageCode(StringFormat.replaceNull(widgetE.getName()));
			pageWidget.setPageLocal(StringFormat.replaceNull(widgetE.getAttributeValue("pageLocal")));
			pageWidget.setMimeType(StringFormat.replaceNull(widgetE.getAttributeValue("mimeType")));
			pageWidget.setLoadSearchWidget(StringFormat.replaceNull(widgetE.getAttributeValue("loadSearchWidget")));
			pageWidget.setLoadGridWidget(StringFormat.replaceNull(widgetE.getAttributeValue("loadGridWidget")));
			pageWidget.setLoadFormWidget(StringFormat.replaceNull(widgetE.getAttributeValue("loadFormWidget")));
			pageWidget.setLoadScriptWidget(StringFormat.replaceNull(widgetE.getAttributeValue("loadScriptWidget")));
			pageWidgets.add(pageWidget);
		}
		return pageWidgets;
	}
	public PageWidget getPageWidget(String resourceName,String pageKey,String pageCode){
		PageWidget pageWidget=new PageWidget();
		if("".equals(resourceName)||"".equals(pageKey)) return pageWidget;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return pageWidget;
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element pageE=doc.getDocElement(pageKey);
		if(pageE==null) return pageWidget;	
		pageWidget.setKey(pageKey);
		pageWidget.setPageCode(pageCode);
		pageWidget.setPageLocal(StringFormat.replaceNull(pageE.getAttributeValue("pageLocal")));
		pageWidget.setMimeType(StringFormat.replaceNull(pageE.getAttributeValue("mimeType")));
		pageWidget.setLoadSearchWidget(StringFormat.replaceNull(pageE.getAttributeValue("loadSearchWidget")));
		pageWidget.setLoadGridWidget(StringFormat.replaceNull(pageE.getAttributeValue("loadGridWidget")));
		pageWidget.setLoadFormWidget(StringFormat.replaceNull(pageE.getAttributeValue("loadFormWidget")));
		pageWidget.setLoadScriptWidget(StringFormat.replaceNull(pageE.getAttributeValue("loadScriptWidget")));
		return pageWidget;
	}
	public void updatePageWidget(String resourceName,PageWidget pageWidget){
		if("".equals(resourceName)||pageWidget==null) return;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element pageE=doc.getDocElement(StringFormat.replaceNull(pageWidget.getKey()));
		if(pageE==null) return;
		pageE.setAttribute("pageLocal", StringFormat.replaceNull(pageWidget.getPageLocal()));
		pageE.setAttribute("mimeType", StringFormat.replaceNull(pageWidget.getMimeType()));
		pageE.setAttribute("loadSearchWidget", StringFormat.replaceNull(pageWidget.getLoadSearchWidget()));
		pageE.setAttribute("loadGridWidget", StringFormat.replaceNull(pageWidget.getLoadGridWidget()));
		pageE.setAttribute("loadFormWidget", StringFormat.replaceNull(pageWidget.getLoadFormWidget()));
		pageE.setAttribute("loadScriptWidget", StringFormat.replaceNull(pageWidget.getLoadScriptWidget()));
		doc.saveDoc();
	}
	public void addPageWidget(PageWidget vo,String resourceName){
		if(vo==null) return;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;
		DocObject docObject=DocFactory.getInstace().getDocObject(file.getPath());
		Element rootE=docObject.getDocRootElement();
		if(rootE==null) return;
		Element widgetE=new Element(StringFormat.replaceNull(vo.getPageCode()));
		widgetE.setAttribute("pageLocal", StringFormat.replaceNull(vo.getPageLocal()));
		widgetE.setAttribute("mimeType", StringFormat.replaceNull(vo.getMimeType()));
		widgetE.setAttribute("loadSearchWidget", StringFormat.replaceNull(vo.getLoadSearchWidget()));
		widgetE.setAttribute("loadGridWidget", StringFormat.replaceNull(vo.getLoadGridWidget()));
		widgetE.setAttribute("loadFormWidget", StringFormat.replaceNull(vo.getLoadFormWidget()));
		widgetE.setAttribute("loadScriptWidget", StringFormat.replaceNull(vo.getLoadScriptWidget()));
		Element searchE=new Element("SEARCH");
		Element gridE=new Element("GRID");
		Element dataSetE=new Element("DATASET");
		Element statementE=new Element("STATEMENT");
		Element interfaceE=new Element("INTERFACE");
		dataSetE.addContent(statementE);
		dataSetE.addContent(interfaceE);
		Element columnSetE=new Element("COLUMNSET");
		searchE.addContent(columnSetE);
		columnSetE=new Element("COLUMNSET");
		Element actionsE=new Element("ACTIONS");
		gridE.addContent(dataSetE);
		gridE.addContent(columnSetE);
		gridE.addContent(actionsE);
		Element formE=new Element("FORM");
		dataSetE=new Element("DATASET");
		statementE=new Element("STATEMENT");
		interfaceE=new Element("INTERFACE");
		dataSetE.addContent(statementE);
		dataSetE.addContent(interfaceE);
		columnSetE=new Element("COLUMNSET");
		actionsE=new Element("ACTIONS");
		formE.addContent(dataSetE);
		formE.addContent(columnSetE);
		formE.addContent(actionsE);
		Element scriptE=new Element("SCRIPT");
		widgetE.addContent(searchE);
		widgetE.addContent(gridE);
		widgetE.addContent(formE);
		widgetE.addContent(scriptE);
		docObject.addDocElement(rootE, widgetE);
	}
	public void deletePageWidget(String[] idArr,String resourceName){
		if(idArr==null) return;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;
		DocObject docObject=DocFactory.getInstace().getDocObject(file.getPath());
		List list=new ArrayList();
		for(int i=0;i<idArr.length;i++){			
			Element subE=docObject.getDocElement(idArr[i]);
			list.add(subE);
		}		
		docObject.deleteBatchDocElement(docObject.getDocRootElement(), list);		
	}
}
