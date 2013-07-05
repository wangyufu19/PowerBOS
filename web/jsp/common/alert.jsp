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
	String msg=reh.get("msg");    	
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

<div id="info" class="popup" style="display:;">
	<div class="closer" onclick="hideMe();" title="关闭"></div>
	<div id="titleBar" class="title">提示信息</div>
	<div class="content"><%=msg%></div>
	<div class="action"><a href="javascript:hideMe();" class="btn-normal"><span>关闭</span></a></div>
</div>





<script language="JavaScript">
<!--
showAtThisElement(document.body,"BC","info");
//-->
</script>


</body>
</html>