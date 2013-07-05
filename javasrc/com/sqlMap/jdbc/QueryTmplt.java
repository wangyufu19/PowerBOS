package com.sqlMap.jdbc;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.sqlMap.QueryParam;

/**
 * JDBC查询模板接口
 * @author youfu.wang
 * @version 1.0
 */
public interface QueryTmplt {
	/**
	 * 该语句返回一个标识符
	 * @param table
	 * @param primary
	 * @return
	 * @throws SQLException
	 */
	public Long getID(String table,String primary) throws SQLException;
	/**
	 * 执行给定的SQL语句，该语句返回单个Object对象
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public Object[] getArray(String sql) throws SQLException;
	/**
	 * 执行给定的SQL语句，该语句返回单个Object对象
	 * @param sql
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws SQLException 
	 */
	public Object getObject(String sql,String returnClass,String resultMap) throws SQLException;
	/**
	 * 执行给定的SQL语句，该语句返回单个Object对象
	 * @param sql
	 * @param params
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws SQLException 
	 */
	public Object getObject(String sql,Map params,String returnClass,String resultMap) throws SQLException;
	/**
	 * 执行给定的SQL语句，该语句返回单个Object对象
	 * @param sql
	 * @param parameterClass
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws SQLException 
	 */
	public Object getObject(String sql,Object parameterClass,String returnClass,String resultMap)throws SQLException;
	/**
	 * 执行给定的SQL语句，该语句返回多个Object对象的集合
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public List iterator(String sql) throws SQLException;
	/**
	 * 执行给定的SQL语句，该语句返回多个实体对象的集合
	 * @param sql
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws SQLException
	 */
	public List iterator(String sql,String returnClass,String resultMap)throws SQLException;
	/**
	 * 执行给定的SQL语句，该语句返回多个实体对象的集合
	 * @param sql
	 * @param parameterClass
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws SQLException
	 */
	public List iterator(String sql,Map params,String returnClass,String resultMap) throws SQLException;
	/**
	 * 执行给定的SQL语句，该语句返回多个实体对象的集合
	 * @param sql
	 * @param parameterClass
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws SQLException
	 */
	public List iterator(String sql,Object parameterClass,String returnClass,String resultMap)throws SQLException;
	/**
	 * 执行给定的SQL语句,该语句返回分页结果集
	 * 该结果集数据为数组对象
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public List queryForList(String sql) throws SQLException;
	/**
	 * 执行给定的SQL语句,该语句返回分页结果集	
	 * @param sql
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws SQLException 
	 */
	public List queryForList(String sql,String returnClass,String resultMap)throws SQLException;
	/**
	 * 执行给定的SQL语句,该语句返回分页结果集	
	 * @param sql
	 * @param parameterClass
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws SQLException 
	 */
	public List queryForList(String sql,Object parameterClass,String returnClass,String resultMap)throws SQLException;
	/**
	 * 设置分页查询参数对象
	 * @param queryParam
	 * @return
	 */
	public void setQueryParam(QueryParam queryParam);
	/**
	 * 得到分页查询参数对象
	 * @return
	 */
	public QueryParam getQueryParam();
}
