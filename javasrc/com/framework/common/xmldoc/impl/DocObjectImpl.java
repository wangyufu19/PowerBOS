package com.framework.common.xmldoc.impl;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jdom.Attribute;
import org.jdom.CDATA;
import org.jdom.Element;

import com.framework.common.xmldoc.DocObject;
import com.framework.common.xmldoc.DocXMLUtil;
import com.framework.common.xmldoc.ElSequence;
import com.powerbosframework.util.StringUtil;

public class DocObjectImpl implements DocObject{
	private Map elHash=new HashMap();
	private DocXMLUtil adpXMLUtil=new DocXMLUtil();
	private String config;
	public DocObjectImpl(){
		
	}
	public void setElHash(Map elHash){
		this.elHash=elHash;
	}
	/**
	 * 保存文档配置
	 */
	public void saveDoc(){
		adpXMLUtil.outputDocumentFile(this.getDocConfig());		
	}
	/**
	 * 设置文档配置
	 */
	public void setDocConfig(String config){
		this.config=config;
	}
	/**
	 * 得到文档配置
	 * @return
	 */
	public String getDocConfig(){
		return config;
	}
	/**
	 * 把元素对象存入哈希表
	 * @param e
	 */
	public void putElement(Element parentE,Element subE){	
		if(parentE==null||subE==null) return;	
		String idValue=subE.getDocument().getRootElement().getAttributeValue("idValue");			
		ElSequence elObj=new ElSequence();			
		elObj.setValue(Integer.parseInt(idValue));	
		adpXMLUtil.setFlag(subE, elObj);			
		subE.getDocument().getRootElement().setAttribute("idValue", ""+elObj.getValue());
	}
	/**
	 * 得到文档根元素对象
	 */
	public Element getDocRootElement(){
		return this.getDocElement("EID_0");
	}
	/**
	 * 得到文档元素对象
	 * @param key
	 * @return
	 */
	public Element getDocElement(String key){
		if(key==null) return null;
		if(!elHash.containsKey(key)) return null;			
		return (Element)elHash.get(key);
	}	
	
	/**
	 * 得到文档元素对象
	 * @param key
	 * @return
	 */
	public List getDocChildrenElement(String key){
		if(key==null) return null;
		List list=new ArrayList();
		Element parentE=this.getDocElement(key);
		if(parentE==null) return list;
		List subs=parentE.getChildren();
		if(subs==null) return list;
		for(int i=0;i<subs.size();i++){
			Element subE=(Element)subs.get(i);
			list.add(subE);
		}
		return list;
	}
	/**
	 * 得到文档元素树
	 * @param key
	 * @return
	 */
	public List getDocChildrenElementTree(String key){
		if(key==null) return null;
		List list=new ArrayList();
		Element parentE=this.getDocElement(key);
		if(parentE==null) return list;
		this.putElementTree(list, parentE);
		return list;
	}
	/**
	 * 递归文档元素树
	 * @param list
	 * @param parentE
	 */
	public void putElementTree(List list,Element parentE){
		List subs=parentE.getChildren();
		if(subs==null) return;
		for(int i=0;i<subs.size();i++){
			Element subE=(Element)subs.get(i);
			list.add(subE);
			this.putElementTree(list, subE);
		}
	}
	/**
	 * 增加文档元素CDATA内容
	 * @param key
	 * @param arg1
	 */
	public void addDocElementCDATA(String key,String arg1){
		if(key==null) return;	
		if("".equals(key)) return;
		Element el=this.getDocElement(key);
		if(el==null) return;
		CDATA cdata=new CDATA(arg1);	
		el.setContent(cdata);
		this.saveDoc();		
	}
	/**
	 * 增加文档元素
	 * @param pKey
	 * @param arg1
	 */
	public void addDocElement(String pKey,String arg1){
		if(pKey==null) return;
		if(arg1==null) return;
		if("".equals(arg1)||"".equals(pKey)) return;
		Element subE=new Element(arg1.toUpperCase());
		this.addDocElement(pKey, subE);
	}
	/**
	 * 增加文档元素
	 * @param pKey
	 * @param subE
	 */
	public void addDocElement(String pKey,Element subE){
		if(pKey==null||subE==null) return;
		Element parentE=this.getDocElement(pKey);
		if(parentE==null) return;
		this.addDocElement(parentE, subE);
	}
	/**
	 * 增加文档元素
	 * @param parentE
	 * @param subE
	 */
	public void addDocElement(Element parentE,Element subE){
		if(parentE==null||subE==null) return;
		parentE.addContent(subE);
		this.putElement(parentE, subE);
		this.saveDoc();	
	}
	/**
	 * 增加批量文档元素
	 * @param parentE
	 * @param list
	 */
	public void addBatchDocElement(Element parentE,List list){
		if(parentE==null||list==null) return;
		for(int i=0;i<list.size();i++){
			Element subE=(Element)list.get(i);
			parentE.addContent(subE);
			this.putElement(parentE, subE);
		}
		this.saveDoc();		
	}
	/**
	 * 删除文档元素
	 * @param pKey
	 * @param key
	 */
	public void deleteDocElement(String pKey,String key){
		if(pKey==null||key==null) return;
		Element parentE=this.getDocElement(pKey);
		Element subE=this.getDocElement(key);
		this.deleteDocElement(parentE, subE);
	}
	/**
	 * 删除文档元素
	 * @param parentE
	 * @param key
	 */
	public void deleteDocElement(Element parentE,String key){
		if(parentE==null||key==null) return;
		Element subE=this.getDocElement(key);
		this.deleteDocElement(parentE, subE);
	}
	/**
	 * 删除文档元素
	 * @param pKey
	 * @param subE
	 */
	public void deleteDocElement(String pKey,Element subE){
		if(pKey==null||subE==null) return;
		Element parentE=this.getDocElement(pKey);
		this.deleteDocElement(parentE, subE);
	}
	/**
	 * 删除文档元素
	 * @param parentE
	 * @param subE
	 */
	public void deleteDocElement(Element parentE,Element subE){
		if(parentE==null||subE==null) return;
		parentE.removeContent(subE);
		this.saveDoc();
	}
	/**
	 * 批量删除文档元素
	 * @param parentE
	 * @param list
	 */
	public void deleteBatchDocElement(Element parentE,List list){
		if(parentE==null||list==null) return;
		for(int i=0;i<list.size();i++){
			Element subE=(Element)list.get(i);
			parentE.removeContent(subE);
		}
		this.saveDoc();
	}
	
	/**
	 * 得到文档元素属性值
	 * @param key
	 * @param name
	 * @return
	 */
	public String getAttbitueValue(String key,String name){
		String value="";
		Element el=this.getDocElement(key);
		if(el==null) return value;
		List items=el.getAttributes();			
		if(items==null) return value;
		for(int i=0;i<items.size();i++){
	    	Attribute att=(Attribute)items.get(i);	
	    	if(att.getName().equals("ETM"))	continue;
	    	String[] arr=new String[3];	    
	    	arr[1]=StringUtil.replaceNull(att.getName());
	    	arr[2]=StringUtil.replaceNull(att.getValue());
	    	if(arr[1].equals(name)){
	    		value=arr[2];
	    		break;
	    	}
	    }	
		return value;		
	}
	/**
	 * 得到文档元素属性数组列表
	 * @param key
	 * @return
	 */
	public List getAttributeValues(String key){		
		List list=new ArrayList();		
		int index=0;
		Element e=this.getDocElement(key);
		if(e==null) return list;
		List items=e.getAttributes();			
		if(items==null) return list;
	    for(int i=0;i<items.size();i++){
	    	Attribute att=(Attribute)items.get(i);	
	    	if(att.getName().equals("ETM"))
	    		continue;
	    	String[] arr=new String[3];
	    	arr[0]=String.valueOf(index++);
	    	arr[1]=StringUtil.replaceNull(att.getName());
	    	arr[2]=StringUtil.replaceNull(att.getValue());
	    	list.add(arr);
	    }	
	    return list;
	}
	/**
	 * 保存文档元素属性
	 * @param key
	 * @param name
	 * @param value
	 */
	public void updateAttribute(String key,String name,String value){
		if(key==null||name==null||value==null) return;		
		Element el=this.getDocElement(key);
		if(el==null) return;
		List list=el.getAttributes();				
	    if(list==null) return;	
		for(int i=0;i<list.size();i++){
			Attribute attribute=(Attribute)list.get(i);
			if(attribute.getName().equals(name)){
				el.removeAttribute(attribute);
			}
		}		
		el.setAttribute(name, value);
		this.saveDoc();	
	}
	/**
	 * 保存文档元素属性
	 * @param key
	 * @param nameArray
	 * @param valueArray
	 */
	public void updateAttribute(String key,String[] nameArray,String[] valueArray){
		if(key==null||nameArray==null||valueArray==null) return;
		for(int i=0;i<nameArray.length;i++){	
			Element el=this.getDocElement(key);
			if(el==null) return;				
			el.setAttribute(nameArray[i], valueArray[i]);
			this.updateAttribute(key, nameArray[i], valueArray[i]);
		}			
	}

}
