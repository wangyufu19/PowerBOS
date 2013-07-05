package com.sqlMap.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import com.sqlMap.Query;
import com.sqlMap.QueryPage;
import com.sqlMap.SqlMapClient;
import com.sqlMap.Transaction;
import com.sqlMap.cfg.Resource;
import com.sqlMap.cfg.ResourceFactory;
import com.sqlMap.exception.MappingException;
import com.sqlMap.id.Identifier;
import com.sqlMap.id.IdentifierGenerator;
import com.sqlMap.log.LogFactory;
import com.sqlMap.log.Logger;
import com.sqlMap.log.DBLogger;
import com.sqlMap.parser.SqlParser;
import com.sqlMap.pool.ConnectionPoolManager;
import com.sqlMap.property.BeanFactory;
import com.sqlMap.property.Metadata;
import com.sqlMap.property.Setter;
/**
 * SqlMapClient实现类
 * @author youfu.wang
 * @version 1.0
 */
public class SqlMapClientImpl implements SqlMapClient{
	private static final Logger log=LogFactory.getInstance();
	private ConnectionPoolManager connectionPoolManager=ConnectionPoolManager.getInstance();
	private Connection connection=null;
	private PreparedStatement pstmt;
	private CallableStatement cstmt;
	
	public SqlMapClientImpl(Connection connection){
		this.connection=connection;
	}	
	/**
	 * 开始事务,并且返回事务对象
	 * @return
	 */
	public Transaction beginTransaction() throws SQLException{
		connection.setAutoCommit(false);
		Transaction tx=new TransactionImpl(connection);
		return tx;
	}
	/**
	 * 创建查询对象
	 * @return
	 */
	public synchronized Query createQuery(){
		Query query=new QueryImpl(connection);
		return query;
	}
	/**
	 * 创建分页查询对象
	 * @return
	 */
	public synchronized QueryPage createQueryPage(){
		QueryPage queryPage=new QueryPageImpl(connection);
		return queryPage;
	}
	/**
     * 根据实体对象和sqlMapId,得到持久化对象
     * @param obj 实体对象
     * @param sqlMapId
     * @return
     * @throws SQLException    
     */
	public Object get(Object obj,String sqlMapId) throws SQLException{
		Query query=new QueryImpl(connection);	
		return query.getUniqueResult(obj, sqlMapId);
		
	}
    /**
     * 根据Map参数和sqlMapId,得到持久化对象
     * @param params 参数集合
     * @param sqlMapId
     * @return
     * @throws SQLException    
     */
	public Object get(Map params,String sqlMapId) throws SQLException{
		Query query=new QueryImpl(connection);		
		return query.getUniqueResult(params, sqlMapId);		
	}
	/**
	 * 根据实体对象和sqlMapId,持久化对象
	 * @param obj
	 * @param sqlMapId
	 * @throws SQLException
	 */
	public synchronized void insert(Object obj,String sqlMapId) throws SQLException{	
		Long id=0L;
		String sql="";
		SqlParser sqlParser=new SqlParserImpl();	
		try {
			Resource resource=ResourceFactory.getInstance();			
			Map statement=resource.getStatementMap(sqlMapId);
			String resultMap=String.valueOf(statement.get("resultMap"));
			Metadata metadata=resource.getMetadata(sqlMapId, resultMap);	
			sql = sqlParser.parseExecuteSQL(obj, metadata, statement);	
			Identifier identifier=metadata.getIdentifier();
			//insert语句,根本主键标识配置得到序列ID号			
			IdentifierGenerator generator=new IdentifierGenerator(connection);
			id=generator.getID(sqlMapId);			
			Setter setter=BeanFactory.getSetter(obj.getClass(), identifier.getProperty().getName());
			setter.set(obj,id);				
			//组装SQL语句				
			sql = sqlParser.parseExecuteSQL(obj, metadata, statement);
		} catch (MappingException e) {			
			e.printStackTrace();
		}
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		pstmt.executeUpdate();	
		if(DBLogger.sql_log.equals("true"))
			DBLogger.saveLog(connection, pstmt, sql);
		pstmt.close();
	}
	/**
	 * 根据Map参数对象和sqlMapId,持久化对象
	 * @param params
	 * @param sqlMapId
	 * @throws SQLException
	 */
	public synchronized void insert(Map params,String sqlMapId) throws SQLException{
		Long id=0L;
		String sql="";
		try {
			Resource resource=ResourceFactory.getInstance();			
			Map statement=resource.getStatementMap(sqlMapId);
			String resultMap=String.valueOf(statement.get("resultMap"));
			Metadata metadata=resource.getMetadata(sqlMapId, resultMap);		
			Identifier identifier=metadata.getIdentifier();
			//insert语句,根据主键标识配置得到序列ID号			
			IdentifierGenerator generator=new IdentifierGenerator(connection);
			id=generator.getID(sqlMapId);
			params.put(identifier.getProperty().getName(), id);		
			//组装SQL语句
			SqlParser sqlParser=new SqlParserImpl();
			sql=sqlParser.parseQuerySQL(params, sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
		}
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		pstmt.executeUpdate();	
		if(DBLogger.sql_log.equals("true"))
			DBLogger.saveLog(connection, pstmt, sql);
		pstmt.close();
	}
	/**
	 * 根据实体对象和sqlMapId,持久化对象
	 * @param obj 实体对象
	 * @param sqlMapId	
	 * @throws SQLException 
	 */
	public synchronized void update(Object obj,String sqlMapId) throws SQLException{
		SqlParser sqlParser=new SqlParserImpl();
		String sql="";		
		try {			
			Resource resource=ResourceFactory.getInstance();			
			Map statement=resource.getStatementMap(sqlMapId);			
			String resultMap=String.valueOf(statement.get("resultMap"));
			Metadata metadata=resource.getMetadata(sqlMapId, resultMap);	
			sql = sqlParser.parseExecuteSQL(obj, metadata, statement);
		} catch (MappingException e) {			
			e.printStackTrace();
		}	
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		pstmt.executeUpdate();	
		if(DBLogger.sql_log.equals("true"))
			DBLogger.saveLog(connection, pstmt, sql);
		pstmt.close();
	}
	/**
	 * 根据实体对象和sqlMapId,持久化对象
	 * @param obj 实体对象
	 * @param sqlMapId	
	 * @throws SQLException 
	 */
	public void delete(Object obj,String sqlMapId) throws SQLException{
		SqlParser sqlParser=new SqlParserImpl();
		String sql="";		
		try {			
			Resource resource=ResourceFactory.getInstance();			
			Map statement=resource.getStatementMap(sqlMapId);		
			String resultMap=String.valueOf(statement.get("resultMap"));
			Metadata metadata=resource.getMetadata(sqlMapId, resultMap);			
			sql = sqlParser.parseExecuteSQL(obj, metadata, statement);
		} catch (MappingException e) {			
			e.printStackTrace();
		}	
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		pstmt.executeUpdate();	
		if(DBLogger.sql_log.equals("true"))
			DBLogger.saveLog(connection, pstmt, sql);
		pstmt.close();
	}
	/**
	 * 根据sqlMapId,持久化对象
	 * @param obj 实体对象
	 * @param sqlMapId	
	 * @throws SQLException 
	 */
	public synchronized void execute(String sqlMapId) throws SQLException{
		SqlParser sqlParser=new SqlParserImpl();
		String sql="";	
		try {
			sql=sqlParser.parseExecuteSQL(sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
		}
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		pstmt.executeUpdate();		
		if(DBLogger.sql_log.equals("true"))
			DBLogger.saveLog(connection, pstmt, sql);			
		pstmt.close();
	}
	/**
	 * 根据实体对象和sqlMapId,持久化对象
	 * @param obj 实体对象
	 * @param sqlMapId	
	 * @throws SQLException 
	 */
	public synchronized void execute(Object obj,String sqlMapId) throws SQLException{		
		SqlParser sqlParser=new SqlParserImpl();
		String sql="";			
		try {			
			Resource resource=ResourceFactory.getInstance();			
			Map statement=resource.getStatementMap(sqlMapId);		
			String resultMap=String.valueOf(statement.get("resultMap"));
			String executeType=String.valueOf(statement.get("executeType"));
			Metadata metadata=resource.getMetadata(sqlMapId, resultMap);			
			//insert语句,根据主键标识配置得到序列ID号			
			if(DBLogger.insert.equals(executeType)){
				Identifier identifier=metadata.getIdentifier();
				IdentifierGenerator identifyGenerator=new IdentifierGenerator(connection);
				String generator=identifier.getGenerator();
				Object id=null;
				if("assigned".equals(generator)){
					id=identifyGenerator.getID(metadata);
				}else if("uuid".equals(generator)){
					id=identifyGenerator.getUUID();
				}								
				Setter setter=BeanFactory.getSetter(obj.getClass(), identifier.getProperty().getName());
				setter.set(obj,id);				
			}			
			//组装SQL语句			
			sql = sqlParser.parseExecuteSQL(obj, sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
		}	
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		pstmt.executeUpdate();		
		if(DBLogger.sql_log.equals("true"))
			DBLogger.saveLog(connection, pstmt, sql);			
		pstmt.close();
	}
	/**
	 * 根据Map参数和sqlMapId,持久化对象
	 * @param params
	 * @param sqlMapId
	 * @throws SQLException
	 */
	public synchronized void execute(Map params,String sqlMapId) throws SQLException{	
		SqlParser sqlParser=new SqlParserImpl();
		String sql="";
		
		try {			
			Resource resource=ResourceFactory.getInstance();			
			Map statement=resource.getStatementMap(sqlMapId);	
			String executeType=String.valueOf(statement.get("executeType"));
			String resultMap=String.valueOf(statement.get("resultMap"));
			Metadata metadata=resource.getMetadata(sqlMapId, resultMap);			
			//insert语句,根本主键标识配置得到序列ID号			
			if(DBLogger.insert.equals(executeType)){
				Identifier identifier=metadata.getIdentifier();
				IdentifierGenerator identifyGenerator=new IdentifierGenerator(connection);
				String generator=identifier.getGenerator();
				Object id=null;
				if("assigned".equals(generator)){
					id=identifyGenerator.getID(metadata);
				}else if("uuid".equals(generator)){
					id=identifyGenerator.getUUID();
				}							
				params.put(identifier.getProperty().getName(), id);		
			}
			//组装SQL语句			
			sql=sqlParser.parseQuerySQL(params, sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
		}
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);	
		pstmt=connection.prepareStatement(sql);	
		pstmt.executeUpdate();	
		
		if(DBLogger.sql_log.equals("true"))
			DBLogger.saveLog(connection, pstmt, sql);			
		pstmt.close();
	}		
	/**
	 * 根据实体对象和sqlMapId,调用存储过程或函数
	 * @param obj
	 * @param sqlMapId
	 * @throws SQLException
	 */
	public synchronized void namedExecute(Object obj,String sqlMapId) throws SQLException{
		SqlParser sqlParser=new SqlParserImpl();
		String sql="";
		try {
			sql=sqlParser.parseQuerySQL(obj, sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
		}
		cstmt=connection.prepareCall(sql);
		cstmt.execute();	
		cstmt.close();
	}
	/**
	 * 根据Map参数和sqlMapId,调用存储过程或函数
	 * @param obj
	 * @param sqlMapId
	 * @throws SQLException
	 */
	public synchronized void namedExecute(Map params,String sqlMapId) throws SQLException{
		SqlParser sqlParser=new SqlParserImpl();
		String sql="";
		try {
			sql=sqlParser.parseQuerySQL(params, sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
		}
		cstmt=connection.prepareCall(sql);
		cstmt.execute();	
		cstmt.close();
	}
	/**
	 * 关闭一个连接
	 * @throws SQLException 
	 */
	public void close() throws SQLException{	
		if(connection!=null){
			connectionPoolManager.freeConnection(connection);
		}
		
	}
}
