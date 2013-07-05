<%@ page contentType="text/html;charset=UTF-8" %>
<%
	String contextPath =request.getContextPath();	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-CN" />
<title>PowerBOS 3.0</title>
</head>
<body>
<br/>
对不起,您的操作已经超时,请重新登录,谢谢.
<a href="<%=contextPath%>/jsp/console/login.jsp" target="_top"><span>重新登录</span></a>
</body>
</html>