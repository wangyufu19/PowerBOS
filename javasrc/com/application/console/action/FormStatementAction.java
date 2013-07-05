package com.application.console.action;
import java.util.List;

import com.application.console.biz.StatementBiz;
import com.controller.action.SupportAction;
/**
 * 表单语句动作类
 * @author wangyf
 * @version 1.0
 */
public class FormStatementAction extends SupportAction{
	
	public String getFormStatementBase(){
		String resourceName=this.getActionContext().get("resourceName");
		String pageKey=this.getActionContext().get("pageKey");
		String pageCode=this.getActionContext().get("pageCode");
		String statementKey=this.getActionContext().get("statementKey");
		StatementBiz biz=new StatementBiz();
		String[] arr=biz.getGridStatmentBase(resourceName, statementKey);			
		this.getActionContext().set("resourceName", resourceName);
		this.getActionContext().set("pageKey", pageKey);
		this.getActionContext().set("pageCode", pageCode);
		this.getActionContext().set("statementKey", statementKey);
		this.getActionContext().set("parameterClass", arr[0]);
		this.getActionContext().set("returnClass", arr[1]);
		this.getActionContext().set("resultMap", arr[2]);
		return this.SUCCESS;
	}
	public String saveFormStatementBase(){
		String resourceName=this.getActionContext().get("resourceName");
		String statementKey=this.getActionContext().get("statementKey");
		String parameterClass=this.getActionContext().get("parameterClass");
		String returnClass=this.getActionContext().get("returnClass");
		String resultMap=this.getActionContext().get("resultMap");
		StatementBiz biz=new StatementBiz();		
		biz.saveGridStatementBase(resourceName, statementKey, parameterClass, returnClass, resultMap);
		return this.getFormStatementBase();
	}
	public String getFormStatementSelect(){
		String resourceName=this.getActionContext().get("resourceName");
		String pageKey=this.getActionContext().get("pageKey");
		String pageCode=this.getActionContext().get("pageCode");
		String statementKey=this.getActionContext().get("statementKey");
		StatementBiz biz=new StatementBiz();
		String[] arr=biz.getGridStatementSelect(resourceName, statementKey);
		this.getActionContext().set("resourceName", resourceName);
		this.getActionContext().set("pageKey", pageKey);
		this.getActionContext().set("pageCode", pageCode);
		this.getActionContext().set("statementKey", statementKey);
		this.getActionContext().set("selectKey", arr[0]);
		this.getActionContext().set("selectText", arr[1]);
		return this.SUCCESS;
	}
	public String saveFormStatementSelect(){
		String resourceName=this.getActionContext().get("resourceName");
		String selectKey=this.getActionContext().get("selectKey");
		String selectText=this.getActionContext().get("selectText");
		StatementBiz biz=new StatementBiz();		
		biz.saveGridStatementSelect(resourceName, selectKey, selectText);
		return this.getFormStatementSelect();
	}
	public String getFormStatementInput(){
		String resourceName=this.getActionContext().get("resourceName");
		String pageKey=this.getActionContext().get("pageKey");
		String pageCode=this.getActionContext().get("pageCode");
		String statementKey=this.getActionContext().get("statementKey");
		StatementBiz biz=new StatementBiz();		
		List setbeans=biz.getGridStatementInput(resourceName, statementKey);				
		this.getActionContext().set("resourceName", resourceName);
		this.getActionContext().set("pageKey", pageKey);
		this.getActionContext().set("pageCode", pageCode);
		this.getActionContext().set("statementKey", statementKey);		
		this.getActionContext().set("setbeans", setbeans);
		return this.SUCCESS;
	}
	public String saveFormStatementInput(){
		String[] nameArr=this.getActionContext().getArray("name");
		String[] valueArr=this.getActionContext().getArray("value");
		String resourceName=this.getActionContext().get("resourceName");
		String statementKey=this.getActionContext().get("statementKey");
		StatementBiz biz=new StatementBiz();		
		biz.saveGridStatementInput(resourceName, statementKey, nameArr, valueArr);
		return this.getFormStatementInput();
	}

}
