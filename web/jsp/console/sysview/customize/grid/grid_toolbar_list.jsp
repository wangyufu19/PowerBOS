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
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/form/table.js"></script>
</head>
<body>
<div class="container">
	
	<div class="box">		
		<div class="tabContent">
			<ul id="tabmenu" class="tabmenu">				   
			    <li id="tab1" class="select"><a href="#">动作集</a></li>				  			
			</ul>
			<form name="submitF" action="" method="post">				
			   <input id="resourceName" name="resourceName" type="hidden" value="<s:write property="resourceName"/>"/>
			   <input id="pageKey" name="pageKey" type="hidden" value="<s:write property="pageKey"/>"/>
			   <input id="pageCode" name="pageCode" type="hidden" value="<s:write property="pageCode"/>"/>
			   <input id="toolbarsKey" name="toolbarsKey" type="hidden" value="<s:write property="toolbarsKey"/>"/>
			  
			<div class="innerBox" id="tab1_content">			  
				<div class="innerBox1">							
					<div class="btnt">
						<button class="btn-able" onclick="javascript:doWith_f(submitF,'saveGridToolbarList.action','您确定要保存吗?');">保存</button>&nbsp;&nbsp;		
						<button class="btn-able" onclick="javascript:doBack_f(submitF,'getPageCustomize.action');">返回</button>	
	
					</div>					
					<div class="listform">
						<!--div class="title">Customize this table</div-->						
						<div class="listInner">
							<ul class="bar">
								<a href="javascript:addLine_f();">新增行</a>	
								<a href="javascript:deleteLine_f(submitF.id);">删除行</a>
								<a href="javascript:moveUp();">上移</a>
								<a href="javascript:moveDown();">下移</a>
								<li class="page">
									<ul class="setpage">								    		
															  
									</ul>
								</li>
							</ul>
							<table id="L0" width="100%" cellpadding="0" cellspacing="0" border="0" >
								<thead>
									<tr>		
									    <th><div>选择</div></th>								   
										<th><div>动作编码</div></th>															
										<th><div>动作名称</div></th>				
																																																									
									</tr>
								</thead>
								<tbody>
								  <s:iterator id="toolbar" name="toolbars" status="st">   								      
							        <tr
							         <s:if condition="$st=even">
							           class="TrEven"
							         </s:if>
							         <s:else>
							           class="TrOdd"
							         </s:else>
							        >			
							            <td>
							            <input id="key" name="key"  type="hidden" value="<s:property name="toolbar" value="key"/>" size="16" class="text"/>
							            <input id="id" name="id" type="radio" value="<s:write name="toolbar" property="key"/>" size="16" class="text" onclick="javascript:loadToolbarBase('<%=contextPath%>/jsp/console/getGridToolbarBase.action?resourceName=<s:write property="resourceName"/>&pageKey=<s:write property="pageKey"/>&pageCode=<s:write property="pageCode"/>&toolbarKey=<s:write name="toolbar" property="key"/>');"/>
							            </td> 				           
										<td><input id="code" name="code"  type="text" value="<s:property name="toolbar" value="code"/>" size="32" class="text"/></td>	
										<td><input id="name" name="name"  type="text" value="<s:property name="toolbar" value="name"/>" size="32" class="text"/></td>	
																															     		
								    </tr>
								  </s:iterator>
								</tbody>
							</table>	
							<ul class="bar">								
								<li class="page">
									<ul class="setpage">	
																	  
									</ul>
								</li>
							</ul>						
							<div class="clear"></div>							
						</div>
					</div>								
				</div>		
				
			</div>    				
		  </form>	 
		</div>
	</div>		
</div>
<script>
window.onload=function(){
	parent.iframe2.location.href="<%=contextPath%>/jsp/console/getGridToolbarBase.action?resourceName=<s:write property="resourceName"/>&pageKey=<s:write property="pageKey"/>&pageCode=<s:write property="pageCode"/>&toolbarKey=";
}
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
function loadToolbarBase(url){
	parent.iframe2.location.href=url;
}
function doBack_f(form,action){
  form.action=""+action+""; 
  form.target="mainboard";
  form.submit();
}
function addLine_f(){
  var table=document.getElementById("L0"); 
  var rows=table.rows.length; 
  if(rows>=1){
     var tr=table.insertRow(rows);    
     var td=tr.insertCell(0);     
     td.innerHTML="<input id=\"key\" name=\"key\"  type=\"hidden\" value=\"\"/><input id=\"id\" name=\"id\" type=\"radio\" value=\"\" size=\"\" class=\"text\" onclick=\"javascript:loadToolbarBase('<%=contextPath%>/jsp/console/getGridToolbarBase.action?resourceName=<s:write property="resourceName"/>&pageKey=<s:write property="pageKey"/>&pageCode=<s:write property="pageCode"/>&toolbarKey=');\"/>";
     var td=tr.insertCell(1);     
     td.innerHTML="<input id=\"code\" name=\"code\"  type=\"text\" value=\"\" size=\"32\" class=\"text\"/>";
     var td=tr.insertCell(2);
     td.innerHTML="<input id=\"name\" name=\"name\"  type=\"text\" value=\"\" size=\"32\" class=\"text\"/>";    
  }
}
</script>
</body>	
</html>

