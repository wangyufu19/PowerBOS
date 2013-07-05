package com.framework.common.servlet.filter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class SSOFilter implements Filter{
	private FilterConfig filterConfig;
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
    //Filter initialize
	public void init(FilterConfig config) throws ServletException {
		try{
			this.filterConfig=config;			
		}catch(Exception e){
			throw new ServletException("SSOFilter init Exception", e);
		}
		
	}
	//Authentication for SSO
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest)request;		
		HttpServletResponse httpResponse=(HttpServletResponse)response;		
		String reqURI=null;		
		try{			
			reqURI=httpRequest.getRequestURI();						
			if(reqURI==null){
				chain.doFilter(request, response);
				return;
			}else{					
				if(reqURI.endsWith(".sso")){
					chain.doFilter(request, response);					
					return;
				}						
				chain.doFilter(request, response);
				return;
			}
		}catch(Exception e){
			try {
                e.printStackTrace();
                httpResponse.setContentType(CONTENT_TYPE);
                PrintWriter pw = httpResponse.getWriter();
                pw.println("Server internal exception:" + e.getMessage());
                pw.println("<a href=\"javascript:window.history.back()\">[go back]</a>");
                pw.close();
                return;
            } catch (Exception ex1) {
                filterConfig.getServletContext().log(ex1.getMessage());
            }
		}
	}
	//Process filter for SSO
	public void processSSO(ServletRequest request, ServletResponse response){
		String appid=request.getParameter("appId");
		String loginname=request.getParameter("loginname");
		String password=request.getParameter("password");
		String signinUrl="";
		try {
			filterConfig.getServletContext().getRequestDispatcher(signinUrl).forward(request, response);
		} catch (ServletException e) {		
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	//Destroy filter
	public void destroy() {
		filterConfig=null;		
	}
}
