package com.powerbosframework.context;

import java.io.InputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import org.jdom.Document;
import org.jdom.Element;
import com.controller.action.ActionServlet;
import com.powerbosframework.beans.BeanException;
import com.powerbosframework.beans.BeanObjectLoader;
import com.powerbosframework.beans.parsing.BeanEntity;
import com.powerbosframework.context.ApplicatioinContextException;
import com.powerbosframework.context.ApplicationContext;
import com.powerbosframework.context.resource.Resource;
import com.powerbosframework.context.resource.ResourceImpl;
import com.powerbosframework.context.resource.ResourceLoader;
import com.powerbosframework.log.LogFactory;
import com.powerbosframework.log.Logger;
import com.powerbosframework.util.StringUtil;
import com.powerbosframework.util.XmlUtil;

/**
 * 应用上下文管理
 * 
 * @author youfu.wang
 * @version 1.0
 */
public class XmlApplicationContext implements ApplicationContext {
	private String configuration;
	private static Map<String, BeanEntity> beanEntities = Collections
			.synchronizedMap(new HashMap<String, BeanEntity>());
	private static Map<String, Object> beanObjects = Collections
			.synchronizedMap(new HashMap<String, Object>());
	private ActionServlet actionServlet;
	private static Logger log = LogFactory.getInstance();


	public XmlApplicationContext() {
		configuration = "applicationContext.xml";
	}

	public XmlApplicationContext(String configuration) {
		this.configuration = configuration;
	}

	/**
	 * 设置ActionServlet
	 * 
	 * @param actionServlet
	 */
	@Override
	public void setActionServlet(ActionServlet actionServlet) {
		this.actionServlet = actionServlet;
	}

	/**
	 * 销毁应用上下文内存
	 */
	@Override
	public void destory() {
		if (beanEntities != null)
			beanEntities.clear();
		if (beanObjects != null)
			beanObjects.clear();
	}

	/**
	 * 加载应用上下文资源
	 * 
	 * @throws ApplicatioinContextException
	 * @throws IOException
	 */
	public void load() throws IOException, ApplicatioinContextException {
		this.load(configuration);
	}

	/**
	 * 加载应用上下文资源
	 * 
	 * @param location
	 * @throws IOException
	 * @throws ApplicatioinContextException
	 * @throws BeanException
	 */
	public void load(String location) throws IOException,
			ApplicatioinContextException {
		
		BeanObjectLoader beanLoader = new BeanObjectLoader(this);
		ResourceLoader resourceLoader = new ResourceLoader(beanLoader);

		List includeInputStreamResources = null;
		includeInputStreamResources = rootE.getChildren("include");
		if (includeInputStreamResources == null)
			return;

		for (int i = 0; i < includeInputStreamResources.size(); i++) {
			Element resourceE = (Element) includeInputStreamResources.get(i);
			Resource incresource = new ResourceImpl(StringUtil
					.replaceNull(resourceE.getAttributeValue("resource")));
			resourceLoader.load(incresource);
		}
		// 加载POJO对象
		List beans = rootE.getChildren("bean");
		try {
			beanLoader.load(beans);
		} catch (Exception e) {
			new BeanException(resource.getResourceName() + "资源文件POJO加载失败")
					.printStackTrace();
		}
		// 初始化控制器插件
		Element pluginE = rootE.getChild("plugin");
		try {
			this.initControllerPlugin(pluginE);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void initControllerPlugin(Element pluginE)
			throws InstantiationException, IllegalAccessException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			InvocationTargetException, ClassNotFoundException {
		if (pluginE == null)
			return;
		String clazz = StringUtil.replaceNull(pluginE
				.getAttributeValue("class"));
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		Class cls = null;
		cls = classLoader.loadClass(clazz);
		if (cls == null) {
			Class.forName(clazz);
		}
		if (cls != null) {

			Method method = cls.getMethod("init", ActionServlet.class);
			method.invoke(cls.newInstance(), actionServlet);
		}
	}

	@Override
	public void setBeanEntity(String id, BeanEntity beanEntity) {
		beanEntities.put(id, beanEntity);
	}

	@Override
	public Map<String, BeanEntity> getBeanEntities() {
		return this.beanEntities;
	}

	@Override
	public synchronized BeanEntity getBeanEntity(String id) {
		if (beanEntities.containsKey(id)) {
			return beanEntities.get(id);
		}
		return null;
	}

	@Override
	public void setBean(String id, Object obj) {
		beanObjects.put(id, obj);
	}

	@Override
	public synchronized Object getBean(String id) {
		if (beanObjects.containsKey(id)) {
			return beanObjects.get(id);
		}
		return null;
	}

}
