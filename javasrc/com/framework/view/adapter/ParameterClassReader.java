package com.framework.view.adapter;
import java.util.List;
import org.jdom.Element;
import com.framework.common.servlet.http.RequestHash;
import com.framework.view.bean.BeanMgr;
import com.framework.view.data.DataTypeConverter;
import com.framework.view.util.RequestUtil;
import com.framework.view.util.StringFormat;
import com.sqlMap.cfg.Resource;
import com.sqlMap.cfg.ResourceFactory;
/**
 * 数据参数读取类
 * @author wangyf
 * @version 1.0
 */
public class ParameterClassReader {
	
	private RequestHash reh;
	
	public ParameterClassReader(RequestHash reh){
		this.reh=reh;
	}
	
	public Object read(Element statementE) throws Exception{			
		if(statementE==null) return null;
		Element beanE=statementE.getChild("INPUTBEAN");		
		if(beanE==null) return null;
		List list=beanE.getChildren("SETBEAN");				
		BeanMgr beanMgr=new BeanMgr();		
		Object obj=null;	
		boolean primitive=false;
		String parameterClass=StringFormat.replaceNull(statementE.getAttributeValue("parameterClass"));
		primitive=DataTypeConverter.isPrimitive(parameterClass);		
	    if(!primitive){																	
			try{			
				Resource resource=ResourceFactory.getInstance();
				parameterClass=resource.getParameterClass(parameterClass);				
				ClassLoader classLoader=Thread.currentThread().getContextClassLoader();				
				obj=classLoader.loadClass(parameterClass).newInstance();
			}catch(Exception e){
				throw  e;			
			}
		}		   	  
	    if(list==null) return obj;		
		for(int i=0;i<list.size();i++){
			Element setbeanE=(Element)list.get(i);
			String name=StringFormat.replaceNull(setbeanE.getAttributeValue("name"));
			String value=StringFormat.replaceNull(setbeanE.getAttributeValue("value"));		
			if(value.indexOf("fun.get(")!=-1){
				String replacement=RequestUtil.getRequestParameterName(value);
			    String target=RequestUtil.getRequestParamNameStr(value);	 
		        replacement=reh.get(replacement);        
		        value=StringFormat.replace(value,target, replacement);			
			}
			if(value.indexOf("fun.getSession")!=-1){
				value=RequestUtil.formatSession(reh.getSessionHash(), value);			
			}			
			value=StringFormat.replaceNull(value);			
			if(primitive){			
				if(!"".equals(value))
					obj=DataTypeConverter.convertToObject(value, parameterClass);
			}else{				 
				if(!"".equals(value))
					beanMgr.setBean(obj, name,DataTypeConverter.convertToObject(value, beanMgr.getReturnType(obj, name).getName()));
			}			
		}						
		return obj;		
	}
}
