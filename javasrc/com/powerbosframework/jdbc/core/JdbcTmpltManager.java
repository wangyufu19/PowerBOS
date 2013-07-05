package com.powerbosframework.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.powerbosframework.jdbc.core.JdbcTmplt;
import com.powerbosframework.jdbc.core.QueryTmplt;
import com.powerbosframework.jdbc.core.QueryTmpltManager;
import com.powerbosframework.jdbc.core.Transaction;
import com.powerbosframework.jdbc.core.TransactionManager;
import com.powerbosframework.jdbc.datasource.DataSource;
import com.powerbosframework.jdbc.util.SqlMakeUpUtil;

/**
 * JDBC模板管理类
 * 
 * @author youfu.wang
 * @version 1.0
 */
public class JdbcTmpltManager implements JdbcTmplt {
	private DataSource dataSource;

	public JdbcTmpltManager(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 设置数据源对象
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 创建一个查询模板实例
	 * 
	 * @return
	 */
	public QueryTmplt createQueryTmplt() {
		QueryTmplt queryTmplt = new QueryTmpltManager(this.dataSource);
		return queryTmplt;
	}

	/**
	 * 开始事务,并且返回事务对象
	 * 
	 * @return
	 */
	public Transaction beginTransaction() throws SQLException {
		Transaction tx = new TransactionManager(this.dataSource.getConnection());
		return tx;
	}

	/**
	 * 执行给定的SQL语句,该语句可能为 INSERT、UPDATE 或 DELETE 语句
	 * 
	 * @param sql
	 * @throws SQLException
	 */
	public void execute(String sql) throws SQLException {
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(
				sql);
		pstmt.executeUpdate();
		pstmt.close();
	}

	/**
	 * 执行给定的SQL语句,该语句可能为 INSERT、UPDATE 或 DELETE 语句
	 * 
	 * @param sql
	 * @param args
	 * @throws SQLException
	 * @throws SQLException
	 */
	public void execute(String sql, Object[] args) throws SQLException {
		sql = SqlMakeUpUtil.makeUp(sql, args, dataSource.getDialect());
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(
				sql);
		pstmt.executeUpdate();
		pstmt.close();
	}

	/**
	 * 关闭数据源连接
	 */
	public void close() throws SQLException {
		if (dataSource != null) {
			dataSource.close();
		}
	}

}
