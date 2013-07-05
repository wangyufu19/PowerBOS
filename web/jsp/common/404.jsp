<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.framework.common.util.SysConstants"%>
<%
	String contextPath =request.getContextPath();    

    
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-CN" />
<title><%=SysConstants.app_name%></title>
<link rel="stylesheet" href="<%=contextPath%>/lib/default/css/css.css" type="text/css"/>
<script language=Javascript src="<%=contextPath%>/lib/default/js/common.js"></script>
<script language="Javascript" src="<%=contextPath%>/lib/default/js/floatinglayer.js"></script>
</head>
<body>
<br/><br/><br/><br/><br/><br/><br/>

<div id="error" class="popup">
	<div class="closer" onclick="hideMe();" title="关闭"></div>
	<div id="titleBar" class="title">页面错误</div>
	<div class="content">对不起，您访问的页面不存在，请联系系统管理员<br/></div>	
</div>
<script language="JavaScript">
<!--
showAtThisElement(document.body,"BC","error");
//-->
</script>
</body>
</html>