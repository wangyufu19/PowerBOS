package com.application.console.action;
import java.util.List;

import com.application.console.biz.PageModuleBiz;
import com.application.console.model.PageModule;
import com.controller.action.SupportAction;
import com.framework.common.servlet.http.RequestHash;
/**
 * 页面模块动作类
 * @author wangyf
 * @version 1.0
 */
public class PageModuleAction extends SupportAction{
	private List pageModules;
	private PageModule pageModule;
	
	
	public List getPageModules() {
		return pageModules;
	}
	public void setPageModules(List pageModules) {
		this.pageModules = pageModules;
	}
	public PageModule getPageModule() {
		return pageModule;
	}
	public void setPageModule(PageModule pageModule) {
		this.pageModule = pageModule;
	}
	public String execute(){
		
		RequestHash reh=new RequestHash(this.getActionContext().getRequest(),this.getActionContext().getResponse());		
		PageModuleBiz biz=new PageModuleBiz();
		biz.setReh(reh);
		this.setPageModules(biz.getPageModuleList());
		return this.SUCCESS;
	}
	public String getAddPageModule(){
		return this.SUCCESS;
	}
	public String addPageModule(){
		
		RequestHash reh=new RequestHash(this.getActionContext().getRequest(),this.getActionContext().getResponse());		
		PageModuleBiz biz=new PageModuleBiz();
		biz.setReh(reh);
		
		biz.addPageModule(pageModule);
		return this.execute();
		
	}
	public String getUpdatePageModule(){
		RequestHash reh=new RequestHash(this.getActionContext().getRequest(),this.getActionContext().getResponse());		
		PageModuleBiz biz=new PageModuleBiz();
		biz.setReh(reh);
		this.setPageModule(biz.getPageModule(reh.get("key")));
		return this.SUCCESS;
	}
	public String updatePageModule(){
		
		RequestHash reh=new RequestHash(this.getActionContext().getRequest(),this.getActionContext().getResponse());		
		PageModuleBiz biz=new PageModuleBiz();
		biz.setReh(reh);
		biz.updatePageModule(pageModule);
		return this.execute();
	}
	public String deletePageModule(){
		RequestHash reh=new RequestHash(this.getActionContext().getRequest(),this.getActionContext().getResponse());		
		PageModuleBiz biz=new PageModuleBiz();
		biz.setReh(reh);
		biz.deletePageModules(reh.getArray("id"));
		return this.execute();
	}
}
