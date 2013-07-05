package com.application.support.model;
import com.framework.common.base.BaseVO;
/**
 * 自定义排序实体类
 * @author wangyf
 * @version 1.0
 */
public class DiySort extends BaseVO{
	private Long userId;
	private String diyCode;
	private String sortColumn;
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
	public String getSortColumn() {
		return sortColumn;
	}
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
	
}
