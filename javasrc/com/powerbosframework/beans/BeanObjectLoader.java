package com.powerbosframework.beans;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.jdom.Element;

import com.powerbosframework.beans.access.BeanFactory;
import com.powerbosframework.beans.access.Setter;
import com.powerbosframework.beans.parsing.BeanEntity;
import com.powerbosframework.beans.parsing.PropertyEntity;
import com.powerbosframework.context.ApplicationContext;
import com.powerbosframework.log.LogFactory;
import com.powerbosframework.log.Logger;
import com.powerbosframework.util.ClassLoaderUtil;
import com.powerbosframework.util.StringUtil;

/**
 * POJO对象加载器
 * 
 * @author youfu.wang
 * @version 1.0
 */
public class BeanObjectLoader {
	private ApplicationContext applicationContext;
	private ClassLoaderUtil beanClassLoader = new ClassLoaderUtil();
	private static Logger log = LogFactory.getInstance();

	public BeanObjectLoader(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * 加载POJO
	 * 
	 * @param rootE
	 */
	public void load(List beans) {
		if (beans == null)
			return;
		for (int i = 0; i < beans.size(); i++) {
			Element beanE = (Element) beans.get(i);
			String id = StringUtil.replaceNull(beanE.getAttributeValue("id"));
			String clazz = StringUtil.replaceNull(beanE
					.getAttributeValue("class"));
			String singleton = StringUtil.replaceNull(beanE
					.getAttributeValue("singleton"));
			if ("".equals(clazz))
				continue;
			if ("".equals(singleton))
				singleton = "true";
			BeanEntity beanEntity = new BeanEntity();
			beanEntity.setId(id);
			beanEntity.setClazz(clazz);
			beanEntity.setSingleton(singleton);

			// 加载POJO属性
			this.loadBeanProperties(beanEntity, beanE);
			applicationContext.setBeanEntity(id, beanEntity);
			if ("true".equals(singleton)) {
				// 实例化POJO
				this.newInstanceBean(beanEntity.getId(), beanEntity.getClazz());
			}
		}
		// 加载POJO属性值
		this.loadBeanPropertyValues();
	}

	/**
	 * 实例化POJO
	 * 
	 * @param id
	 * @param clazz
	 */
	private void newInstanceBean(String id, String clazz) {
		try {
			Object obj = beanClassLoader.loadClass(clazz).newInstance();
			applicationContext.setBean(id, obj);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载POJO属性
	 * 
	 * @param obj
	 * @param beanE
	 */
	private void loadBeanProperties(BeanEntity beanEntity, Element beanE) {
		if (beanE == null)
			return;
		List properties = beanE.getChildren("property");
		if (properties == null)
			return;
		List beanEntityProperties = new ArrayList();
		for (int i = 0; i < properties.size(); i++) {
			Element proE = (Element) properties.get(i);
			String name = StringUtil
					.replaceNull(proE.getAttributeValue("name"));
			String value = StringUtil.replaceNull(proE
					.getAttributeValue("value"));
			String ref = StringUtil.replaceNull(proE.getAttributeValue("ref"));
			PropertyEntity proEntity = new PropertyEntity();
			proEntity.setName(name);
			proEntity.setValue(value);
			proEntity.setRef(ref);
			beanEntityProperties.add(proEntity);
		}
		beanEntity.setProperties(beanEntityProperties);
	}

	/**
	 * 加载POJO属性值
	 * 
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private void loadBeanPropertyValues() {
		for (Map.Entry<String, BeanEntity> entry : this.applicationContext
				.getBeanEntities().entrySet()) {
			String key = entry.getKey();
			BeanEntity beanEntity = entry.getValue();
			if ("true".equals(beanEntity.getSingleton())) {
				List properties = beanEntity.getProperties();
				if (properties == null)
					continue;
				for (int i = 0; i < properties.size(); i++) {
					PropertyEntity proEntity = (PropertyEntity) properties
							.get(i);
					Object obj = applicationContext.getBean(beanEntity.getId());
					Setter setter = BeanFactory.getSetter(obj.getClass(),
							proEntity.getName());
					if (!"".equals(proEntity.getRef())) {
						setter.set(obj, applicationContext.getBean(proEntity
								.getRef()));
					} else {
						setter.set(obj, proEntity.getValue());
					}
				}
			}
		}
	}
}
