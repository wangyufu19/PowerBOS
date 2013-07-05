package com.sqlMap.parser;
import java.util.Map;
import com.sqlMap.QueryParam;
import com.sqlMap.exception.MappingException;
import com.sqlMap.property.Metadata;
/**
 * SQL语句解析器接口
 * @author youfu.wang
 * @version 1.0
 */
public interface SqlParser {	
	/**
	 * 解析资源数据的SELECT语句
	 * @param sqlMapId
	 * @return
	 * @throws MappingException 
	 */
	public String getSQLForMaxRow(String sqlMapId) throws MappingException;
	/**
	 * 解析资源数据的SELECT语句
	 * @param params
	 * @param sqlMapId
	 * @return
	 * @throws MappingException
	 */
	public String getSQLForMaxRow(Map params,String sqlMapId) throws MappingException;
	/**
	 * 解析资源数据的SELECT语句
	 * @param obj
	 * @param sqlMapId
	 * @return
	 * @throws MappingException 
	 */
	public String getSQLForMaxRow(Object obj,String sqlMapId) throws MappingException;
	/**
	 * 解析资源数据的SELECT语句,并且组装成分页查询语句
	 * @param sqlMapId
	 * @param queryParam
	 * @return
	 * @throws MappingException
	 */
	public String parseSQLForQueryPage(String sqlMapId,QueryParam queryParam) throws MappingException;
	/**
	 * 解析资源数据的SELECT语句,并且组装成分页查询语句
	 * @param params
	 * @param sqlMapId
	 * @param queryParam
	 * @return
	 * @throws MappingException
	 */
	public String parseSQLForQueryPage(Map params,String sqlMapId,QueryParam queryParam) throws MappingException;
	/**
	 * 解析资源数据的SELECT语句,并且组装成分页查询语句
	 * @param obj
	 * @param sqlMapId
	 * @param queryParam
	 * @return
	 * @throws MappingException
	 */
	public String parseSQLForQueryPage(Object obj,String sqlMapId,QueryParam queryParam) throws MappingException;
	/**
	 * 解析资源数据查询语句
	 * @param sqlMapId
	 * @return
	 * @throws MappingException 
	 */
	public String parseQuerySQL(String sqlMapId) throws MappingException;
	/**
	 * 解析资源数据查询语句
	 * @param params
	 * @param sqlMapId
	 * @return
	 * @throws MappingException 
	 */
	public String parseQuerySQL(Map params,String sqlMapId) throws MappingException;
	/**
	 *解析资源数据查询语句
	 * @param obj
	 * @param sqlMapId
	 * @return
	 * @throws MappingException 
	 */
	public String parseQuerySQL(Object obj,String sqlMapId)throws MappingException;
	/**
	 * 解析资源数据查询语句
	 * @param sql
	 * @param params
	 * @param resultMap	
	 * @return
	 */
	public String parseQuerySQL(String sql,Map params,String resultMap);
	/**
	 * 解析资源数据查询语句
	 * @param sql
	 * @param obj
	 * @param resultMap	
	 * @return
	 */
	public String parseQuerySQL(String sql,Object obj,String resultMap);
	/**
	 * 解析资源数据语句
	 * @param sqlMapId
	 * @return
	 * @throws MappingException 
	 */
	public String parseExecuteSQL(String sqlMapId) throws MappingException;
	/**
	 * 解析资源数据语句
	 * @param params
	 * @param sqlMapId
	 * @return
	 * @throws MappingException 
	 */
	public String parseExecuteSQL(Map params,String sqlMapId) throws MappingException;
	/**
	 * 解析资源数据语句
	 * @param obj
	 * @param sqlMapId
	 * @return
	 * @throws MappingException 
	 */
	public String parseExecuteSQL(Object obj,String sqlMapId) throws MappingException;
	/**
	 * 解析资源数据语句
	 * @param obj
	 * @param metadata
	 * @param statement
	 * @return
	 */
	public String parseExecuteSQL(Object obj,Metadata metadata,Map statement);
	/**
	 * 解析资源数据语句
	 * @param sql
	 * @param params
	 * @param resultMap
	 * @return
	 */
	public String parseExecuteSQL(String sql,Map params,String resultMap);
	/**
	 * 解析资源数据语句
	 * @param sql
	 * @param obj
	 * @param resultMap
	 * @return
	 */
	public String parseExecuteSQL(String sql,Object obj,String resultMap);
	/**
	 * 解析资源参数语句
	 * @param expression
	 * @param obj
	 * @param property
	 * @return
	 */
	public String parseIsNotEmpty(String expression,Object obj);
}
