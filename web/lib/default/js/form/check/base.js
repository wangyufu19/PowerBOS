    /*
    * 替换左边空格（去除字符串的左边空格）
    * 参数 sInputString  为待处理的字符串
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
    * 替换右边空格（去除字符串的右边空格）
    * 参数 sInputString  为待处理的字符串
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
    *替换左右边空格（去除字符串的左右边空格）
    * 参数 sInputString  为待处理的字符串
    */

    function getTrim(sInputString){

        sInputString=getRightTrim(sInputString);
        sInputString=getLeftTrim(sInputString);
        return sInputString;

    }



     /*
        * 替换字符串中的所有空格
        * 参数 sInputString  为待处理的字符串
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
	* 检测一个输入框中是否输入了数值,
	* 参数输入框对象.
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
	* 检测一个输入框中是否输入了文本,已作空替换（空格也是空值）
	* 参数输入框对象.
	* 返回true 表示输入框未输入文本，否则，则表示输入了文本
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
	* 检测一个输入框中是否输入了文本,未作空替换，（空格不是空值）
	* 参数输入框对象.
	* return   返回true 表示输入框未输入文本，否则，则表示输入了文本
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
	* 得到一个字符串中含有子字符串的个数，区分大小写
	* 参数输入：
	* sInputString 为待处理的字符串
	* sIncludeString 待查找的字符串
	* return   待查找的字符串出现的次数
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








