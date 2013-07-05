package com.framework.common.ajax.buffalo;
import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import net.buffalo.request.*;
import net.buffalo.service.DefaultServiceRepository;
import net.buffalo.service.ServiceRepository;
import net.buffalo.web.RequestUtils;

import com.application.support.action.LoginService;
import com.framework.common.servlet.http.RequestHash;

public class AjaxApplicationServlet extends HttpServlet {		
	private static final long serialVersionUID = 1L;
	public AjaxApplicationServlet() {
		
	}
	public String getServletInfo() {
		return "Ajax Application Gateway Servlet";
	}
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		initServiceRepository();
	}
	private void initServiceRepository() {
		if (getServletContext().getAttribute(ServiceRepository.WEB_CONTEXT_KEY) == null) {			
			ServiceRepository repository = new DefaultServiceRepository(
					getServletContext());
			getServletContext().setAttribute(ServiceRepository.WEB_CONTEXT_KEY,
					repository);
		}
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	protected void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestContext context = new RequestContext(getServletContext(),
				request, response);
//		LoginService loginService=new LoginService();
//		RequestHash reh=new RequestHash(request,response);
//		loginService.setReh(reh);
//		loginService.login(reh.get("username"), reh.get("password"), null);
		String pathInfo = request.getPathInfo();			
		RequestWorker worker = null;	
		if (pathInfo.startsWith("/buffalo")){
			worker = new RequestWorkerImpl();
		}else if(pathInfo.startsWith("/ajaxapp")){
			worker=new RequestWorkerImpl();
		}else
			throw new ServletException("Cannot find the request worker!");
		worker.initRequest(context);
		try {
			worker.validate();
		} catch (ValidationException ex) {
			throw new ServletException("Service validation error", ex);
		}
		worker.processRequest();
	}

	protected Locale getLocaleFromRequest(RequestContext context)
			throws ServletException {
		Cookie cookie = context.getCookie("com.eNets.framework.ajax.locale");
		if (cookie != null)
			return RequestUtils.getLocale(cookie.getValue());
		else
			return context.getRequest().getLocale();
	}

}
