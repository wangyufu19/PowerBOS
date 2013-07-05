package com.sqlMap.jdbc;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import com.sqlMap.Transaction;
/**
 * JDBC模板接口
 * @author youfu.wang
 * @version 1.0
 */
public interface JdbcTmplt {	
	/**
	 * 返回一个数据库连接
	 * @return
	 */
	public Connection getConnection();
	/**
	 * 开始事务,并且返回事务对象
	 * @return
	 */
	public Transaction beginTransaction() throws SQLException;	
	/**
	 * 创建查询接口QueryTmplt
	 * @return
	 */
	public QueryTmplt createQueryTmplt();
	/**
	 * 执行给定的SQL语句,该语句为 INSERT 语句
	 * @param sql
	 * @throws SQLException 
	 */
	public void insert(String sql)throws SQLException;
	/**
	 * 执行给定的SQL语句,该语句为 INSERT 语句
	 * @param sql
	 * @param params
	 * @param resultMap
	 * @throws SQLException 
	 */
	public void insert(String sql,Map params,String resultMap)throws SQLException;
	/**
	 * 执行给定的SQL语句,该语句为 INSERT 语句
	 * @param sql
	 * @param parameterClass
	 * @param resultMap
	 * @throws SQLException 
	 */
	public void insert(String sql,Object parameterClass,String resultMap)throws SQLException;
	/**
	 * 执行给定的SQL语句,该语句可能为 INSERT、UPDATE 或 DELETE 语句
	 * @param sql
	 * @throws SQLException
	 */
	public void execute(String sql) throws SQLException;
	/**
	 * 执行给定的SQL语句,该语句可能为 INSERT、UPDATE 或 DELETE 语句
	 * 该SQL语句包含通配符,通过SQLMAP组件解析器组装该SQL语句
	 * @param sql
	 * @param params
	 * @param resultMap
	 * @throws SQLException 
	 */
	public void execute(String sql,Map params,String resultMap) throws SQLException;
	/**
	 * 执行给定的SQL语句,该语句可能为 INSERT、UPDATE 或 DELETE 语句
	 * 该SQL语句包含通配符,通过SQLMAP组件解析器组装该SQL语句
	 * @param sql
	 * @param parameterClass
	 * @param resultMap
	 * @throws SQLException 
	 */
	public void execute(String sql,Object parameterClass,String resultMap) throws SQLException;
	/**
     * 执行给定的批量SQL语句,该语句可能为 INSERT、UPDATE 或 DELETE 语句
     * @param statementTmplt
     * @throws SQLException
     */
	public void executeBatch(StatementTmplt statementTmplt) throws SQLException;
	/**
	 * 执行给定的SQL语句,该语句读取Clob数据
	 * @param sql
	 * @param col
	 * @return
	 * @throws SQLException 
	 * @throws Exception
	 */
	public String readClob(String sql,String col) throws SQLException;
	/**
	 * 执行给定的SQL语句,该语句保存Clob数据
	 * @param sql
	 * @param col
	 * @param data
	 * @throws SQLException 
	 * @throws Exception
	 */
	public void updateClob(String sql,String col,String data) throws SQLException;
	/**
	 * 执行给定的SQL语句,该语句读取Blob数据
	 * @param sql
	 * @param col
	 * @return
	 * @throws SQLException 
	 * @throws Exception
	 */
	public OutputStream readBlob(String sql,String col)throws SQLException;
	/**
	 * 执行给定的SQL语句,该语句保存Blob数据
	 * @param sql
	 * @param col
	 * @param data
	 * @throws SQLException 
	 * @throws Exception
	 */
	public void updateBlob(String sql,String col,String data) throws SQLException;
	/**
	 * 执行给定的语句,该语句为过程或函数
	 * @param sql
	 * @throws SQLException
	 */
	public void namedExecute(String sql) throws SQLException;
	/**
	 * 关闭一个连接
	 * @throws SQLException 
	 */
	public void close() throws SQLException;
}
