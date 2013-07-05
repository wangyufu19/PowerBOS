package com.application.support.model;
import com.framework.common.base.BaseVO;
public class UserRole extends BaseVO{
	private Long userId;
	private Long roleId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
