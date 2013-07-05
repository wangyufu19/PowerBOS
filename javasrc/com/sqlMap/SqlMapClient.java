package com.sqlMap;
import java.sql.SQLException;
import java.util.Map;
/**
 * SqlMapClient接口
 * @author youfu.wang
 * @version 1.0
 */
public interface SqlMapClient {
	/**
	 * 关闭一个连接
	 * @throws SQLException 
	 */
	public void close() throws SQLException;
	/**
	 * 开始事务,并且返回事务对象
	 * @return
	 */
	public Transaction beginTransaction() throws SQLException;
	/**
	 * 创建查询对象
	 * @return
	 */
	public Query createQuery();
	/**
	 * 创建分页查询对象
	 * @return
	 */
	public QueryPage createQueryPage();
	/**
     * 根据实体对象和sqlMapId,得到持久化对象
     * @param params 参数集合
     * @param sqlMapId
     * @return
     * @throws SQLException    
     */
	public Object get(Map params,String sqlMapId) throws SQLException;
	  /**
     * 根据Map参数和sqlMapId,得到持久化对象
     * @param obj 实体对象
     * @param sqlMapId
     * @return
     * @throws SQLException    
     */
	public Object get(Object obj,String sqlMapId) throws SQLException;
	/**
	 * 根据实体对象和sqlMapId,持久化对象
	 * @param obj
	 * @param sqlMapId
	 * @throws SQLException
	 */
	public void insert(Object obj,String sqlMapId) throws SQLException;
	/**
	 * 根据Map参数对象和sqlMapId,持久化对象
	 * @param params
	 * @param sqlMapId
	 * @throws SQLException
	 */
	public void insert(Map params,String sqlMapId) throws SQLException;
	/**
	 * 根据实体对象和sqlMapId,持久化对象
	 * @param obj 实体对象
	 * @param sqlMapId	
	 * @throws SQLException 
	 */
	public void update(Object obj,String sqlMapId) throws SQLException;
	/**
	 * 根据实体对象和sqlMapId,持久化对象
	 * @param obj 实体对象
	 * @param sqlMapId	
	 * @throws SQLException 
	 */
	public void delete(Object obj,String sqlMapId) throws SQLException;
	/**
	 * 根据sqlMapId,持久化对象
	 * @param obj 实体对象
	 * @param sqlMapId	
	 * @throws SQLException 
	 */
	public void execute(String sqlMapId) throws SQLException;
	/**
	 * 根据实体对象和sqlMapId,持久化对象
	 * @param obj 实体对象
	 * @param sqlMapId	
	 * @throws SQLException 
	 */
	public void execute(Object obj,String sqlMapId) throws SQLException;
	/**
	 * 根据Map参数和sqlMapId,持久化对象
	 * @param params
	 * @param sqlMapId
	 * @throws SQLException
	 */
	public void execute(Map params,String sqlMapId)throws SQLException;
	/**
	 * 根据实体对象和sqlMapId,调用存储过程或函数
	 * @param obj
	 * @param sqlMapId
	 * @throws SQLException
	 */
	public void namedExecute(Map params,String sqlMapId) throws SQLException;
	/**
	 * 根据Map参数和sqlMapId,调用存储过程或函数
	 * @param obj
	 * @param sqlMapId
	 * @throws SQLException
	 */
	public void namedExecute(Object obj,String sqlMapId) throws SQLException;


}
