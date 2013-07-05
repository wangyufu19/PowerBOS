<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.framework.common.servlet.http.RequestHash"%>
<%@ page import="com.framework.common.util.SysConstants"%>
<%@ page import="com.application.support.biz.RoleMgrBiz" %>

<% 
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
%>
<%
    String contextPath = request.getContextPath();	
	RequestHash reh=new RequestHash(request,response);
	String msg="";
	String roleId=reh.get("roleId");
	String doWith=reh.get("doWith");
	if(doWith==null) doWith="";		
try{
	if("save".equals(doWith)){
		//保存角色授权
		RoleMgrBiz biz=new RoleMgrBiz();
		biz.setReh(reh);
		msg=biz.saveRolePerviewList();
	}
	RoleMgrBiz biz=new RoleMgrBiz();
	biz.setReh(reh);
	String treedata=biz.getRolePerviewListByUser(roleId);	
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>	
	<title><%=SysConstants.app_name%></title>	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">	
	<link rel="stylesheet" type="text/css" href="<%=contextPath%>/lib/default/css/dtree.css">
	<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/default/js/tree/xtree.js"></script>
	<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/default/js/tree/webfxcheckboxtreeitem.js"></script>
	<style type="text/css">
		input.tree-check-box {
			width:		auto;
			margin:		0;
			padding:	0;
			height:		14px;
			vertical-align:	middle;
		}
    </style>
</head>
  
<body>

<div class="dtree">
<div align="right"></div>
<a id="openAll" href="javascript:xtree.expandAll();">展开</a> |
<a href="javascript:xtree.collapseAll();">收缩</a> |
<a href="javascript:save()">保存</a> |
<a href="javascript:self.close()">关闭</a>
<hr color="red" width="100%">	

<form name="submitF" method="post" action="">    
  <input type="hidden" name="doWith" value=""/>
  <input type="hidden" name="SELECTEDID" value=""/>
  <input type="hidden" name="id" value="<%=roleId%>"/>
<script language="JavaScript" type="text/javascript">
    var webFXTreeConfig={
	rootIcon:'<%=contextPath%>/lib/default/images/xtree/xp/folder.png',
	openRootIcon:'<%=contextPath%>/lib/default/images/xtree/xp/openfolder.png',
	folderIcon:'<%=contextPath%>/lib/default/images/xtree/xp/folder.png',
	openFolderIcon:'<%=contextPath%>/lib/default/images/xtree/xp/openfolder.png',
	fileIcon:'<%=contextPath%>/lib/default/images/xtree/xp/file.png',
	iIcon           : '<%=contextPath%>/lib/default/images/xtree/xp/I.png',
	lIcon           : '<%=contextPath%>/lib/default/images/xtree/xp/L.png',
	lMinusIcon      : '<%=contextPath%>/lib/default/images/xtree/xp/Lminus.png',
	lPlusIcon       : '<%=contextPath%>/lib/default/images/xtree/xp/Lplus.png',
	tIcon           : '<%=contextPath%>/lib/default/images/xtree/xp/T.png',
	tMinusIcon      : '<%=contextPath%>/lib/default/images/xtree/xp/Tminus.png',
	tPlusIcon       : '<%=contextPath%>/lib/default/images/xtree/xp/Tplus.png',
	blankIcon       : '<%=contextPath%>/lib/default/images/blank.png',
	defaultText     : 'Tree Item',
	defaultAction   : 'javascript:void(0);',
	defaultBehavior : 'classic',
	usePersistence	: true
	};

<%=treedata%>;
</script>   
</form>
<script language="JavaScript" type="text/javascript">
	var msg="<%=msg%>";
	if(msg!=""){
		alert(msg);		
	}
	
	function save(){
	    if (!window.confirm('您确定要保存吗？')) {
			return;
		} 	
		document.submitF.doWith.value="save";   
		document.submitF.submit();	
	}    
</script>
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