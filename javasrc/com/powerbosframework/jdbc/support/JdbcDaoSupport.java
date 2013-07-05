package com.powerbosframework.jdbc.support;

import java.sql.SQLException;
import java.util.List;

import com.powerbosframework.jdbc.core.JdbcSessionFactory;
import com.powerbosframework.jdbc.core.JdbcTmplt;
import com.powerbosframework.jdbc.core.QueryTmplt;

/**
 * JDBC DAO支撑类
 * 
 * @author youfu.wang
 * @version 1.0
 */
public class JdbcDaoSupport {
	private JdbcSessionFactory jdbcSessionFactory;

	public JdbcDaoSupport() {

	}

	public JdbcSessionFactory getJdbcSessionFactory() {
		return jdbcSessionFactory;
	}

	public void setJdbcSessionFactory(JdbcSessionFactory jdbcSessionFactory) {
		this.jdbcSessionFactory = jdbcSessionFactory;
	}

	/**
	 * 执行给定的SQL语句，该语句返回单个Object对象
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public Object[] queryForArray(String sql) throws SQLException {
		JdbcTmplt jdbcTmplt = jdbcSessionFactory.getJdbcTmplt();
		QueryTmplt queryTmplt = jdbcTmplt.createQueryTmplt();
		Object[] obj = queryTmplt.queryForArray(sql);
		jdbcTmplt.close();
		return obj;
	}

	/**
	 * 执行给定的SQL语句，该语句返回单个Object对象
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public Object[] queryForArray(String sql, Object[] args)
			throws SQLException {
		JdbcTmplt jdbcTmplt = jdbcSessionFactory.getJdbcTmplt();
		QueryTmplt queryTmplt = jdbcTmplt.createQueryTmplt();
		Object[] obj = queryTmplt.queryForArray(sql, args);
		jdbcTmplt.close();
		return obj;
	}

	/**
	 * 执行给定的SQL语句，该语句返回List列表
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public List queryForList(String sql) throws SQLException {
		JdbcTmplt jdbcTmplt = jdbcSessionFactory.getJdbcTmplt();
		QueryTmplt queryTmplt = jdbcTmplt.createQueryTmplt();
		List list = queryTmplt.queryForList(sql);
		jdbcTmplt.close();
		return list;
	}

	/**
	 * 执行给定的SQL语句，该语句返回List列表
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public List queryForList(String sql, Object[] args) throws SQLException {
		JdbcTmplt jdbcTmplt = jdbcSessionFactory.getJdbcTmplt();
		QueryTmplt queryTmplt = jdbcTmplt.createQueryTmplt();
		List list = queryTmplt.queryForList(sql, args);
		jdbcTmplt.close();
		return list;
	}
}
