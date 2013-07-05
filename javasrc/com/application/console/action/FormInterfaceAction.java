package com.application.console.action;
import com.application.console.biz.InterfaceBiz;
import com.controller.action.SupportAction;
/**
 * 表单外部接口动作类
 * @author wangyf
 * @version 1.0
 */
public class FormInterfaceAction extends SupportAction{
	
	public String getFormInterfaceBase(){
		String resourceName=this.getActionContext().get("resourceName");
		String pageKey=this.getActionContext().get("pageKey");
		String pageCode=this.getActionContext().get("pageCode");
		String interfaceKey=this.getActionContext().get("interfaceKey");
		InterfaceBiz biz=new InterfaceBiz();
		String[] arr=biz.getGridInterfaceBase(resourceName, interfaceKey);
		this.getActionContext().set("resourceName", resourceName);
		this.getActionContext().set("pageKey", pageKey);
		this.getActionContext().set("pageCode", pageCode);
		this.getActionContext().set("interfaceKey", interfaceKey);
		this.getActionContext().set("className", arr[0]);
		this.getActionContext().set("methodName", arr[1]);
		return this.SUCCESS;
	}
	public String saveFormInterfaceBase(){
		String resourceName=this.getActionContext().get("resourceName");
		String interfaceKey=this.getActionContext().get("interfaceKey");
		String className=this.getActionContext().get("className");
		String methodName=this.getActionContext().get("methodName");
		InterfaceBiz biz=new InterfaceBiz();
		biz.saveGridInterfaceBase(resourceName, interfaceKey, className, methodName);
		return this.getFormInterfaceBase();
	}
}
