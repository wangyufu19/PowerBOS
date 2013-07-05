package com.sqlMap.property;
/**
 * 源数据属性类
 * @author youfu.wang
 * @version 1.0
 */
public class Property {
	
	private String name;//属性名称
	private String type;//属性数据类型
	private String column;//属性对应列名称
	private String length;//属性对应列长度
	private String lengthScale;//属性对应列长度的标度
	
	public Property(){
		
	}
	/**
	 * 返回属性名称
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置属性名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 返回属性数据类型
	 * @return
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置属性数据类型
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 返回属性对应列名称
	 * @return
	 */
	public String getColumn() {
		return column;
	}
	/**
	 * 设置属性对应列名称
	 * @param column
	 */
	public void setColumn(String column) {
		this.column = column;
	}
	/**
	 * 返回属性对应列长度
	 * @return
	 */
	public String getLength() {
		return length;
	}
	/**
	 * 设置属性对应列长度
	 * @param length
	 */
	public void setLength(String length) {
		this.length = length;
	}
	/**
	 * 返回属性对应列长度的标度
	 * @return
	 */
	public String getLengthScale() {
		return lengthScale;
	}
	/**
	 * 设置属性对应列长度的标度
	 * @param lengthScale
	 */
	public void setLengthScale(String lengthScale) {
		this.lengthScale = lengthScale;
	}
}
