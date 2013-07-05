package com.powerbosframework.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.powerbosframework.jdbc.core.QueryParam;
import com.powerbosframework.jdbc.core.QueryTmplt;
import com.powerbosframework.jdbc.datasource.DataSource;
import com.powerbosframework.jdbc.util.SqlMakeUpUtil;

/**
 * JDBC查詢模板类
 * 
 * @author youfu.wang
 * @version 1.0
 */
public class QueryTmpltManager implements QueryTmplt {
	private DataSource dataSource;
	private QueryParam queryParam;

	public QueryTmpltManager(DataSource dataSource) {
		this.queryParam = new QueryParam();
		this.dataSource = dataSource;
	}

	/**
	 * 得到最大行数
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public int getROWNUM(String sql) throws SQLException {
		int rownum = 0;
		sql = "select count(*) from (" + sql + ")";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(
				sql);
		ResultSet rst = pstmt.executeQuery();
		while (rst.next()) {
			rownum = rst.getInt(1);
			break;
		}
		pstmt.close();
		rst.close();
		return rownum;
	}

	/**
	 * 执行给定的SQL语句，该语句返回单个Object对象
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public Object[] queryForArray(String sql) throws SQLException {
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(
				sql);
		ResultSet rst = pstmt.executeQuery();
		while (rst.next()) {
			ResultSetMetaData rsmd = rst.getMetaData();
			Object[] obj = new Object[rsmd.getColumnCount()];
			for (int i = 0; i < obj.length; i++) {
				obj[i] = rst.getObject(i + 1);
			}
			pstmt.close();
			rst.close();
			return obj;
		}
		return null;
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
		sql = SqlMakeUpUtil.makeUp(sql, args, dataSource.getDialect());
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(
				sql);
		ResultSet rst = pstmt.executeQuery();
		while (rst.next()) {
			ResultSetMetaData rsmd = rst.getMetaData();
			Object[] obj = new Object[rsmd.getColumnCount()];
			for (int i = 0; i < obj.length; i++) {
				obj[i] = rst.getObject(i + 1);
			}
			pstmt.close();
			rst.close();
			return obj;
		}
		return null;
	}

	/**
	 * 执行给定的SQL语句，该语句返回多个Object对象的集合
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public List queryForList(String sql) throws SQLException {
		List list = new ArrayList();
		// 是否采用分页查询数据
		if (queryParam.getMaxResult() > 0) {
			int rownum = this.getROWNUM(sql);
			if (rownum > 0) {
				if (rownum % queryParam.getPageSize() == 0) {
					queryParam.setMaxPage(rownum / queryParam.getPageSize());
				} else
					queryParam
							.setMaxPage(rownum / queryParam.getPageSize() + 1);
			} else
				queryParam.setMaxPage(1);
			// 分别组装oracle,mssql,mysql数据库方言的分页查询语句
			if ("oracle".equals(dataSource.getDialect())) {
				sql = "select * from (select rownum r,t1.* from (" + sql
						+ ") t1 where rownum<=" + queryParam.getMaxResult()
						+ ") t2 where r>=" + queryParam.getFirstResult();
			} else if ("mssql".equals(dataSource.getDialect())) {
				sql = "select * from (select row_number() over(order by"
						+ queryParam.getPrimary() + ") r,t1.* from (" + sql
						+ ") t1 ) t2 where r between "
						+ queryParam.getFirstResult() + " and "
						+ queryParam.getMaxResult();

			} else if ("mysql".equals(dataSource.getDialect())) {
				sql = "select * from (select t1.* from (" + sql
						+ ") t1) limit " + queryParam.getMaxResult() + ","
						+ queryParam.getFirstResult();
			} else if ("db2".equals(dataSource.getDialect())) {

			}
		}
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(
				sql);
		ResultSet rst = pstmt.executeQuery();
		while (rst.next()) {
			ResultSetMetaData rsmd = rst.getMetaData();
			Object[] obj = new Object[rsmd.getColumnCount()];
			for (int i = 0; i < obj.length; i++) {
				obj[i] = rst.getObject(i + 1);
			}
			list.add(obj);
		}
		pstmt.close();
		rst.close();
		return list;
	}

	/**
	 * 执行给定的SQL语句，该语句返回多个Object对象的集合
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public List queryForList(String sql, Object[] args) throws SQLException {
		sql = SqlMakeUpUtil.makeUp(sql, args, dataSource.getDialect());
		List list = new ArrayList();
		// 是否采用分页查询数据
		if (queryParam.getMaxResult() > 0) {
			int rownum = this.getROWNUM(sql);
			if (rownum > 0) {
				if (rownum % queryParam.getPageSize() == 0) {
					queryParam.setMaxPage(rownum / queryParam.getPageSize());
				} else
					queryParam
							.setMaxPage(rownum / queryParam.getPageSize() + 1);
			} else
				queryParam.setMaxPage(1);
			// 分别组装oracle,mssql,mysql数据库方言的分页查询语句
			if ("oracle".equals(dataSource.getDialect())) {
				sql = "select * from (select rownum r,t1.* from (" + sql
						+ ") t1 where rownum<=" + queryParam.getMaxResult()
						+ ") t2 where r>=" + queryParam.getFirstResult();
			} else if ("mssql".equals(dataSource.getDialect())) {
				sql = "select * from (select row_number() over(order by"
						+ queryParam.getPrimary() + ") r,t1.* from (" + sql
						+ ") t1 ) t2 where r between "
						+ queryParam.getFirstResult() + " and "
						+ queryParam.getMaxResult();

			} else if ("mysql".equals(dataSource.getDialect())) {
				sql = "select * from (select t1.* from (" + sql
						+ ") t1) limit " + queryParam.getMaxResult() + ","
						+ queryParam.getFirstResult();
			} else if ("db2".equals(dataSource.getDialect())) {

			}
		}
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(
				sql);
		ResultSet rst = pstmt.executeQuery();
		while (rst.next()) {
			ResultSetMetaData rsmd = rst.getMetaData();
			Object[] obj = new Object[rsmd.getColumnCount()];
			for (int i = 0; i < obj.length; i++) {
				obj[i] = rst.getObject(i + 1);
			}
			list.add(obj);
		}
		pstmt.close();
		rst.close();
		return list;
	}
}
