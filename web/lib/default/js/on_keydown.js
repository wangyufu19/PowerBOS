//���ο�ݼ�����
function KeyDown()
{
	//�ҷ���� 39  Tab��  9
	//alert(window.event.keyCode);
	if ((window.event.altKey)&&
      ((window.event.keyCode==37)||   //���� Alt+ ����� ��
       (window.event.keyCode==39))){  //���� Alt+ ����� ��
   //  alert("�Բ���������ʹ��ALT+�����ǰ������ˣ�");
     event.returnValue=false;
     }
  if (
//(event.keyCode==8)  ||                 //�����˸�ɾ����
      (event.keyCode==116)||                 //���� F5 ˢ�¼�
      (event.ctrlKey && event.keyCode==82)){ //Ctrl + R
     event.keyCode=0;
     event.returnValue=false;
     }
  if ((event.ctrlKey)&&(event.keyCode==78))   //���� Ctrl+n
     event.returnValue=false;
  if ((event.shiftKey)&&(event.keyCode==121)) //���� shift+F10
     event.returnValue=false;
  if (window.event.srcElement.tagName == "A" && window.event.shiftKey) 
      window.event.returnValue = false;  //���� shift ���������¿�һ��ҳ
  if ((window.event.altKey)&&(window.event.keyCode==115)){ //����Alt+F4
      window.showModelessDialog("about:blank","","dialogWidth:1px;dialogheight:1px");
      return false;}

	doEnterKey();
	doUpDownKey();
	
	
	
}
//---------------�س�������Զ���ת��һ�����------------------
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



//--------- ������½���Ӧ ----------------
function doUpDownKey(){
  if(typeof(_autoDiv_)!="undefined"){
	if(_autoDiv_!=null&&_autoDiv_.style.visibility=="visible"){
		return;
	}
 }

	var ieKey=event.keyCode;
	var ele = document.activeElement;//��ǰԪ��
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
//obj ��ǰԪ�أ�cell��ǰԪ�����ڵ�TD��row ��һ��Ԫ�����ڵ�TR
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
//obj ��ǰԪ�أ�c1 ��ǰԪ�����ڵ�TD��c2 ��һ��Ԫ�����ڵ�TD
function getObjFromCell(obj,c1,c2){
	if(c2.style.display=="none" || c2.getElementsByTagName("table").length>0){
		return null;
	}
	var len = c1.all.length;
	var index=0;//obj,�� c1 �е�λ��
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


//���Ԫ���Ƿ�ɻ�ý���
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
//--------- ������½���Ӧ end----------------
