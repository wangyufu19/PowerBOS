package com.framework.common.mail;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;

import com.framework.common.mail.param.Mail;
import com.framework.common.mail.param.MailParam;
import com.framework.common.mail.param.ReceiveParam;
import com.framework.common.util.DateUtil;
import com.framework.common.util.SysConstants;
import com.powerbosframework.util.StringUtil;

public class MailClient extends Authenticator{	
	private String userId;
    private int storePort;
    private int transportPort;
    private Session session;
    private PasswordAuthentication authentication;    
    public MailClient(){
    	
    }
  
    public MailClient(MailParam mailParam) {       	    	
        this.storePort = mailParam.getStorePort();
        this.transportPort = mailParam.getTransportPort();          
        this.userId=String.valueOf(mailParam.getUserId());
        authentication = new PasswordAuthentication(mailParam.getAccount(), mailParam.getPassword());      
        Properties props = new Properties();           
        props.put("mail.pop3", StringUtil.replaceNull(mailParam.getPop3()));      
        props.put("mail.smtp", StringUtil.replaceNull(mailParam.getSmtp()));
        props.put("mail.smtp.auth", "true");       
        props.put("mail.store.protocol", StringUtil.replaceNull(mailParam.getStoreProtocol()));
        props.put("mail.transport.protocol", StringUtil.replaceNull(mailParam.getTransportProtocol()));
        props.put("mail.mime.charset", SysConstants.charset_code);        
        session = Session.getInstance(props, this);
        session.setDebug(false);           
//        System.out.println("******storePort="+mailParam.getStorePort());
//        System.out.println("******transportPort="+mailParam.getTransportPort());
//        System.out.println("******account="+mailParam.getAccount());
//        System.out.println("******password="+mailParam.getPassword());
//        System.out.println("******mail.pop3="+mailParam.getPop3());
//        System.out.println("******mail.smtp="+mailParam.getSmtp());
//        System.out.println("******mail.store.protocol="+mailParam.getStoreProtocol());
//        System.out.println("******mail.transport.protocol="+mailParam.getTransportProtocol());
        
    }
    
    public Session getSession() {
        return session;
    }
    public void setSession(Session session) {
        this.session = session;
    }
   
    public PasswordAuthentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(PasswordAuthentication authentication) {
        this.authentication = authentication;
    }

    public int getStorePort() {
        return storePort;
    }

    public void setStorePort(int storePort) {
        this.storePort = storePort;
    }

    public int getTransportPort() {
        return transportPort;
    }

    public void setTransportPort(int transportPort) {
        this.transportPort = transportPort;
    }
    
    public void sendMail(Mail mail) throws MessagingException {    	
        mail.getMessage().setSentDate(DateUtil.getCurrentDate());
        Transport trans = null;    	
        trans = getTransport();               
        trans.sendMessage(mail.getMessage(), mail.getMessage().getAllRecipients());   
        trans.close();        
    }
    
    public void sendMail(List mails) throws MessagingException {
        Transport trans = null;
        try {
            trans = getTransport();
            for(Iterator i=mails.iterator(); i.hasNext();) {
                Mail mail = (Mail)i.next();
                mail.getMessage().setSentDate(DateUtil.getCurrentDate());
                trans.sendMessage(mail.getMessage(), mail.getMessage().getAllRecipients());
            }
        } catch (MessagingException ex) {
            ex.printStackTrace();;
        } finally {
            trans.close();
        }
    }
    
    public List receiveMail() throws MessagingException{    
    	List<ReceiveParam> list=new ArrayList<ReceiveParam>();  	
    	Store store =null;     		    		
		store=this.getStore();      		
		store.connect();    	    		
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_WRITE);    		
		Message[] message =folder.getMessages();
		Flags flags = new Flags(Flags.Flag.DELETED); //设置为下次不收    	    		
		for(int i=0;i<message.length;i++){    	 
			ReceiveParam receive=new ReceiveParam();
			Mail mail=new Mail(message[i]);    			
 			mail.setUserId(userId);     

 			System.out.println("******messageNo="+mail.getMessageId());
 			System.out.println("******sender="+mail.getSendForm());
 			System.out.println("******receiver="+mail.getSendTo());
 			System.out.println("******subject="+mail.getSubject());
 			System.out.println("******content="+mail.getContent());
 			System.out.println("******recTime="+mail.getSendDate());
 			System.out.println("******capacity="+mail.getSize());
			receive.setUserId(mail.getUserId());
			receive.setMessageId(mail.getMessageId());
			receive.setSender(StringUtil.getSubString(mail.getSendForm(), '<', '>'));
			receive.setReceiver(mail.getSendTo());
			receive.setSubject(mail.getSubject());
			receive.setContent(mail.getContent());
			receive.setRecTime(mail.getSendDate());
			receive.setCapacity(mail.getSize());
			receive.setResult(Long.parseLong("1"));
 			list.add(receive);
 			if(i>=1) break;
 			message[i].setFlags(flags, true);
			
		}       		
		folder.close(true);
		store.close();
    	return list;
    }
    public Store getStore()throws MessagingException{
    	 /* 接收邮件需要服务器验证 */
        URLName urlName = new URLName(session.getProperty("mail.store.protocol"),
                                      session.getProperty("mail.pop3"), 
                                      storePort, 
                                      null, 
                                      authentication.getUserName(), 
                                      authentication.getPassword());     
        System.out.println("******mail.store.protocol: "+session.getProperty("mail.store.protocol"));
        System.out.println("******mail.pop3: "+session.getProperty("mail.pop3"));
        System.out.println("******storePort: "+storePort);
        System.out.println("******username: "+authentication.getUserName());
        System.out.println("******password: "+authentication.getPassword());
        Store store = session.getStore(urlName);          
        return store;
    }
    private Transport getTransport() throws MessagingException {
    	
        /* 发送邮件需要服务器验证 */
        URLName urlName = new URLName(session.getProperty("mail.transport.protocol"),
                                      session.getProperty("mail.smtp"), 
                                      transportPort, 
                                      null, 
                                      authentication.getUserName(), 
                                      authentication.getPassword());
     
        Transport trans = session.getTransport(urlName);
        System.out.println("******mail.transport.protocol: "+session.getProperty("mail.transport.protocol"));
        System.out.println("******mail.smtpl: "+session.getProperty("mail.smtp"));
        System.out.println("******transportPort: "+transportPort);
        System.out.println("******username: "+authentication.getUserName());
        System.out.println("******password: "+authentication.getPassword());
        trans.connect();
        return trans;
    }
}

