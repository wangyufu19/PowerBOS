package com.powerbosframework.jdbc.core;

import java.sql.SQLException;

import com.powerbosframework.jdbc.core.QueryTmplt;
import com.powerbosframework.jdbc.core.Transaction;
import com.powerbosframework.jdbc.datasource.DataSource;

/**
 * JDBC模板接口
 * 
 * @author youfu.wang
 * @version 1.0
 */
public interface JdbcTmplt {
	/**
	 * 设置数据源对象
	 */
	void setDataSource(DataSource dataSource);

	/**
	 * 开始事务,并且返回事务对象
	 * 
	 * @return
	 */
	Transaction beginTransaction() throws SQLException;

	/**
	 * 创建一个查询模板实例
	 * 
	 * @return
	 */
	QueryTmplt createQueryTmplt();

	/**
	 * 执行给定的SQL语句,该语句可能为 INSERT、UPDATE 或 DELETE 语句
	 * 
	 * @param sql
	 * @throws SQLException
	 */
	void execute(String sql) throws SQLException;

	/**
	 * 执行给定的SQL语句,该语句可能为 INSERT、UPDATE 或 DELETE 语句
	 * 
	 * @param sql
	 * @param args
	 * @throws SQLException
	 */
	void execute(String sql, Object[] args)  throws SQLException;

	/**
	 * 关闭数据源连接
	 */
	void close() throws SQLException;
}
