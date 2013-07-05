package com.framework.view.form.action;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdom.Element;
import com.framework.common.encrypt.Base64;
import com.framework.common.encrypt.Md5Encrypt;
import com.framework.common.servlet.http.RequestHash;
import com.framework.common.util.SysConstants;
import com.framework.view.bean.BeanMgr;
import com.framework.view.data.DataTypeConverter;
import com.framework.view.util.DataFormat;
import com.framework.view.util.RequestUtil;
import com.framework.view.util.StringFormat;
import com.framework.view.util.WidgetException;
import com.powerbosframework.log.LogFactory;
import com.powerbosframework.log.Logger;
import com.sqlMap.cfg.Resource;
import com.sqlMap.cfg.ResourceFactory;
import com.sqlMap.exception.MappingException;
import com.sqlMap.id.Identifier;
import com.sqlMap.id.IdentifierGenerator;
import com.sqlMap.jdbc.JdbcTmplt;
import com.sqlMap.jdbc.QueryTmplt;
import com.sqlMap.property.Metadata;

/**
 * 语句块请求处理器
 * @author wangyf
 * @version 1.0
 */
public class StatmentProcessor {
	private static Logger log=LogFactory.getInstance();
	private RequestHash reh;
	
	public StatmentProcessor(RequestHash reh){
		this.reh=reh;
	}
	/**
	 * 持久化数据库
	 * @param jdbcTmplt
	 * @param statementE
	 * @return	
	 * @throws WidgetException 
	 * @throws SQLException 
	 */
	public String doStatement(JdbcTmplt jdbcTmplt,Element statementE,Element inputbeanE) throws WidgetException, SQLException{	
		if(statementE==null) return SysConstants.exce_succeed;		
		String parameterClass=StringFormat.replaceNull(statementE.getAttributeValue("parameterClass"));		
		String resultMap=StringFormat.replaceNull(statementE.getAttributeValue("resultMap"));			
		String sql=StringFormat.replaceNull(statementE.getText());	
		
		try {		
			if(this.isBatchBean(inputbeanE)){
	    		List list=this.getBatchBean(inputbeanE);
	    		for(int i=0;i<list.size();i++){
	    			Map params=(Map)list.get(i);     		    			
					jdbcTmplt.execute(sql, params, resultMap);					  			
	    		}
	    	}else{      		
	    		Object obj=null;  							
				obj=this.getParameterClass(jdbcTmplt,inputbeanE, parameterClass,resultMap);				
				jdbcTmplt.execute(sql,obj,resultMap);							
	    	}	
		} catch (SQLException e) {			
			e.printStackTrace();
			throw e;
		} 		    				
		return SysConstants.exce_succeed;
	}	
	/**
	 * 得到参数对象
	 * @param beanE	
	 * @param parameterClass
	 * @return
	 * @throws SQLException 
	 * @throws WidgetException 
	 */
	public Object getParameterClass(JdbcTmplt jdbcTmplt,Element beanE,String parameterClass,String resultMap) throws SQLException, WidgetException{			
		if(beanE==null) return null;
		List list=beanE.getChildren("SETBEAN");		
		if(list==null) return null;		
		BeanMgr beanMgr=new BeanMgr();		
		Object obj=null;	
		boolean primitive=false;			
		if("".equals(parameterClass)) throw new WidgetException("语句对象的参数类没有设置");			
		primitive=DataTypeConverter.isPrimitive(parameterClass);	
		Resource resource=ResourceFactory.getInstance();
	    if(!primitive){																	
			try{						
				parameterClass=resource.getParameterClass(parameterClass);				
				ClassLoader classLoader=Thread.currentThread().getContextClassLoader();				
				obj=classLoader.loadClass(parameterClass).newInstance();
			}catch(Exception e){
				throw new WidgetException("语句对象的"+parameterClass+"参数类没有找到",e);					
			}
		}		   	   	  
		for(int i=0;i<list.size();i++){
			Element setbeanE=(Element)list.get(i);
			String name=StringFormat.replaceNull(setbeanE.getAttributeValue("name"));
			String value=StringFormat.replaceNull(setbeanE.getAttributeValue("value"));	
			if(value.indexOf("fun.getID()")!=-1){			
				Identifier identifier=new Identifier();					
				Metadata metadata=null;
				try {
					metadata = resource.getMetadata(resultMap);
				} catch (MappingException e) {			
					e.printStackTrace();
				}			
				if(metadata!=null){
					identifier=metadata.getIdentifier();
				}
				String table="";
				String primary="";
				table=metadata.getTable();
				primary=identifier.getProperty().getColumn().toLowerCase();				
				QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
				String replacement="";
				String target="fun.getID()";
				replacement=String.valueOf(queryTmplt.getID(table, primary));
				value=StringFormat.replace(value,target, replacement);	
			}
			if(value.indexOf("fun.getUUID()")!=-1){
				String replacement="";
				String target="fun.getUUID()";
				replacement=String.valueOf(new IdentifierGenerator().getUUID());
				value=StringFormat.replace(value,target, replacement);	
			}
			if(value.toLowerCase().indexOf("fun.md5")!=-1){					
				String replacement=RequestUtil.getRequestParameterName(value);		
				if(DataFormat.isNumeric(replacement)){
					value=replacement;
				}else
					value=reh.get(replacement);      			       
		        value=Md5Encrypt.md5(value);		        
			}
			if(value.toLowerCase().indexOf("fun.base64")!=-1){
				String replacement=RequestUtil.getRequestParameterName(value);		
				if(DataFormat.isNumeric(replacement)){
					value=replacement;
				}else
					value=reh.get(replacement);    
				Base64 base64=new Base64();
				value=base64.Base64Encode(value);
			}
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
	public List getBatchBean(Element inputbeanE){
		if(inputbeanE==null) return new ArrayList();
		List list=inputbeanE.getChildren("SETBEAN");
		if(list==null) return new ArrayList();
		List bean=new ArrayList();	
		String[][] strArray=new String[list.size()][];
		String[] nameArray=new String[list.size()];
		int index=0;
		//组装fun.getArray
		for(int i=0;i<list.size();i++){
			Element setbeanE=(Element)list.get(i);
			String name=StringFormat.replaceNull(setbeanE.getAttributeValue("name"));
			nameArray[i]=name;
			String value=StringFormat.replaceNull(setbeanE.getAttributeValue("value"));
		    if(value.indexOf("fun.getArray")!=-1){
				String replacement=RequestUtil.getRequestParameterName(value);			   
			    strArray[i]=reh.getArray(replacement);  	
			    index=i;
			}
		}			
		if(strArray[index]==null) return new ArrayList();		
		//组装fun.get
		for(int i=0;i<list.size();i++){
			Element setbeanE=(Element)list.get(i);	
			String value=StringFormat.replaceNull(setbeanE.getAttributeValue("value"));
			if(value.indexOf("fun.get(")!=-1){
				String replacement=RequestUtil.getRequestParameterName(value);	
				value=reh.get(replacement);				
				String[] arr=new String[strArray[index].length];
				for(int j=0;j<arr.length;j++){
					arr[j]=value;
				}
				strArray[i]=arr;
			}
		}	
		//组装非fun.getArray和fun.get
		//组装常量参数
		for(int i=0;i<list.size();i++){
			Element setbeanE=(Element)list.get(i);	
			String value=StringFormat.replaceNull(setbeanE.getAttributeValue("value"));
			if(value.indexOf("fun.getArray")==-1&&value.indexOf("fun.get(")==-1){								
				String[] arr=new String[strArray[index].length];
				for(int j=0;j<arr.length;j++){
					arr[j]=value;
				}
				strArray[i]=arr;
			}
		}	
		for(int i=0;i<strArray[0].length;i++){		
			Map params=new HashMap();
			for(int j=0;j<nameArray.length;j++){	
				params.put(nameArray[j], strArray[j][i]);			
			}
			bean.add(params);
		}		
		return bean;
	}
	private boolean isBatchBean(Element inputbeanE){
		boolean bool=false;
		if(inputbeanE==null) return false;
		List list=inputbeanE.getChildren("SETBEAN");
		if(list==null) return false;
		for(int i=0;i<list.size();i++){
			Element setbeanE=(Element)list.get(i);			
			String value=StringFormat.replaceNull(setbeanE.getAttributeValue("value"));
			if(value.indexOf("fun.getArray")!=-1){
				bool=true;
				break;
			}
		}
		return bool;
	}
}
