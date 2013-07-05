

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
    * ���һ����ѡ���һ����ѡ�����Ƿ���һ��Ԫ�ر�ѡ��,
    * ������ѡ���һ����ѡ�������.
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
    * ���һ����ѡ���һ����ѡ�����Ƿ�ֻ��һ��Ԫ�ر�ѡ��,
    * ������ѡ���һ����ѡ�������.
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
    *  ���һ��checkbox���ѡ��Ԫ�ظ���
    *  ������ѡ���һ����ѡ�������.
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
 * ����ѡ���Ϊѡ��״̬.
 * ��ʱ��ѡ��������һ���������ͬ���Ķ������.
 * @param checkbox ѡ������
 */
function setBoxChecked(checkbox) {

	if (typeof(checkbox) == "undefined") { // ѡ��򲻴���
		return;
	}

	if (typeof(checkbox.length) == "undefined") { // ����һ��ѡ���
		checkbox.checked = true;
		return;
	}
	else { // ���ж��ѡ���
		for (var i = 0; i < checkbox.length; i++) {
			checkbox[i].checked = true;
		}
		return;
	}
}