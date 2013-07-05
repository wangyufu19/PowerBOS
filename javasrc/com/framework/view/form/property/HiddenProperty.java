package com.framework.view.form.property;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.framework.common.servlet.http.RequestHash;
import com.framework.view.util.StringFormat;
/**
 * 表单全局隐藏属性元素类
 * @author wangyf
 * @version 1.0
 */
public class HiddenProperty {
	private RequestHash reh;
	private Map paramHash;
	private Map hidden=new HashMap();	
	private Map filterPros=new HashMap();
	
	public HiddenProperty(RequestHash reh){
		this.reh=reh;
	}
	public void addHiddenProperty(String key,String value){		
		hidden.put(key, value);
	}	
	public void addFilterProperty(Map pros){
		if(pros==null) return;
		for(Iterator it=pros.keySet().iterator();it.hasNext();){
			String key=StringFormat.replaceNull(it.next().toString());
			Object obj=pros.get(key);			
			filterPros.put(key, obj);
		}
	}
	public void addFilterProperty(String key,String value){
		filterPros.put(key, value);
	}
	public String render(){
		paramHash=reh.getParamHash();			
		hidden.put("action", "");	
		hidden.put("SELECTEDID", "");
		hidden.put("currentPage", "");		
		for(Iterator it=paramHash.keySet().iterator();it.hasNext();){
			String name=StringFormat.replaceNull(it.next().toString());
			String value=StringFormat.replaceNull(reh.get(name));				
			if(!filterPros.containsKey(name)){				
				if("CODE".equals(name)){						
					hidden.put(name, value);	
				}else if("action".equals(name)){
					hidden.put(name, "");
				}else if("SHOW_TYPE".equals(name)){
					hidden.put(name, value);	
				}else if("SELECTEDID".equals(name)){
					hidden.put(name, "");
				}else if("currentPage".equals(name)){
					hidden.put(name, "");
				}else {
					hidden.put(name, value);
				}
			}			
		}
		StringBuilder buf=new StringBuilder();						
		for(Iterator it=hidden.keySet().iterator();it.hasNext();){
			String name=it.next().toString();			
			String value=String.valueOf(hidden.get(name));				
			buf.append("<input ")
			   .append("type=\"hidden\" ")
			   .append("id=\""+name+"\" ")
			   .append("name=\""+name+"\" ")
			   .append("value=\""+value+"\"/>\n");
		}			
		return buf.toString();
	}

}
