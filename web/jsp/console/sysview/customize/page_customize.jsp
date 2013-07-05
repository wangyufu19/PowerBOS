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
</head>
<body>
<div class="container">
    
    <div class="space"></div>
    <form name="submitF" action="" method="post">		     		    
     <input id="resourceName" name="resourceName" type="hidden" value="<s:write property="resourceName"/>"/>
     <input id="pageKey" name="pageKey" type="hidden" value="<s:write property="pageKey"/>"/>		
	 <input id="pageCode" name="pageCode" type="hidden" value="<s:write property="pageCode"/>"/>
	 
    <div class="btnt">	
        <button class="btn-able" onclick="javascript:review_f(submitF)">预览</button>&nbsp;&nbsp;		
		<button class="btn-able" onclick="javascript:doBack_f(submitF,'getPageWidgetList.action');">返回</button>
	</div>		
	<div class="space"></div>
	<fieldset>
		<legend><s:write property="pageCode"/></legend>				
		<table width="100%"  cellspacing="0" cellpadding="0" class="content">
			<tr>
				<td width="45%">
					<div class="boxItem">	
					    <h5><a href="javascript:doWith_f(submitF,'getSearchObjectBase.action?searchKey=<s:write property="searchKey"/>');">查询对象</a></h5>							
						<dl class="sublist">										
							<dd><a href="javascript:doWith_f(submitF,'getSearchColumnSetIframe.action?columnSetKey=<s:write property="searchColumnSetKey"/>');">字段集</a></dd>															
						</dl>
					</div>
					<div class="boxItem">	
					    <h5><a href="javascript:doWith_f(submitF,'getGridObjectBase.action?gridKey=<s:write property="gridKey"/>');">网格对象</a></h5>							
						<dl class="sublist">								   
							<dd>数据集</dd>	
							<dl class="sublist">	
							   <dd><a href="javascript:doWith_f(submitF,'getGridStatementBase.action?statementKey=<s:write property="gridStatementKey"/>');">SQL语句</a></dd>	
							   <dd><a href="javascript:doWith_f(submitF,'getGridInterfaceBase.action?interfaceKey=<s:write property="gridInterfaceKey"/>');">外部接口</a></dd>		
							</dl>
							<dd><a href="javascript:doWith_f(submitF,'getGridColumnSetIframe.action?columnSetKey=<s:write property="gridColumnSetKey"/>');">字段集</a></dd>		
							<dd><a href="javascript:doWith_f(submitF,'getGridToolbarIframe.action?toolbarsKey=<s:write property="gridToolbarsKey"/>');">动作集</a></dd>								
						</dl>
					</div>
				</td>		
				<td width="10%"></td>
				<td width="45%">
					<div class="boxItem">	
					    <h5><a href="javascript:doWith_f(submitF,'getFormObjectBase.action?formKey=<s:write property="formKey"/>');">表单对象</a></h5>							
						<dl class="sublist">
						   <dd>数据集</dd>	
						   <dl class="sublist">	
							   <dd><a href="javascript:doWith_f(submitF,'getFormStatementBase.action?statementKey=<s:write property="formStatementKey"/>');">SQL语句</a></dd>	
							   <dd><a href="javascript:doWith_f(submitF,'getFormInterfaceBase.action?interfaceKey=<s:write property="formInterfaceKey"/>');">外部接口</a></dd>		
						   </dl>								  
						   <dd><a href="javascript:doWith_f(submitF,'getFormColumnSetIframe.action?columnSetKey=<s:write property="formColumnSetKey"/>');">字段集</a></dd>		
						   <dd><a href="javascript:doWith_f(submitF,'getFormToolbarIframe.action?toolbarsKey=<s:write property="formToolbarsKey"/>');">动作集</a></dd>																							
						</dl>
					</div>
					<div class="boxItem">	
					    <h5><a href="#">脚本对象</a></h5>							
						<dl class="sublist">
						    <dd><a href="javascript:doWith_f(submitF,'getScriptObjectContent.action?scriptKey=<s:write property="scriptKey"/>');">脚本内容</a></dd>															
						</dl>
					</div>
				</td>						
			</tr>
		</table>
	</fieldset>	
	
	<div class="space"></div>	
	<div class="btnt">		
	    <button class="btn-able" onclick="javascript:review_f(submitF)">预览</button>&nbsp;&nbsp;		
		<button class="btn-able" onclick="javascript:doBack_f(submitF,'getPageWidgetList.action');">返回</button>
	</div>	
	</form>    
</div>
<script>
$(document).ready(function(){
	initBox();
}); 	
function review_f(form){
  form.action="<%=contextPath%>/jsp/console/sysview/customize/dynamic_page.jsp?CODE=<s:write property="pageCode"/>";
  form.target="_blank";
  form.submit();
  form.target="";
}

function doWith_f(form,action){
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
