package com.framework.common.mail;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.framework.common.mail.param.Mail;
import com.framework.common.mail.param.MailParam;

public class MailService {
    private MailClient client;

    private List<Mail> mails = new ArrayList<Mail>();    
 
    public MailService(){
    	
    }
    public MailService(MailParam mailParm) {           
        client = new MailClient(mailParm);
    }  
   
    public Mail newMail(boolean isHTMLMail) {
        MimeMessage msg = new MimeMessage(client.getSession());
        Mail newMail = new Mail(msg, isHTMLMail);
        mails.add(newMail);
        return newMail;
    }
    
    public void send(Mail mail) throws MessagingException {    	
        client.sendMail(mail);
    }
    
    public void sendMails() throws MessagingException {
        client.sendMail(this.mails);
    }
    public List receive() throws MessagingException{    	
    	 return client.receiveMail();    	
    }


    public static void main(String[] args) throws MessagingException, IOException{
    	MailService service=new MailService();
    	Mail mail=service.newMail(false);
    	mail.setSendFrom("wangyufu19@163.com");
    	mail.setSendTo("wangyf@pominfo.cn");
    	mail.setSubject("测试邮件");
    	mail.setContent("测试内容");
    	service.send(mail);
    	//service.receiveMail();
    }
}
