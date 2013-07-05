<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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
	String username="";
	if(loginInfo!=null) username=loginInfo.getUserName();
	UserMenuBiz biz=new UserMenuBiz();
	biz.setJdbcTmplt(reh.getJdbcTmplt());
	List list=null;	
	list=biz.loadUserPerviewMenu(loginInfo,"0");	
	String defTopMenuId="";
	String defTopMenuName="";
	if(list==null) list=new ArrayList();
%>

<!DOCTYPE html PUBLIC "-//W3C//Dtd html 4.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-CN" />
<meta content="all" name="robots" />
<meta name="author" content="" />
<meta name="Copyright" content=""/>
<meta name="Description" content="" />
<meta name="Keywords" content="" />
<title><%=SysConstants.app_name%></title>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=contextPath%>/lib/default/css/css.css" type="text/css"/>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/default/js/jquery/jquery-1.5.1.min.js"></script>
</head>
<body leftMargin=0 topMargin=0 marginheight="0" marginwidth="0" >
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
	  <!--Header Code-->
      <div id=header>
        <div id=toplogo><a href="#"><img alt="PowerBOS" src="<%=contextPath%>/lib/default/images/blank.gif" border=0></a></div>
		<div id=topmenu>
		  <ul>
			<li id=pass><a href="<%=contextPath%>/jsp/support/dynamic_page.jsp?CODE=OIS_MODIFY_PASSWORD" target="mainboard"><span>修改密码</span></a></li>
			<li id=quit><a href="javascript:logout_f();"><span>退出</span></a></li>
		  </ul>
		</div>
		<div id=desc></div>
		<div id=hello>				  		
		  <div id=username>您好,<span><%=username%></span></div>
		</div>
      </div>
      <!--Header Code-->
      <!--MenuBar Code-->
      <div id=menubar>                 
        <ul>
          <li class="left"></li>
          <%
          	for(int i=0;i<list.size();i++){
          		Function function=(Function)list.get(i);          		
          		if(i==0){
          			defTopMenuId=String.valueOf(function.getId());
          			defTopMenuName=function.getFunctionName();
          			
          		}
          		String leftUrl=function.getFunctionUrl()+"?topMenuId="+function.getId()+"&topMenuName="+function.getFunctionName();	
          		if(leftUrl.startsWith("/"))
          			leftUrl=contextPath.concat(leftUrl);	
          		else
          			leftUrl=contextPath+"/".concat(leftUrl);
          %>
          <li><a href="<%=leftUrl%>" title=""  target="leftboard"><span><%=function.getFunctionName()%></span></a></li>
          <%
          	}
          %>
          <li class="right"></li>
        </ul>            
      </div>
      <!--MenuBar Code-->
      <!--QuickMenu Code-->
      <div id=quickmenu title="快捷菜单栏">      
		 <ul>
          <li class="left"></li>
          <li class="qmtitle"></li>
          <li><a href="" target="mainboard"><span></span></a></li>
          <li class="right"></li>
        </ul>
      </div>
      <!--QuickMenu Code-->
    </td>
  </tr>
  <tr>
    <td height="100%" class="content"><!--Content Code-->
      <script language=JavaScript src="<%=contextPath%>/lib/default/js/general.js" type=text/javascript></script>
      <script language=JavaScript type=text/javascript>
		<!--
		function toggleSideBar(id) {
			var sideBarObj=getObj("sidebar"+id)
			var sideBarImgObj=getObj("sidebarimg"+id)
			if (sideBarObj.style.display=="none") {
				sideBarImgObj.src="<%=contextPath%>/lib/default/images/side_collapse.gif"
				sideBarObj.style.display="block"
			} else {
				sideBarImgObj.src="<%=contextPath%>/lib/default/images/side_expand.gif"
				sideBarObj.style.display="none"
			}
			set_cookie("sidebar"+id,sideBarObj.style.display)
		}
		
		function setSideBarExpColl() {
			for (i = 1 ; i <= parseInt(getObj("totSideBarCnt").value) ; i++) {
				var sideBarObj=getObj("sidebar"+i)
				var toggleImageObj=getObj("sidebarimg"+i)
				var status = get_cookie("sidebar"+i)
				
				if (status == "none" || status==null) {
					toggleImageObj.src="<%=contextPath%>/lib/default/images/side_expand.gif"
					sideBarObj.style.display="none"
				} else {
					toggleImageObj.src="<%=contextPath%>/lib/default/images/side_collapse.gif"
					sideBarObj.style.display="block"
				}
			}
		}
		
		function setSideBarWidth() {
			var sideBarObj=getObj("sidebar")
			var sideBarWidth = get_cookie("sideBarWidth")
			var sideBarState = get_cookie("sideBarState")
			
			if (sideBarState == "none" || sideBarState==null) {
				sbState="e"
				sideBarObj.style.width="162px"
				getObj("sbToggleImg").src="<%=contextPath%>/lib/default/images/btn_remove.jpg"
			} else {
				if (sideBarState == "e") {
					sbState="e"
					getObj("sbToggleImg").src="<%=contextPath%>/lib/default/images/btn_remove.jpg"
					if (sideBarWidth == "none" || sideBarWidth==null) sideBarObj.style.width="162px"	
					else sideBarObj.style.width=sideBarWidth+"px"
				} else {
					sbState="c"
					getObj("sbToggleImg").src="<%=contextPath%>/lib/default/images/btn_add.jpg"
					sideBarObj.style.width="5px"
				}		
			}	
			
			/*
			var domainLayerObj=getObj("domainLayer")
			var dmLayerWidth = get_cookie("domainLayerWidth")
			var dmLayerState = get_cookie("domainLayerState")
			
			if (dmLayerState == "none" || dmLayerState==null) {
				dmState="e"
				domainLayerObj.style.width="162px"
				getObj("dmToggleImg").src="<%=contextPath%>/lib/default/images/btn_remove.jpg"
			} else {
				if (dmLayerState == "e") {
					dmState="e"
					getObj("dmToggleImg").src="<%=contextPath%>/lib/default/images/btn_remove.jpg"
					if (dmLayerWidth == "none" || dmLayerWidth==null) domainLayerObj.style.width="162px"	
					else domainLayerObj.style.width=dmLayerWidth+"px"
				} else {
					dmState="c"
					getObj("dmToggleImg").src="<%=contextPath%>/lib/default/images/btn_add.jpg"
					domainLayerObj.style.width="5px"
				}		
			}
			*/
		}
		
		
		function MM_openBrWindow(theURL,winName,features) { //v2.0
		  window.open(theURL,winName,features);
		}
		/*
		function showHideDomains()
		{
			var obj = document.getElementById('domainListDiv');
			if(obj.style.display == 'none')
			{
				obj.style.display = 'block';
			}
			else
			{
				obj.style.display = 'none';
			}
		}
		*/
		//-->
		</script>
      <table height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
        <tbody>
          <tr>
            <td width="100%" height="100%"><table class=mainboard height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
                <tbody>
                  <tr>
                    <td id=sidebar valign=top width="162px" height="100%">
					  <div id=treeloading style="display:none;"><br>&nbsp;<img src="lib/default/images/s_progressbar.gif"><span class=blacktxtbold>&nbsp;Loading SideBar...</span></div>
					  <iframe id=leftboard name=leftboard src="<%=contextPath%>/jsp/support/leftmenu.jsp?topMenuId=<%=defTopMenuId%>&topMenuName=<%=defTopMenuName%>" frameborder="0" marginwidth="0" marginheight="0" width="100%" height="100%" scrolling="auto"></iframe>
					</td>
                    <td class=resizeLayerBorder onMouseDown="startResize(event,'sidebar')"  width=7 height="100%">
					  <A onClick="toggleFrame('sidebar')" href="javascript:void(false);"><img src="" name="sbToggleImg" width=7 height=80 hspace=1 border=0 id=sbToggleImg></A></td>
                    <td class=mainiframe valign=top height="100%">
					  <iframe id=mainboard name=mainboard src="" frameborder="0" marginwidth="0" marginheight="0" width="100%" height="100%" scrolling="auto"></iframe>
					</td>
                  </tr>
                </tbody>
              </table></td>
          </tr>
        </tbody>
      </table>
      <div onmouseup=endResize(event) onmousemove=doResize(event) id=resizeLayer></div>
      <script>
		if (window.navigator.appName.toUpperCase()!="OPERA") { 
			var rlObj=getObj("resizeLayer");
			var sbWidth=162;
			var dmWidth=162;
			var currLayer;
			var currWidth=0;
			var dmState="e";
			var sbState="e";
		}
		function toggleFrame(layerId) {
			currLayerObj=getObj(layerId)
			rlObj.style.left=rlObj.style.top="-1000px"
			rlObj.style.width=rlObj.style.height=0			
			if (layerId=="domainLayer")	{
				if (dmState=='e') {
					toggleImg="<%=contextPath%>/lib/default/images/btn_add.jpg"
					currLayerObj.style.width=1			
				} else {
					toggleImg="<%=contextPath%>/lib/default/images/btn_remove.jpg"
					currLayerObj.style.width=dmWidth
				}
				getObj("dmToggleImg").src=toggleImg
				set_cookie("domainLayerWidth",dmWidth)
				dmState=(dmState=="c")?"e":"c"		
				set_cookie("domainLayerState",dmState)
			} else {			    
				if (sbState=='e') {
					currLayerObj.style.width=1
					toggleImg="<%=contextPath%>/lib/default/images/btn_add.jpg"
				} else {
					currLayerObj.style.width=sbWidth
					toggleImg="<%=contextPath%>/lib/default/images/btn_remove.jpg"
				}
				getObj("sbToggleImg").src=toggleImg
				set_cookie("sideBarWidth",sbWidth)
				sbState=(sbState=="c")?"e":"c"
				set_cookie("sideBarState",sbState)
			}
		}
		function startResize(ev,layerId) {
			currLayer=layerId
			currLayerObj=getObj(layerId)
			
			if (browser_ie)
				currObj=window.event.srcElement
			else if (browser_nn4 || browser_nn6)
				currObj=ev.target
				
			if (currObj.id!="sbToggleImg" && currObj.id!="dmToggleImg") {
				if (window.navigator.appName.toUpperCase()!="OPERA") { 
					if (browser_ie) {
						rlObj.style.top=(document.body.scrollTop>85) ? findPosY(currLayerObj)-document.body.scrollTop : findPosY(currLayerObj)
						rlObj.style.left=findPosX(currLayerObj)-document.body.scrollLeft
						rlObj.style.width=currLayerObj.offsetWidth+document.body.scrollLeft
						rlObj.style.height=currLayerObj.offsetHeight+document.body.scrollTop
					} else if (browser_nn4 || browser_nn6)  {
						rlObj.style.top=(window.scrollY>85) ? findPosY(currLayerObj)-window.scrollY : findPosY(currLayerObj)
						rlObj.style.left=findPosX(currLayerObj)-window.scrollX
						rlObj.style.width=currLayerObj.offsetWidth+window.scrollX
						rlObj.style.height=currLayerObj.offsetHeight+window.scrollY
					}
					
					if (browser_ie)	{
						rlObj.setCapture()
					} else if (browser_nn4 || browser_nn6) { 
						document.body.style.cursor="e-resize"
						treeFrame.document.body.style.cursor="e-resize"
						objectProperties.document.body.style.cursor="e-resize"
						document.addEventListener("mousemove",doResize,false);
						document.addEventListener("mouseup",endResize,false);
						treeFrame.document.addEventListener("mousemove",doResize,false);
						treeFrame.document.addEventListener("mouseup",endResize,false);	
						objectProperties.document.addEventListener("mousemove",doResize,false);
						objectProperties.document.addEventListener("mouseup",endResize,false);	
					}
				}
			}
		}
		function doResize(ev) {
			clearTextSelection()
			if (browser_ie) {
				var wdt=rlObj.style.pixelWidth+(window.event.clientX-(rlObj.offsetLeft+rlObj.style.pixelWidth))
			} else if (browser_nn4 || browser_nn6) {
				var pixelWdt=parseInt(rlObj.style.width.substring(0,rlObj.style.width.indexOf("p")))
				var wdt=pixelWdt+(ev.pageX-(rlObj.offsetLeft+pixelWdt))
			}
			currWidth=(wdt>1) ? (wdt<400) ? wdt : 400 : 1
			rlObj.style.width=currWidth;
		}
		function endResize(ev) {
			if (browser_ie)	{ 
				rlObj.releaseCapture()
			} else if (browser_nn4 || browser_nn6) {
				document.removeEventListener("mousemove",doResize,false);
				document.removeEventListener("mouseup",endResize,false);
				treeFrame.document.removeEventListener("mousemove",doResize,false);
				treeFrame.document.removeEventListener("mouseup",endResize,false);
				objectProperties.document.removeEventListener("mousemove",doResize,false);
				objectProperties.document.removeEventListener("mouseup",endResize,false);
				treeFrame.document.body.style.cursor=""
				objectProperties.document.body.style.cursor=""
			}
			
			document.body.style.cursor=""
			currLayerObj.style.width=currWidth
			
			rlObj.style.left=rlObj.style.top="-1000px"
			rlObj.style.width=rlObj.style.height=0
		
			if (currLayer=="domainLayer") {
				dmWidth=currWidth
				dmState="e"
				getObj("dmToggleImg").src="<%=contextPath%>/lib/default/images/btn_remove.jpg"
				set_cookie("domainLayerWidth",dmWidth)
				set_cookie("domainLayerState","e")
			} else {
				sbWidth=currWidth
				sbState="e"
				getObj("sbToggleImg").src="<%=contextPath%>/lib/default/images/btn_remove.jpg"
				set_cookie("sideBarWidth",sbWidth)
				set_cookie("sideBarState","e")
			}
		}
		</script>
      <script>
			if (window.navigator.appName.toUpperCase()!="OPERA") setSideBarWidth()
		</script>
      <!--Content Code-->
    </td>
  </tr>
  <tr>
    <td class="footer">
	<div id=footer>
        <div id=bottommenu></div>
        <div id=copyright>技术支持 <%=SysConstants.technology_corp%></div>
        <!--<div id=about><a href="javascript:void(0);" onClick="showHideLayer('aboutbos');"><img alt="About Wbpmp2" src="<%=contextPath%>/lib/default/images/blank.gif" border=0></a>
		  <div id=aboutbos style="display:none;">
			<div class="closer" onClick="showHideLayer('aboutbos');" title="关闭"></div>
			<ul>
			  <li>产&nbsp;&nbsp;&nbsp;&nbsp;品：PowerBOS</li>
			  <li>版&nbsp;&nbsp;&nbsp;&nbsp;本：1.0</li>
			  <li>更新时间：2009-05-20</li>
			  <li>网&nbsp;&nbsp;&nbsp;&nbsp;站：<a href="http://www.pominfo.cn" target="_blank">www.sinobpo.com.cn</a></li>
			  <li>邮&nbsp;&nbsp;&nbsp;&nbsp;件：<a href="mailto:service@sinobpo.com.cn" target="_blank">service@sinobpo.com.cn</a></li>
			</ul>
			<div id=arrowlayer></div>
		  </div>
		</div>-->
      </div>
	</td>
  </tr>
</table>
<SCRIPT language=javascript>
function logout_f(){
	$.ajax({
	   type:"post",
	   url:"<%=contextPath%>/jsp/support/logout.action",
	   contentType:"application/x-www-form-urlencoded; charset=utf-8",
	   success:function(msg){
	  		if(msg!="成功"){
	  			alert(msg);
	  			return;
			}		
			window.location.href="<%=contextPath%>/jsp/support/login.jsp";
	   }
	});	
}
</SCRIPT>
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
