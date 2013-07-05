package com.application.console.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.controller.action.SupportAction;
import com.framework.common.encrypt.Md5Encrypt;
import com.framework.common.util.SysConstants;
/**
 * 控制台登录动作类
 * @author wangyf
 * @version 1.0
 */
public class LoginAction extends SupportAction{

	public String login(){	
		HttpServletResponse response=this.getActionContext().getResponse();		
		response.setContentType("text/html;charset=utf-8");
		String msg="成功";
		String username=this.getActionContext().get("username");
		String password=this.getActionContext().get("password");
		try {        
			password=Md5Encrypt.md5(password);
			if(!SysConstants.auth_username.equals(username)||!SysConstants.auth_password.equals(password)){
				msg="对不起,用户名或密码不正确!";
			}else if(SysConstants.auth_username.equals(username)&&SysConstants.auth_password.equals(password)){
				HttpSession session=this.getActionContext().getSession();
				Map user=new HashMap();
				user.put("console.username", username);
				user.put("console.password", password);
				session.removeAttribute(session.getId());
				session.setAttribute(session.getId(), user);			
			}
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
		response.setContentType("text/html;charset=utf-8");
		String msg="成功";
		HttpSession session=this.getActionContext().getSession();
		session.removeAttribute(session.getId());		
		try {
			PrintWriter out= response.getWriter();
			out.write(msg);
	        out.flush();
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		return null;
	}
}
