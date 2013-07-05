package com.application.support.model;
/**
 * 用户登录实体类
 * @author wangyf
 * @version 1.0
 */
public class UserLogin{
	private String id;
	private String userId;
	private String ipAddress;
	private String sessionId;
	private java.sql.Timestamp lastLoginTime;
	private Long isOnline;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public Long getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(Long isOnline) {
		this.isOnline = isOnline;
	}
	public java.sql.Timestamp getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(java.sql.Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}	
}
