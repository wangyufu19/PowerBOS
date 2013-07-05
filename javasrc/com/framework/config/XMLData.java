package com.framework.config;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdom.Element;
import com.framework.common.util.SysConstants;
import com.framework.common.util.XMLUtil;
import com.framework.common.xmldoc.DocFactory;
import com.framework.common.xmldoc.DocObject;
import com.framework.view.util.StringFormat;
/***
 * 页面资源加载类
 * @author wangyf
 * @version 1.0
 */
public class XMLData {
	private static Map xmlresources=new HashMap();
	private static Map xmldatas=new HashMap();
	private static Map actions=new HashMap();
	private XMLUtil XMLUtil=new XMLUtil();	
	
	public void load(){
		String resource=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page";
		this.loadXMLData(resource);
	}
	private void loadXMLData(String resource){
		File file=new File(resource);
		if(!file.exists()) return;		
		File[] list=file.listFiles();
		if(list==null) return;
		for(int i=0;i<list.length;i++){
			File tmpFile=(File)list[i];			
			if(tmpFile.getName().endsWith(".xml")){				
				parseResource(tmpFile);
			}			
		}				 
	}
	private void parseResource(File file){
		DocObject docObject=DocFactory.getInstace().getDocObject(file.getPath());
		Element rootE=docObject.getDocRootElement();
		if(rootE==null) return;
		List list=rootE.getChildren();
        if(list==null) return;	
        for(int i=0;i<list.size();i++){
			Element xmldataE=(Element)list.get(i);
			this.xmldatas.put(xmldataE.getName(), xmldataE);	
			this.xmlresources.put(xmldataE.getName(), file.getName());
			this.parseActionResource(xmldataE);			
		}				
	}

	private void parseActionResource(Element xmldataE){
		if(xmldataE==null) return;
		Map actions=new HashMap();
		this.parseGridAction(xmldataE,actions);
		this.parseFormAction(xmldataE,actions);		
		this.actions.put(xmldataE.getName(), actions);
	}

	private void parseGridAction(Element xmldataE,Map actions){
		if(xmldataE==null) return;
		Element gridE=xmldataE.getChild("GRID");
		if(gridE==null) return;
		Element actinosE=gridE.getChild("ACTIONS");
		if(actinosE==null) return;
		List list=actinosE.getChildren("ACTION");
		if(list==null) return;		
		for(int i=0;i<list.size();i++){
			Element actionE=(Element)list.get(i);
			actions.put(StringFormat.replaceNull(actionE.getAttributeValue("code")), actionE);			
		}
	}
	private void parseFormAction(Element xmldataE,Map actions){
		if(xmldataE==null) return;
		Element formE=xmldataE.getChild("FORM");
		if(formE==null) return;
		Element actionsE=formE.getChild("ACTIONS");
		if(actionsE==null) return;
		List list=actionsE.getChildren("ACTION");
		if(list==null) return;		
		for(int i=0;i<list.size();i++){
			Element actionE=(Element)list.get(i);
			actions.put(StringFormat.replaceNull(actionE.getAttributeValue("code")), actionE);				
		}
	}
	public static Map getXMLResources(){
		return xmlresources;
	}
	public static Map getXMLData(){
		return xmldatas;
	}
	
	public static Map getXMLDataActions(){
		return actions;
	}
	public void clear(){		
		xmldatas.clear();
		actions.clear();		
	}
}
