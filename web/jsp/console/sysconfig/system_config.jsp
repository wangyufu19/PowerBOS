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
										onclick="javascript:doWith_f(submitF,'saveSysConfig.action','您确定要保存吗?');">
										保存
									</button>									
								</div>
								<div class="detailForm">
								
									<table width="100%" cellpadding="0" cellspacing="0" border="0">
										<tr class="TrEven">
											<td>
												<b>产品名称:</b>
											</td>
											<td>
												<b><s:property name="productName"/></b>
											</td>	
											<td>	
												<b>&nbsp;&nbsp;&nbsp;&nbsp;</b>												
											</td>										
										</tr>
										<tr class="TrOdd">
											<td>
												<b>版本编号:</b>
											</td>
											<td>
												<b><s:property name="versionNum"/></b>
											</td>		
											<td>	
												<b>&nbsp;&nbsp;&nbsp;&nbsp;</b>												
											</td>									
										</tr>
										<tr class="TrEven">
											<td>
												<b>运行容器:</b>
											</td>
											<td>
												<b><s:property name="runtimeServer"/></b>
											</td>
											<td>		
												<b>&nbsp;&nbsp;&nbsp;&nbsp;</b>											
											</td>
										</tr>
										<tr class="TrOdd">
											<td>
												<b>运行模式:</b>
											</td>
											<td>
												<b><s:property name="runtimeMode"/></b>
											</td>
											<td>		
												<b>&nbsp;&nbsp;&nbsp;&nbsp;</b>											
											</td>
										</tr>
										<tr class="TrEven">
											<td>
												<b>字符集编码:</b>
											</td>
											<td>				
												<select id="charsetCode" name="charsetCode">
													<option value="">请选择</option>
													<option value="utf-8" <%if(ValueStack.vs.get("charsetCode").equals("utf-8")) out.write("selected");%>>utf-8</option>
													<option value="gbk" <%if(ValueStack.vs.get("charsetCode").equals("gbk")) out.write("selected");%>>gbk</option>
												</select>							
											</td>
											<td>
												<b>平台统一字符集编码</b>		
											</td>
										</tr>
										<tr class="TrOdd">
											<td>
												<b>系统调试日志:</b>												
											</td>
											<td>
												<select id="debugLog" name="debugLog">
													<option value="">请选择</option>
													<option value="true" <%if(ValueStack.vs.get("debugLog").equals("true")) out.write("selected");%>>true</option>
													<option value="false" <%if(ValueStack.vs.get("debugLog").equals("false")) out.write("selected");%>>false</option>
												</select>											
											</td>
											<td>
												<b>true:开启日志；false:关闭日志	</b>
											</td>
										</tr>
										<tr class="TrEven">
											<td>
												<b>验证会话限制:</b>
											</td>
											<td>
												<select id="sessionLimit" name="sessionLimit">
													<option value="">请选择</option>
													<option value="true" <%if(ValueStack.vs.get("sessionLimit").equals("true")) out.write("selected");%>>true</option>
													<option value="false" <%if(ValueStack.vs.get("sessionLimit").equals("false")) out.write("selected");%>>false</option>
												</select>									
											</td>
											<td>
												<b>true:单一会话；false:多个会话	</b>
											</td>
										</tr>
										<tr class="TrOdd">
											<td>
												<b>验证会话时间:</b>
											</td>
											<td>
												<select id="sessionTime" name="sessionTime">
													<option value="">请选择</option>
													<option value="true" <%if(ValueStack.vs.get("sessionTime").equals("true")) out.write("selected");%>>true</option>
													<option value="false" <%if(ValueStack.vs.get("sessionTime").equals("false")) out.write("selected");%>>false</option>
												</select>	
											</td>
											<td>
												<b>true:验证会话时间；false:不验证会话时间</b>
											</td>
											
										</tr>
										<tr class="TrEven">
											<td>
												<b>上传文件临时目录:</b>
											</td>
											<td colspan="2">
												<input id="uploadPath" name="uploadPath" type="text"
													value="<s:property name="uploadPath"/>"
													size="32" class="text"  />											
											</td>
										</tr>
										<tr class="TrOdd">
											<td>
												<b>允许上传文件名称:</b>
											</td>
											<td colspan="2">
												<input id="uploadAllowableExtensions" name="uploadAllowableExtensions" type="text"
													value="<s:property name="uploadAllowableExtensions"/>"
													size="64" class="text" />
											</td>
										</tr>
									</table>
								</div>
								<div class="btnt">
									<button class="btn-able"
										onclick="javascript:doWith_f(submitF,'saveSysConfig.action','您确定要保存吗?');">
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

