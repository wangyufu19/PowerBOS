<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.framework.common.servlet.http.RequestHash"%>
<%@ page import="com.framework.common.util.SysConstants"%>
<%@ page import="com.framework.view.Viewport"%>
<%
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0);
%>
<%
	String contextPath=request.getContextPath();		
	RequestHash reh=new RequestHash(request,response);			
	
try{	
	String param=reh.getJqueryParameterSerialize();	
	Viewport viewport=new Viewport(reh);		
%>

<!DOCTYPE HTML PUBLIC "-//W3C//Dtd HTML 4.01 Transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
<head>
<title><%=SysConstants.app_name%></title>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<meta http-equiv="Content-Language" content="zh-CN" />
<meta content="all" name="robots" />
<meta name="Copyright" content="" />
<meta name="Description" content="" />
<meta name="Keywords" content="" />
<link href="<%=contextPath%>/lib/default/css/css.css" type=text/css rel=stylesheet>
<link href="<%=contextPath%>/lib/default/js/yui/container/assets/container.css" rel="stylesheet" >
<link href="<%=contextPath%>/lib/default/js/yui/container/assets/alert.css" rel="stylesheet" >
<link href="<%=contextPath%>/lib/fckeditor/editor/css/default.css" rel="stylesheet" >
</head>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/default/js/yui/yahoo/yahoo.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/default/js/yui/dom/dom.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/default/js/yui/event/event.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/default/js/yui/dragdrop/dragdrop.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/default/js/yui/container/container.js"></script>
<script language="javascript" type="text/javascript" src="<%=contextPath%>/lib/default/js/fckeditor/fckeditor.js"></script>


<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/default/js/jquery/jquery-1.5.1.min.js" ></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/default/js/jquery/jquery.form.js" ></script>


<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/default/js/form/dynpage/dynpage.js" charset="GBK"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/default/js/form/dynpage/window.js" charset="GBK"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/default/js/form/dynpage/edit-grid.js" charset="GBK"></script>

<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/default/js/form/check/check.js" charset="GBK"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/default/js/form/check/base.js" charset="GBK"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/default/js/form/check/text.js" charset="GBK"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/default/js/form/checkbox.js" charset="GBK"></script>
<script type="text/javascript">

</script>
<body>
<div id=loading style="display:none;">
	<div class="text">正在处理数据,请稍等...</div>
	<div class="progressbar"></div>
</div>
<div id="maincontent">  
	<%=viewport.render()%>
</div>
<script LANGUAGE="JavaScript" type="text/javascript"> 

	 var contextPath="<%=contextPath%>";
	 var param="<%=param%>"; 	 
	 function showHideLayer(obj){
	 	var cls=$(obj).attr("class");	 	
	 	$("div.tinput div.tbody").toggle();
	 	$("div.tlist div.tbody").toggle();
	 	$("div.tview div.tbody").toggle();
	 }	
 	
	
</script>
</body>
</html>
<%		
	}catch(Exception e){
		reh.clear();
		e.printStackTrace();
	}finally{
		reh.clear();
	}
%>
