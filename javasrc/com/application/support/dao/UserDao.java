package com.application.support.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.application.support.model.Function;
import com.application.support.model.LoginInfo;
import com.application.support.model.User;
import com.application.support.model.UserLogin;
import com.framework.common.base.JdbcBaseDao;
import com.sqlMap.jdbc.JdbcTmplt;
import com.sqlMap.jdbc.util.MakeUpUtil;
import com.sqlMap.id.IdentifierGenerator;
import com.powerbosframework.context.ApplicationContext;
import com.powerbosframework.context.XmlApplicationContext;
public class UserDao extends JdbcBaseDao{
	/**
	 * 验证用户安全性
	 * @param loginInfo
	 * @param loginType
	 * @return
	 */
	public boolean allowLogin(LoginInfo loginInfo){
		boolean bool=false;
		if(isExist(loginInfo)){				
			bool=true;
		}				
		if(isLock(loginInfo)){			
			bool=false;
		}
		
		return bool;
	}
	/**
	 * 验证用户是否存在
	 * @param loginInfo
	 * @param loginType
	 * @return
	 */
	public boolean isExist(LoginInfo loginInfo){		
		if(loginInfo==null){		
			return false;
		}	
		return true;
	}
	/**
	 * 验证用户是否锁定
	 * @param loginInfo
	 * @return
	 */
	public boolean isLock(LoginInfo loginInfo){			
		int userStatus=2;	
		if(loginInfo!=null){		
			userStatus=loginInfo.getUserStatus();	
			if(userStatus==2){
				return true;
			}	
		}					
		return false;
	}		
	//插入用户登录会话信息
	public void addLoginInfo(UserLogin userLogin) throws SQLException{
		String sql="";
		String resultMap="userLoginResult";
		JdbcTmplt jdbcTmplt=this.getJdbcTmplt();
		userLogin.setId(String.valueOf(new IdentifierGenerator().getUUID()));
		sql="insert into sm_t_user_login(id,user_id,session_id,ip_address,last_login_time,is_online) values(" +
			"$id$,$userId$,$sessionId$,$ipAddress$,$lastLoginTime$,$isOnline$)";			
		jdbcTmplt.execute(sql, userLogin, resultMap);		
	}
	//更新用户登出信息
	public void updateLogoutInfo(User user) throws SQLException{
		
	}	
	//通过用户名和密码验证方式,得到用户对象
	public LoginInfo getLoginInfo(String loginName, String password) throws SQLException{
		ApplicationContext applicationContext=new XmlApplicationContext();		
		String sql="";
		String returnClass="user";
		String resultMap="userResult";
	    LoginInfo loginInfo=new LoginInfo();	  
	    User user=new User();
	    user.setLoginName(loginName);
	    user.setLoginPwd(password);  		 
	    sql="select id,user_name,login_name,login_pwd,user_type,user_status,org_id,company_id,site_id,email,is_admin "+
            "from sm_t_user where login_name=$loginName$ and login_pwd=$loginPwd$";
    	user=(User)this.getObject(sql, user, returnClass, resultMap);			
		if(user==null) return null;		
	
		loginInfo.setUserId(user.getId());
		loginInfo.setUserName(user.getUserName());
		loginInfo.setLoginName(user.getLoginName());
		loginInfo.setLoginPwd(user.getLoginPwd());
		loginInfo.setUserType(user.getUserType());	
		loginInfo.setUserStatus(user.getUserStatus());		
		loginInfo.setOrgId(user.getOrgId());
		loginInfo.setCompanyId(user.getCompanyId());
		loginInfo.setSiteId(user.getSiteId());			
		loginInfo.setEmail(user.getEmail());	
		loginInfo.setIsAdmin(user.getIsAdmin());
		
		return loginInfo;	
	}
	//通过邮箱验证方式,得到用户对象
	public LoginInfo getLoginInfoByEmail(String email,String password) throws SQLException{
		String sql="";
		String returnClass="user";
		String resultMap="userResult";
		LoginInfo loginInfo=new LoginInfo();	   
	    User user=new User();
	    user.setEmail(email);
	    user.setLoginPwd(password);  		
		sql="select id,user_name,login_name,login_pwd,user_type,user_status,org_id,company_id,site_id,email,is_online "+ 
            "from sm_t_user where email=$email$ and login_pwd=$loginPwd$";
    	user=(User)this.getObject(sql,user, returnClass, resultMap);				
		if(user==null) return null;			
		loginInfo.setUserId(user.getId());
		loginInfo.setUserName(user.getUserName());
		loginInfo.setLoginName(user.getLoginName());
		loginInfo.setLoginPwd(user.getLoginPwd());
		loginInfo.setUserType(user.getUserType());
		loginInfo.setUserStatus(user.getUserStatus());
		loginInfo.setOrgId(user.getOrgId());
		loginInfo.setCompanyId(user.getCompanyId());
		loginInfo.setSiteId(user.getSiteId());		
		loginInfo.setEmail(user.getEmail());
		loginInfo.setOnline(user.getIsOnline());				
		return loginInfo;
	}
	//验证用户有效时间范围
	public boolean authenticationTimeValid(LoginInfo loginInfo) throws NumberFormatException, SQLException{			
		String sql="";
		String returnClass="user";
		String resultMap="userResult";
		Object obj=null;	
		Map params=new HashMap();
		params.put("id", loginInfo.getUserId());
		sql="select id from sm_t_user where to_char(sysdate,'yyyymmdd')>=to_char(start_date,'yyyymmdd') and to_char(end_date,'yyyymmdd')>=to_char(sysdate,'yyyymmdd') and id=$id$";
		obj=this.getObject(sql, params, returnClass, resultMap);    		
		if(obj==null) 
			return false;			
		return true;
	}	
	//得到用户被授予的权限
	public Map getUserGrantPerview(LoginInfo loginInfo) throws SQLException{
		String sql="";
		String returnClass="function";
		String resultMap="funResult";
		Map<String, Function> perviews=new HashMap<String, Function>();		
		List list=new ArrayList();			
		if(loginInfo.getIsAdmin()==1){	
			//超级管理员授权		
			sql="select id,function_code,function_name,function_url,function_type from sm_t_function";
			list=this.getList(sql, returnClass, resultMap);			
		}else {
			//其它用户类型授权							
			sql="select d.id,d.function_code,d.function_name,d.function_url,d.function_type from sm_t_function d "+ 
            "where d.id in(select distinct function_id from sm_t_role_function c where c.role_id in(select a.role_id from sm_t_user_role a "+
            "inner join sm_t_role b on a.role_id=b.id where a.user_id=@))";
			sql=MakeUpUtil.makeUp(loginInfo.getUserId(), sql);
			list=this.getList(sql, returnClass, resultMap);				
		}						
		if(list==null) return perviews;		
		for(int i=0;i<list.size();i++){
			Function function=(Function)list.get(i);				
			perviews.put(function.getFunctionCode(), function);
		}		
		return perviews;
	}		
	public List getUserPerviewMenu(String parentId) throws SQLException{
		String sql="";
		String returnClass="function";
		String resultMap="funResult";	
		sql="select a.id,a.function_code,a.function_name,a.function_type,a.function_url,a.order_no from sm_t_function a" +
			" where a.function_parent_id=$functionParentId$ order by a.order_no";
		Function function=new Function();
		function.setFunctionParentId(parentId);
		List list=this.getList(sql, function, returnClass, resultMap);			
		return list;
	}
}
