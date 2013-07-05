package com.framework.common.servlet.http;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Enumeration;
import javax.servlet.http.*;
import com.framework.common.file.Upload;
import com.framework.common.util.SysConstants;
import com.powerbosframework.util.StringUtil;

/**
 * 功能说明:HTTP请求类
 * @author wangyf
 * @version 1.0
 */
public class ReqAndResHttp{	
	private HttpServletRequest httpServletRequest;
	private HttpServletResponse httpServletResponse;
	private Map<String, String[]> requestHash=new HashMap<String, String[]>();
	private Map<String, String> paramHash=new HashMap<String, String>();	
	private String jqueryParameterSerialize="";
	private boolean isUpload=false;
    private boolean encode=false;    

	public ReqAndResHttp(HttpServletRequest httpServletRequest){
		this.httpServletRequest=httpServletRequest;
	}
	public ReqAndResHttp(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
		this.httpServletRequest = httpServletRequest;
		this.httpServletResponse = httpServletResponse;	
		SysConstants.context_path=httpServletRequest.getContextPath();
		String contentType = this.httpServletRequest.getContentType();	
		if (contentType != null) {			
			if (contentType.indexOf("multipart/form-data") != -1) {
				isUpload = true;
			}					
		}			
		initEnumeration(httpServletRequest);
	}    
	public ReqAndResHttp(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,boolean encode){
		this.httpServletRequest = httpServletRequest;
		this.httpServletResponse = httpServletResponse;	
		this.encode=encode;
		SysConstants.context_path=httpServletRequest.getContextPath();
		String contentType = this.httpServletRequest.getContentType();	
		if (contentType != null) {			
			if (contentType.indexOf("multipart/form-data") != -1) {
				isUpload = true;
			}					
		}			
		initEnumeration(httpServletRequest);
	}   
    /**
     * 返回请求
     * @return httpServletResponse
     */
	public HttpServletResponse getHttpServletResponse(){
		return httpServletResponse;
	}
    /**
     * 返回发送
     * @return httpServletRequest
     */
	public HttpServletRequest getHttpServletRequest(){
		return httpServletRequest;
	}
    /**
     * 返回请求参加值
     * @param key
     * @return value
     */
	public String getParameter(String key){
		String value = "";
		value = httpServletRequest.getParameter(key);
		return value;
	}	
   
    /**
     * 返回页面请求值
     * @param key
     * @return value
     */
	public String get(String key){
		String value = "";
		if (key == null || key.equals(""))
			return "";
		Object obj = requestHash.get(key);
		if (obj == null)
			return value;
		if (obj instanceof String)
			value = (String)obj;
		if (obj instanceof String[])
		{
			String arr[] = (String[])obj;
			if (arr.length > 0)
				value = arr[0];
		}
		value = StringUtil.replaceNull(value);
		return value;
	}
    /**
     * 返回页面请求数组值
     * @param key
     * @return valueArray
     */
	public String[] getArray(String key){
		String valueArray[] = (String[])null;
		Object obj = requestHash.get(key);
		if (obj == null)
			return null;
		if (obj instanceof String){
			valueArray = new String[1];
			valueArray[0] = (String)obj;
		}
		if (obj instanceof String[]){
			valueArray = (String[])obj;
			if (valueArray.length > 0){
				for (int i = 0; i < valueArray.length; i++)
					valueArray[i] = valueArray[i];
			}
		}
		valueArray = StringUtil.replaceNull(valueArray);
		return valueArray;
	}
	/**
	 * 返回JQUERY参数序列
	 * @return
	 */
	public String getJqueryParameterSerialize(){
		return this.jqueryParameterSerialize;
	}
    /**
     * 返回页面请求参数Hash
     * @return paramHash
     */
	public Map getParamHash(){
		return this.paramHash;
	}   
	/**
	 * 返回页面请求Hash
	 * @return
	 */
	public Map getRequestHash(){
		return this.requestHash;
	}
    /**
     * 初始页面请求参数
     * @param req
     */
	public void initEnumeration(HttpServletRequest req){	
		String method=req.getMethod().toLowerCase();		
		//文件表单
		if(isUpload){			
			Upload upload=new Upload();
			requestHash=upload.doUpload(httpServletRequest);		
			for(Iterator it=requestHash.keySet().iterator();it.hasNext();){
				String name=it.next().toString();
				paramHash.put(name, name);
			}			
		}else{			
			//GET表单
			if("get".equals(method)){
				int index=0;
				StringBuilder buf=new StringBuilder();
				for (Enumeration enu = req.getParameterNames(); enu.hasMoreElements();){
					index++;					
					String name = (String)enu.nextElement();					
					paramHash.put(name, name);						
					String ret[] = req.getParameterValues(name);
					
					if(encode){
						ret[0]=StringUtil.getGetEncode(ret[0]);						
					}
					
					requestHash.put(name, ret);	
					if(index==1){
						buf.append(name+"="+ret[0]);						
					}else{
						buf.append("&"+name+"="+ret[0]);						
					}					
				}
				this.jqueryParameterSerialize=buf.toString();
			}
			//POST表单	
			if("post".equals(method)){
				int index=0;
				StringBuilder buf=new StringBuilder();
				for (Enumeration enu = req.getParameterNames(); enu.hasMoreElements();){
					String name = (String)enu.nextElement();
					paramHash.put(name, name);							
					String ret[] = req.getParameterValues(name);				
					if (ret != null){
						if (ret.length == 1){					
							if(encode)
								ret[0]=StringUtil.getPostEncode(ret[0]);								
							requestHash.put(name, ret);			
							if(index==1){
								buf.append(name+"="+ret[0]);						
							}else{
								buf.append("&"+name+"="+ret[0]);						
							}	
						} 
						else{
							for (int i = 0; i < ret.length; i++){
								if(encode)
									ret[i] = StringUtil.getPostEncode(ret[i]);
								if(index==1){
									buf.append(name+"="+ret[0]);						
								}else{
									buf.append("&"+name+"="+ret[0]);						
								}	
							}
							requestHash.put(name, ret);
						}
					}
				}
				this.jqueryParameterSerialize=buf.toString();
			}			
		}
	}          
}
