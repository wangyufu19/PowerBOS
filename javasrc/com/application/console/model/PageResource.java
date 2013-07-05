package com.application.console.model;

/**
 * 页面资源实体类
 * @author wangyf
 * @version 1.0
 */
public class PageResource{
	private String uid;
	private String resourceName;
	private String resourceSize;
	private String createTime;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}	
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceSize() {
		return resourceSize;
	}
	public void setResourceSize(String resourceSize) {
		this.resourceSize = resourceSize;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}	
}
