package com.application.support.action;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.application.support.biz.OrgMgrBiz;
import com.controller.action.SupportAction;
import com.framework.common.servlet.http.RequestHash;

/**
 * 组织机构管理动作类
 * @author wangyf
 * @version 1.0
 */
public class OrgMgrAction extends SupportAction{	
	
	public String getChildOrgTree(){
		HttpServletResponse response=this.getActionContext().getResponse();
		HttpServletRequest request=this.getActionContext().getRequest();
	    response.setCharacterEncoding("gb2312");
        response.setContentType("text/xml charset=gb2312");   
        response.setHeader("Pragma","No-cache"); 
    	response.setHeader("Cache-Control","no-cache"); 
    	response.setDateHeader("Expires", 0);   
        String treedoc="";
        RequestHash reh=new RequestHash(request,response);
    	OrgMgrBiz biz=new OrgMgrBiz();
    	biz.setReh(reh);        	
        try {        
			treedoc=biz.getChildOrganizationXML();		
			PrintWriter out = response.getWriter();
			out.write(treedoc);
	        out.flush();	       
		} catch (IOException e) {		
			e.printStackTrace();
		}
        return null;
	}
	public String checkOrgCode(){
		HttpServletResponse response=this.getActionContext().getResponse();
		HttpServletRequest request=this.getActionContext().getRequest();
		response.setContentType("text/html;charset=utf-8");
		RequestHash reh=new RequestHash(request,response,false);			
		String msg="成功";
		String id=reh.get("id");
		String orgCode=reh.get("orgCode");
		try {    
			OrgMgrBiz biz=new OrgMgrBiz();
			biz.setReh(reh);
			msg=biz.checkOrgCode(id, orgCode);
			PrintWriter out = response.getWriter();
			out.write(msg);
	        out.flush();	       
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return null;
	}
	public String checkOrgName(){
		HttpServletResponse response=this.getActionContext().getResponse();
		HttpServletRequest request=this.getActionContext().getRequest();
		response.setContentType("text/html;charset=utf-8");
		RequestHash reh=new RequestHash(request,response,false);	
		String msg="成功";
		String id=reh.get("id");
		String orgName=reh.get("orgName");		
		try {    
			OrgMgrBiz biz=new OrgMgrBiz();
			biz.setReh(reh);
			msg=biz.checkOrgName(id, orgName);
			PrintWriter out = response.getWriter();
			out.write(msg);
	        out.flush();	       
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return null;
	}
}
