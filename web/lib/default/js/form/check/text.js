

   /**
    * 替换字符串左右两边空格，可以为单个输入框或数组
    * 参数为输入框对象和描述字符串
    */

    function InputNullsreplaced(texto){
        if(typeof(texto.length)=="undefined"){
            texto.value=getTrim(texto.value);
        }else{
            for(var i=0;i<texto.length;i++) {
                texto[i].value=getTrim(texto[i].value);
            }
        }
    }

    /**
    * 是否输入为空判断(不替换左右两边空格)
    * 只要为空 返回true
    * 参数为输入框对象和描述字符串
    */

    function isInputNull(texto,message){   
        if(isNull(texto)){
            alert(message+"不能为空!");
            texto.focus();
            return true;
        }
        return false;
    }

   /**
    * 是否输入为空判断(替换左右两边空格)
    * 只要为空 返回true
    * 参数为输入框对象和描述字符串
    */

    function isInputNullreplaced(texto,message){    	
        InputNullsreplaced(texto);
	    return isInputNull(texto,message)
    }



    /**
    * 是否输入为空判断,可以是数组(不替换左右两边空格)
    * 只要一个为空 返回true
    *  参数为输入框对象和描述字符串
    */

    function isInputNulls(texto,message){
        if(typeof(texto.length)=="undefined"){
            return isInputNull(texto,message);
        }else{
            for(var i=0;i<texto.length;i++) {
                if(isInputNull(texto[i],message)) {
                    return true;
                }
            }
        }
        return false;
    }


   /**
    * 是否输入为空判断,可以是数组(替换左右两边空格)
    * 只要一个为空 返回true
    *  参数为输入框对象和描述字符串
    */

    function isInputNullsreplaced(texto,message){
        InputNullsreplaced(texto);
	    return isInputNulls(texto,message)

    }


    /**
    * 输入字符串的长度校验,可以为空(不替换左右两边空格)
    * 只要一个超出范围 返回false
    *  参数为输入框对象和描述字符串和数据最大长度
    */
    function isInputLimitsChar(texto,message,minlen,maxlen){
        var tvalue=texto.value;
        if(tvalue=="") return true;
        if(tvalue.length>maxlen || tvalue.length<minlen){
            alert(message+"长度在["+minlen+"位--"+maxlen+"位]之间！");
            texto.focus();
            return false;
        }
        return true;
    }


   /**
    * 输入字符串的长度校验,可以为空(替换左右两边空格)
    * 只要一个超出范围 返回false
    *  参数为输入框对象和描述字符串和数据最大长度
    */
    function isInputLimitsCharreplaced(texto,message,minlen,maxlen){
        InputNullsreplaced(texto);
	    return isInputLimitsChar(texto,message,minlen,maxlen)
    }




    /**
    * 输入字符串的长度校验,不可以为空(不替换左右两边空格)
    * 只要一个超出范围 返回false
    *  参数为输入框对象和描述字符串和数据最大长度
    */
    function isInputNotNullLimitsChar(texto,message,minlen,maxlen){
        var tvalue=texto.value;
        if(tvalue==""){
            texto.focus();
            alert(message+"不能为空！");
            return false;
        }
        if(tvalue.length>maxlen || tvalue.length<minlen){
            alert(message+"长度在("+minlen+"位--"+maxlen+"位]之间！");
            texto.focus();
            return false;
        }
        return true;
    }


    /**
    * 输入字符串的长度校验,不可以为空(替换左右两边空格)
    * 只要一个超出范围 返回false
    *  参数为输入框对象和描述字符串和数据最大长度
    */
    function isInputNotNullLimitsCharreplaced(texto,message,minlen,maxlen){
       InputNullsreplaced(texto);
	    return isInputNotNullLimitsChar(texto,message,minlen,maxlen);
    }



     /**
    * 输入字符串的长度校验,可以为空(不替换左右两边空格)
    * 只要一个超出范围 返回false
    *  参数为输入框对象和描述字符串和数据最大长度
    */
    function isInputLimitChar(texto,message,len){
        if(isInputLimitsChar(texto,message,0,len))
            return true;
        else
            return false;
    }

     /**
    * 输入字符串的长度校验,可以为空(替换左右两边空格)
    * 只要一个超出范围 返回false
    *  参数为输入框对象和描述字符串和数据最大长度
    */
    function isInputLimitCharreplaced(texto,message,len){
        InputNullsreplaced(texto);
	    return isInputLimitChar(texto,message,len)
    }



    /**
    * 输入字符串的长度校验,可以为空(不替换左右两边空格)
    * 只要一个超出 返回false
    *  参数为输入框对象和描述字符串和数据最大长度
    */
    function isInputNotNullLimitChar(texto,message,len){
        if(isInputNotNullLimitsChar(texto,message,0,len))
            return true;
        else
            return false;
    }


    /**
    * 输入字符串的长度校验,可以为空(替换左右两边空格)
    * 只要一个超出 返回false
    *  参数为输入框对象和描述字符串和数据最大长度
    */
    function isInputNotNullLimitCharreplaced(texto,message,len){
        InputNullsreplaced(texto);
	    return isInputNotNullLimitChar(texto,message,len);
    }


    /**
    * 输入字符串的长度校验,可以是数组,可以为空(不替换左右两边空格)
    * 只要一个超出 返回false
    *  参数为输入框对象和描述字符串和数据最大长度     ok
    */

    function isInputLimitChars(texto,message,len){
        if(typeof(texto.length)=="undefined"){
            if(isInputLimitChar(texto,message,len))
                return true;
            else
                return false;
        }else{
            for(var i=0;i<texto.length;i++){
                if(!isInputLimitChar(texto[i],message,len)){
                    return false;
                }
            }
        }
        return true;
    }


    /**
    * 输入字符串的长度校验,可以是数组,可以为空(替换左右两边空格)
    * 只要一个超出 返回false
    *  参数为输入框对象和描述字符串和数据最大长度     ok
    */

    function isInputLimitCharsreplaced(texto,message,len){
        InputNullsreplaced(texto);
	    return isInputLimitChars(texto,message,len);
    }


    /**
    * 输入字符串的长度校验,可以是数组,不可以为空(不替换左右两边空格)
    * 只要一个超出 返回false
    *  参数为输入框对象和描述字符串和数据最大长度  ok
    */

    function isInputNotNullLimitChars(texto,message,len){
        if(typeof(texto.length)=="undefined"){
            if(isInputNotNullLimitsChar(texto,message,0,len))
                return true;
            else
                return false;
        }else{
            for(var i=0;i<texto.length;i++){
                if(!isInputNotNullLimitsChar(texto[i],message,0,len)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
    * 输入字符串的长度校验,可以是数组,不可以为空(替换左右两边空格)
    * 只要一个超出 返回false
    *  参数为输入框对象和描述字符串和数据最大长度  ok
    */

    function isInputNotNullLimitCharsreplaced(texto,message,len){
        InputNullsreplaced(texto);
	    return isInputNotNullLimitChars(texto,message,len);
    }





    /**
    * 输入字符串的长度校验,可以是数组,可以为空格(不替换左右两边空格)
    * 只要一个超出范围 返回false
    *  参数为输入框对象和描述字符串和数据最小(len1)最大长度(len2)  ok
    */

    function isInputLimitsChars(texto,message,len1,len2){
        if(typeof(texto.length)=="undefined"){
            if(isInputLimitsChar(texto,message,len1,len2))
                return true;
            else
                return false;
        }else{
            for(var i=0;i<texto.length;i++){
                if(!isInputLimitsChar(texto[i],message,len1,len2)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
    * 输入字符串的长度校验,可以是数组,可以为空格(替换左右两边空格)
    * 只要一个超出范围 返回false
    *  参数为输入框对象和描述字符串和数据最小(len1)最大长度(len2)  ok
    */

    function isInputLimitsCharsreplaced(texto,message,len1,len2){
	    InputNullsreplaced(texto);
	    return isInputLimitsChars(texto,message,len1,len2);
    }




    /**
    * 输入字符串的长度校验,可以是数组(不替换左右两边空格)
    * 只要一个超出范围 返回false
    *  参数为输入框对象和描述字符串和数据最小(len1)最大长度(len2)  ok
    */

    function isInputNotNullLimitsChars(texto,message,len1,len2){
        if(typeof(texto.length)=="undefined"){
            if(isInputNotNullLimitsChar(texto,message,len1,len2))
                return true;
            else
                return false;
        }else{
            for(var i=0;i<texto.length;i++){
                if(!isInputNotNullLimitsChar(texto[i],message,len1,len2)){
                    return false;
                }
            }
        }
        return true;
    }


    /**
    * 输入字符串的长度校验,可以是数组(替换左右两边空格)
    * 只要一个超出范围 返回false
    *  参数为输入框对象和描述字符串和数据最小(len1)最大长度(len2)  ok
    */

    function isInputNotNullLimitsCharsreplaced(texto,message,len1,len2){
	    InputNullsreplaced(texto);
        return isInputNotNullLimitsChars(texto,message,len1,len2);
    }

    /**
    *去首尾空格
    *参数为输入框对象
    */

    function trim(texto){
        var tvalue=texto.value;
        tvalue=getTrim(tvalue);
        texto.value=tvalue;
        texto.focus();
        return tvalue;
    }


    /**
    *去空格,字符串中的所有空格均被去除
    *参数为输入框对象
    */

    function trimAll(texto){
        var tvalue=texto.value;
        tvalue=getAllTrim(tvalue);
        texto.value=tvalue;
        texto.focus();
        return tvalue;
    }

    /**
    *去首空格,去掉字符串头部的所有空格，直到出现非空格字符
    *参数为输入框对象
    */

    function trimFore(texto){
        tvalue=texto.value;
        tvalue=getLeftTrim(tvalue);
        texto.value=tvalue;
        texto.focus();
        return tvalue;
    }


    /**
    *去尾空格,去掉字符串尾部的所有空格，直到出现非空格字符(反方向为准)
    *参数为输入框对象
    */

    function trimEnd(texto){
        tvalue=texto.value;
        tvalue=getRightTrim(tvalue);
        texto.value=tvalue;
        texto.focus();
        return tvalue;
    }
