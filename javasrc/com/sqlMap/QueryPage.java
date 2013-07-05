package com.sqlMap;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 * 分页查询接口
 * @author youfu.wang
 * @version 1.0
 */
public interface QueryPage {
	/**
	 * 得到分页数据集合
	 * @param sqlMapId
	 * @return
	 * @throws SQLException 
	 */
	public List queryForList(String sqlMapId) throws SQLException;
	/**
	 * 得到分页数据集合
	 * @param params
	 * @param sqlMapId
	 * @return
	 * @throws SQLException 
	 */
	public List queryForList(Map params,String sqlMapId) throws SQLException;
	/**
	 * 得到分页数据集合
	 * @param obj
	 * @param sqlMapId
	 * @return
	 * @throws SQLException 
	 */
	public List queryForList(Object obj,String sqlMapId) throws SQLException;
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
