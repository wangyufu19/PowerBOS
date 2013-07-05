package com.application.console.action;
import com.application.console.biz.ScriptObjectBiz;
import com.controller.action.SupportAction;
/**
 * 脚本对象动作类
 * @author wangyf
 * @version 1.0
 */
public class ScriptObjectAction extends SupportAction{

	public String getScriptObjectContent(){
		String resourceName=this.getActionContext().get("resourceName");
		String pageKey=this.getActionContext().get("pageKey");
		String pageCode=this.getActionContext().get("pageCode");
		String scriptKey=this.getActionContext().get("scriptKey");
		ScriptObjectBiz biz=new ScriptObjectBiz();
		String scriptText=biz.getScriptObjectContent(resourceName, scriptKey);
		this.getActionContext().set("resourceName", resourceName);
		this.getActionContext().set("pageKey", pageKey);
		this.getActionContext().set("pageCode", pageCode);
		this.getActionContext().set("scriptKey", scriptKey);
		this.getActionContext().set("scriptText", scriptText);		
		return this.SUCCESS;
	}
	public String saveScriptObjectContent(){	
		String resourceName=this.getActionContext().get("resourceName");
		String scriptKey=this.getActionContext().get("scriptKey");
		String scriptText=this.getActionContext().get("scriptText");
		ScriptObjectBiz biz=new ScriptObjectBiz();
		biz.saveScriptObjectContent(resourceName, scriptKey, scriptText);
		return this.getScriptObjectContent();
	}
}
