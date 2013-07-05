package com.application.console.action;
import com.application.console.biz.FormObjectBiz;
import com.controller.action.SupportAction;
/**
 * 表单对象动作类
 * @author wangyf
 * @version 1.0
 */
public class FormObjectAction extends SupportAction{
	
	public String getFormObjectBase(){
		String resourceName=this.getActionContext().get("resourceName");
		String pageKey=this.getActionContext().get("pageKey");
		String pageCode=this.getActionContext().get("pageCode");
		String formKey=this.getActionContext().get("formKey");	
		FormObjectBiz biz=new FormObjectBiz();
		String[] arr=biz.getFormObjectBase(resourceName, formKey);
		this.getActionContext().set("resourceName", resourceName);
		this.getActionContext().set("pageKey", pageKey);
		this.getActionContext().set("pageCode", pageCode);
		this.getActionContext().set("formKey", formKey);	
		this.getActionContext().set("formTitle", arr[0]);	
		this.getActionContext().set("loadDataSetStyle", arr[1]);	
		this.getActionContext().set("loadColumnSetStyle", arr[2]);	
		return this.SUCCESS;
	}
	public String saveFormObjectBase(){
		String resourceName=this.getActionContext().get("resourceName");
		String formKey=this.getActionContext().get("formKey");	
		String formTitle=this.getActionContext().get("formTitle");
		String loadDataSetStyle=this.getActionContext().get("loadDataSetStyle");
		String loadColumnSetStyle=this.getActionContext().get("loadColumnSetStyle");
		FormObjectBiz biz=new FormObjectBiz();
		biz.saveFormObjectBase(resourceName, formKey, formTitle, loadDataSetStyle, loadColumnSetStyle);
		return this.getFormObjectBase();
	}
}
