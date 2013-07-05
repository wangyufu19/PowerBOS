package com.sqlMap.impl;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sqlMap.Query;
import com.sqlMap.exception.MappingException;
import com.sqlMap.impl.SqlParserImpl;
import com.sqlMap.log.DBLogger;
import com.sqlMap.log.Logger;
import com.sqlMap.log.LogFactory;
import com.sqlMap.parser.SqlParser;
import com.sqlMap.util.ObjectUtil;

/**
 * 数据库查询实现类
 * @author youfu.wang
 * @version 1.0
 */
public class QueryImpl implements Query{	
	private static final Logger log=LogFactory.getInstance();
	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet rst;

	private String sql;
	
	public QueryImpl(Connection connection){	
		this.connection=connection;		
	}		
	/**
	 * 得到数据数组对象
	 * @param sqlMapId
	 * @return
	 * @throws SQLException
	 */
	public Object[] getArray(String sqlMapId) throws SQLException{		
		ObjectUtil objectUtil=new ObjectUtil();
		SqlParser sqlParser=new SqlParserImpl();		
		try {						
			sql=sqlParser.parseQuerySQL(sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
		}			
	   
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);		
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();				
		while(rst.next()){	
			Object[] tmpObj=null;
			tmpObj=objectUtil.getObjectArray(rst);	
			pstmt.close();
			rst.close();
			return tmpObj;
		}	
		return null;
	}
	/**
	 * 得到数据数组对象
	 * @param params
	 * @param sqlMapId	 
	 * @return
	 * @throws SQLException
	 */
	public Object[] getArray(Map params,String sqlMapId) throws SQLException{		
		ObjectUtil objectUtil=new ObjectUtil();
		SqlParser sqlParser=new SqlParserImpl();		
		try {						
			sql=sqlParser.parseQuerySQL(params, sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
		}			
	   
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);		
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();				
		while(rst.next()){	
			Object[] tmpObj=null;
			tmpObj=objectUtil.getObjectArray(rst);	
			pstmt.close();
			rst.close();
			return tmpObj;
		}	
		return null;
	}
	/**
	 * 得到数据数组对象
	 * @param obj
	 * @param sqlMapId
	 * @return
	 * @throws SQLException
	 */
	public Object[] getArray(Object obj,String sqlMapId) throws SQLException{		
		ObjectUtil objectUtil=new ObjectUtil();
		SqlParser sqlParser=new SqlParserImpl();		
		try {						
			sql=sqlParser.parseQuerySQL(obj, sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
		}			
	   
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);		
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();				
		while(rst.next()){	
			Object[] tmpObj=null;
			tmpObj=objectUtil.getObjectArray(rst);	
			pstmt.close();
			rst.close();
			return tmpObj;
		}	
		return null;
	}	
	/**
	 * 得到数据集合
	 * @param sqlMapId
	 * @return
	 * @throws SQLException
	 */
	public List iterator(String sqlMapId) throws SQLException{
		List list=new ArrayList();				
		ObjectUtil objectUtil=new ObjectUtil();
		SqlParser sqlParser=new SqlParserImpl();		
		try {						
			sql=sqlParser.parseQuerySQL(sqlMapId);	
		} catch (MappingException e) {			
			e.printStackTrace();
		}		    
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);		
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();				
		while(rst.next()){					
			list.add(objectUtil.getObjectArray(rst));			
		}
		pstmt.close();
		rst.close();
		return list;
	}
	/**
	 * 得到数据集合
	 * @param params
	 * @param sqlMapId
	 * @return
	 * @throws SQLException
	 */
	public List iterator(Map params,String sqlMapId) throws SQLException{
		List list=new ArrayList();				
		ObjectUtil objectUtil=new ObjectUtil();
		SqlParser sqlParser=new SqlParserImpl();		
		try {						
			sql=sqlParser.parseQuerySQL(params, sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
		}		    
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);		
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();				
		while(rst.next()){					
			list.add(objectUtil.getObjectArray(rst));			
		}
		pstmt.close();
		rst.close();
		return list;
	}
	/**
	 * 得到数据集合
	 * @param obj
	 * @param sqlMapId
	 * @return
	 * @throws SQLException
	 */
	public List iterator(Object obj,String sqlMapId) throws SQLException{
		List list=new ArrayList();				
		ObjectUtil objectUtil=new ObjectUtil();
		SqlParser sqlParser=new SqlParserImpl();		
		try {						
			sql=sqlParser.parseQuerySQL(obj, sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
		}		    
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);		
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();				
		while(rst.next()){					
			list.add(objectUtil.getObjectArray(rst));			
		}
		pstmt.close();
		rst.close();
		return list;
	}	
	/**
	 * 得到数据集合列表
	 * @param sqlMapId
	 * @return 	 
	 */
	public synchronized List list(String sqlMapId) throws SQLException{
		List list=new ArrayList();		
		SqlParser sqlParser=new SqlParserImpl();
		ObjectUtil objectUtil=new ObjectUtil();
		try {			
			sql=sqlParser.parseQuerySQL(sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
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
		pstmt.close();
		rst.close();
		return list;
	}
	/**
	 * 得到数据集合列表
	 * @param params
	 * @param sqlMapId
	 * @return
	 */
	public synchronized List list(Map params,String sqlMapId) throws SQLException{
		List list=new ArrayList();
		ObjectUtil objectUtil=new ObjectUtil();		
		SqlParser sqlParser=new SqlParserImpl();
		try {		
			sql=sqlParser.parseQuerySQL(params, sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
		}
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();			
		while(rst.next()){			
			try {
				list.add(objectUtil.getObject(rst, sqlMapId));
			} catch (MappingException e) {			
				e.printStackTrace();
			}
		}
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt.close();
		rst.close();
		return list;
	}
	/**
	 * 得到数据集合实体列表
	 * @param obj 
	 * @param sqlMapId 
	 * @return
	 */
	public synchronized List list(Object obj,String sqlMapId) throws SQLException{
		List list=new ArrayList();
		ObjectUtil entityUtil=new ObjectUtil();		
		SqlParser sqlParser=new SqlParserImpl();
		try {			
			sql=sqlParser.parseQuerySQL(obj,sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
		}				
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();	
		while(rst.next()){			
			Object entity=new Object();
			try {
				entity = entityUtil.getObject(rst, sqlMapId);
			} catch (MappingException e) {					
				e.printStackTrace();
			}
			list.add(entity);					
		}	
		return list;
	}		
	/**
	 * 得到实体对象数组
	 * @param params
	 * @param sqlMapId
	 * @return
	 */
	public Object getUniqueResult(Map params,String sqlMapId) throws SQLException{		
		SqlParser sqlParser=new SqlParserImpl();
		try {
			sql=sqlParser.parseQuerySQL(params, sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
		}	
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();				
		ObjectUtil objectUtil=new ObjectUtil();		
		while(rst.next()){				
			Object obj=null;
			try {
				obj=objectUtil.getObject(rst, sqlMapId);
			} catch (MappingException e) {			
				e.printStackTrace();
			}		
			return obj;			
		}		
		pstmt.close();
		rst.close();
		return null;
	}	
	/**
	 * 得到实体对象
	 * @param obj
	 * @param sqlMapId
	 * @return
	 */
	public Object getUniqueResult(Object obj,String sqlMapId) throws SQLException{		
		SqlParser sqlParser=new SqlParserImpl();
		try {						
			sql=sqlParser.parseQuerySQL(obj,sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
			return null;
		}	
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();			
		ObjectUtil objectUtil=new ObjectUtil();		
		while(rst.next()){					
			try {
				obj=objectUtil.getObject(rst, sqlMapId);
			} catch (MappingException e) {				
				e.printStackTrace();
			}	
			pstmt.close();
			rst.close();
			return obj;
		}			
		return null;
	}		
	
}
