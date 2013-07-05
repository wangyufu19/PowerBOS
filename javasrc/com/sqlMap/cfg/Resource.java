package com.sqlMap.cfg;
import java.util.Map;

import com.sqlMap.exception.ConfigurationException;
import com.sqlMap.exception.MappingException;
import com.sqlMap.property.Metadata;
/**
 * SqlMap资源接口
 * @author wangyf
 * @version 1.0
 */
public interface Resource {	
	/**
	 * 加载SqlMap配置资源
	 * @param resource
	 * @return
	 * @throws ConfigurationException
	 */
	public void addResource(String resource) throws ConfigurationException;
	/**
	 * 得到基本元数据
	 * @param resultMap
	 * @return
	 * @throws MappingException
	 */
	public Metadata getMetadata(String resultMap) throws MappingException;
	/**
	 * 得到基本元数据
	 * @param sqlMapId
	 * @param resultMap
	 * @return
	 * @throws MappingException
	 */
	public Metadata getMetadata(String sqlMapId,String resultMap) throws MappingException;
	/**
	 * 得到该类的路径别名
	 * @param returnClass
	 * @return
	 * @throws MappingException
	 */
	public String getReturnClass(String returnClass);
	/**
	 * 得到该类的路径别名
	 * @param sqlMapId
	 * @param returnClass
	 * @return
	 * @throws MappingException
	 */
	public String getReturnClass(String sqlMapId,String returnClass) throws MappingException;
	/**
	 * 得到该类的路径别名
	 * @param parameterClass
	 * @return
	 * @throws MappingException
	 */
	public String getParameterClass(String parameterClass);
	/**
	 * 得到该类的路径别名
	 * @param sqlMapId
	 * @param parameterClass
	 * @return
	 * @throws MappingException
	 */
	public String getParameterClass(String sqlMapId,String parameterClass) throws MappingException;
	/**
     * 得到语句集合
     * @param parameterClass
	 * @return
	 * @throws MappingException
     */
	public Map getStatementMap(String sqlMapId) throws MappingException;
	/**
	 * 设置语句排序列
	 * @param sqlMapId
	 * @param column
	 */
	public void putOrderBy(String sqlMapId,String column);
	/**
	 * 得到语句排序列
	 * @param sqlMapId
	 * @return
	 */
	public Map getOrderBy(String sqlMapId);

}
