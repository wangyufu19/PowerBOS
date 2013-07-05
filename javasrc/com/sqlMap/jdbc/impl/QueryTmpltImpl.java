package com.sqlMap.jdbc.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sqlMap.QueryParam;
import com.sqlMap.cache.CacheManager;
import com.sqlMap.cfg.Environment;
import com.sqlMap.cfg.Resource;
import com.sqlMap.cfg.ResourceFactory;
import com.sqlMap.exception.MappingException;
import com.sqlMap.id.Identifier;
import com.sqlMap.id.IdentifierGenerator;
import com.sqlMap.impl.SqlParserImpl;
import com.sqlMap.jdbc.QueryTmplt;
import com.sqlMap.log.DBLogger;
import com.sqlMap.log.Logger;
import com.sqlMap.log.LogFactory;
import com.sqlMap.parser.SqlParser;
import com.sqlMap.property.Metadata;
import com.sqlMap.util.ObjectUtil;
/**
 * JDBC查询模板实现类
 * @author youfu.wang
 * @version 1.0
 */
public class QueryTmpltImpl implements QueryTmplt{
	private static final Logger log=LogFactory.getInstance();
	CacheManager caheManager=CacheManager.getInstance();
	Resource resource=ResourceFactory.getInstance();	
	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet rst;
	String useQueryCache="false";
	private QueryParam queryParam=new QueryParam();
	
	public QueryTmpltImpl(Connection connection){
		this.connection=connection;
	}
	/**
	 * 该语句返回一个标识符
	 * @param table
	 * @param primary
	 * @return
	 * @throws SQLException
	 */
	public Long getID(String table,String primary) throws SQLException{
		IdentifierGenerator identifyGenerator=new IdentifierGenerator(this.connection);
		return identifyGenerator.getID(table, primary);
	}
	/**
	 * 执行给定的SQL语句，该语句返回单个Object对象
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public Object[] getArray(String sql) throws SQLException{
		ObjectUtil objectUtil=new ObjectUtil();
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);		
		if("true".equals(useQueryCache)){
			Object[] objs=(Object[])caheManager.getCacheObject(sql);
			if(objs!=null) return objs;
		}		
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();				
		while(rst.next()){	
			Object[] tmpObj=null;
			tmpObj=objectUtil.getObjectArray(rst);	
			pstmt.close();
			rst.close();
			if("true".equals(useQueryCache)){
				caheManager.putCacheObject(sql, tmpObj);
			}			
			return tmpObj;
		}	
		return null;
	}	
	/**
	 * 执行给定的SQL语句，该语句返回单个Object对象
	 * @param sql
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws SQLException 
	 */
	public Object getObject(String sql,String returnClass,String resultMap) throws SQLException{		
		List list=new ArrayList();				
		ObjectUtil objectUtil=new ObjectUtil();
		try {
			Metadata metadata = resource.getMetadata(resultMap);
			useQueryCache=metadata.getUseQueryCache();
		} catch (MappingException e1) {		
			e1.printStackTrace();
		}	
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);	
		if("true".equals(useQueryCache)){
			List cacheObjects=(ArrayList)caheManager.getCacheObject(sql);
			if(cacheObjects!=null) return cacheObjects;
		}
		
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();		
		Object obj=null;
		while(rst.next()){					
			try {
				obj=objectUtil.getObject(rst, returnClass, resultMap);				
			} catch (MappingException e) {				
				e.printStackTrace();
			}		
			pstmt.close();
			rst.close();
			if("true".equals(useQueryCache)){
				caheManager.putCacheObject(sql, list);
			}
			
			return obj;
		}
		
		return null;
	}
	/**
	 * 执行给定的SQL语句，该语句返回单个Object对象
	 * @param sql
	 * @param params
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws SQLException 
	 */
	public Object getObject(String sql,Map params,String returnClass,String resultMap) throws SQLException{		
		List list=new ArrayList();			
		SqlParser sqlParser=new SqlParserImpl();
		sql=sqlParser.parseQuerySQL(sql, params, resultMap);
		try {
			Metadata metadata = resource.getMetadata(resultMap);
			useQueryCache=metadata.getUseQueryCache();
		} catch (MappingException e1) {		
			e1.printStackTrace();
		}	
		ObjectUtil objectUtil=new ObjectUtil();
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);		
		if("true".equals(useQueryCache)){
			List cacheObjects=(ArrayList)caheManager.getCacheObject(sql);
			if(cacheObjects!=null) return cacheObjects;
		}
		
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();		
		Object obj=null;
		while(rst.next()){					
			try {
				obj=objectUtil.getObject(rst, returnClass, resultMap);				
			} catch (MappingException e) {				
				e.printStackTrace();
			}		
			pstmt.close();
			rst.close();
			if("true".equals(useQueryCache)){
				caheManager.putCacheObject(sql, list);
			}
			
			return obj;
		}
		
		return null;
	}
	/**
	 * 执行给定的SQL语句，该语句返回单个Object对象
	 * @param sql
	 * @param parameterClass
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws SQLException 
	 */
	public Object getObject(String sql,Object parameterClass,String returnClass,String resultMap) throws SQLException{
		SqlParser sqlParser=new SqlParserImpl();
		sql=sqlParser.parseQuerySQL(sql, parameterClass, resultMap);
		try {
			Metadata metadata = resource.getMetadata(resultMap);
			useQueryCache=metadata.getUseQueryCache();
		} catch (MappingException e1) {		
			e1.printStackTrace();
		}		
		List list=new ArrayList();				
		ObjectUtil objectUtil=new ObjectUtil();
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);		
		if("true".equals(useQueryCache)){
			List cacheObjects=(ArrayList)caheManager.getCacheObject(sql);
			if(cacheObjects!=null) return cacheObjects;
		}
		
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();		
		Object obj=null;
		while(rst.next()){					
			try {
				obj=objectUtil.getObject(rst, returnClass, resultMap);				
			} catch (MappingException e) {				
				e.printStackTrace();
			}		
			pstmt.close();
			rst.close();
			if("true".equals(useQueryCache)){
				caheManager.putCacheObject(sql, list);
			}			
			return obj;
		}		
		return null;
	}
	/**
	 * 执行给定的SQL语句，该语句返回多个Object对象的集合
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public List iterator(String sql) throws SQLException{
		List list=new ArrayList();				
		ObjectUtil objectUtil=new ObjectUtil();
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);		
		if("true".equals(useQueryCache)){
			List cacheObjects=(ArrayList)caheManager.getCacheObject(sql);
			if(cacheObjects!=null) return cacheObjects;
		}		
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();				
		while(rst.next()){					
			list.add(objectUtil.getObjectArray(rst));			
		}
		pstmt.close();
		rst.close();
		if("true".equals(useQueryCache)){
			caheManager.putCacheObject(sql, list);
		}		
		return list;
	}
	/**
	 * 执行给定的SQL语句，该语句返回多个实体对象的集合
	 * @param sql
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws SQLException
	 */
	public List iterator(String sql,String returnClass,String resultMap) throws SQLException{		
		List list=new ArrayList();				
		ObjectUtil objectUtil=new ObjectUtil();		
		try {
			Metadata metadata = resource.getMetadata(resultMap);
			useQueryCache=metadata.getUseQueryCache();
		} catch (MappingException e1) {		
			e1.printStackTrace();
		}		
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);		
		if("true".equals(useQueryCache)){			
			List cacheObjects=(ArrayList)caheManager.getCacheObject(sql);			
			if(cacheObjects!=null) return cacheObjects;
		}		
		
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();				
		while(rst.next()){					
			try {
				list.add(objectUtil.getObject(rst, returnClass, resultMap));
			} catch (MappingException e) {				
				e.printStackTrace();
			}			
		}
		pstmt.close();
		rst.close();
		if("true".equals(useQueryCache)){			
			caheManager.putCacheObject(sql, list);
		}		
		return list;
	}
	/**
	 * 执行给定的SQL语句，该语句返回多个实体对象的集合
	 * @param sql
	 * @param parameterClass
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws SQLException
	 */
	public List iterator(String sql,Map params,String returnClass,String resultMap) throws SQLException{
		SqlParser sqlParser=new SqlParserImpl();
		sql=sqlParser.parseQuerySQL(sql, params, resultMap);
		try {
			Metadata metadata = resource.getMetadata(resultMap);
			useQueryCache=metadata.getUseQueryCache();
		} catch (MappingException e1) {		
			e1.printStackTrace();
		}		
		List list=new ArrayList();				
		ObjectUtil objectUtil=new ObjectUtil();
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);		
		
		if("true".equals(useQueryCache)){
			List cacheObjects=(ArrayList)caheManager.getCacheObject(sql);
			if(cacheObjects!=null) return cacheObjects;
		}
	
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();				
		while(rst.next()){					
			try {
				list.add(objectUtil.getObject(rst, returnClass, resultMap));
			} catch (MappingException e) {				
				e.printStackTrace();
			}			
		}
		pstmt.close();
		rst.close();
		if("true".equals(useQueryCache)){
			caheManager.putCacheObject(sql, list);
		}
		
		return list;
	}
	/**
	 * 执行给定的SQL语句，该语句返回多个实体对象的集合
	 * @param sql
	 * @param parameterClass
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws SQLException
	 * @throws MappingException 
	 */
	public List iterator(String sql,Object parameterClass,String returnClass,String resultMap)throws SQLException{		
		SqlParser sqlParser=new SqlParserImpl();
		sql=sqlParser.parseQuerySQL(sql, parameterClass, resultMap);			
		try {
			Metadata metadata = resource.getMetadata(resultMap);
			useQueryCache=metadata.getUseQueryCache();
		} catch (MappingException e1) {		
			e1.printStackTrace();
		}		
		List list=new ArrayList();				
		ObjectUtil objectUtil=new ObjectUtil();
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);		
		
		if("true".equals(useQueryCache)){
			List cacheObjects=(ArrayList)caheManager.getCacheObject(sql);
			if(cacheObjects!=null) return cacheObjects;
		}		
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();				
		while(rst.next()){					
			try {
				list.add(objectUtil.getObject(rst, returnClass, resultMap));
			} catch (MappingException e) {				
				e.printStackTrace();
			}			
		}
		pstmt.close();
		rst.close();
		if("true".equals(useQueryCache)){
			caheManager.putCacheObject(sql, list);
		}		
		return list;
	}
	/**
	 * 执行给定的SQL语句,该语句返回分页结果集
	 * 该结果集数据为数组对象
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public List queryForList(String sql) throws SQLException{
		List list=new ArrayList();
		ObjectUtil objectUtil=new ObjectUtil();
		//是否采用分页查询数据
		if(queryParam.getMaxResult()>0){				
			//设置分页数据最大页
			IdentifierGenerator generator=new IdentifierGenerator(connection);
			int rownum=generator.getROWNUM(sql);			
			if(rownum>0){
				if(rownum%queryParam.getPageSize()==0){
					queryParam.setMaxPage(rownum/queryParam.getPageSize());			
				}else
					queryParam.setMaxPage(rownum/queryParam.getPageSize()+1);								
			}else
				queryParam.setMaxPage(1);	
			//分别组装oracle,mssql,mysql数据库方言的分页查询语句
			if("oracle".equals(Environment.dialect)){			
				sql="select * from (select rownum r,t1.* from ("+sql+") t1 where rownum<="+queryParam.getMaxResult()+") t2 where r>="+queryParam.getFirstResult();
			}else if("mssql".equals(Environment.dialect)){				
				if("2000".equals(Environment.dialect_version)){
					sql="select * from ( select top "+queryParam.getPageSize()+" * from ("+sql+") t1 where ("+queryParam.getPrimary()+" not in (select top "+(queryParam.getFirstResult()-1)+" "+queryParam.getPrimary()+" from "+queryParam.getTable()+"))) t2";
				}else if("2005".equals(Environment.dialect_version)){
					sql="select * from (select row_number() over(order by"+queryParam.getPrimary()+") r,t1.* from ("+sql+") t1 ) t2 where r between "+queryParam.getFirstResult()+" and "+queryParam.getMaxResult();
				}			
			}else if("mysql".equals(Environment.dialect)){
				sql="select * from (select t1.* from ("+sql+") t1) limit "+queryParam.getMaxResult()+","+queryParam.getFirstResult();
			}else if("db2".equals(Environment.dialect)){
				
			}else if("kingbase".equals(Environment.dialect)){
				sql="select * from (select t1.* from ("+sql+") t1) limit "+queryParam.getPageSize()+" offset "+(queryParam.getFirstResult()-1);
			}	
		}
		
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		if("true".equals(useQueryCache)){
			List cacheObjects=(ArrayList)caheManager.getCacheObject(sql);
			if(cacheObjects!=null) return cacheObjects;
		}		
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();	
		while(rst.next()){						
			list.add(objectUtil.getObjectArray(rst));					
		}	
		rst.close();
		if("true".equals(useQueryCache)){
			caheManager.putCacheObject(sql, list);
		}		
		return list;
	}
	/**
	 * 执行给定的SQL语句,该语句返回分页结果集	
	 * @param sql
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws SQLException 
	 */
	public List queryForList(String sql,String returnClass,String resultMap) throws SQLException{
		List list=new ArrayList();
		ObjectUtil objectUtil=new ObjectUtil();	
		//是否采用分页查询数据
		if(queryParam.getMaxResult()>0){				
			Resource resource=ResourceFactory.getInstance();			
			try {
				Metadata metadata= resource.getMetadata(resultMap);
				useQueryCache=metadata.getUseQueryCache();
				Identifier identifier=metadata.getIdentifier();
				queryParam.setTable(metadata.getTable());
				queryParam.setPrimary(identifier.getProperty().getColumn().toLowerCase());
			} catch (MappingException e) {			
				e.printStackTrace();
			}			
			//设置分页数据最大页
			IdentifierGenerator generator=new IdentifierGenerator(connection);
			int rownum=generator.getROWNUM(sql);			
			if(rownum>0){
				if(rownum%queryParam.getPageSize()==0){
					queryParam.setMaxPage(rownum/queryParam.getPageSize());			
				}else
					queryParam.setMaxPage(rownum/queryParam.getPageSize()+1);								
			}else
				queryParam.setMaxPage(1);	
			//分别组装oracle,mssql,mysql数据库方言的分页查询语句
			if("oracle".equals(Environment.dialect)){			
				sql="select * from (select rownum r,t1.* from ("+sql+") t1 where rownum<="+queryParam.getMaxResult()+") t2 where r>="+queryParam.getFirstResult();
			}else if("mssql".equals(Environment.dialect)){				
				if("2000".equals(Environment.dialect_version)){
					sql="select * from ( select top "+queryParam.getPageSize()+" * from ("+sql+") t1 where ("+queryParam.getPrimary()+" not in (select top "+(queryParam.getFirstResult()-1)+" "+queryParam.getPrimary()+" from "+queryParam.getTable()+"))) t2";
				}else if("2005".equals(Environment.dialect_version)){
					sql="select * from (select row_number() over(order by"+queryParam.getPrimary()+") r,t1.* from ("+sql+") t1 ) t2 where r between "+queryParam.getFirstResult()+" and "+queryParam.getMaxResult();
				}			
			}else if("mysql".equals(Environment.dialect)){
				sql="select * from (select t1.* from ("+sql+") t1) limit "+queryParam.getMaxResult()+","+queryParam.getFirstResult();
			}else if("db2".equals(Environment.dialect)){
				
			}else if("kingbase".equals(Environment.dialect)){
				sql="select * from (select t1.* from ("+sql+") t1) limit "+queryParam.getPageSize()+" offset "+(queryParam.getFirstResult()-1);
			}	
		}
		
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		
		if("true".equals(useQueryCache)){
			List cacheObjects=(ArrayList)caheManager.getCacheObject(sql);
			if(cacheObjects!=null) return cacheObjects;
		}		
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();	
		while(rst.next()){						
			try {
				list.add(objectUtil.getObject(rst, returnClass, resultMap));
			} catch (MappingException e) {			
				e.printStackTrace();
			}					
		}	
		rst.close();
		if("true".equals(useQueryCache)){
			caheManager.putCacheObject(sql, list);
		}	
		return list;
	}
	/**
	 * 执行给定的SQL语句,该语句返回分页结果集	
	 * @param sql
	 * @param parameterClass
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws SQLException 
	 */
	public List queryForList(String sql,Object parameterClass,String returnClass,String resultMap) throws SQLException{
		
		SqlParser sqlParser=new SqlParserImpl();
		sql=sqlParser.parseQuerySQL(sql, parameterClass, resultMap);
		List list=new ArrayList();
		ObjectUtil objectUtil=new ObjectUtil();
		//是否采用分页查询数据
		if(queryParam.getMaxResult()>0){		
			Resource resource=ResourceFactory.getInstance();			
			try {
				Metadata metadata= resource.getMetadata(resultMap);
				useQueryCache=metadata.getUseQueryCache();
				Identifier identifier=metadata.getIdentifier();
				queryParam.setTable(metadata.getTable());
				queryParam.setPrimary(identifier.getProperty().getColumn().toLowerCase());
			} catch (MappingException e) {			
				e.printStackTrace();
			}
			//设置分页数据最大页
			IdentifierGenerator generator=new IdentifierGenerator(connection);
			int rownum=generator.getROWNUM(sql);			
			if(rownum>0){
				if(rownum%queryParam.getPageSize()==0){
					queryParam.setMaxPage(rownum/queryParam.getPageSize());			
				}else
					queryParam.setMaxPage(rownum/queryParam.getPageSize()+1);								
			}else
				queryParam.setMaxPage(1);	
			//分别组装oracle,mssql,mysql数据库方言的分页查询语句
			if("oracle".equals(Environment.dialect)){			
				sql="select * from (select rownum r,t1.* from ("+sql+") t1 where rownum<="+queryParam.getMaxResult()+") t2 where r>="+queryParam.getFirstResult();
			}else if("mssql".equals(Environment.dialect)){				
				if("2000".equals(Environment.dialect_version)){
					sql="select * from ( select top "+queryParam.getPageSize()+" * from ("+sql+") t1 where ("+queryParam.getPrimary()+" not in (select top "+(queryParam.getFirstResult()-1)+" "+queryParam.getPrimary()+" from "+queryParam.getTable()+"))) t2";
				}else if("2005".equals(Environment.dialect_version)){
					sql="select * from (select row_number() over(order by"+queryParam.getPrimary()+") r,t1.* from ("+sql+") t1 ) t2 where r between "+queryParam.getFirstResult()+" and "+queryParam.getMaxResult();
				}			
			}else if("mysql".equals(Environment.dialect)){
				sql="select * from (select t1.* from ("+sql+") t1) limit "+queryParam.getMaxResult()+","+queryParam.getFirstResult();
			}else if("db2".equals(Environment.dialect)){
				
			}else if("kingbase".equals(Environment.dialect)){
				sql="select * from (select t1.* from ("+sql+") t1) limit "+queryParam.getPageSize()+" offset "+(queryParam.getFirstResult()-1);
			}	
		}
		
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		
		if("true".equals(useQueryCache)){
			list=(ArrayList)caheManager.getCacheObject(sql);
			if(list!=null) return list;
		}		
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();	
		while(rst.next()){						
			try {
				list.add(objectUtil.getObject(rst, returnClass, resultMap));
			} catch (MappingException e) {			
				e.printStackTrace();
			}					
		}	
		rst.close();
		if("true".equals(useQueryCache)){
			caheManager.putCacheObject(sql, list);
		}		
		return list;
	}
	/**
	 * 设置分页查询参数对象
	 * @param queryParam
	 * @return
	 */
	public void setQueryParam(QueryParam queryParam){
		this.queryParam=queryParam;
	}
	/**
	 * 得到分页查询参数对象
	 * @return
	 */
	public QueryParam getQueryParam(){
		return queryParam;
	}
	
}
