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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=contextPath%>/lib/console/css/css.css" type="text/css"/>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/jquery/jquery-1.5.1.min.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/layout/tab.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/form/check/check.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/form/check/text.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/form/check/base.js"></script>
</head>
<body>
<div class="container">

	<div class="box">		
		<div class="tabContent">
			<ul id="tabmenu" class="tabmenu">	
			    <li id="tab1" class="select"><a href="#">页面编码</a></li>						
			</ul>
			<form name="submitF" action="" method="post">		    
			<input id="resourceName" name="resourceName" type="hidden" value="<s:write property="resourceName"/>"/>		    
			<div class="innerBox" id="tab1_content">
				<div class="innerBox1">		
				    <div class="btnt">
						<button class="btn-able" onclick="javascript:doWith_f(submitF,'addPageWidget.action','您确定要保存吗?');">保存</button>&nbsp;&nbsp;
						<button class="btn-able" onclick="javascript:doBack_f(submitF,'getPageWidgetList.action');">返回</button>
					</div>				   				
					<div class="detailForm">				
						<table width="100%" cellpadding="0" cellspacing="0" border="0" >						   
							<tr class="TrEven">
								<td><b>页面编码:</b></td>							
								<td colspan="2"><input name="pageCode" type="text" value="" size="32" class="text" check="string(64),nn,页面编码"/><span class="star">*</span></td>										
							</tr>	
							<tr class="TrOdd">
								<td><b>页面位置:</b></td>							
								<td colspan="2"><input name="pageLocal" type="text" value="" size="32" class="text" check="string(64),nn,页面位置"/><span class="star">*</span></td>										
							   
							</tr>		
							<tr class="TrEven">
								<td><b>MIME类型:</b></td>							
								<td colspan="2">
								<select name="mimeType" style="width:152px">
								   <option value="">请选择</option>
								   <option value="normal">普通表单</option>
								   <option value="multipart/form-data">复合表单</option>
								</select>		
								</td>															
							</tr>		
							<tr class="TrOdd">
								<td><b>加载查询组件:</b></td>							
								<td colspan="2">
								<select name="loadSearchWidget" style="width:152px">
								   <option value="">请选择</option>
								   <option value="true">加载</option>
								   <option value="false">不加载</option>
								</select>	
								</td>																
							</tr>		
							<tr class="TrEven">
								<td><b>加载网格组件:</b></td>							
								<td colspan="2">
								<select name="loadGridWidget" style="width:152px">
								   <option value="">请选择</option>
								   <option value="true">加载</option>
								   <option value="false">不加载</option>
								</select>		
								</td>															
							</tr>		
							<tr class="TrOdd">
								<td><b>加载表单组件:</b></td>							
								<td colspan="2">
								<select name="loadFormWidget" style="width:152px">
								   <option value="">请选择</option>
								   <option value="true">加载</option>
								   <option value="false">不加载</option>
								</select>	
								</td>																
							</tr>	
							<tr class="TrEven">
								<td><b>加载脚本组件:</b></td>							
								<td colspan="2">
								<select name="loadScriptWidget" style="width:152px">
								   <option value="">请选择</option>
								   <option value="true">加载</option>
								   <option value="false">不加载</option>
								</select>	
								</td>																
							</tr>																								
						</table>
					</div>
					<div class="btnt">
						<button class="btn-able" onclick="javascript:doWith_f(submitF,'addPageWidget.action','您确定要保存吗?');">保存</button>&nbsp;&nbsp;
						<button class="btn-able" onclick="javascript:doBack_f(submitF,'getPageWidgetList.action');">返回</button>
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

