package com.application.support.mail;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.framework.common.base.BaseBiz;
import com.framework.common.mail.param.ReceiveParam;
import com.sqlMap.Transaction;
import com.sqlMap.jdbc.JdbcTmplt;
import com.sqlMap.jdbc.QueryTmplt;
import com.sqlMap.jdbc.util.MakeUpUtil;
import com.sqlMap.type.DateType;
/**
 * 邮件管理业务类
 * @author wangyf
 * @version 1.0
 */
public class MailMgrBiz extends BaseBiz{
	/**
	 * 得到用户邮箱账号和密码
	 * @param userId
	 * @return
	 * @throws SQLException 
	 */
	public Object[] getUserMailAccount(String userId){	
		String sql="";
		JdbcTmplt jdbcTmplt=reh.getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		sql="select account_name,account_pwd,pop_service,smtp_service,pop_port,smtp_port from bos_t_email_account where user_id=#";
		sql=MakeUpUtil.makeUp(userId, sql);
		Object[] obj=null;
		try {
			obj = queryTmplt.getArray(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return obj;
	}
	/**
	 * 得到用户已经存在的邮件
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public Map getUserExsitMail(String userId){
		Map map=new HashMap();
		String sql="";
		JdbcTmplt jdbcTmplt=reh.getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		sql="select message_no from bos_t_email_receive where user_id=#";
		List list=null;
		try {
			sql=MakeUpUtil.makeUp(userId, sql);
			list=queryTmplt.iterator(sql);
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		if(list==null) return map;
		for(int i=0;i<list.size();i++){
			Object[] obj=(Object[])list.get(i);
			map.put(String.valueOf(obj[0]), obj);
		}
		return map;
	}
	public String saveUserSendMail(){
		String sql="";
		String userId="";
		String sender="";
		String content=reh.get("content");
		String capacity="";
		Object[] obj=null;
		JdbcTmplt jdbcTmplt=reh.getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();	
		Transaction tx=null;
		try {
			capacity=String.valueOf((content.getBytes()).length);			
			tx=jdbcTmplt.beginTransaction();
			sql="select account_name from bos_t_email_account where user_id=#";
			sql=MakeUpUtil.makeUp(userId, sql);		
			obj=queryTmplt.getArray(sql);
			if(obj!=null) sender=String.valueOf(obj[0]);
			String id=String.valueOf(queryTmplt.getID("bos_t_email_send", "id"));
			sql="insert into bos_t_email_send(id,user_id,sender,receiver,subject,content,capacity,result) " +
			 "values(#,#,@,@,@,@,#,#)";
			String[] arr={id,userId,sender,reh.get("receiver"),reh.get("subject"),"empty_clob()",capacity,"1"};
			sql=MakeUpUtil.makeUp(arr, sql);	
			jdbcTmplt.execute(sql);
			sql="select content from bos_t_email_send where id=# for update";
			sql=MakeUpUtil.makeUp(id, sql);
			jdbcTmplt.updateClob(sql, "content",content);	
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
			return "操作失败";
		}				
		return "成功";
	}
	public void saveUserReceiveMail(ReceiveParam receive){
		String sql="";	
		JdbcTmplt jdbcTmplt=reh.getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();		
		Transaction tx=null;				
		try {
			tx=jdbcTmplt.beginTransaction();
			String id=String.valueOf(queryTmplt.getID("bos_t_email_receive", "id"));
			sql="insert into bos_t_email_receive(id,user_id,message_no,sender,receiver,subject,content,rec_time,capacity,result) " +
			 "values(#,#,@,@,@,@,@,$,#,#)";
			String[] arr={id,receive.getUserId(),receive.getMessageId(),receive.getSender(),receive.getReceiver(),receive.getSubject(),"empty_clob()",DateType.insertdbyyyymmddhhmiss(receive.getRecTime()),receive.getCapacity(),String.valueOf(receive.getResult())};
			sql=MakeUpUtil.makeUp(arr, sql);	
			jdbcTmplt.execute(sql);
			sql="select content from bos_t_email_receive where id=# for update";
			sql=MakeUpUtil.makeUp(id, sql);
			jdbcTmplt.updateClob(sql, "content", receive.getContent());	
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
		}		
	}
}
