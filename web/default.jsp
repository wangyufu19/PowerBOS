<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String contextPath=request.getContextPath();
	String url=contextPath+"/jsp/support/login.jsp";	
	response.sendRedirect(url);
%>
