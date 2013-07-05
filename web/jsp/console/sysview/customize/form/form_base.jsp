<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/controller.tld" prefix="s"%>
<%@ page import="com.controller.util.ValueStack"%>
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
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/layout/.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/form/check/check.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/form/check/text.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/form/check/base.js"></script>
</head>
<body>
<div class="container">
	
	<div class="box">		
		<div class="tabContent">
			<ul id="tabmenu" class="tabmenu">	
			    <li id="tab1" class="select"><a href="#">基本属性</a></li>				   	   				
			</ul>
			<form name="submitF" action="" method="post">					
			   <input id="resourceName" name="resourceName" type="hidden" value="<s:write property="resourceName"/>"/>
			   <input id="pageKey" name="pageKey" type="hidden" value="<s:write property="pageKey"/>"/>
			   <input id="pageCode" name="pageCode" type="hidden" value="<s:write property="pageCode"/>"/>
			   <input id="formKey" name="formKey" type="hidden" value="<s:write property="formKey"/>"/>
			<div class="innerBox" id="tab1_content">			  
				<div class="innerBox1">							
					<div class="btnt">
						<button class="btn-able" onclick="javascript:doWith_f(submitF,'saveFormObjectBase.action','您确定要保存吗?');">保存</button>&nbsp;&nbsp;
						<button class="btn-able" onclick="javascript:doBack_f(submitF,'getPageCustomize.action');">返回</button>
					</div>
					<div class="detailForm">				
						<table width="100%" cellpadding="0" cellspacing="0" border="0">
							<tr class="TrEven">
								<td>
								<b>表单标题:</b>							
								</td>
								<td colspan="2">
								<input name="formTitle" id="formTitle" type="text" value="<s:write property="formTitle"/>" size="32" class="text" />
								</td>
						    </tr>
						    <tr class="TrOdd">
								<td>
								<b>加载数据集方式:</b>							
								</td>
								<td colspan="2">
								<select name="loadDataSetStyle" id="loadDataSetStyle" style="width:152px" >
								<option value="">请选择</option>
								<option value="statement" <%if(ValueStack.vs.get("loadDataSetStyle").equals("statement")) out.write("selected");%>>SQL语句</option>
								<option value="interface" <%if(ValueStack.vs.get("loadDataSetStyle").equals("interface")) out.write("selected");%>>外部接口</option>
								</select>
								</td>
						    </tr>
							<tr class="TrEven">
								<td>
								<b>加载字段集方式:</b>							
								</td>
								<td colspan="2">
								<select name="loadColumnSetStyle" id="loadColumnSetStyle" style="width:152px" >
								<option value="">请选择</option>
								<option value="single" <%if(ValueStack.vs.get("loadColumnSetStyle").equals("single")) out.write("selected");%>>单列加载</option>
								<option value="double" <%if(ValueStack.vs.get("loadColumnSetStyle").equals("double")) out.write("selected");%>>双列加载</option>
								</select>
								</td>
						    </tr>							
					    </table>						
					</div>
					<div class="btnt">
						<button class="btn-able" onclick="javascript:doWith_f(submitF,'saveFormObjectBase.action','您确定要保存吗?');">保存</button>&nbsp;&nbsp;
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

