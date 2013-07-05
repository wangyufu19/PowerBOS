<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.framework.common.servlet.http.RequestHash"%>
<%@ page import="com.framework.common.util.SysConstants"%>
<% 
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
%>

<%
	String contextPath =request.getContextPath();	
    RequestHash reh=new RequestHash(request,response);

%>

<html>

<head>
<title><%=SysConstants.app_name%></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=contextPath%>/lib/default/css/dtree.css" rel="stylesheet" type="text/css">
<script language="javascript" src="<%=contextPath%>/lib/default/js/dtree.js"></script>
</head>
<body>

<script type="text/javascript" language="JavaScript">
	
</script>
</body>
</html>

