//Utility Functions
//initJs
window.onload = initJS;
// Initialize
function initJS()
{
	initHeader();
	initTbody();
}
/////////////////////////////////////////////////////////////////////////////////////////////////

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval("window.open("+"'"+selObj.options[selObj.selectedIndex].value+"','"+targ+"','')");
  if (restore) selObj.selectedIndex=0;
}

function MM_reloadPage(init) { //reloads the window if Nav4 resized
 if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
  document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
 else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
// -->

function MM_findObj(n, d) { //v4.0
 var p,i,x; if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
  d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
 if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
 for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
 if(!x && document.getElementById) x=document.getElementById(n); return x;
}

function MM_dragLayer(objName,x,hL,hT,hW,hH,toFront,dropBack,cU,cD,cL,cR,targL,targT,tol,dropJS,et,dragJS) { //v3.0
 //Copyright 1998 Macromedia, Inc. All rights reserved.
 var i,j,aLayer,retVal,curDrag=null,NS=(navigator.appName=='Netscape'), curLeft, curTop;
 if (!document.all && !document.layers) return false;
 retVal = true; if(!NS && event) event.returnValue = true;
 if (MM_dragLayer.arguments.length > 1) {
  curDrag = MM_findObj(objName); if (!curDrag) return false;
  if (!document.allLayers) { document.allLayers = new Array();
   with (document) if (NS) { for (i=0; i<layers.length; i++) allLayers[i]=layers[i];
    for (i=0; i<allLayers.length; i++) if (allLayers[i].document && allLayers[i].document.layers)
     with (allLayers[i].document) for (j=0; j<layers.length; j++) allLayers[allLayers.length]=layers[j];
   } else for (i=0;i<all.length;i++) if (all[i].style&&all[i].style.position) allLayers[allLayers.length]=all[i];}
  curDrag.MM_dragOk=true; curDrag.MM_targL=targL; curDrag.MM_targT=targT;
  curDrag.MM_tol=Math.pow(tol,2); curDrag.MM_hLeft=hL; curDrag.MM_hTop=hT;
  curDrag.MM_hWidth=hW; curDrag.MM_hHeight=hH; curDrag.MM_toFront=toFront;
  curDrag.MM_dropBack=dropBack; curDrag.MM_dropJS=dropJS;
  curDrag.MM_everyTime=et; curDrag.MM_dragJS=dragJS;
  curDrag.MM_oldZ = (NS)?curDrag.zIndex:curDrag.style.zIndex;
  curLeft= (NS)?curDrag.left:curDrag.style.pixelLeft; curDrag.MM_startL = curLeft;
  curTop = (NS)?curDrag.top:curDrag.style.pixelTop; curDrag.MM_startT = curTop;
  curDrag.MM_bL=(cL<0)?null:curLeft-cL; curDrag.MM_bT=(cU<0)?null:curTop -cU;
  curDrag.MM_bR=(cR<0)?null:curLeft+cR; curDrag.MM_bB=(cD<0)?null:curTop +cD;
  curDrag.MM_LEFTRIGHT=0; curDrag.MM_UPDOWN=0; curDrag.MM_SNAPPED=false; //use in your JS!
  document.onmousedown = MM_dragLayer; document.onmouseup = MM_dragLayer;
  if (NS) document.captureEvents(Event.MOUSEDOWN|Event.MOUSEUP);
 } else {
  var theEvent = ((NS)?objName.type:event.type);
  if (theEvent == 'mousedown') {
   var mouseX = (NS)?objName.pageX : event.clientX + document.body.scrollLeft;
   var mouseY = (NS)?objName.pageY : event.clientY + document.body.scrollTop;
   var maxDragZ=null; document.MM_maxZ = 0;
   for (i=0; i<document.allLayers.length; i++) { aLayer = document.allLayers[i];
    var aLayerZ = (NS)?aLayer.zIndex:aLayer.style.zIndex;
    if (aLayerZ > document.MM_maxZ) document.MM_maxZ = aLayerZ;
    var isVisible = (((NS)?aLayer.visibility:aLayer.style.visibility).indexOf('hid') == -1);
    if (aLayer.MM_dragOk != null && isVisible) with (aLayer) {
     var parentL=0; var parentT=0;
     if (!NS) { parentLayer = aLayer.parentElement;
      while (parentLayer != null && parentLayer.style.position) {
       parentL += parentLayer.offsetLeft; parentT += parentLayer.offsetTop;
       parentLayer = parentLayer.parentElement; } }
     var tmpX=mouseX-(((NS)?pageX:style.pixelLeft+parentL)+MM_hLeft);
     var tmpY=mouseY-(((NS)?pageY:style.pixelTop +parentT)+MM_hTop);
     var tmpW = MM_hWidth; if (tmpW <= 0) tmpW += ((NS)?clip.width :offsetWidth);
     var tmpH = MM_hHeight; if (tmpH <= 0) tmpH += ((NS)?clip.height:offsetHeight);
     if ((0 <= tmpX && tmpX < tmpW && 0 <= tmpY && tmpY < tmpH) && (maxDragZ == null
       || maxDragZ <= aLayerZ)) { curDrag = aLayer; maxDragZ = aLayerZ; } } }
   if (curDrag) {
    document.onmousemove = MM_dragLayer; if (NS) document.captureEvents(Event.MOUSEMOVE);
    curLeft = (NS)?curDrag.left:curDrag.style.pixelLeft;
    curTop = (NS)?curDrag.top:curDrag.style.pixelTop;
    MM_oldX = mouseX - curLeft; MM_oldY = mouseY - curTop;
    document.MM_curDrag = curDrag; curDrag.MM_SNAPPED=false;
    if(curDrag.MM_toFront) {
     eval('curDrag.'+((NS)?'':'style.')+'zIndex=document.MM_maxZ+1');
     if (!curDrag.MM_dropBack) document.MM_maxZ++; }
    retVal = false; if(!NS) event.returnValue = false;
  } } else if (theEvent == 'mousemove') {
   if (document.MM_curDrag) with (document.MM_curDrag) {
    var mouseX = (NS)?objName.pageX : event.clientX + document.body.scrollLeft;
    var mouseY = (NS)?objName.pageY : event.clientY + document.body.scrollTop;
    newLeft = mouseX-MM_oldX; newTop = mouseY-MM_oldY;
    if (MM_bL!=null) newLeft = Math.max(newLeft,MM_bL);
    if (MM_bR!=null) newLeft = Math.min(newLeft,MM_bR);
    if (MM_bT!=null) newTop = Math.max(newTop ,MM_bT);
    if (MM_bB!=null) newTop = Math.min(newTop ,MM_bB);
    MM_LEFTRIGHT = newLeft-MM_startL; MM_UPDOWN = newTop-MM_startT;
    if (NS) {left = newLeft; top = newTop;}
    else {style.pixelLeft = newLeft; style.pixelTop = newTop;}
    if (MM_dragJS) eval(MM_dragJS);
    retVal = false; if(!NS) event.returnValue = false;
  } } else if (theEvent == 'mouseup') {
   document.onmousemove = null;
   if (NS) document.releaseEvents(Event.MOUSEMOVE);
   if (NS) document.captureEvents(Event.MOUSEDOWN); //for mac NS
   if (document.MM_curDrag) with (document.MM_curDrag) {
    if (typeof MM_targL =='number' && typeof MM_targT == 'number' &&
      (Math.pow(MM_targL-((NS)?left:style.pixelLeft),2)+
       Math.pow(MM_targT-((NS)?top:style.pixelTop),2))<=MM_tol) {
     if (NS) {left = MM_targL; top = MM_targT;}
     else {style.pixelLeft = MM_targL; style.pixelTop = MM_targT;}
     MM_SNAPPED = true; MM_LEFTRIGHT = MM_startL-MM_targL; MM_UPDOWN = MM_startT-MM_targT; }
    if (MM_everyTime || MM_SNAPPED) eval(MM_dropJS);
    if(MM_dropBack) {if (NS) zIndex = MM_oldZ; else style.zIndex = MM_oldZ;}
    retVal = false; if(!NS) event.returnValue = false; }
   document.MM_curDrag = null;
  }
  if (NS) document.routeEvent(objName);
 } return retVal;
}


// Setting cookiesfor the function & javascripts
function set_cookie ( name, value, exp_y, exp_m, exp_d, path, domain, secure )
{
  var cookie_string = name + "=" + escape ( value );

  if (exp_y) //delete_cookie(name)
  {
    var expires = new Date ( exp_y, exp_m, exp_d );
    cookie_string += "; expires=" + expires.toGMTString();
  }

  if (path) cookie_string += "; path=" + escape ( path );
  if (domain) cookie_string += "; domain=" + escape ( domain );
  if (secure) cookie_string += "; secure";

  document.cookie = cookie_string;
}

// Retrieving cookies
function get_cookie(cookie_name)
{
  var results = document.cookie.match(cookie_name + '=(.*?)(;|$)');
  if (results) return (unescape(results[1]));
  else return null;
}

// Delete cookies 
function delete_cookie( cookie_name )
{
  var cookie_date = new Date ( );  // current date & time
  cookie_date.setTime ( cookie_date.getTime() - 1 );
  document.cookie = cookie_name += "=; expires=" + cookie_date.toGMTString();
}

// Get object
function getobj(id)
{
	return document.getElementById(id);
}

// Initialize the status of header
function initHeader(){
	var header=get_cookie("header");
	var toplogo	= document.getElementById("toplogo");
	var topmenu	= document.getElementById("topmenu");
	var description	= document.getElementById("desc");
	var hello	= document.getElementById("hello");
	if(!toplogo || !topmenu || !description || !hello) return;
	if(header=="none"){
		toplogo.style.display="none";
		topmenu.style.display="none";
		description.style.display="none";
		//hello.style.display="none";
	}
	var x = document.getElementById("header");
	if(!x) return;
	if(header=="none"){
		x.title = "双击显示头部";
	}else{
		x.title = "双击隐藏头部";
	}
	x.ondblclick = showHideHeader;
}

// Show/Hide the header
function showHideHeader(){
	var header = document.getElementById("header");
	var toplogo	= document.getElementById("toplogo");
	var topmenu	= document.getElementById("topmenu");
	var description	= document.getElementById("desc");
	var hello	= document.getElementById("hello");
	if(!toplogo || !topmenu || !description || !hello) return;
	if(toplogo.style.display=="none"){
		toplogo.style.display="";
		topmenu.style.display="";
		description.style.display="";
		//hello.style.display="";
		header.title = "双击隐藏头部";

		set_cookie("header","");
	}else{
		toplogo.style.display="none";
		topmenu.style.display="none";
		description.style.display="none";
		//hello.style.display="none";
		header.title = "双击显示头部";

		set_cookie("header","none");
	}
}

//下拉菜单
function showSubNav(item) {
	items = item.getElementsByTagName('UL');
	if(items.length > 0) {
		items[0].style.display = 'block';
		//items[0].style.top = item.offsetTop+item.offsetHeight+'px';
		//items[0].style.left = item.offsetLeft+'px';
		//alert(item.innerHTML+", "+item.offsetTop);
		//HideOverSels(items[0]);
	}
}

function hideSubNav(item) {
	items = item.getElementsByTagName('UL');
	if(items.length > 0) {
		items[0].style.display = 'none';
		//ShowOverSels(items[0]);
	}
}
var ie55up = IsIE55Up();

// 隐藏被ID为objID的对象（层）遮挡的所有select
function HideOverSels(objID)
{

	var obj = objID;
	// 只有IE5.5以上Iframe的z-index才有效
	if (ie55up)
	{
		var overIframe = document.createElement("<iframe src='about:blank' style='position:absolute;left:0;top:0;z-index:998;display:none' scrolling='no' frameborder='0'></iframe>");
		obj.insertAdjacentElement("beforeBegin",overIframe);
		with(overIframe.style)
		{
			top = obj.style.top;
			left = obj.style.left;
			width = obj.offsetWidth;
			height = obj.offsetHeight;
			display = 'block';
		}
		obj.style.zIndex = "999";
	}
}
function ShowOverSels(objID)
{

	var obj = objID;
	overIframe = obj.parentNode.getElementsByTagName('iframe');
	if (overIframe.length > 0){
		overIframe[0].outerHTML = "";
	}
}

// 是否IE5.5以上版本
function IsIE55Up () {
	var agt = navigator.userAgent.toLowerCase();
	var isIE = (agt.indexOf("msie")!=-1);
	if (isIE)
	{
		var stIEVer = agt.substring(agt.indexOf("msie ") + 5);
		var verIEFull = parseFloat(stIEVer);
		return verIEFull >= 5.5;
	}
	return false;
}

// Show/Hide a layer by id
function showHideLayer(id){
	var y	= document.getElementById(id);
	if(!y) return;
	if(y.style.display==""){
		y.style.display="none";
	}else{
		y.style.display="";
	}
}

// Open a window in full screen
function openwin_full(url) {
	var scrWidth  = screen.availWidth;
	var scrHeight = screen.availHeight;
	var sgcc = window.open(url, "PowerBOS", "resizable=1");
	sgcc.moveTo(0, 0);
	sgcc.resizeTo(scrWidth, scrHeight);
	window.opener = null;
	window.close(); 
}

// Show/Hide the tbody - <div class="tbody"></div>
function initTbody()
{
	var ptitles = document.getElementsByTagName('table');	
	if (ptitles.length < 0) return;	
	for (var i = 0; i < ptitles.length; i++ ){
		if (ptitles[i].className == "ptitle"){			
			ptitles[i].onclick = showHideTbody;
		}
	}
}
function showHideTbody(event)
{
	var t = window.event.srcElement.parentNode.parentNode.parentNode.parentNode;
	var tdivs = t.getElementsByTagName('div');
	for (var i = 0; i < tdivs.length ; i++ ){
		if (tdivs[i].className == 'tbody'){
			tbody = tdivs[i];
			break;
		}
	}
	if (tbody.style.display == "none"){
		tbody.style.display = "block";		
	}else{
		tbody.style.display = "none";
	}
}

// Show/Hide the popup
function popup(title,content)
{
	var str = '';
	str = str + '<div id="error" class="popup">';
	str = str + 	'<div class="closer" onclick="showHideLayer(\'error\');" title="关闭"></div>';
	str = str + 	'<div class="title">' + title + '</div>';
	str = str + 	'<div class="content">' + content + '</div>';
	str = str + 	'<div class="action"><a href="javascript:void(0);" class="btn-primary"><span>确定</span></a></div>';
	str = str + '</div>';
	
	document.write(str);
}

