package com.application.console.action;
import java.util.List;

import com.application.console.biz.PageResourceBiz;
import com.application.console.model.PageResource;
import com.controller.action.SupportAction;
import com.framework.common.servlet.http.RequestHash;
/**
 * 页面资源管理类
 * @author wangyf
 * @version 1.0
 */
public class PageResourceAction extends SupportAction{
	private List pageResources;	
	private PageResource pageResource;		
	
	public List getPageResources() {
		return pageResources;
	}
	public void setPageResources(List pageResources) {
		this.pageResources = pageResources;
	}
	public PageResource getPageResource() {
		return pageResource;
	}
	public void setPageResource(PageResource pageResource) {
		this.pageResource = pageResource;
	}
	public String execute(){		
		RequestHash reh=new RequestHash(this.getActionContext().getRequest(),this.getActionContext().getResponse());		
		PageResourceBiz biz=new PageResourceBiz();	
		biz.setReh(reh);
		List list=biz.getPageResourceList();	
		this.setPageResources(list);			
		return this.SUCCESS;
	}
	public String getAddPageResource(){
		return this.SUCCESS;
	}
	public String addPageResource(){
		RequestHash reh=new RequestHash(this.getActionContext().getRequest(),this.getActionContext().getResponse());		
		PageResourceBiz biz=new PageResourceBiz();		
		biz.setReh(reh);		
		biz.addPageResource(pageResource);		
		return this.execute();
	}	
	public String deletePageResource(){
		RequestHash reh=new RequestHash(this.getActionContext().getRequest(),this.getActionContext().getResponse());		
		PageResourceBiz biz=new PageResourceBiz();
		biz.setReh(reh);
		String[] resources=this.getActionContext().getArray("id");
		biz.deletePageResources(resources);
		return this.execute();
	}
}
