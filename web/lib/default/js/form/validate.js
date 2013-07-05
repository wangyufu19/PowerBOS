   /********************************************************
	* 校验图片,电话号码,电子邮箱,邮政编码,身份证的有效性	
	* author      :wangyf
	* create-date :2011-12-01
	********************************************************/


   /*
    * 校验文否为图形文件,可以为空
	* @param texto 文件框对象
	* @param message 文件框对象描述符
    * @return
    */
    function  isImage(texto,message){
        var filename=texto.value;
        filename=getTrim(filename);
        texto.value=filename;
        if(filename=="") return true;
        if(filename.indexOf(".")==0){
            alert("文件名出错!");
            texto.focus();
            return false;
        }
        var len=filename.length;
        if(len>4){
            var str=filename.substring((len-6),len);
            if(str.toLowerCase().indexOf(".gif")>0) return true;
            if(str.toLowerCase().indexOf(".jpg")>0) return true;
        }
        alert(message + "必须选择GIF或JPG图形文件!");
        texto.focus();
        return false;
    }
    /*
     * 检验电话号码的有效性,可以为空
	 * @param texto 文本框对象
	 * @param message 文本框对象描述符
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
            alert(message+"不合法,并且只允许含有,;:-/_()符号!");
            texto.focus();
            return false;
        }
        if(tvlaue!=tvlaue.replace("e","") || tvlaue!=tvlaue.replace("E","")){
            alert(message+"不合法!");
            texto.focus();
            return false;
        }
        return true;
    }
    /*
     * 检验电话号码的有效性,不可以为空
	 * @param texto 文本框对象
	 * @param message 文本框对象描述符
	 * @return 
     */
    function isInputNotNullTelephone(texto,message){
        if(isNullreplaced(texto)){
            texto.focus();
            alert(message+"不能为空！");
            return false;
        }
        return isInputTelephone(texto,message);
    }
    /*
     * 检验电话号码的有效性,可以是数组，可以为空
	 * @param texto 文本框对象数组
	 * @param message 文本框对象描述符
	 * @return 
     */
    function isInputTelephones(texto,message){
        if(typeof(texto.length)=="undefined") return isInputTelephone(texto,message);
        else for(var i=0;i<texto.length;i++)
        if(!isInputTelephone(texto[i],message)) return false;
        return true;
    }
    /*
     * 检验电话号码的有效性,可以是数组，不可以为空
	 * @param texto 文本框对象数组
	 * @param message 文本框对象描述符
	 * @return 
     */
    function isInputNotNullTelephones(texto,message){
        if(typeof(texto.length)=="undefined") return isInputNotNullTelephone(texto,message);
        else for(var i=0;i<texto.length;i++)
        if(!isInputNotNullTelephone(texto[i],message)) return false;
        return true;
    }
    /*
     * 检验邮政编码的有效性，可以为空
	 * @param texto 文本框对象
	 * @param message 文本框对象描述符
	 * @return 
     */
	function isInputPostCode(texto,message){
		var tvalue=texto.value;
        tvalue=getTrim(tvalue);
        texto.value=tvalue;
        if(tvalue=="") return true;
		if((!isNumber(texto))||(tvalue.indexOf(".")!=-1)||(tvalue.length!=6)){
			alert(message+"不合法！");
			texto.focus();
            return false;
        }
		return true;
	}
    /*
     * 检验电子邮箱的有效性,可以为空
	 * @param texto 文本框对象
	 * @param message 文本框对象描述符
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
            alert(message+"不合法!");
            texto.focus();
            return false;
        }
    }
    /*
     * 检验电子邮箱的有效性,不可以为空
	 * @param texto 文本框对象
	 * @param message 文本框对象描述符
	 * @return 
     */
    function isInputNotNullEmail(texto,message){
        if(isNullreplaced(texto)){
            texto.focus();
            alert(message+"不能为空");
            return false;
        }
        return isInputEmail(texto,message);
    }
    /*
     * 检验电子邮箱的有效性,可以为空
	 * @param texto 文本框对象数组
	 * @param message 文本框对象描述符
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
     * 检验电子邮箱的有效性,不可以为空
	 * @param texto 文本框对象数组
	 * @param message 文本框对象描述符
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
     * 检验身份证号码的有效性
	 * @param texto 文本框对象	
	 * @return 
     */
	function checkIdcard(testo){
		var Errors=new Array(
		"验证通过",
		"身份证号码位数不对!",
		"身份证号码出生日期超出范围或含有非法字符!",
		"身份证号码校验错误!",
		"身份证地区非法!"
		);
		var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"} 
		var idcard;

		if(testo)
		{
			idcard=testo.value;
		}

		var Y,JYM;
		var S,M;
		var idcard_array = new Array();
		idcard_array = idcard.split("");
		//地区检验

		if(area[parseInt(idcard.substr(0,2))]==null) return Errors[4];
		//身份号码位数及格式检验
		switch(idcard.length){
		case 15:
		if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
			ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
		} else {
			ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
		}
		if(ereg.test(idcard)) return Errors[0];
		else return Errors[2];
		break;
		case 18:
		//18位身份号码检测
		//出生日期的合法性检查 
		//闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
		//平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
		if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){
			ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
		} else {
			ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
		}
		if(ereg.test(idcard)){//测试出生日期的合法性
    		return Errors[0];
		/*计算校验位
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
		M = JYM.substr(Y,1);//判断校验位
		if(M == idcard_array[17]) return Errors[0]; //检测ID的校验位
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
    *在输入框texto中，按上下键及回车键(代表下移焦点)，将焦点相应转移到窗体formto中的元素对象
    *参数 e 为 event, formto 窗体对象，texto 输入框对象
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