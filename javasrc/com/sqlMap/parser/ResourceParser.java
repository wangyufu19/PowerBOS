package com.sqlMap.parser;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;
import java.util.Vector;
import org.jdom.Document;
import org.jdom.Element;
import com.sqlMap.id.Identifier;
import com.sqlMap.property.Metadata;
import com.sqlMap.property.Property;
import com.sqlMap.util.StringUtil;

/**
 * SqlMap资源解析器
 * @author youfu.wang
 * @version 1.0
 */
public class ResourceParser {
	private Map aliases=new HashMap();
	private Map metadatas=new HashMap();
	private Map statments=new HashMap();
	
	public ResourceParser(){
		
	}
	/**
	 * 加入资源表别名集合
	 * @param map
	 */
	public void pushAliases(Map map){
		for(Iterator it=aliases.keySet().iterator();it.hasNext();){
			String key=it.next().toString();
			String cls=String.valueOf(aliases.get(key));
			map.put(key, cls);
		}
	}
	/**
	 * 加入资源表源数据
	 * @param map
	 */
	public void pushMetadats(Map map){
		for(Iterator it=metadatas.keySet().iterator();it.hasNext();){
			String key=it.next().toString();
			Metadata metadata=(Metadata)metadatas.get(key);
			map.put(key, metadata);
		}			
	}
	/**
	 * 加入资源表SQL语句块
	 * @param map
	 */
	public void pushStatments(Map map){
		for(Iterator it=statments.keySet().iterator();it.hasNext();){
			String key=it.next().toString();
			Map statment=(Map)statments.get(key);
			map.put(key, statment);
		}
	}
	/**
	 * 解析sqlMap资源表数据
	 * @param doc
	 */
	public void parse(Document doc){
		Element rootE=doc.getRootElement();
		if(rootE==null) return;	
		Element aliasE=rootE.getChild("alias");
		this.accessAlias(aliasE);
		Element sqlMapE=rootE.getChild("sqlMap");
		if(sqlMapE==null) return;						
		List list=sqlMapE.getChildren("resultMap");
		if(list==null) list=new ArrayList();
		for(int i=0;i<list.size();i++){
			Element resultMap=(Element)list.get(i);
			this.accessResultMap(resultMap);			
		}		
		this.accessStatment(sqlMapE);				
	}	
    private void accessAlias(Element aliasE){
    	if(aliasE==null) return;
    	String name=StringUtil.replaceNull(aliasE.getAttributeValue("name"));
    	String cls=StringUtil.replaceNull(aliasE.getAttributeValue("class"));
    	this.aliases.put(name, cls);
    }
	private void accessResultMap(Element resultMapE){	
		Metadata metadata=new Metadata();
		if(resultMapE==null) return;
		String name=StringUtil.replaceNull(resultMapE.getAttributeValue("name"));	
		String table=StringUtil.replaceNull(resultMapE.getAttributeValue("table"));
		String useQueryCache=StringUtil.replaceNull(resultMapE.getAttributeValue("useQueryCache"));
		metadata.setName(name);		
		metadata.setTable(table);
		metadata.setUseQueryCache(useQueryCache);
		this.accessIdentifer(resultMapE,metadata);
		this.accessProperties(resultMapE, metadata);
		this.metadatas.put(name, metadata);
	}
	/**
	 * 解析标识符配置
	 * @param resultMapE
	 * @param metadata
	 */
	private void accessIdentifer(Element resultMapE,Metadata metadata){
		Element idE=resultMapE.getChild("id");
		if(idE==null) return;
		Identifier identifier=new Identifier();
		Property property=new Property();
		property.setName(StringUtil.replaceNull(idE.getAttributeValue("name")));
		property.setType(StringUtil.replaceNull(idE.getAttributeValue("type")));	
		Element colE=idE.getChild("column");
		if(colE!=null){
			property.setColumn(StringUtil.replaceNull(colE.getAttributeValue("name")));
			property.setLength(StringUtil.replaceNull(colE.getAttributeValue("length")));
			property.setLengthScale(StringUtil.replaceNull(colE.getAttributeValue("scale")));
			identifier.setGenerator(StringUtil.replaceNull(colE.getAttributeValue("key")));
		}	
		identifier.setProperty(property);
		metadata.setIdetifier(identifier);
	}
	/**
	 * 解析属性配置
	 * @param resultMapE
	 * @param metadata
	 */
	private void accessProperties(Element resultMapE,Metadata metadata){			
		List list=resultMapE.getChildren("property");		
		if(list==null) return;				
		for(int i=0;i<list.size();i++){
			Element proE=(Element)list.get(i);
			Property property=new Property();
			String name=StringUtil.replaceNull(proE.getAttributeValue("name"));
			String type=StringUtil.replaceNull(proE.getAttributeValue("type"));
			property.setName(name);
			property.setType(type);
			Element colE=proE.getChild("column");
			if(colE!=null){
				String column=StringUtil.replaceNull(colE.getAttributeValue("name"));
				String length=StringUtil.replaceNull(colE.getAttributeValue("length"));
				String scale=StringUtil.replaceNull(colE.getAttributeValue("scale"));	
				property.setColumn(column);
				property.setLength(length);
				property.setLengthScale(scale);
			}
			metadata.putProperty(property);			
		}		
	}

	/**
	 * 解析SQL语句配置
	 * @param sqlMapE
	 * @param table
	 */
	private void accessStatment(Element sqlMapE){		
		if(sqlMapE==null) return;
		List list=sqlMapE.getChildren();
		if(list==null) return;
		for(int i=0;i<list.size();i++){
			Element sqlE=(Element)list.get(i);
			if("select".equals(sqlE.getName().toLowerCase())){
				this.accessQueryStatement(sqlE,"select");
			}else if("insert".equals(sqlE.getName().toLowerCase())){
				this.accessExecuteStatement(sqlE,"insert");
			}else if("update".equals(sqlE.getName().toLowerCase())){
				this.accessExecuteStatement(sqlE,"update");
			}else if("delete".equals(sqlE.getName().toLowerCase())){
				this.accessExecuteStatement(sqlE,"delete");
			}
		}		
	}
	
	private void accessQueryStatement(Element queryE,String executeType){
		Map query=new HashMap();
		List isNotEmptys=new ArrayList();
		List isEuqals=new ArrayList();
		if(queryE==null) return;			
		String id=queryE.getAttributeValue("id");
		String usage=queryE.getAttributeValue("usage");
		String parameterClass=queryE.getAttributeValue("parameterClass");
		String returnClass=queryE.getAttributeValue("returnClass");
		String resultMap=queryE.getAttributeValue("resultMap");
		String sql=queryE.getTextTrim();
		isNotEmptys=queryE.getChildren("isNotEmpty");	
		isEuqals=queryE.getChildren("isEqual");		
		query.put("executeType", executeType);
		query.put("id", id);
		query.put("usage", usage);
		query.put("parameterClass", parameterClass);
		query.put("returnClass", returnClass);
		query.put("resultMap", resultMap);
		query.put("sql", sql);		
		query.put("isNotEmpty", this.getStatmentIsNotEmpty(isNotEmptys));
		query.put("isEqual", this.getStatementIsEqual(isEuqals));
		this.statments.put(id, query);		
	}
	private void accessExecuteStatement(Element executeE,String executeType){
		Map execute=new HashMap();
		if(executeE==null) return;				
		String id=executeE.getAttributeValue("id");
		String usage=executeE.getAttributeValue("usage");
		String parameterClass=executeE.getAttributeValue("parameterClass");
		String resultMap=executeE.getAttributeValue("resultMap");
		String sql=executeE.getTextTrim();		
		execute.put("executeType", executeType);
		execute.put("id", id);
		execute.put("usage", usage);
		execute.put("parameterClass", parameterClass);
		execute.put("resultMap", resultMap);
		execute.put("sql", sql);					
		this.statments.put(id, execute);
	}	
	private Vector getStatmentIsNotEmpty(List list){		
		Vector isNotEmptys=new Vector();
		isNotEmptys.clear();
		if(list==null) return isNotEmptys;
		for(int i=0;i<list.size();i++){
			Map isNotEmpty=new HashMap();
			Element isNotEmptyE=(Element)list.get(i);
			String operator=isNotEmptyE.getAttributeValue("operator");
			String property=isNotEmptyE.getAttributeValue("property");
			String expression=isNotEmptyE.getTextTrim();			
			isNotEmpty.put("operator", operator);
			isNotEmpty.put("property", property);
			isNotEmpty.put("expression", expression);
			isNotEmptys.add(isNotEmpty);			
		}
		return isNotEmptys;		
	}
	private Vector getStatementIsEqual(List list){
		Vector isEquals=new Vector();
		isEquals.clear();
		if(list==null) return isEquals;
		for(int i=0;i<list.size();i++){
			Map isEqual=new HashMap();
			Element isEqualE=(Element)list.get(i);				
			String operator=isEqualE.getAttributeValue("operator");
			String property=isEqualE.getAttributeValue("property");
			String compareValue=isEqualE.getAttributeValue("compareValue");
			String expression=isEqualE.getTextTrim();	
			isEqual.put("operator", operator);			
			isEqual.put("property", property);			
			isEqual.put("compareValue", compareValue);	
			isEqual.put("expression", expression);	
			isEquals.add(isEqual);			
		}
		return isEquals;
	}
}
