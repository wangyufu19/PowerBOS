package com.application.console.biz;
import java.io.File;

import java.util.List;
import java.util.ArrayList;

import org.jdom.Element;

import com.application.console.model.PageModule;
import com.framework.common.base.BaseBiz;
import com.framework.common.util.SysConstants;
import com.framework.common.xmldoc.DocFactory;
import com.framework.common.xmldoc.DocObject;
import com.framework.view.util.StringFormat;

/**
 * 页面模块业务类
 * @author wangyf
 * @version 1.0
 */
public class PageModuleBiz extends BaseBiz{
	
	public PageModuleBiz(){
		
	}
	
	public List getPageModuleList(){
		List pageModules=new ArrayList();
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"config"+File.separator+"module"+File.separator+"module_configuration.xml";
		File file=new File(fullPath);
		if(!file.exists()) return pageModules;		
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element rootE=doc.getDocRootElement();
		if(rootE==null) return pageModules;	
		List list=rootE.getChildren("module");
		if(list==null) return pageModules;
		for(int j=0;j<list.size();j++){
			PageModule pageModule=new PageModule();
			Element moduleE=(Element)list.get(j);
			pageModule.setKey(StringFormat.replaceNull(moduleE.getAttributeValue("EID")));
			pageModule.setModuleName(StringFormat.replaceNull(moduleE.getAttributeValue("name")));
			pageModule.setResourceName(StringFormat.replaceNull(moduleE.getAttributeValue("resource")));
			pageModules.add(pageModule);
		}				
		return pageModules;
	}
	public PageModule getPageModule(String key){
		PageModule pageModule=new PageModule();
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"config"+File.separator+"module"+File.separator+"module_configuration.xml";
		File file=new File(fullPath);
		if(!file.exists()) return pageModule;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element moduleE=doc.getDocElement(key);
		if(moduleE==null) return pageModule;
		pageModule.setKey(key);
		pageModule.setModuleName(StringFormat.replaceNull(moduleE.getAttributeValue("name")));
		pageModule.setResourceName(StringFormat.replaceNull(moduleE.getAttributeValue("resource")));
		return pageModule;
	}
	public void addPageModule(PageModule vo){
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"config"+File.separator+"module"+File.separator+"module_configuration.xml";
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element rootE=doc.getDocRootElement();
		if(rootE==null) return;	
		Element moduleE=new Element("module");
		
		moduleE.setAttribute("name", vo.getModuleName());
		moduleE.setAttribute("resource", vo.getResourceName());
		doc.addDocElement(rootE, moduleE);
		doc.saveDoc();
	}
	public void updatePageModule(PageModule vo){
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"config"+File.separator+"module"+File.separator+"module_configuration.xml";
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element moduleE=doc.getDocElement(vo.getKey());
		if(moduleE==null) return;
		if("".equals(vo.getModuleName())||"".equals(vo.getResourceName())) return;
		moduleE.setAttribute("name", StringFormat.replaceNull(vo.getModuleName()));
		moduleE.setAttribute("resource", StringFormat.replaceNull(vo.getResourceName()));
		doc.saveDoc();
	}
	public void deletePageModules(String[] keyArr){
		if(keyArr==null) return;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"config"+File.separator+"module"+File.separator+"module_configuration.xml";
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element rootE=doc.getDocRootElement();
		if(rootE==null) return;	
		for(int i=0;i<keyArr.length;i++){
			doc.deleteDocElement(rootE, keyArr[i]);
		}
		doc.saveDoc();
	}
}
