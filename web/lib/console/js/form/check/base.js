    /*
    * �滻��߿ո�ȥ���ַ�������߿ո�
    * ���� sInputString  Ϊ��������ַ���
    */

    function getLeftTrim(sInputString){
        var rtnStr;
        rtnStr=""
        for (var i=0;i<sInputString.length;i++){
            if (sInputString.charAt(i)!=" "){
            rtnStr=sInputString.substr(i);
            break;
            }
        }
        return rtnStr;
    }


    /*
    * �滻�ұ߿ո�ȥ���ַ������ұ߿ո�
    * ���� sInputString  Ϊ��������ַ���
    */

    function getRightTrim(sInputString){
        var rtnStr;
        rtnStr=""
        for (var i=sInputString.length-1;i>=0;i--){
        if (sInputString.charAt(i)!=" "){
        rtnStr=sInputString.substring(0,i+1);
        break;
        }
        }
        return rtnStr;
    }

    /*
    *�滻���ұ߿ո�ȥ���ַ��������ұ߿ո�
    * ���� sInputString  Ϊ��������ַ���
    */

    function getTrim(sInputString){

        sInputString=getRightTrim(sInputString);
        sInputString=getLeftTrim(sInputString);
        return sInputString;

    }



     /*
        * �滻�ַ����е����пո�
        * ���� sInputString  Ϊ��������ַ���
    */

    function getAllTrim(sInputString){
        while(sInputString.indexOf(" ") != -1) sInputString=sInputString.replace(" ","");
        return sInputString;

    }
    
    
    function getTrimOb(ob){
    		if (typeof(ob)=="undefined") return;
        
    		var sInputString=ob.value;
        while(sInputString.indexOf(" ") != -1) sInputString=sInputString.replace(" ","");
        ob.value=sInputString;

    }



    /*
	* ���һ����������Ƿ���������ֵ,
	* ������������.
	*/

    function isNumber(texto){//new
        var gvalue=getTrim(texto.value);
        texto.value=gvalue;
        var tvalue=gvalue+" ";
        if (isNaN(tvalue)||(tvalue.length<1)){
            return false;
        }

        return true;
    }


	/*
	* ���һ����������Ƿ��������ı�,�������滻���ո�Ҳ�ǿ�ֵ��
	* ������������.
	* ����true ��ʾ�����δ�����ı����������ʾ�������ı�
	*/

    function isNullreplaced(texto){
        tvalue=getTrim(texto.value);
        if ((tvalue=="")||(tvalue.length<1)){
            return true;
        }
        texto.value=tvalue;
        return false;
    }



/*
	* ���һ����������Ƿ��������ı�,δ�����滻�����ո��ǿ�ֵ��
	* ������������.
	* return   ����true ��ʾ�����δ�����ı����������ʾ�������ı�
	*/

    function isNull(texto){
	//var tvalue=getTrim(texto.value);
	var tvalue= texto.value;
        if ((tvalue=="")||(tvalue.length<1)){
            return true;
        }
        return false;
    }




    /*
	* 
	* �õ�һ���ַ����к������ַ����ĸ��������ִ�Сд
	* �������룺
	* sInputString Ϊ��������ַ���
	* sIncludeString �����ҵ��ַ���
	* return   �����ҵ��ַ������ֵĴ���
	*/

    function getNumberIncludeString(sInputString,sIncludeString){
        var i=0;
        if(getTrim(sInputString)=="") return 0;
        while(sInputString.indexOf(sIncludeString)!= -1){
            sInputString=sInputString.replace(sIncludeString,"");
            i+=1;
        }
        return i;
    }



function contextdown1(obj)
{
	  obj.style.left = "1px";
	  obj.style.top = "1px";
	  obj.style.position = "relative";
}

function contextup1(obj) {
	  obj.style.left = "0px";
	  obj.style.top = "0px";
	  obj.style.position = "relative";
} 








