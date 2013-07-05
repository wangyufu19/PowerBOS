package com;
import java.math.BigDecimal;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
public class Test {

	public static void main(String[] args){
		
		String host="114.112.173.104";
		int port=5222;
		String username="youfu.wang";
		String password="1234";
		ConnectionConfiguration config=new ConnectionConfiguration(host,port);
		config.setCompressionEnabled(true);
		config.setSASLAuthenticationEnabled(true);
		XMPPConnection cnn=new XMPPConnection(config);
		
		try {
			cnn.connect();
			cnn.login(username, password);
			while(true){
				;
			}
		} catch (XMPPException e) {			
			e.printStackTrace();
		}
//		finally{
//			cnn.disconnect();
//		}
		
	}
	

}