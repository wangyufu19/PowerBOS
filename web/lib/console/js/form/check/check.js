	/*
	判断一个form内的输入项输入数据的合法性
	param formName form的名称
	return true 合法 false 不合法
	       
	------------------------------------------------------------------------------       
	check_f() 语法规则
	check中可以没有内容（如1所示），如果有值必须满足2的书写格式
	1、check=""	
	2、check="[typeName(number1[,nubmer2])],[not null],[message]"
	------------------------------------------------------------------------------
	typeName:（共8种形式）
		number		数字（整数，实数）
		char		英文字符
		string     中英文字符widestring
		email		E-mail
		phone		电话
		date		日期	（YYYY-MM-DD）
		year		年		（YYYY）
		month		年月	（YYYY-MM）
	    postcode    邮政编码
	
		
	非空属性设置:
		not null	
	
	为了方便编程提供一下几种缩写的方式：
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
	number1  number2 的说明
	typeName == number
		number1：实数的总长度（不包括小数点）
		number2：小数点后保留的位数（无输入时默认为0，表示输入数字应为整数）
	注：number1 number2 都无输入表示输入数字应为实数（此时check的值中没有括号）
		
	typeName == 其它
		number1：输入字符串的最大长度
		number2：输入字符串的最小长度（无输入时默认为0）
	
	
	-----------------------------------------------------------------------------
	只输入message认为是没有意义的，视为非法输入       
	*/
	/*
	判断一个form内的输入项输入数据的合法性
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
	判断一个对象的数据合法性
	param object 对象
	return true 合法 false 不合法
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
			            alert(result[4]+"错误\n长度应小于"+result[2]+"位!");
			            object.focus();
			            result=false;
			        }
		       }else if(object.value!=""){
							if(result[1] != result[2]){
		        		if(object.value.length-result[2]>0||object.value.length-result[1]<0) {
		        			alert(result[4]+"错误\n长度应小于"+result[2]+"位,大于"+result[1]+"位!");
		        			object.focus();
									result=false;
								}
		        	}else{
								if(object.value.length != result[1]) {
									alert(result[4]+"错误\n长度应等于"+result[1]+"位!");
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
							// Ascii码大于255是双字节的字符
							if(chrs.charCodeAt(i) > 255){
								alert(result[4]+"错误\n请输入英文数据!");
								object.focus();
								result=false;
								break;
							}
							/*var chr = chrs.charAt(i); // 对特殊字符不作限制，jbh
							if(!((chr>="A" && chr<="Z") || (chr>="a" && chr<="z") || (chr>="0" && chr<="9") || chr=='-' || chr=='_' || chr==' ' || chr=='.')){
								alert(result[4]+"错误\n不能输入特殊字符!");
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
							// Ascii码大于255是双字节的字符
							if(chrs.charCodeAt(i) < 255){
								alert(result[4]+"错误\n请输入中文数据!");
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
							// Ascii码大于255是双字节的字符
							if(chrs.charCodeAt(i) < 255){
								intLen += 1;
							}else{
								intLen += 2;
							}
					}
					if(result[1]=="0") {
						if(intLen-result[2]>0){
							alert(result[4]+"错误\n长度应小于汉字"+(result[2]/2)+"位或者英文字符"+result[2]+"位!");
							object.focus();
							result=false;
						}
				   }else if(object.value!=""){
								if(result[1] != result[2]){
							if(object.value.length-result[2]>0 || object.value.length-result[1]<0) {
								alert(result[4]+"错误\n长度应小于汉字"+(result[2]/2)+"位或者英文字符"+result[2]+"位,大于"+result[1]+"位!");
								object.focus();
										result=false;
									}
						}else{
									if(object.value.length != result[1]) {
										alert(result[4]+"错误\n长度应等于"+result[1]+"位!");
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
							alert(result[4]+"错误\n应是实数!");
							result=false;
							break;
						}
					} else if(result[1]=="0") {
						if(object.value.length>result[2]) {
							object.select();
							alert(result[4]+"错误\n长度应小于 "+result[2]+" 位!");
							result=false;
							break;
						}
						if((!isNumber(object))||(object.value.indexOf(".")!=-1)||(object.value.indexOf("-")!=-1)) {
							object.select();
							alert(result[4]+"错误\n应是正整数!");
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
							alert(result[4]+"错误\n应为整数!");
							result=false;
							break;
						} else if(temp<1800||temp>2500) {
							object.select();
							alert(result[4]+"错误\n输入 1800 至 2500 的年份!");
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
							alert(result[4]+"错误\n输入格式为:YYYY-MM");
							result=false;
							break;
						} else {
							var temp1=temp.substring(0,i);
							var temp2=temp.substring(i+1);
							if(isNaN(temp1)||temp1.indexOf(".")!=-1||isNaN(temp2)||temp2.indexOf(".")!=-1) {
								object.select();
								alert(result[4]+"错误\n应为整数!");
								result=false;
								break;
							} else if(temp1<1800||temp1>2500||temp2<1||temp2>12) {
								object.select();
								alert(result[4]+"错误\n输入 1800-01 至 2500-12 的年月!");
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
							alert(result[4]+"错误\n输入格式为:YYYYMM!");
							result=false;
							break;
						} else {
							var temp1=temp.substring(0,4);
							var temp2=temp.substring(4,6);
							if(isNaN(temp1)||temp1.indexOf(".")!=-1){
								object.select();
								alert(result[4]+"错误\n年份应为整数!");
								result=false;
								break;
							}if(isNaN(temp2)||temp2.indexOf(".")!=-1){
								object.select();
								alert(result[4]+"错误\n月份应为整数!");
								result=false;
								break;
							}else if(temp1<1800||temp1>2500){
								object.select();
								alert(result[4]+"错误\n输入 1800 至 2500 的年份!");
								result=false;
								break;
							}else if(temp2<1||temp2>12) {
								object.select();
								alert(result[4]+"错误\n输入 01 至 12 的月份!");
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
								//格式校验
								if (temp.length!=16){
									object.select();
									alert("" + result[4] + "\n请输入标准格式 例:YYYY-MM-DD HH:MI");
									result=false;
									break;
								}else if (!(temp.substring(4,5)=="-" && temp.substring(7,8)=="-" && temp.substring(10,11)==" " && temp.substring(13,14)==":")){
									object.select();
									alert("" + result[4] + "\n请输入标准格式 例：YYYY-MM-DD HH:MI");
									result=false;
									break;
								}else {
								//年、月、日、时、分校验
								var year = temp.substring(0,4);
								var month = temp.substring(5,7);
								
								var day = temp.substring(8,10);
								var hour = temp.substring(11,13);
								var minute = temp.substring(14,16);
									
									//确定当月最后一天
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
									if (isNaN(temp)){//输入合法性校验（必须为数字）
										object.select();
										alert("" + result[4] + "输入有误\n必须为数字!"); 
										result=false;
										break;
									}else  if (year<1800 || year>2500){//年校验
										object.select();
										alert("" + result[4] + "输入有误\n输入 1800 至 2500 的年份!");
										result=false;
										break;
										
									}else if (month<1 || month>12){//月校验
									
										object.select();
										alert("" + result[4] + "输入有误\n输入 01 至 12 的月份!");
										result=false;
										break;
									}else if (day<1 || day>maxday){//日校验
										object.select();
										alert("" + result[4] + "输入有误\n输入 01 至 " +maxday+ " 的日期!");
										result=false;
										break;
									}else if (hour<0 || hour>24){//小时校验
										object.select();
										alert("" + result[4] + "输入有误\n输入 01 至 24 的小时!");
										result=false;
										break;
									}else if (minute<0 || minute>59){//分钟校验
										object.select();
										alert("" + result[4] + "输入有误\n输入 01 至 59 的分钟!");
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
       					alert("未实现类型："+result[0]+"的判断!");
					break;
				}
			}	
		}
		return result;	
		
	}
	/*
	分析一个对象中的属性check值，得到相应的变量值 
	param checkValue 属性check的值
	return result[5] 分析的结果共5个变量
	       null 分析失败
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
					error="缺少右括号";
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
				default:error="非空设置非法: "+isNull;flag="false";break;
			}
			if(dotLong==""||isNaN(dotLong)||dotLong.indexOf(".")!=-1) {
				error="number2非法: "+dotLong;
				flag="false";
			}
			if(long1==""||isNaN(long1)||long1.indexOf(".")!=-1) {
				error="number1非法: "+long1
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
				default:error="typeName非法: "+type;flag="false";break;
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
			alert(error+"\n"+"check 格式为:"+"\n"+"[typeName(number1[,nubmer2])],[not null],[message]");
			return;
		}
	}