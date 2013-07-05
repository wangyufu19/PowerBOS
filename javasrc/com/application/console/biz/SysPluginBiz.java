package com.application.console.biz;

import java.io.File;

import org.jdom.Element;

import com.application.console.model.SysPlugin;
import com.framework.common.util.SysConstants;
import com.framework.common.xmldoc.DocFactory;
import com.framework.common.xmldoc.DocObject;
import com.powerbosframework.util.StringUtil;


/**
 * 系统自定义插件管理类
 * @author youfu.wang
 * @version 1.0
 */
public class SysPluginBiz {
	
	public java.util.List getSysPluginList(){
		java.util.List list=new java.util.ArrayList();
		String configuration=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"config"+File.separator+"plugin_configuration.xml";		
		File file=new File(configuration);
		if(!file.exists()) return list;			
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element rootE=doc.getDocRootElement();		
		if(rootE==null) return list;	
		Element pluginsE=rootE.getChild("plugins");
		if(pluginsE!=null){
			java.util.List plugins=pluginsE.getChildren("plugin");
			if(plugins==null) return list;
			for(int i=0;i<plugins.size();i++){
				Element pluginE=(Element)plugins.get(i);
				SysPlugin sysPlugin=new SysPlugin();
				sysPlugin.setKey(StringUtil.replaceNull(pluginE.getAttributeValue("EID")));				
				sysPlugin.setName(StringUtil.replaceNull(pluginE.getAttributeValue("name")));
				sysPlugin.setClazz(StringUtil.replaceNull(pluginE.getAttributeValue("class")));
				sysPlugin.setDesc(StringUtil.replaceNull(pluginE.getAttributeValue("desc")));
				list.add(sysPlugin);
			}
		}
		return list;
	}
	public SysPlugin getSysPlugin(String key){
		String configuration=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"config"+File.separator+"plugin_configuration.xml";		
		File file=new File(configuration);
		if(!file.exists()) return new SysPlugin();	
		SysPlugin sysPlugin=new SysPlugin();
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element pluginE=doc.getDocElement(key);
		sysPlugin.setKey(StringUtil.replaceNull(pluginE.getAttributeValue("EID")));				
		sysPlugin.setName(StringUtil.replaceNull(pluginE.getAttributeValue("name")));
		sysPlugin.setClazz(StringUtil.replaceNull(pluginE.getAttributeValue("class")));
		sysPlugin.setDesc(StringUtil.replaceNull(pluginE.getAttributeValue("desc")));
		return sysPlugin;
	}
	public void addSysPlugin(SysPlugin sysPlugin){
		String configuration=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"config"+File.separator+"plugin_configuration.xml";		
		File file=new File(configuration);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element rootE=doc.getDocRootElement();
		if(rootE==null) return;
		Element pluginE=new Element("plugin");
		pluginE.setAttribute("name", sysPlugin.getName());
		pluginE.setAttribute("class", sysPlugin.getClazz());
		pluginE.setAttribute("desc", sysPlugin.getDesc());
		doc.addDocElement(rootE.getChild("plugins"), pluginE);
		doc.saveDoc();
	}
	public void updateSysPlugin(SysPlugin sysPlugin){
		String configuration=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"config"+File.separator+"plugin_configuration.xml";		
		File file=new File(configuration);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element pluginE=doc.getDocElement(sysPlugin.getKey());
		
		pluginE.setAttribute("name", sysPlugin.getName());
		pluginE.setAttribute("class", sysPlugin.getClazz());
		pluginE.setAttribute("desc", sysPlugin.getDesc());
		doc.saveDoc();
	}
	public void deleteSysPlugin(String[] keyArr){
		if(keyArr==null) return;
		String configuration=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"config"+File.separator+"plugin_configuration.xml";		
		File file=new File(configuration);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element rootE=doc.getDocRootElement();
		if(rootE==null) return;
		for(int i=0;i<keyArr.length;i++){
			doc.deleteDocElement(rootE.getChild("plugins"), keyArr[i]);
		}
		doc.saveDoc();
	}
}
