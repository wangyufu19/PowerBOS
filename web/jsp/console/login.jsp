<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.framework.common.util.SysConstants;"%>
<% 	
	String contextPath=request.getContextPath();	
	String username="";
	String password="";
	if(SysConstants.runtime_mode.equals("true")){
		username="admin";
		password="111111";
	}	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=SysConstants.product_name%><%=SysConstants.version_num%></title>
<link rel="stylesheet" href="<%=contextPath%>/lib/console/css/css.css" type="text/css"/>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/jquery/jquery-1.5.1.min.js"></script>
</head>

<body id="login">
	<div class="logForm">
		<dl class="logInner">
			<dt><div class="titlel"></div></dt>
			
			<dd>
				<label>用户名：</label>
				<input type="text" class="text" name="username" id="username" value="<%=username%>"/>
			</dd>
			<dd>
				<label>密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
				<input type="password" class="text" name="password" id="password" value="<%=password%>"/>
			</dd>
			<%
			if("true".equals(SysConstants.check_code)){
			%>
			<dd>
				<label>验证码：</label>
				<input type="text" class="text1" name="checkCode" id="checkCode" size="10"/>
				<img alt="验证码" id="checkCodeImg" src="<%=contextPath%>/showCheck.action" onclick="javascript:changeImg_f();"/>
			</dd>
			<%
			}
			%>
			<dd class="btn">
				<button class="button" onclick="javascript:login_f();">登录</button>
			</dd>
		</dl>
	</div>
	
<script language="JavaScript">
window.onload=function() {	
	if(typeof(document.getElementById("username"))!="undefined")
		document.getElementById("username").focus();
}
function login_f(){
	var username=document.getElementById("username").value;
	var password=document.getElementById("password").value;
	var check="";
	if(typeof(document.getElementById("checkCode"))!="undefined" &&  document.getElementById("checkCode")!=null){
		check=document.getElementById("checkCode").value;
	}			
	$.ajax({
	    type:"post",
	    url:"<%=contextPath%>/jsp/console/login.action",
	    contentType:"application/x-www-form-urlencoded; charset=utf-8",
	    data:({username:username,password:password}),
	    success:function(msg){
	   		if(msg!="成功"){
	   			alert(msg);
	   			if(typeof(document.getElementById("checkCode"))!="undefined" &&  document.getElementById("checkCode")!=null){
					document.getElementById("checkCodeImg").src="<%=contextPath%>/jsp/console/checkImg.jsp";
					document.getElementById("checkCode").value="";
				}		
				return;
	   		}
	   		window.location.href="<%=contextPath%>/jsp/console/index.jsp";
	    }
	});		
}
function reset_f(){
	document.getElementById("username").value="";
	document.getElementById("password").value="";
	if(typeof(document.getElementById("checkCode"))!="undefined" &&  document.getElementById("checkCode")!=null){
		document.getElementById("checkCode").value="";
	}
}


function changeImg_f(){
	document.getElementById("checkCodeImg").src="<%=contextPath%>/jsp/console/checkImg.jsp";
}
</script>
</body>
</html>