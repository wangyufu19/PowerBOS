package com.sqlMap.impl;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Date;
import java.util.Vector;

import com.sqlMap.QueryParam;
import com.sqlMap.cfg.Resource;
import com.sqlMap.cfg.ResourceFactory;
import com.sqlMap.exception.MappingException;
import com.sqlMap.id.Identifier;
import com.sqlMap.log.LogFactory;
import com.sqlMap.log.Logger;
import com.sqlMap.parser.SqlParser;
import com.sqlMap.property.Getter;
import com.sqlMap.property.BeanFactory;
import com.sqlMap.property.Metadata;
import com.sqlMap.type.DateType;
import com.sqlMap.util.StringUtil;
import com.sqlMap.util.TypeUtil;
import com.sqlMap.cfg.Environment;

/**
 * SQL语句解析器实现类
 * @author youfu.wang
 * @version 1.0
 */
public class SqlParserImpl implements SqlParser{	
	private static final Logger log=LogFactory.getInstance();
	
	/**
	 * 解析资源数据的SELECT语句
	 * @param sqlMapId
	 * @return
	 * @throws MappingException 
	 */
	public String getSQLForMaxRow(String sqlMapId) throws MappingException{
		StringBuilder buf=new StringBuilder();		
		Resource resource=ResourceFactory.getInstance();
		Map statement=resource.getStatementMap(sqlMapId);		
		//解析查询语句
	    buf.append(String.valueOf(statement.get("sql")));		
		return buf.toString();
	}
	/**
	 * 解析资源数据的SELECT语句
	 * @param params
	 * @param sqlMapId
	 * @return
	 * @throws MappingException
	 */
	public String getSQLForMaxRow(Map params,String sqlMapId) throws MappingException{
		StringBuilder buf=new StringBuilder();
		Resource resource=ResourceFactory.getInstance();			
		Map statement=resource.getStatementMap(sqlMapId);					
		String resultMap=String.valueOf(statement.get("resultMap"));
		Metadata metadata=resource.getMetadata(sqlMapId, resultMap);
		//解析查询语句
		buf.append(this.parseSQL(params, metadata, statement));		
		//解析IsNotEmpty条件语句
		buf.append(this.parseIsNotEmpty(params, statement));	
		return buf.toString();
	}
	/**
	 * 解析资源数据的SELECT语句
	 * @param obj
	 * @param sqlMapId
	 * @return
	 * @throws MappingException 
	 */
	public String getSQLForMaxRow(Object obj,String sqlMapId) throws MappingException{
		StringBuilder buf=new StringBuilder();		
		Resource resource=ResourceFactory.getInstance();			
		Map statement=resource.getStatementMap(sqlMapId);
		String resultMap=StringUtil.replaceNull(String.valueOf(statement.get("resultMap")));				
		Metadata metadata=resource.getMetadata(sqlMapId, resultMap);		
		//解析查询语句
		buf.append(this.parseSQL(StringUtil.replaceNull(obj), metadata, statement));
		//解析IsNotEmpty条件语句		
		buf.append(this.parseIsNotEmpty(StringUtil.replaceNull(obj), statement));	
		return buf.toString();
	}
	/**
	 * 解析资源数据的SELECT语句,并且组装成分页查询语句
	 * @param sqlMapId
	 * @param queryParam
	 * @return
	 * @throws MappingException
	 */
	public String parseSQLForQueryPage(String sqlMapId,QueryParam queryParam) throws MappingException{
		StringBuilder buf=new StringBuilder();
		String sql="";
		Resource resource=ResourceFactory.getInstance();			
		Map statement=resource.getStatementMap(sqlMapId);					
		String resultMap=String.valueOf(statement.get("resultMap"));
		Metadata metadata=resource.getMetadata(sqlMapId, resultMap);
		Identifier identifier=metadata.getIdentifier();	
		String tableName=metadata.getTable();
		String keyColumn=identifier.getProperty().getColumn();		
		sql=String.valueOf(statement.get("sql"));
		//解析查询语句
		buf.append(String.valueOf(statement.get("sql")));	
		//解析IsEqual条件语句
		buf.append(this.parseIsEqual(resource, sqlMapId, statement));
		sql=buf.toString();		
		//分别组装oracle,mssql,mysql数据库方言的分页查询语句
		if("oracle".equals(Environment.dialect)){			
			sql="select * from (select rownum r,t1.* from ("+sql+") t1 where rownum<="+queryParam.getMaxResult()+") t2 where r>="+queryParam.getFirstResult();
		}else if("mssql".equals(Environment.dialect)){			
			if("2000".equals(Environment.dialect_version)){
				sql="select * from ( select top "+queryParam.getPageSize()+" * from ("+sql+") t1 where ("+keyColumn+" not in (select top "+(queryParam.getFirstResult()-1)+" "+keyColumn+" from "+tableName+"))) t2";
			}else if("2005".equals(Environment.dialect_version)){
				sql="select * from (select row_number() over(order by "+keyColumn+") r,t1.* from ("+sql+") t1 ) t2 where r between "+queryParam.getFirstResult()+" and "+queryParam.getMaxResult();
			}			
		}else if("mysql".equals(Environment.dialect)){
			sql="select * from (select t1.* from ("+sql+") t1) limit "+queryParam.getMaxResult()+","+queryParam.getFirstResult();
		}else if("db2".equals(Environment.dialect)){
			
		}else if("kingbase".equals(Environment.dialect)){
			sql="select * from (select t1.* from ("+sql+") t1) limit "+queryParam.getPageSize()+" offset "+(queryParam.getFirstResult()-1);
		}
		return sql;
	}
	/**
	 * 解析资源数据的SELECT语句,并且组装成分页查询语句
	 * @param params
	 * @param sqlMapId
	 * @param queryParam
	 * @return
	 * @throws MappingException
	 */
	public String parseSQLForQueryPage(Map params,String sqlMapId,QueryParam queryParam) throws MappingException{
		StringBuilder buf=new StringBuilder();
		String sql="";
		Resource resource=ResourceFactory.getInstance();			
		Map statement=resource.getStatementMap(sqlMapId);					
		String resultMap=String.valueOf(statement.get("resultMap"));
		Metadata metadata=resource.getMetadata(sqlMapId, resultMap);
		Identifier identifier=metadata.getIdentifier();	
		String tableName=metadata.getTable();
		String keyColumn=identifier.getProperty().getColumn();
		//解析查询语句
		buf.append(this.parseSQL(params, metadata, statement));
		//解析IsNotEmpty条件语句
		buf.append(this.parseIsNotEmpty(params, statement));	
		//解析IsEqual条件语句
		buf.append(this.parseIsEqual(resource, sqlMapId, params, statement));
		sql=buf.toString();
		//分别组装oracle,mssql,mysql数据库方言的分页查询语句
		if("oracle".equals(Environment.dialect)){				
			sql="select * from (select rownum r,t1.* from ("+sql+") t1 where rownum<="+queryParam.getMaxResult()+") t2 where r>="+queryParam.getFirstResult();
		}else if("mssql".equals(Environment.dialect)){			
			if("2000".equals(Environment.dialect_version)){
				sql="select * from ( select top "+queryParam.getPageSize()+" * from ("+sql+") t1 where ("+keyColumn+" not in (select top "+(queryParam.getFirstResult()-1)+" "+keyColumn+" from "+tableName+"))) t2";
			}else if("2005".equals(Environment.dialect_version)){
				sql="select * from (select row_number() over(order by "+keyColumn+") r,t1.* from ("+sql+") t1 ) t2 where r between "+queryParam.getFirstResult()+" and "+queryParam.getMaxResult();
			}	
		}else if("mysql".equals(Environment.dialect)){
			sql="select * from (select t1.* from ("+sql+") t1) limit "+queryParam.getMaxResult()+","+queryParam.getFirstResult();
		}else if("db2".equals(Environment.dialect)){
			
		}else if("kingbase".equals(Environment.dialect)){
			sql="select * from (select t1.* from ("+sql+") t1) limit "+queryParam.getPageSize()+" offset "+(queryParam.getFirstResult()-1);
		}				
		return sql;
	}
	/**
	 * 解析资源数据的SELECT语句,并且组装成分页查询语句
	 * @param obj
	 * @param sqlMapId
	 * @param queryParam
	 * @return
	 * @throws MappingException
	 */
	public String parseSQLForQueryPage(Object obj,String sqlMapId,QueryParam queryParam) throws MappingException{
		StringBuilder buf=new StringBuilder();
		String sql="";
		Resource resource=ResourceFactory.getInstance();			
		Map statement=resource.getStatementMap(sqlMapId);					
		String resultMap=String.valueOf(statement.get("resultMap"));
		Metadata metadata=resource.getMetadata(sqlMapId, resultMap);
		Identifier identifier=metadata.getIdentifier();	
		String tableName=metadata.getTable();
		String keyColumn=identifier.getProperty().getColumn();       
		//解析查询语句
		buf.append(this.parseSQL(StringUtil.replaceNull(obj), metadata, statement));
		//解析IsNotEmpty条件语句		
		buf.append(this.parseIsNotEmpty(StringUtil.replaceNull(obj), statement));
		//解析IsEqual条件语句
		buf.append(this.parseIsEqual(resource, sqlMapId, obj, statement));
		sql=buf.toString();			
		//分别组装oracle,mssql,mysql数据库方言的分页查询语句
		if("oracle".equals(Environment.dialect)){		
			sql="select * from (select rownum r,t1.* from ("+sql+") t1 where rownum<="+queryParam.getMaxResult()+") t2 where r>="+queryParam.getFirstResult();
		}else if("mssql".equals(Environment.dialect)){			
			if("2000".equals(Environment.dialect_version)){
				sql="select * from ( select top "+queryParam.getPageSize()+" * from ("+sql+") t1 where ("+keyColumn+" not in (select top "+(queryParam.getFirstResult()-1)+" "+keyColumn+" from "+tableName+"))) t2";
			}else if("2005".equals(Environment.dialect_version)){
				sql="select * from (select row_number() over(order by "+keyColumn+") r,t1.* from ("+sql+") t1 ) t2 where r between "+queryParam.getFirstResult()+" and "+queryParam.getMaxResult();
			}	
		}else if("mysql".equals(Environment.dialect)){
			sql="select * from (select t1.* from ("+sql+") t1) limit "+queryParam.getMaxResult()+","+queryParam.getFirstResult();
		}else if("db2".equals(Environment.dialect)){
			
		}else if("kingbase".equals(Environment.dialect)){
			sql="select * from (select t1.* from ("+sql+") t1) limit "+queryParam.getPageSize()+" offset "+(queryParam.getFirstResult()-1);
		}						
		return sql;
	}
	/**
	 * 解析资源数据查询语句
	 * @param sqlMapId
	 * @return
	 * @throws MappingException 
	 */
	public String parseQuerySQL(String sqlMapId) throws MappingException{
		StringBuilder buf=new StringBuilder();		
		Resource resource=ResourceFactory.getInstance();
		Map statement=resource.getStatementMap(sqlMapId);		
		//解析查询语句
	    buf.append(String.valueOf(statement.get("sql")));	
	    //解析IsEqual条件语句		
		buf.append(this.parseIsEqual(resource, sqlMapId, statement));
		return buf.toString();
	}
	/**
	 * 解析资源数据查询语句
	 * @param params
	 * @param sqlMapId
	 * @return
	 * @throws MappingException 
	 */
	public String parseQuerySQL(Map params,String sqlMapId) throws MappingException{
		StringBuilder buf=new StringBuilder();
		Resource resource=ResourceFactory.getInstance();			
		Map statement=resource.getStatementMap(sqlMapId);					
		String resultMap=String.valueOf(statement.get("resultMap"));
		Metadata metadata=resource.getMetadata(sqlMapId, resultMap);
		//解析查询语句
		buf.append(this.parseSQL(params, metadata, statement));
		//解析IsNotEmpty条件语句
		buf.append(this.parseIsNotEmpty(params, statement));	
		//解析IsEqual条件语句		
		buf.append(this.parseIsEqual(resource, sqlMapId, params, statement));
		return buf.toString();
	}	
	/**
	 * 解析资源数据查询语句
	 * @param obj
	 * @param sqlMapId
	 * @return
	 * @throws MappingException 
	 */
	public String parseQuerySQL(Object obj,String sqlMapId) throws MappingException{		
		StringBuilder buf=new StringBuilder();		
		Resource resource=ResourceFactory.getInstance();			
		Map statement=resource.getStatementMap(sqlMapId);
		String resultMap=StringUtil.replaceNull(String.valueOf(statement.get("resultMap")));				
		Metadata metadata=resource.getMetadata(sqlMapId, resultMap);		
		//解析查询语句
		buf.append(this.parseSQL(StringUtil.replaceNull(obj), metadata, statement));
		//解析IsNotEmpty条件语句		
		buf.append(this.parseIsNotEmpty(StringUtil.replaceNull(obj), statement));
		//解析IsEqual条件语句
		buf.append(this.parseIsEqual(resource, sqlMapId, obj, statement));
		return buf.toString();
	}	
	/**
	 * 解析资源数据查询语句
	 * @param sql
	 * @param params
	 * @param resultMap	
	 * @return
	 */
	public String parseQuerySQL(String sql,Map params,String resultMap){
		Resource resource=ResourceFactory.getInstance();
		Metadata metadata=null;
		try {
			metadata=resource.getMetadata(resultMap);
		} catch (MappingException e) {		
			e.printStackTrace();
		}
		return this.parseSQL(sql, params, metadata);
	}
	/**
	 * 解析资源数据查询语句
	 * @param sql
	 * @param obj
	 * @param resultMap	
	 * @return
	 */
	public String parseQuerySQL(String sql,Object obj,String resultMap){
		Resource resource=ResourceFactory.getInstance();
		Metadata metadata=null;
		try {
			metadata=resource.getMetadata(resultMap);
		} catch (MappingException e) {		
			e.printStackTrace();
		}
		return this.parseSQL(sql,obj,metadata);
	}
//	/**
//	 * 解析资源数据语句
//	 * @param params
//	 * @param metadata
//	 * @param statement
//	 * @return
//	 */
//	public String parseSQL(Map params,Metadata metadata,Map statement){
//		StringBuilder buf=new StringBuilder();
//		Identifier identifier=metadata.getIdentifier();		
//		String sql=String.valueOf(statement.get("sql"));	
//		String finalSql=String.valueOf(statement.get("sql"));				
//		while(sql.indexOf("$")!=-1){		
//			sql=sql.substring(sql.indexOf("$"),sql.length());				
//			int start=sql.indexOf("$");		
//			sql=sql.substring(start+1, sql.length());
//			int end=sql.indexOf("$");				
//			String property=sql.substring(start, end);
//			sql=sql.substring(end+1, sql.length());						
//			Object value=params.get(property);				
//			if(identifier.isIdentifierKey(property)){				
//				String type=identifier.getProperty().getType();				
//				finalSql=finalSql.replace("$"+property+"$",TypeUtil.formatValue(type, value));
//			}else{				
//				String type=metadata.getPropertyType(property);					
//				finalSql=finalSql.replace("$"+property+"$",TypeUtil.formatValue(type, value));				
//			}					
//		}	
//		buf.append(finalSql+" ");				
//		return buf.toString();
//	}		
	/**
	 * 解析资源数据语句
	 * @param sql
	 * @param params
	 * @param metadata	
	 * @return
	 */
	private String parseSQL(String sql,Map params,Metadata metadata){
		StringBuilder buf=new StringBuilder();
		Identifier identifier=metadata.getIdentifier();	
		if(params==null) return sql;
		String finalSql=sql;
		while(sql.indexOf("$")!=-1){		
			sql=sql.substring(sql.indexOf("$"),sql.length());				
			int start=sql.indexOf("$");		
			sql=sql.substring(start+1, sql.length());
			int end=sql.indexOf("$");				
			String property=sql.substring(start, end);
			sql=sql.substring(end+1, sql.length());						
			Object value=params.get(property);				
			if(identifier.isIdentifierKey(property)){				
				String type=identifier.getProperty().getType();				
				finalSql=finalSql.replace("$"+property+"$",TypeUtil.formatValue(type, value));
			}else{				
				String type=metadata.getPropertyType(property);					
				finalSql=finalSql.replace("$"+property+"$",TypeUtil.formatValue(type, value));				
			}					
		}	
		buf.append(finalSql+" ");	
		return buf.toString();		
	}
	/**
	 * 解析资源数据语句
	 * @param sql
	 * @param obj
	 * @param metadata	
	 * @return
	 */
	private String parseSQL(String sql,Object obj,Metadata metadata){	
		StringBuilder buf=new StringBuilder();
		String finalSql=sql;		
		Identifier identifier=metadata.getIdentifier();			
		if(obj==null) return sql;
		//解析普通语句段
		while(sql.indexOf("$")!=-1){		
			sql=sql.substring(sql.indexOf("$"),sql.length());				
			int start=sql.indexOf("$");		
			sql=sql.substring(start+1, sql.length());
			int end=sql.indexOf("$");				
			String property=sql.substring(start, end);
			sql=sql.substring(end+1, sql.length());		
			String value="";
			String type="";						
			if(obj instanceof Long){
				type="Long";
				value=TypeUtil.formatValue(type, obj);	
				finalSql=this.replaceProperty(finalSql, property, value);
				break;
			}else if(obj instanceof Float){
				value=TypeUtil.formatValue("Float", obj);
				finalSql=this.replaceProperty(finalSql, property, value);
				break;
			}else if(obj instanceof String ){
				type="String";				
				value=TypeUtil.formatValue(type, obj);		
				finalSql=this.replaceProperty(finalSql, property, value);
				break;
			}else if(obj instanceof Date){
				type="Date";
				value=TypeUtil.formatValue(type, obj);
				finalSql=this.replaceProperty(finalSql, property, value);
				break;
			}else if(obj instanceof Timestamp){
				type="Timestamp";
				value=TypeUtil.formatValue(type, obj);	
				finalSql=this.replaceProperty(finalSql, property, value);
				break;
			}else{
				Getter getter=BeanFactory.getGetter(obj.getClass(), property);								
				if(identifier.isIdentifierKey(property)){					
					type=identifier.getProperty().getType();
					value=TypeUtil.formatValue(type, getter.get(obj));			
				}else{
					type=metadata.getPropertyType(property);		
					value=TypeUtil.formatValue(type, getter.get(obj));
				}
				finalSql=this.replaceProperty(finalSql, property, value);
			}						
		}					
		buf.append(finalSql+" ");		
		return buf.toString();
	}
	/**
	 * 解析资源数据语句
	 * @param obj
	 * @param metadata
	 * @param statment
	 * @return
	 */
	private String parseSQL(Object obj,Metadata metadata,Map statement){    	
		String sql=String.valueOf(statement.get("sql"));
		return this.parseSQL(sql, obj, metadata);
	}
	/**
	 * 替换语句属性空值
	 * @param arg1
	 * @param property
	 * @param value
	 * @return
	 */
	private String replaceProperty(String arg1,String property,String value){
		String sql="";		
		//替换空值
		if("null".equals(value)||"".equals(value)){			
			sql=arg1.replace("$"+property+"$",value);
			
		}else{		
			sql=arg1.replace("$"+property+"$",value);
		}	
		return sql;
	}
	/**
	 * 解析资源数据语句
	 * @param sqlMapId
	 * @return
	 * @throws MappingException 
	 */
	public String parseExecuteSQL(String sqlMapId) throws MappingException{
		StringBuilder buf=new StringBuilder();		
		Resource resource=ResourceFactory.getInstance();
		Map statement=resource.getStatementMap(sqlMapId);		
		//解析执行语句
	    buf.append(String.valueOf(statement.get("sql")));	
		return buf.toString();
	}
	/**
	 * 解析资源数据语句
	 * @param obj
	 * @param sqlMapId
	 * @return
	 * @throws MappingException 
	 */
	public String parseExecuteSQL(Object obj,String sqlMapId) throws MappingException{
		StringBuilder buf=new StringBuilder();	
		Resource resource=ResourceFactory.getInstance();			
		Map statement=resource.getStatementMap(sqlMapId);		
		String resultMap=String.valueOf(statement.get("resultMap"));	
		Metadata metadata=resource.getMetadata(sqlMapId, resultMap);	
		//解析执行语句
		buf.append(this.parseSQL(StringUtil.replaceNull(obj), metadata, statement));
		return buf.toString();
	}
	/**
	 * 解析资源数据语句
	 * @param params
	 * @param sqlMapId
	 * @return
	 * @throws MappingException 
	 */
	public String parseExecuteSQL(Map params,String sqlMapId) throws MappingException{
		StringBuilder buf=new StringBuilder();	
		Resource resource=ResourceFactory.getInstance();			
		Map statement=resource.getStatementMap(sqlMapId);		
		String resultMap=String.valueOf(statement.get("resultMap"));	
		Metadata metadata=resource.getMetadata(sqlMapId, resultMap);	
		//解析执行语句
		buf.append(this.parseSQL(params, metadata, statement));
		return buf.toString();
	}
	/**
	 * 解析资源数据语句
	 * @param obj
	 * @param metadata
	 * @param statement
	 * @return
	 */
	public String parseExecuteSQL(Object obj,Metadata metadata,Map statement){
		StringBuilder buf=new StringBuilder();		
		buf.append(this.parseSQL(StringUtil.replaceNull(obj), metadata, statement));
		return buf.toString();
	}   
	/**
	 * 解析资源数据语句
	 * @param sql
	 * @param params
	 * @param resultMap
	 * @return
	 */
	public String parseExecuteSQL(String sql,Map params,String resultMap){
		StringBuilder buf=new StringBuilder();	
		Resource resource=ResourceFactory.getInstance();	
		Metadata metadata=null;
		try {
			metadata = resource.getMetadata(resultMap);
		} catch (MappingException e) {			
			e.printStackTrace();
		}
		buf.append(this.parseSQL(sql, params, metadata));
		return buf.toString();
	}
	/**
	 * 解析资源数据语句
	 * @param sql
	 * @param obj
	 * @param resultMap
	 * @return
	 */
	public String parseExecuteSQL(String sql,Object obj,String resultMap){
		StringBuilder buf=new StringBuilder();	
		Resource resource=ResourceFactory.getInstance();	
		Metadata metadata=null;
		try {
			metadata = resource.getMetadata(resultMap);
		} catch (MappingException e) {			
			e.printStackTrace();
		}
		buf.append(this.parseSQL(sql, obj, metadata));
		return buf.toString();
	}
	/**
	 * 解析资源参数语句
	 * @param params
	 * @param statement
	 * @return
	 */
	private String parseIsNotEmpty(Map params,Map statement){
		StringBuilder buf=new StringBuilder();
		Vector isNotEmptys=(Vector)statement.get("isNotEmpty");			
		for(int i=0;i<isNotEmptys.size();i++){
			Map isNotEmpty=(Map)isNotEmptys.get(i);
			String operator=String.valueOf(isNotEmpty.get("operator"));
			String property=String.valueOf(isNotEmpty.get("property"));
			String expression=String.valueOf(isNotEmpty.get("expression"));				
			String value=StringUtil.replaceNull(String.valueOf(params.get(property)));			
			expression=expression.replace("$"+property+"$",value);
			if(!"".equals(value)){
				buf.append(operator+" ");
				buf.append(expression);		
			}			
		}						
		return buf.toString();		
	}	
	/**
	 * 解析资源参数语句
	 * @param obj
	 * @param statment
	 * @return
	 */
	private String parseIsNotEmpty(Object obj,Map statement){
		StringBuilder buf=new StringBuilder();		
		if("".equals(obj)||obj==null) return "";
				
		Vector isNotEmptys=(Vector)statement.get("isNotEmpty");	
		for(int i=0;i<isNotEmptys.size();i++){
			Map isNotEmpty=(Map)isNotEmptys.get(i);
			String operator=String.valueOf(isNotEmpty.get("operator"));
			String property=String.valueOf(isNotEmpty.get("property"));
			String expression=String.valueOf(isNotEmpty.get("expression"));				
			Getter getter=BeanFactory.getGetter(obj.getClass(), property);	
			String value="";
			if(getter.get(obj) instanceof java.util.Date){
				DateType dateType=new DateType();
				value=dateType.format((Date)getter.get(obj));
			}else
				value=StringUtil.replaceNull(String.valueOf(getter.get(obj)));			
			expression=expression.replace("$"+property+"$",value);
			if(!"".equals(value)){
				buf.append(operator+" ");
				buf.append(expression);		
			}			
		}							
		return buf.toString();
	}
	
	/**
	 * 解析资源参数语句
	 * @param expression
	 * @param obj
	 * @param property
	 * @return
	 */
	public String parseIsNotEmpty(String expression,Object obj){		
		
		String exp1=expression;
		String property="";
		while(exp1.indexOf("$")!=-1){		
			exp1=exp1.substring(exp1.indexOf("$"),exp1.length());				
			int start=exp1.indexOf("$");		
			exp1=exp1.substring(start+1, exp1.length());
			int end=exp1.indexOf("$");				
			property=exp1.substring(start, end);			
			exp1=exp1.substring(end+1, exp1.length());		
			Getter getter=BeanFactory.getGetter(obj.getClass(), property);	
			String value="";			
			if(getter.get(obj) instanceof java.util.Date){
				DateType dateType=new DateType();
				value=dateType.format((Date)getter.get(obj));
			}else
				value=StringUtil.replaceNull(String.valueOf(getter.get(obj)));
			expression=expression.replace("$"+property+"$",value);		
		}		
		return expression;
	}
	/**
	 * 解析资源条件语句IsEqual
	 * @param resource
	 * @param sqlMapId
	 * @param statement
	 * @return
	 */
	private String parseIsEqual(Resource resource,String sqlMapId,Map statement){
		StringBuilder buf=new StringBuilder();	
		if(statement==null) return buf.toString();
		Vector isEquals=(Vector)statement.get("isEqual");	
		if(isEquals==null) return buf.toString();
			
		for(int i=0;i<isEquals.size();i++){
			Map isEqual=(Map)isEquals.get(i);
			if(isEqual==null) continue;
			String operator=StringUtil.replaceNull(String.valueOf(isEqual.get("operator")));
			String property=StringUtil.replaceNull(String.valueOf(isEqual.get("property")));
			String compareValue=StringUtil.replaceNull(String.valueOf(isEqual.get("compareValue")));
			String expression=StringUtil.replaceNull(String.valueOf(isEqual.get("expression")));
			if("orderBy".equals(property)){
				Map orderBy=(Map)resource.getOrderBy(sqlMapId);
				if(orderBy==null) continue; 
				String orderByColumn=String.valueOf(orderBy.get(property));	
				if(orderByColumn.equals(compareValue)){
					buf.append(operator+" "+ expression);		
				}
			}
		}
		return buf.toString();
	}
	/**
	 * 解析资源条件语句IsEqual
	 * @param resource
	 * @param sqlMapId
	 * @param params
	 * @param statement
	 * @return
	 */
	private String parseIsEqual(Resource resource,String sqlMapId,Map params,Map statement){ 
		StringBuilder buf=new StringBuilder();	
		if(statement==null) return buf.toString();
		Vector isEquals=(Vector)statement.get("isEqual");	
		if(isEquals==null) return buf.toString();		
		for(int i=0;i<isEquals.size();i++){
			Map isEqual=(Map)isEquals.get(i);
			if(isEqual==null) continue;
			String operator=StringUtil.replaceNull(String.valueOf(isEqual.get("operator")));
			String property=StringUtil.replaceNull(String.valueOf(isEqual.get("property")));
			String compareValue=StringUtil.replaceNull(String.valueOf(isEqual.get("compareValue")));
			String expression=StringUtil.replaceNull(String.valueOf(isEqual.get("expression")));
			if("orderBy".equals(property)){
				Map orderBy=(Map)resource.getOrderBy(sqlMapId);
				if(orderBy==null) continue; 
				String orderByColumn=String.valueOf(orderBy.get(property));	
				if(orderByColumn.equals(compareValue)){
					buf.append(operator+" "+ expression);		
				}
			}else{			
				String value=StringUtil.replaceNull(String.valueOf(params.get(property)));			
				if(value.equals(compareValue)){
					buf.append(operator+" "+ expression);					
				}
			}
		}
		return buf.toString();
	}
	/**
	 * 解析资源条件语句IsEqual
	 * @param resource
	 * @param sqlMapId
	 * @param obj
	 * @param statement
	 * @return
	 */
	private String parseIsEqual(Resource resource,String sqlMapId,Object obj,Map statement){
		StringBuilder buf=new StringBuilder();	
		if(statement==null) return buf.toString();
		Vector isEquals=(Vector)statement.get("isEqual");	
		if(isEquals==null) return buf.toString();
				
		for(int i=0;i<isEquals.size();i++){
			Map isEqual=(Map)isEquals.get(i);
			if(isEqual==null) continue;
			String operator=StringUtil.replaceNull(String.valueOf(isEqual.get("operator")));
			String property=StringUtil.replaceNull(String.valueOf(isEqual.get("property")));
			String compareValue=StringUtil.replaceNull(String.valueOf(isEqual.get("compareValue")));
			String expression=StringUtil.replaceNull(String.valueOf(isEqual.get("expression")));
			if("orderBy".equals(property)){
				Map orderBy=(Map)resource.getOrderBy(sqlMapId);
				if(orderBy==null) continue; 
				String orderByColumn=String.valueOf(orderBy.get(property));	
				if(orderByColumn.equals(compareValue)){
					buf.append(operator+" "+ expression);		
				}
			}else{
				Getter getter=BeanFactory.getGetter(obj.getClass(), property);	
				String value=StringUtil.replaceNull(String.valueOf(getter.get(obj)));
				if(value.equals(compareValue)){
					buf.append(operator+" "+ expression);					
				}
			}
		}
		return buf.toString();
	}
}
