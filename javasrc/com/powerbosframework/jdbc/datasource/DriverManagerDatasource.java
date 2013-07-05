package com.powerbosframework.jdbc.datasource;

import com.powerbosframework.jdbc.datasource.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 加载数据源实现类
 * 
 * @author youfu.wang
 * @version 1.0
 */
public class DriverManagerDatasource implements DataSource {
	private Connection connection;
	private String driverClass;
	private String url;
	private String username;
	private String password;
	private String dialect;
	private String showSql;

	public DriverManagerDatasource() {
		connection = null;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	public String getShowSql() {
		return showSql;
	}

	public void setShowSql(String showSql) {
		this.showSql = showSql;
	}

	@Override
	/**
	 * 得到数据库连接
	 * 
	 * @throws SQLException
	 * @return
	 */
	public Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName(this.getDriverClass());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(this.url, username,
					password);
			return connection;
		} else {
			return connection;
		}
	}

	@Override
	/**
	 * 关闭数据库连接
	 * 
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		if (connection != null) {
			connection.close();
			connection = null;
		}
	}
}
