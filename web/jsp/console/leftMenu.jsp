<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.framework.common.util.SysConstants"%>
<%@ page import="com.framework.common.servlet.http.RequestHash"%>
<%@ page import="com.application.console.biz.PageModuleBiz" %>
<%@ page import="com.application.console.model.PageModule" %>

<%
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0);
%>
<%
	String contextPath=request.getContextPath();	
	RequestHash reh=new RequestHash(request,response);	
try{	
	
	PageModuleBiz biz=new PageModuleBiz();
	biz.setReh(reh);
	List list=biz.getPageModuleList();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=contextPath%>/lib/console/css/css.css" type="text/css"/>
<link rel="stylesheet" href="<%=contextPath%>/lib/console/css/xtree.css" type="text/css"/>
<script type="text/javascript" src="<%=contextPath%>/lib/console/js/tree/xtree.js"></script>
</head>
<body>
<div id="leftSide">	
	<div class="item2">
		<div class="title"><h2><%=SysConstants.product_name%></h2></div>
		<div class="itemContent boxScroll">
			<script type="text/javascript">		
				var path = "<%=contextPath%>/lib/console/images/xtree/";				
				var webFXTreeConfig = {
					rootIcon        : path + 'base.gif',
					openRootIcon    : path + 'base.gif',
					folderIcon      : path + 'folder.gif',
					openFolderIcon  : path + 'file_open.gif',
					fileIcon        : path + 'files.gif',
					iIcon           : path + 'line.gif',
					lIcon           : path + 'joinbottom.gif',
					lMinusIcon      : path + 'minusbottom.gif',
					lPlusIcon       : path + 'plusbottom.gif',
					tIcon           : path + 'join.gif',
					tMinusIcon      : path + 'minus.gif',
					tPlusIcon       : path + 'plus.gif',
					blankIcon       : path + 'empty.gif',
					defaultText     : 'Tree Item',
					defaultAction   : 'javascript:void(0);',
					defaultBehavior : 'classic',
					usePersistence	: true
				};
		        var root = new WebFXTree("PowerBOS","","","","","");
				root.setBehavior('classic');
				if (document.getElementById) {	
					var sysConfig=new WebFXTreeItem("系统配置","",root,"","","mainboard");	
					var sysSet=new WebFXTreeItem("系统设置","<%=contextPath%>/jsp/console/getSystemConfig.action",sysConfig,"","","mainboard");				
					var dataConfig=new WebFXTreeItem("数据安装","<%=contextPath%>/jsp/console/getSysDatabaseConfig.action",sysConfig,"","","mainboard");						
					var sysPlugin=new WebFXTreeItem("插件管理","<%=contextPath%>/jsp/console/getSystemPluginList.action",sysConfig,"","","mainboard");	
					
								
					var vewDev=new WebFXTreeItem("视图定制","",root,"","","mainboard");	
					var pageConfigs=new WebFXTreeItem("页面空间","",vewDev,"","","mainboard");					
					var pageResources=new WebFXTreeItem("页面资源","<%=contextPath%>/jsp/console/getPageResourceList.action",pageConfigs,"","","mainboard");
					var pageModules=new WebFXTreeItem("页面模块","<%=contextPath%>/jsp/console/getPageModuleList.action",pageConfigs,"","","mainboard");
					var pageCustomize=new WebFXTreeItem("页面定制","",vewDev,"","","mainboard");					
					<%
						for(int i=0;i<list.size();i++){
							PageModule vo=(PageModule)list.get(i);
							String action=contextPath+"/jsp/console/getPageWidgetList.action?resourceName="+vo.getResourceName();
					%>
					var module=new WebFXTreeItem("<%=vo.getModuleName()%>","<%=action%>",pageCustomize,"","","mainboard");	
					<%
						}
					%>					
					document.write(root);
				}				
			</script>
		</div>
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
