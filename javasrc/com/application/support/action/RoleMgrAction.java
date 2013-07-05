package com.application.support.action;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.framework.common.servlet.http.RequestHash;
import com.application.support.biz.RoleMgrBiz;
import com.controller.action.SupportAction;
/**
 * 角色管理动作类
 * @author wangyf
 * @version 1.0
 */
public class RoleMgrAction extends SupportAction{
	
	public String checkRoleCode(){
		HttpServletResponse response=this.getActionContext().getResponse();
		HttpServletRequest request=this.getActionContext().getRequest();
		response.setContentType("text/html;charset=utf-8");
		RequestHash reh=new RequestHash(request,response,false);			
		String msg="成功";
		String id=reh.get("id");
		String roleCode=reh.get("roleCode");
		try {    		
			RoleMgrBiz biz=new RoleMgrBiz();
			biz.setReh(reh);
			msg=biz.checkRoleCode(id, roleCode);
			PrintWriter out = response.getWriter();
			out.write(msg);
	        out.flush();	       
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return null;
	}

}
