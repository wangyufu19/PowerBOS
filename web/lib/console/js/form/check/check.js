	/*
	�ж�һ��form�ڵ��������������ݵĺϷ���
	param formName form������
	return true �Ϸ� false ���Ϸ�
	       
	------------------------------------------------------------------------------       
	check_f() �﷨����
	check�п���û�����ݣ���1��ʾ���������ֵ��������2����д��ʽ
	1��check=""	
	2��check="[typeName(number1[,nubmer2])],[not null],[message]"
	------------------------------------------------------------------------------
	typeName:����8����ʽ��
		number		���֣�������ʵ����
		char		Ӣ���ַ�
		string     ��Ӣ���ַ�widestring
		email		E-mail
		phone		�绰
		date		����	��YYYY-MM-DD��
		year		��		��YYYY��
		month		����	��YYYY-MM��
	    postcode    ��������
	
		
	�ǿ���������:
		not null	
	
	Ϊ�˷������ṩһ�¼�����д�ķ�ʽ��
	number = num; nu; n
	char = ch; c
	email = e
	phone = ph; p
	date = d
	year = y
	month = mon; m
	month_new = mon_n; mn
	not null = nn
	-----------------------------------------------------------------------------
	number1  number2 ��˵��
	typeName == number
		number1��ʵ�����ܳ��ȣ�������С���㣩
		number2��С���������λ����������ʱĬ��Ϊ0����ʾ��������ӦΪ������
	ע��number1 number2 ���������ʾ��������ӦΪʵ������ʱcheck��ֵ��û�����ţ�
		
	typeName == ����
		number1�������ַ�������󳤶�
		number2�������ַ�������С���ȣ�������ʱĬ��Ϊ0��
	
	
	-----------------------------------------------------------------------------
	ֻ����message��Ϊ��û������ģ���Ϊ�Ƿ�����       
	*/
	/*
	�ж�һ��form�ڵ��������������ݵĺϷ���
	*/
	function check_f(formName){		
		var len =formName.elements.length;
		var checkValue;
		var result=true;
		for(var i=0;i<len ;i++){
			if(!_check_f(formName.elements[i])){				
				result=false;
				break;
				i=len;
			}
		}	
		return result;
	}
	/*
	�ж�һ����������ݺϷ���
	param object ����
	return true �Ϸ� false ���Ϸ�
	*/
	function _check_f(object){
		checkValue=object.getAttribute("check");
		var result=true;
		if(checkValue!=null){
        //var tvalue=object.value;
        //tvalue=getTrim(tvalue);
        //object.value=tvalue;
			var result=analyse(checkValue);
			//alert(result[3]);
			switch(result[3]){
				
				case "not null":
				{
					if(isInputNullreplaced(object,result[4])){
						result=false;
						break;
					}
					break;
				}
			}

			if(result[1]!="-1" && result[0]!="number" && result[0]!="string") {
				if(result[1]=="0") {
			        if(object.value.length-result[2]>0){
			            alert(result[4]+"����\n����ӦС��"+result[2]+"λ!");
			            object.focus();
			            result=false;
			        }
		       }else if(object.value!=""){
							if(result[1] != result[2]){
		        		if(object.value.length-result[2]>0||object.value.length-result[1]<0) {
		        			alert(result[4]+"����\n����ӦС��"+result[2]+"λ,����"+result[1]+"λ!");
		        			object.focus();
									result=false;
								}
		        	}else{
								if(object.value.length != result[1]) {
									alert(result[4]+"����\n����Ӧ����"+result[1]+"λ!");
		        			object.focus();
									result=false;
								}
							}
		        }
			}
			if(result == false){
				return false;
			}
			

			switch(result[0]){
				case "char":
				{

					var intCount = 0;
					var chrs = object.value;
					for(var i = 0;i < chrs.length;i ++)
					{
							// Ascii�����255��˫�ֽڵ��ַ�
							if(chrs.charCodeAt(i) > 255){
								alert(result[4]+"����\n������Ӣ������!");
								object.focus();
								result=false;
								break;
							}
							/*var chr = chrs.charAt(i); // �������ַ��������ƣ�jbh
							if(!((chr>="A" && chr<="Z") || (chr>="a" && chr<="z") || (chr>="0" && chr<="9") || chr=='-' || chr=='_' || chr==' ' || chr=='.')){
								alert(result[4]+"����\n�������������ַ�!");
								object.focus();
								result=false;
								break;
							}*/
					}

					break;
				}

				case "widestring":
				{
					var intCount = 0;
					var chrs = object.value;
					for(var i = 0;i < chrs.length;i ++)
					{
							// Ascii�����255��˫�ֽڵ��ַ�
							if(chrs.charCodeAt(i) < 255){
								alert(result[4]+"����\n��������������!");
								object.focus();
								result=false;
								break;
							}
					}

					break;
				}

				case "string":
				{
					var intLen = 0;
					var chrs = object.value;
					for(var i = 0;i < chrs.length;i ++)
					{
							// Ascii�����255��˫�ֽڵ��ַ�
							if(chrs.charCodeAt(i) < 255){
								intLen += 1;
							}else{
								intLen += 2;
							}
					}
					if(result[1]=="0") {
						if(intLen-result[2]>0){
							alert(result[4]+"����\n����ӦС�ں���"+(result[2]/2)+"λ����Ӣ���ַ�"+result[2]+"λ!");
							object.focus();
							result=false;
						}
				   }else if(object.value!=""){
								if(result[1] != result[2]){
							if(object.value.length-result[2]>0 || object.value.length-result[1]<0) {
								alert(result[4]+"����\n����ӦС�ں���"+(result[2]/2)+"λ����Ӣ���ַ�"+result[2]+"λ,����"+result[1]+"λ!");
								object.focus();
										result=false;
									}
						}else{
									if(object.value.length != result[1]) {
										alert(result[4]+"����\n����Ӧ����"+result[1]+"λ!");
								object.focus();
										result=false;
									}
								}
					}
					break;
				}
			
				case "email":
				{
					if(!isInputEmail(object,result[4])){
						object.select();
						result=false;
						break;
					}
					break;
				}
				case "postcode":
				{
					if(!isInputPostCode(object,result[4])){
						object.select();
						result=false;
						break;
					}
					break;
				}
				case "phone":
				{
					if(!isInputTelephone(object,result[4])){
						object.select();
						result=false;
						break;
					}
					break;
				}
			
				case "date":
				{
					if(!dateCheck(object,result[4])){
						object.select();
						result=false;
						break;
					}else{
						var sValue1 = dateValueChange(object);
						if(sValue1!="-1"){
							object.value = sValue1;
						}
					}
					break;
				}

				case "time":
				{
					if(!isNull(object) && !isTime(object, result[4])){
						object.select();
						result=false;
						break;
					}
					break;
				}
				case "datetime":
				{
					if(!isNull(object) && !isDateTime(object, result[4])){
						object.select();
						result=false;
						break;
					}
					break;
				}
			
				case "number":
				{
					//alert(result[1]+":"+result[2]);
					if(result[1]=="-1") {
						if(!isNumber(object)) {
							object.select();
							alert(result[4]+"����\nӦ��ʵ��!");
							result=false;
							break;
						}
					} else if(result[1]=="0") {
						if(object.value.length>result[2]) {
							object.select();
							alert(result[4]+"����\n����ӦС�� "+result[2]+" λ!");
							result=false;
							break;
						}
						if((!isNumber(object))||(object.value.indexOf(".")!=-1)||(object.value.indexOf("-")!=-1)) {
							object.select();
							alert(result[4]+"����\nӦ��������!");
							result=false;
							break;
						}
					} else if(!isInputDecimalLimitNumbers(object,result[4],result[2],result[1])){
						object.select();
						result=false;
						break;
					}
					break;
				}
				
				case "year":
				{
					var temp=object.value;
					if(temp!=""&&temp.length>0){
						if((!isNumber(object))||temp.indexOf(".")!=-1) {
							object.select();
							alert(result[4]+"����\nӦΪ����!");
							result=false;
							break;
						} else if(temp<1800||temp>2500) {
							object.select();
							alert(result[4]+"����\n���� 1800 �� 2500 �����!");
							result=false;
							break;
						}
					}
					break;
				}
				
				case "month":
				{
					temp=object.value;
					if(temp!=""&&temp.length>0){
						var i=temp.indexOf("-");
						if(i==-1) {
							object.select();
							alert(result[4]+"����\n�����ʽΪ:YYYY-MM");
							result=false;
							break;
						} else {
							var temp1=temp.substring(0,i);
							var temp2=temp.substring(i+1);
							if(isNaN(temp1)||temp1.indexOf(".")!=-1||isNaN(temp2)||temp2.indexOf(".")!=-1) {
								object.select();
								alert(result[4]+"����\nӦΪ����!");
								result=false;
								break;
							} else if(temp1<1800||temp1>2500||temp2<1||temp2>12) {
								object.select();
								alert(result[4]+"����\n���� 1800-01 �� 2500-12 ������!");
								result=false;
								break;
							}
						}
					} 
					break;
				}
				case "month_new":
                {
					temp=object.value;
					if(temp!=""&&temp.length>0){
						var i=temp.length;
						if( isNaN(temp)||i!="6") {
							object.select();
							alert(result[4]+"����\n�����ʽΪ:YYYYMM!");
							result=false;
							break;
						} else {
							var temp1=temp.substring(0,4);
							var temp2=temp.substring(4,6);
							if(isNaN(temp1)||temp1.indexOf(".")!=-1){
								object.select();
								alert(result[4]+"����\n���ӦΪ����!");
								result=false;
								break;
							}if(isNaN(temp2)||temp2.indexOf(".")!=-1){
								object.select();
								alert(result[4]+"����\n�·�ӦΪ����!");
								result=false;
								break;
							}else if(temp1<1800||temp1>2500){
								object.select();
								alert(result[4]+"����\n���� 1800 �� 2500 �����!");
								result=false;
								break;
							}else if(temp2<1||temp2>12) {
								object.select();
								alert(result[4]+"����\n���� 01 �� 12 ���·�!");
								result=false;
								break;
							}
						}
					}
                   break;
				}
				case "minute":
					{
					
						temp=object.value;
						if(temp!=""&&temp.length>0){
								//��ʽУ��
								if (temp.length!=16){
									object.select();
									alert("" + result[4] + "\n�������׼��ʽ ��:YYYY-MM-DD HH:MI");
									result=false;
									break;
								}else if (!(temp.substring(4,5)=="-" && temp.substring(7,8)=="-" && temp.substring(10,11)==" " && temp.substring(13,14)==":")){
									object.select();
									alert("" + result[4] + "\n�������׼��ʽ ����YYYY-MM-DD HH:MI");
									result=false;
									break;
								}else {
								//�ꡢ�¡��ա�ʱ����У��
								var year = temp.substring(0,4);
								var month = temp.substring(5,7);
								
								var day = temp.substring(8,10);
								var hour = temp.substring(11,13);
								var minute = temp.substring(14,16);
									
									//ȷ���������һ��
									//month=parseInt(month);
									month=parseInt(month,10);//modified by ssq
									var maxday=31;
									if(month==4 || month==6 || month==9 || month==11  )
										maxday=30;
									if(month==1 || month==3 || month==5 || month==7 ||month==8 || month==10 || month==12)
										maxday=31;
									
									if(month==2 && ((Math.round(year/4)==year/4 && Math.round(year/100)!=year/100)
									||(Math.round(year/400)==year/400)))
										maxday=29;
									else if(month==2)
										maxday=28;

										temp = year+month+day+hour+minute;
									if (isNaN(temp)){//����Ϸ���У�飨����Ϊ���֣�
										object.select();
										alert("" + result[4] + "��������\n����Ϊ����!"); 
										result=false;
										break;
									}else  if (year<1800 || year>2500){//��У��
										object.select();
										alert("" + result[4] + "��������\n���� 1800 �� 2500 �����!");
										result=false;
										break;
										
									}else if (month<1 || month>12){//��У��
									
										object.select();
										alert("" + result[4] + "��������\n���� 01 �� 12 ���·�!");
										result=false;
										break;
									}else if (day<1 || day>maxday){//��У��
										object.select();
										alert("" + result[4] + "��������\n���� 01 �� " +maxday+ " ������!");
										result=false;
										break;
									}else if (hour<0 || hour>24){//СʱУ��
										object.select();
										alert("" + result[4] + "��������\n���� 01 �� 24 ��Сʱ!");
										result=false;
										break;
									}else if (minute<0 || minute>59){//����У��
										object.select();
										alert("" + result[4] + "��������\n���� 01 �� 59 �ķ���!");
										result=false;
										break;
									}
								}
						} 
						break;
					}

				default:
				{
					if(result[0] != "")
       					alert("δʵ�����ͣ�"+result[0]+"���ж�!");
					break;
				}
			}	
		}
		return result;	
		
	}
	/*
	����һ�������е�����checkֵ���õ���Ӧ�ı���ֵ 
	param checkValue ����check��ֵ
	return result[5] �����Ľ����5������
	       null ����ʧ��
	*/
	function analyse(checkValue) {
		var i=0;
		var type="";
		var long1="-1";
		var dotLong="0";
		var isNull="null";
		var message="";
		var flag="true";
		var error;
		var result=new Array(5);
		checkValue=checkValue.replace("(","[");
		checkValue=checkValue.replace(")","]");
		if(checkValue!="") {
			i=checkValue.indexOf("[");
			if(i==-1) {
				i=checkValue.indexOf(",");
				if(i==-1) {
					if(checkValue!="nn"&&checkValue!="not null") {
						type=checkValue;
					} else {
						isNull=checkValue;
					}
				} else {
					temp=checkValue.substring(0,i);
					checkValue=checkValue.substring(i+1);
					i=checkValue.indexOf(",");
					if(i==-1) {
						if(temp!="nn"&&temp!="not null") {
							type=temp;
						} else {
							isNull=temp;
						}
						if(checkValue!="nn"&&checkValue!="not null") {
							message=checkValue;
						} else {
							isNull=checkValue;
						}
					} else {
						type=temp;
						isNull=checkValue.substring(0,i);
						message=checkValue.substring(i+1);
					}
				}
			} else {
				type=checkValue.substring(0,i);
				checkValue=checkValue.substring(i+1);
				i=checkValue.indexOf("]");
				if(i==-1) {
					error="ȱ��������";
					flage="false";
				} else {
					temp=checkValue.substring(0,i);
					checkValue=checkValue.substring(i+1);
					i=temp.indexOf(",");
					if(i==-1) {
						dotLong = temp;
						long1="0";
					} else {
						long1=temp.substring(0,i);
						dotLong=temp.substring(i+1);
					}
					i=checkValue.indexOf(",");
					if(i!=-1) {
						checkValue=checkValue.substring(i+1);	
						i=checkValue.indexOf(",");
						if(i==-1) {
							if(checkValue!="nn"&&checkValue!="not null") {
								message=checkValue;
							} else {
								isNull=checkValue;
							}
						} else {
							isNull=checkValue.substring(0,i);
							message=checkValue.substring(i+1);
						}
					} 
				}
			}
			isNull = isNull.toLowerCase();
			temp=isNull;
			switch(temp) {
				case "null":isNull="null";break;
				case "nn":
				case "not null":isNull="not null";break;
				default:error="�ǿ����÷Ƿ�: "+isNull;flag="false";break;
			}
			if(dotLong==""||isNaN(dotLong)||dotLong.indexOf(".")!=-1) {
				error="number2�Ƿ�: "+dotLong;
				flag="false";
			}
			if(long1==""||isNaN(long1)||long1.indexOf(".")!=-1) {
				error="number1�Ƿ�: "+long1
				flag="false";
			}
			type=type.toLowerCase();
			temp=type;
			switch(temp){
				case "":type="";break;
				case "number":
				case "num":
				case "nu":
				case "n":type="number";break;
				case "char":
				case "ch":
				case "c":type="char";break;
				case "string":
				case "str":
				case "s":type="string";break;
				case "widestring":
				case "ws":type="widestring";break;
				case "date":
				case "d":type="date";break;
				case "time":
				case "t":type="time";break;
				case "minute":type="minute";break;
				case "datetime":break;
				case "phone":
				case "ph":
				case "p":type="phone";break;
				case "postcode":type="postcode";break;
				case "email":
				case "e":type="email";break;
				case "year":
				case "y":type="year";break;
				case "month":
				case "mon":
				case "m":type="month";break;
				case "month_new":
				case "mon_n":
				case "mn":type="month_new";break;
				default:error="typeName�Ƿ�: "+type;flag="false";break;
			}
		}
		
		if(parseInt(dotLong) < parseInt(long1)) {
			l = long1;
			long1 = dotLong;
			dotLong = l;
		}

		if(flag=="true") {
			result[0]=type;
			result[1]=long1;
			result[2]=dotLong;
			result[3]=isNull;
			result[4]=message;
			//alert(type+"\n"+long1+"\n"+dotLong+"\n"+isNull+"\n"+message);
			return result;	
		} else {
			alert(error+"\n"+"check ��ʽΪ:"+"\n"+"[typeName(number1[,nubmer2])],[not null],[message]");
			return;
		}
	}