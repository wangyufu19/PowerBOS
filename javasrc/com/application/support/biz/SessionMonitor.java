package com.application.support.biz;
import java.util.Map;
import java.util.Hashtable;
import java.util.Iterator;
import java.lang.IllegalStateException;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.http.HttpSession;

import com.application.support.dao.UserDao;
import com.application.support.model.LoginInfo;
import com.application.support.model.User;
import com.application.support.model.UserLogin;
import com.framework.common.servlet.http.RequestHash;
import com.framework.common.util.SysConstants;

/**
 * Session监视器
 * @author wangyf
 * @version 1.0
 */
public class SessionMonitor {
	private static Map users=new Hashtable();		
	private RequestHash reh;
	
	public SessionMonitor(){
		
	}
	public SessionMonitor(RequestHash reh){
		this.reh=reh;
	}
	//通过Session验证用户登录状态
	public boolean verify(String loginName,String password){
		//清除超时用户会话
		this.clear();
		//验证用户是否在线							
		for(Iterator it=users.keySet().iterator();it.hasNext();){
			HttpSession tmpSession=(HttpSession)users.get(String.valueOf(it.next().toString()));
			LoginInfo tmpInfo=(LoginInfo)tmpSession.getAttribute(SysConstants.user_key);
			if(tmpInfo==null) continue;		
			if(tmpInfo.getLoginName().equals(loginName)&&tmpInfo.getLoginPwd().equals(password)){					
				return false;				
			}
		}	
		return true;		
	}
	//将用户信息Session存入系统会话管理器
	public String login(HttpSession session,LoginInfo loginInfo) throws SQLException{		
		if(session==null) return "创建会话失败";
		session.removeAttribute(SysConstants.user_key);    		    	
		session.setAttribute(SysConstants.user_key, loginInfo);	    		
//		Long nowTime=new Date().getTime();	
//		if(loginInfo.getLastLoginTime()!=null){
//			if(nowTime-loginInfo.getLastLoginTime()>SysConstants.SHORT_ONLINE_TIME*1000){
//				this.clear(loginInfo);
//			}	
//		}		
		//清除超时用户会话
		this.clear();
		if(users.containsKey(session.getId()))
			users.remove(session.getId());
		users.put(session.getId(), session);
		//System.out.println("******login start sessionid is "+session.getId());
		//刷新用户登录会话日志
		if("true".equals(SysConstants.session_log)){
			UserDao dao=new UserDao();
			dao.setJdbcTmplt(reh.getJdbcTmplt());
			UserLogin vo=new UserLogin();
			vo.setUserId(loginInfo.getUserId());			
			vo.setIpAddress(reh.getHttpServletRequest().getRemoteHost());
			vo.setSessionId(session.getId());
			vo.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
			vo.setIsOnline(1L);			
			dao.addLoginInfo(vo);
		}		
		return SysConstants.exce_succeed;
	}	
	
	//得到用户信息
	public LoginInfo getLoginInfo(HttpSession session){		
		LoginInfo loginInfo=null;
		if(users.containsKey(session.getId()))
			try{
				loginInfo=(LoginInfo)session.getAttribute(SysConstants.user_key);
			}catch(IllegalStateException e){
				return null;
			}			
		else
			return null;
		return loginInfo;
	}
	//判断用户会话是否超时
	public boolean isOverTime(HttpSession session){
		//System.out.println("******over time's sessionid is "+session.getId());
		LoginInfo loginInfo=null;		
		if(session==null) return true;
		loginInfo=this.getLoginInfo(session);
		if(loginInfo==null)
			return true;
		return false;
	}
	//清除当前用户会话
	public void logout(HttpSession session) throws SQLException{
		if(session==null) return;
		if(users.containsKey(session.getId())){					
			try{
				LoginInfo loginInfo=(LoginInfo)session.getAttribute(SysConstants.user_key);	
				//刷新用户登出会话数据
				if("true".equals(SysConstants.session_log)){
					UserDao dao=new UserDao();
					User user=new User();
					user.setId(loginInfo.getUserId());
					user.setIsOnline(0);
					dao.updateLogoutInfo(user);
				}				
				session.removeAttribute(SysConstants.user_key);					
				session.removeAttribute(SysConstants.img_key);			
				users.remove(session.getId());
			}catch(IllegalStateException e){
				return;
			}		
			
					
		}	
	}
	//清除指定的用户会话
	public void clear(User user){		
		if(user==null) return;
		for(Iterator it=users.keySet().iterator();it.hasNext();){
			HttpSession session=(HttpSession)users.get(String.valueOf(it.next().toString()));		
			try{
				LoginInfo loginInfo=(LoginInfo)session.getAttribute(SysConstants.user_key);		
				if(String.valueOf(user.getId()).equals(loginInfo.getUserId())){			
					session.removeAttribute(SysConstants.user_key);							
					session.removeAttribute(SysConstants.img_key);					
					users.remove(session.getId());
					break;
				}	
			}catch(IllegalStateException e){
				return;
			}						
		}
	}
	//清除超时用户会话,条件为超时时间大于5分钟 
	public void clear(LoginInfo loginInfo){		
		//清除系统超时用户会话
		for(Iterator it=users.keySet().iterator();it.hasNext();){
			HttpSession session=(HttpSession)users.get(String.valueOf(it.next().toString()));
			if(session==null) continue;
			if(this.isOverTime(session)){
				users.remove(session.getId());
			}
		}
		if(loginInfo==null) return;
		//清除指定的用户会话
		for(Iterator it=users.keySet().iterator();it.hasNext();){
			HttpSession session=(HttpSession)users.get(String.valueOf(it.next().toString()));			
			if(session==null) continue;			
			LoginInfo tmpInfo=(LoginInfo)session.getAttribute(SysConstants.user_key);
			if(tmpInfo.getLoginName().equals(loginInfo.getLoginName())&&tmpInfo.getLoginPwd().equals(loginInfo.getLoginPwd())){					
				users.remove(session.getId());
				//System.out.println("******remove sessionid is "+session.getId());
				break;					
			}			
		}	
	}
	//清除超时用户会话
	public void clear(){
		if(users==null) return;
		//清除系统超时用户会话
		for(Iterator it=users.keySet().iterator();it.hasNext();){
			HttpSession session=(HttpSession)users.get(String.valueOf(it.next().toString()));			
			if(session==null) continue;			
			if(this.isOverTime(session)){
				it.remove();				
			}
		}		
	}
	
}
