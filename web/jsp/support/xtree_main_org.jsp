<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.framework.common.util.SysConstants"%>
<%@ page import="com.framework.common.servlet.http.RequestHash"%>
<%@ page import="com.application.support.biz.SessionMonitor"%>
<%@ page import="com.application.support.model.LoginInfo" %>
<%@ page import="com.application.support.biz.OrgMgrBiz" %>
<% 
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
%>

<%
	String contextPath =request.getContextPath();	
	RequestHash reh=new RequestHash(request,response);
	String CODE=reh.get("CODE");	
	String parentId="";
	SessionMonitor sessionMonitor=new SessionMonitor(reh);
	LoginInfo loginInfo=(LoginInfo)sessionMonitor.getLoginInfo(reh.getSession());		
	if(loginInfo!=null){
		if(loginInfo.getIsAdmin()==1){
			parentId="0";	
		}else{
			OrgMgrBiz biz=new OrgMgrBiz();
			biz.setReh(reh);
			parentId=biz.getOrgParentId(loginInfo.getOrgId());	
		}
		
	}
%>
<html>
<head>
<title><%=SysConstants.app_name%></title>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=contextPath%>/lib/default/css/global.css" type="text/css"/>
<link rel="stylesheet" href="<%=contextPath%>/lib/default/css/table.css" type="text/css"/>
</head> 
<frameset cols="150,*" framespacing="2" frameborder="yes" border="0" bordercolor="#EFEFEF">
  <frame src="<%=contextPath%>/jsp/support/xtree_org.jsp?CODE=<%=CODE%>&parentId=<%=parentId%>" scrolling="auto" frameborder="0" name="dtree" id="dtree"  />
  <frame src="" name="dtable" id="dtable" frameborder="0"/>
</frameset>
<noframes>
<body>
</body>
</noframes>  
</html>


