package com.application.support.model;
       
/**
 * 权限实体类
 * @author wangyf
 * @version 1.0
 */
public class Function{	
	private String id;
	private String functionParentId;
	private String functionCode;
	private String functionName;
	private Long functionType;
	private String functionUrl;
	private Long orderNo;
	private String isleaf;
	private String checked;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFunctionParentId() {
		return functionParentId;
	}
	public void setFunctionParentId(String functionParentId) {
		this.functionParentId = functionParentId;
	}
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public Long getFunctionType() {
		return functionType;
	}
	public void setFunctionType(Long functionType) {
		this.functionType = functionType;
	}
	public String getFunctionUrl() {
		return functionUrl;
	}
	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}	
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}
}
