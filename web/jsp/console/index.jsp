<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.framework.common.util.SysConstants"%>
<%@ page import="com.framework.common.servlet.http.RequestHash"%>

<% 
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
%>

<% 
	String contextPath=request.getContextPath();	
	RequestHash reh=new RequestHash(request,response);	
	Map user=(Map)reh.getSession().getAttribute(reh.getSession().getId());
	String username="";
	if(user!=null) username=String.valueOf(user.get("console.username"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=SysConstants.product_name%><%=SysConstants.version_num%></title>
<link rel="stylesheet" href="<%=contextPath%>/lib/console/css/css.css" type="text/css"/>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/jquery/jquery-1.5.1.min.js"></script>
</head>
<body>
<div class="head"></div>
<table cellspacing="0" cellpadding="1" width="100%" border="0">
	<tr>
		<td valign="top">
		 <iframe id="sidebar" name="sidebar" src="<%=contextPath%>/jsp/console/leftMenu.jsp" width ="235" frameborder="0" onload="this.height = (document.documentElement.clientHeight-48) + 'px'" scrolling="no"></iframe>
		</td>
		<td valign="top" width="100%" class="main">
			<div class="topmenu">
				<ul>
					<li class="wel">欢迎您: <%=username%></li>
					<li class="right">
						<ul class="menuLeft">
							<li class="crumb">连接至:<b><%=SysConstants.product_name%></b></li>
							<li class="home"><a href="<%=contextPath%>/jsp/support/login.jsp" target="_blank">支撑系统</a></li> 
							<li><a href="javascript:logout_f();">退出</a></li>							
							<li><a href="#">帮助</a></li>							
						</ul>
					</li>
				</ul>
			</div>
			<iframe id="mainboard" name="mainboard" class="mainContent" src="" frameborder="0" width="100%" onload="this.height = (document.documentElement.clientHeight) + 'px'" scrolling="yes"></iframe>
		</td>
	</tr>
</table>
<script language="JavaScript" type="text/javascript"> 	
function logout_f(){
	$.ajax({
		type:"post",
		url:"<%=contextPath%>/jsp/console/logout.action",
		success:function(msg){
			if(msg!="成功"){
   				alert(msg);
   				return;
   			}	
   			window.location.href="<%=contextPath%>/jsp/console/login.jsp";		
		}
	});
}		
</script>
</body>
</html>