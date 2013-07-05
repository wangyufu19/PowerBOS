package com.sqlMap;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 * 数据库查询接口
 * @author youfu.wang
 * @version 1.0
 */
public interface Query {	
	/**
	 * 得到数据数组对象
	 * @param sqlMapId
	 * @return
	 * @throws SQLException
	 */
	public Object[] getArray(String sqlMapId) throws SQLException;
	/**
	 * 得到数据数组对象
	 * @param params
	 * @param sqlMapId	 
	 * @return
	 * @throws SQLException
	 */
	public Object[] getArray(Map params,String sqlMapId) throws SQLException;
	/**
	 * 得到数据数组对象
	 * @param obj
	 * @param sqlMapId
	 * @return
	 * @throws SQLException
	 */
	public Object[] getArray(Object obj,String sqlMapId) throws SQLException;
	/**
	 * 得到数据集合
	 * @param sqlMapId
	 * @return
	 * @throws SQLException
	 */
	public List iterator(String sqlMapId) throws SQLException;
	/**
	 * 得到数据集合
	 * @param params
	 * @param sqlMapId
	 * @return
	 * @throws SQLException
	 */
	public List iterator(Map params,String sqlMapId) throws SQLException;
	/**
	 * 得到数据集合
	 * @param obj
	 * @param sqlMapId
	 * @return
	 * @throws SQLException
	 */
	public List iterator(Object obj,String sqlMapId) throws SQLException;
	/**
	 * 得到数据集合列表
	 * @param sqlMapId
	 * @return 	 
	 */
	public List list(String sqlMapId)throws SQLException;
	/**
	 * 得到数据集合列表
	 * @param params
	 * @param sqlMapId
	 * @return
	 */
	public List list(Map params,String sqlMapId) throws SQLException;
	/**
	 * 得到数据集合实体列表
	 * @param obj 
	 * @param sqlMapId 
	 * @return
	 */
	public List list(Object obj,String sqlMapId)throws SQLException;
	/**
	 * 得到实体对象数组
	 * @param params
	 * @param sqlMapId
	 * @return
	 */
	public Object getUniqueResult(Map params,String sqlMapId) throws SQLException;
	/**
	 * 得到实体对象
	 * @param obj
	 * @param sqlMapId
	 * @return
	 */
	public Object getUniqueResult(Object obj,String sqlMapId) throws SQLException;
}
