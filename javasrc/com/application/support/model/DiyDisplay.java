package com.application.support.model;
import com.framework.common.base.BaseVO;
/**
 * 自定义显示实体类
 * @author wangyf
 * @version 1.0
 */
public class DiyDisplay extends BaseVO{
	private Long userId;
	private String diyCode;
	private String diyColumn;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getDiyCode() {
		return diyCode;
	}
	public void setDiyCode(String diyCode) {
		this.diyCode = diyCode;
	}
	public String getDiyColumn() {
		return diyColumn;
	}
	public void setDiyColumn(String diyColumn) {
		this.diyColumn = diyColumn;
	}
	
}
