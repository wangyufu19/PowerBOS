


    /*
	* �滻�����ַ����Ŀո�
	* ������������ֵ
	*/

    function getNumberTrim(texto){
        var tvalue=getTrim(texto.value);
        texto.value=tvalue;
        return tvalue;
    }

	/**
	* ȡ���������ֵ�С����λ��
	* ������������.
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
	* ȡ���������ֵ�����λ��
	* ������������.
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
    * �ж����������(������Ϊ��)�Ƿ����0, ����������
    * ֻҪһ��С��0����Ϊ�գ� ����false
    *  ����Ϊ��������������ַ���
    */

    function isInputNotNullPositiveNumbers(texto,message){
        if(typeof(texto.length)=="undefined"){
            if(!isInputNotNullNumber(texto,message)){
                return false;
            }
            var num=parseFloat(texto.value);
            if(isNaN(num) || num<=0.0){             //isNaN(num)�У�num����ӦΪtexto.value,
                alert(message+"ӦΪ�����������!");//���򽫻������"123ert"��������Ϊ��ȷ
                texto.focus();
                return false;
            }
        }else{
            for(var i=0;i<texto.length;i++){
                if(!isInputNotNullNumber(texto[i],message)){
                    return false;
                }
                var num=parseFloat(texto[i].value);
                if(isNaN(num) || num<=0.0){         //isNaN(num)�У�num����ӦΪtexto.value,
                    alert(message+"ӦΪ�����������!"); //���򽫻������"123ert"��������Ϊ��ȷ
                    texto[i].focus();
                    return false;
                }
            }
        }
        return true;
    }




    /**
    * �ж����������(����Ϊ��)�Ƿ����0, ����������
    * ֻҪһ��С�ڵ���0�� ����false
    *  ����Ϊ��������������ַ���
    */

    function isInputPositiveNumbers(texto,message){
        if(typeof(texto.length)=="undefined"){
            if(!isInputNumber(texto,message)){
                return false;
            }
            var tvalue=texto.value;
            tvalue=getNumberTrim(texto);
            var num=parseFloat(tvalue);
            if(tvalue!="" || num<=0.0){             //isNaN(num)�У�num����ӦΪtexto.value
                alert(message+"ӦΪ�����������!"); //���򽫻������"123ert"��������Ϊ��ȷ
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
                if(tvalue!="" &&  num<=0.0){          //isNaN(num)�У�num����ӦΪtexto.value,
                    alert(message+"ӦΪ�����������!");  //���򽫻������"123ert"��������Ϊ��ȷ
                    texto[i].focus();
                    return false;
                }
            }
        }
        return true;
    }



    /**
    * ����ĳ���У��,����Ϊ��
    * ֻҪһ������ ����false
    *  ����Ϊ��������������ַ�����������󳤶�
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
                alert(message+"����ֵ��["+num3+","+num2+"]֮��!");
                return false;
            }
        }else{
            return false;
        }
    }


    /**
    * ����ĳ���У��,������Ϊ��
    * ֻҪһ������ ����false
    *  ����Ϊ��������������ַ�����������󳤶�
    */

    function isInputNotNullLimitNumber(texto,message,minlen,maxlen){
        var tvalue=getNumberTrim(texto);
        if(tvalue=="" || tvalue.length<1){
            alert(message+"����Ϊ�գ�");
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
                alert(message+"����ֵ��["+num3+","+num2+"]֮��!");
                return false;
            }
        }else{
            return false;
        }
    }






    /**
    * ����ĳ���У��,����������,����Ϊ��
    * ֻҪһ������ ����false
    *  ����Ϊ��������������ַ�����������󳤶�
    */

    function isInputLimitNumbers(texto,message,len){
        return(isInputLimitsNumbers(texto,message,0,len));
    }



    /**
    * ����ĳ���У��,����������,������Ϊ��
    * ֻҪһ������ ����false
    *  ����Ϊ��������������ַ�����������󳤶�
    */

    function isInputNotNullLimitNumbers(texto,message,len){
        return(isInputNotNullLimitsNumbers(texto,message,0,len));
    }






    /**
    * ����ĳ���У��,����������,����Ϊ��
    * ֻҪһ��������Χ ����false
    *  ����Ϊ��������������ַ�����������С(len1)��󳤶�(len2)
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
    * ����ĳ���У��,����������,������Ϊ��
    * ֻҪһ��������Χ ����false
    *  ����Ϊ��������������ַ�����������С(len1)��󳤶�(len2)
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
    * �Ƿ�����Ϊ�����ж�,����Ϊ��
    * ����Ϊ��������������ַ���
    */

    function isInputNumber(texto,message){
        if(!isNumber(texto)){
            alert(message+"ӦΪ����!");
            texto.focus();
            return false;
        }
        return true;
    }




    /**
    * �Ƿ�����Ϊ�����ж�,������Ϊ��
    * ����Ϊ��������������ַ���
    */

    function isInputNotNullNumber(texto,message){
        if(isNullreplaced(texto)){
            alert(message+"����Ϊ�գ�");
            texto.focus();
            return false;
        }
        if(!isInputNumber(texto,message)) return false;
        return true;
    }








    /**
    * �Ƿ�����Ϊ�����ж�,���������飬����Ϊ��
    * ֻҪһ���������� ����false
    * ����Ϊ��������������ַ���
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
    * �Ƿ�����Ϊ�����ж�,���������飬������Ϊ��
    * ֻҪһ���������ֻ�Ϊ�� ����false
    * ����Ϊ��������������ַ���
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
    * �Ƿ�����Ϊ�����ж�,���������飬����Ϊ�� ,����ܳ���maxo��ָ����ֵ,texto��maxoһһ��Ӧ
    * ����Ϊ��������������ַ����Լ����ֵ����Ӧ�Ķ���maxo
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
            alert(message+"ӦΪ����,�Ҳ��ܴ���:"+maxo.value);
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
                    alert(message+"ӦΪ����,�Ҳ��ܴ���:"+maxo[i].value);
                    texto[i].focus();
                    return false;
                }
            }
        }
        return true;
    }
    /**
    * �ж��ַ����Ƿ�������
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
    * �Ƿ�����Ϊ�����ж�,���������飬������Ϊ�� ,����ܳ���maxo��ָ����ֵ,texto��maxoһһ��Ӧ
    * ����Ϊ��������������ַ����Լ����ֵ����Ӧ�Ķ���maxo
    */

    function isInputNotNullMaxNumbers(texto,message,maxo){
        if(!isInputNotNullNumbers(texto,message)) return false;
        if(!isInputMaxNumbers(texto,message,maxo)) return false;
        return true;
    }







    /**
    * �ж����������(���������飬����Ϊ��)֮���Ƿ񳬹������maxval
    * ����Ϊ��������������ַ����Լ����ֵmaxval
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
                alert(message+"ӦΪ����,�Ҳ��ܴ���:"+maxval);
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
                alert(message+"ӦΪ����,�Ҹ���֮�Ͳ��ܴ���:"+maxval);
                texto[0].focus();
                return false;
            }
        }
        return true;
    }



    /**
    * �ж����������(���������飬������Ϊ��)֮���Ƿ񳬹������maxval
    * ����Ϊ��������������ַ����Լ����ֵmaxval
    */

    function isInputNotNullSumNumbers(texto,message,maxval){
        if(!isInputNotNullNumbers(texto,message)) return false;
        if(!isInputSumNumbers(texto,message,maxval)) return false;
        return true;
    }






    /**
    * �Ƿ�����Ϊ�����ж�,���������飬����Ϊ�� ,ֻ����numλС��
    * ����Ϊ��������������ַ����Լ�С��λ��
    */

    function isInputDecimalNumbers(texto,message,num){
        if(typeof(texto.length)=="undefined"){
            if(!isInputNumber(texto,message)){
                return false;
            }
            var len=getDecimal(texto)
            if(len>num){
                alert(message+"����"+num+"λС��!");
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
                    alert(message+"����"+num+"λС��!");
                    texto[i].focus();
                    return false;
                }
            }
        }
        return true;
    }



    /**
    * �Ƿ�����Ϊ�����ж�,���������飬������Ϊ�� ,ֻ����numλС��
    * ����Ϊ��������������ַ����Լ�С��λ��
    */

    function isInputNotNullDecimalNumbers(texto,message,num){
        if(!isInputNotNullNumbers(texto,message)) return false;
        if(!isInputDecimalNumbers(texto,message,num)) return false;
        return true;
    }






    /**
    * �Ƿ�����Ϊ�����ж�,���������飬����Ϊ�� ,ֻ����numλС��,
    * ���ĳ��Ȳ�����alen��������С���㣩,alen������������ͬС������֮��
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
                alert(message + "���ݵ��������Ȳ��ܳ���" + (alen - num) + "λ!");
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
                    alert(message + "���ݵ��������Ȳ��ܳ���" + (alen-num) + "λ!");
                        texto[i].focus();
                        return false;
                }
            }
        }
        return true;
    }



        /**
	* �Ƿ�����Ϊ�����ж�,���������飬������Ϊ�� ,ֻ����numλС��,
	* ���ĳ��Ȳ�����alen��������С���㣩,alen������������ͬС������֮��
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
                    alert(message+"��ѧ�������ĸ�ʽӦΪ��\n\n��һ�����������e��E�ٸ�һ��\n������308��ʮ�����������ɡ�\n\n����1.23E34��");
                    texto.focus();
                    return false;
                }
            }
        }else{
            alert(message+"ӦΪ����!");
            texto.focus();
            return false;
        }
    }