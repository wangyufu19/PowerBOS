package com.powerbosframework.jdbc.datasource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 加载数据源接口
 * 
 * @author youfu.wang
 * @version 1.0
 */
public interface DataSource {
	String getDriverClass();

	String getUrl();

	String getUsername();

	String getPassword();

	String getDialect();

	String getShowSql();

	/**
	 * 得到数据库连接
	 * 
	 * @throws SQLException
	 * @return
	 */
	Connection getConnection() throws SQLException;

	/**
	 * 关闭数据库连接
	 * 
	 * @throws SQLException
	 */
	void close() throws SQLException;
}
