package com.sqlMap.impl;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.jdom.Document;

import com.sqlMap.cfg.Resource;
import com.sqlMap.exception.ConfigurationException;
import com.sqlMap.exception.MappingException;
import com.sqlMap.parser.ResourceParser;
import com.sqlMap.property.Metadata;
import com.sqlMap.util.XmlUtil;
/**
 * SqlMap资源类
 * @author wangyf
 * @version 1.0
 */
public class ResourceImpl implements Resource{
	protected static Map aliases=new HashMap();
	protected static Map metadatas=new HashMap();
	protected static Map statments=new HashMap();
	protected static Map orderBys=new HashMap();
	public ResourceImpl(){
		
	}
	/**
	 * 加载SqlMap配置资源
	 * @param resource
	 * @return
	 * @throws ConfigurationException
	 */
	public void addResource(String resource) throws ConfigurationException{
		InputStream stream=Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
		if(stream==null) throw new ConfigurationException("持久化组件环境映射资源"+resource+"不存在!");
		XmlUtil xmlUtil=new XmlUtil();
		Document doc=xmlUtil.parse(stream);
		if(doc==null) throw new ConfigurationException("持久化组件环境映射资源"+resource+"出现异常!");	
		ResourceParser parser=new ResourceParser();
		parser.parse(doc);	
		parser.pushAliases(aliases);
		parser.pushMetadats(metadatas);
		parser.pushStatments(statments);		
	}
	/**
	 * 得到该类的路径别名
	 * @param returnClass
	 * @return
	 * @throws MappingException
	 */
	public String getReturnClass(String returnClass){
		if(aliases.containsKey(returnClass)){
			return String.valueOf(aliases.get(returnClass));			
		}
		return returnClass;
	}
	/**
	 * 得到该类的路径别名
	 * @param sqlMapId
	 * @param returnClass
	 * @return
	 * @throws MappingException
	 */
	public String getReturnClass(String sqlMapId,String returnClass) throws MappingException{
		if("".equals(returnClass))
			throw new MappingException("持久化组件环境资源的\""+sqlMapId+"\"语句配置没有定义返回类");
		if(aliases.containsKey(returnClass)){
			return String.valueOf(aliases.get(returnClass));			
		}
		return returnClass;
	}
	/**
	 * 得到该类的路径别名
	 * @param sqlMapId
	 * @param parameterClass
	 * @return
	 * @throws MappingException
	 */
	public String getParameterClass(String sqlMapId,String parameterClass) throws MappingException{
		if("returnClass".equals(parameterClass))
			throw new MappingException("持久化组件环境资源的\""+sqlMapId+"\"语句配置没有定义参数类");
		if(aliases.containsKey(parameterClass)){
			return String.valueOf(aliases.get(parameterClass));
		}
		return parameterClass;
	}
	/**
	 * 得到该类的路径别名
	 * @param parameterClass
	 * @return
	 * @throws MappingException
	 */
	public String getParameterClass(String parameterClass){		
		if(aliases.containsKey(parameterClass)){
			return String.valueOf(aliases.get(parameterClass));
		}
		return parameterClass;
	}
	/**
	 * 得到基本元数据
	 * @param sqlMapId
	 * @param resultMap
	 * @return
	 * @throws MappingException
	 */
	public Metadata getMetadata(String sqlMapId,String resultMap) throws MappingException{
		if(!metadatas.containsKey(resultMap)){
			throw new MappingException("持久化组件环境资源的\""+sqlMapId+"\"语句配置没有定义resultMap");
		}		
		return (Metadata)metadatas.get(resultMap);
	}	
	/**
	 * 得到基本元数据
	 * @param resultMap
	 * @return
	 * @throws MappingException
	 */
	public Metadata getMetadata(String resultMap) throws MappingException{
		if(!metadatas.containsKey(resultMap)){
			throw new MappingException("持久化组件环境资源的元数据配置没有定义"+resultMap+"的resultMap");
		}		
		return (Metadata)metadatas.get(resultMap);
	}	 
    /**
     * 得到语句集合
     * @param parameterClass
	 * @return
	 * @throws MappingException
     */
	public Map getStatementMap(String sqlMapId) throws MappingException{		
		if(!statments.containsKey(sqlMapId))
			throw new MappingException("持久化组件环境资源没有\""+sqlMapId+"\"的SQL语句配置");
		Map statement=(Map)statments.get(sqlMapId);
		return statement;
	}	
	/**
	 * 设置语句排序列
	 * @param sqlMapId
	 * @param column
	 */
	public void putOrderBy(String sqlMapId,String column) {
		this.orderBys.remove(sqlMapId);
		Map orderBy=new HashMap();
		orderBy.put("orderBy", column);
		this.orderBys.put(sqlMapId, orderBy);		
	}
	/**
	 * 得到语句排序列
	 * @param sqlMapId
	 * @return
	 */
	public Map getOrderBy(String sqlMapId){
		if(this.orderBys.containsKey(sqlMapId))
			return (Map)this.orderBys.get(sqlMapId);
		return null;
	}
	
}
