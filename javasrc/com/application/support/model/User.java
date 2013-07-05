package com.application.support.model;
import java.util.Date;

/**
 * 系统用户实体类
 * @author wangyf
 * @version 1.0
 */
public class User{
	private String id;
	private String userName;//用户名称
	private String loginName;//登录名称
	private String loginPwd;//登录密码
	private java.lang.Integer userType;//用户类型
	private java.lang.Integer userStatus;//用户状态
	private String orgId;//隶属组织机构
	private java.lang.Integer isOnline;//是否在线
	private String companyId;//隶属单位
	private String siteId;//隶属站点
	private String mobilePhone;//手机号码
	private String telePhone;//电话号码
	private String email;//邮箱地址
	private String postcode;//邮政编码
	private String address;//联系地址
	private String remark;//用户备注
	private Date startDate;//起始日期
	private Date endDate;//截止日期	
	private java.lang.Integer isAdmin;//是否管理员
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public java.lang.Integer getUserType() {
		return userType;
	}
	public void setUserType(java.lang.Integer userType) {
		this.userType = userType;
	}
	public java.lang.Integer getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(java.lang.Integer userStatus) {
		this.userStatus = userStatus;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public java.lang.Integer getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(java.lang.Integer isOnline) {
		this.isOnline = isOnline;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public java.lang.Integer getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(java.lang.Integer isAdmin) {
		this.isAdmin = isAdmin;
	}	
}
