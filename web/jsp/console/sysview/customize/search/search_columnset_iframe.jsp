<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/controller.tld" prefix="s"%>
<%
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0);
%>
<%
	String contextPath=request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//Dtd HTML 4.01 Transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=contextPath%>/lib/console/css/css.css" type="text/css"/>
</head>

<body>    
    <div id="iframe1_content"><iframe id="iframe1" src="<%=contextPath%>/jsp/console/getSearchColumnSetList.action?resourceName=<s:write property="resourceName"/>&pageKey=<s:write property="pageKey"/>&pageCode=<s:write property="pageCode"/>&columnSetKey=<s:write property="columnSetKey"/>" frameborder="0"></iframe></div>
	<div id="iframe2_content"><iframe id="iframe2" src="" frameborder="0"></iframe></div>   
</body>
</html>
