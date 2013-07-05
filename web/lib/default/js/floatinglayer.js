//Floating Layer
var theLayer;
function getAbsolutePosition(el) {
	var SL = 0, ST = 0;
	var is_div = /^div$/i.test(el.tagName);
	if (is_div && el.scrollLeft)
		SL = el.scrollLeft;
	if (is_div && el.scrollTop)
		ST = el.scrollTop;
	var r = { x: el.offsetLeft - SL, y: el.offsetTop - ST, width:el.offsetWidth, height: el.offsetHeight };
	if (el.offsetParent) {
		var tmp = getAbsolutePosition(el.offsetParent);
		r.x += tmp.x;
		r.y += tmp.y;
		
	}
	return r;
	}

	function showAtThisElement(el,opts,el1) {

	theLayer = el1;
	el1 = document.getElementById(el1);
	if (!el1) return;

	var self = this;
	
	var p = getAbsolutePosition(el);	
        var elpos = getAbsolutePosition(el1);	                  
	if (!opts || typeof opts != "string") {
		showAtPosition(p.x, p.y + el.offsetHeight,el,el1);		
		return true;
		}
	else{
                //layers width and height
                
              
	        var w = elpos.width;
		var h = elpos.height;
		var valign = opts.substr(0, 1);
		var halign = "l";
		if (opts.length > 1) {
			halign = opts.substr(1, 1);
		}
		// vertical alignment
		switch (valign) {
		    case "T": p.y -= h;break;
		    case "B": p.y += el.offsetHeight;break;
		    case "C": p.y += (el.offsetHeight - 100) / 2; break;// - h
		    case "t": p.y += el.offsetHeight - 100; break;// - h
		    case "b": break; // already there
		}
		// horizontal alignment
		switch (halign) {
		    case "L": p.x -= w;break;
		    case "R": p.x += el.offsetWidth; break;
		    case "C": p.x += (el.offsetWidth - 400) / 2; break;// - w
		    case "r": p.x += el.offsetWidth - 400; break;// - w
		    case "l": break; // already there
		}
		p.width = w;
		p.height = h + 40;
		//fixPosition(p);
		showAtPosition(p.x, p.y,el,el1);
	    }
	}
	
	function showAtPosition(x, y,el,el1) {
	var s = el1.style;	
	s.left = x + "px";
	s.top = y + "px";	
	show(s,el1);
        }
	
        function show(pos,el1){
      
        if (isIE||isNN){
        el1.style.left=pos.left;
        el1.style.top=pos.top;
        el1.style.display="";

/*	 whichDog.style.left=pos.left;
        whichDog.style.top=pos.top;
        whichDog.style.visibility="visible";
*/
        }
        else if (isN4) {
        el1.style.left=pos.left;
        el1.style.top=pos.top;
        el1.style.display="";
                        }
		//Hide the <Select>
		//showHideFrameCombos('hidden');
        }
        
        
// Script Source: CodeLifter.com
// Copyright 2003
// Do not remove this header

isIE=document.all;
isNN=!document.all&&document.getElementById;
isN4=document.layers;
isHot=false;

function ddInit(e){
  topDog=isIE ? "BODY" : "HTML";
  //alert(theLayer);
  whichDog=isIE ? document.all(theLayer):document.getElementById(theLayer);
  hotDog=isIE ? event.srcElement : e.target;
  //while (hotDog.id!="titleBar"&&hotDog.tagName!=topDog){
  //  hotDog=isIE ? hotDog.parentElement : hotDog.parentNode;
  //}
  if (hotDog.id=="titleBar"){
    offsetx=isIE ? event.clientX : e.clientX;
    offsety=isIE ? event.clientY : e.clientY;
    nowX=parseInt(whichDog.style.left);
    nowY=parseInt(whichDog.style.top);
    ddEnabled=true;
    document.onmousemove=dd;
  }
}

function dd(e){
  if (!ddEnabled) return;
  whichDog.style.left=isIE ? nowX+event.clientX-offsetx : nowX+e.clientX-offsetx;
  whichDog.style.top=isIE ? nowY+event.clientY-offsety : nowY+e.clientY-offsety;
  return false;
}

function ddN4(whatDog){
  if (!isN4) return;
  N4=eval(whatDog);
  N4.captureEvents(Event.MOUSEDOWN|Event.MOUSEUP);
  N4.onmousedown=function(e){
    N4.captureEvents(Event.MOUSEMOVE);
    N4x=e.x;
    N4y=e.y;
  }
  N4.onmousemove=function(e){
    if (isHot){
      N4.moveBy(e.x-N4x,e.y-N4y);
      return false;
    }
  }
  N4.onmouseup=function(){
    N4.releaseEvents(Event.MOUSEMOVE);
  }
}

function hideMe(){
  if (isIE||isNN) whichDog.style.display="none";
  else if (isN4) document.theLayer.display="none";

  		//Hide the <Select>
		//showHideFrameCombos('visible');
}

function showMe(){
  if (isIE||isNN) whichDog.style.display="";
  else if (isN4) document.theLayer.display="";
}

document.onmousedown=ddInit;
document.onmouseup=Function("ddEnabled=false");
