package com.application.console.action;
import java.util.List;

import com.application.console.biz.ColumnSetBiz;
import com.application.console.model.ColumnSet;
import com.controller.action.SupportAction;
/**
 * 表单字段集动作
 * @author wangyf
 * @version 1.0
 */
public class FormColumnSetAction extends SupportAction{
	
	private List columnSets;
	private ColumnSet columnSet;	
	
	public List getColumnSets() {
		return columnSets;
	}

	public void setColumnSets(List columnSets) {
		this.columnSets = columnSets;
	}	
	public ColumnSet getColumnSet() {
		return columnSet;
	}

	public void setColumnSet(ColumnSet columnSet) {
		this.columnSet = columnSet;
	}
	public String getFormColumnSetIframe(){
		String resourceName=this.getActionContext().get("resourceName");
		String pageKey=this.getActionContext().get("pageKey");
		String pageCode=this.getActionContext().get("pageCode");
		String columnSetKey=this.getActionContext().get("columnSetKey");
		this.getActionContext().set("resourceName", resourceName);
		this.getActionContext().set("pageKey", pageKey);
		this.getActionContext().set("pageCode", pageCode);
		this.getActionContext().set("columnSetKey", columnSetKey);
		return this.SUCCESS;
	}
	public String getFormColumnSetList(){
		String resourceName=this.getActionContext().get("resourceName");
		String pageKey=this.getActionContext().get("pageKey");
		String pageCode=this.getActionContext().get("pageCode");
		String columnSetKey=this.getActionContext().get("columnSetKey");
		ColumnSetBiz biz=new ColumnSetBiz();
		this.setColumnSets(biz.getColumnSetList(resourceName, columnSetKey));		
		this.getActionContext().set("resourceName", resourceName);
		this.getActionContext().set("pageKey", pageKey);
		this.getActionContext().set("pageCode", pageCode);
		this.getActionContext().set("columnSetKey", columnSetKey);
		return this.SUCCESS;
	}
	
	public String saveFormColumnSetList(){
		String resourceName=this.getActionContext().get("resourceName");
		String columnSetKey=this.getActionContext().get("columnSetKey");
		ColumnSetBiz biz=new ColumnSetBiz();
		String[] columnkeyArr=this.getActionContext().getArray("key");		
		String[] nameArr=this.getActionContext().getArray("name");
		String[] chineseNameArr=this.getActionContext().getArray("chineseName");
		String[] valueArr=this.getActionContext().getArray("value");
		String[] dataTypeArr=this.getActionContext().getArray("dataType");		
		biz.saveColumnSetList(resourceName, columnSetKey,columnkeyArr,nameArr,chineseNameArr,valueArr,dataTypeArr);
		return this.getFormColumnSetList();
	}
	public String getFormColumnSetAtt(){
		String resourceName=this.getActionContext().get("resourceName");
		String pageKey=this.getActionContext().get("pageKey");
		String pageCode=this.getActionContext().get("pageCode");
		String columnKey=this.getActionContext().get("columnKey");
		ColumnSetBiz biz=new ColumnSetBiz();
		this.setColumnSet(biz.getFormColumnSetAtt(resourceName, columnKey));
		this.getActionContext().set("resourceName", resourceName);
		this.getActionContext().set("pageKey", pageKey);
		this.getActionContext().set("pageCode", pageCode);
		this.getActionContext().set("columnKey", columnKey);	
		return this.SUCCESS;
	}
	public String saveFormColumnSetAtt(){
		String resourceName=this.getActionContext().get("resourceName");
		String columnKey=this.getActionContext().get("columnKey");
		ColumnSetBiz biz=new ColumnSetBiz();
		biz.saveFormColumnSetAtt(resourceName, columnKey, columnSet);
		return this.getFormColumnSetAtt();
	}	
}
