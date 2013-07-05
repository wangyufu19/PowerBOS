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
			    <li id="tab1"><a href="getGridStatementBase.action?resourceName=<s:write property="resourceName"/>&pageKey=<s:write property="pageKey"/>&pageCode=<s:write property="pageCode"/>&statementKey=<s:write property="statementKey"/>">基本属性</a></li>				  
			    <li id="tab2"><a href="getGridStatementSelect.action?resourceName=<s:write property="resourceName"/>&pageKey=<s:write property="pageKey"/>&pageCode=<s:write property="pageCode"/>&statementKey=<s:write property="statementKey"/>">基本语句</a></li>	
			    <li id="tab3"><a href="getGridStatementWhere.action?resourceName=<s:write property="resourceName"/>&pageKey=<s:write property="pageKey"/>&pageCode=<s:write property="pageCode"/>&statementKey=<s:write property="statementKey"/>">条件语句</a></li>
			    <li id="tab4" class="select"><a href="#">排序语句</a></li>		
			    <li id="tab5"><a href="getGridStatementInput.action?resourceName=<s:write property="resourceName"/>&pageKey=<s:write property="pageKey"/>&pageCode=<s:write property="pageCode"/>&statementKey=<s:write property="statementKey"/>">输入参数</a></li>				   				
			</ul>
			<form name="submitF" action="" method="post">					
			   <input id="resourceName" name="resourceName" type="hidden" value="<s:write property="resourceName"/>"/>
			   <input id="pageKey" name="pageKey" type="hidden" value="<s:write property="pageKey"/>"/>
			   <input id="pageCode" name="pageCode" type="hidden" value="<s:write property="pageCode"/>"/>
			   <input id="statementKey" name="statementKey" type="hidden" value="<s:write property="statementKey"/>"/>
			   <input id="orderbyKey" name="orderbyKey" type="hidden" value="<s:write property="orderbyKey"/>"/>
			<div class="innerBox" id="tab1_content">			  
				<div class="innerBox1">							
					<div class="btnt">
						<button class="btn-able" onclick="javascript:doWith_f(submitF,'saveGridStatementOrderby.action','您确定要保存吗?');">保存</button>&nbsp;&nbsp;
						<button class="btn-able" onclick="javascript:doBack_f(submitF,'getPageCustomize.action');">返回</button>
					</div>
					<div class="detailForm">				
						<table width="100%" cellpadding="0" cellspacing="0" border="0" >						   
							<tr class="TrEven">		
							    <td><b>排序语句:</b></td>								
								<td colspan="3">
								<textarea id="orderbyText" name="orderbyText" class="text" rows="5" cols="50"><s:write property="orderbyText"></s:write></textarea>						
								</td>										
							</tr>																																							
						</table>	
					</div>
					<div class="btnt">
						<button class="btn-able" onclick="javascript:doWith_f(submitF,'saveGridStatementOrderby.action','您确定要保存吗?');">保存</button>&nbsp;&nbsp;
						<button class="btn-able" onclick="javascript:doBack_f(submitF,'getPageCustomize.action');">返回</button>
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

