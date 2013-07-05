package com.framework.common.mail.search;
import java.util.Date;
import javax.mail.search.SearchTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SentDateTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.SubjectTerm;
import javax.mail.Message;
import javax.mail.Folder;
import javax.mail.MessagingException;

import com.framework.common.util.DateUtil;
/**
 * 功能说明:平台邮件消息查询类
 * @author wangyf
 * @version 1.0
 */
public class MessageQuery {
	private SearchTerm searchTerm;
	public MessageQuery(){
		
	}
	public void setSubject(){
		 searchTerm = new SubjectTerm("测试邮件");  
	}
	public void setReceiveDate(){
		Date recdate=null;		
		try{
			recdate=this.getSearchDate();
			System.out.println("******recdate="+DateUtil.getYYYYMMDD(recdate));
			searchTerm=new ReceivedDateTerm(ComparisonTerm.GE,recdate);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void setSendDate(){
		Date senddate=null;	
		senddate=this.getSearchDate();
		System.out.println("******senddate="+DateUtil.getYYYYMMDD(senddate));
		searchTerm=new SentDateTerm(ComparisonTerm.NE,senddate);
		
	}
		
	public Message[] getReceiveMessage(Folder folder) throws MessagingException{
		Message[] msg=null;
		msg=folder.search(searchTerm);
		return msg;
	}
	public Date getSearchDate(){
		return new Date();
	}
	

}
