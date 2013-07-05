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
    String code=reh.get("code");
%>
<html>
<head>
<title><%=SysConstants.app_name%></title>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=contextPath%>/lib/default/css/global.css" type="text/css"/>
<link rel="stylesheet" href="<%=contextPath%>/lib/default/css/table.css" type="text/css"/>
<script language=Javascript src="<%=contextPath%>/lib/default/js/common.js"></script>
<script language=Javascript src="<%=contextPath%>/lib/default/js/general.js"></script>

</head> 
<frameset cols="150,*" framespacing="2" frameborder="yes" border="0" bordercolor="#EFEFEF">
  <frame src="<%=contextPath%>/jsp/support/dtree.jsp?code=<%=code%>" scrolling="auto" frameborder="0" name="dtree" id="dtree"  />
  <frame src="" name="dtable" id="dtable" frameborder="0"/>
</frameset>
<noframes>
<body>

</body>
</noframes>  
</html>


