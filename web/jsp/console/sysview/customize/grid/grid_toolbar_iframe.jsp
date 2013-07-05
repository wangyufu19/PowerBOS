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
<!DOCTYPE HTML PUBLIC "-//W3C//Dtd HTML 4.01 Transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=contextPath%>/lib/console/css/css.css" type="text/css"/>
</head>

<body>  
    <!--<div id="toggleImgs">
    <a onClick="toggleFrame()" href="javascript:void(false);">
    <img src="<%=contextPath%>/lib/console/images/up.gif" name="sbToggleImg" id="toggleImg">
    </a>
    </div>-->
    <div id="iframe1_content"><iframe id="iframe1" src="<%=contextPath%>/jsp/console/getGridToolbarList.action?resourceName=<s:write property="resourceName"/>&pageKey=<s:write property="pageKey"/>&pageCode=<s:write property="pageCode"/>&toolbarsKey=<s:write property="toolbarsKey"/>" frameborder="0"></iframe></div>
	
	<div id="iframe2_content"><iframe id="iframe2" src="" frameborder="0"></iframe></div> 
	<script type="text/javascript">
		if (window.navigator.appName.toUpperCase()!="OPERA") { 
			var sbState="e";
		}
		function toggleFrame() {
			div1=document.getElementById("iframe1_content");
			div2=document.getElementById("iframe2_content");
			var listss=document.getElementById("iframe2");
			var toggleImg=document.getElementById("toggleImg");
			
			if (sbState=='c') {
				div1.style.height=675;
				div2.style.height=0;
				listss.style.height=0;
				toggleImg.src="<%=contextPath%>/lib/console/images/up.gif";
			} else {
				div1.style.height=320;
				div2.style.height=320;
				listss.style.height=320;
				toggleImg.src="<%=contextPath%>/lib/console/images/down.gif";
			}
			sbState=(sbState=="c")?"e":"c";
		}
	</script>  
</body>
</html>
