package com.application.support.action;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.framework.common.servlet.http.RequestHash;
import com.application.support.biz.UserMgrBiz;
import com.controller.action.SupportAction;
/**
 * 用户管理动作类
 * @author wangyf
 * @version 1.0
 */
public class UserMgrAction extends SupportAction{
	
	public String checkUserName(){
		HttpServletResponse response=this.getActionContext().getResponse();
		HttpServletRequest request=this.getActionContext().getRequest();
		response.setContentType("text/html;charset=utf-8");
		RequestHash reh=new RequestHash(request,response,false);			
		String msg="成功";
		String id=reh.get("id");
		String userName=reh.get("userName");
		try {    		
			UserMgrBiz biz=new UserMgrBiz();
			biz.setReh(reh);
			msg=biz.checkUserName(id, userName);
			PrintWriter out = response.getWriter();
			out.write(msg);
	        out.flush();	       
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return null;
	}
	public String checkLoginName(){
		HttpServletResponse response=this.getActionContext().getResponse();
		HttpServletRequest request=this.getActionContext().getRequest();
		response.setContentType("text/html;charset=utf-8");
		RequestHash reh=new RequestHash(request,response,false);			
		String msg="成功";
		String id=reh.get("id");
		String loginName=reh.get("loginName");
		try {    		
			UserMgrBiz biz=new UserMgrBiz();
			biz.setReh(reh);
			msg=biz.checkLoginName(id, loginName);
			PrintWriter out = response.getWriter();
			out.write(msg);
	        out.flush();	       
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return null;
	}
	public String checkOldPwd(){
		HttpServletResponse response=this.getActionContext().getResponse();
		HttpServletRequest request=this.getActionContext().getRequest();
		response.setContentType("text/html;charset=utf-8");
		RequestHash reh=new RequestHash(request,response,false);			
		String msg="成功";
		String id=reh.get("id");
		String oldPwd=reh.get("oldPwd");
		try {    		
			UserMgrBiz biz=new UserMgrBiz();
			biz.setReh(reh);
			msg=biz.checkOldPwd(id, oldPwd);
			PrintWriter out = response.getWriter();
			out.write(msg);
	        out.flush();	       
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return null;
	}
}
