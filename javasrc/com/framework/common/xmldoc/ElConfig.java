package com.framework.common.xmldoc;
//package com.powerbos.framework.console.cfg;
//package com.powerbos.framework.adp.cfg;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Iterator;
//import org.jdom.Attribute;
//import org.jdom.CDATA;
//import org.jdom.Document;
//import org.jdom.Element;
//
//import com.persistence.cfg.Resource;
//import com.persistence.cfg.ResourceFactory;
//import com.persistence.exception.MappingException;
//import com.persistence.property.Metadata;
//import com.persistence.property.Property;
//import com.persistence.id.Identifier;
//import com.powerbos.framework.exception.BOSException;
//import com.powerbos.framework.adp.el.ElLabel;
//import com.powerbos.framework.adp.el.ElObj;
//import com.powerbos.framework.adp.util.ReplaceUtil;
//import com.powerbos.framework.constants.SysConstants;
//
//import com.powerbos.framework.page.column.Column;
//import com.powerbos.framework.page.parse.ColumnParser;
//import com.powerbos.framework.page.util.StringUtil;
//import com.powerbos.framework.util.CheckData;
//import com.powerbos.framework.util.Debug;
//import com.powerbos.framework.util.MyString;
//import com.powerbos.framework.xml.XMLUtil;
//import com.powerbos.framework.util.ArrayRow;
//
///**
// * 功能说明:页面元素配置类
// * @author wangyf
// * @version 1.0
// */
//public class ElConfig {
//	private HashMap<String, HashMap> elHash=new HashMap<String, HashMap>();	
//	private XMLUtil xmlUtil;	
//	private String fileName;
//	private String type;
//	public ElConfig(){
//		xmlUtil=new XMLUtil();			
//	}
//	public ElConfig(String fileName,String type){
//		this.fileName=fileName;
//		this.type=type;
//		xmlUtil=new XMLUtil();			
//		initHash();
//	}
//	public void reload(){
//		if(fileName.equals("")){
//			return;
//		}
//		String path="";
//		path=getFile(fileName,type);
//		Document doc=xmlUtil.getDocument(path);		
//		if(doc==null){
//			return;
//		}				
//		try{
//			setConfig(doc,path);
//		}catch(Exception e){		  
//			Debug.println("******初始化元素Hash失败");
//			e.printStackTrace();
//		}		
//	}
//	public void initHash(){		
//		if(fileName.equals("")){
//			return;
//		}
//		String path="";
//		path=getFile(fileName,type);
//		if(elHash.containsKey(path)){				
//			return;
//		}
//		Document doc=xmlUtil.getDocument(path);		
//		if(doc==null){
//			return;
//		}				
//		try{
//			setConfig(doc,path);
//		}catch(Exception e){
//		    Debug.println("******初始化元素Hash失败");
//			e.printStackTrace();
//		}		
//	}
//	public void setConfig(Document doc,String path){
//		if(elHash.containsKey(path)){
//			return;
//		}
//		HashMap<String, Element> tmpHash=new HashMap<String, Element>();
//		putElement(doc.getRootElement(),tmpHash);
//		elHash.put(path, tmpHash);
//	}
//	public void putElement(Element e,HashMap<String, Element> hash){
//		if(e==null){
//			return;
//		}		
//		hash.put(e.getAttributeValue("ETM"), e);
//		List list=e.getChildren();
//		if(list!=null&&list.size()>0){
//			for(int i=0;i<list.size();i++){
//				Element sub=(Element)list.get(i);
//				putElement(sub,hash);
//			}
//		}
//	}
//	public Element getRootElement(){
//		String path=getFile(fileName,type);
//		Document doc=xmlUtil.getDocument(path);
//		if(doc==null){
//			return null;
//		}
//		return doc.getRootElement();
//	}
//	public Element getElement(String key){
//		String path=getFile(fileName,type);
//		return getElement(path,key);
//	}
//	public Element getElement(String path,String key){
//		if(!elHash.containsKey(path)){		
//			return null;
//		}
//		HashMap tmpHash=elHash.get(path);
//		if(tmpHash==null){
//			return null;
//		}
//		if(!tmpHash.containsKey(key)){
//			return null;
//		}
//		return (Element)tmpHash.get(key);
//	}	
//	/**
//	 * 得到根的子元素
//	 * @return
//	 */
//	public List getElementUnderRoot(){		
//		String[][] ret=null;		
//		List list=new ArrayList();
//		String path=getFile(fileName,type);
//		Document doc=xmlUtil.getDocument(path);
//		Element rootE=doc.getRootElement();
//		if(rootE==null) return list;			
//		List items=rootE.getChildren();
//		if(items==null) return null;
//		for(int i=0;i<items.size();i++){
//			Element subE=(Element)items.get(i);
//			String[] arr=new String[3];
//			arr[0]=StringUtil.replaceNull(subE.getAttributeValue("ETM"));
//			arr[1]=StringUtil.replaceNull(subE.getName());
//			arr[2]=StringUtil.replaceNull(subE.getAttributeValue("name"));
//			list.add(arr);
//			
//		}		
//		return list;
//	}
//	
//
//	public void addElement(String parentKey,Element sub){		
//		Element parentElement=null;
//		String path=getFile(fileName,type);
//		if(parentKey==null||parentKey.equals("")){
//			parentElement=getRootElement();
//		}else
//			parentElement=getElement(parentKey);
//		if(parentElement==null){
//			return;
//		}		
//		try{			
//			parentElement.addContent(sub);
//			doWithAddElement(sub);
//			xmlUtil.outputDocumentFile(path);	
//		}catch(Exception e){
//			System.out.println("******添加元素失败");
//			e.printStackTrace();
//		}				
//	}
//	public void addElement(String parentKey,String name){
//		if(name==null||name.equals(""))
//			return;
//		Element sub=new Element(name.toUpperCase());			
//		addElement(parentKey,sub);
//			
//	}
//	public void addElement(String parentKey,String name,String text){
//		if(name==null||name.equals("")){
//			return;
//		}
//		Element sub=new Element(name.toUpperCase());
//		sub.setText(text);
//		addElement(parentKey,sub);
//	}
//	/**
//	 * 得到元素内容
//	 * @param key
//	 * @return
//	 */
//	public String getElementText(String key){
//		Element e=getElement(key);
//		if(e==null) return "";
//		return e.getTextTrim();
//	}
//	/**
//	 * 添加元素内容
//	 * @param key
//	 * @param CDATA
//	 */
//	public void addElementCDATA(String key,String CDATA){
//		Element e=getElement(key);
//		if(e==null)return;
//		String path=getFile(fileName,type);			
//		List list=e.getChildren();	
//		CDATA cDATA=null;
//		cDATA=new CDATA(CDATA);			
//		if(list==null||list.size()<1){					
//			e.setContent(cDATA);				
//		}else{				
//			ArrayList<Element> v=new ArrayList<Element>();				
//			for(int i=0;i<list.size();i++){
//				Element sub=(Element)list.get(i);
//				v.add(sub);
//			}				
//			e.setContent(cDATA);
//			for(int k=0;k<v.size();k++){
//				Element sub=(Element)v.get(k);
//				e.addContent(sub);
//			}				
//		}								
//		xmlUtil.outputDocumentFile(path);
//						
//	}
//	public void addElementText(String key,String text){		
//		Element e=getElement(key);
//		if(e==null){
//			return;
//		}
//		List list=e.getChildren();
//		String path=getFile(fileName,type);		
//		try{			
//			if(list==null||list.size()<1){
//				e.setText(text);
//			}else{
//				ArrayList<Element> v=new ArrayList<Element>();				
//				for(int i=0;i<list.size();i++){
//					Element sub=(Element)list.get(i);
//					//Debug.println("remove 第" + i + "个sub=" + sub.getName() + " id=" + sub.getAttributeValue("ETM"));
//					v.add(sub);
//				}
//				e.setText(text);
//				for(int j=0;j<v.size();j++){
//					Element sub=(Element)v.get(j);
//					e.addContent(sub);
//				}
//			}			
//			xmlUtil.outputDocumentFile(path);	
//		}catch(Exception e1){
//			System.out.println("******添加元素内容失败");
//			e1.printStackTrace();
//		}	
//	}
//	public List getAttributeValue(String key){		
//		List list=new ArrayList();		
//		int index=0;
//		Element e=getElement(key);		
//		if(e==null) return list;
//		List items=e.getAttributes();			
//		if(items==null) return list;
//	    for(int i=0;i<items.size();i++){
//	    	Attribute att=(Attribute)items.get(i);	
//	    	if(att.getName().equals("ETM"))
//	    		continue;
//	    	String[] arr=new String[3];
//	    	arr[0]=String.valueOf(index++);
//	    	arr[1]=StringUtil.replaceNull(att.getName());
//	    	arr[2]=StringUtil.replaceNull(att.getValue());
//	    	list.add(arr);
//	    }	
//	    return list;
//	}
//	/**
//	 * 得到元素属性值
//	 * @param key
//	 * @param name
//	 * @return
//	 */
//	public String getAttbitueValue(String key,String name){
//		String value="";
//		Element el=getElement(key);		
//		if(el==null) return value;
//		List items=el.getAttributes();			
//		if(items==null) return value;
//		for(int i=0;i<items.size();i++){
//	    	Attribute att=(Attribute)items.get(i);	
//	    	if(att.getName().equals("ETM"))
//	    		continue;
//	    	String[] arr=new String[3];	    
//	    	arr[1]=StringUtil.replaceNull(att.getName());
//	    	arr[2]=StringUtil.replaceNull(att.getValue());
//	    	if(arr[1].equals(name)){
//	    		value=arr[2];
//	    		break;
//	    	}
//	    }	
//		return value;		
//	}
//	/**
//	 * 添加元素属性
//	 * @param key
//	 * @param name
//	 * @param value
//	 */
//	public void addAttribute(String key,String name,String value){		
//		if(name==null||name.equals("")) return;
//		Element e=getElement(key);
//		if(e==null) return;
//		String path=getFile(fileName,type);		
//		e.setAttribute(name, value);
//		xmlUtil.outputDocumentFile(path);			
//				
//	}
//	/**
//	 * 删除元素属性
//	 * @param key
//	 * @param name
//	 */
//	public void deleteAttribute(String key,String name){		
//		if(name==null||name.equals("")) return;
//		Element e=getElement(key);
//		if(e==null) return;
//		String path=getFile(fileName,type);	
//		e.removeAttribute(name);					
//		xmlUtil.outputDocumentFile(path);				
//	}
//	/**
//	 * 修改元素属性
//	 * @param key
//	 * @param name
//	 * @param value
//	 */
//	public void updateAttribute(String key,String name,String value){
//		if(name==null&&value==null) return;
//		Element e=getElement(key);
//		if(e==null) return;
//		List list=e.getAttributes();		
//		String path=getFile(fileName,type);	
//	    if(list==null) return;	
//		for(int i=0;i<list.size();i++){
//			Attribute attribute=(Attribute)list.get(i);
//			if(attribute.getName().equals(name)){
//				e.removeAttribute(attribute);
//			}
//		}		
//		e.setAttribute(name, value);
//		xmlUtil.outputDocumentFile(path);			
//	}
//	/**
//	 * 得到子元素列表
//	 * @param parentKey
//	 * @return
//	 */
//	public List getChildrenElement(String parentKey){
//		List list=new ArrayList();
//		if(parentKey==null||parentKey.equals("")) return list;		
//		Element parentElement=getElement(parentKey);
//		if(parentElement==null) return list;
//		List items=parentElement.getChildren();		
//		if(items==null) return list;		
//		for(int i=0;i<items.size();i++){
//			String[] arr=new String[8];			
//			Element subE=(Element)items.get(i);
//			arr[0]=StringUtil.replaceNull(subE.getAttributeValue("ETM"));
//			arr[1]=StringUtil.replaceNull(parentKey);
//			arr[2]=StringUtil.replaceNull(subE.getName());
//			arr[3]=StringUtil.replaceNull(subE.getTextTrim());
//			arr[4]=StringUtil.replaceNull(subE.getAttributeValue("name"));
//		    arr[5]=StringUtil.replaceNull(subE.getAttributeValue("url"));		   
//		    if(subE.getName().equals("COLUMN")){
//		    	Element child=subE.getChild("NAME");
//		    	if(child!=null){
//		    		arr[6]=StringUtil.replaceNull(child.getTextTrim());
//		    	}else
//		    		arr[6]="";
//		    }else if(subE.getName().equals("ACTION")){
//		    	arr[6]=StringUtil.replaceNull(subE.getAttributeValue("action"));		    	
//		    }else if(subE.getName().equals("MENU")){
//		    	arr[6]=StringUtil.replaceNull(subE.getAttributeValue("name"));
//		    }else
//		    	arr[6]="";
//		    arr[7]=StringUtil.replaceNull(ReplaceUtil.getElementCName(type, subE.getName()));
//		    list.add(arr);
//		}		
//		return list;
//	}
//	/**
//	 * 排序元素
//	 * @param key
//	 * @param sortKey
//	 */
//	public void sortElement(String pKey,String[] sortKey){
//		if(pKey==null||pKey.equals("")) return;
//		if(sortKey==null) return;
//		Element parentElement=getElement(pKey);
//		if(parentElement==null) return;
//		String path=getFile(fileName,type);
//		ArrayList<Element> v=new ArrayList<Element>();
//		for(int i=0;i<sortKey.length;i++){
//			Element sub=getElement(sortKey[i]);
//			v.add(sub);
//			parentElement.removeContent(sub);
//		}
//		for(int i=0;i<v.size();i++){
//			Element sub=(Element)v.get(i);
//			parentElement.addContent(sub);
//		}
//		xmlUtil.outputDocumentFile(path);		
//	}
//	/**
//	 * 加载元素树
//	 * @param parentKey
//	 * @return
//	 */
//	public String[][] getElementTree(String parentKey){
//		String[][] ret=null;
//		if(parentKey==null)return null;
//		Element parentElement=null;		
//		parentElement=getElement(parentKey);
//		if(parentElement==null) return null;
//		ArrayList<String[]> v=new ArrayList<String[]>();
//		String path=getFile(fileName,type);
//		HashMap hash=elHash.get(path);
//		if(hash==null||hash.size()<1) return null;
//		setTreeElement(v,parentElement);
//		if(v.size()<1) return null;
//		ret=MyString.toArray(v);
//		return ret;		
//	}
//	public void setTreeElement(ArrayList<String[]> v,Element e){
//		List list=e.getChildren();
//		if(list!=null){
//			for(int i=0;i<list.size();i++){
//				Element sub=(Element)list.get(i);
//				setTreeElement(v,sub);
//			}
//		}
//		String key=e.getAttributeValue("ETM");
//		String parentKey=e.getParentElement().getAttributeValue("ETM");
//		String name=e.getName();
//		String parentName=e.getName();		
//		String[] strArray=new String[4];
//		strArray[0]=key;
//		strArray[1]=parentKey;
//		strArray[2]=name;
//		strArray[3]=parentName;
//		v.add(strArray);		
//	}
//	public void deleteElement(String parentKey,String[] key){
//		if(key==null) return;
//		for(int i=0;i<key.length;i++){
//			deleteElement(parentKey,key[i]);
//		}
//		
//	}
//	public void deleteElement(String parentKey,String key){		
//		if(key==null||key.equals("")){
//			return;
//		}				
//		Element parentElement=null;
//		String path=getFile(fileName,type);
//		HashMap hash=elHash.get(path);
//		if(hash==null){
//			return;
//		}
//		
//		Element sub=null;
//		sub=(Element)hash.get(key);
//		if(parentKey==null||parentKey.equals("")){
//			parentElement=getRootElement();
//		}else
//			parentElement=getElement(parentKey);
//		if(parentElement==null){
//			System.out.println("******父元素为空");
//			return;
//		}		
//		try{
//			parentElement.removeContent(sub);
////			hash.remove(key);				
////			elHash.remove(path);
////			System.out.println("size is "+elHash.get(path));
////			elHash.put(path, hash);
////			System.out.println("size is "+hash.size());
//			xmlUtil.outputDocumentFile(path);
//		}catch(Exception e){
//			System.out.println("******删除元素失败");
//			e.printStackTrace();			
//		}
//	}
//	public void doWithAddElement(Element e){
//		String path=getFile(fileName,type);
//		doWithAddElement(path,e);
//	}
//	public void doWithAddElement(String path,Element e){		
//		if(e==null){
//			return;
//		}
//		if(e.getParentElement()==null){
//			return;
//		}
//		HashMap tmpHash=elHash.get(path);		
//		if(tmpHash==null&&tmpHash.size()<1){
//			return;
//		}		
//		String idValue=e.getDocument().getRootElement().getAttributeValue("idValue");			
//		ElObj elObj=new ElObj();			
//		elObj.setValue(Integer.parseInt(idValue));		
//		try{
//			xmlUtil.setFlag(e, elObj);
//		}catch(Exception e1){
//			e1.printStackTrace();
//		}	
//		
//		this.putElement(e, tmpHash);			
//		e.getDocument().getRootElement().setAttribute("idValue", ""+elObj.getValue());
//	}
//	/**
//	 * 得到显示列
//	 * @param key
//	 * @return
//	 */
//	public String[][] getDisplayCol(String key){
//		String[][] retArray=null;	   
//		Element e=getElement(key);
//		Element fieldSubElement=null;
//		Element colSubElement=null;		
//		List list=e.getChildren();
//		List list1=null;		
//		ArrayList<String[]> v=new ArrayList<String[]>();
//		if(list!=null&&list.size()>0){			
//			for(int j=0;j<list.size();j++){				
//				fieldSubElement=(Element)list.get(j);		
//				String[] str=new String[3];						
//				list1=fieldSubElement.getChildren();
//				str[0]=fieldSubElement.getAttributeValue("col");		
//				for(int k=0;k<list1.size();k++){					
//					colSubElement=(Element)list1.get(k);
//					if(colSubElement.getName().equals("NAME")){
//						str[1]=colSubElement.getText();
//					}else if(colSubElement.getName().equals("DISPLAYNAME")){
//						str[2]=colSubElement.getText();
//					}					
//				}	
//				v.add(str);
//			}
//			retArray=MyString.toArray(v);
//		}		
//		return retArray;
//	}
//	
//	public void addActionColumn(Element fieldE,String action,String id){		
//		ElLabel elLabel=new ElLabel("PAGE");		
//		ArrayRow arrayRow=new ArrayRow(elLabel.getEleLabel("COLUMN"));
//		if(fieldE==null) return;
//		if(arrayRow==null) return;
//		Element column=new Element("COLUMN");
//		if("deleteOne".equals(action)){
//			column.setAttribute("col", "100");
//		}else if("update".equals(action)){
//			column.setAttribute("col", "101");
//		}		
//		fieldE.addContent(column);
//		this.doWithAddElement(column);
//				
//		for(int j=0;j<arrayRow.getNum();j++){
//			arrayRow.setRow(j);
//			Element sub=new Element(arrayRow.getString(0));
//			String name="";
//			String displayName="";
//			String displayType="";
//			String value="";			
//			String attribute="";			
//			if("deleteOne".equals(action)){
//				name="deleteAction";
//				displayName="删除";
//				displayType="L";
//				value="<img src=\"fun.getSession(PATH)/lib/default/images/ico_delete_16.gif\" alt=\"删除\" width=\"16\" height=\"16\" hspace=\"2\" vspace=\"2\" border=\"0\" align=\"absmiddle\"/>";
//				attribute="href=\"javascript:doWith_f(fun.getSession(FORM),'deleteOne','您确定要删除吗?','obj.get("+id+")')\"";
//			}else if("update".equals(action)){
//				name="updateAction";
//				displayName="修改";
//				displayType="L";
//				value="<img src=\"fun.getSession(PATH)/lib/default/images/ico_edit_16.gif\" alt=\"修改\" width=\"16\" height=\"16\" hspace=\"2\" vspace=\"2\" border=\"0\" align=\"absmiddle\"/>";
//				//1:winopen;2:url
//				//attribute="href=\"javascript:winopen700('fun.getSession(DYNPAGE)?CODE=XX_XX_XX&SHOW_TYPE=modify&"+id+"=fun.getString(0)')\"";
//				attribute="href=\"fun.getSession(DYNPAGE)?CODE=XX_XX_XX&SHOW_TYPE=modify&"+id+"=obj.get("+id+")\"";
//			}			
//			if(sub.getName().equals("NAME")){
//				sub.setText(name);
//			}else if(sub.getName().equals("DISPLAYNAME")){
//				sub.setText(displayName);
//			}else if(sub.getName().equals("DATATYPE")){
//				sub.setText("");
//			}else if(sub.getName().equals("DISPLAYTYPE")){
//				sub.setText(displayType);
//			}else if(sub.getName().equals("VALUE")){
//				sub.setText(value);
//			}else if(sub.getName().equals("ISNULL")){
//				sub.setText("false");
//			}else if(sub.getName().equals("ATTRIBUTE")){
//				sub.setText(attribute);
//			}else if(sub.getName().equals("ISIDENTIFY")){
//				sub.setText("false");
//			}else if(sub.getName().equals("ISUNIQUE")){
//				sub.setText("false");
//			}else if(sub.getName().equals("DBFIELD")){
//				sub.setText("");
//			}
//			column.addContent(sub);
//			this.doWithAddElement(sub);
//		}
//	}
//
//	public void addFieldsColumn(Element parentE,Element colE,ArrayRow arrayRow,Property property){
//		String name=property.getName();
//		String column=property.getColumn();
//		String length=property.getLength();
//		String scale=property.getLengthScale();
//		for(int j=0;j<arrayRow.getNum();j++){
//			arrayRow.setRow(j);
//			Element subE=new Element(arrayRow.getString(0));
//			String displayType="";
//			String dataType="";
//			String value="";
//			String maxlength="";
//			if("0".equals(scale)) 
//				maxlength=length;
//			else if("".equals(scale))
//				maxlength=length;
//			else 
//				maxlength=length+","+scale;
//			if(parentE.getName().equals("LIST")){
//				dataType="";
//				displayType="CT";						
//				value="obj.get("+name+")";
//			}else if(parentE.getName().equals("MASTER")){
//				dataType="C";
//				displayType="IT";
//				value="obj.get("+name+")";
//			}					
//			if(subE.getName().equals("NAME")){
//				subE.setText(name);
//			}else if(subE.getName().equals("DISPLAYNAME")){
//				subE.setText(name);
//			}else if(subE.getName().equals("DATATYPE")){
//				subE.setText(dataType);
//			}else if(subE.getName().equals("DISPLAYTYPE")){
//				subE.setText(displayType);
//			}else if(subE.getName().equals("MAXLENGTH")){
//				subE.setText(maxlength);
//			}else if(subE.getName().equals("VALUE")){
//				subE.setText(value);
//			}else if(subE.getName().equals("DBFIELD")){
//				subE.setText(column);
//			}				
//			colE.addContent(subE);
//			this.doWithAddElement(subE);
//		}
//	}
//	/**
//	 * 功能说明:V2.0创建字段集方法
//	 * @param key
//	 * @throws MappingException
//	 */
//	public void createFields(String key) throws BOSException{
//		String path=getFile(fileName,type);
//		Element beanE=getElement(key);
//		if(beanE==null) return;
//		Element filedsE=new Element("FIELDS");
//		String sqlMapId=beanE.getAttributeValue("sqlMapId");
//		if(sqlMapId==null) throw new BOSException(new Exception("BEAN元素没有指定所使用的sqlMapId"));
//		if("".equals(sqlMapId)) return;			
//		Map properties=null;
//		Identifier identifier=new Identifier();
//		try {
//			Resource resource=ResourceFactory.getInstance();		
//			Map statment= resource.getStatementMap(sqlMapId);
//			String parameterClass=String.valueOf(statment.get("parameterClass"));
//			Metadata metadata=resource.getParameterClassMetadata(parameterClass);
//			identifier=metadata.getIdentifier();
//			properties=metadata.getProperties();			
//		} catch (MappingException e) {			
//			e.printStackTrace();
//		}			
//		if(properties==null) return;
//		Element parentE=beanE.getParentElement();
//		if(parentE==null) return;
//		//清除配置中的FIELDS元素
//		parentE.removeChild("FIELDS");
//		parentE.addContent(filedsE);
//		this.doWithAddElement(filedsE);
//		//返回COLUMN子元素字符串数组
//		ElLabel elLabel=new ElLabel("PAGE");
//		ArrayRow arrayRow=new ArrayRow(elLabel.getEleLabel("COLUMN"));	
//		int col=0;
//		Property keyProperty=identifier.getProperty();
//		Element keyColE=new Element("COLUMN");
//		keyColE.setAttribute("col", String.valueOf(col));
//		filedsE.addContent(keyColE);
//		this.doWithAddElement(keyColE);			
//		this.addFieldsColumn(parentE, keyColE, arrayRow, keyProperty);
//		col++;
//		for(Iterator it=properties.keySet().iterator();it.hasNext();){
//			Property property=(Property)properties.get(it.next().toString());			
//			Element colE=new Element("COLUMN");
//			colE.setAttribute("col", String.valueOf(col));
//			filedsE.addContent(colE);
//			this.doWithAddElement(colE);			
//			this.addFieldsColumn(parentE, colE, arrayRow, property);
//			col++;			
//		}
//		xmlUtil.outputDocumentFile(path);
//			
//	}
//	/**
//	 * 功能说明:V1.0创建字段集方法
//	 * @param key
//	 */
//	public void initField(String key){
//		String path=getFile(fileName,type);
//		String[] fieldArr=null;
//		Element statement=getElement(key);
//		Element select=null;
//		Element list=null;
//		Element fileds=new Element("FIELDS");
//		if(statement!=null){
//			select=statement.getChild("SELECT");
//			list=statement.getParentElement();			
//		}
//		if(select!=null){
//			fieldArr=CheckData.getParseStr(select.getTextTrim().substring(6, select.getTextTrim().indexOf("from")));
//		}
//		if(list!=null){
//			list.removeChild("FIELDS");
//			list.addContent(fileds);
//			this.doWithAddElement(fileds);
//		}
//		ElLabel elLabel=new ElLabel("PAGE");
//		ArrayRow arrayRow=new ArrayRow(elLabel.getEleLabel("COLUMN"));
//		if(fieldArr!=null){
//			for(int i=0;i<fieldArr.length;i++){
//				Element column=new Element("COLUMN");
//				column.setAttribute("col", String.valueOf(i));
//				fileds.addContent(column);
//				this.doWithAddElement(column);
//				for(int j=0;j<arrayRow.getNum();j++){
//					arrayRow.setRow(j);
//					Element sub=new Element(arrayRow.getString(0));
//					String displayType="";
//					String dataType="";
//					String value="";
//					if(list.getName().equals("LIST")){
//						dataType="";
//						displayType="CT";						
//						value="fun.getString("+i+")";
//					}else if(list.getName().equals("MASTER")){
//						dataType="C";
//						displayType="IT";
//						value="fun.getString("+i+")";
//					}					
//					if(sub.getName().equals("NAME")){
//						sub.setText(CheckData.replaceName(fieldArr[i]));
//					}else if(sub.getName().equals("DISPLAYNAME")){
//						sub.setText(CheckData.replaceName(fieldArr[i]));
//					}else if(sub.getName().equals("DATATYPE")){
//						sub.setText(dataType);
//					}else if(sub.getName().equals("DISPLAYTYPE")){
//						sub.setText(displayType);
//					}else if(sub.getName().equals("VALUE")){
//						sub.setText(value);
//					}else if(sub.getName().equals("ISNULL")){
//						sub.setText("false");
//					}else if(sub.getName().equals("ISIDENTIFY")){
//						sub.setText("false");
//					}else if(sub.getName().equals("ISUNIQUE")){
//						sub.setText("false");
//					}else if(sub.getName().equals("DBFIELD")){
//						sub.setText(fieldArr[i]);
//					}
//					column.addContent(sub);
//					this.doWithAddElement(sub);
//				}
//			}
//		}
//		xmlUtil.outputDocumentFile(path);
//	}
//	public void createShowAddEvent(String pKey){
//		String path=getFile(fileName,type);
//		Element codeE=getElement(pKey);	
//		if(codeE==null) return;
//		List list=codeE.getChildren("ACTION");
//		if(list!=null){
//			for(int i=0;i<list.size();i++){
//				Element actionElement=(Element)list.get(i);
//				String value=actionElement.getAttributeValue("action");
//				if("showAdd".equals(value)){
//					codeE.removeContent(actionElement);
//				}
//			}
//		}		
//    	//创建新增事件
//     	//1:winopen;2:url
////		String href="javascript:winopen700('fun.getSession(DYNPAGE)?CODE=XX_XX_XX&SHOW_TYPE=add')";
//		String href="fun.getSession(DYNPAGE)?CODE=XX_XX_XX&SHOW_TYPE=add";
//		Element actionE=new Element("ACTION");
//		actionE.setAttribute("type", "url");
//		actionE.setAttribute("refreshOpener", "false");
//		actionE.setAttribute("closeWindow", "false");
//		actionE.setAttribute("class", "");
//		actionE.setAttribute("value","新增");
//		actionE.setAttribute("action","showAdd");
//		actionE.setAttribute("href", href);
//		codeE.addContent(actionE);
//		this.doWithAddElement(actionE);
//		xmlUtil.outputDocumentFile(path);
//	}
//	/**
//	 * 创建新事事件
//	 * @param code
//	 * @param key
//	 */
//	public void createAddEvent(String pKey){
//		String path=getFile(fileName,type);
//		Element codeE=getElement(pKey);		
//		if(codeE==null) return;
//		List list=codeE.getChildren("ACTION");
//	    //清除原有的ACTION元素
//		if(list!=null){
//			for(int i=0;i<list.size();i++){
//				Element actionE=(Element)list.get(i);
//				String value=actionE.getAttributeValue("action");
//				if("add".equals(value)){
//					codeE.removeContent(actionE);
//				}
//			}
//		}		
//		//创建新增事件		
//		String href="javascript:doWith_f(fun.getSession(FORM),'add','你确定要新增吗?')";
//		Element actionE=new Element("ACTION");
//		actionE.setAttribute("type", "button");
//		actionE.setAttribute("refreshOpener", "false");
//		actionE.setAttribute("closeWindow", "false");
//		actionE.setAttribute("class", "button");
//		actionE.setAttribute("value","新增");
//		actionE.setAttribute("action","add");
//		actionE.setAttribute("href", href);
//		actionE.setAttribute("condition","fun.get(SHOW_TYPE)==add");
//		codeE.addContent(actionE);
//		this.doWithAddElement(actionE);
//		
//		Element beanE=new Element("BEAN");
//		beanE.setAttribute("SQLCOMM", "");
//		beanE.setAttribute("sqlMapId", "");
//		beanE.setAttribute("entity", "");
//		actionE.addContent(beanE);
//		this.doWithAddElement(beanE);
//		
//		xmlUtil.outputDocumentFile(path);
//	}
//	/**
//	 * 创建返回事件
//	 * @param code
//	 * @param key
//	 */
//	public void createBackEvent(String pKey){
//		String path=getFile(fileName,type);
//		Element codeE=getElement(pKey);	
//		if(codeE==null) return;
//		List list=codeE.getChildren("ACTION");
//		if(list!=null){
//			for(int i=0;i<list.size();i++){
//				Element actionElement=(Element)list.get(i);
//				String value=actionElement.getAttributeValue("action");
//				if("back".equals(value)){
//					codeE.removeContent(actionElement);
//				}
//			}
//		}		
//		
//		//创建返回事件		
//		String href="javascript:doWith_f(fun.getSession(FORM),'back','你确定要返回吗?')";
//		Element actionE=new Element("ACTION");
//		actionE.setAttribute("type", "button");
//		actionE.setAttribute("refreshOpener", "false");
//		actionE.setAttribute("closeWindow", "false");
//		actionE.setAttribute("class", "button");
//		actionE.setAttribute("value","返回");
//		actionE.setAttribute("action","back");
//		actionE.setAttribute("href", href);		
//		codeE.addContent(actionE);
//		this.doWithAddElement(actionE);
//		xmlUtil.outputDocumentFile(path);
//	}
//	/**
//	 * 创建关闭事件
//	 * @param code
//	 * @param key
//	 */
//	public void createCloseEvent(String pKey){
//		String path=getFile(fileName,type);
//		Element codeE=getElement(pKey);	
//		if(codeE==null) return;
//		List list=codeE.getChildren("ACTION");
//		//清除原有关闭事件
//		if(list!=null){
//			for(int i=0;i<list.size();i++){
//				Element actionE=(Element)list.get(i);
//				String value=actionE.getAttributeValue("action");
//				if("close".equals(value)){
//					codeE.removeContent(actionE);
//				}
//			}
//		}		
//		//创建关闭事件		
//     	
//		String href="javascript:window.close();";
//		Element actionE=new Element("ACTION");
//		actionE.setAttribute("type", "button");
//		actionE.setAttribute("refreshOpener", "false");
//		actionE.setAttribute("closeWindow", "false");
//		actionE.setAttribute("class", "button");
//		actionE.setAttribute("value","关闭");
//		actionE.setAttribute("action","close");
//		actionE.setAttribute("href", href);		
//		codeE.addContent(actionE);
//		this.doWithAddElement(actionE);
//		xmlUtil.outputDocumentFile(path);
//	}
//	/**
//	 * 创建更新事件
//	 * @param code
//	 * @param key
//	 */
//	public void createModifyEvent(String pKey){
//		String path=getFile(fileName,type);
//		Element codeE=getElement(pKey);	
//		if(codeE==null) return; 
//		List list=codeE.getChildren("ACTION");
//		//清除原有更新元素
//		if(list!=null){
//			for(int i=0;i<list.size();i++){
//				Element actionE=(Element)list.get(i);
//				String value=actionE.getAttributeValue("action");
//				if("update".equals(value)){
//					codeE.removeContent(actionE);
//				}
//			}
//		}		
//		//创建更新事件     	
//		String href="javascript:doWith_f(fun.getSession(FORM),'update','你确定要更新吗?')";
//		Element actionE=new Element("ACTION");
//		actionE.setAttribute("type", "button");
//		actionE.setAttribute("refreshOpener", "false");
//		actionE.setAttribute("closeWindow", "false");
//		actionE.setAttribute("class", "button");
//		actionE.setAttribute("value","更新");
//		actionE.setAttribute("action","update");
//		actionE.setAttribute("href", href);
//		actionE.setAttribute("condition","fun.get(SHOW_TYPE)==modify");
//		codeE.addContent(actionE);
//		this.doWithAddElement(actionE);
//		
//		Element beanE=new Element("BEAN");
//		beanE.setAttribute("SQLCOMM", "");
//		beanE.setAttribute("sqlMapId", "");
//		beanE.setAttribute("entity", "");
//		actionE.addContent(beanE);
//		this.doWithAddElement(beanE);
//		
//		xmlUtil.outputDocumentFile(path);
//	}
//	/**
//	 * 创建删除事件
//	 * @param code
//	 * @param key
//	 */
//	public void createDeleteEvent(String pKey,String key){
//		String path=getFile(fileName,type);
//		Element codeE=getElement(pKey);
//		Element listE=getElement(key);
//		if(codeE==null||listE==null) return;
//		List list=codeE.getChildren("ACTION");
//		//清除原有删除事件
//		if(list!=null){
//			for(int i=0;i<list.size();i++){
//				Element actionE=(Element)list.get(i);
//				String value=actionE.getAttributeValue("action");
//				if("delete".equals(value)){
//					codeE.removeContent(actionE);
//				}
//			}
//		}				
//		Element fieldsE=listE.getChild("FIELDS");
//		ColumnParser columnParser=new ColumnParser();
//		columnParser.parse(fieldsE);
//		Map columns=columnParser.getColumnMap();
//		Column column=((Column)columns.get("0"));		
//		String id="";		
//		if(column==null) 
//			id="id";
//		else
//			id=column.getId();		
//		String href="javascript:doWith_f(fun.getSession(FORM),'delete','你确定要删除吗?','','"+id+"')";
//		Element actionE=new Element("ACTION");
//		actionE.setAttribute("type", "url");
//		actionE.setAttribute("refreshOpener", "false");
//		actionE.setAttribute("closeWindow", "false");
//		actionE.setAttribute("class", "");
//		actionE.setAttribute("value","删除");
//		actionE.setAttribute("action","delete");
//		actionE.setAttribute("href", href);
//		codeE.addContent(actionE);
//		this.doWithAddElement(actionE);
//		Element beanE=new Element("BEAN");
//		beanE.setAttribute("SQLCOMM", "");
//		beanE.setAttribute("sqlMapId", "");
//		beanE.setAttribute("entity", "");
//		actionE.addContent(beanE);
//		this.doWithAddElement(beanE);
//		
//		xmlUtil.outputDocumentFile(path);
//	}
//	/**
//	 * 创建编辑事件
//	 * @param code
//	 * @param key
//	 */
//	public void createEditEvent(String pKey,String key){
//		String path=getFile(fileName,type);
//		Element codeE=getElement(pKey);
//		Element listE=getElement(key);
//		if(codeE==null||listE==null)return;
//			
//		Element fieldsE=listE.getChild("FIELDS");
//		ColumnParser columnParser=new ColumnParser();
//		columnParser.parse(fieldsE);
//		Map columns=columnParser.getColumnMap();
//		Column column=((Column)columns.get("0"));
//		if(column==null) return;	
//		//创建编辑列配置
//		this.addActionColumn(fieldsE, "update", column.getId());
//		
//	}
//	/**
//	 * 创建删除事件
//	 * @param code
//	 * @param key
//	 */
//	public void createDeleteOneEvent(String pKey,String key){
//		String path=getFile(fileName,type);
//		Element codeE=getElement(pKey);
//		Element listE=getElement(key);
//		if(codeE==null||listE==null) return;
//		List list=codeE.getChildren("ACTION");
//		if(list!=null){
//			for(int i=0;i<list.size();i++){
//				Element actionElement=(Element)list.get(i);
//				String value=actionElement.getAttributeValue("action");
//				if("deleteOne".equals(value)){
//					codeE.removeContent(actionElement);
//				}
//			}
//		}		
//		
//		Element fieldsE=listE.getChild("FIELDS");
//		ColumnParser columnParser=new ColumnParser();
//		columnParser.parse(fieldsE);
//		Map columns=columnParser.getColumnMap();
//		Column column=((Column)columns.get("0"));		
//		//创建删除列配置
//		if(column==null) 
//			this.addActionColumn(fieldsE, "deleteOne", "id");
//		else
//			this.addActionColumn(fieldsE, "deleteOne", column.getId());
//		//创建删除事件配置			
//		Element action=new Element("ACTION");
//		action.setAttribute("type", "");
//		action.setAttribute("refreshOpener", "false");
//		action.setAttribute("closeWindow", "false");
//		action.setAttribute("class", "");
//		action.setAttribute("value","删除");
//		action.setAttribute("action","deleteOne");
//		action.setAttribute("href", "");
//		codeE.addContent(action);
//		this.doWithAddElement(action);
//		Element beanE=new Element("BEAN");
//		beanE.setAttribute("sqlMapId", " ");
//		beanE.setAttribute("entity", "");
//		action.addContent(beanE);
//		this.doWithAddElement(beanE);		
//		xmlUtil.outputDocumentFile(path);
//	}
//	/**
//	 * 初始化list显示元素
//	 * @param key
//	 * @param selectedFields
//	 */
//	public void initListDisplay(String key,String[] selectedFields){	
//		String path=getFile(fileName,type);
//		Element fieldsElement=getElement(key);
//		Element listElement=null;	
//		List list=null;		
//		Element fieldsColumnElement=null;		
//		Element DISPLAYElement=new Element("DISPLAY");
//		DISPLAYElement.setAttribute("id", "L0");
//		DISPLAYElement.setAttribute("class", "tlistbody");
//		DISPLAYElement.setAttribute("width", "100%");
//		DISPLAYElement.setAttribute("border", "0");
//		DISPLAYElement.setAttribute("align", "center");
//		DISPLAYElement.setAttribute("cellPadding", "0");
//		DISPLAYElement.setAttribute("cellSpacing", "1"); 
//		if(fieldsElement!=null){
//			listElement=fieldsElement.getParentElement();
//			listElement.removeChild("DISPLAY");
//		}		
//		if(listElement!=null){
//			listElement.addContent(DISPLAYElement);
//			this.doWithAddElement(DISPLAYElement);
//		}
//		Element THElement=new Element("TH");		
//		THElement.setAttribute("class", "tablehead");		
//		Element TRElement=new Element("TR");
//		TRElement.setAttribute("SLCSS", "rowOdd");
//		TRElement.setAttribute("DLCSS", "rowEven");	
//		DISPLAYElement.addContent(THElement);
//		DISPLAYElement.addContent(TRElement);
//		this.doWithAddElement(THElement);
//		this.doWithAddElement(TRElement);
//		list=fieldsElement.getChildren();		
//		if(list!=null&&list.size()>0){
//			for(int k=0;k<selectedFields.length;k++){				
//				for(int i=0;i<list.size();i++){
//					fieldsColumnElement=(Element)list.get(i);				
//					if(fieldsColumnElement.getAttributeValue("col").equals(selectedFields[k])){	
//						Element THColumnElement=new Element("COLUMN");
//						Element TRColumnElement=new Element("COLUMN");
//						Element colSubElement=null;
//						colSubElement=fieldsColumnElement.getChild("DISPLAYNAME");	
//						if(colSubElement!=null){						
//							THColumnElement.setText(colSubElement.getText());
//						}else
//						    THColumnElement.setText(fieldsColumnElement.getText());
//						TRColumnElement.setAttribute("biddingCol", fieldsColumnElement.getAttributeValue("col"));
//						TRColumnElement.setText(fieldsColumnElement.getText());
//          			    THElement.addContent(THColumnElement);	  
//          			    this.doWithAddElement(THColumnElement);
//          			    TRElement.addContent(TRColumnElement);    
//          			    this.doWithAddElement(TRColumnElement);
//					}					
//				}				
//			}			
//		}
//		xmlUtil.outputDocumentFile(path);
//		
//	}
//	public void initMasterDisplay(String key,String[] selectedFields,boolean isDouble){
//		Element fieldsElement=getElement(key);
//		int index=0;
//		Element listElement=null;			
//		String ETM="";
//		List list=null;	
//		String path=getFile(fileName,type);
//		Element fieldsColumnElement=null;		
//		Element DISPLAYElement=new Element("DISPLAY");
//		DISPLAYElement.setAttribute("class", "tlistbody");
//		DISPLAYElement.setAttribute("width", "100%");
//		DISPLAYElement.setAttribute("border", "0");
//		DISPLAYElement.setAttribute("align", "center");
//		DISPLAYElement.setAttribute("cellPadding", "0");
//		DISPLAYElement.setAttribute("cellSpacing", "1"); 	
//		if(fieldsElement!=null){
//			listElement=fieldsElement.getParentElement();	
//			listElement.removeChild("DISPLAY");
//		}			
//		if(listElement!=null){
//			listElement.addContent(DISPLAYElement);
//			this.doWithAddElement(DISPLAYElement);
//		}		
//		list=fieldsElement.getChildren();			
//		if(list!=null&&list.size()>0){
//			for(int k=0;k<selectedFields.length;k++){					
//				for(int i=0;i<list.size();i++){					
//					fieldsColumnElement=(Element)list.get(i);					
//					String col=fieldsColumnElement.getAttributeValue("col");
//					Element TRElement=new Element("TR");	
//					if(selectedFields[k].equals(col)){							
//						Element TTColumnElement=new Element("COLUMN");
//						Element TCColumnElement=new Element("COLUMN");		
//						Element colSubElement=null;
//						colSubElement=fieldsColumnElement.getChild("DISPLAYNAME");	
//						TTColumnElement.setAttribute("class", "tabletitle");
//						if(colSubElement!=null){							
//							TTColumnElement.setText(colSubElement.getText());
//						}							
//						TCColumnElement.setAttribute("biddingCol",fieldsColumnElement.getAttributeValue("col"));						
//						TCColumnElement.setAttribute("class", "tabletxt");	
//						if(isDouble){									
//							if(index%2==0){										
//								DISPLAYElement.addContent(TRElement);								
//								this.doWithAddElement(TRElement);								
//								ETM=TRElement.getAttributeValue("ETM");										
//								TRElement.addContent(TTColumnElement);						
//								this.doWithAddElement(TTColumnElement);									
//								TRElement.addContent(TCColumnElement);		
//								this.doWithAddElement(TCColumnElement);	
//								
//							}else{											
//								TRElement=this.getElement(ETM);									
//								TRElement.addContent(TTColumnElement);										
//								this.doWithAddElement(TTColumnElement);									
//								TRElement.addContent(TCColumnElement);		
//								this.doWithAddElement(TCColumnElement);								
//							}
//						}else{
//							DISPLAYElement.addContent(TRElement);		
//							this.doWithAddElement(TRElement);	
//							TRElement.addContent(TTColumnElement);						
//							this.doWithAddElement(TTColumnElement);									
//							TRElement.addContent(TCColumnElement);		
//							this.doWithAddElement(TCColumnElement);	
//						}
//						index++;
//					}					
//				}				
//			}			
//		}
//		xmlUtil.outputDocumentFile(path);
//	}
//	/**
//	 * 初始化list查询元素
//	 * @param key
//	 * @param selectedFields
//	 * @param isDouble
//	 */
//	public void initListSearch(String key,String[] selectedFields,boolean isDouble ){
//		Element fieldsElement=getElement(key);
//		Element listElement=null;
//		Element statementElement=null;	
//		Element fieldsColumnElement=null;		
//		List list=null;		
//		List list2=null;
//		String ETM="";
//		int index=0;
//		String path=getFile(fileName,type);
//		if(fieldsElement!=null){
//			listElement=fieldsElement.getParentElement();					
//		}		
//		if(listElement!=null){
//			listElement.removeChild("SEARCH");			
//		}
//		Element searchElement=new Element("SEARCH");
//		listElement.addContent(searchElement);
//		this.doWithAddElement(searchElement);
//		Element titleElement=new Element("TITLE");
//		searchElement.addContent(titleElement);
//		this.doWithAddElement(titleElement);
//		Element displayElement=new Element("DISPLAY");
//		displayElement.setAttribute("class", "tlistbody");
//		displayElement.setAttribute("width", "100%");
//		displayElement.setAttribute("border", "0");
//		displayElement.setAttribute("align", "center");
//		displayElement.setAttribute("cellPadding", "0");
//		displayElement.setAttribute("cellSpacing", "1"); 	
//		searchElement.addContent(displayElement);
//		this.doWithAddElement(displayElement);
//		list=fieldsElement.getChildren();		
//		if(list!=null&&list.size()>0){
//			for(int k=0;k<selectedFields.length;k++){					
//				for(int i=0;i<list.size();i++){					
//					fieldsColumnElement=(Element)list.get(i);							
//					String col=fieldsColumnElement.getAttributeValue("col");
//					Element TRElement=new Element("TR");					
//					if(selectedFields[k].equals(col)){	
//
//						Element TTColumnElement=new Element("COLUMN");
//						Element TCColumnElement=new Element("COLUMN");		
//						Element colSubElement=null;
//						colSubElement=fieldsColumnElement.getChild("DISPLAYNAME");	
//						TTColumnElement.setAttribute("class", "tabletitle");
//						if(colSubElement!=null){							
//							TTColumnElement.setText(colSubElement.getText());
//						}							
//						TCColumnElement.setAttribute("biddingCol",fieldsColumnElement.getAttributeValue("col"));						
//						TCColumnElement.setAttribute("class", "tabletxt");							
//						if(isDouble){								
//							if(index%2==0){								
//								displayElement.addContent(TRElement);								
//								this.doWithAddElement(TRElement);		
//								ETM=TRElement.getAttributeValue("ETM");										
//								TRElement.addContent(TTColumnElement);						
//								this.doWithAddElement(TTColumnElement);									
//								TRElement.addContent(TCColumnElement);		
//								this.doWithAddElement(TCColumnElement);									
//							}else{								
//								TRElement=this.getElement(ETM);								
//								TRElement.addContent(TTColumnElement);						
//								this.doWithAddElement(TTColumnElement);									
//								TRElement.addContent(TCColumnElement);		
//								this.doWithAddElement(TCColumnElement);			
//							
//							}
//							List list1=fieldsColumnElement.getChildren();							
//							if(list1!=null){
//								for(int j=0;j<list1.size();j++){
//									String name=((Element)list1.get(j)).getName();
//									String text=((Element)list1.get(j)).getTextTrim();									
//									if("H".equals(text)){
//										text="IT";
//									}
//									if("VALUE".equals(name)){
//										text="";										
//									}
//									if("NAME".equals(name)){
//										text=text+"_s";
//									}
//									Element tmpElement=new Element(name);
//									tmpElement.setText(text);
//									TCColumnElement.addContent(tmpElement);
//									this.doWithAddElement(tmpElement);
//								}							
//								
//							}								
//						}
//						index++;
//					}					
//				}				
//			}			
//		}
//		xmlUtil.outputDocumentFile(path);
//	}
//	public void setFileName(String fileName){
//		this.fileName=fileName;
//	}
//	public void setType(String type){
//		this.type=type;
//	}
//	public String getFile(String fileName,String type){
//		String path="";
//		if(fileName.indexOf(".xml")!=-1){
//			path=SysConstants.PUBLIC_PATH+File.separator+"SYSTEM"+File.separator+type+File.separator+this.fileName;
//		}else{
//			path=SysConstants.PUBLIC_PATH+File.separator+"SYSTEM"+File.separator+type+File.separator+"menu_"+this.fileName+".xml";
//		}
//		return path;
//	}
//
//}
