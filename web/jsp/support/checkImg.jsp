<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.framework.common.file.RandomImage"%>
<%@ page import="com.framework.common.servlet.http.RequestHash" %>
<%@ page import="com.framework.common.util.SysConstants" %>



<%	
    RequestHash reh=new RequestHash(request,response);     
	RandomImage randImage=new RandomImage(reh);
	randImage.checkImage();
	if(SysConstants.runtime_server.equals("tomcat")){
		out.clear();
		out = pageContext.pushBody();
	}
	
%>