package com.application.console.action;
import com.application.console.biz.SearchObjectBiz;
import com.controller.action.SupportAction;
/**
 * 查询对象动作类
 * @author wangyf
 * @version 1.0
 */
public class SearchObjectAction extends SupportAction{
	
	public String getSearchObjectBase(){
		String resourceName=this.getActionContext().get("resourceName");
		String pageKey=this.getActionContext().get("pageKey");
		String pageCode=this.getActionContext().get("pageCode");
		String searchKey=this.getActionContext().get("searchKey");	
		SearchObjectBiz biz=new SearchObjectBiz();
		String[] arr=biz.getSearchObjectBase(resourceName, searchKey);
		this.getActionContext().set("resourceName", resourceName);
		this.getActionContext().set("pageKey", pageKey);
		this.getActionContext().set("pageCode", pageCode);
		this.getActionContext().set("searchKey", searchKey);	
		this.getActionContext().set("searchTitle", arr[0]);		
		this.getActionContext().set("loadColumnSetStyle", arr[1]);	
		return this.SUCCESS;
	}
	public String saveSearchObjectBase(){
		String resourceName=this.getActionContext().get("resourceName");
		String searchKey=this.getActionContext().get("searchKey");	
		String searchTitle=this.getActionContext().get("searchTitle");
		String loadColumnSetStyle=this.getActionContext().get("loadColumnSetStyle");
		SearchObjectBiz biz=new SearchObjectBiz();
		biz.saveSearchObjectBase(resourceName, searchKey, searchTitle, loadColumnSetStyle);
		return this.getSearchObjectBase();
	}
}
