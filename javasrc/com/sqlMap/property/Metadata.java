package com.sqlMap.property;
import java.util.Collections;
import java.util.Map;
import java.util.Iterator;
import java.util.LinkedHashMap;
import com.sqlMap.id.Identifier;
/**
 * 源数据类
 * @author youfu.wang
 * @version 1.0
 */
public class Metadata{
	private String name;//resultMap名称
	private String table;//resultMap对应表名称
	private String useQueryCache="false";//使用查询缓存机制
	private Identifier identifier;	
	private Map properties=Collections.synchronizedMap(new LinkedHashMap());
	
	public Metadata(){
		
	}
	/**
	 * 返回主键标识符对象
	 * @return
	 */
	public Identifier getIdentifier(){
		if(this.identifier==null) 
			return new Identifier();
		return this.identifier;
	}	
	/**
	 * 返回源数据所有属性
	 * @return
	 */
	public Map getProperties(){
		return this.properties;
	}
	/**
	 * 返回指定列属性
	 * @param column
	 * @return
	 */
	public Property getProperty(String column){
		if(this.properties.containsKey(column)){
			return (Property)this.properties.get(column);
		}
		for(Iterator it=this.properties.keySet().iterator();it.hasNext();){
			Property property=(Property)this.properties.get(it.next().toString());
			if(column.equals(property.getColumn())||column.equals(property.getColumn().toLowerCase()))
				return property;
		}
		return new Property();
	}	
	/**
	 * 返回指定列属性数据类型
	 * @param column
	 * @return
	 */
	public String getPropertyType(String column){
		Property property=this.getProperty(column);		
		return property.getType();
	}
	/**
	 * 设置主键标识符对象
	 * @param identifier
	 */
	public void setIdetifier(Identifier identifier) {
		this.identifier=identifier;		
	}
	/**
	 * 加入一个属性对象到集合
	 * @param property
	 */
	public void putProperty(Property property){		
		this.properties.put(property.getName(), property);	
	}
	/**
	 * 返回resultMap名称
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置resultMap名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 返回resultMap对应表名称
	 * @return
	 */
	public String getTable() {
		return table;
	}
	/**
	 * 设置resultMap对应表名称
	 * @param table
	 */
	public void setTable(String table) {
		this.table = table;
	}
	/**
	 * 返回是否使用查询缓存机制
	 * @return
	 */
	public String getUseQueryCache() {
		return useQueryCache;
	}
	/**
	 * 是否使用查询缓存机制
	 * @param useQueryCache
	 */
	public void setUseQueryCache(String useQueryCache) {
		this.useQueryCache = useQueryCache;
	}
	
}
