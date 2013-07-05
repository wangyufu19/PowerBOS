package com.sqlMap.id;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;
import com.sqlMap.cfg.Resource;
import com.sqlMap.cfg.ResourceFactory;
import com.sqlMap.exception.MappingException;
import com.sqlMap.impl.SqlParserImpl;
import com.sqlMap.log.DBLogger;
import com.sqlMap.log.Logger;
import com.sqlMap.log.LogFactory;
import com.sqlMap.parser.SqlParser;
import com.sqlMap.property.Metadata;
/**
 * 主键标识符生成器
 * @author youfu.wang
 * @version 1.0
 */
public class IdentifierGenerator {
	private static final Logger log=LogFactory.getInstance();
	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet rst;	

	
	public IdentifierGenerator(){
		
	}
	public IdentifierGenerator(Connection connection){
		this.connection=connection;
	}

	/**
	 * 得到全局标识符
	 * @param table
	 * @param primary
	 * @return
	 * @throws SQLException
	 */
	public Long getID(String table,String primary) throws SQLException{
		Long id=0L;
		String sql="select max("+primary+") from "+table;
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();	
		while(rst.next()){			
			if(rst.getLong(1)==0L){				
				id=id+1L;
			}else
				id=rst.getLong(1)+1L;
			break;
		}
		pstmt.close();
		rst.close();
		return id;
	}
	/**
	 * 得到全局标识符
	 * @param table
	 * @param primary
	 * @param prefix
	 * @param length
	 * @return
	 * @throws SQLException
	 */
	public Long getID(String table,String primary,long prefix,long length) throws SQLException{
		Long id=0L;
		String sql="select max("+primary+") from "+table;
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();	
		while(rst.next()){			
			if(rst.getLong(1)==0L){							
				for(int i=0;i<length;i++){
					id=prefix*10;
					prefix=prefix*10;				
					if(String.valueOf(id).length()>=length){
						break;
					}
				}
				id=id+1L;
			}else
				id=rst.getLong(1)+1L;
			break;
		}
		pstmt.close();
		rst.close();
		return id;
	}
	/**
	 * 得到序列号
	 * @param sqlMapId
	 * @return
	 * @throws SQLException
	 */
	public Long getID(String sqlMapId) throws SQLException{
		Long id=0L;
		String table="";
		String primay="";			
		try {
			Resource resource=ResourceFactory.getInstance();
			Map statement= resource.getStatementMap(sqlMapId);			
			String resultMap=String.valueOf(statement.get("resultMap"));
			Metadata metadata=resource.getMetadata(sqlMapId, resultMap);			
			table=metadata.getTable();
			primay=String.valueOf(metadata.getIdentifier().getProperty().getColumn());
		} catch (MappingException e) {			
			e.printStackTrace();
		}				
		String sql="select max("+primay+") from "+table;
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();	
		while(rst.next()){			
			if(rst.getLong(1)==0L){			
				id=id+1L;
			}else
				id=rst.getLong(1)+1L;
			break;
		}
		pstmt.close();
		rst.close();
		return id;
	}
	/**
	 * 得到序列号
	 * @param sqlMapId
	 * @param prefix
	 * @param length
	 * @return
	 * @throws SQLException
	 */
	public Long getID(String sqlMapId,long prefix,long length) throws SQLException{
		Long id=0L;
		String table="";
		String primay="";			
		try {
			Resource resource=ResourceFactory.getInstance();
			Map statement= resource.getStatementMap(sqlMapId);			
			String resultMap=String.valueOf(statement.get("resultMap"));
			Metadata metadata=resource.getMetadata(sqlMapId, resultMap);			
			table=metadata.getTable();
			primay=String.valueOf(metadata.getIdentifier().getProperty().getColumn());
		} catch (MappingException e) {			
			e.printStackTrace();
		}				
		String sql="select max("+primay+") from "+table;
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();	
		while(rst.next()){			
			if(rst.getLong(1)==0L){			
				for(int i=0;i<length;i++){
					id=prefix*10;
					prefix=prefix*10;				
					if(String.valueOf(id).length()>=length){
						break;
					}
				}
				id=id+1L;
			}else
				id=rst.getLong(1)+1L;
			break;
		}
		pstmt.close();
		rst.close();
		return id;
	}
	/**
	 * 得到全局标识符
	 * @param metadata
	 * @return
	 * @throws SQLException
	 */
	public Object getID(Metadata metadata) throws SQLException{
		Long id=0L;
		String table="";
		String primay="";	
		table=metadata.getTable();
		primay=String.valueOf(metadata.getIdentifier().getProperty().getColumn());						
		String sql="select max("+primay+") from "+table;
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();	
		while(rst.next()){			
			if(rst.getLong(1)==0L){					
				id=id+1L;
			}else
				id=rst.getLong(1)+1L;
			break;
		}
		pstmt.close();
		rst.close();
		return id;
	}
	/**
	 * 得到全局标识符
	 * @param metadata
	 * @param prefix
	 * @param length
	 * @return
	 * @throws SQLException
	 */
	public Object getID(Metadata metadata,long prefix,long length) throws SQLException{
		Long id=0L;
		String table="";
		String primay="";	
		table=metadata.getTable();
		primay=String.valueOf(metadata.getIdentifier().getProperty().getColumn());						
		String sql="select max("+primay+") from "+table;
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();	
		while(rst.next()){			
			if(rst.getLong(1)==0L){	
				for(int i=0;i<length;i++){
					id=prefix*10;
					prefix=prefix*10;				
					if(String.valueOf(id).length()>=length){
						break;
					}
				}
				id=id+1L;
			}else
				id=rst.getLong(1)+1L;
			break;
		}
		pstmt.close();
		rst.close();
		return id;
	}
	/**
	 * 得到全局标识符
	 * @return
	 */
	public Object getUUID(){
		UUID uuid = UUID.randomUUID(); 
		return uuid.toString();
	}
	/**
	 * 得到最大行,同时兼容oracle和sql server
	 * @param params
	 * @param sqlMapId
	 * @throws SQLException 
	 */
	public int getMaxRow(Map params,String sqlMapId) throws SQLException{
		int rownum=0;
		String sql="";
		SqlParser sqlParser=new SqlParserImpl();		
		try {
			sql=sqlParser.getSQLForMaxRow(params, sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
		}
		sql="select count(*) from ("+sql+") t1";
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();	
		while(rst.next()){			
			rownum=rst.getInt(1);
			break;		
		}
		pstmt.close();
		rst.close();
		return rownum;
	}
	/**
	 * 得到最大行,同时兼容oracle和sql server
	 * @param obj
	 * @param sqlMapId
	 * @throws SQLException 
	 */

	public int getMaxRow(Object obj,String sqlMapId) throws SQLException{
		int rownum=0;
		String sql="";
		SqlParser sqlParser=new SqlParserImpl();		
		try {
			sql=sqlParser.getSQLForMaxRow(obj, sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
		}
		sql="select count(*) from ("+sql+") t1";
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();	
		while(rst.next()){			
			rownum=rst.getInt(1);
			break;		
		}
		pstmt.close();
		rst.close();
		return rownum;
	}
	/**
	 * 得到最大行,同时兼容oracle和sql server
	 * @param sqlMapId
	 * @return
	 * @throws SQLException
	 */
	public int getMaxRow(String sqlMapId) throws SQLException{
		int rownum=0;
		String sql="";
		SqlParser sqlParser=new SqlParserImpl();	
		try {
			sql=sqlParser.getSQLForMaxRow(sqlMapId);
		} catch (MappingException e) {			
			e.printStackTrace();
		}		
		sql="select count(*) from ("+sql+") t1";
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();	
		while(rst.next()){			
			rownum=rst.getInt(1);
			break;		
		}
		pstmt.close();
		rst.close();
		return rownum;
	}
	/**
	 * 得到最大行数
	 * @param sqlMapId
	 * @return
	 * @throws SQLException
	 */
	public int getROWNUM(String sql) throws SQLException{
		int rownum=0;	
		sql="select count(*) from ("+sql+")";
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		rst=pstmt.executeQuery();	
		while(rst.next()){			
			rownum=rst.getInt(1);
			break;
		}
		pstmt.close();
		rst.close();
		return rownum;
	}
}
