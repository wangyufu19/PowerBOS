function keydown(obj){
   key=event.keyCode.toString()
   if(obj.dataType=="date") dateinput(obj)
	else  if(obj.dataType=="float") floatinput(obj)
   	else  if(obj.dataType=="int") intinput(obj)
    var oldinputvalue=obj.value

}

 function keyup(obj){
 	if(obj.dataType=="date") datakeyup(obj)
	else  if(obj.dataType=="float") floatkeyup(obj)
   	else  if(obj.dataType=="int") intkeyup(obj)
}
function dateinput(obj){
	key=event.keyCode.toString()
	var strkey="9,37,39,46,48,49,50,51,52,53,54,55,56,57,96,97,98,99,100,101,102,103,104,105"
	if(strkey.indexOf(key)==-1)//在允许符号内
		event.returnValue=false;
	else{
		var str=obj.value
		if(str.length==10)		oldinputvalue=str
		var strsub=str.split("-")
		if((key=="96"||key=="48")&&str.length>2){
		  if(strsub[0].length==4&&parseInt(strsub[0].substr(0,2))==0) event.returnValue=false;

		}
	}

}
 function inputblur(obj){
    if(obj.dataType=="date"&&obj.value.length!=10&&obj.value.length!=0){
		alert("日期不合法,正确格式yyyy-MM-dd")
        try{
   		  obj.focus()
        }catch(e){
 	    }
        obj.vlid=false
	}
	else if(obj.dataType=="float"&&obj.value!=""){//保证小数位数

		var ls

		if(obj.value.indexOf(".")==-1){
			ls=obj.value+"."
			for(var i=0;i<parseInt(obj.npoint);i++) ls+="0"
		}
		else {
			ls=obj.value
			if(obj.value.indexOf(".")==(obj.value.indexOf("-")+1)&&(obj.value.indexOf("-")!=-1))//-.00
				ls="-0"+ls.substr(obj.value.indexOf("."))
			for(var i=obj.value.length;i<(obj.value.indexOf(".")+parseInt(obj.npoint)+1);i++) ls+="0"
		}
		obj.value=ls

	}
    if(typeof(myBlur)!="undefined"){
		obj.vlid= myBlur(obj)

	}

	event.returnValue=false

}
function datakeyup(obj){

	var format=new Array(1999,15,25)
	var str=obj.value
	var strsub=str.split("-")

	if(strsub.length<=1){
	    var c=strsub[0]
		if(c.length==1&&(c<"1"||c>"3"))  obj.value=""//首位年数为0-1-2
		else if(c.length==2&&(c>"30"||c=="00"))  obj.value=c.substr(c.length-1)
		else if(c.length==3&&(c>"300"||c=="000"))  obj.value=c.substr(c.length-1)
		else if(c.length==4&&(c>"3000"||c=="0000"))  obj.value=c.substr(c.length-1)
		else if(c.length>4) obj.value=c.substr(0,4)
		else if(c.length==4&&c!="0000"&&c<"3000"){
			obj.value=str.substr(0,4)+"-"
		}

	}
	else if(strsub.length==2){
	    var c=strsub[1]
	    if(strsub[0].length==4&&strsub[0]=="0000")  obj.value="2000"+"-"+strsub[1]+"-"+strsub[2]
		else if(strsub[0].length>4){////检验年份,年份没改变时检验月份
			 c=strsub[0]
			 if(c<"3000"&&c>"0000") c=c.substr(0,4)
			 else if(c>"3000") c=c.substr(1,4)
			 else if(c=="0000") c="2000"////
		    obj.value=c+"-"+strsub[1]
        }
		else if(strsub[0].length<4){
		     c=strsub[0]
			 if(c.length==0) c="2000"
			 else if (c.length==1)c=c+"000"
			 else if (c.length==2)c=c+"00"
			 else if (c.length==3)c=c+"0"
			obj.value=c+"-"+strsub[1]
		}
	    else if(c.length==1&&c>"1" ){
			obj.value=str.substr(0,str.length-1)
		}
		else if(c.length==2&&(c=="00"||c.substr(0,2)>"12")){
			obj.value=str.substr(0,str.length-1)
		}
        else if(c.length>2)
			obj.value=strsub[0]+"-"+c.substr(0,2)
		else if(c.length==2&&c<"13"){
			obj.value=str+"-"
		}

	}
	else if(strsub.length==3){
	    if(strsub[0].length==4&&strsub[0]=="0000")  obj.value="2000"+"-"+strsub[1]+"-"+strsub[2]
		else if(strsub[1].length==2&&strsub[1]=="00")  obj.value=strsub[0]+"-"+"12"+"-"+strsub[2]
		else if(strsub[0].length>4){//检验年份,年份没改变时检验月份
		     var c=strsub[0]
			 if(c<"3000"&&c>"0000") c=c.substr(0,4)
			 else if(c>"3000") c=c.substr(1,4)
			 else if(c=="0000") c="2000"////
		     obj.value=c+"-"+strsub[1]+"-"+strsub[2]

		}
		else if(strsub[0].length<4){
		     c=strsub[0]
			 if(c.length==0) c="2000"
			 else if (c.length==1)c=c+"000"
			 else if (c.length==2)c=c+"00"
			 else if (c.length==3)c=c+"0"
			obj.value=c+"-"+strsub[1]+"-"+strsub[2]
		}
		else if(strsub[1].length>2){//检验月份,在月份上按了数字
		        var c=strsub[1]
                if(c.substr(0,2)=="00") c=c.substr(1,2)
                else if(c.substr(0,2)<"13"&&c.substr(0,2)>"00") c=c.substr(0,2)
				else if(c.substr(0,1)>"1") c=c.substr(1,2)
				else if(c.substr(1,1)>"2") c=c.substr(0,1)+c.substr(2,1)
				obj.value=strsub[0]+"-"+c+"-"+strsub[2]

		}
		else if(strsub[1].length<2){//检验月份,在月份上按了数字
		        var c=strsub[1]
                if(c.length==0) c="12"
				else if(c.length==1&&c<"2") c=c+"0"
				else c="12"
				obj.value=strsub[0]+"-"+c+"-"+strsub[2]

		}

		else  if(strsub[2].length==1&&strsub[2]>"3"){//日期首位>3
			obj.value=str.substr(0,str.length-1)
		}
		else if(strsub[2].length==2&&strsub[2]=="00"){//日期>31
			obj.value=str.substr(0,str.length-2)+"01"
		}
		else if(strsub[2].length==2&&strsub[2]>"31"){//日期>31
			obj.value=str.substr(0,str.length-1)
		}
		else if(strsub[2].length==2&&strsub[2]>"29"&&strsub[1]=="02"){//修改2004/02/13
		      obj.value=str.substr(0,str.length-1)
		}                                                             //yjy
		else if(strsub[2].length>2&&oldinputvalue!=str){//按了数字键
		    var c= strsub[2]
			if(c.substr(0,2)=="00")  c=c.substr(2,2)
			else if(c.substr(0,2)<"32"&&c.substr(0,2)>"00")  c=c.substr(0,2)
			else if(c.substr(0,1)>"3") c=c.substr(1,2)
			else if(c.substr(1,1)>"1") c=c.substr(0,1)+c.substr(2,1)
			obj.value=strsub[0]+"-"+strsub[1]+"-"+c

		}

	}

}
function intinput(obj){
	var str=obj.value
	key=event.keyCode.toString()
	var strkey="8,9,37,39,46,48,49,50,51,52,53,54,55,56,57,96,97,98,99,100,101,102,103,104,105"

	if(parseInt(obj.vmin)!=0) strkey+=",109,189"

	if(key=="89" || strkey.indexOf(key)==-1) {//在允许符号内
		event.returnValue=false;
	}
	else if(key=="109"&&obj.value.indexOf("-")>-1){//不重复-号,如重复移去-号
	    obj.value=str.substr(1)
		event.returnValue=false;
	}
    else if(key=="109"||key=="189"){//-号处理,加在最前面
	    obj.value="-"+str
		event.returnValue=false;
	}
	else if(str.indexOf("-")>0){//-号处理,不能在中间,如在中间则移去
	    obj.value=str.substr(1)
		event.returnValue=false;
	}
}
function intkeyup(obj){

}
function floatkeyup(obj){//处理小数点后多余指定位数的情况
	var str=obj.value

    if(typeof(obj.npoint)=="undefined"||isNaN(parseInt(obj.npoint))) obj.npoint=2
    if(str.indexOf(".")>-1&&(str.indexOf(".")+parseInt(obj.npoint)+1)<str.length){
		obj.value=str.substr(0,str.length-1)
	}
}

function floatinput(obj){
	var str=obj.value
	key=event.keyCode.toString()
	var strkey="8,9,37,39,46,48,49,50,51,52,53,54,55,56,57,96,97,98,99,100,101,102,103,104,105,110,190"
	if(parseInt(obj.vmin)!=0) strkey+=",109,189"

	if(strkey.indexOf(key)==-1)//在允许符号内
		event.returnValue=false;
    else if((key=="110" || key=="190")&&obj.value.indexOf(".")>-1) //不重复.号
		event.returnValue=false;
	else if((key=="109" || key=="189")&&obj.value.indexOf("-")>-1){//不重复-号,如重复移去-号
	    obj.value=str.substr(1)
		event.returnValue=false;
	}
    else if(key=="109" || key=="189"){//-号处理,加在最前面
	    obj.value="-"+str
		event.returnValue=false;
	}
	else if(str.indexOf("-")>0){//-号处理,不能在中间,如在中间则移去
	    obj.value=str.substr(1)
		event.returnValue=false;
	}
} 