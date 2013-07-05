package com.application.support.model;
/**
 * 数据字典实体类
 * @author wangyf
 * @version 1.0
 */
public class DataDict {
	private String id;
	private String dictParentId;
	private String dictCode;
	private String dictName;
	private Long orderNo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDictParentId() {
		return dictParentId;
	}
	public void setDictParentId(String dictParentId) {
		this.dictParentId = dictParentId;
	}
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}	
}
