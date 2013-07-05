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
			    <li id="tab1" class="select"><a href="#">基本属性</a></li>	
			    <li id="tab2"><a href="#">高级属性</a></li>	
			    <li id="tab3"><a href="#">事件属性</a></li>		  			
			</ul>
			<form name="submitF" action="" method="post">				
			   <input id="resourceName" name="resourceName" type="hidden" value="<s:write property="resourceName"/>"/>
			   <input id="pageKey" name="pageKey" type="hidden" value="<s:write property="pageKey"/>"/>
			   <input id="pageCode" name="pageCode" type="hidden" value="<s:write property="pageCode"/>"/>
			   <input id="columnKey" name="columnKey" type="hidden" value="<s:write property="columnKey"/>"/>
			  
			<div class="innerBox" id="tab1_content">			  
				<div class="innerBox1">							
					<div class="btnt">
						<button id="save" disabled="disabled" class="btn-able" onclick="javascript:doWith_f(submitF,'saveFormColumnSetAtt.action','您确定要保存吗?');">保存</button>&nbsp;&nbsp;
						<button id="cancel" class="btn-able" onclick="javascript:submitF.reset();">取消</button>
					</div>					
					<div class="detailForm">				
						<table width="100%" cellpadding="0" cellspacing="0" border="0">
						    <tr class="TrEven">
								<td><b>加载条件:</b></td>							
								<td colspan="2"><input name="loadCondition" type="text" value="<s:write name="columnSet" property="loadCondition"/>" size="32" class="text"/></td>										
							</tr>						   
							<tr class="TrOdd">
								<td><b>字段类型:</b></td>							
								<td colspan="2">
								<select name="docType" style="width:152px" check="string(32),nn,文档类型">
								   <option value="">请选择</option>
								   <option value="hidden" <%if(ValueStack.get("columnSet","docType").equals("hidden")) out.write("selected");%>>隐藏文本框</option>
								   <option value="text" <%if(ValueStack.get("columnSet","docType").equals("text")) out.write("selected");%>>输入文本框</option>
								   <option value="date" <%if(ValueStack.get("columnSet","docType").equals("date")) out.write("selected");%>>日期选择框</option>
								   <option value="password" <%if(ValueStack.get("columnSet","docType").equals("password")) out.write("selected");%>>密码输入框</option>
								   <option value="select" <%if(ValueStack.get("columnSet","docType").equals("select")) out.write("selected");%>>下拉选择框</option>
								   <option value="textarea" <%if(ValueStack.get("columnSet","docType").equals("textarea")) out.write("selected");%>>多行文本框</option>
								   <option value="file" <%if(ValueStack.get("columnSet","docType").equals("file")) out.write("selected");%>>文件选择框</option>
								   <option value="image" <%if(ValueStack.get("columnSet","docType").equals("image")) out.write("selected");%>>图片选择框</option>
								</select>
								<span class="star">*</span>	
								</td>										
							</tr>		
							<tr class="TrEven">
								<td><b>字段样式:</b></td>							
								<td colspan="2"><input name="docCss" type="text" value="<s:write name="columnSet" property="docCss"/>" size="32" class="text"/></td>										
							</tr>
							<tr class="TrOdd">
								<td><b>字段宽度:</b></td>							
								<td colspan="2"><input name="docWidth" type="text" value="<s:write name="columnSet" property="docWidth"/>" size="32" class="text"/></td>										
							</tr>	
							<tr class="TrEven">
								<td><b>字段高度:</b></td>							
								<td colspan="2"><input name="docHeight" type="text" value="<s:write name="columnSet" property="docHeight"/>" size="32" class="text"/></td>										
							</tr>	
							<tr class="TrOdd">
								<td><b>最大长度:</b></td>							
								<td colspan="2"><input name="docMaxlength" type="text" value="<s:write name="columnSet" property="docMaxlength"/>" size="32" class="text"/></td>										
							</tr>																								
						</table>
					</div>	
					<div class="btnt">
						<button id="save" disabled="disabled" class="btn-able" onclick="javascript:doWith_f(submitF,'saveFormColumnSetAtt.action','您确定要保存吗?');">保存</button>&nbsp;&nbsp;
						<button id="cancel" class="btn-able" onclick="javascript:submitF.reset();">取消</button>
					</div>				
				</div>			
			</div>    	
			
			<div class="innerBox" id="tab2_content" style="display:none;">			  
				<div class="innerBox1">							
					<div class="btnt">
						<button id="save" disabled="disabled" class="btn-able" onclick="javascript:doWith_f(submitF,'saveFormColumnSetAtt.action','您确定要保存吗?');">保存</button>&nbsp;&nbsp;
						<button id="cancel" class="btn-able" onclick="javascript:submitF.reset();">取消</button>
					</div>					
					<div class="detailForm">				
						<table width="100%" cellpadding="0" cellspacing="0" border="0">
						    <tr class="TrEven">
								<td><b>引用规则:</b></td>							
								<td colspan="2">
								<textarea name="refFormula" rows="3" cols="50" class="text"><s:write name="columnSet" property="refFormula"/></textarea>
								</td>										
							</tr>
							<tr class="TrOdd">
								<td><b>字段扩展:</b></td>							
								<td colspan="2">
								<textarea name="docExtend" rows="3" cols="50" class="text"><s:write name="columnSet" property="docExtend"/></textarea>
								</td>										
							</tr>						   
							<tr class="TrEven">
								<td><b>是否显示:</b></td>							
								<td colspan="2">
								<select name="isDisabled" style="width:152px">
								   <option value="">请选择</option>
								   <option value="true" <%if(ValueStack.get("columnSet","isDisabled").equals("true")) out.write("selected");%>>是</option>
								   <option value="false" <%if(ValueStack.get("columnSet","isDisabled").equals("false")) out.write("selected");%>>否</option>								  
								</select>								
								</td>										
							</tr>	
							<tr class="TrOdd">
								<td><b>是否只读:</b></td>							
								<td colspan="2">
								<select name="isReadonly" style="width:152px">
								   <option value="">请选择</option>
								   <option value="true" <%if(ValueStack.get("columnSet","isReadonly").equals("true")) out.write("selected");%>>是</option>
								   <option value="false" <%if(ValueStack.get("columnSet","isReadonly").equals("false")) out.write("selected");%>>否</option>								  
								</select>								
								</td>										
							</tr>
							<tr class="TrEven">
								<td><b>是否必填:</b></td>							
								<td colspan="2">
								<select name="isNull" style="width:152px">
								   <option value="">请选择</option>
								   <option value="true" <%if(ValueStack.get("columnSet","isNull").equals("true")) out.write("selected");%>>是</option>
								   <option value="false" <%if(ValueStack.get("columnSet","isNull").equals("false")) out.write("selected");%>>否</option>								  
								</select>								
								</td>										
							</tr>																								
						</table>
					</div>	
					<div class="btnt">
						<button id="save" disabled="disabled" class="btn-able" onclick="javascript:doWith_f(submitF,'saveFormColumnSetAtt.action','您确定要保存吗?');">保存</button>&nbsp;&nbsp;
						<button id="cancel" class="btn-able" onclick="javascript:submitF.reset();">取消</button>
					</div>				
				</div>			
			</div>	
			
			<div class="innerBox" id="tab3_content" style="display:none;">			  
				<div class="innerBox1">							
					<div class="btnt">
						<button id="save" disabled="disabled" class="btn-able" onclick="javascript:doWith_f(submitF,'saveFormColumnSetAtt.action','您确定要保存吗?');">保存</button>&nbsp;&nbsp;
						<button id="cancel" class="btn-able" onclick="javascript:submitF.reset();">取消</button>
					</div>					
					<div class="detailForm">				
						<table width="100%" cellpadding="0" cellspacing="0" border="0">
						    <tr class="TrEven">
								<td><b>单击事件:</b></td>							
								<td colspan="2">
								<textarea name="docOnclick" rows="3" cols="50" class="text"><s:write name="columnSet" property="docOnclick"/></textarea>
								</td>										
							</tr>
							<tr class="TrOdd">
								<td><b>焦点事件:</b></td>							
								<td colspan="2">
								<textarea name="docOnblur" rows="3" cols="50" class="text"><s:write name="columnSet" property="docOnblur"/></textarea>
								</td>										
							</tr>																									
						</table>
					</div>	
					<div class="btnt">
						<button id="save" disabled="disabled" class="btn-able" onclick="javascript:doWith_f(submitF,'saveFormColumnSetAtt.action','您确定要保存吗?');">保存</button>&nbsp;&nbsp;
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
  var v1=document.getElementById("columnKey");         
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

