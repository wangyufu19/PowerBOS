package com.controller.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import org.jdom.input.SAXBuilder;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

import com.controller.config.ActionConfig;
import com.controller.config.ModuleConfig;
import com.controller.exception.ControllerException;
import com.controller.forward.ActionForward;
import com.controller.forward.GlobalForward;
/**
 * 模块配置解析器类
 * @author wangyf
 * @version 1.0
 */
public class ModuleParser {
	protected Map actionsconfig;
	protected Map globalforwards;
	
	public ModuleParser(){
		actionsconfig=new HashMap();		
		globalforwards=new HashMap();		
	}	
	/**
	 * 将解析资源global,actions配置推入到配置对象里
	 * @param config
	 */
	public void push(ModuleConfig config){		
		if(config==null){
			return;
		}				
		for(Iterator it=actionsconfig.keySet().iterator();it.hasNext();){
			String key=it.next().toString();
			ActionConfig actionconfig=(ActionConfig)actionsconfig.get(key);				
			config.addActionConfig(actionconfig);
		}				
		for(Iterator it=globalforwards.keySet().iterator();it.hasNext();){
			String key=it.next().toString();				      		
			GlobalForward globalforward=(GlobalForward)globalforwards.get(key);
			config.addGlobalForward(globalforward);			
		}
	}
	/**
	 * 解析Struts配置资源
	 * @param inputstream
	 * @return
	 * @throws ControllerException
	 */
	public Document parseResource(InputStream inputstream) throws ControllerException{
		SAXBuilder sax=new SAXBuilder();	
		Document doc=null;
		try {			
			doc=sax.build(inputstream);
		} catch (JDOMException e) {			
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		if(doc==null){
			throw new ControllerException("The config paser failure");
		}
		return doc;
	}
	/**
	 * 解析Struts配置包含模块资源
	 * @param inputstream
	 * @throws ControllerException
	 */
	public void parseIncludeResource(InputStream inputstream) throws ControllerException{
		SAXBuilder sax=new SAXBuilder();	
		Document doc=null;
		try {			
			doc=sax.build(inputstream);
		} catch (JDOMException e) {			
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		if(doc==null){
			throw new ControllerException("The config paser failure");
		}
		Element e=doc.getRootElement();
		if(e==null) return;				
		parseGlobal(e);
		parseAction(e);
	}	
	/**
	 * 解析资源配置全局元素
	 * @param e
	 */
	private void parseGlobal(Element e){
		Element e1=e.getChild("global");
		if(e1==null) return;
		List list=e1.getChildren();
		if(list==null)return;
		for(int i=0;i<list.size();i++){
			Element e2=(Element)list.get(i);
			if("results".equals(e2.getName())){
				paserGlobalResult(e2);
			}
		}		
	}
	/**
	 * 解析资源配置全局转发结果元素
	 * @param e
	 */
	private void paserGlobalResult(Element e){
		List list=e.getChildren("result");
		if(list==null) return;
		for(int i=0;i<list.size();i++){
			Element e1=(Element)list.get(i);
			String name=e1.getAttributeValue("name");
			String value=e1.getTextTrim();
			GlobalForward globalforward=new GlobalForward();					
			globalforward.setName(name);
			globalforward.setValue(value);
			globalforwards.put(name, globalforward);				
		}
	}
	/**
	 * 解析资源配置动作元素
	 * @param e
	 */
	private void parseAction(Element e){
		Element e1=e.getChild("actions");
		if(e1==null) return;
		List list=e1.getChildren("action");
		if(list==null) return;
		for(int i=0;i<list.size();i++){
			Element e2=(Element)list.get(i);
			ActionConfig actionconfig=new ActionConfig();
			String name=e2.getAttributeValue("name");
			String clazz=e2.getAttributeValue("class");
			String method=e2.getAttributeValue("method");
			String path=name+".com.controller.config";
			actionconfig.setPath(path);		
			actionconfig.setName(name);
			actionconfig.setClazz(clazz);
			actionconfig.setMethod(method);
			actionsconfig.put(path, actionconfig);
			List list1=e2.getChildren("result");					
			if(list1!=null){
				for(int j=0;j<list1.size();j++){
					Element e3=(Element)list1.get(j);
					name=e3.getAttributeValue("name");
					String url=e3.getTextTrim();
					ActionForward actionforward=new ActionForward();					
					actionforward.setName(name);
					actionforward.setValue(url);
					actionconfig.addActionForward(actionforward);					
				}
			}
		}	
	}
}
