function Today(){
	var day="";
	var month="";
	var ampm="";
	var ampmhour="";
	var myweekday="";
	var year="";
	mydate=new Date();
	myweekday=mydate.getDay();
	mymonth=mydate.getMonth()+1;
	myday= mydate.getDate();
	myyear= mydate.getYear();
	year=(myyear > 200) ? myyear : 1900 + myyear;
	if(myweekday == 0)
	weekday=" 星期日 ";
	else if(myweekday == 1)
	weekday=" 星期一 ";
	else if(myweekday == 2)
	weekday=" 星期二 ";
	else if(myweekday == 3)
	weekday=" 星期三 ";
	else if(myweekday == 4)
	weekday=" 星期四 ";
	else if(myweekday == 5)
	weekday=" 星期五 ";
	else if(myweekday == 6)
	weekday=" 星期六 ";
	document.write("<font color='#D93202'>"+year+"年"+mymonth+"月"+myday+"日 "+weekday +"</font>");
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

function hover(obj) {
	obj.className="Menubar-hover";
}

function normal(obj) {
	obj.className="Menubar-normal";
}

function active(obj) {
	obj.className="Menubar-active";
}

function down(obj)
{
	  obj.style.left = "1px";
	  obj.style.top = "1px";
	  obj.style.position = "relative";
}

function up(obj) {
	  obj.style.left = "0px";
	  obj.style.top = "0px";
	  obj.style.position = "relative";
}  

//////////图片渐变//////////////////////////////////////////////////////////////////////////////////////////
// Flash Image Extension for Dreamwever ,by Yichun Yuan(dezone@sina.com)
nereidFadeObjects = new Object();
nereidFadeTimers = new Object();
function nereidFade(object, destOp, rate, delta){
if (!document.all)
return
    if (object != "[object]"){  //do this so I can take a string too
        setTimeout("nereidFade("+object+","+destOp+","+rate+","+delta+")",0);
        return;
    }
    clearTimeout(nereidFadeTimers[object.sourceIndex]);
    diff = destOp-object.filters.alpha.opacity;
    direction = 1;
    if (object.filters.alpha.opacity > destOp){
        direction = -1;
    }
    delta=Math.min(direction*diff,delta);
    object.filters.alpha.opacity+=direction*delta;
    if (object.filters.alpha.opacity != destOp){
        nereidFadeObjects[object.sourceIndex]=object;
        nereidFadeTimers[object.sourceIndex]=setTimeout("nereidFade(nereidFadeObjects["+object.sourceIndex+"],"+destOp+","+rate+","+delta+")",rate);
    }
}
//////////背景音乐////////////////////////////////////////////////////////////////////////////////////////////
//document.write('<bgsound src="lib/sounds/fly.mid" loop="-1">');

function TreeShow()
{
	Treehide.style.display="";
	Treeshow.style.display="none";
	Tree.style.display="";
}
function TreeHide()
{
	Treehide.style.display="none";
	Treeshow.style.display="";
	Tree.style.display="none";
}