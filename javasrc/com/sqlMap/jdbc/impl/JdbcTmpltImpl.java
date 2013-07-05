package com.sqlMap.jdbc.impl;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.sqlMap.Transaction;
import com.sqlMap.cfg.Environment;
import com.sqlMap.cfg.Resource;
import com.sqlMap.cfg.ResourceFactory;
import com.sqlMap.exception.MappingException;
import com.sqlMap.id.Identifier;
import com.sqlMap.id.IdentifierGenerator;
import com.sqlMap.impl.SqlParserImpl;
import com.sqlMap.impl.TransactionImpl;
import com.sqlMap.jdbc.JdbcTmplt;
import com.sqlMap.jdbc.QueryTmplt;
import com.sqlMap.jdbc.StatementTmplt;
import com.sqlMap.jdbc.impl.QueryTmpltImpl;
import com.sqlMap.log.DBLogger;
import com.sqlMap.log.Logger;
import com.sqlMap.log.LogFactory;
import com.sqlMap.parser.SqlParser;
import com.sqlMap.pool.ConnectionPoolManager;
import com.sqlMap.property.BeanFactory;
import com.sqlMap.property.Metadata;
import com.sqlMap.property.Setter;

public class JdbcTmpltImpl implements JdbcTmplt{	
	private Connection connection;
	private PreparedStatement pstmt;
	private CallableStatement cstmt;
	private ResultSet rst;
	private ConnectionPoolManager connectionPoolManager=ConnectionPoolManager.getInstance();
	private static final Logger log=LogFactory.getInstance();
	
	public JdbcTmpltImpl(Connection connection){
		this.connection=connection;
	}
	/**
	 * 返回一个数据库连接
	 * @return
	 */
	public Connection getConnection(){
		return this.connection;
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
	 * 创建查询接口QueryTmplt
	 * @return
	 */
	public QueryTmplt createQueryTmplt(){
		QueryTmplt QueryTmplt=new QueryTmpltImpl(connection);
		return QueryTmplt;
	}	
	/**
	 * 执行给定的SQL语句,该语句为 INSERT 语句
	 * @param sql
	 * @throws SQLException 
	 */
	public synchronized void insert(String sql) throws SQLException{
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		pstmt.executeUpdate();
		if(DBLogger.sql_log.equals("true"))
			DBLogger.saveLog(connection, pstmt, sql);			
		pstmt.close();
	}
	/**
	 * 执行给定的SQL语句,该语句为 INSERT 语句
	 * @param sql
	 * @param params
	 * @param resultMap
	 * @throws SQLException 
	 */
	public synchronized void insert(String sql,Map params,String resultMap) throws SQLException{		
		SqlParser sqlParser=new SqlParserImpl();
		Resource resource=ResourceFactory.getInstance();
		try {
			Metadata metadata=resource.getMetadata(resultMap);
			Identifier identifier=metadata.getIdentifier();
			String generator=identifier.getGenerator();			
			//insert语句,根据主键标识配置得到序列ID号			
			Object id=null;			
			IdentifierGenerator identifyGenerator=new IdentifierGenerator(connection);
			if("assigned".equals(generator)){
				id=identifyGenerator.getID(metadata);
			}else if("uuid".equals(generator)){
				id=identifyGenerator.getUUID();
			}		
			params.put(identifier.getProperty().getName(), id);					
		} catch (MappingException e) {			
			e.printStackTrace();
		}	
		sql=sqlParser.parseExecuteSQL(sql, params, resultMap);
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		pstmt.executeUpdate();
		if(DBLogger.sql_log.equals("true"))
			DBLogger.saveLog(connection, pstmt, sql);			
		pstmt.close();
	}
	/**
	 * 执行给定的SQL语句,该语句为 INSERT 语句
	 * @param sql
	 * @param parameterClass
	 * @param resultMap
	 * @throws SQLException 
	 */
	public synchronized void insert(String sql,Object parameterClass,String resultMap) throws SQLException{
		SqlParser sqlParser=new SqlParserImpl();
		Resource resource=ResourceFactory.getInstance();
		try {
			Metadata metadata=resource.getMetadata(resultMap);
			Identifier identifier=metadata.getIdentifier();
			String generator=identifier.getGenerator();			
			//insert语句,根据主键标识配置得到序列ID号			
			Object id=null;			
			IdentifierGenerator identifyGenerator=new IdentifierGenerator(connection);
			if("assigned".equals(generator)){
				id=identifyGenerator.getID(metadata);
			}else if("uuid".equals(generator)){
				id=identifyGenerator.getUUID();
			}					
			Setter setter=BeanFactory.getSetter(parameterClass.getClass(), identifier.getProperty().getName());
			setter.set(parameterClass,id);					
		} catch (MappingException e) {			
			e.printStackTrace();
		}	
		sql=sqlParser.parseExecuteSQL(sql, parameterClass, resultMap);
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		pstmt.executeUpdate();
		if(DBLogger.sql_log.equals("true"))
			DBLogger.saveLog(connection, pstmt, sql);			
		pstmt.close();
	}
	/**
	 * 执行给定的SQL语句,该语句可能为 INSERT、UPDATE 或 DELETE 语句
	 * @param sql
	 * @throws SQLException
	 */
	public synchronized void execute(String sql) throws SQLException{
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		pstmt.executeUpdate();
		if(DBLogger.sql_log.equals("true"))
			DBLogger.saveLog(connection, pstmt, sql);			
		pstmt.close();
	}
	/**
	 * 执行给定的SQL语句,该语句可能为 INSERT、UPDATE 或 DELETE 语句
	 * 该SQL语句包含通配符,通过SQLMAP组件解析器组装该SQL语句
	 * @param sql
	 * @param params
	 * @param resultMap
	 * @throws SQLException 
	 */
	public synchronized void execute(String sql,Map params,String resultMap) throws SQLException{
		SqlParser sqlParser=new SqlParserImpl();
		sql=sqlParser.parseExecuteSQL(sql, params, resultMap);
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		pstmt.executeUpdate();
		if(DBLogger.sql_log.equals("true"))
			DBLogger.saveLog(connection, pstmt, sql);			
		pstmt.close();
	}
	/**
	 * 执行给定的SQL语句,该语句可能为 INSERT、UPDATE 或 DELETE 语句
	 * 该SQL语句包含通配符,通过SQLMAP组件解析器组装该SQL语句
	 * @param sql
	 * @param parameterClass
	 * @param resultMap
	 * @throws SQLException 
	 */
	public synchronized void execute(String sql,Object parameterClass,String resultMap) throws SQLException{
		SqlParser sqlParser=new SqlParserImpl();	
		sql=sqlParser.parseExecuteSQL(sql, parameterClass, resultMap);		
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);	
		pstmt.executeUpdate();
		if(DBLogger.sql_log.equals("true"))
			DBLogger.saveLog(connection, pstmt, sql);			
		pstmt.close();
	}
    /**
     * 执行给定的批量SQL语句,该语句可能为 INSERT、UPDATE 或 DELETE 语句
     * @param statementTmplt
     * @throws SQLException
     */
	public synchronized void executeBatch(StatementTmplt statementTmplt) throws SQLException{		
		List sqls=statementTmplt.getSQLList();
		if(sqls==null) return;
		String sql="";
		if(Environment.dialect.equals("oracle")){
			for(int i=0;i<sqls.size();i++){
				sql=String.valueOf(sqls.get(i));
				pstmt.addBatch(sql);
				if(DBLogger.show_sql.equals("true"))
					log.info("sql: "+sql);
			}
			pstmt.addBatch();
			pstmt.close();
		}else if(Environment.dialect.equals("mssql")){
			for(int i=0;i<sqls.size();i++){
				sql=String.valueOf(sqls.get(i));
				pstmt.addBatch(sql);
				if(DBLogger.show_sql.equals("true"))
					log.info("sql: "+sql);
				pstmt.execute(sql);
			}
			pstmt.close();
		}else if(Environment.dialect.equals("mysql")){
			for(int i=0;i<sqls.size();i++){
				sql=String.valueOf(sqls.get(i));
				pstmt.addBatch(sql);
				if(DBLogger.show_sql.equals("true"))
					log.info("sql: "+sql);
				pstmt.execute(sql);
			}
			pstmt.close();
		}
	}
	/**
	 * 执行给定的SQL语句,该语句读取Clob数据
	 * @param sql
	 * @param col
	 * @return
	 * @throws SQLException 
	 * @throws Exception
	 */
	public String readClob(String sql,String col) throws SQLException{
		pstmt=connection.prepareStatement(sql);		
		rst=pstmt.executeQuery();
		while(rst.next()){
			 java.sql.Clob clob = (java.sql.Clob) rst.getClob(col); 
			 BufferedReader in = new BufferedReader(clob.getCharacterStream());
             StringWriter out = new StringWriter();
             int c;
             try {
				while((c = in.read()) != -1) 
				     out.write(c);
				out.close();
	            in.close();
			 } catch (IOException e) {				
				e.printStackTrace();
			 }             
             String str = out.getBuffer().toString();            
             return str;
		}
		rst.close();
		pstmt.close();
		return "";
	}
	/**
	 * 执行给定的SQL语句,该语句保存Clob数据
	 * @param sql
	 * @param col
	 * @param data
	 * @throws SQLException 
	 * @throws Exception
	 */
	public synchronized void updateClob(String sql,String col,String data) throws SQLException{
		if(DBLogger.show_sql.equals("true"))
			log.info("sql: "+sql);
		pstmt=connection.prepareStatement(sql);		
		rst=pstmt.executeQuery();
		while(rst.next()){					
			oracle.sql.CLOB clob = (oracle.sql.CLOB) rst.getObject(col);   
	        BufferedWriter out=null;	        
	        if(clob!=null){
	        	out= new BufferedWriter(clob.getCharacterOutputStream());	 	        		 	        
	        }	 	    
	        if(out!=null){
	        	try {
					out.write(data);
					 out.close();
				} catch (IOException e) {				
					e.printStackTrace();
				}	        	
	 	       
	        }
		}		
		rst.close();
		pstmt.close();
	}
	/**
	 * 执行给定的SQL语句,该语句读取Blob数据
	 * @param sql
	 * @param col
	 * @return
	 * @throws SQLException 
	 * @throws Exception
	 */
	public OutputStream readBlob(String sql,String col) throws SQLException{
		pstmt=connection.prepareStatement(sql);		
		rst=pstmt.executeQuery();
		OutputStream output=null;
		while(rst.next()){
			oracle.sql.BLOB blob = (oracle.sql.BLOB)rst.getBlob(col);
            if(blob != null && !blob.isEmptyLob()){
                BufferedOutputStream out = new BufferedOutputStream(output);
                BufferedInputStream in = new BufferedInputStream(blob.getBinaryStream());
                int c;
                try {
					while((c = in.read()) != -1) 
					    out.write(c);
					in.close();
	                out.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}                
            }
		}
		rst.close();
		pstmt.close();
		return output;
	}
	/**
	 * 执行给定的SQL语句,该语句保存Blob数据
	 * @param sql
	 * @param col
	 * @param data
	 * @throws SQLException 
	 * @throws Exception
	 */
	public synchronized void updateBlob(String sql,String col,String data) throws SQLException{
		pstmt=connection.prepareStatement(sql);		
		rst=pstmt.executeQuery();
		while(rst.next()){
			oracle.sql.BLOB blob=(oracle.sql.BLOB)rst.getObject(col);  
			BufferedOutputStream out=null;
			if(blob!=null){
				out=new BufferedOutputStream(blob.getBinaryOutputStream());				
			}
			if(out!=null){
				byte[] buffer=data.getBytes();
				try {
					out.write(buffer);
					out.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}				
			}
		}
		rst.close();
		pstmt.close();
	}
	/**
	 * 执行给定的语句,该语句为过程或函数
	 * @param sql
	 * @throws SQLException
	 */
	public synchronized void namedExecute(String sql) throws SQLException{
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
//			if(!connection.isClosed())
//				connection.close();	
			connectionPoolManager.freeConnection(connection);
			connection=null;
		}		
	}
}
