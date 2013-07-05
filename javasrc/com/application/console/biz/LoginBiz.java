package com.application.console.biz;
import com.framework.common.util.SysConstants;
import com.framework.common.encrypt.Md5Encrypt;
/**
 * 后台登录业务类
 * @author wangyf
 * @version 1.0
 */
public class LoginBiz {
	
	public String login(String username,String password){
		String msg="成功";		
		password=Md5Encrypt.md5(password);
		if(!SysConstants.auth_username.equals(username)||!SysConstants.auth_password.equals(password)){
			msg="对不起,用户名或密码不正确,请重新输入";
		}		
		return msg;
	}
}
