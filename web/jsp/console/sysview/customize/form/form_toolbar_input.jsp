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
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/form/table.js"></script>
</head>
<body>
<div class="container">
	
	<div class="box">		
		<div class="tabContent">
			<ul id="tabmenu" class="tabmenu">				   
			    <li id="tab1"><a href="<%=contextPath%>/jsp/console/getFormToolbarBase.action?resourceName=<s:write property="resourceName"/>&toolbarKey=<s:write property="toolbarKey"/>">基本属性</a></li>	
			    <li id="tab2"><a href="<%=contextPath%>/jsp/console/getFormToolbarStatement.action?resourceName=<s:write property="resourceName"/>&toolbarKey=<s:write property="toolbarKey"/>">SQL语句</a></li>	
			    <li id="tab3" class="select"><a href="#">输入参数</a></li>	
			    <li id="tab4"><a href="<%=contextPath%>/jsp/console/getFormToolbarInterface.action?resourceName=<s:write property="resourceName"/>&toolbarKey=<s:write property="toolbarKey"/>">外部接口</a></li>
			    <li id="tab5"><a href="#">文件移动</a></li>		  			
			</ul>
			<form name="submitF" action="" method="post">				
			   <input id="resourceName" name="resourceName" type="hidden" value="<s:write property="resourceName"/>"/>			  
			   <input id="toolbarKey" name="toolbarKey" type="hidden" value="<s:write property="toolbarKey"/>"/>
			<div class="innerBox" id="tab1_content">			  
				<div class="innerBox1">							
					<div class="btnt">
						<button id="save" class="btn-able" onclick="javascript:doWith_f(submitF,'saveFormToolbarInput.action','您确定要保存吗?');">保存</button>&nbsp;&nbsp;
						<button id="addLine" class="btn-able" onclick="javascript:addLine_f();">新增行</button>&nbsp;&nbsp;
						<button id="deleteLine" class="btn-able" onclick="javascript:deleteLine_f(submitF.id);">删除行</button>&nbsp;&nbsp;
						<button id="cancel" class="btn-able" onclick="javascript:submitF.reset();">取消</button>
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
							<table id="L0" width="100%" cellpadding="0" cellspacing="0" border="0" >
								<thead>
									<tr>				
									    <th><div>选择</div></th>						   
										<th><div>参数名</div></th>															
										<th><div>参数值</div></th>																	
									</tr>
								</thead>
								<tbody>
								  <s:iterator id="setbean" name="setbeans" status="st">   								      
							        <tr
							         <s:if condition="$st=even">
							           class="TrEven"
							         </s:if>
							         <s:else>
							           class="TrOdd"
							         </s:else>
							        >
							            <td><input id="id" name="id"  type="checkbox" value="" class="text"/></td>
										<td><input id="name" name="name"  type="text" value="<s:write name="setbean" property="name"/>" size="32" class="text"/></td>	
										<td><input id="value" name="value"  type="text" value="<s:write name="setbean" property="value"/>" size="32" class="text"/></td>																										     		
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
						<button id="save" class="btn-able" onclick="javascript:doWith_f(submitF,'saveFormToolbarInput.action','您确定要保存吗?');">保存</button>&nbsp;&nbsp;
						<button id="addLine" class="btn-able" onclick="javascript:addLine_f();">新增行</button>&nbsp;&nbsp;
						<button id="deleteLine" class="btn-able" onclick="javascript:deleteLine_f(submitF.id);">删除行</button>&nbsp;&nbsp;
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
  var v1=document.getElementById("toolbarKey");         
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
function doBack_f(form,action){
  form.action=""+action+""; 
  form.submit();
}
function addLine_f(){
  var table=document.getElementById("L0"); 
  var rows=table.rows.length; 
  if(rows>=1){
     var tr=table.insertRow(rows);    
     var td=tr.insertCell(0);     
     td.innerHTML="<input id=\"id\" name=\"id\"  type=\"checkbox\" value=\"\" size=\"\" class=\"text\"/>";
     var td=tr.insertCell(1);     
     td.innerHTML="<input id=\"name\" name=\"name\"  type=\"text\" value=\"\" size=\"32\" class=\"text\"/>";
      var td=tr.insertCell(2);
     td.innerHTML="<input id=\"value\" name=\"value\"  type=\"text\" value=\"\" size=\"32\" class=\"text\"/>";
  }
}
</script>
</body>	
</html>

