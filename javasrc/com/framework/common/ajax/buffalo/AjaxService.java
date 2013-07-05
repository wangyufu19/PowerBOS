package com.framework.common.ajax.buffalo;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import net.buffalo.request.RequestContext;

import com.framework.common.servlet.http.RequestHash;
/**
 * Ajax服务类
 * @author wangyf
 * @version 1.0
 */
public class AjaxService {
	private RequestContext requestContext;
	private RequestHash reh;
	
	public AjaxService() {
		
	}

	public void init(RequestContext requestContext) {
		this.requestContext = requestContext;
		try {
			reh = new RequestHash(requestContext.getRequest(), requestContext
					.getResponse());			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ServletContext getContext() {
		return requestContext.getContext();
	}

	public HttpServletRequest getRequest() {
		return reh.getReqeust();
	}
	public void setReh(RequestHash reh){
		this.reh=reh;
	}
	public HttpServletResponse getResponse() {
		return reh.getResponse();
	}

	public RequestHash getReh() {
		return reh;
	}

	public HttpSession getSession() {
		return reh.getSession();
	}

}
