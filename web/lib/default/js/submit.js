/**
  *��form����Ķ��󣬴���URL����
	*
	*/


function sort_f(form,eid){

	var str = form.sortMode.value.toUpperCase();
	//alert(str);
	var pos = str.indexOf(eid.toUpperCase());
	if(pos == -1){
		str += ","+eid+" ASC"
	}else{
		var pos1 = str.indexOf(",", pos+1);
		if(pos1 == -1) pos1 = str.length;
		var pos2 = str.indexOf(" ", pos+1);
		var newstr = str.substring(0,pos-1);
		var ascdesc = str.substring(pos2+1,pos1);
		var newstr2;
		
		if(newstr == ""){
			if(ascdesc == "ASC") ascdesc = "DESC"
			else ascdesc = "ASC";
			newstr2 = eid+" "+ascdesc+",";
		}else{
			newstr2 = eid+" ASC,"+newstr+",";
		}
		//alert(newstr2+":"+str.substring(pos1+1));
		str= newstr2+str.substring(pos1+1);
		while(str.charAt(str.length-1) == ','){
			str = str.substring(0,str.length-1);
		}
		while(str.indexOf(",,")!=-1){
			str = str.replace(",,",",");
		}
	}
	//alert(str);
	form.method.value = "showMain";
	reset_f();
	form.sortMode.value = str;
	//alert(form.sortMode.value);
	form.submit();
}

/**��Ϊƽ̨isTime(textto,message)��ʾ��������,��isTime_f(textto,message)
	*�ж�����ʱ�䣨���������ڣ��Ƿ���Ч
	*
	*/
	function isTime_f(textto,message){
	var time=textto.value;
	var hour,minute,index,hourstr,len,minutestr;
	if(!isNull(textto)){
		len=time.length;
		index=time.indexOf(":");
		if (index>2){
			alert(message+"�����ʽ������");
			return false;
		}
		hourstr = time.substring(0,index);
		minutestr=time.substring(index+1,len);
		if(isNumberS(hourstr)&&isNumberS(minutestr)){
			hour=parseInt(hourstr);
			minute=parseInt(minutestr);
			if(hour>=0 && hour<=23){
				if(minute>=0 && minute<=59){
					return true;
				}else{
					alert("�������볬����Χ��");
					textto.focus();
					return false;
				}
			}else{
				alert("Сʱ���볬����Χ");
				textto.focus();
				return false;
			}
		}else{
			alert(message+"ʱ���ʽӦΪ hh:mm");
			textto.focus();
			textto.select();
			return false;
		}

	}else{
		alert(message+"����Ϊ�գ�");
		return false;
	}
			
	}

