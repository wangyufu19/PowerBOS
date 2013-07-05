package com.application.support.model;
import com.framework.common.base.BaseVO;
public class RoleFunction extends BaseVO{
	private Long roleId;
	private Long functionId;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getFunctionId() {
		return functionId;
	}
	public void setFunctionId(Long functionId) {
		this.functionId = functionId;
	}
}
