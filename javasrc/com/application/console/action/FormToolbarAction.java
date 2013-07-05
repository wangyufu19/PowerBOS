package com.application.console.action;
import java.util.List;

import com.application.console.biz.ToolbarBiz;
import com.application.console.model.Toolbar;
import com.controller.action.SupportAction;
/**
 * 表单工具条动作类
 * @author wangyf
 * @version 1.0
 */
public class FormToolbarAction extends SupportAction{
	private List toolbars;
	private Toolbar toolbar;
	private List setbeans;
	
	public List getToolbars() {
		return toolbars;
	}
	public void setToolbars(List toolbars) {
		this.toolbars = toolbars;
	}
	public Toolbar getToolbar() {
		return toolbar;
	}
	public void setToolbar(Toolbar toolbar) {
		this.toolbar = toolbar;
	}
	public List getSetbeans() {
		return setbeans;
	}
	public void setSetbeans(List setbeans) {
		this.setbeans = setbeans;
	}
	public String getFormToolbarIframe(){
		String resourceName=this.getActionContext().get("resourceName");
		String pageKey=this.getActionContext().get("pageKey");
		String pageCode=this.getActionContext().get("pageCode");
		String toolbarsKey=this.getActionContext().get("toolbarsKey");
		ToolbarBiz biz=new ToolbarBiz();
		this.setToolbars(biz.getToolbarList(resourceName, toolbarsKey));
		this.getActionContext().set("resourceName", resourceName);
		this.getActionContext().set("pageKey", pageKey);
		this.getActionContext().set("pageCode", pageCode);
		this.getActionContext().set("toolbarsKey", toolbarsKey);
		return this.SUCCESS;
	}
	public String getFormToolbarList(){
		String resourceName=this.getActionContext().get("resourceName");	
		String pageKey=this.getActionContext().get("pageKey");
		String pageCode=this.getActionContext().get("pageCode");
		String toolbarsKey=this.getActionContext().get("toolbarsKey");
		ToolbarBiz biz=new ToolbarBiz();
		this.setToolbars(biz.getToolbarList(resourceName, toolbarsKey));
		this.getActionContext().set("resourceName", resourceName);	
		this.getActionContext().set("pageKey", pageKey);
		this.getActionContext().set("pageCode", pageCode);
		this.getActionContext().set("toolbarsKey", toolbarsKey);
		return this.SUCCESS;
	}
	public String saveFormToolbarList(){
		String resourceName=this.getActionContext().get("resourceName");
		String toolbarsKey=this.getActionContext().get("toolbarsKey");
		String[] toolbarKeyArr=this.getActionContext().getArray("key");
		String[] codeArr=this.getActionContext().getArray("code");
		String[] nameArr=this.getActionContext().getArray("name");
		String[] freshOpenerArr=this.getActionContext().getArray("freshOpener");
		String[] closeWindowArr=this.getActionContext().getArray("closeWindow");		
		ToolbarBiz biz=new ToolbarBiz();
		biz.saveToolbarList(resourceName, toolbarsKey, toolbarKeyArr, codeArr, nameArr, freshOpenerArr, closeWindowArr);
		return this.getFormToolbarList();
	}
	public String getFormToolbarBase(){
		String resourceName=this.getActionContext().get("resourceName");		
		String toolbarKey=this.getActionContext().get("toolbarKey");
		ToolbarBiz biz=new ToolbarBiz();
		this.setToolbar(biz.getToolbarBase(resourceName, toolbarKey));
		this.getActionContext().set("resourceName", resourceName);	
		this.getActionContext().set("toolbarKey", toolbarKey);		
		return this.SUCCESS;
	}
	public String saveFormToolbarBase(){
		String resourceName=this.getActionContext().get("resourceName");	
		String toolbarKey=this.getActionContext().get("toolbarKey");
		ToolbarBiz biz=new ToolbarBiz();
		biz.saveToolbarBase(resourceName, toolbarKey, toolbar);
		return this.getFormToolbarBase();
	}
	public String getFormToolbarStatement(){
		String resourceName=this.getActionContext().get("resourceName");
		String toolbarKey=this.getActionContext().get("toolbarKey");		
		ToolbarBiz biz=new ToolbarBiz();
		String[] arr=biz.getToolbarStatement(resourceName, toolbarKey);			
		this.getActionContext().set("statementKey", arr[0]);
		this.getActionContext().set("parameterClass", arr[1]);		
		this.getActionContext().set("resultMap", arr[2]);		
		this.getActionContext().set("statementText", arr[3]);		
		this.getActionContext().set("resourceName", resourceName);
		this.getActionContext().set("toolbarKey", toolbarKey);		
		return this.SUCCESS;
	}
	public String saveFormToolbarStatement(){
		String resourceName=this.getActionContext().get("resourceName");
		String statementKey=this.getActionContext().get("statementKey");
		String parameterClass=this.getActionContext().get("parameterClass");
		String resultMap=this.getActionContext().get("resultMap");
		String statementText=this.getActionContext().get("statementText");		
		ToolbarBiz biz=new ToolbarBiz();
		biz.saveToolbarStatement(resourceName, statementKey, statementText,parameterClass,resultMap);
		return this.getFormToolbarStatement();
	}
	public String getFormToolbarInput(){
		String resourceName=this.getActionContext().get("resourceName");
		String toolbarKey=this.getActionContext().get("toolbarKey");		
		ToolbarBiz biz=new ToolbarBiz();
		this.setSetbeans(biz.getToolbarInput(resourceName, toolbarKey));
		this.getActionContext().set("resourceName", resourceName);
		this.getActionContext().set("toolbarKey", toolbarKey);	
		return this.SUCCESS;
	}
	public String saveFormToolbarInput(){
		String resourceName=this.getActionContext().get("resourceName");
		String toolbarKey=this.getActionContext().get("toolbarKey");
		String[] nameArr=this.getActionContext().getArray("name");
		String[] valueArr=this.getActionContext().getArray("value");
		ToolbarBiz biz=new ToolbarBiz();
		biz.saveToolbarInput(resourceName, toolbarKey, nameArr, valueArr);
		return this.getFormToolbarInput();
	}
	public String getFormToolbarInterface(){
		String resourceName=this.getActionContext().get("resourceName");
		String toolbarKey=this.getActionContext().get("toolbarKey");	
		ToolbarBiz biz=new ToolbarBiz();
		String[] arr=biz.getToolbarInterface(resourceName, toolbarKey);
		this.getActionContext().set("className", arr[0]);
		this.getActionContext().set("methodName", arr[1]);
		this.getActionContext().set("resourceName", resourceName);
		this.getActionContext().set("toolbarKey", toolbarKey);			
		return this.SUCCESS;
	}
	public String saveFormToolbarInterface(){
		String resourceName=this.getActionContext().get("resourceName");
		String toolbarKey=this.getActionContext().get("toolbarKey");	
		String className=this.getActionContext().get("className");
		String methodName=this.getActionContext().get("methodName");
		ToolbarBiz biz=new ToolbarBiz();
		biz.saveToolbarInterface(resourceName, toolbarKey, className, methodName);		
		return this.getFormToolbarInterface();
	}
}
