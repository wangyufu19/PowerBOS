

function selectAllbox(boxObj,listBoxObj){        
        if (typeof(boxObj)=="undefined"){
            return false;
        }       
        if (typeof(listBoxObj)=="undefined"){
            return;
        }           
        if(boxChecked(boxObj)){         
      		if (typeof(listBoxObj.length)=="undefined"){      		   
				if(!listBoxObj.disabled)
					listBoxObj.checked=true;
	        }else{
	            for (var i=0;i<listBoxObj.length;i++) {
					if(listBoxObj[i].disabled) continue;
	                listBoxObj[i].checked=true;
	            }
	        }        	
        }else{        
	      	if (typeof(listBoxObj.length)=="undefined"){
			   if(!listBoxObj.disabled)
	             listBoxObj.checked=false;
	        }else{
	            for (var i=0;i<listBoxObj.length;i++) {
					if(listBoxObj[i].disabled) continue;
	                listBoxObj[i].checked=false;
	            }
	        }       
        }
}


    /*
    * 检测一个复选框或一个单选框组是否有一个元素被选中,
    * 参数复选框或一个单选框组对象.
    */

    function boxChecked(box){        
        var choosed=false;
     
        if (typeof(box)=="undefined"){
            return false;
        }      
     
        if (typeof(box.length)=="undefined"){ 
            if (box.checked){
                choosed=true;
            }            
        }else{           
            for (var i=0;i<box.length;i++) {
                if (box[i].checked) {
                    choosed=true;
                    break;
                }
            }
        }
        return choosed;
    }
	function isChecked(idInputName){	
	     var box = document.getElementsByName(idInputName);	   	    
	     var bool = false;   
		 for(var i=0;i<box.length;i++){
		 	if(box[i].checked == true){
		 		bool = true;
		 	}
		 }	 
		 return bool;
	}
    /*
    *对表格增加行,删除行操作
    *
    */
    var line_L0=0;
	function createrow(){
	   line_L0++;
	   var tr = L0.insertRow(L0.rows.length);
	   var td;   
	   if(line_L0%2==0){
	       tr.setAttribute('className','rowOdd');
	   }else{
	       tr.setAttribute('className','rowEven');
	   } 
	   td=tr.insertCell();
	   td.innerHTML="<input name=codeId type=checkbox id=codeId >";
	   td = tr.insertCell();
	   td.innerHTML = "<input name=code type=text id=code>";
	   td = tr.insertCell();
	   td.innerHTML = "<input name=name type=text id=name>";
	}

	function deleterow(){      
	   if(L0.rows.length==1) return;
	   var bool=false;
	   var box=document.getElementsByName("codeId");   
	   for(var i=0;i<box.length;i++){
	     if(box[i].checked == true){
	        bool = true;        
	        L0.deleteRow(i+1);
	        break;
	     }
	   }	
	   if(!bool){
	     alert("请选择删除的行!");
	     return;
	   }
	}
    /*
    * 检测一个复选框或一个单选框组是否只有一个元素被选中,
    * 参数复选框或一个单选框组对象.
    */

    function oneBoxChecked(box){
        var choose=false;
        if (!boxChecked(box)) return choose;
        if (getCheckedNumber(box)== 1){
            choose=true;
        }
        return choose;
    }

		function getRadioChecked(box){
			
			if (typeof(box) == "undefined"){
            return;
      }
      if (typeof(box.length) == "undefined"){
            if (box.checked){
                return box;
            }
      }else{
            for (var j = 0; j < box.length; j++){
                if (box[j].checked){
                    return  box[j];
                }
            }
      }
			
		}


    /*
    *  检查一个checkbox组的选中元素个数
    *  参数复选框或一个单选框组对象.
    */

    function getCheckedNumber(box){
        var id1;
        var choosed = false;
        var i = 0;
        if (typeof(box) == "undefined"){
            return i;
        }
        if (typeof(box.length) == "undefined"){
            if (box.checked){
                id1 = box.value;
                choosed = true;
                i++;
            }
        }else{
            for (var j = 0; j < box.length; j++){
                if (box[j].checked){
                    id1 = box[j].value;
                    choosed = true;
                    i++;
                }
            }
        }
        if (!choosed) return i
        return i;
    }

/*
 * 设置选择框为选中状态.
 * 此时的选择框可以是一个对象或者同名的多个对象.
 * @param checkbox 选择框对象
 */
function setBoxChecked(checkbox) {

	if (typeof(checkbox) == "undefined") { // 选择框不存在
		return;
	}

	if (typeof(checkbox.length) == "undefined") { // 仅有一个选择框
		checkbox.checked = true;
		return;
	}
	else { // 含有多个选择框
		for (var i = 0; i < checkbox.length; i++) {
			checkbox[i].checked = true;
		}
		return;
	}
}