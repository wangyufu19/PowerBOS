package com.application.support.action;
import java.util.HashMap;
import java.util.Map;

import com.application.support.biz.LoginBiz;
import com.framework.common.ajax.buffalo.AjaxService;
import com.framework.common.servlet.http.RequestHash;
import com.framework.common.util.SysConstants;

/**
 * 登录验证类
 * @author wangyf
 * @version 1.0
 */
public class LoginService extends AjaxService{	
	private static final String AJAX_LOGIN_RETURN_MSG="ajaxLoginReturnMsg";
	private static final String AJAX_LOGOUT_RETURN_MSG="ajaxLogoutReturnMsg";
	private RequestHash reh;
	public LoginService(){
		
	}
	/**
     * 登录系统
     * @param loginName 登录名
     * @param loginPwd 密码
     * @param checkCode 验证码
     * @return 返回登录的结果
     */
	public Map login(String loginName,String loginPWD,String checkCode){		
		String ret="";
		reh=super.getReh();	
		Map<String, String> map=new HashMap<String, String>();		
		LoginBiz loginBiz=new LoginBiz(reh);
		if("true".equals(SysConstants.check_code))
			ret=loginBiz.login(loginName, loginPWD, checkCode);	
		else
			ret=loginBiz.login(loginName, loginPWD);	
		map.put(AJAX_LOGIN_RETURN_MSG, ret);
		return map;
	}	
	/**
	 * 登录系统
	 * @param email
	 * @param loginPWD
	 * @param checkCode
	 * @return 返回登录的结果
	 */
	public Map loginByMail(String email,String loginPWD,String checkCode){
		String ret="";
		Map<String, String> map=new HashMap<String, String>();
		reh=super.getReh();	
		LoginBiz loginBiz=new LoginBiz(reh);
		ret=loginBiz.loginByEmail(email, loginPWD, checkCode);
		map.put(AJAX_LOGIN_RETURN_MSG, ret);
		return map;
	}
	/**
	 * 退出系统
	 * @return 返回退出的结果
	 */
    public Map logout(){    	
    	String ret="";
    	Map<String, String> map=new HashMap<String, String>();	
    	reh=super.getReh();    	
		LoginBiz loginBiz=new LoginBiz(reh);
		ret=loginBiz.logout();     	
    	map.put(AJAX_LOGOUT_RETURN_MSG, ret);
    	return map;
    }
	
	
}
