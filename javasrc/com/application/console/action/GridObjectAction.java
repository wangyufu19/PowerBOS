package com.application.console.action;
import com.application.console.biz.GridObjectBiz;
import com.application.console.model.GridBase;
import com.controller.action.SupportAction;
/**
 * 网络对象动作类
 * @author wangyf
 * @version 1.0
 */
public class GridObjectAction extends SupportAction{
	private GridBase gridBase;
	
	public void setGridBase(GridBase gridBase) {
		this.gridBase = gridBase;
	}
	public GridBase getGridBase() {
		return gridBase;
	}
	public String getGridObjectBase(){
		String resourceName=this.getActionContext().get("resourceName");
		String pageKey=this.getActionContext().get("pageKey");
		String pageCode=this.getActionContext().get("pageCode");
		String gridKey=this.getActionContext().get("gridKey");	
		GridObjectBiz biz=new GridObjectBiz();
		this.setGridBase(biz.getGridObjectBase(resourceName, gridKey));
		this.getActionContext().set("resourceName", resourceName);
		this.getActionContext().set("pageKey", pageKey);
		this.getActionContext().set("pageCode", pageCode);
		this.getActionContext().set("gridKey", gridKey);		
		return this.SUCCESS;
	}
	public String saveGridObjectBase(){
		String resourceName=this.getActionContext().get("resourceName");
		String gridKey=this.getActionContext().get("gridKey");	
		GridObjectBiz biz=new GridObjectBiz();
		biz.saveGridObjectBase(resourceName,gridKey,gridBase);
		return this.getGridObjectBase();
	}
	

}
