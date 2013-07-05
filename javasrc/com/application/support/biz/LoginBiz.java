package com.application.support.biz;
import java.util.Map;

import javax.servlet.http.HttpSession;
import com.application.support.dao.UserDao;
import com.application.support.dao.LoginDao;
import com.application.support.model.LoginInfo;
import com.framework.common.encrypt.Md5Encrypt;
import com.framework.common.servlet.http.RequestHash;
import com.framework.common.util.SysConstants;
/**
 * 用户验证类
 * @author wangyf
 * @version 1.0
 */
public class LoginBiz {
	private RequestHash reh;		
	
	public LoginBiz(RequestHash reh){
		this.reh=reh;		
	}
	
	//用户登出
	public String logout(){				
		try{				
			HttpSession session=reh.getSession();
			if(session!=null){
				SessionMonitor monitor=new SessionMonitor(reh);
				monitor.logout(session);						
			}						
			reh.clear();	
		}catch(Exception e){
			e.printStackTrace();
			return SysConstants.exce_failed;
		}
		return SysConstants.exce_succeed;
	}
	//用户登录
	public String login(){
		String loginName=reh.get("loginName");
		String loginPWD=reh.get("loginPWD");
		String checkCode=reh.get("checkCode");
		return login(loginName,loginPWD,checkCode);
	}
	//用户登录
	public String login(String loginName,String loginPWD,String checkCode){		
		String result="";	
		if(checkCode.equals("")||checkCode!=null){
			String auth=(String)reh.getSession().getAttribute(SysConstants.img_key);
			if(auth==null){
				return "对不起,非法用户访问";
			}
			if(!checkCode.equals(auth)){
				return "对不起,验证码不正确";
			}
		}			
		result=commUser(loginName,Md5Encrypt.md5(loginPWD),"1");		
		reh.clear();
		return result;
	}
	//用户登录
	public String login(String loginName,String loginPWD){
		String result="";				
		result=commUser(loginName,Md5Encrypt.md5(loginPWD),"1");		
		reh.clear();
		return result;
	}
	//EMAIL方式用户登录
	public String loginByEmail(String email,String loginPWD,String checkCode){
		String result="";	
		if(checkCode.equals("")||checkCode!=null){
			String auth=(String)reh.getSession().getAttribute(SysConstants.img_key);
			if(auth==null){
				return "对不起,非法用户访问";
			}
			if(!checkCode.equals(auth)){
				return "对不起,验证码不正确";
			}
		}			
		result=commUser(email,Md5Encrypt.md5(loginPWD),"2");
		if(!result.equals("成功")){
			return result;
		}			
		return result;
	}		
	public String commUser(String uid,String password,String loginType){			
		String result=SysConstants.exce_succeed;		
	    LoginInfo loginInfo=null;					
    	try {    	
    		
    		UserDao userDao=new UserDao();
    		userDao.setJdbcTmplt(reh.getJdbcTmplt());	
    		SessionMonitor monitor=new SessionMonitor(reh);
    		//多会话登录相同账号开关
    		if("true".equals(SysConstants.session_limit)){
    			if(!monitor.verify(uid, password))
        			return "对不起,"+uid+"用户已经登录系统";	
    		}    	
            //验证用户登录 1:用户验证;2:邮箱验证;3:CA验证
    		if("1".equals(loginType)){    			
    			loginInfo=userDao.getLoginInfo(uid, password); 
    		}else if("2".equals(loginType)){    				
    			loginInfo=userDao.getLoginInfoByEmail(uid, password);
    		}    	
    
    		if(!userDao.allowLogin(loginInfo)){    			
    			if(!userDao.isExist(loginInfo)){
    				return "对不起,用户名或密码不正确,请重新输入";
    			}else if(userDao.isLock(loginInfo)){
    				return "对不起,用户被锁定,请联系管理员";
    			}
    		}           		
    		//验证用户有效日期,管理员用户和员工用户不需要验证;1:管理员用户;2:内部用户;3:外部用户
    		if("true".equals(SysConstants.session_time)){       		  		    			
    			if(!"1".equals(loginInfo.getUserType())&&!"3".equals(loginInfo.getUserType())){  				
            		if(!userDao.authenticationTimeValid(loginInfo)){
        				return "对不起,用户有效日期为"+loginInfo.getStartDate()+"至"+loginInfo.getEndDate()+",请联系管理员";
        			}
    			}    			
    		}    	        	
            //加载用户授权
    		loginInfo.setPerviews(userDao.getUserGrantPerview(loginInfo));
			if(loginInfo.getPerviews().size()<1){
				return "对不起,用户没有分配权限,请联系管理员";
			}    
			//验证用户登录会话
	   		HttpSession session=reh.getSession();    		
    		result=monitor.login(session,loginInfo);        		
    		reh.getSessionHash().put("USERID", loginInfo.getUserId());
    		reh.getSessionHash().put("USERNAME", loginInfo.getUserName());
    		reh.getSessionHash().put("LOGINNAME", loginInfo.getLoginName());
    		reh.getJdbcTmplt().close();
    	} catch (Exception e) {				
			e.printStackTrace();				
			return SysConstants.exce_failed;
		}				
		return result;		
	}
}
