<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.framework.common.util.SysConstants"%>
<% 
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
%>

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
<meta http-equiv="Content-Language" content="zh-CN" />
<meta content="all" name="robots" />
<meta name="author" content="" />
<meta name="Copyright" content="" />
<meta name="Description" content=""/>
<meta name="Keywords" content="" />
<title><%=SysConstants.app_name%></title>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=contextPath%>/lib/default/css/login.css" type="text/css"/>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/default/js/jquery/jquery-1.5.1.min.js"></script>

</head>

<body class="login">
<div id=container>
  <div id=header>
	<div id=logo><a href="#"><img alt="Wbpmp2 Logo" src="<%=contextPath%>/lib/default/images/blank.gif" border=0/></a></div>
  </div>
  <div id=content>
	<div id=loginpanel>
	  <div class="pheader"><h5>用户登录</h5></div>
	  <div class="sepline"></div>
	  <div class="pcontent">	   
	    <form name="submitF" action="login.jsp" method="post">
		<input type="hidden" name="doWith" value="login"/>
		<input type="hidden" name="opener" value=""/>
		  <label><span>用户名：</span><input id="username" name="username" type="text" value="<%=username%>" class="input"/></label>
		  <label><span>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span><input id="password" name="password" type="password" value="<%=password%>" class="input"/></label>		  
		<%
		if("true".equals(SysConstants.check_code)){
		%>
		  <label><span>验证码：</span><input id="checkCode" name="checkCode" type="text" class="input" style="width:60px;"/>
		   <img alt="验证码" width=70 height=22 align=middle border="0" id="checkCodeImg" src="<%=contextPath%>/jsp/support/checkImg.jsp" onclick="javascript:changeImg_f();"/>
		  </label>
	    <%
		}
		%>
		   <!--  <label><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="checkbox" name="remember" value="remember" id="remember" class="checkbox"/><label for="remember">记住密码</label></label>-->
		  <label class="action">
		   
		  <a href="javascript:loginF();" id="login" name="login" class="btn-primary"><span>登 录</span></a>
		  <a href="javascript:clear_f();" id="cancel" name="cancel" class="btn-normal""><span>取 消</span></a>
		 
		  
		  </label>
		</form>
	  </div>
	  
	</div>
  </div>
  <div id=footer>
	<div id=bottommenu></div>
	<div id=copyright>版权所有 <%=SysConstants.copyright_corp%></div>
	<div id=powerdby><a href="#" target=blank><img alt="Copyright 2010 wangyf" src="<%=contextPath%>/lib/default/images/blank.gif" border=0/></a></div>
  </div>
</div>


<script language="JavaScript">
window.onload=function() {	
	if(typeof(submitF)!="undefined" && typeof(submitF.username)!="undefined"){
		$("#username").focus();
	}		
}

function loginF(){
	var loginName=$("#username").val();
	var password=$("#password").val();
	var checkCode=$("#checkCode").val();	
	if(loginName=="" || password==""){
		alert("对不起，用户名及密码不能为空！");
		$("#username").focus();
		return;
	}	
	<%
	if("true".equals(SysConstants.check_code)){
	%>
		if(checkCode==""){
			alert("验证码不能为空");
			$("#checkCode").focus();
			return;
		}
	<%
	}
	%>
	$.ajax({
	   type:"post",
	   url:"<%=contextPath%>/jsp/support/login.action",
	   contentType:"application/x-www-form-urlencoded; charset=utf-8",
	   data:({loginName:loginName,password:password,checkCode:checkCode}),
	   success:function(msg){
	  		if(msg!="成功"){
	  			alert(msg);
	  			return;
			}		
			window.location.href="<%=contextPath%>/jsp/support/index.jsp";
	   }
	});		  
}
function clear_f(){
	$("#username").val("");
	$("#password").val("");
	$("#checkCode").val("");
	$("#username").focus();
}
function changeImg_f(){
	document.getElementById("checkCodeImg").src="<%=contextPath%>/jsp/support/checkImg.jsp";
}
</script>
</body>
</html>



