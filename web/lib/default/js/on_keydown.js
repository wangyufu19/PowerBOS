//屏蔽快捷键函数
function KeyDown()
{
	//右方向键 39  Tab健  9
	//alert(window.event.keyCode);
	if ((window.event.altKey)&&
      ((window.event.keyCode==37)||   //屏蔽 Alt+ 方向键 ←
       (window.event.keyCode==39))){  //屏蔽 Alt+ 方向键 →
   //  alert("对不起，您不能使用ALT+方向键前进或后退！");
     event.returnValue=false;
     }
  if (
//(event.keyCode==8)  ||                 //屏蔽退格删除键
      (event.keyCode==116)||                 //屏蔽 F5 刷新键
      (event.ctrlKey && event.keyCode==82)){ //Ctrl + R
     event.keyCode=0;
     event.returnValue=false;
     }
  if ((event.ctrlKey)&&(event.keyCode==78))   //屏蔽 Ctrl+n
     event.returnValue=false;
  if ((event.shiftKey)&&(event.keyCode==121)) //屏蔽 shift+F10
     event.returnValue=false;
  if (window.event.srcElement.tagName == "A" && window.event.shiftKey) 
      window.event.returnValue = false;  //屏蔽 shift 加鼠标左键新开一网页
  if ((window.event.altKey)&&(window.event.keyCode==115)){ //屏蔽Alt+F4
      window.showModelessDialog("about:blank","","dialogWidth:1px;dialogheight:1px");
      return false;}

	doEnterKey();
	doUpDownKey();
	
	
	
}
//---------------回车键光标自动跳转下一输入框------------------
function doEnterKey(){
	if(typeof(_autoDiv_)!="undefined"){
		if(_autoDiv_!=null&&_autoDiv_.style.visibility=="visible"){
			return;
		}
	}
	var ieKey = event.keyCode;
	if(ieKey==13){
		var ele = event.srcElement;	
		switch(ele.type){
			case "textarea":return;
			default:break;
		}
		event.keyCode = 9;
	}


	//if(ieKey==39){
		//var ele = event.srcElement;	
		//switch(ele.type){
		//	case "textarea":return;
			//default:break;
		//}
		//event.keyCode = 9;
	//}
	//if(ieKey==37){
		//var ele = event.srcElement;	
		//switch(ele.type){
			//case "textarea":return;
			//default:break;
		//}
		//window.event.shiftKey=true;
		//event.keyCode = 13;
		//event.keyCode = 9;
	//}
}



//--------- 光标上下建响应 ----------------
function doUpDownKey(){
  if(typeof(_autoDiv_)!="undefined"){
	if(_autoDiv_!=null&&_autoDiv_.style.visibility=="visible"){
		return;
	}
 }

	var ieKey=event.keyCode;
	var ele = document.activeElement;//当前元素
	switch(ele.tagName){
		case "INPUT":break;
		case "TEXTAREA":break;
		default:return;
	}
	var obj=null;
	if(ieKey==38){
		obj = _doUpKey(ele);
	}else if(ieKey==40){
		obj = _doDownKey(ele);
	}
	if(obj != null){
		switch(obj.tagName){
			case "TEXTAREA":
			case "INPUT":obj.select();break;
			case "SELECT":obj.focus();break;
			default:;
		}
	}
}
function _doUpKey(obj){
	var upObj = null;
	var cell = obj.parentElement;
	while(cell.tagName!="TD"){
		cell = cell.parentElement;
	}
	var row = cell.parentElement;
	var upRow=row.previousSibling;

	while(upRow!=null){
		upObj = getObjFromRow(obj,cell,upRow);
		if(upObj==null){
			upRow = upRow.previousSibling;
		}else{
			return upObj;
		}
	}

	var tbls = document.getElementsByTagName("table");
	var tbl = row.parentElement.parentElement;
	var len = tbls.length;
	var flag=false;
	for(var i=len-1;i>=0;i--){
		if(flag){
			if(tbls[i].style.display!="none"){
				var l=tbls[i].rows.length;
				for(var j=l-1;j>=0;j--){
					upRow = tbls[i].rows[j];
					upObj = getObjFromRow(obj,cell,upRow);
					if(upObj!=null){
						return upObj;
					}
				}
			}
		}else if(tbls[i]==tbl){
			flag=true;
		}
	}

	return null;
}
function _doDownKey(obj){
	var downObj = null;
	var cell = obj.parentElement;
	while(cell.tagName!="TD"){
		cell = cell.parentElement;
	}
	var row = cell.parentElement;
	var downRow=row.nextSibling;
	while(downRow!=null){
		downObj = getObjFromRow(obj,cell,downRow);
		if(downObj==null){
			downRow = downRow.nextSibling;
		}else{
			return downObj;
		}
	}

	var tbls = document.getElementsByTagName("table");
	var tbl = row.parentElement.parentElement;
	var len = tbls.length;
	var flag=false;
	for(var i=0;i<len;i++){
		if(flag){
			if(tbls[i].style.display!="none"){
				var l=tbls[i].rows.length;
				for(var j=0;j<l;j++){
					downRow = tbls[i].rows[j];
					downObj = getObjFromRow(obj,cell,downRow);
					if(downObj!=null){
						return downObj;
					}
				}
			}
		}else if(tbls[i]==tbl){
			flag=true;
		}
	}

	return null;
}
//obj 当前元素，cell当前元素所在的TD，row 下一个元素所在的TR
function getObjFromRow(obj,cell,row){
	if(row.style.display=="none"){
		return null;
	}

	var len = row.cells.length;
	var downObj;
	var downCell;
	if(len>cell.cellIndex){
		len = cell.cellIndex;
	}else{
		len--;
	}
	for(var i=len;i>=0;i--){
		downCell = row.cells[i];
		downObj = getObjFromCell(obj,cell,downCell);
		if(downObj!=null){
			return downObj;
		}
	}
	return null;
}
//obj 当前元素，c1 当前元素所在的TD，c2 下一个元素所在的TD
function getObjFromCell(obj,c1,c2){
	if(c2.style.display=="none" || c2.getElementsByTagName("table").length>0){
		return null;
	}
	var len = c1.all.length;
	var index=0;//obj,在 c1 中的位置
	for(var i=0;i<len;i++){
		if(_checkEle(c1.all[i])){
			index++;
			if(c1.all[i]==obj){
				break;
			}
		}
	}
	len = c2.all.length;
	var downObj=null;
	for(var i=0;i<len;i++){
		if(_checkEle(c2.all[i])){
			downObj = c2.all[i]
			index--;
			if(index==0){
				break;
			}
		}
	}
	return downObj;
}


//检测元素是否可获得焦点
function _checkEle(ele){
	if(ele==null){
		return false;
	}
	switch(ele.tagName){
		case "INPUT": if(ele.type=="hidden" || ele.type=="button" || ele.readonly)return false;break;
		case "SELECT":break;
		case "TEXTAREA":break;
		default:return false;
	}
	return ele.style.display!="none";
}
//--------- 光标上下建响应 end----------------
