package com.sqlMap.util;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import com.sqlMap.cfg.Resource;
import com.sqlMap.cfg.ResourceFactory;
import com.sqlMap.exception.MappingException;
import com.sqlMap.id.Identifier;
import com.sqlMap.property.BeanFactory;
import com.sqlMap.property.Metadata;
import com.sqlMap.property.Property;
import com.sqlMap.property.Setter;
import com.sqlMap.type.DateType;
import com.sqlMap.type.FloatType;
import com.sqlMap.type.IntType;
import com.sqlMap.type.LongType;
import com.sqlMap.type.StringType;
import com.sqlMap.type.TimestampType;
import com.sqlMap.util.StringUtil;

/**
 * 关系对象数据查询类
 * @author youfu.ang
 * @version 1.0
 */
public class ObjectUtil {
	/**
	 * 得到集合数据对象数组
	 * @param rst
	 * @return
	 * @throws SQLException
	 */
	public Object[] getObjectArray(ResultSet rst) throws SQLException{
		ResultSetMetaData rsmd = rst.getMetaData();	
		Object[] obj =new Object[rsmd.getColumnCount()];
		for(int i=0;i<obj.length;i++){
			obj[i]=rst.getObject(i+1);
		}
		return obj;
	}
	/**
	 * 得到集合数据对象
	 * @param rst
	 * @param metadata
	 * @return
	 * @throws SQLException
	 */
	private Object getObject(ResultSet rst,Metadata metadata,String returnClass) throws SQLException{	
		ClassLoader classLoader=Thread.currentThread().getContextClassLoader();				
		Object obj=null;		
		try {
			obj = classLoader.loadClass(returnClass).newInstance();
		} catch (InstantiationException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}					
		int count=rst.getMetaData().getColumnCount();		
		Identifier identifier=metadata.getIdentifier();
		if(identifier==null) identifier=new Identifier();	
		for(int i=1;i<=count;i++){	
			String name="";
			String type="";
			String column=rst.getMetaData().getColumnName(i);			
			if(identifier.isIdentifierKey(column)){
				name=identifier.getProperty().getName();
				type=identifier.getProperty().getType();				
			}else{
				Property property=metadata.getProperty(column);				
				name=property.getName();
				type=property.getType();
			}		
			Setter setter=BeanFactory.getSetter(obj.getClass(), name);	
			if("java.lang.Integer".equals(type)){
				Object propertyValue;
				IntType intType=new IntType();
				propertyValue=intType.get(rst, column);					
				setter.set(obj, propertyValue);	
			}if("java.lang.Long".equals(type)){	
				Object propertyValue;
				LongType longType=new LongType();
				propertyValue=longType.get(rst, column);					
				setter.set(obj, propertyValue);	
			}else if("java.lang.Float".equals(type)){
				Object propertyValue;
				FloatType floatType=new FloatType();
				propertyValue=floatType.get(rst, column);
				setter.set(obj, propertyValue);
			}else if("java.lang.String".equals(type)){						
				Object propertyValue;						
				StringType stringType=new StringType();
				propertyValue=StringUtil.replaceNull(stringType.get(rst, column));				
				setter.set(obj, propertyValue);	
			}else if("java.util.Date".equals(type)){
				Object propertyValue;
				DateType dateType=new DateType();
				propertyValue=dateType.get(rst, column);
				if(propertyValue!=null)
					setter.set(obj, propertyValue);	
			}else if("java.sql.Timestamp".equals(type)){
				Object propertyValue;				
				TimestampType timeType=new TimestampType();
				propertyValue=timeType.get(rst, column);				
				setter.set(obj, propertyValue);	
			}
		}
		return obj;
	}
	/**
	 * 得到集合数据对象
	 * @param rst
	 * @param sqlMapId
	 * @return
	 * @throws MappingException
	 * @throws SQLException
	 */
	public Object getObject(ResultSet rst,String sqlMapId) throws MappingException, SQLException{	
		Resource resource=ResourceFactory.getInstance();	
		Map statement=resource.getStatementMap(sqlMapId);	
		String resultMap=StringUtil.replaceNull(String.valueOf(statement.get("resultMap")));
		String returnClass=resource.getReturnClass(sqlMapId, StringUtil.replaceNull(String.valueOf(statement.get("returnClass"))));			
		Metadata metadata=resource.getMetadata(sqlMapId, resultMap);		
		if("".equals(returnClass)){
			ResultSetMetaData rsmd = rst.getMetaData();	
			Object[] obj =new Object[rsmd.getColumnCount()];
			for(int i=0;i<obj.length;i++){
				obj[i]=rst.getObject(i+1);
			}
			return obj;
		}
		return this.getObject(rst, metadata,returnClass);			
	}
	/**
	 * 得到集合数据对象
	 * @param rst
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws MappingException
	 * @throws SQLException
	 */
	public Object getObject(ResultSet rst,String returnClass,String resultMap) throws MappingException, SQLException{
		Resource resource=ResourceFactory.getInstance();
		Metadata metadata=resource.getMetadata(resultMap);
		returnClass=resource.getReturnClass(returnClass);
		if("".equals(returnClass)){
			ResultSetMetaData rsmd = rst.getMetaData();	
			Object[] obj =new Object[rsmd.getColumnCount()];
			for(int i=0;i<obj.length;i++){
				obj[i]=rst.getObject(i+1);
			}
			return obj;
		}
		return this.getObject(rst, metadata, returnClass);
	}
}
