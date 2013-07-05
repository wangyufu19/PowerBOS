package com.controller.action;
import com.controller.action.ActionContext;
import com.controller.action.GlobalMessage;
/**
 * 控制器动作类，开发者的业务动作可以继承它,该业务动作从而被加入到控制器
 * @author youfu.wang
 * @version 1.0
 */
public class SupportAction {	
	public  final String INDEX="index";
	public  final String login="login";
	public  final String SUCCESS="success";
	public  final String ERROR="error";

	private ActionContext actionContext;

	/**
	 * 返回控制器动作上下文对象
	 * @return
	 */
	public ActionContext getActionContext() {
		return actionContext;
	}
	/**
	 * 设置控制器动作上下文对象
	 * @param actionContext
	 */
	public void setActionContext(ActionContext actionContext) {
		this.actionContext = actionContext;
	}			
	/**
	 * 加入动作业务的消息值到全局消息
	 * @param value
	 */
	public void addActionMessage(String value){
		getActionContext().set(GlobalMessage.GLOBAL_KEY, value);
	}
	/**
	 * 执行业务动作请求
	 * @return
	 */
	public String execute(){
		return null;
	}
}
