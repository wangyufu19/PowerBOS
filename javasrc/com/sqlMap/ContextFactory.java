package com.sqlMap;
import java.sql.Connection;
import com.sqlMap.exception.ConfigurationException;
import com.sqlMap.jdbc.JdbcTmplt;
import com.sqlMap.SqlMapClient;

public interface ContextFactory {
	/**
	 * 返回一个数据库连接
	 * @return
	 */
	public Connection getConnection();
	/**
	 * 返回一个SqlMapClient对象
	 * @return
	 * @throws ConfigurationException
	 */
	public SqlMapClient getSqlMapClient()throws ConfigurationException;
	/**
	 * 返回一个SqlMapClient对象
	 * @param identifer
	 * @return
	 * @throws ConfigurationException
	 */
	public SqlMapClient getSqlMapClient(String identifer)throws ConfigurationException;
	/**
	 * 返回一个JDBC模板对象
	 * @return
	 * @throws ConfigurationException
	 */
	public JdbcTmplt getJdbcTmplt()throws ConfigurationException;
	/**
	 * 返回一个JDBC模板对象
	 * @param identifer
	 * @return
	 * @throws ConfigurationException
	 */
	public JdbcTmplt getJdbcTmplt(String identifer)throws ConfigurationException;
}
