package com.sqlMap.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sqlMap.QueryPage;
import com.sqlMap.QueryParam;
import com.sqlMap.exception.MappingException;
import com.sqlMap.id.IdentifierGenerator;
import com.sqlMap.log.DBLogger;
import com.sqlMap.log.Logger;
import com.sqlMap.log.LogFactory;
import com.sqlMap.parser.SqlParser;
import com.sqlMap.util.ObjectUtil;
/**
 * 分页查询实现类
 * @author wangyf
 * @version 1.0
 */
public class QueryPageImpl implements QueryPage{
	private static final Logger log=LogFactory.getInstance();
	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet rst;
	private QueryParam queryParam;
	
	public QueryPageImpl(Connection connection){
		this.connection=connection;
	}
	/**
	 * 得到分页数据集合
	 * @param sqlMapId
	 * @return
	 * @throws SQLException 
	 */
	public List queryForList(String sqlMapId) throws SQLException{
		List list=new ArrayList();				
		String sql="";
		ObjectUtil objectUtil=new ObjectUtil();
		SqlParser sqlParser=new SqlParserImpl();					
		//是否采用分页查询数据
		if(queryParam.getMaxResult()>0){		
			try {
				sql=sqlParser.parseSQLForQueryPage(sqlMapId, queryParam);
			} catch (MappingException e) {				
				e.printStackTrace();
			}
			//设置分页数据最大页
			IdentifierGenerator generator=new IdentifierGenerator(connection);
			int rownum=generator.getMaxRow(sqlMapId);				
			if(rownum>0){
				if(rownum%queryParam.getPageSize()==0){
					queryParam.setMaxPage(rownum/queryParam.getPageSize());			
				}else
					queryParam.setMaxPage(rownum/queryParam.getPageSize()+1);								
			}else
				queryParam.setMaxPage(1);	
		}else{
			try {
				sql=sqlParser.parseQuerySQL(sqlMapId);
			} catch (MappingException e) {				
				e.printStackTrace();
			}
		}
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();	
		while(rst.next()){			
			Object entity=new Object();
			try {
				entity = objectUtil.getObject(rst, sqlMapId);
			} catch (MappingException e) {					
				e.printStackTrace();
			}
			list.add(entity);					
		}	
		rst.close();
		return list;
	}
	/**
	 * 得到分页数据集合
	 * @param params
	 * @param sqlMapId
	 * @return
	 * @throws SQLException 
	 */
	public List queryForList(Map params,String sqlMapId) throws SQLException{
		List list=new ArrayList();				
		String sql="";
		ObjectUtil objectUtil=new ObjectUtil();
		SqlParser sqlParser=new SqlParserImpl();					
		//是否采用分页查询数据
		if(queryParam.getMaxResult()>0){		
			try {
				sql=sqlParser.parseSQLForQueryPage(params, sqlMapId, queryParam);
			} catch (MappingException e) {				
				e.printStackTrace();
			}
			//设置分页数据最大页
			IdentifierGenerator generator=new IdentifierGenerator(connection);
			int rownum=generator.getMaxRow(params,sqlMapId);
			if(rownum>0){
				if(rownum%queryParam.getPageSize()==0){
					queryParam.setMaxPage(rownum/queryParam.getPageSize());			
				}else
					queryParam.setMaxPage(rownum/queryParam.getPageSize()+1);								
			}else
				queryParam.setMaxPage(1);	
		}else{
			try {
				sql=sqlParser.parseQuerySQL(params,sqlMapId);
			} catch (MappingException e) {				
				e.printStackTrace();
			}
		}
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();	
		while(rst.next()){			
			Object entity=new Object();
			try {
				entity = objectUtil.getObject(rst, sqlMapId);
			} catch (MappingException e) {					
				e.printStackTrace();
			}
			list.add(entity);					
		}	
		rst.close();
		return list;
	}
	/**
	 * 得到分页数据集合
	 * @param obj
	 * @param sqlMapId
	 * @return
	 * @throws SQLException 
	 */
	public List queryForList(Object obj,String sqlMapId) throws SQLException{
		List list=new ArrayList();				
		String sql="";
		ObjectUtil objectUtil=new ObjectUtil();
		SqlParser sqlParser=new SqlParserImpl();			
		//是否采用分页查询数据
		if(queryParam.getMaxResult()>0){		
			try {
				sql=sqlParser.parseSQLForQueryPage(obj, sqlMapId, queryParam);
			} catch (MappingException e) {				
				e.printStackTrace();
			}
			//设置分页数据最大页
			IdentifierGenerator generator=new IdentifierGenerator(connection);		
			int rownum=generator.getMaxRow(obj,sqlMapId);		
			if(rownum>0){
				if(rownum%queryParam.getPageSize()==0){
					queryParam.setMaxPage(rownum/queryParam.getPageSize());			
				}else
					queryParam.setMaxPage(rownum/queryParam.getPageSize()+1);								
			}else
				queryParam.setMaxPage(1);	
		}else{
			try {
				sql=sqlParser.parseQuerySQL(obj,sqlMapId);
			} catch (MappingException e) {				
				e.printStackTrace();
			}
		}
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();	
		while(rst.next()){			
			Object entity=new Object();
			try {
				entity = objectUtil.getObject(rst, sqlMapId);
			} catch (MappingException e) {					
				e.printStackTrace();
			}
			list.add(entity);					
		}	
		rst.close();
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
//	public void setFirstResult(int firstResult){
//		queryParam.setFirstResult(firstResult);
//	}
//	public void setMaxResult(int maxResult){
//		queryParam.setMaxResult(maxResult);
//	}
//	public void setPageSize(int pageSize){
//		queryParam.setPageSize(pageSize);
//	}
}
