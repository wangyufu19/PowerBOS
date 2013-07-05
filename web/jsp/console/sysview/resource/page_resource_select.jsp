<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/controller.tld" prefix="s"%>
<%@ page import="java.util.List" %>
<%@ page import="com.controller.util.ValueStack" %>
<%@ page import="com.framework.common.servlet.http.RequestHash" %>
<%@ page import="com.application.console.biz.PageResourceBiz" %>
<% 
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
%>

<%
	String contextPath=request.getContextPath();
    RequestHash reh=new RequestHash(request,response);
    PageResourceBiz biz=new PageResourceBiz();
    biz.setReh(reh);
    List pageResources=biz.getPageResourceList(); 
    ValueStack vs=new ValueStack();
    vs.set("pageResources",pageResources);
    request.setAttribute("valueStack", vs);   
 
    
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
			    <li id="tab1" class="select"><a href="#">页面资源</a></li>					   			
			</ul>
			<form name="submitF" action="" method="post">							 
			<div class="innerBox" id="tab1_content">			  
				<div class="innerBox1">							
					<div class="btnt">
						<button id="select" class="btn-able" disabled="disabled" onclick="javascript:select_f();">选择</button>&nbsp;&nbsp;
						<button id="cancel" class="btn-able" onclick="javascript:cancel_f();">取消</button>&nbsp;&nbsp;
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
										<th><div>资源名称</div></th>																								
										<th><div>创建时间</div></th>										
									</tr>
								</thead>
								<tbody>
								  <s:iterator id="pageResource" name="pageResources" status="st">   								      
							        <tr
							         <s:if condition="$st=even">
							           class="TrEven"
							         </s:if>
							         <s:else>
							           class="TrOdd"
							         </s:else>
							        >				
							            <td><input id="id" name="id" type="radio" value="<s:write name="pageResource" property="resourceName"/>" class="text" onclick="javascript:enable_f('id');"/></td> 					       																    
									    <td><s:write name="pageResource" property="resourceName"/></td>																								
										<td><s:write name="pageResource" property="createTime"/></td>																     		
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
						<button id="select" class="btn-able" disabled="disabled" onclick="javascript:select_f();">选择</button>&nbsp;&nbsp;
						<button id="cancel" class="btn-able" onclick="javascript:cancel_f();">取消</button>&nbsp;&nbsp;
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

function enable_f(id){   
  var v=document.getElementsByName("select");                      
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
function select_f(){
  var box = document.getElementsByName("id");		    
  var bool = false;   
  var v1="";
  for(var i=0;i<box.length;i++){
 	if(box[i].checked == true){
 		bool = true;
 		v1=box[i].value;
 		break;
 	}
  }	   
  if(bool){     	
  	window.opener.setResourceName(v1);
  	window.close();
  }
}
function cancel_f(){
  var v=document.getElementsByName("select");
  for(var i=0;i<v.length;i++){
	v[i].disabled=true;
  }
  submitF.reset();
}
function search_f(){
   submitF.submit();
}
</script>

</body>	
</html>

