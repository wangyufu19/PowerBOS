package com.application.support.biz;
import java.sql.SQLException;

import com.application.support.model.User;
import com.framework.common.base.BaseBiz;
import com.framework.common.encrypt.Md5Encrypt;
import com.sqlMap.Transaction;
import com.sqlMap.id.IdentifierGenerator;
import com.sqlMap.jdbc.JdbcTmplt;
import com.sqlMap.jdbc.QueryTmplt;
import com.sqlMap.jdbc.util.MakeUpUtil;
/**
 * 用户管理业务类
 * @author wangyf
 * @version 1.0
 */
public class UserMgrBiz extends BaseBiz{
	public String addUser(){
		String msg="成功";
		User user=new User();
		user.setOrgId(reh.get("orgId"));
		user.setUserName(reh.get("userName"));
		user.setLoginName(reh.get("loginName"));
		user.setLoginPwd(Md5Encrypt.md5(reh.get("loginPwd")));
		user.setUserType(Integer.valueOf(reh.get("userType")));
		user.setUserStatus(Integer.valueOf(reh.get("userStatus")));
		user.setEmail(reh.get("email"));
		user.setMobilePhone(reh.get("mobilePhone"));
		user.setTelePhone(reh.get("telePhone"));
		user.setPostcode(reh.get("postcode"));
		user.setAddress(reh.get("address"));
		user.setRemark(reh.get("remark"));
		JdbcTmplt jdbcTmplt=reh.getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		String sql="";						
		Transaction tx=null;		
		try {
			tx=jdbcTmplt.beginTransaction();	
			user.setId(String.valueOf(new IdentifierGenerator().getUUID()));
			sql="insert into sm_t_user(id,org_id,user_name,login_name,login_pwd,user_type,user_status,email,mobile_phone," +
			"tele_phone,postcode,address,remark) values(@,@,@,@,@,#,#,@,@,@,@,@,@)";
			String[] arr={String.valueOf(user.getId()),String.valueOf(user.getOrgId()),user.getUserName(),user.getLoginName(),user.getLoginPwd(),
					      String.valueOf(user.getUserType()),String.valueOf(user.getUserStatus()),user.getEmail(),user.getMobilePhone(),
					      user.getTelePhone(),user.getPostcode(),user.getAddress(),user.getRemark()};
			sql=MakeUpUtil.makeUp(arr, sql);
			jdbcTmplt.execute(sql);
			sql="select id from sm_t_role where role_code='default'";
			Object[] obj=queryTmplt.getArray(sql);
			String roleId="";
			if(obj!=null)
				roleId=String.valueOf(obj[0]);
			sql="insert into sm_t_user_role(id,user_id,role_id) values(@,@,@)";
		    String[] arr1={String.valueOf(new IdentifierGenerator().getUUID()),user.getId(),roleId};
		    sql=MakeUpUtil.makeUp(arr1, sql);		    
		    jdbcTmplt.execute(sql);
			tx.commit();
		} catch (SQLException e) {		
			if(tx!=null){
				try {
					tx.rollback();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			msg="操作失败";
		}		
		return msg;
	}
	public String deleteUser(){
		String msg="成功";
		String[] idArr=reh.getArray("id");
		if(idArr==null) return msg;
		JdbcTmplt jdbcTmplt=reh.getJdbcTmplt();
		String sql="";						
		Transaction tx=null;		
		try {
			tx=jdbcTmplt.beginTransaction();	
			for(int i=0;i<idArr.length;i++){
				sql="delete from sm_t_user where id=@";
				sql=MakeUpUtil.makeUp(idArr[i], sql);
				jdbcTmplt.execute(sql);
				sql="delete from sm_t_user_role where user_id=@";
				sql=MakeUpUtil.makeUp(idArr[i], sql);
				jdbcTmplt.execute(sql);
			}
			tx.commit();
		} catch (SQLException e) {		
			if(tx!=null){
				try {
					tx.rollback();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			msg="操作失败";
		}		
		return msg;
	}
	/**
	 * 验证用户名称唯一性
	 * @param id
	 * @param userName
	 * @return
	 */
	public String checkUserName(String id,String userName){
		String msg="成功";
		String sql="";
		Object[] obj=null;		
		if("".equals(userName)) return msg;
		JdbcTmplt jdbcTmplt=this.getReh().getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		sql="select * from sm_t_user where user_name=@ and " +
			"user_name not in(select user_name from sm_t_user where id=@)";	
		String[] arr={userName,id};
		sql=MakeUpUtil.makeUp(arr, sql);
		try {
			obj=queryTmplt.getArray(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			try {
				jdbcTmplt.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		} 
		if(obj!=null) msg="对不起,您输入的用户名称已经存在!";
		return msg;		
	}
	/**
	 *  验证登录名称唯一性
	 * @param id
	 * @param loginName
	 * @return
	 */
	public String checkLoginName(String id,String loginName){
		String msg="成功";
		String sql="";
		Object[] obj=null;		
		if("".equals(loginName)) return msg;
		JdbcTmplt jdbcTmplt=this.getReh().getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		sql="select id from sm_t_user where login_name=@ and " +
			"login_name not in(select login_name from sm_t_user where id=@)";
		String[] arr={loginName,id};
		sql=MakeUpUtil.makeUp(arr, sql);
		try {
			obj=queryTmplt.getArray(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			try {
				jdbcTmplt.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		} 
		if(obj!=null) msg="对不起,您输入的登录名称已经存在!";
		return msg;		
	}
	/**
	 *  验证旧密码是否正确
	 * @param id
	 * @param oldPwd
	 * @return
	 */
	public String checkOldPwd(String id,String oldPwd){
		String msg="成功";
		String sql="";
		Object[] obj=null;
		if("".equals(oldPwd)) return msg;
		JdbcTmplt jdbcTmplt=this.getReh().getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		sql="select id from sm_t_user where login_pwd=@ and id=@";		
		String[] arr={Md5Encrypt.md5(oldPwd),id};
		sql=MakeUpUtil.makeUp(arr, sql);
		try {
			obj=queryTmplt.getArray(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			try {
				jdbcTmplt.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		} 
		if(obj==null) msg="对不起,您输入的旧密码不正确,请重新输入!";
		return msg;	
	}

}
