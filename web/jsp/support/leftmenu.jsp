<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.framework.common.servlet.http.RequestHash"%>
<%@ page import="com.framework.common.util.SysConstants"%>
<%@ page import="com.application.support.biz.SessionMonitor"%>
<%@ page import="com.application.support.biz.UserMenuBiz"%>
<%@ page import="com.application.support.model.LoginInfo" %>
<%@ page import="com.application.support.model.Function" %>

<% 
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
%>
<%
	String contextPath=request.getContextPath();	 	
	RequestHash reh=new RequestHash(request,response);	
try{	
	SessionMonitor sessionMonitor=new SessionMonitor(reh);
	LoginInfo loginInfo=(LoginInfo)sessionMonitor.getLoginInfo(reh.getSession());	
	String topMenuId=reh.get("topMenuId");
	String topMenuName=reh.get("topMenuName");
	UserMenuBiz biz=new UserMenuBiz();
	biz.setReh(reh);	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=SysConstants.app_name%></title>
<link rel="stylesheet" href="<%=contextPath%>/lib/default/css/global.css" type="text/css"/>
<link rel="stylesheet" href="<%=contextPath%>/lib/default/css/left.css" type="text/css"/>
</head>
<body>
<div id="sidebar">
  <div id="sidebar_inner">
	<div id="sidebar_top"></div>
		<%
		    List sub2=biz.loadUserPerviewMenu(loginInfo,topMenuId);	
			for(int i=0;i<sub2.size();i++){
				Function function=(Function)sub2.get(i);	
				if(!function.getFunctionUrl().startsWith("/"))
					function.setFunctionUrl("/"+function.getFunctionUrl());				
        		List sub3=biz.loadUserPerviewMenu(loginInfo,String.valueOf(function.getId()));            		
        		if(sub3.size()==0){
		%>
			   <%
			     if(i==0){
			   %>   
			     <ul>
		          <li class="Folder"><span class="titletxt"><%=topMenuName%></span>	
		           <ul>	   
			   <%
			     }
			   %>
		           <li><a href="<%=contextPath.concat(function.getFunctionUrl())%>" target="mainboard"><span><%=function.getFunctionName()%></span></a></li>    
	           <%
	             if(i+1==sub2.size()){
	           %>
		           </ul>
		          </li>
		         </ul>  
	           <%
	             }
	           %>		   			
		<%
        		}else{
        %>       
                 <ul>
		          <li class="Folder"><span class="titletxt"><%=function.getFunctionName()%></span>	
		           <ul>	      
                      <%
                        for(int j=0;j<sub3.size();j++){                 	
                            function=(Function)sub3.get(j);	   
                            if(!function.getFunctionUrl().startsWith("/"))
            					function.setFunctionUrl("/"+function.getFunctionUrl());
                      %>
                      <li><a href="<%=contextPath.concat(function.getFunctionUrl())%>" target="mainboard"><span><%=function.getFunctionName()%></span></a></li>
                      <%
                        }
                      %>
                    </ul>
		          </li>
		         </ul>  
        <%			
        		}
			}			
		%>	
	<div id="sidebar_bottom"><span>&nbsp;</span></div>
  </div>
</div>
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
