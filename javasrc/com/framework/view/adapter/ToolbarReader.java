package com.framework.view.adapter;
import java.util.List;
import java.util.ArrayList;
import org.jdom.Element;

import com.framework.common.servlet.http.RequestHash;
import com.framework.view.adapter.ToolbarObject;
import com.framework.view.util.RequestUtil;
import com.framework.view.util.StringFormat;
import com.powerbosframework.util.StringUtil;
/**
 * 工具条读取器类
 * @author wangyf
 * @version 1.0
 */
public class ToolbarReader {
	private RequestHash reh;
	
	public ToolbarReader(RequestHash reh){
		this.reh=reh;
	}
	public ToolbarObject getToolbarObject(Element toolbarE){
		boolean bool=false;				
		String code=StringFormat.replaceNull(toolbarE.getAttributeValue("code"));
		String name=StringFormat.replaceNull(toolbarE.getAttributeValue("name"));
		String href=StringFormat.replaceNull(toolbarE.getAttributeValue("href"));
		String value=StringUtil.replaceNull(toolbarE.getAttributeValue("value"));
		String onclick=StringFormat.replaceNull(toolbarE.getAttributeValue("onclick"));
		if(!"".equals(onclick)){
			//解析多个请求参数
			while(onclick.indexOf("fun.get(")!=-1){
				String replacement=RequestUtil.getRequestParameterName(onclick);
			    String target=RequestUtil.getRequestParamNameStr(onclick);	 
		        replacement=reh.get(replacement);        
		        onclick=StringFormat.replace(onclick,target, replacement);		
			}
			if(onclick.indexOf("fun.getSession")!=-1){
				onclick=RequestUtil.formatSession(reh.getSessionHash(), onclick);			
			}					       
		}else if(!"".equals(href)){
			//解析多个请求参数
			while(href.indexOf("fun.get(")!=-1){
				String replacement=RequestUtil.getRequestParameterName(href);
			    String target=RequestUtil.getRequestParamNameStr(href);	 
		        replacement=reh.get(replacement);        
		        href=StringFormat.replace(href,target, replacement);		
			}
			if(href.indexOf("fun.getSession")!=-1){
				href=RequestUtil.formatSession(reh.getSessionHash(), href);			
			}	       			       
		}
		//解析事件加载条件
		String loadCondition=StringFormat.replaceNull(toolbarE.getAttributeValue("loadCondition"));
		if(loadCondition.indexOf("fun.get(")!=-1){
			String replacement=RequestUtil.getRequestParameterName(loadCondition);
		    String target=RequestUtil.getRequestParamNameStr(loadCondition);	 
	        replacement=reh.get(replacement);  
	        loadCondition=StringFormat.replace(loadCondition,target, replacement);	
		}
		if(loadCondition.indexOf("fun.getSession")!=-1){
			loadCondition=RequestUtil.formatSession(reh.getSessionHash(), loadCondition);			
		}		
        bool=StringFormat.convertOfBoolean(StringFormat.replaceNull(loadCondition));  
		ToolbarObject obj=new ToolbarObject();
		if(bool){        	
        	obj.setCode(code);
        	obj.setName(name);
        	obj.setValue(value);
        	obj.setHref(href);
        	obj.setOnclick(onclick);	 
        	obj.setFlag(bool);
	    }    
		return obj;
	}
	public List readToolbar(Element gridE){
		List list=new ArrayList();
		if(gridE==null) return list;
		Element actionsE=gridE.getChild("ACTIONS");
		if(actionsE==null) return list;
		List toolbars=actionsE.getChildren("ACTION");
		for(int i=0;i<toolbars.size();i++){
			Element toolbarE=(Element)toolbars.get(i);				
			ToolbarObject obj=this.getToolbarObject(toolbarE);
			if(obj.getFlag()) list.add(obj);	
		}	
		return list;
	}
	public List readLinkToolbar(Element gridE){
		List list=new ArrayList();
		if(gridE==null) return list;
		Element actionsE=gridE.getChild("ACTIONS");
		if(actionsE==null) return list;
		List toolbars=actionsE.getChildren("ACTION");
		for(int i=0;i<toolbars.size();i++){
			Element toolbarE=(Element)toolbars.get(i);
			String toolbarType=StringFormat.replaceNull(toolbarE.getAttributeValue("actionType"));
			if("linkToolbar".equals(toolbarType)){		
				ToolbarObject obj=this.getToolbarObject(toolbarE);
				if(obj.getFlag()) list.add(obj);
			}			
		}	
		return list;
	}
	public List readHandleToolbar(Element gridE){
		List list=new ArrayList();
		if(gridE==null) return list;
		Element actionsE=gridE.getChild("ACTIONS");		
		if(actionsE==null) return list;
		List toolbars=actionsE.getChildren("ACTION");		
		if(toolbars==null) return list;				
		for(int i=0;i<toolbars.size();i++){
			Element toolbarE=(Element)toolbars.get(i);
			String toolbarType=StringFormat.replaceNull(toolbarE.getAttributeValue("actionType"));
			if("handleToolbar".equals(toolbarType)){					
				ToolbarObject obj=this.getToolbarObject(toolbarE);					
				if(obj.getFlag()) list.add(obj);
			}			
		}			
		return list;
	}
	public List readButtonToolbar(Element formE){
		List list=new ArrayList();
		if(formE==null) return list;
		Element actionsE=formE.getChild("ACTIONS");
		if(actionsE==null) return list;
		List toolbars=actionsE.getChildren("ACTION");
		for(int i=0;i<toolbars.size();i++){
			Element toolbarE=(Element)toolbars.get(i);
			String toolbarType=StringFormat.replaceNull(toolbarE.getAttributeValue("actionType"));
			if("buttonToolbar".equals(toolbarType)){		
				ToolbarObject obj=this.getToolbarObject(toolbarE);
				if(obj.getFlag()) list.add(obj);
			}			
		}	
		return list;
	}
}
