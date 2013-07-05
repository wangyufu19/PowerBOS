package com.application.support.dao;
import java.sql.SQLException;

import com.application.support.model.LoginInfo;
import com.powerbosframework.jdbc.support.JdbcDaoSupport;

public class LoginDao extends JdbcDaoSupport{
	
	public LoginInfo getLoginInfo(String loginName, String password) throws SQLException{
		
		String sql="";
		
	    LoginInfo loginInfo=new LoginInfo();	  
	   	String[] args=new String[2];
	   	args[0]=loginName;
	   	args[1]=password;
	    sql="select id,user_name,login_name,login_pwd,user_type,user_status,org_id,company_id,site_id,email,is_admin "+
            "from sm_t_user where login_name=? and login_pwd=?";
    	Object[] obj=this.queryForArray(sql, args);
    	
		loginInfo.setUserId(String.valueOf(obj[0]));
		loginInfo.setUserName(String.valueOf(obj[1]));
		loginInfo.setLoginName(String.valueOf(obj[2]));
		loginInfo.setLoginPwd(String.valueOf(obj[3]));
		loginInfo.setUserType(Integer.parseInt(String.valueOf(obj[4])));	
		loginInfo.setUserStatus(Integer.parseInt(String.valueOf(obj[5])));		
		loginInfo.setOrgId(String.valueOf(obj[6]));
		loginInfo.setCompanyId(String.valueOf(obj[7]));
		loginInfo.setSiteId(String.valueOf(obj[8]));			
		loginInfo.setEmail(String.valueOf(obj[9]));	
		loginInfo.setIsAdmin(Integer.parseInt(String.valueOf(obj[10])));
		
		return loginInfo;	
	}
}
