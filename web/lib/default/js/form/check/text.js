

   /**
    * �滻�ַ����������߿ո񣬿���Ϊ��������������
    * ����Ϊ��������������ַ���
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
    * �Ƿ�����Ϊ���ж�(���滻�������߿ո�)
    * ֻҪΪ�� ����true
    * ����Ϊ��������������ַ���
    */

    function isInputNull(texto,message){   
        if(isNull(texto)){
            alert(message+"����Ϊ��!");
            texto.focus();
            return true;
        }
        return false;
    }

   /**
    * �Ƿ�����Ϊ���ж�(�滻�������߿ո�)
    * ֻҪΪ�� ����true
    * ����Ϊ��������������ַ���
    */

    function isInputNullreplaced(texto,message){    	
        InputNullsreplaced(texto);
	    return isInputNull(texto,message)
    }



    /**
    * �Ƿ�����Ϊ���ж�,����������(���滻�������߿ո�)
    * ֻҪһ��Ϊ�� ����true
    *  ����Ϊ��������������ַ���
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
    * �Ƿ�����Ϊ���ж�,����������(�滻�������߿ո�)
    * ֻҪһ��Ϊ�� ����true
    *  ����Ϊ��������������ַ���
    */

    function isInputNullsreplaced(texto,message){
        InputNullsreplaced(texto);
	    return isInputNulls(texto,message)

    }


    /**
    * �����ַ����ĳ���У��,����Ϊ��(���滻�������߿ո�)
    * ֻҪһ��������Χ ����false
    *  ����Ϊ��������������ַ�����������󳤶�
    */
    function isInputLimitsChar(texto,message,minlen,maxlen){
        var tvalue=texto.value;
        if(tvalue=="") return true;
        if(tvalue.length>maxlen || tvalue.length<minlen){
            alert(message+"������["+minlen+"λ--"+maxlen+"λ]֮�䣡");
            texto.focus();
            return false;
        }
        return true;
    }


   /**
    * �����ַ����ĳ���У��,����Ϊ��(�滻�������߿ո�)
    * ֻҪһ��������Χ ����false
    *  ����Ϊ��������������ַ�����������󳤶�
    */
    function isInputLimitsCharreplaced(texto,message,minlen,maxlen){
        InputNullsreplaced(texto);
	    return isInputLimitsChar(texto,message,minlen,maxlen)
    }




    /**
    * �����ַ����ĳ���У��,������Ϊ��(���滻�������߿ո�)
    * ֻҪһ��������Χ ����false
    *  ����Ϊ��������������ַ�����������󳤶�
    */
    function isInputNotNullLimitsChar(texto,message,minlen,maxlen){
        var tvalue=texto.value;
        if(tvalue==""){
            texto.focus();
            alert(message+"����Ϊ�գ�");
            return false;
        }
        if(tvalue.length>maxlen || tvalue.length<minlen){
            alert(message+"������("+minlen+"λ--"+maxlen+"λ]֮�䣡");
            texto.focus();
            return false;
        }
        return true;
    }


    /**
    * �����ַ����ĳ���У��,������Ϊ��(�滻�������߿ո�)
    * ֻҪһ��������Χ ����false
    *  ����Ϊ��������������ַ�����������󳤶�
    */
    function isInputNotNullLimitsCharreplaced(texto,message,minlen,maxlen){
       InputNullsreplaced(texto);
	    return isInputNotNullLimitsChar(texto,message,minlen,maxlen);
    }



     /**
    * �����ַ����ĳ���У��,����Ϊ��(���滻�������߿ո�)
    * ֻҪһ��������Χ ����false
    *  ����Ϊ��������������ַ�����������󳤶�
    */
    function isInputLimitChar(texto,message,len){
        if(isInputLimitsChar(texto,message,0,len))
            return true;
        else
            return false;
    }

     /**
    * �����ַ����ĳ���У��,����Ϊ��(�滻�������߿ո�)
    * ֻҪһ��������Χ ����false
    *  ����Ϊ��������������ַ�����������󳤶�
    */
    function isInputLimitCharreplaced(texto,message,len){
        InputNullsreplaced(texto);
	    return isInputLimitChar(texto,message,len)
    }



    /**
    * �����ַ����ĳ���У��,����Ϊ��(���滻�������߿ո�)
    * ֻҪһ������ ����false
    *  ����Ϊ��������������ַ�����������󳤶�
    */
    function isInputNotNullLimitChar(texto,message,len){
        if(isInputNotNullLimitsChar(texto,message,0,len))
            return true;
        else
            return false;
    }


    /**
    * �����ַ����ĳ���У��,����Ϊ��(�滻�������߿ո�)
    * ֻҪһ������ ����false
    *  ����Ϊ��������������ַ�����������󳤶�
    */
    function isInputNotNullLimitCharreplaced(texto,message,len){
        InputNullsreplaced(texto);
	    return isInputNotNullLimitChar(texto,message,len);
    }


    /**
    * �����ַ����ĳ���У��,����������,����Ϊ��(���滻�������߿ո�)
    * ֻҪһ������ ����false
    *  ����Ϊ��������������ַ�����������󳤶�     ok
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
    * �����ַ����ĳ���У��,����������,����Ϊ��(�滻�������߿ո�)
    * ֻҪһ������ ����false
    *  ����Ϊ��������������ַ�����������󳤶�     ok
    */

    function isInputLimitCharsreplaced(texto,message,len){
        InputNullsreplaced(texto);
	    return isInputLimitChars(texto,message,len);
    }


    /**
    * �����ַ����ĳ���У��,����������,������Ϊ��(���滻�������߿ո�)
    * ֻҪһ������ ����false
    *  ����Ϊ��������������ַ�����������󳤶�  ok
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
    * �����ַ����ĳ���У��,����������,������Ϊ��(�滻�������߿ո�)
    * ֻҪһ������ ����false
    *  ����Ϊ��������������ַ�����������󳤶�  ok
    */

    function isInputNotNullLimitCharsreplaced(texto,message,len){
        InputNullsreplaced(texto);
	    return isInputNotNullLimitChars(texto,message,len);
    }





    /**
    * �����ַ����ĳ���У��,����������,����Ϊ�ո�(���滻�������߿ո�)
    * ֻҪһ��������Χ ����false
    *  ����Ϊ��������������ַ�����������С(len1)��󳤶�(len2)  ok
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
    * �����ַ����ĳ���У��,����������,����Ϊ�ո�(�滻�������߿ո�)
    * ֻҪһ��������Χ ����false
    *  ����Ϊ��������������ַ�����������С(len1)��󳤶�(len2)  ok
    */

    function isInputLimitsCharsreplaced(texto,message,len1,len2){
	    InputNullsreplaced(texto);
	    return isInputLimitsChars(texto,message,len1,len2);
    }




    /**
    * �����ַ����ĳ���У��,����������(���滻�������߿ո�)
    * ֻҪһ��������Χ ����false
    *  ����Ϊ��������������ַ�����������С(len1)��󳤶�(len2)  ok
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
    * �����ַ����ĳ���У��,����������(�滻�������߿ո�)
    * ֻҪһ��������Χ ����false
    *  ����Ϊ��������������ַ�����������С(len1)��󳤶�(len2)  ok
    */

    function isInputNotNullLimitsCharsreplaced(texto,message,len1,len2){
	    InputNullsreplaced(texto);
        return isInputNotNullLimitsChars(texto,message,len1,len2);
    }

    /**
    *ȥ��β�ո�
    *����Ϊ��������
    */

    function trim(texto){
        var tvalue=texto.value;
        tvalue=getTrim(tvalue);
        texto.value=tvalue;
        texto.focus();
        return tvalue;
    }


    /**
    *ȥ�ո�,�ַ����е����пո����ȥ��
    *����Ϊ��������
    */

    function trimAll(texto){
        var tvalue=texto.value;
        tvalue=getAllTrim(tvalue);
        texto.value=tvalue;
        texto.focus();
        return tvalue;
    }

    /**
    *ȥ�׿ո�,ȥ���ַ���ͷ�������пո�ֱ�����ַǿո��ַ�
    *����Ϊ��������
    */

    function trimFore(texto){
        tvalue=texto.value;
        tvalue=getLeftTrim(tvalue);
        texto.value=tvalue;
        texto.focus();
        return tvalue;
    }


    /**
    *ȥβ�ո�,ȥ���ַ���β�������пո�ֱ�����ַǿո��ַ�(������Ϊ׼)
    *����Ϊ��������
    */

    function trimEnd(texto){
        tvalue=texto.value;
        tvalue=getRightTrim(tvalue);
        texto.value=tvalue;
        texto.focus();
        return tvalue;
    }
