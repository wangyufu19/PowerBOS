


    /*
	* 替换输入字符串的空格
	* 参数输入框对象值
	*/

    function getNumberTrim(texto){
        var tvalue=getTrim(texto.value);
        texto.value=tvalue;
        return tvalue;
    }

	/**
	* 取得输入数字的小数点位数
	* 参数输入框对象.
	*/

    function getDecimal(texto){
        if(isNumber(texto)){
            var num=parseFloat(texto.value);
            var tvalue=num.toString();
            if(tvalue.lastIndexOf(".")==-1){
                return 0;
            }else{
                return tvalue.substring(tvalue.lastIndexOf(".")+1,tvalue.length).length;
            }
        }else{
            return -1;
        }
    }


    /**
	* 取得输入数字的整数位数
	* 参数输入框对象.
	*/

    function getInteger(texto){
        if(isNumber(texto)){
            var lenI=0;
            var lenD=getDecimal(texto);
            var num=parseFloat(texto.value);
            if(isNaN(num)) return 0;
            var tvalue=num.toString();
            var len=tvalue.length;
            if(lenD>0) lenD+=1;
            lenI=len-lenD;
            return lenI;
        }else{
            return -1;
        }
    }





    /**
    * 判断输入的数字(不可以为空)是否大于0, 可以是数组
    * 只要一个小于0或者为空， 返回false
    *  参数为输入框对象和描述字符串
    */

    function isInputNotNullPositiveNumbers(texto,message){
        if(typeof(texto.length)=="undefined"){
            if(!isInputNotNullNumber(texto,message)){
                return false;
            }
            var num=parseFloat(texto.value);
            if(isNaN(num) || num<=0.0){             //isNaN(num)中，num参数应为texto.value,
                alert(message+"应为大于零的数字!");//否则将会把类似"123ert"的输入视为正确
                texto.focus();
                return false;
            }
        }else{
            for(var i=0;i<texto.length;i++){
                if(!isInputNotNullNumber(texto[i],message)){
                    return false;
                }
                var num=parseFloat(texto[i].value);
                if(isNaN(num) || num<=0.0){         //isNaN(num)中，num参数应为texto.value,
                    alert(message+"应为大于零的数字!"); //否则将会把类似"123ert"的输入视为正确
                    texto[i].focus();
                    return false;
                }
            }
        }
        return true;
    }




    /**
    * 判断输入的数字(可以为空)是否大于0, 可以是数组
    * 只要一个小于等于0， 返回false
    *  参数为输入框对象和描述字符串
    */

    function isInputPositiveNumbers(texto,message){
        if(typeof(texto.length)=="undefined"){
            if(!isInputNumber(texto,message)){
                return false;
            }
            var tvalue=texto.value;
            tvalue=getNumberTrim(texto);
            var num=parseFloat(tvalue);
            if(tvalue!="" || num<=0.0){             //isNaN(num)中，num参数应为texto.value
                alert(message+"应为大于零的数字!"); //否则将会把类似"123ert"的输入视为正确
                texto.focus();
                return false;
            }
        }else{
            for(var i=0;i<texto.length;i++){
                if(!isInputNumber(texto[i],message)){
                    return false;
                }
                var tvalue=texto[i].value;
                tvalue=getNumberTrim(texto[i]);
                var num=parseFloat(tvalue);
                if(tvalue!="" &&  num<=0.0){          //isNaN(num)中，num参数应为texto.value,
                    alert(message+"应为大于零的数字!");  //否则将会把类似"123ert"的输入视为正确
                    texto[i].focus();
                    return false;
                }
            }
        }
        return true;
    }



    /**
    * 输入的长度校验,可以为空
    * 只要一个超出 返回false
    *  参数为输入框对象和描述字符串和数据最大长度
    */

    function isInputLimitNumber(texto,message,minlen,maxlen){
        var tvalue=getNumberTrim(texto);
        if(tvalue=="" || tvalue.length<1){
            texto.value=tvalue;
            return true;
        }
        if(isInputNumber(texto,message)){
            var num1=parseFloat(tvalue);
            var num2=Math.pow(10,maxlen)-1;
            var num3;
            if(minlen==1 || minlen==0) num3=0;
            else num3=Math.pow(10,(minlen-1));
            if(num1<=num2 && num1>=num3) return true;
            else{
                alert(message+"的数值在["+num3+","+num2+"]之间!");
                return false;
            }
        }else{
            return false;
        }
    }


    /**
    * 输入的长度校验,不可以为空
    * 只要一个超出 返回false
    *  参数为输入框对象和描述字符串和数据最大长度
    */

    function isInputNotNullLimitNumber(texto,message,minlen,maxlen){
        var tvalue=getNumberTrim(texto);
        if(tvalue=="" || tvalue.length<1){
            alert(message+"不能为空！");
            texto.value=tvalue;
            return false;
        }
        if(isInputNumber(texto,message)){
            var num1=parseFloat(tvalue);
            var num2=Math.pow(10,maxlen)-1;
            var num3;
            if(minlen==1 || minlen==0) num3=0;
            else num3=Math.pow(10,(minlen-1));
            if(num1<=num2 && num1>=num3) return true;
            else{
                alert(message+"的数值在["+num3+","+num2+"]之间!");
                return false;
            }
        }else{
            return false;
        }
    }






    /**
    * 输入的长度校验,可以是数组,可以为空
    * 只要一个超出 返回false
    *  参数为输入框对象和描述字符串和数据最大长度
    */

    function isInputLimitNumbers(texto,message,len){
        return(isInputLimitsNumbers(texto,message,0,len));
    }



    /**
    * 输入的长度校验,可以是数组,不可以为空
    * 只要一个超出 返回false
    *  参数为输入框对象和描述字符串和数据最大长度
    */

    function isInputNotNullLimitNumbers(texto,message,len){
        return(isInputNotNullLimitsNumbers(texto,message,0,len));
    }






    /**
    * 输入的长度校验,可以是数组,可以为空
    * 只要一个超出范围 返回false
    *  参数为输入框对象和描述字符串和数据最小(len1)最大长度(len2)
    */

    function isInputLimitsNumbers(texto,message,len1,len2){
        if(typeof(texto.length)=="undefined"){
            if(isInputLimitNumber(texto,message,len1,len2)) return true;
            else return false;
        }else{
            for(var i=0;i<texto.length;i++){
                if(!isInputLimitNumber(texto[i],message,len1,len2)){
                    return false;
                }
            }
            return true;
        }


    }



    /**
    * 输入的长度校验,可以是数组,不可以为空
    * 只要一个超出范围 返回false
    *  参数为输入框对象和描述字符串和数据最小(len1)最大长度(len2)
    */

    function isInputNotNullLimitsNumbers(texto,message,len1,len2){
        if(typeof(texto.length)=="undefined"){
            if(isInputNotNullLimitNumber(texto,message,len1,len2)) return true;
            else return false;
        }else{
            for(var i=0;i<texto.length;i++){
                if(!isInputNotNullLimitNumber(texto[i],message,len1,len2)){
                    return false;
                }
            }
        return true;
        }
    }






    /**
    * 是否输入为数字判断,可以为空
    * 参数为输入框对象和描述字符串
    */

    function isInputNumber(texto,message){
        if(!isNumber(texto)){
            alert(message+"应为数字!");
            texto.focus();
            return false;
        }
        return true;
    }




    /**
    * 是否输入为数字判断,不可以为空
    * 参数为输入框对象和描述字符串
    */

    function isInputNotNullNumber(texto,message){
        if(isNullreplaced(texto)){
            alert(message+"不能为空！");
            texto.focus();
            return false;
        }
        if(!isInputNumber(texto,message)) return false;
        return true;
    }








    /**
    * 是否输入为数字判断,可以是数组，可以为空
    * 只要一个不是数字 返回false
    * 参数为输入框对象和描述字符串
    */

    function isInputNumbers(texto,message){
        if(typeof(texto.length)=="undefined"){
            return isInputNumber(texto,message);
        }else{
            for(var i=0;i<texto.length;i++){
                if(!isInputNumber(texto[i],message)){
                    return false;
                }
            }
        }
        return true;
    }




    /**
    * 是否输入为数字判断,可以是数组，不可以为空
    * 只要一个不是数字或为空 返回false
    * 参数为输入框对象和描述字符串
    */

    function isInputNotNullNumbers(texto,message){
        if(typeof(texto.length)=="undefined"){
            return isInputNotNullNumber(texto,message);
        }else{
            for(var i=0;i<texto.length;i++){
                if(!isInputNotNullNumber(texto[i],message)){
                    return false;
                }
            }
        }
        return true;
    }




    /**
    * 是否输入为数字判断,可以是数组，可以为空 ,最大不能超过maxo中指定的值,texto和maxo一一对应
    * 参数为输入框对象和描述字符串以及最大值所对应的对象maxo
    */

    function isInputMaxNumbers(texto,message,maxo){
    if(typeof(texto.length)=="undefined"){
        if(!isInputNumber(texto,message)){
            return false;
        }
        if(isNullreplaced(texto)) return true;
        var num=parseFloat(texto.value);
        var maxnum=parseFloat(maxo.value);
        if(isNaN(num) || isNaN(maxnum) || num > maxnum ){
            alert(message+"应为数字,且不能大于:"+maxo.value);
            texto.focus();
            return false;
        }
    }else{
        for(var i=0;i<texto.length;i++){
            if(!isInputNumber(texto[i],message)){
                return false;
            }
            if(isNullreplaced(texto[i])) continue;
                var num=parseFloat(texto[i].value);
                var maxnum=parseFloat(maxo[i].value);
                if(isNaN(num) || isNaN(maxnum) || num > maxnum ){
                    alert(message+"应为数字,且不能大于:"+maxo[i].value);
                    texto[i].focus();
                    return false;
                }
            }
        }
        return true;
    }
    /**
    * 判断字符串是否是数字
    * 
    */
	function isNumberS(vlaue){
	    var vlaue="1"+vlaue;	
	    if (isNaN(vlaue)||(parseInt(vlaue)<0)||(vlaue.length<1)) {
		    return false;
	    }
	    return true;
	}

    /**
    * 是否输入为数字判断,可以是数组，不可以为空 ,最大不能超过maxo中指定的值,texto和maxo一一对应
    * 参数为输入框对象和描述字符串以及最大值所对应的对象maxo
    */

    function isInputNotNullMaxNumbers(texto,message,maxo){
        if(!isInputNotNullNumbers(texto,message)) return false;
        if(!isInputMaxNumbers(texto,message,maxo)) return false;
        return true;
    }







    /**
    * 判断输入的数字(可以是数组，可以为空)之和是否超过最大数maxval
    * 参数为输入框对象和描述字符串以及最大值maxval
    */

    function isInputSumNumbers(texto,message,maxval){
        if(typeof(texto.length)=="undefined"){
            if(!isInputNumber(texto,message)){
                return false;
            }
            if(isNullreplaced(texto)) return true;
            var num=parseFloat(texto.value);
            var maxnum=parseFloat(maxval);
            if(isNaN(num) || isNaN(num) || num > maxnum ){
                alert(message+"应为数字,且不能大于:"+maxval);
                texto.focus();
                return false;
            }
        }else{
            var sumnum=0;
            for(var i=0;i<texto.length;i++){
                if(!isInputNumber(texto[i],message)){
                    return false;
                }
                if(isNullreplaced(texto[i])) continue;
                var num=parseFloat(texto[i].value);
                sumnum+=num;
            }
            var maxnum=parseFloat(maxval);
            if(isNaN(maxnum) || sumnum > maxnum + 0.01 ){
                alert(message+"应为数字,且各项之和不能大于:"+maxval);
                texto[0].focus();
                return false;
            }
        }
        return true;
    }



    /**
    * 判断输入的数字(可以是数组，不可以为空)之和是否超过最大数maxval
    * 参数为输入框对象和描述字符串以及最大值maxval
    */

    function isInputNotNullSumNumbers(texto,message,maxval){
        if(!isInputNotNullNumbers(texto,message)) return false;
        if(!isInputSumNumbers(texto,message,maxval)) return false;
        return true;
    }






    /**
    * 是否输入为数字判断,可以是数组，可以为空 ,只能有num位小数
    * 参数为输入框对象和描述字符串以及小数位数
    */

    function isInputDecimalNumbers(texto,message,num){
        if(typeof(texto.length)=="undefined"){
            if(!isInputNumber(texto,message)){
                return false;
            }
            var len=getDecimal(texto)
            if(len>num){
                alert(message+"保留"+num+"位小数!");
                texto.focus();
                return false;
            }
        }else{
            for(var i=0;i<texto.length;i++) {
                if(!isInputNumber(texto[i],message)){
                    return false;
                }
                var len=getDecimal(texto[i])
                if(len>num){
                    alert(message+"保留"+num+"位小数!");
                    texto[i].focus();
                    return false;
                }
            }
        }
        return true;
    }



    /**
    * 是否输入为数字判断,可以是数组，不可以为空 ,只能有num位小数
    * 参数为输入框对象和描述字符串以及小数位数
    */

    function isInputNotNullDecimalNumbers(texto,message,num){
        if(!isInputNotNullNumbers(texto,message)) return false;
        if(!isInputDecimalNumbers(texto,message,num)) return false;
        return true;
    }






    /**
    * 是否输入为数字判断,可以是数组，可以为空 ,只能有num位小数,
    * 最大的长度不超过alen（不包括小数点）,alen等于整数长度同小数长度之和
    */

    function isInputDecimalLimitNumbers(texto,message,alen, num) {
        if(!isInputDecimalNumbers(texto,message,num)) return false;
        if(typeof(texto.length)=="undefined") {
            if(!isInputNumber(texto,message)){
                return false;
            }
            var len=getDecimal(texto);
            var len1=getInteger(texto);
            if(len1 > (alen - num)) {
                alert(message + "数据的整数长度不能超过" + (alen - num) + "位!");
                texto.focus();
                return false;
            }
        }else{
            for(var i=0;i<texto.length;i++) {
                if(!isInputNumber(texto[i],message)){
                    return false;
                }
                var len=getDecimal(texto[i]);
                var len1=getInteger(texto[i]);
                if(len1 > (alen - num)) {
                    alert(message + "数据的整数长度不能超过" + (alen-num) + "位!");
                        texto[i].focus();
                        return false;
                }
            }
        }
        return true;
    }



        /**
	* 是否输入为数字判断,可以是数组，不可以为空 ,只能有num位小数,
	* 最大的长度不超过alen（不包括小数点）,alen等于整数长度同小数长度之和
	*/

        function isInputNotNullDecimalLimitNumbers(texto,message,alen, num) {
	    if(!isInputNotNullNumbers(texto,message)) return false;
            if(!isInputDecimalLimitNumbers(texto,message,alen,num)) return false;
            return true;
        }










    function isInputENumber(texto,message){
        var tvalue=texto.value;
        if(isNullreplaced(texto)) return true;
        tvalue=tvalue.toLowerCase();
        if(isNumber(texto)){
            if(tvalue.indexOf("e") != -1){
                if((parseFloat(tvalue)!="Infinity") && (!isNaN(parseFloat(tvalue)))) return true;
                else{
                    alert(message+"科学计数法的格式应为：\n\n由一个浮点数后跟e或E再跟一个\n不大于308的十进制整数构成。\n\n例：1.23E34。");
                    texto.focus();
                    return false;
                }
            }
        }else{
            alert(message+"应为数字!");
            texto.focus();
            return false;
        }
    }