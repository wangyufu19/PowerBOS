<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.framework.common.util.SysConstants"%>
<%@ page import="com.framework.common.servlet.http.RequestHash"%>
<%@ page import="com.application.support.biz.OrgMgrBiz" %>
<%@ page import="com.framework.view.tree.XtreePanel"%>
<%@ page import="com.framework.view.tree.XtreeLoader"%>
<%@ page import="com.framework.view.tree.TreeNode"%>

<% 
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
%>
<%
	String contextPath =request.getContextPath();
	RequestHash reh=new RequestHash(request,response);
    String parentId=reh.get("parentId");
	if("".equals(parentId)||parentId==null||"null".equals(parentId)){
		parentId="0";	
	}		
	String CODE=reh.get("CODE");
try{
	OrgMgrBiz biz=new OrgMgrBiz();
	biz.setReh(reh);
	List list=biz.getChildOrganizationList(parentId);
	if(list==null) list=new ArrayList();	
	
	XtreeLoader xtreeLoader=new XtreeLoader();
	for(int i=0;i<list.size();i++){
		 Object[] obj=(Object[])list.get(i);    	  
		 TreeNode treeNode=new TreeNode();
		 treeNode.setId(String.valueOf(obj[0]));
		 treeNode.setText(String.valueOf(obj[3]));
		 treeNode.setIsleaf(String.valueOf(obj[4]));
		 treeNode.setTarget("dtable");
		 treeNode.setSrc(contextPath+"/jsp/support/getChildOrgTree.action?CODE="+CODE+"&parentId="+treeNode.getId()+"&target="+treeNode.getTarget());
		 treeNode.setAction(contextPath+"/jsp/support/dynamic_page.jsp?CODE="+CODE+"&parentId="+treeNode.getId());
		 xtreeLoader.addTreeNode(treeNode);
	}
	TreeNode rootNode=new TreeNode();
	rootNode.setText("机构导航");
	rootNode.setAction(contextPath+"/jsp/support/dynamic_page.jsp?CODE="+CODE+"&parentId="+parentId);
	rootNode.setTarget("dtable");	
	XtreePanel xtreePanel=new XtreePanel();
	xtreePanel.setContextPath(contextPath);
	xtreePanel.setXtreeLoader(xtreeLoader);	
	xtreePanel.setRootNode(rootNode);
%>
<html>
<head>
<title><%=SysConstants.app_name%></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/lib/default/css/xtree.css" />
<script type="text/javascript" src="<%=contextPath%>/lib/default/js/tree/xtree.js"></script>
<script type="text/javascript" src="<%=contextPath%>/lib/default/js/tree/xmlextras.js"></script>
<script type="text/javascript" src="<%=contextPath%>/lib/default/js/tree/xloadtree.js"></script>

</head>
<body>
<script type="text/javascript">
    <%=xtreePanel.render()%>
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