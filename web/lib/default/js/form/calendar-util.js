

    /*
    * ���һ����������������ݵĺϷ���
    * ��������������Ͷ���ݵ������ַ���
    */

	function isYear(texto,message){
       var tvalue=texto.value;
       tvalue=getAllTrim(tvalue);
       texto.value=tvalue;
       if(tvalue=="" || tvalue.length<1 || !isNumber(texto) || tvalue.indexOf(".")!=-1){
            alert(message+"�����ַ���Ч��");
            return false;
       }
       if(parseFloat(tvalue)<1960||parseFloat(tvalue)>2049){
           alert(message+"���볬����Χ��");
           texto.focus();
           return false;
       }
       return true;
    }






    /* ���һ���������������·ݵĺϷ���
    *��������������Ͷ��·ݵ������ַ���
    */

    function isMonth(texto,message){
        var tvalue=texto.value;
        var gvalue=getAllTrim(tvalue);
        texto.value=gvalue;
        tvalue="1"+gvalue;
        if(gvalue="" || gvalue.length<1 || !isNumber(texto) || tvalue.indexOf(".")!=-1){
            alert(message+"�����ַ���Ч��");
            return false;
        }
        if((parseFloat(tvalue,10)>=11&&parseFloat(tvalue,10)<=19)||(parseFloat(tvalue,10)>=101&&parseFloat(tvalue,10)<=112)){
            return true;
        }else{
            alert(message+"���볬����Χ!");
        texto.focus();
        }
        return false;
    }



    /*
    * ���һ���������������շݵĺϷ���
    * ��������������Ͷ��շݵ������ַ���
    */

    function isDay(texto,message){
        var tvalue=texto.value;
        var gvalue=getAllTrim(tvalue);
        texto.value=gvalue;
        tvalue=gvalue+"1";
        if(!isNumber(texto) || tvalue.indexOf(".")!=-1 || gvalue=="" || gvalue.length<1){
            alert(message+"�����ַ���Ч��");
            return false;
        }
        if((parseFloat(tvalue,10)>=11&&parseFloat(tvalue,10)<=91)||(parseFloat(tvalue,10)>=101&&parseFloat(tvalue,10)<=311)){
            return true;
        }else{
            alert(message+"���볬����Χ!");
            texto.focus();
        }
        return false;
    }



    /*
    * ���һ��������������Сʱ�ĺϷ���
    * ��������������Ͷ�Сʱ�������ַ���
    */

    function isHour(texto,message){
        var tvalue=texto.value;
        var gvalue=getAllTrim(tvalue);
        texto.value=gvalue;
        tvalue=gvalue+"1";
        if(!isNumber(texto) || tvalue.indexOf(".")!=-1 || gvalue=="" || gvalue.length<1){
            alert(message+"�����ַ���Ч��");
            return false;
        }
        if((parseFloat(tvalue,10)>=01&&parseFloat(tvalue,10)<=91)||(parseFloat(tvalue,10)>=101&&parseFloat(tvalue,10)<=231)){
            return true;
        }else{
            alert(message+"���볬����Χ!");
            texto.focus();
        }
        return false;
    }



    /*
    * ���һ�������������ķֵĺϷ���
    * ��������������ͶԷֵ������ַ���
    */

    function isMinute(texto,message){
        var tvalue=texto.value;
        var gvalue=getAllTrim(tvalue);
        texto.value=gvalue;
        tvalue=gvalue+"1";
        if(!isNumber(texto) || tvalue.indexOf(".")!=-1 || gvalue=="" || gvalue.length<1){
            alert(message+"�����ַ���Ч��");
            return false;
        }
        if((parseFloat(tvalue)>=1&&parseFloat(tvalue)<=91)||(parseFloat(tvalue,10)>=101&&parseFloat(tvalue,10)<=591)){
            return true;
        }else{
            alert(message+"���볬����Χ!");
            texto.focus();
        }
        return false;
    }



	/*
	*�����û�������������ַ��������������
	*
	*/

	function  getYearVN(texto){
				
        date=texto.value;
        date=getAllTrim(date);
        texto.value=date;
        
        while(date.indexOf("-") != -1) date=date.replace("-","/");
        dt= new Date(date);
        if(dt.getYear()<2000)
            return (dt.getYear()+1900);
        else
            return (dt.getYear());
	}
/*
	*�����û�������������ַ��������������
	*
	*/

	function  getYearVN1(texto){
				
        date=texto;
        date=getAllTrim(date);
        texto.value=date;
        
        while(date.indexOf("-") != -1) date=date.replace("-","/");
        dt= new Date(date);
        if(dt.getYear()<2000)
            return (dt.getYear()+1900);
        else
            return (dt.getYear());
	}


	/*
	*�����û�������������ַ������������·�
	*
	*/

	function  getMonthVN(texto){
	   date=texto.value;
	   date=getAllTrim(date);
       texto.value=date;
	   while(date.indexOf("-") != -1) date = date.replace("-","/");
       dt= new Date(date);
       return dt.getMonth()+1;
	}
/*
	*�����û�������������ַ������������·�
	*
	*/

	function  getMonthVN1(texto){
	   date=texto;
	   date=getAllTrim(date);
       texto.value=date;
	   while(date.indexOf("-") != -1) date = date.replace("-","/");
       dt= new Date(date);
       return dt.getMonth()+1;
	}


	/*
	*�����û�������������ַ���������������
	*
	*/

	function  getDateVN(texto){
        date=texto.value;
        date=getAllTrim(date);
        texto.value=date;
        while(date.indexOf("-") != -1) date = date.replace("-","/");
            dt= new Date(date);
            return dt.getDate();
        }
	/*
	*�����û�������������ַ���������������
	*
	*/

	function  getDateVN1(texto){
        date=texto;
        date=getAllTrim(date);
        texto.value=date;
        while(date.indexOf("-") != -1) date = date.replace("-","/");
            dt= new Date(date);
            return dt.getDate();
        }


	/*
	*�����û�������������ַ���������������
	* 0 ������;1 ����һ��2 ���ڶ���3 ��������4 �����ģ�5 �����壻6 ������
	*
	*/
    function  getDayVN(texto){
        date=texto.value;
        date=getAllTrim(date);
        texto.value=date;
        while(date.indexOf("-") != -1) date = date.replace("-","/");
        dt= new Date(date);
        return dt.getDay();
    }










    /*
    *�����û�������������ַ���������������
    * 1 һ���ȣ�2 �����ȣ�3 �����ȣ�4 �ļ��ȣ�
    *
    */
    function  getQuarterVN(texto){
        var month=getMonthVN(texto);
         switch(month)
         {
           case 1:
           case 2:
           case 3:
                 return 1;
                 break;
           case 4:
           case 5:
           case 6:
                 return 2;
                 break;
           case 7:
           case 8:
           case 9:
                 return 3;
                 break;
           case 10:
           case 11:
           case 12:
                  return 4;
                  break;
           default:
                  return -1;
        }
	}



    function isQuarter(texto,message){
        var tvalue=texto.value;
        tvalue=getAllTrim(tvalue);
        texto.value=tvalue;
        if(!isNumber(texto) || tvalue=="" || tvalue.length<1){
            alert(message+"�����ַ���Ч��");
            return false;
        }
        if(!isInteger(texto,message)) return false;
        if(parseFloat(tvalue)<1||parseFloat(tvalue)>4){
            alert(message+"���볬����Χ!");
            texto.focus();
            return false;
        }
        return true;
    }


	 /*
	*�����û�������ı������ж��Ƿ�Ϊ����
	*
	*/
	 function isInteger(texto,message)
        {
          var tvalue=texto.value;
          tvalue=getAllTrim(tvalue);
          texto.value=tvalue;
          if(tvalue!="" && isNumber(texto) && parseFloat(tvalue).toString().indexOf(".")==-1)
            return true;
          else{
            alert(message+"����������!");
            texto.focus();
            return false;
          }
       }


    /**
    * ��������ڵ���Ч��У�� ������ʱ��
    * ��������������Ͷ���������ַ���
    */


    function dateCheck(texto, message){
        date=texto.value;
        date=getAllTrim(date);
        texto.value=date;
        if(date=="") return true;
        if(date.indexOf("-") == -1 && date.indexOf("/") == -1){
        	sldateCheck(texto,message);
        	return;
       	}
        
        while(date.indexOf("-") != -1) date = date.replace("-","/");
        if ((date!="")){
            var sdate = Date.parse(date);
            if(isNaN(sdate)){
                alert("" + message + "\n�������׼��ʽ\n����YYYY-MM-DD");
                return false;
            }
            var maxday=31;
            if(Date.parse("2500/01/01") > sdate && sdate > Date.parse("1800/01/01")){
                var datetrue = new Date(sdate);
                var datestring = ""+datetrue.getFullYear() + "-" + (datetrue.getMonth() + 1) + "-" + datetrue.getDate();
                var index1;
                var index2;
                index1=date.indexOf("/");
                date=date.replace("/","-");
                index2=date.indexOf("/");
                var yearStr = date.substring(0,index1);
                var year=parseInt(date.substring(0,index1));
                if(yearStr.length != 4){
                    alert("" + message + "\n������Ϸ���4λ���");
                    return false;
                }
                var month=parseInt(date.substring(index1+1, index2),10);
                if(month==4 || month==6 || month==9 || month==11  )
                maxday=30;
                if(month==1 || month==3 || month==5 || month==7 ||month==8 || month==10 || month==12)
                    maxday=31;
                if(month==2 && ((Math.round(year/4)==year/4 && Math.round(year/100)!=year/100)
                ||(Math.round(year/400)==year/400)))
                    maxday=29;
                else if(month==2)
                    maxday=28;
                if(month>12 || month <1){
                    alert("" + message + "\n������Ϸ��·�");
                    return false;
                }
                var day=parseInt(date.substring(index2+1),10);
                if(day<1 || day>maxday){
                alert("" + message + "\n������Ϸ�����");
                return false;
                }
            }else{
                alert("" + message + "\n���� 1800-01-01 �� 2499-12-31 ������");
                return false;
            }
        }
        return true;
    }
    
    
    	/**
         *Ϊ���ʺ�������������ر�����
         */
    
    
    function sldateCheck(texto, message){
		
        date=texto.value;
		if(date=="") return true;
        /**
         * ���� -
         */
        date=date.substring(0,4)+"/"+date.substring(4,6)+"/"+date.substring(6,8);
        if ((date!="")){
            var sdate = Date.parse(date);
            if(isNaN(sdate)){
                alert("" + message + "\n�������׼��ʽ\n����YYYY-MM-DD");
                return false;
            }
            var maxday=31;
            if(Date.parse("2500/01/01") > sdate && sdate > Date.parse("1800/01/01")){
                var datetrue = new Date(sdate);
                var datestring = ""+datetrue.getFullYear() + "-" + (datetrue.getMonth() + 1) + "-" + datetrue.getDate();
                var index1;
                var index2;
                index1=date.indexOf("/");
                date=date.replace("/","-");
                index2=date.indexOf("/");
                var yearStr = date.substring(0,index1);
                var year=parseInt(date.substring(0,index1));
                if(yearStr.length != 4){
                    alert("" + message + "\n������Ϸ���4λ���");
                    return false;
                }
                var month=parseInt(date.substring(index1+1, index2),10);
                if(month==4 || month==6 || month==9 || month==11  )
                maxday=30;
                if(month==1 || month==3 || month==5 || month==7 ||month==8 || month==10 || month==12)
                    maxday=31;
                if(month==2 && ((Math.round(year/4)==year/4 && Math.round(year/100)!=year/100)
                ||(Math.round(year/400)==year/400)))
                    maxday=29;
                else if(month==2)
                    maxday=28;
                if(month>12 || month <1){
                    alert("" + message + "\n������Ϸ��·�");
                    return false;
                }
                var day=parseInt(date.substring(index2+1),10);
                if(day<1 || day>maxday){
                alert("" + message + "\n������Ϸ�����");
                return false;
                }
            }else{
                alert("" + message + "\n���� 18000101 �� 24991231 ������");
                return false;
            }
        }
        return true;
    }



	/**
	* �������ڵ���Ч��У�� ������ʱ��,���������飬����Ϊ��
	*/


    function isInputDates(texto,message){
        if(typeof(texto.length)=="undefined"){
            if(!dateCheck(texto,message)){
                texto.focus();
                return false;
            }
        }else{
            for(var i=0;i<texto.length;i++){
                if(!dateCheck(texto[i], message)){
                    texto[i].focus();
                    return false;
                }
            }
        }
        return true;
    }



	/**
	* �������ڵ���Ч��У�� ������ʱ��,���������飬������Ϊ��
	*/


    function isInputNotNullDates(texto,message){
        if(typeof(texto.length)=="undefined"){
            if(isNullreplaced(texto)){
                texto.focus();
                alert(message+"����Ϊ�գ�");
                return false;
            }
            if(!dateCheck(texto,message)){
                texto.focus();
                return false;
            }
        }else{
            for(var i=0;i<texto.length;i++){
                if(isNullreplaced(texto[i])){
                    texto[i].focus();
                    alert(message+"����Ϊ�գ�");
                    return false;
                }
                if(!dateCheck(texto[i], message)){
                    texto[i].focus();
                    return false;
                }
            }
        }
        return true;
    }



	/**
	* �����������Ч���ڵıȽ� ������ʱ��,���������벻��ȷ���򷵻� -1
	*/

	function compareDate(texto1,texto2){
	   var date1=getYearVN(texto1)+"/"+getMonthVN(texto1)+"/"+getDateVN(texto1);
	   var date2=getYearVN(texto2)+"/"+getMonthVN(texto2)+"/"+getDateVN(texto2);
       if(isNaN(Date.parse(date1))) return -1;
       if(isNaN(Date.parse(date2))) return -1;
	   var sdate1 = Date.parse(date1);
	   var sdate2 = Date.parse(date2);
	   if(sdate1>sdate2) return ">";
	   if(sdate1==sdate2) return "=";
	   if(sdate1<sdate2) return "<";
	}
	
	/**
	* �����������Чʱ��ıȽ� 
	*/

	function compareTime(texto1,texto2){
		var starttime = texto1.value;
		var endtime = texto2.value;
		var index1,index2,slen,elen,shourstr,ehourstr,smistr,emistr,shour,ehour,smi,emi;
		slen = starttime.length;
		elen = endtime.length;
		index1=starttime.indexOf(":");
		index2=endtime.indexOf(":");
		shourstr=starttime.substring(0,index1);
		smistr = starttime.substring(index1+1,slen);
		ehourstr=endtime.substring(0,index2);
		emistr = endtime.substring(index2+1,elen);
		shour=parseInt(shourstr);
		ehour=parseInt(ehourstr);
		smi=parseInt(smistr);
		emi=parseInt(emistr);
		
		if (shour>ehour){			
			return false;
		}else if (shour==ehour){
				if (smi>emi){					
					return false;
				}
			}else{
				return true;
			}
		
		return true;
	}
	
	/**
	 * �Ƚ���������ʱ��Ĵ�С
	 */
	function compareDateTime(texto1,texto2){
		
		var date1=texto1.value;
	    var date2=texto2.value;
		while(date1.indexOf("-") != -1) date1=date1.replace("-","/");
		while(date2.indexOf("-") != -1) date2=date2.replace("-","/");
        if(isNaN(Date.parse(date1))) return -1;
        if(isNaN(Date.parse(date2))) return -1;
	    var sdate1 = Date.parse(date1);
	    var sdate2 = Date.parse(date2);
	    if(sdate1>sdate2) return ">";
	    if(sdate1==sdate2) return "=";
	    if(sdate1<sdate2) return "<";
	}

		

	/**
	*ǿ�н�����ת��Ϊyyyy-mm-dd����,�������Ƿ� ���� -1
	*
	*/

	function dateValueChange(texto){
	   var ret;
       var gvalue=texto.value;
       gvalue=getAllTrim(gvalue);
	   while(gvalue.indexOf("-") != -1) gvalue = gvalue.replace("-","/");
       if(isNaN(Date.parse(gvalue))) return -1;
       ret=getYearVN(texto)+"-";
	   if(getMonthVN(texto)<10)
	     ret=ret+"0"+getMonthVN(texto)+"-";
	   else
	     ret=ret+getMonthVN(texto)+"-";
	   if(getDateVN(texto)<10)
	     ret=ret+"0"+getDateVN(texto);
	   else
	     ret=ret+getDateVN(texto);
	   return  ret;
	}

	/**
	*�ж�����ʱ�䣨���������ڣ��Ƿ���Ч
	*
	*/
	function isTime(textto,message){
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
			alert(message+"ʱ���ʽӦΪ hh24:mi");
			textto.focus();
			textto.select();
			return false;
		}

	}else{
		alert(message+"����Ϊ�գ�");
		return false;
	}
			
	}


       function getFirstDate(yearto,quarterto){
          var date;
         if(isNaN(yearto+" ") || getAllTrim(yearto)=="" || isNaN(quarterto+" ") || getAllTrim(quarterto)=="" ){
            alert("��������");
            return -1;
          }
          switch(quarterto){
            case 1:
                 date=yearto+"-01-0"+1;
                 break;
            case 2:
                 date=yearto+"-04-0"+1;
                 break;
            case 3:
                 date=yearto+"-07-0"+1;
                 break;
            case 4:
                 date=yearto+"-10-0"+1;
                 break;
            default:
                 alert("��������");
                 return -1;
         }
       return date;
     }


      function getLastDate(yearto,quarterto){
        var date;
        if(isNaN(yearto+" ") || getAllTrim(yearto)=="" || isNaN(quarterto+" ") || getAllTrim(quarterto)=="" ){
            alert("��������");
            return -1;
          }
        switch(quarterto){
          case 1:
               date=yearto+"-03-"+31;
               break;
          case 2:
               date=yearto+"-06-"+30;
               break;
          case 3:
               date=yearto+"-09-"+30;
               break;
          case 4:
               date=yearto+"-12-"+31;
               break;
          default:
               alert("��������");
               return -1;
        }
        return date;
     }




     function reduceDate(texto,num){
        date=texto.value;
        date=getAllTrim(date);
        texto.value=date;
        while(date.indexOf("-") != -1) date = date.replace("-","/");
        dt= new Date(date);
        if(isNaN(dt)) return -1;
        time=dt.getTime();
        time-=num*24*60*60*1000;
        dt=new Date(time);
        date=dt.getYear()+"-"+(dt.getMonth()+1)+"-"+dt.getDate(dt);
        return date;
      }



    function addDate(texto,num){
       var date=getYearVN(texto)+"/"+getMonthVN(texto)+"/"+(getDateVN(texto)+num);
       dt= new Date(date);
       if(isNaN(dt)) return -1;
       var date=dt.getYear()+"-"+(dt.getMonth()+1)+"-"+dt.getDate(dt);
       return date;

    }
    
//+---------------------------------------------------
//| ����ʱ����
//| ��ʽΪ��YYYY-MM-DD HH:MM:SS
//+---------------------------------------------------
function checkDateTime(str){
    var reg = /^(\d+)-(\d{1,2})-(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
    var r = str.match(reg);
    if (r == null) {
	    return false;
    }
    r[2] = r[2] - 1;
    var d = new Date(r[1], r[2], r[3], r[4], r[5], r[6]);
    if (d.getFullYear() != r[1]) {
	return false;
    }
    if (d.getMonth() != r[2]) {
	return false;
    }
    if (d.getDate() != r[3]) {
        return false;
    }
    if (d.getHours() != r[4]) {
		return false;
    }
    if (d.getMinutes() != r[5]) {
		return false;
    }
    if (d.getSeconds() != r[6]) {
		return false;
    }
    return true;
}

/**
 * �ж����������ʱ���Ƿ���Ч
 * ����ʱ��ĸ�ʽΪ��YYYY-MM-DD HH:MM:SS
 */
function isDateTime(textto, message) { 
	var dateTime = textto.value;
	if (checkDateTime(dateTime) == false) {
		alert(message + " ����ʱ���ʽӦΪ YYYY-MM-DD HH:MM:SS");
		return false;
	}
	return true;
}