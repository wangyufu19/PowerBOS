package com.powerbosframework.context;

import java.io.IOException;
import java.util.Map;
import com.controller.action.ActionServlet;
import com.powerbosframework.beans.parsing.BeanEntity;

/**
 * 应用上下文管理接口
 * 
 * @author youfu.wang
 * @version 1.0
 */
public interface ApplicationContext {
	/**
	 * 设置ActionServlet
	 * 
	 * @param actionServlet
	 */
	void setActionServlet(ActionServlet actionServlet);

	/**
	 * 销毁应用上下文内存
	 */
	void destory();

	/**
	 * 加载应用上下文资源
	 */
	void load() throws IOException, ApplicatioinContextException;

	/**
	 * 加载应用上下文资源
	 * 
	 * @param location
	 * @throws IOException
	 * @throws ApplicatioinContextException
	 */
	void load(String location) throws IOException, ApplicatioinContextException;

	/**
	 * 设置应用上下文POJO实体
	 * 
	 * @param id
	 * @param beanEntity
	 */
	void setBeanEntity(String id, BeanEntity beanEntity);

	/**
	 * 得到应用上下文POJO全部实体集合
	 * 
	 * @return
	 */
	Map<String, BeanEntity> getBeanEntities();

	/**
	 * 得到应用上下文POJO实体
	 * 
	 * @param id
	 * @return
	 */
	BeanEntity getBeanEntity(String id);

	/**
	 * 设置应用上下文POJO对象
	 * 
	 * @param id
	 * @param obj
	 */
	void setBean(String id, Object obj);

	/**
	 * 得到应用上下文POJO对象
	 * 
	 * @param id
	 * @return
	 */
	Object getBean(String id);
}
