   /********************************************************
	* У��ͼƬ,�绰����,��������,��������,���֤����Ч��	
	* author      :wangyf
	* create-date :2011-12-01
	********************************************************/


   /*
    * У���ķ�Ϊͼ���ļ�,����Ϊ��
	* @param texto �ļ������
	* @param message �ļ������������
    * @return
    */
    function  isImage(texto,message){
        var filename=texto.value;
        filename=getTrim(filename);
        texto.value=filename;
        if(filename=="") return true;
        if(filename.indexOf(".")==0){
            alert("�ļ�������!");
            texto.focus();
            return false;
        }
        var len=filename.length;
        if(len>4){
            var str=filename.substring((len-6),len);
            if(str.toLowerCase().indexOf(".gif")>0) return true;
            if(str.toLowerCase().indexOf(".jpg")>0) return true;
        }
        alert(message + "����ѡ��GIF��JPGͼ���ļ�!");
        texto.focus();
        return false;
    }
    /*
     * ����绰�������Ч��,����Ϊ��
	 * @param texto �ı������
	 * @param message �ı������������
	 * @return 
     */
    function isInputTelephone(texto,message){
        var tvlaue=texto.value;
        tvlaue=getTrim(tvlaue);
        texto.value=tvlaue;
        if(tvlaue=="") return true;
        while(tvlaue.indexOf(",") != -1)  tvlaue = tvlaue.replace(",","");
        while(tvlaue.indexOf(";") != -1)  tvlaue = tvlaue.replace(";","");
        while(tvlaue.indexOf(":") != -1)  tvlaue = tvlaue.replace(":","");
        while(tvlaue.indexOf(" ") != -1)  tvlaue = tvlaue.replace(" ","");
        while(tvlaue.indexOf("-") != -1)  tvlaue = tvlaue.replace("-","");
        while(tvlaue.indexOf("/") != -1)  tvlaue = tvlaue.replace("/","");
        while(tvlaue.indexOf("_") != -1)  tvlaue = tvlaue.replace("_","");
        while(tvlaue.indexOf("(") != -1)  tvlaue = tvlaue.replace("(","");
        while(tvlaue.indexOf(")") != -1)  tvlaue = tvlaue.replace(")","");
        var tvlaue="1"+tvlaue;
        if (isNaN(tvlaue) || (parseInt(tvlaue)<=0) || (tvlaue.length<1) || tvlaue.indexOf(".")!=-1){
            alert(message+"���Ϸ�,����ֻ������,;:-/_()����!");
            texto.focus();
            return false;
        }
        if(tvlaue!=tvlaue.replace("e","") || tvlaue!=tvlaue.replace("E","")){
            alert(message+"���Ϸ�!");
            texto.focus();
            return false;
        }
        return true;
    }
    /*
     * ����绰�������Ч��,������Ϊ��
	 * @param texto �ı������
	 * @param message �ı������������
	 * @return 
     */
    function isInputNotNullTelephone(texto,message){
        if(isNullreplaced(texto)){
            texto.focus();
            alert(message+"����Ϊ�գ�");
            return false;
        }
        return isInputTelephone(texto,message);
    }
    /*
     * ����绰�������Ч��,���������飬����Ϊ��
	 * @param texto �ı����������
	 * @param message �ı������������
	 * @return 
     */
    function isInputTelephones(texto,message){
        if(typeof(texto.length)=="undefined") return isInputTelephone(texto,message);
        else for(var i=0;i<texto.length;i++)
        if(!isInputTelephone(texto[i],message)) return false;
        return true;
    }
    /*
     * ����绰�������Ч��,���������飬������Ϊ��
	 * @param texto �ı����������
	 * @param message �ı������������
	 * @return 
     */
    function isInputNotNullTelephones(texto,message){
        if(typeof(texto.length)=="undefined") return isInputNotNullTelephone(texto,message);
        else for(var i=0;i<texto.length;i++)
        if(!isInputNotNullTelephone(texto[i],message)) return false;
        return true;
    }
    /*
     * ���������������Ч�ԣ�����Ϊ��
	 * @param texto �ı������
	 * @param message �ı������������
	 * @return 
     */
	function isInputPostCode(texto,message){
		var tvalue=texto.value;
        tvalue=getTrim(tvalue);
        texto.value=tvalue;
        if(tvalue=="") return true;
		if((!isNumber(texto))||(tvalue.indexOf(".")!=-1)||(tvalue.length!=6)){
			alert(message+"���Ϸ���");
			texto.focus();
            return false;
        }
		return true;
	}
    /*
     * ��������������Ч��,����Ϊ��
	 * @param texto �ı������
	 * @param message �ı������������
	 * @return 
     */
    function isInputEmail(texto,message){
        var tvalue=texto.value;
        tvalue=getTrim(tvalue);
        texto.value=tvalue;
        if(tvalue=="") return true;
        if(getNumberIncludeString(tvalue,"@")==1 && getNumberIncludeString(tvalue,".")>=1 && getNumberIncludeString(tvalue,"@.")!=1 && getNumberIncludeString(tvalue,".@")!=1)
            return true;
        else{
            alert(message+"���Ϸ�!");
            texto.focus();
            return false;
        }
    }
    /*
     * ��������������Ч��,������Ϊ��
	 * @param texto �ı������
	 * @param message �ı������������
	 * @return 
     */
    function isInputNotNullEmail(texto,message){
        if(isNullreplaced(texto)){
            texto.focus();
            alert(message+"����Ϊ��");
            return false;
        }
        return isInputEmail(texto,message);
    }
    /*
     * ��������������Ч��,����Ϊ��
	 * @param texto �ı����������
	 * @param message �ı������������
	 * @return 
     */
    function isInputEmails(texto,message){
        if(typeof(texto.length)=="undefined")
            return isInputEmail(texto,message);
        else
            for(var i=0;i<texto.length;i++)
                if(!isInputEmail(texto[i],message)) return false;
        return true;
    }
    /*
     * ��������������Ч��,������Ϊ��
	 * @param texto �ı����������
	 * @param message �ı������������
	 * @return 
     */
    function isInputNotNullEmails(texto,message){
        if(typeof(texto.length)=="undefined")
            return isInputNotNullEmail(texto,message);
        else
            for(var i=0;i<texto.length;i++)
                if(!isInputNotNullEmail(texto[i],message)) return false;
        return true;
    }
    /*
     * �������֤�������Ч��
	 * @param texto �ı������	
	 * @return 
     */
	function checkIdcard(testo){
		var Errors=new Array(
		"��֤ͨ��",
		"���֤����λ������!",
		"���֤����������ڳ�����Χ���зǷ��ַ�!",
		"���֤����У�����!",
		"���֤�����Ƿ�!"
		);
		var area={11:"����",12:"���",13:"�ӱ�",14:"ɽ��",15:"���ɹ�",21:"����",22:"����",23:"������",31:"�Ϻ�",32:"����",33:"�㽭",34:"����",35:"����",36:"����",37:"ɽ��",41:"����",42:"����",43:"����",44:"�㶫",45:"����",46:"����",50:"����",51:"�Ĵ�",52:"����",53:"����",54:"����",61:"����",62:"����",63:"�ຣ",64:"����",65:"�½�",71:"̨��",81:"���",82:"����",91:"����"} 
		var idcard;

		if(testo)
		{
			idcard=testo.value;
		}

		var Y,JYM;
		var S,M;
		var idcard_array = new Array();
		idcard_array = idcard.split("");
		//��������

		if(area[parseInt(idcard.substr(0,2))]==null) return Errors[4];
		//��ݺ���λ������ʽ����
		switch(idcard.length){
		case 15:
		if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
			ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//���Գ������ڵĺϷ���
		} else {
			ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//���Գ������ڵĺϷ���
		}
		if(ereg.test(idcard)) return Errors[0];
		else return Errors[2];
		break;
		case 18:
		//18λ��ݺ�����
		//�������ڵĺϷ��Լ�� 
		//��������:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
		//ƽ������:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
		if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){
			ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//����������ڵĺϷ���������ʽ
		} else {
			ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//ƽ��������ڵĺϷ���������ʽ
		}
		if(ereg.test(idcard)){//���Գ������ڵĺϷ���
    		return Errors[0];
		/*����У��λ
		S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
			+ (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
			+ (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
			+ (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
			+ (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
			+ (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
			+ (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
			+ parseInt(idcard_array[7]) * 1 
			+ parseInt(idcard_array[8]) * 6
			+ parseInt(idcard_array[9]) * 3 ;
		Y = S % 11;
		M = "F";
		JYM = "10X98765432";
		M = JYM.substr(Y,1);//�ж�У��λ
		if(M == idcard_array[17]) return Errors[0]; //���ID��У��λ
		else return Errors[3];
		*/		
		}
		else return Errors[2];
		break;
		default:
		return Errors[1];
		break;
		}
	}

   /**
    *�������texto�У������¼����س���(�������ƽ���)����������Ӧת�Ƶ�����formto�е�Ԫ�ض���
    *���� e Ϊ event, formto �������texto ��������
    */

    function toMoveFocus(e,formto,texto){
        var i;
        var j=0;
        var k=formto.elements.length;
        var ob;
        var key_Number=(navigator.appName=="Netscape")?e.which:e.keyCode;
        if(key_Number!=38  && key_Number!=40 && key_Number!=13){
            return ;
        }else{
            for(i=0;i<k;i++){
                if(formto.elements[i]!=texto && formto.elements[i].type!="hidden"){
                    ob=formto.elements[i];
                    if(j==1){
                        ob.focus();
                        break;
                    }
                }else if(formto.elements[i]==texto){
                    j=1;
                    if((key_Number==38 && i==0) || ((key_Number==40 || key_Number==13) && i==k-1))
                        break;
                    if(key_Number==38){
                        ob.focus();
                        break;
                    }
                }
            }
            return;
        }
    }