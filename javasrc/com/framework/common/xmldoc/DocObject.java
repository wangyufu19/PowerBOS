package com.framework.common.xmldoc;
import java.util.List;
import java.util.Map;
import org.jdom.Element;
public interface DocObject {	
	public void setElHash(Map elHash);
	public void saveDoc();
	public void setDocConfig(String config);
	public String getDocConfig();
	public Element getDocRootElement();
	public Element getDocElement(String key);
	public List getDocChildrenElement(String key);
	public List getDocChildrenElementTree(String key);
	public void addDocElementCDATA(String key,String arg1);
	public void addDocElement(String pKey,String arg1);
	public void addDocElement(String pKey,Element subE);
	public void addDocElement(Element parentE,Element subE);
	public void addBatchDocElement(Element parentE,List list);
	public void deleteDocElement(String pKey,String key);
	public void deleteDocElement(Element parentE,String key);
	public void deleteDocElement(String pKey,Element subE);
	public void deleteDocElement(Element parentE,Element subE);
	public void deleteBatchDocElement(Element parentE,List list);
	public String getAttbitueValue(String key,String name);
	public List getAttributeValues(String key);
	public void updateAttribute(String key,String name,String value);
	public void updateAttribute(String key,String[] nameArray,String[] valueArray);
}
