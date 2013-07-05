<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/controller.tld" prefix="s"%>
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
							<a href="#">插件管理</a>
						</li>
					</ul>
					<form name="submitF" action="" method="post">
						<input id="key" name="key" type="hidden" value="<s:write name="sysPlugin" property="key"/>"/>
						<div class="innerBox" id="tab1_content">
							<div class="innerBox1">
								<div class="btnt">
									<button class="btn-able"
										onclick="javascript:doWith_f(submitF,'updateSysPlugin.action','您确定要保存吗?');">
										保存
									</button>
									&nbsp;&nbsp;
									<button class="btn-able"
										onclick="javascript:doBack_f(submitF,'getSystemPluginList.action');">
										返回
									</button>
								</div>
								<div class="detailForm">
									<table width="100%" cellpadding="0" cellspacing="0" border="0">
										<tr class="TrEven">
											<td>
												<b>插件名称:</b>
											</td>
											<td colspan="2">
												<input id="name" name="name" type="text"
													value="<s:write name="sysPlugin" property="name"/>"
													size="32" class="text" check="string(64),nn,插件名称" />
												<span class="star">*</span>
											</td>
										</tr>
										<tr class="TrOdd">
											<td>
												<b>插件类库:</b>
											</td>
											<td colspan="2">
												<input id="clazz" name="clazz" type="text"
													value="<s:write name="sysPlugin" property="clazz"/>"
													size="64" class="text" check="string(64),nn,插件类库" />
													<span class="star">*</span>
											</td>
										</tr>
										<tr class="TrEven">
											<td>
												<b>插件描述:</b>
											</td>
											<td colspan="2">
												<textarea id="desc" name="desc" rows="5" cols="50"><s:write name="sysPlugin" property="desc"/></textarea>	
											</td>
										</tr>
									</table>
								</div>
								<div class="btnt">
									<button class="btn-able"
										onclick="javascript:doWith_f(submitF,'updateSysPlugin.action','您确定要保存吗?');">
										保存
									</button>
									&nbsp;&nbsp;
									<button class="btn-able"
										onclick="javascript:doBack_f(submitF,'getSystemPluginList.action');">
										返回
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

