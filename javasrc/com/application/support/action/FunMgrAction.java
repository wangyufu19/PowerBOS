package com.application.support.action;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.support.biz.FunMgrBiz;
import com.controller.action.SupportAction;
import com.framework.common.servlet.http.RequestHash;
/**
 * 系统权限管理
 * @author wangyf
 * @version 1.0
 */
public class FunMgrAction extends SupportAction{
	
	public String getChildFunTree(){
		HttpServletResponse response=this.getActionContext().getResponse();
		HttpServletRequest request=this.getActionContext().getRequest();
		RequestHash reh=new RequestHash(request,response);
	    response.setCharacterEncoding("gb2312");
        response.setContentType("text/xml charset=gb2312");   
        response.setHeader("Pragma","No-cache"); 
    	response.setHeader("Cache-Control","no-cache"); 
    	response.setDateHeader("Expires", 0);  		
		String treedoc="";		
		FunMgrBiz biz=new FunMgrBiz();
		biz.setReh(reh);
		try {           	
			treedoc=biz.getChildFunXML();		
			PrintWriter out = response.getWriter();
			out.write(treedoc);
	        out.flush();	       
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return null;
	}
	public String getFunOrderNumber(){
		HttpServletResponse response=this.getActionContext().getResponse();
		HttpServletRequest request=this.getActionContext().getRequest();
		response.setContentType("text/html;charset=utf-8");
		RequestHash reh=new RequestHash(request,response,false);			
		String orderNumber="1";
		String parentId=reh.get("parentId");		
		try {    		
			FunMgrBiz biz=new FunMgrBiz();
			biz.setReh(reh);
			orderNumber=biz.getOrderNumber(parentId);
			PrintWriter out = response.getWriter();
			out.write(orderNumber);
	        out.flush();	       
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return null;
	}
	public String checkFunCode(){
		HttpServletResponse response=this.getActionContext().getResponse();
		HttpServletRequest request=this.getActionContext().getRequest();
		response.setContentType("text/html;charset=utf-8");
		RequestHash reh=new RequestHash(request,response,false);			
		String msg="成功";
		String id=reh.get("id");
		String funCode=reh.get("funCode");
		try {    		
			FunMgrBiz biz=new FunMgrBiz();
			biz.setReh(reh);
			msg=biz.checkFunCode(id, funCode);
			PrintWriter out = response.getWriter();
			out.write(msg);
	        out.flush();	       
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return null;
	}
	public String checkFunName(){
		HttpServletResponse response=this.getActionContext().getResponse();
		HttpServletRequest request=this.getActionContext().getRequest();
		response.setContentType("text/html;charset=utf-8");
		RequestHash reh=new RequestHash(request,response,false);			
		String msg="成功";
		String id=reh.get("id");
		String funName=reh.get("funName");
		try {    		
			FunMgrBiz biz=new FunMgrBiz();
			biz.setReh(reh);
			msg=biz.checkFunName(id, funName);
			PrintWriter out = response.getWriter();
			out.write(msg);
	        out.flush();	       
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return null;
	}
}
