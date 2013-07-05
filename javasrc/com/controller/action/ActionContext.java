package com.controller.action;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.config.ConfigConstants;
import com.controller.util.ValueStack;

/**
 * 控制器动作上下文
 * @author wangyf
 * @version 1.0
 */
public class ActionContext {
	private ServletContext servletContext;
	private HttpServletRequest request;
	private HttpServletResponse response;	
	private Map params=new HashMap(); 
	private ValueStack vs=new ValueStack();
	
	public ActionContext(){
		
	}
	public ActionContext(HttpServletRequest request,HttpServletResponse response){
		this.request=request;
		this.response=response;		
		this.initEnumeration(request);
	}
	/**
	 * 返回HttpServletRequest对象
	 * @return
	 */
	public HttpServletRequest getRequest(){
		return request;
	}
	/**
	 * 返回HttpServletResponse对象
	 * @return
	 */
	public HttpServletResponse getResponse(){
		return response;
	}
	/**
	 * 返回Session对象
	 * @return
	 */
	public HttpSession getSession(){		
		return request.getSession();
	}
	/**
	 * 设置ServletContext
	 * @param servletContext
	 */
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	/**
	 * 返回ServletContext
	 * @return
	 */
	public ServletContext getServletContext() {
		return servletContext;
	}
	/**
	 * 返回控制器ValueStack对象
	 * @return
	 */
	public ValueStack getValueStack(){
		return vs;
	}
	/**
	 * 设置一个值到ValueStack对象
	 * @param name
	 * @param value
	 */
	public void set(String name,Object value){
		vs.set(name, value);
	}	
	 /**
     * 格式参数编码方式
     * @param value
     * @return
     */
    private String formatParameterEncoding(String value){    	
    	try {
			return value=new String(value.getBytes(ConfigConstants.controller_iso_encoding),ConfigConstants.controller_encoding);
			} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
		return value;			
    }
	private void initEnumeration(HttpServletRequest request){	
		boolean isUpload=false;
		String method=request.getMethod().toLowerCase();		
		String contentType =request.getContentType();	
		if (contentType != null) {			
			if (contentType.indexOf("multipart/form-data") != -1) {
				isUpload = true;
			}					
		}			
		//文件表单
		if(isUpload){			
			;	
		}else{			
			//GET表单
			if("get".equals(method)){
				for (Enumeration enu = request.getParameterNames(); enu.hasMoreElements();){
					String name = (String)enu.nextElement();						
					String values[] = request.getParameterValues(name);					
					params.put(name, values[0]);	
				}
			}
			//POST表单	
			if("post".equals(method)){
				for (Enumeration enu = request.getParameterNames(); enu.hasMoreElements();){
					String name = (String)enu.nextElement();							
					String values[] = request.getParameterValues(name);				
					if (values != null)
						if (values.length == 1){									
							params.put(name, values[0]);	
						} 
						else{							
							params.put(name, values);
						}
				}
			}			
		}
	}   
	public String get(String key){
		String value = "";
		if (key == null || key.equals(""))
			return "";
		Object obj = params.get(key);
		if (obj == null)
			return value;
		if (obj instanceof String)
			value = (String)obj;
		if (obj instanceof String[]){
			String arr[] = (String[])obj;
			if (arr.length > 0)
				value = arr[0];
		}
		if(value==null) return "";		
		return formatParameterEncoding(value);	
	}
	public String get(String key,boolean encode){
		String value = "";
		if (key == null || key.equals(""))
			return "";
		Object obj = params.get(key);
		if (obj == null)
			return value;
		if (obj instanceof String)
			value = (String)obj;
		if (obj instanceof String[]){
			String arr[] = (String[])obj;
			if (arr.length > 0)
				value = arr[0];
		}
		if(value==null) return "";		
		if(encode)
			value=formatParameterEncoding(value);	
		return value;
	}
	public String[] getArray(String key){
		String values[] = (String[])null;
		Object obj = params.get(key);
		if (obj == null)
			return null;
		if (obj instanceof String){
			values = new String[1];
			values[0] = formatParameterEncoding((String)obj);
		}
		if (obj instanceof String[]){
			values = (String[])obj;
			if (values.length > 0){
				for (int i = 0; i < values.length; i++)
					values[i] = formatParameterEncoding(values[i]);
			}
		}		
		return values;
	}
}
