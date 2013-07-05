package com.framework.common.servlet.filter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.framework.common.util.SysConstants;

public class ConsoleFilter implements Filter{
	private FilterConfig filterConfig;
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
    //Filter initialize
	public void init(FilterConfig config) throws ServletException {
		try{
			this.filterConfig=config;			
		}catch(Exception e){
			throw new ServletException("BOSFilter init() Exception", e);
		}
		
	}
	//ConsoleFilter for filter page
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest)request;		
		HttpServletResponse httpResponse=(HttpServletResponse)response;		
		String reqURI=null;
		String contextPath=null;			
		try{			
			reqURI=httpRequest.getRequestURI();						
			if(reqURI==null){
				chain.doFilter(request, response);
				return;
			}else{				
				contextPath=httpRequest.getContextPath();							
				//如果请求是根URI,就直接跳过验证
				if(reqURI.equals(contextPath+"/")||reqURI.equals(contextPath)){					
					chain.doFilter(request, response);
					return;
				}
				//如果请求的是 .css,.gif,.jpg,.js等文件,直接跳过验证
				if(reqURI.endsWith("login.jsp")||reqURI.endsWith("login.action")||reqURI.endsWith("logout.action")||reqURI.endsWith("checkImg.jsp")){
					chain.doFilter(request, response);
					return;
				}	
				//如果请求URI以.jsp扩展名,就验证页面的有效性
				if(reqURI.endsWith(".jsp")||reqURI.endsWith(".action")){						
					HttpSession session=httpRequest.getSession();					
					Map user=(Map)session.getAttribute(session.getId());					
					if(user==null){
						filterConfig.getServletContext().getRequestDispatcher("/jsp/console/common/overtime.jsp").forward(request, response);
						return;
					}
					String username=String.valueOf(user.get("console.username"));
					String password=String.valueOf(user.get("console.password"));
					if(!SysConstants.auth_username.equals(username)||!SysConstants.auth_password.equals(password)){
						filterConfig.getServletContext().getRequestDispatcher("/jsp/console/common/overtime.jsp").forward(request, response);
						return;
					}								
					chain.doFilter(request, response);
					return;
				}else{					
					chain.doFilter(request, response);
					return;
				}				
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
	//Destroy filter
	public void destroy() {
		filterConfig=null;		
	}

}
