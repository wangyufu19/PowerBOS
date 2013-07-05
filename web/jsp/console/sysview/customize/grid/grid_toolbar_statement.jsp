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
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/form/checkbox.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/form/check/check.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/form/check/text.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/form/check/base.js"></script>
</head>
<body>
<div class="container">	
	<div class="box">		
		<div class="tabContent">
			<ul id="tabmenu" class="tabmenu">				   
			    <li id="tab1"><a href="<%=contextPath%>/jsp/console/getGridToolbarBase.action?resourceName=<s:write property="resourceName"/>&toolbarKey=<s:write property="toolbarKey"/>">基本属性</a></li>	
			    <li id="tab2" class="select"><a href="#">SQL语句</a></li>	
			    <li id="tab3"><a href="<%=contextPath%>/jsp/console/getGridToolbarInput.action?resourceName=<s:write property="resourceName"/>&toolbarKey=<s:write property="toolbarKey"/>">输入参数</a></li>	
			    <li id="tab4"><a href="<%=contextPath%>/jsp/console/getGridToolbarInterface.action?resourceName=<s:write property="resourceName"/>&toolbarKey=<s:write property="toolbarKey"/>">外部接口</a></li>
			    <li id="tab5"><a href="#">文件移动</a></li>		  			
			</ul>
			<form name="submitF" action="" method="post">				
			   <input id="resourceName" name="resourceName" type="hidden" value="<s:write property="resourceName"/>"/>	
			   <input id="toolbarKey" name="toolbarKey" type="hidden" value="<s:write property="toolbarKey"/>"/>		  
			   <input id="statementKey" name="statementKey" type="hidden" value="<s:write property="statementKey"/>"/>
			  
			<div class="innerBox" id="tab1_content">			  
				<div class="innerBox1">							
					<div class="btnt">
						<button id="save" disabled="disabled" class="btn-able" onclick="javascript:doWith_f(submitF,'saveGridToolbarStatement.action','您确定要保存吗?');">保存</button>&nbsp;&nbsp;
						<button id="cancel" class="btn-able" onclick="javascript:submitF.reset();">取消</button>
					</div>					
					<div class="detailForm">				
						<table width="100%" cellpadding="0" cellspacing="0" border="0">							
							<tr class="TrEven">
								<td><b>parameterClass:</b></td>							
								<td colspan="2">
								<input id="parameterClass" name="parameterClass"  type="text" value="<s:write property="parameterClass"/>" size="40" class="text"/>
								</td>										
							</tr>	
							<tr class="TrOdd">
								<td><b>resultMap:</b></td>							
								<td colspan="2">
								<input id="resultMap" name="resultMap"  type="text" value="<s:write property="resultMap"/>" size="32" class="text"/>
								</td>										
							</tr>
							<tr class="TrEven">
								<td><b>SQL语句:</b></td>							
								<td colspan="2">
								<textarea name="statementText" rows="8" cols="70" class="text"><s:write property="statementText"/></textarea>
								</td>										
							</tr>																				
						</table>
					</div>	
					<div class="btnt">
						<button id="save" disabled="disabled" class="btn-able" onclick="javascript:doWith_f(submitF,'saveGridToolbarStatement.action','您确定要保存吗?');">保存</button>&nbsp;&nbsp;
						<button id="cancel" class="btn-able" onclick="javascript:submitF.reset();">取消</button>
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
window.onload=function(){
  var v=document.getElementsByName("save"); 
  var v1=document.getElementById("statementKey");         
  if(v1.value!=""){
    if(typeof(v.length)!="undefined"){
	  	for(var i=0;i<v.length;i++){
	  		v[i].disabled=false;
	  	}
  	}  
  }else{
  	if(typeof(v.length)!="undefined"){
	  	for(var i=0;i<v.length;i++){
	  		v[i].disabled=true;
	  	}
  	}  
  }   
}
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

