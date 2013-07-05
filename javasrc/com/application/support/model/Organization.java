package com.application.support.model;
/**
 * 组织机构实体类
 * @author wangyf
 * @version 1.0
 */
public class Organization{
	private String id;
	private String orgParentId;
	private String orgCode;
	private String orgCodeTree;
	private String orgName;
	private Long orgType;
	private String linkName;
	private String mobilePhone;
	private String telePhone;
	private String postcode;
	private String address;
	private String orgDesc;
	private String isleaf;
	
	public String getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public Long getOrgType() {
		return orgType;
	}
	public void setOrgType(Long orgType) {
		this.orgType = orgType;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getTelePhone() {
		return telePhone;
	}
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
	public String getOrgDesc() {
		return orgDesc;
	}
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgParentId() {
		return orgParentId;
	}
	public void setOrgParentId(String orgParentId) {
		this.orgParentId = orgParentId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrgCodeTree() {
		return orgCodeTree;
	}
	public void setOrgCodeTree(String orgCodeTree) {
		this.orgCodeTree = orgCodeTree;
	}
	
}
