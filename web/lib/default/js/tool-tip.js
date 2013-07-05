//$Id

/***********************************************
* Cool DHTML tooltip script II- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

var offsetfromcursorX=20; //Customize x offset of tooltip
var offsetfromcursorY=5; //Customize y offset of tooltip

var offsetdivfrompointerX=10; //Customize x offset of tooltip DIV relative to pointer image
var offsetdivfrompointerY=14; //Customize y offset of tooltip DIV relative to pointer image. Tip: Set it to (height_of_pointer_image-1).

var ie=document.all;
var ns6=document.getElementById && !document.all;
var tipobj;
var pointerobj;
var enabletip=false;

document.write('<div id="dhtmltooltip"></div>') //write out tooltip DIV
document.write('<div id="dhtmlpointer" > <img src="lib/default/images/arrow2.gif"> </div>') //write out pointer image

function clearTimeOut()
{
	if(enabletip)
	{
	}
}

function ddrivetip(elm,e,thetext,isPointerRequired,autoclose,thewidth,thecolor){
	if (ns6||ie){
		tipobj = document.getElementById("dhtmltooltip");
		pointerobj=document.getElementById("dhtmlpointer");
		tipobj.style.visibility="hidden";
		pointerobj.style.visibility="hidden";
		/*if (typeof thewidth!="undefined") 
		{
		 tipobj.style.width=thewidth+"px";
		}*/
		if (typeof thecolor!="undefined" && thecolor!="") {
			tipobj.style.backgroundColor=thecolor;
		}
                var v = '<table width="180" id="tiptable" width='+thewidth+' border="0"><tr><td class="blacktxt1" align="left">'+thetext+'</td></tr>';
                v = v+'</table>';
                tipobj.innerHTML = v;
		tipobj.style.width=document.getElementById("tiptable").offsetWidth;
		enabletip=true;
		if(e!=null)
		{
			setTimeOutForMenu();
			positiontip(e);
		}
		else
		{
		    if(isPointerRequired==true)
			{ 
			 var destx = elm.offsetLeft;
			 var desty = elm.offsetTop;
			}
			else
			{
			 var destx = elm.offsetLeft+140;
			 var desty = elm.offsetTop-50;
			} 
			var thisNode = elm;
			while (thisNode.offsetParent &&
					(thisNode.offsetParent != document.body)) {
				thisNode = thisNode.offsetParent;
				destx += thisNode.offsetLeft;
				desty += thisNode.offsetTop;
			}
			desty = desty+elm.offsetHeight;
			tipobj.style.left=destx+"px";
			pointerobj.style.left=(destx+5)+"px";
			pointerobj.style.top=(desty)+"px";
			tipobj.style.top=(desty+pointerobj.offsetHeight)+"px";
			if(thetext!="")
			{
			  tipobj.style.visibility="visible";
			 pointerobj.style.visibility="visible";
			}
			else
			{
			  tipobj.style.visibility="hidden";
			  pointerobj.style.visibility="hidden";
			}
			if(isPointerRequired==true)
			{
			  tipobj.style.visibility="visible";
			 pointerobj.style.visibility="visible";
			} 

		}
		return false;
	}
}


var nondefaultpos =false;
function positiontip(e){
	if (enabletip){
		nondefaultpos=false;
		
		var curX=(ns6)?e.pageX : event.clientX+ietruebody().scrollLeft;
		var curY=(ns6)?e.pageY : event.clientY+ietruebody().scrollTop;
		//Find out how close the mouse is to the corner of the window
		var winwidth=ie&&!window.opera? ietruebody().clientWidth : window.innerWidth-20;
		var winheight=ie&&!window.opera? ietruebody().clientHeight : window.innerHeight-20;

		var rightedge=ie&&!window.opera? winwidth-event.clientX-offsetfromcursorX : winwidth-e.clientX-offsetfromcursorX;
		var bottomedge=ie&&!window.opera? winheight-event.clientY-offsetfromcursorY : winheight-e.clientY-offsetfromcursorY;

		var leftedge=(offsetfromcursorX<0)? offsetfromcursorX*(-1) : -1000;
		pointerobj.childNodes[0].src = "lib/default/images/arrow2.gif";
		//if the horizontal distance isn't enough to accomodate the width of the context menu
		if (rightedge<tipobj.offsetWidth){
			//move the horizontal position of the menu to the left by it's width
			tipobj.style.left=curX-tipobj.offsetWidth+"px";			
			pointerobj.style.left=curX-25+"px";
			pointerobj.childNodes[0].src = "lib/default/images/arrow2.gif";
			//nondefaultpos=true;
		}
		else if (curX<leftedge){
					

			tipobj.style.left="5px";
			pointerobj.style.left="10px";
		}
		else{
					

			//position the horizontal position of the menu where the mouse is positioned
			tipobj.style.left=curX+offsetfromcursorX-offsetdivfrompointerX+"px";
			pointerobj.style.left=curX+offsetfromcursorX+"px";
		}

		//same concept with the vertical position
		if (bottomedge<tipobj.offsetHeight){
			tipobj.style.top=curY-tipobj.offsetHeight-offsetfromcursorY-15+"px";
			pointerobj.style.top=curY-tipobj.offsetHeight-offsetfromcursorY-pointerobj.offsetHeight+"px";
			nondefaultpos=true;
		}
		else{
			tipobj.style.top=curY+offsetfromcursorY+offsetdivfrompointerY+"px";
			pointerobj.style.top=curY+offsetfromcursorY+"px";
		}
/*
		tipobj.style.visibility="visible";
		if (!nondefaultpos){
		
			pointerobj.style.visibility="visible";
		}
		else{
			pointerobj.style.visibility="hidden";
			
		}
*/
		
	}
}

function delayhideddrivetip()
{
	if (NS5||IE5)
	{
		delayVal = setTimeout("hideddrivetip()", 150);
	}
}

function hideddrivetip()
{
			//alert("TMK");	
	hide();
        //fadecounter1=1;
	//startHideFadeTimer(fadespeed);
}

function hide()
{
	if (ns6||ie)
	{
		enabletip=false;
		tipobj.style.visibility="hidden";
		pointerobj.style.visibility="hidden";
		pointerobj.style.left="-1000px";
		tipobj.style.left="-1000px";
		tipobj.style.backgroundColor='';
		tipobj.style.width='';
	}
}

var fadecounter=0;
var fadecounter1=1;
var fadespeed = 0.03;

function setTimeOutForMenu()
{
	if (NS5||IE5)
	{
		delayShowVal = setTimeout("startFade()", 50);
	}
}

function clearTimeOutForMenu()
{
	if(enabletip)
	{
		clearTimeOut(delayShowVal);
	}
}

function startFade() 
{
		if (!nondefaultpos){
	
		tipobj.style.visibility="visible";
			pointerobj.style.visibility="visible";
		}
		else{
		tipobj.style.visibility="hidden";
			pointerobj.style.visibility="hidden";
			
		}
        fadecounter=0;
        startFadeTimer(fadespeed);
}

function startHideFadeTimer(fadespeed) 
{
        fadecounter1=fadecounter1-fadespeed;
        if(fadecounter1<0)
	{
		fadecounter1 = 0;
	}
        tipobj.style.opacity=fadecounter1;
        pointerobj.style.filter="alpha(opacity="+parseInt(100*fadecounter1)+")";
        tipobj.style.filter="alpha(opacity="+parseInt(100*fadecounter1)+")";
        pointerobj.style.opacity=fadecounter1;
        if (fadecounter1>0)
	{
                setTimeout("startHideFadeTimer("+fadespeed+")",20);
	}
}

/**
* Keep changing the opacity till we reach the maximum(1)
*/
function startFadeTimer(fadespeed) 
{
        fadecounter=fadecounter+fadespeed;
        if(fadecounter>1)
	{
		fadecounter = 1;
	}
        tipobj.style.opacity=fadecounter;
        pointerobj.style.filter="alpha(opacity="+parseInt(100*fadecounter)+")";
        tipobj.style.filter="alpha(opacity="+parseInt(100*fadecounter)+")";
        pointerobj.style.opacity=fadecounter;
        if (fadecounter<1)
	{
                setTimeout("startFadeTimer("+fadespeed+")",20);
	}
}
function ietruebody(){
        return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body;
}

document.onmousemove=positiontip
