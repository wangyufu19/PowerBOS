package com.powerbosframework.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * XML解析帮助类
 * @author youfu.wang
 * @version 1.0
 */
public class XmlUtil {
	
	/**
	 * 解析资源为Document对象
	 * @param resource
	 * @return
	 */
	public Document parse(String resource){		
		SAXBuilder builder=new SAXBuilder();		
		Document doc=null;			
		doc=this.parse(resource);
		return doc;				
	}
	/**
	 * 解析文件为Document对象
	 * @param file
	 * @return
	 */
	public Document parse(File file){		
		SAXBuilder builder=new SAXBuilder();		
		Document doc=null;						
		try {
			doc=builder.build(file);			
		} catch (JDOMException e) {				
			e.printStackTrace();
		} catch (IOException e) {				
			e.printStackTrace();
		}			
		return doc;				
	}
	/**
	 * 解析文件输入流为Document对象
	 * @param stream
	 * @return
	 */
	public Document parse(InputStream stream){
		SAXBuilder builder=new SAXBuilder();	
		Document doc=null;	
		try {
			doc=builder.build(stream);
		} catch (JDOMException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return doc;
	}
	/**
	 * 解析url为Document对象
	 * @param url
	 * @return
	 */
	public Document parse(URL url){
		SAXBuilder builder=new SAXBuilder();	
		Document doc=null;	
		try {
			doc=builder.build(url);
		} catch (JDOMException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return doc;
	}

}
