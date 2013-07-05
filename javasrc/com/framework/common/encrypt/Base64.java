package com.framework.common.encrypt;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.framework.common.util.SysConstants;
/**
 * 功能说明:平台Base64加密类
 * @author wangyf
 * @version 1.0
 */
public class Base64 {
	
	public Base64(){
		
	}
	//编码
	public String Base64Encode(String s){
		if(s==null){
			return null;
		}else{
			 BASE64Encoder encoder = new BASE64Encoder();
			 try {
				return encoder.encode(s.getBytes(SysConstants.charset_code));
			} catch (UnsupportedEncodingException e) {			
				e.printStackTrace();
			}
		}
		return null;
	}
	//解码
	public String Base64Decode(String s){
		if(s==null){
			return null;
		}else{
			 BASE64Decoder decoder = new BASE64Decoder();
			 byte[] bt=null;
			try {
				bt = decoder.decodeBuffer(s);
			} catch (IOException e) {				
				e.printStackTrace();
			}
			 try {
				return new String(bt,SysConstants.charset_code);
			} catch (UnsupportedEncodingException e) {
			
				e.printStackTrace();
			}
		}
		return null;
	}
	public static void main(String[] args){
		Base64 base64=new Base64();
		try{			
			System.out.println(base64.Base64Encode("1"));
			System.out.println(base64.Base64Decode(base64.Base64Encode("1")));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
