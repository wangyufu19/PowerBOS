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
						<button id="add" class="btn-able" onclick="javascript:doWith_f(submitF,'getAddPageWidget.action?resouceName=<s:write property="resourceName"/>');">新增</button>&nbsp;&nbsp;
						<button id="delete" class="btn-able" disabled="disabled" onclick="javascript:doWith_f(submitF,'deletePageWidget.action','您确定要删除吗?');">删除</button>&nbsp;&nbsp;
					</div>
					<div class="listform">
						<!--div class="title">Customize this table</div-->
						<div class="listInner">
							<ul class="bar">
								<li class="page">
									<ul class="setpage">									  
									</ul>
								</li>
							</ul>
							<table width="100%" cellpadding="0" cellspacing="0" border="0" >
								<thead>
									<tr>
									    <th><div>选择</div></th>	
										<th><div>页面编码</div></th>															
										<th><div>页面位置</div></th>											
										<th><div>表单类型</div></th>	
										<th><div>操作</div></th>												
									</tr>
								</thead>
								<tbody>
								  <s:iterator id="pageWidget" name="pageWidgets" status="st">   								      
							        <tr
							         <s:if condition="$st=even">
							           class="TrEven"
							         </s:if>
							         <s:else>
							           class="TrOdd"
							         </s:else>
							        >			
							            <td><input id="id" name="id" type="checkbox" value="<s:write name="pageWidget" property="key"/>" class="text" onclick="javascript:enable_f('id');"/></td> 					       																    
									    <td><a href="getUpdatePageWidget.action?resourceName=<s:write property="resourceName"/>&key=<s:write name="pageWidget" property="key"/>&pageCode=<s:write name="pageWidget" property="pageCode"/>"><s:write name="pageWidget" property="pageCode"/></a></td>														
										<td><s:write name="pageWidget" property="pageLocal"/></td>										
										<td><s:write name="pageWidget" property="mimeType"/></td>		
										<td><a href="getPageCustomize.action?resourceName=<s:write property="resourceName"/>&pageKey=<s:write name="pageWidget" property="key"/>&pageCode=<s:write name="pageWidget" property="pageCode"/>">页面定制</a></td>																     		
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
					<div class="btnt">
						<button id="add" class="btn-able" onclick="javascript:doWith_f(submitF,'getAddPageWidget.action?resouceName=<s:write property="resourceName"/>');">新增</button>&nbsp;&nbsp;
						<button id="delete" class="btn-able" disabled="disabled" onclick="javascript:doWith_f(submitF,'deletePageWidget.action','您确定要删除吗?');">删除</button>&nbsp;&nbsp;
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
  if(typeof(msg)!="undefined" && msg!=""){   
	if(!window.confirm(msg)){
		return;
	}
  }	
  form.action=""+action+""; 
  form.submit();
}	
function enable_f(id){   
  var v=document.getElementsByName("delete");                      
  if(isChecked(id)){
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
function search_f(){
   submitF.submit();
}
</script>

</body>	
</html>

