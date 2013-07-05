package com.controller.forward;
import java.io.IOException;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.controller.exception.ActionException;
import com.controller.forward.ActionForward;

/**
 * 请求转发器
 * @author wangyf
 * @version 1.0
 */
public class Dispatcher {
	
	private HttpServletRequest httpRequest;
	private HttpServletResponse httpResponse;
	private static Log log;
	static Class com$controller$forward$Dispatcher;
	
	public Dispatcher(HttpServletRequest httpRequest,HttpServletResponse httpResponse){
		this.httpRequest=httpRequest;
		this.httpResponse=httpResponse;		
	}
	/**
     * 处理全局转发
     * @param httpservletrequest
     * @param httpservletresponse
     * @param s
     * @throws IOException
     * @throws ServletException
     * @throws ActionException
     */
    public void processGlobalForward(GlobalForward globalforward,String s) throws IOException, ServletException, ActionException{
       	if(globalforward==null){
    		String error="The requested resource "+s+" is not available";
    		httpResponse.sendError(404,error);    	
    		return;
    	}    	
    	String s1=globalforward.getValue();    	    	
    	this.doForward(s1);
    }
    /**
     * 处理action局部转发
     * @param actionforward
     * @throws IOException
     * @throws ServletException
     */
	public void processForwardConfig(ActionForward actionforward) throws IOException, ServletException{
		if(actionforward==null){
    		return;
    	}
    	if(log.isDebugEnabled())
            log.debug("processForwardConfig(" + actionforward + ")");
    	String s=actionforward.getValue();
    	String s1=null;
    	if(s.startsWith("/")){
    		s1 =s;
    	}else
    		s1="/"+s;    	
    	doForward(s1);
	}
	 /**
     * 执行转发
     * @param s
     * @param httpservletrequest
     * @param httpservletresponse
     * @throws IOException
     * @throws ServletException
     */
    protected void doForward(String s) throws IOException, ServletException{
    	RequestDispatcher requestdispatcher = httpRequest.getRequestDispatcher(s);
        if(requestdispatcher == null){
        	httpResponse.sendError(500, s);
            return;
        } else{
            requestdispatcher.forward(httpRequest, httpResponse);
            return;
        }
    }
    
	static Class class$(String s){
		try {
			return Class.forName(s);
		} catch (ClassNotFoundException classnotfoundexception) {			
			throw new NoClassDefFoundError(classnotfoundexception.getMessage());
		}
	}
	
	static{
		log=LogFactory.getLog(com$controller$forward$Dispatcher!=null?com$controller$forward$Dispatcher:(com$controller$forward$Dispatcher=class$("com.controller.forward.Dispatcher")));
	}

}
