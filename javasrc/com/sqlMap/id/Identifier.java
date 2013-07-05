package com.sqlMap.id;
import com.sqlMap.property.Property;
import com.sqlMap.util.StringUtil;
/**
 * 主键标识符属性对象
 * @author wangyf
 * @version 1.0
 */
public class Identifier {
	private Property property;
	private String generator;
	/**
	 * 返回主键属性对象
	 * @return
	 */
	public Property getProperty() {
		return property;
	}
	/**
	 * 设置主键属性对象
	 * @param property
	 */
	public void setProperty(Property property) {
		this.property = property;
	}
	/**
	 * 是否主键列
	 * @param arg1
	 * @return
	 */
	public boolean isIdentifierKey(String arg1){	
		if(this.property==null)
			return false;		
		if(arg1.equals(StringUtil.replaceNull(this.property.getColumn())))
			return true;		
		if(arg1.equals(StringUtil.replaceNull(this.property.getName())))
			return true;
		return false;
	}
	/**
	 * 返回主键列值生成方式
	 * @return
	 */
	public String getGenerator() {
		return generator;
	}
	/**
	 * 设置主键列值生成方式
	 * @param generator
	 */
	public void setGenerator(String generator) {
		this.generator = generator;
	}
}
