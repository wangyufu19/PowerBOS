<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/controller.tld" prefix="s"%>
<%@ page import="com.controller.util.ValueStack"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="<%=contextPath%>/lib/console/css/css.css"
			type="text/css" />
		<script language="JavaScript" type="text/javascript"
			src="<%=contextPath%>/lib/console/js/jquery/jquery-1.5.1.min.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=contextPath%>/lib/console/js/layout/tab.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=contextPath%>/lib/console/js/layout/window.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=contextPath%>/lib/console/js/form/check/check.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=contextPath%>/lib/console/js/form/check/text.js"></script>
		<script language="JavaScript" type="text/javascript"
			src="<%=contextPath%>/lib/console/js/form/check/base.js"></script>

	</head>
	<body>
		<div class="container">
			<div class="box">
				<div class="tabContent">
					<ul id="tabmenu" class="tabmenu">
						<li id="tab1" class="select">
							<a href="#">系统设置</a>
						</li>
					</ul>
					<form name="submitF" action="" method="post">
						
						<div class="innerBox" id="tab1_content">
							<div class="innerBox1">
								<div class="btnt">
									<button class="btn-able"
										onclick="javascript:doWith_f(submitF,'saveSysDatabaseConfig.action','您确定要保存吗?');">
										保存
									</button>									
								</div>
								<div class="detailForm">
									<table width="100%" cellpadding="0" cellspacing="0" border="0">
										<tr class="TrEven">
											<td>
												<b>数据库:</b>
											</td>
											<td colspan="2">				
												<select id="dialect" name="dialect">
													<option value="">请选择</option>
													<option value="oracle" <%if(ValueStack.vs.get("dialect").equals("oracle")) out.write("selected");%>>oracle</option>
													<option value="mssql" <%if(ValueStack.vs.get("dialect").equals("mssql")) out.write("selected");%>>mssql</option>
													<option value="mysql" <%if(ValueStack.vs.get("dialect").equals("mysql")) out.write("selected");%>>mysql</option>
												</select>							
											</td>
										</tr>
										
										<tr class="TrOdd">
											<td>
												<b>驱动程序:</b>
											</td>
											<td colspan="2">
												<input id="driver" name="driver" type="text"
													value="<s:property name="driver"/>"
													size="32" class="text"  />											
											</td>
										</tr>
										<tr class="TrEven">
											<td>
												<b>数据库URL:</b>
											</td>
											<td colspan="2">
												<input id="url" name="url" type="text"
													value="<s:property name="url"/>"
													size="64" class="text" />
											</td>
										</tr>
										<tr class="TrOdd">
											<td>
												<b>用户名称:</b>
											</td>
											<td colspan="2">
												<input id="username" name="username" type="text"
													value="<s:property name="username"/>"
													size="31" class="text" />
											</td>
										</tr>
										<tr class="TrOdd">
											<td>
												<b>用户密码:</b>
											</td>
											<td colspan="2">
												<input id="password" name="password" type="password"
													value="<s:property name="password"/>"
													size="32" class="text" />
											</td>
										</tr>
									</table>
								</div>
								<div class="btnt">
									<button class="btn-able"
										onclick="javascript:doWith_f(submitF,'saveSysDatabaseConfig.action','您确定要保存吗?');">
										保存
									</button>
									
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<script>
$(document).ready(function(){
	initTab();
	initOrder("<%=contextPath%>/lib/console/images/");
}); 	
function doWith_f(form,action,msg){
  if(!check_f(form)) return;  
  if(typeof(msg)!="undefined" && msg!=""){   
	if(!window.confirm(msg)){
		return;
	}
  }	
  form.action=""+action+""; 
  form.submit();
}	
function doBack_f(form,action){
  form.action=""+action+""; 
  form.submit();
}

</script>
	</body>
</html>

