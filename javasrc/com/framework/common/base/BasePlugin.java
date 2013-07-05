package com.framework.common.base;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.framework.common.servlet.http.RequestHash;

public class BasePlugin {
	protected RequestHash reh;
	
	public BasePlugin(){
		
	}
	public BasePlugin(RequestHash reh){
		this.reh=reh;
	}
	public void setReh(RequestHash reh){
		this.reh=reh;
	}
	public RequestHash getReh(){
		return this.reh;
	}
	public String logException(Exception e){
		String message="";	
		return message;
	}
	public String getReturn(String ret){	
		if(ret==null){
			return "";
		}		
		return ret;
	}
	public HttpServletRequest getRequest(){
		return reh.getReqeust();
	}
	public HttpServletResponse getResponse(){
		return reh.getResponse();
	}
	public HttpSession getHttpSession(){
		return reh.getSession();
	}

}
