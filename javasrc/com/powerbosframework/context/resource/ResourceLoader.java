package com.powerbosframework.context.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import com.powerbosframework.beans.BeanException;
import com.powerbosframework.beans.BeanObjectLoader;
import com.powerbosframework.context.ApplicatioinContextException;
import com.powerbosframework.util.XmlUtil;

/**
 * 上下文资源加载器
 * 
 * @author youfu.wang
 * @version 1.0
 */
public class ResourceLoader {
	private BeanObjectLoader beanLoader;
	private XmlUtil xmlUtil = new XmlUtil();

	public ResourceLoader(BeanObjectLoader beanLoader) {
		this.beanLoader = beanLoader;
	}

	/**
	 * 加载POJO
	 * 
	 * @param resource
	 * @throws IOException
	 * @throws ApplicatioinContextException
	 * @throws BeanException
	 */
	public void load(Resource resource) throws IOException,
			ApplicatioinContextException {
		InputStream stream = resource.getInputStream();
		if (stream == null)
			throw new IOException(resource.getResourceName() + "资源文件没有找到");
		Document doc = xmlUtil.parse(stream);
		if (doc == null)
			throw new ApplicatioinContextException(resource.getResourceName()
					+ "资源文件解析失败");
		Element rootE = doc.getRootElement();
		if (rootE == null)
			throw new ApplicatioinContextException(resource.getResourceName()
					+ "资源文件解析失败");
		List beans = rootE.getChildren("bean");
		try {
			beanLoader.load(beans);
		} catch (Exception e) {
			new BeanException(resource + "资源文件POJO加载失败").printStackTrace();
		}
	}
}
