//width:900;height:700大小打开一个窗口
function winopen900(url,fname)
{	
	var xposition = 0; 
	var yposition = 0;
	if ((parseInt(navigator.appVersion) >= 4 )) {
		xposition = (window.screen.width - 900) / 2;
		yposition = (window.screen.height - 700 - 25) / 2;
	}
	if(typeof(fname)!="undefined"){
		win=window.open(url,fname,'width=900,height=700,fullscreen=0,toolbar=no,Status=no,menubar=no,location=no,scrollbars=yes,resizable=yes' + ',left=' + xposition + ',top=' + yposition);
	}else{
		win=window.open(url,'','width=900,height=700,fullscreen=0,toolbar=no,Status=no,menubar=no,location=no,scrollbars=yes,resizable=yes' + ',left=' + xposition + ',top=' + yposition);
	}
	win.focus();
}
//width:700;height:500大小打开一个窗口
function winopen700(url,fname)
{	
	var xposition = 0; 
	var yposition = 0;
	if ((parseInt(navigator.appVersion) >= 4 )) {
		xposition = (window.screen.width - 700) / 2;
		yposition = (window.screen.height - 500 - 25) / 2;
	}
	if(typeof(fname)!="undefined"){
		win=window.open(url,fname,'width=700,height=500,fullscreen=0,toolbar=no,Status=no,menubar=no,location=no,scrollbars=yes,resizable=no' + ',left=' + xposition + ',top=' + yposition);
	}else{
		win=window.open(url,'','width=700,height=500,fullscreen=0,toolbar=no,Status=no,menubar=no,location=no,scrollbars=yes,resizable=no' + ',left=' + xposition + ',top=' + yposition);
	}
	win.focus();
}
//width:600;height:500大小打开一个窗口
function winopen600(url,fname)
{	
	var xposition = 0; 
	var yposition = 0;
	if ((parseInt(navigator.appVersion) >= 4 )) {
		xposition = (window.screen.width - 600) / 2;
		yposition = (window.screen.height - 500 - 25) / 2;
	}
	if(typeof(fname)!="undefined"){
		win=window.open(url,fname,'width=600,height=500,fullscreen=0,toolbar=no,Status=no,menubar=no,location=no,scrollbars=yes,resizable=yes' + ',left=' + xposition + ',top=' + yposition);
	}else{
		win=window.open(url,'','width=600,height=500,fullscreen=0,toolbar=no,Status=no,menubar=no,location=no,scrollbars=yes,resizable=yes' + ',left=' + xposition + ',top=' + yposition);
	}
	win.focus();
}
function winopen(url,fname){ 
    var xposition = 0; 
	var yposition = 0;
	if ((parseInt(navigator.appVersion) >= 4 )) {
		xposition = (window.screen.width-300) / 2;
		yposition = (window.screen.height-500-25) / 2;
	}
	if(typeof(fname)!="undefined"){
		win=window.open(url,fname,'width=350,height=500,fullscreen=0,toolbar=no,Status=no,menubar=no,location=no,scrollbars=yes,resizable=no' + ',left=' + xposition + ',top=' + yposition);
	}else{
		win=window.open(url,'','width=350,height=500,fullscreen=0,toolbar=no,Status=no,menubar=no,location=no,scrollbars=yes,resizable=no' + ',left=' + xposition + ',top=' + yposition);
	}
	win.focus();
 
}
//width:400;height:300大小打开一个窗口
function winopen400(url,fname)
{	
	var xposition = 0; 
	var yposition = 0;
	if ((parseInt(navigator.appVersion) >= 4 )) {
		xposition = (window.screen.width - 400) / 2;
		yposition = (window.screen.height - 300 - 25) / 2;
	}
	if(typeof(fname)!="undefined"){
		win=window.open(url,fname,'width=400,height=300,fullscreen=0,toolbar=no,Status=no,menubar=no,location=no,scrollbars=yes,resizable=no' + ',left=' + xposition + ',top=' + yposition);
	}else{
		win=window.open(url,'','width=400,height=300,fullscreen=0,toolbar=no,Status=no,menubar=no,location=no,scrollbars=yes,resizable=no' + ',left=' + xposition + ',top=' + yposition);
	}
	win.focus();
}

//以指定的坐标打开一个窗口
function winopendir(url,w,h,fname)
{
	url=doWithUrl(url);
	var xposition = 0; 
	var yposition = 0;
	if ((parseInt(navigator.appVersion) >= 4 )) {
		xposition = (window.screen.width - w) / 2;
		yposition = (window.screen.height - h - 25) / 2;
	}
	if(typeof(fname)!="undefined"){
		win=window.open(url,'','width='+w+',height='+h+',fullscreen=0,toolbar=no,Status=no,menubar=no,location=no,scrollbars=yes,resizable=yes' + ',left=' + xposition + ',top=' + yposition);
	}else{
		win=window.open(url,fname,'width='+w+',height='+h+',fullscreen=0,toolbar=no,Status=no,menubar=no,location=no,scrollbars=yes,resizable=yes' + ',left=' + xposition + ',top=' + yposition);
	}
	win.focus();
}
//以全屏方式打开一个窗口
function winopenmax(url,fname)
{
	url=doWithUrl(url);
	width=window.screen.width;
	height=window.screen.height;
	if(typeof(fname)!="undefined"){
		win=window.open(url,fname,'width='+width+',height='+height+',top=0,left=0,fullscreen=0,toolbar=no,Status=no,menubar=no,location=no,scrollbars=yes,resizable=no');
	}else{
		win=window.open(url,'','width='+width+',height='+height+',top=0,left=0,fullscreen=0,toolbar=no,Status=no,menubar=no,location=no,scrollbars=yes,resizable=no');
	}
	win.focus();
}
//以全屏方式打开一个窗口
function winOpen_f(url,fname)
{
	url=doWithUrl(url);
	width=window.screen.width;
	height=window.screen.height;
	if(typeof(fname)!="undefined"){
		win=window.open(url,fname,'width='+width+',height='+height+',top=0,left=0,fullscreen=0,toolbar=no,Status=no,menubar=no,location=no,scrollbars=yes,resizable=no');
	}else{
		win=window.open(url,'','width='+width+',height='+height+',top=0,left=0,fullscreen=0,toolbar=no,Status=no,menubar=no,location=no,scrollbars=yes,resizable=no');
	}
	win.focus();
}