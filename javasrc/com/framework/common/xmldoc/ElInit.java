package com.framework.common.xmldoc;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.jdom.Document;
import org.jdom.Element;

/**
 * 功能说明:元素初始化
 * @author wangyf
 * @create-date 2010-11-18
 */
public class ElInit {
	private Map elHash=new HashMap();
	
	public ElInit(){
		
	}	
	/**
	 * 初始化元素,并且把元素对象存入哈希表
	 * @param arg 元素配置文件绝对路径名称	
	 */
	public void init(String arg1){
		if(arg1==null) return;
		File file=new File(arg1);
		this.initHash(file);
	}
	/**
	 * 初始化元素,并且把元素对象存入哈希表
	 * @param file
	 */
	public void initHash(File file){
		if(file==null) return;
		DocXMLUtil adpXMLUtil=new DocXMLUtil();
		Document doc=adpXMLUtil.buildDocument(file);		
		if(doc==null) return;	
		this.putElement(doc);
	}
	/**
	 * 把元素对象存入哈希表
	 * @param doc
	 */
	public void putElement(Document doc){
		Element rootE=doc.getRootElement();
		if(rootE==null) return;
		this.putElement(rootE);
	}
	/**
	 * 递归把元素以及该元素的子集对象存入哈希表
	 * @param parentE
	 */
	public void putElement(Element parentE){
		if(parentE==null) return;		
		this.elHash.put(parentE.getAttributeValue("EID"), parentE);
		List subs=parentE.getChildren();
		if(subs==null) return;
		for(int i=0;i<subs.size();i++){
			Element subE=(Element)subs.get(i);
			putElement(subE);
		}
	}
	/**
	 * 返回元素哈希表
	 * @return
	 */
	public Map getElHash(){
		return this.elHash;
	}
}
