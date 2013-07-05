package com.application.support.model;
import java.sql.Timestamp;
/**
 * 数据日志实体类
 * @author wangyf
 * @version 1.0
 */
public class DBLog{
	private String id;
	private String content;
	private String actionType;
	private Timestamp logTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public Timestamp getLogTime() {
		return logTime;
	}
	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
	}
}
