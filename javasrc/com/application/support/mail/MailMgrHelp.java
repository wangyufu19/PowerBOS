package com.application.support.mail;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import javax.mail.MessagingException;
import com.framework.common.mail.MailService;
import com.framework.common.mail.param.Mail;
import com.framework.common.mail.param.MailParam;
import com.framework.common.mail.param.ReceiveParam;
import com.framework.common.servlet.http.RequestHash;
import com.framework.common.util.SysConstants;
/**
 * 功能说明:邮件管理插件类
 * @author wangyf
 * @version 1.0
 */
public class MailMgrHelp{
	private RequestHash reh;
	
	public MailMgrHelp(RequestHash reh){
		this.reh=reh;
	}
	/**
	 * 接收邮件
	 * @return
	 */
	public String receive(){		
		MailMgrBiz biz=new MailMgrBiz();
		biz.setReh(reh);				
		MailParam param=new MailParam();
		String userId="";
		Object[] obj=null;
		obj = biz.getUserMailAccount(userId);		
		if(obj!=null){
			if(String.valueOf(obj[4])!=null&&!"".equals(String.valueOf(obj[4]))&&!"null".equals(String.valueOf(obj[4])))
				param.setStorePort(Integer.parseInt(String.valueOf(obj[4])));
			param.setPop3(String.valueOf(obj[2]));
			param.setAccount(String.valueOf(obj[0]));
			param.setPassword(String.valueOf(obj[1]));
			param.setUserId(userId);		
		}			
		MailService service=new MailService(param);		
		try {
			Map map= biz.getUserExsitMail(userId);
			List list=service.receive();
			if(list==null) list=new ArrayList();
			for(int i=0;i<list.size();i++){
				ReceiveParam receive=(ReceiveParam)list.get(i);
				if(map.containsKey(receive.getMessageId())){
					continue;
				}
				biz.saveUserReceiveMail(receive);
			}
			
		} catch (MessagingException e) {		
			e.printStackTrace();
			if(e.toString().indexOf("javax.mail.NoSuchProviderException")!=-1){    		    			
    			return "对不起,系统没有设置pop3协议!";
    		}else if(e.toString().indexOf("javax.mail.AuthenticationFailedException")!=-1){
    			return "对不起,用户邮箱验证失败,请确认您的邮箱绑定是否正确,谢谢!";
    		}else if(e.toString().indexOf("Connect failed")!=-1){
    			return "对不起,邮箱连接失败,您的账号或密码不正确!";
    		}			
			return SysConstants.exce_failed;
		} 	
		return SysConstants.exce_succeed;
	}
	/**
	 * 发送邮件
	 * @return
	 */
	public String send(){		
		String userId="";
		MailMgrBiz biz=new MailMgrBiz();
		biz.setReh(reh);	
		MailParam param=new MailParam();		
		Object[] obj=null;	
		obj = biz.getUserMailAccount(userId);		
		if(obj!=null){
			if(String.valueOf(obj[5])!=null&&!"".equals(String.valueOf(obj[5]))&&!"null".equals(String.valueOf(obj[5])))
				param.setTransportPort(Integer.parseInt(String.valueOf(obj[5])));
			param.setSmtp(String.valueOf(obj[3]));
			param.setAccount(String.valueOf(obj[0]));
			param.setPassword(String.valueOf(obj[1]));
			param.setUserId(userId);		
		}	
		try {			
			MailService service=new MailService(param);				
			Mail newMail=service.newMail(true);				
			String fromAddr=String.valueOf(obj[0]);
			String toAddr=reh.get("receiver");
			String subject=reh.get("subject");
			String content=reh.get("content");		
			newMail.setSendFrom(fromAddr);
			newMail.setSendTo(toAddr);
			newMail.setSubject(subject);
			newMail.setContent(content);				
			service.send(newMail);
			
		} catch (MessagingException e) {			
			e.printStackTrace();
			if(e.toString().indexOf("Unknown SMTP host")!=-1){
				return "对不起,连接失败,请确定您的网络是否正常!";
			}else if(e.toString().indexOf("javax.mail.NoSuchProviderException")!=-1){    		    			
    			return "对不起,系统没有设置pop3协议!";
    		}else if(e.toString().indexOf("javax.mail.AuthenticationFailedException")!=-1){
    			return "对不起,用户邮箱验证失败,请您维护邮件管理功能的参数设定,谢谢!";
    		}else if(e.toString().indexOf("Connect failed")!=-1){
    			return "对不起,邮箱连接失败,您的账号或密码不正确!";
    		}else if(e.toString().indexOf("javax.mail.SendFailedException: Invalid Addresses")!=-1){	    			
    			return "对不起,收件人邮箱地址不正确";
    		}    			
			return SysConstants.exce_failed;
		}					
		return SysConstants.exce_succeed;
	}
	public String saveMail(){
		String msg="成功";
		MailMgrBiz biz=new MailMgrBiz();
		biz.setReh(reh);	
		msg=biz.saveUserSendMail();
		return msg;
	}
	

}
