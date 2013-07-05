package com.framework.common.mail.param;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.framework.common.util.DateUtil;
import com.powerbosframework.util.StringUtil;

public class Mail {    
    private Message message;
    private String userId;
    private boolean isHTMLMail;    
    private String htmlEncodeCharset = "gbk";    
    private String text;
    public Mail(Message message){
    	this.message=message;
    }
    public Mail(Message message, boolean isHTMLMail) {
        this.message = message;
        this.isHTMLMail=isHTMLMail;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
    
    public Message getMessage() {
        return message;
    }
    public void setUserId(String userId){
    	this.userId=userId;
    }
    public String getUserId(){
    	return this.userId;
    }
    public String getMessageId(){
    	String msgId="";    	
    	try {    		
			msgId=((MimeMessage)message).getMessageID();
		} catch (MessagingException e) {		
			e.printStackTrace();
		}
    	return msgId;
    }
    public String getSendForm(){
    	String formAddr="";
    	try {
			formAddr=((InternetAddress) message.getFrom()[0]).toString();
			formAddr=StringUtil.getChineseDecode(formAddr);
		} catch (MessagingException e) {			
			e.printStackTrace();
		}
		try {
			formAddr=MimeUtility.decodeText(formAddr);
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
    	return formAddr;
    }
    public String getSendTo(){
    	String toAddr="";
    	try {
			toAddr=((InternetAddress) message.getAllRecipients()[0]).toString();
			toAddr=StringUtil.getChineseDecode(toAddr);
		} catch (MessagingException e) {		
			e.printStackTrace();
		}
		try {
			toAddr=MimeUtility.decodeText(toAddr);
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
    	return toAddr;
    }
    public String getSendDate(){
    	String senddate=null;
    	try {    		
			senddate=DateUtil.getYYYYMMDDHHMMSS(message.getSentDate());
		} catch (MessagingException e) {			
			e.printStackTrace();
		}
    	return senddate;
    }
    public String getSubject(){
    	String subject="";
    	try {    		
			subject=message.getSubject();
			subject=StringUtil.getChineseDecode(subject);
		} catch (MessagingException e) {			
			e.printStackTrace();
		}
		try {
			subject=MimeUtility.decodeText(subject);
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
    	return subject;
    }
    public String getSize(){
    	String size="";;
    	try {
			size=String.valueOf(message.getSize());
		} catch (MessagingException e) {			
			e.printStackTrace();
		}
    	return size;
    }
    public String getContent(){
    	String content="";
    	Object obj;
		try {
			obj = message.getContent();
			if(obj instanceof MimeMultipart){
				MimeMultipart mp=(MimeMultipart)obj;
				int count=mp.getCount();
				for(int j=0;j<count;j++){
					Part part  = mp.getBodyPart(j);    
					String disposition = part.getDisposition();    					
					if(disposition!=null&&((disposition.equals(part.ATTACHMENT)||disposition.equals(part.INLINE)))){
						//接收附件
					}else if(disposition==null){
				        //接收text/plain,image/gif,text/html
						MimeBodyPart mbp = (MimeBodyPart)part;						
						if(mbp.isMimeType("text/html")) {
							content=mbp.getContent().toString();								
		    			}    		    				
					}    								
				}
			}else{
				content=String.valueOf(obj);
			}
			//content=StringUtil.getChineseDecode(content);
			try {
				content=MimeUtility.decodeText(content);
			} catch (UnsupportedEncodingException e) {			
				e.printStackTrace();
			}
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (MessagingException e) {			
			e.printStackTrace();
		}    			
		return content;
    }
    public void setSendFrom(String fromAddr) throws MessagingException {
        Address addr = new InternetAddress(fromAddr);
        message.setFrom(addr);
    }
    
    public void setSendTo(String toAddr) throws MessagingException {
        Address addr = new InternetAddress(toAddr);
        message.setRecipient(Message.RecipientType.TO, addr);
    }
    
    public void setSendTo(String[] toAddrs) throws MessagingException {
        Address[] addrs = new Address[toAddrs.length];
        for(int i=0; i<toAddrs.length; i++) {
            addrs[i] = new InternetAddress(toAddrs[i]);
        }
        message.setRecipients(Message.RecipientType.TO, addrs);
    }
    
    public void addSendTo(String toAddr) throws MessagingException {
        Address addr = new InternetAddress(toAddr);
        message.addRecipient(Message.RecipientType.TO, addr);
    }
    
    public void addSendTo(String[] toAddrs) throws MessagingException {
        Address[] addrs = new Address[toAddrs.length];
        for(int i=0; i<toAddrs.length; i++) {
            addrs[i] = new InternetAddress(toAddrs[i]);
        }
        message.addRecipients(Message.RecipientType.TO, addrs);
    }
    
    public void addSendCC(String ccAddr) throws MessagingException {
        Address addr = new InternetAddress(ccAddr);
        message.addRecipient(Message.RecipientType.CC, addr);
    }
    
    public void addSendBCC(String bccAddr) throws MessagingException {
        Address addr = new InternetAddress(bccAddr);
        message.addRecipient(Message.RecipientType.BCC, addr);
    }
    
    public void setSubject(String subject) throws MessagingException {
        message.setSubject(subject);
    }
    
    public void setSendDate(Date date) throws MessagingException {
        message.setSentDate(date);
    }
    
    public void setContent(String text) throws MessagingException {
        if(text != null && !text.equals("")) {
            this.text = text;
            if (isHTMLMail)
                message.setContent(text, "text/html;charset=" + htmlEncodeCharset);
            else
                message.setText(text);
        }
    }
    
    public void appendContent(String text) throws MessagingException {
        if(text != null && !text.equals("")) {
            this.text += text;
            if (isHTMLMail) {
                message.setContent(this.text, "text/html;charset=" + htmlEncodeCharset);
            }
            else
                message.setText(this.text);
        }
    }
   
    public String getContent(Part msg) throws MessagingException, IOException {
        Object content = msg.getContent();
        String contentStr = "";

        if (content instanceof String) {
            contentStr = (String) content;
        }

        if (content instanceof java.io.InputStream) {
            contentStr = content.toString();
        }

        return contentStr;
    }
    
    public static int getMailSize(Message msg) throws MessagingException, IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        msg.writeTo(bos);
        bos.flush();
        bos.close();
        return bos.toByteArray().length;
    }
}
