package com.application.support.action;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.framework.common.servlet.http.RequestHash;
import com.application.support.biz.LoginBiz;
import com.controller.action.SupportAction;
/**
 * 登录验证类
 * @author wangyf
 * @version 1.0
 */
public class LoginAction extends SupportAction{
	
	public String login(){
		
		HttpServletResponse response=this.getActionContext().getResponse();
		HttpServletRequest request=this.getActionContext().getRequest();
		response.setContentType("text/html;charset=utf-8");
		RequestHash reh=new RequestHash(request,response,false);			
		String msg="成功";
		String loginName=reh.get("loginName");
		String password=reh.get("password");
		try {    	
			LoginBiz loginBiz=new LoginBiz(reh);
			msg=loginBiz.login(loginName, password);
			
			PrintWriter out = response.getWriter();
			out.write(msg);
	        out.flush();	       
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return null;
	}
	public String logout(){
		HttpServletResponse response=this.getActionContext().getResponse();
		HttpServletRequest request=this.getActionContext().getRequest();
		response.setContentType("text/html;charset=utf-8");
		RequestHash reh=new RequestHash(request,response,false);			
		String msg="成功";
		try {    		
			LoginBiz loginBiz=new LoginBiz(reh);
			msg=loginBiz.logout();
			PrintWriter out = response.getWriter();
			out.write(msg);
	        out.flush();	       
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return null;
	}

}
