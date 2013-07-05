/**********************************************************************************************************
 * 说明：

   1. 编写请求初始化函数，把请求条件数据封装入 XMLHTTP 对象， 函数名固定为 ：  function initXMLRequest_f(oXMLHttp)
	@param	oXMLHttp      Microsoft.XMLHTTP   对象实例 

	例如：
	function initXMLRequest_f(oXMLHttp){
		oXMLHttp.open("POST","/saige",true);
		oXMLHttp.setRequestHeader("year",myform.year.value); 
		oXMLHttp.setRequestHeader("month",myform.month.value); 
		oXMLHttp.setRequestHeader("classname","com.saige.common.chart.ChartCtrl");
		oXMLHttp.setRequestHeader("method","queryChart");
		oXMLHttp.setRequestHeader("handler",myform.handler.value);
	}

    2. 编写获得服务器端输出后加载XML数据函数， 函数名固定为 :  function loadXMLDoc_f(oXMLDoc)
	@param  oXMLDoc	       Microsoft.XMLDOM   对象实例

	例如：
	function loadXMLDoc_f(oXMLDoc){
		var root;  
		var tmpValue;

		if(oXMLDoc.documentElement){
			root = oXMLDoc.documentElement;
			//chartCtrl.style.height = (root.firstChild.childNodes.length-3) * 350;
		
			//myform.currentPage.value = root.getAttribute("currentPage")?root.getAttribute("currentPage"):"0";
			//myform.maxPage.value = root.getAttribute("maxPage")?root.getAttribute("maxPage"):"0";
			//document.all["currentPageLab"].innerHTML = myform.currentPage.value;
			//document.all["maxPageLab"].innerHTML = myform.maxPage.value;
		}
		objChart.XMLData = oXMLDoc.transformNode(objChartXSL.XMLDocument);
	}

    3. 在提交查询时调用 requestXMLDoc_f() 函数即可。


***********************************************************************************************************/

var objXMLHttp;
var bXMLHttpLoad = false;

/**
 * 发送请求到服务器，请求 XML Document 数据。
 */
function requestXMLDoc_f(){
	
	// 处理同步
	if (bXMLHttpLoad) return;
	bXMLHttpLoad = true;
	
	objXMLHttp = new ActiveXObject("Microsoft.XMLHTTP");
	// 设置回调函数
	objXMLHttp.onreadystatechange = backCall_f;
	try{
		// 初始化请求头
		
		if(typeof(initXMLRequest_f)){
			initXMLRequest_f(objXMLHttp);
		}
	}catch(ex){
		alert("初始化请求条件数据失败！请检查数据格式...");
		bXMLHttpLoad = false;
		return;
	}
	// 发送请求
	objXMLHttp.send(); 
}


/**
 * XMLHTTP 对象的回调函数。
 * 当客户端获得服务器端的响应数据时，此函数开始运行。
 */
function backCall_f(){   
	var iReadyState;
	var objXMLDoc;
	try{
		iReadyState = objXMLHttp.readyState;
	}catch(e){
		return;
	}
	if (iReadyState != 4){
		return;
	}
	objXMLDoc = objXMLHttp.responseXML;
	if(objXMLDoc.documentElement == null){
		alert("提取数据失败！请检查网络连接...");
		bXMLHttpLoad = false;
		return;
	}
	try{
		if(typeof(loadXMLDoc_f)){
			loadXMLDoc_f(objXMLDoc);
		}
	}catch(e){
		alert("加载数据失败！请检查加载数据函数是否有错...");
	}
	bXMLHttpLoad = false;
}

/**
* 翻页到首页
* 参数为提交请求翻页的表单对象form,以及表单中的三个对象：
* 当前页currentpage对象,最大页maxpage对象
*/

function firstClick(form,currentpage,maxpage){
	var page = currentpage.value;
	if(page==1||page==0) return;
	currentpage.value = 1;
	requestXMLDoc_f();
}


/**
* 翻页到前一页
* 参数为提交请求翻页的表单对象form,以及表单中的三个对象:
* 当前页currentpage对象,最大页maxpage对象
*/

function previousClick(form,currentpage,maxpage){
	var page = currentpage.value;
	if(page==1||page==0) return;
	else currentpage.value = page - 1;
	requestXMLDoc_f();
}



/**
* 翻页到后一页
* 参数为提交请求翻页的表单对象form,以及表单中的三个对象:
* 当前页currentpage对象,最大页maxpage对象
*/

function nextClick(form,currentpage,maxpage){
	var page = currentpage.value;
	var mpage = maxpage.value;
	if(page==mpage) return;
	else currentpage.value = page - (-1);
	requestXMLDoc_f();
}



/**
* 翻页到最后一页
* 参数为提交请求翻页的表单对象form,以及表单中的三个对象:
* 当前页currentpage对象,最大页maxpage对象
*/

function endClick(form,currentpage,maxpage){
	var page = currentpage.value;
	var mpage = maxpage.value;
	if(page==mpage) return;
	else currentpage.value = mpage;
	requestXMLDoc_f();
}

/**
 *检查记录是否已经存在于数据库中
 *
 *
 *For example:
 *	requestPage = "/haitai?classname=bms.customer.CustomerCtrl&method=checkRecord&code="+code+"&name="+name;
 *
 *
 */
 // *注意该方法只有在后台传送回来的数据为：<root>success</root>或者是：<root>failure</root>时候,可以使用下面的方法

 function checkRecord(requestPage,alertmessage)
{
	var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");//定义http请求对象
	var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
	
	xmlHttp.open("POST",requestPage,false);
	xmlHttp.send("");
	if(xmlHttp.readyState==4)
	{
		xmlDoc.loadXML(bytes2BSTR(xmlHttp.responseBody));
		xmlDoc.abort();
		xmlHttp.abort();
		var msg = xmlDoc.xml;
		if((msg.substring(6,msg.lastIndexOf("<"))) =="success")
		{
			return true;//如果记录没有存在,则返回true
		}else
		{
			alert(alertmessage);
			return false;//如果记录已经存在,返回false
		}
	}
}

/*--------------------------------------------------------------------------------------------------------*/