package com.powerbosframework.jdbc.core;

import java.sql.SQLException;
import java.util.List;

/**
 * JDBC查詢模板接口
 * 
 * @author youfu.wang
 * @version 1.0
 */
public interface QueryTmplt {
	/**
	 * 得到最大行数
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	int getROWNUM(String sql) throws SQLException;
	/**
	 * 执行给定的SQL语句，该语句返回单个Object对象
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	Object[] queryForArray(String sql) throws SQLException;
	/**
	 * 执行给定的SQL语句，该语句返回单个Object对象
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	Object[] queryForArray(String sql,Object[] args) throws SQLException;
	/**
	 * 执行给定的SQL语句，该语句返回多个Object对象的集合
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	List queryForList(String sql) throws SQLException;
	/**
	 * 执行给定的SQL语句，该语句返回多个Object对象的集合
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	List queryForList(String sql,Object[] args) throws SQLException;
}
