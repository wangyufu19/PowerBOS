package com.powerbosframework.beans;

import java.io.IOException;
import java.io.InputStream;
import org.jdom.Document;
import org.jdom.Element;
import com.powerbosframework.context.ApplicatioinContextException;
import com.powerbosframework.context.resource.Resource;
import com.powerbosframework.context.resource.ResourceImpl;
import com.powerbosframework.util.XmlUtil;
/**
 * POJO对象工厂类
 * @author youfu.wang
 * @version 1.0
 */
public class BeanObjectFactory {
	private XmlUtil xmlUtil = new XmlUtil();
	
	public void load(String location) throws IOException, ApplicatioinContextException{
		Resource resource = new ResourceImpl(location);
		InputStream stream = resource.getInputStream();
		if (stream == null)
			throw new IOException(resource + "资源文件没有找到");

		Document doc = xmlUtil.parse(stream);
		if (doc == null)
			throw new ApplicatioinContextException(resource + "资源文件解析失败");
		Element rootE = doc.getRootElement();
		if (rootE == null)
			throw new ApplicatioinContextException(resource + "资源文件解析失败");
	}
	private void loadBeanObject(String location) throws IOException, ApplicatioinContextException{
	
	}

}
