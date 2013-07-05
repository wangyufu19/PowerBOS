/**********************************************************************************************************
 * ˵����

   1. ��д�����ʼ���������������������ݷ�װ�� XMLHTTP ���� �������̶�Ϊ ��  function initXMLRequest_f(oXMLHttp)
	@param	oXMLHttp      Microsoft.XMLHTTP   ����ʵ�� 

	���磺
	function initXMLRequest_f(oXMLHttp){
		oXMLHttp.open("POST","/saige",true);
		oXMLHttp.setRequestHeader("year",myform.year.value); 
		oXMLHttp.setRequestHeader("month",myform.month.value); 
		oXMLHttp.setRequestHeader("classname","com.saige.common.chart.ChartCtrl");
		oXMLHttp.setRequestHeader("method","queryChart");
		oXMLHttp.setRequestHeader("handler",myform.handler.value);
	}

    2. ��д��÷���������������XML���ݺ����� �������̶�Ϊ :  function loadXMLDoc_f(oXMLDoc)
	@param  oXMLDoc	       Microsoft.XMLDOM   ����ʵ��

	���磺
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

    3. ���ύ��ѯʱ���� requestXMLDoc_f() �������ɡ�


***********************************************************************************************************/

var objXMLHttp;
var bXMLHttpLoad = false;

/**
 * �������󵽷����������� XML Document ���ݡ�
 */
function requestXMLDoc_f(){
	
	// ����ͬ��
	if (bXMLHttpLoad) return;
	bXMLHttpLoad = true;
	
	objXMLHttp = new ActiveXObject("Microsoft.XMLHTTP");
	// ���ûص�����
	objXMLHttp.onreadystatechange = backCall_f;
	try{
		// ��ʼ������ͷ
		
		if(typeof(initXMLRequest_f)){
			initXMLRequest_f(objXMLHttp);
		}
	}catch(ex){
		alert("��ʼ��������������ʧ�ܣ��������ݸ�ʽ...");
		bXMLHttpLoad = false;
		return;
	}
	// ��������
	objXMLHttp.send(); 
}


/**
 * XMLHTTP ����Ļص�������
 * ���ͻ��˻�÷������˵���Ӧ����ʱ���˺�����ʼ���С�
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
		alert("��ȡ����ʧ�ܣ�������������...");
		bXMLHttpLoad = false;
		return;
	}
	try{
		if(typeof(loadXMLDoc_f)){
			loadXMLDoc_f(objXMLDoc);
		}
	}catch(e){
		alert("��������ʧ�ܣ�����������ݺ����Ƿ��д�...");
	}
	bXMLHttpLoad = false;
}

/**
* ��ҳ����ҳ
* ����Ϊ�ύ����ҳ�ı�����form,�Լ����е���������
* ��ǰҳcurrentpage����,���ҳmaxpage����
*/

function firstClick(form,currentpage,maxpage){
	var page = currentpage.value;
	if(page==1||page==0) return;
	currentpage.value = 1;
	requestXMLDoc_f();
}


/**
* ��ҳ��ǰһҳ
* ����Ϊ�ύ����ҳ�ı�����form,�Լ����е���������:
* ��ǰҳcurrentpage����,���ҳmaxpage����
*/

function previousClick(form,currentpage,maxpage){
	var page = currentpage.value;
	if(page==1||page==0) return;
	else currentpage.value = page - 1;
	requestXMLDoc_f();
}



/**
* ��ҳ����һҳ
* ����Ϊ�ύ����ҳ�ı�����form,�Լ����е���������:
* ��ǰҳcurrentpage����,���ҳmaxpage����
*/

function nextClick(form,currentpage,maxpage){
	var page = currentpage.value;
	var mpage = maxpage.value;
	if(page==mpage) return;
	else currentpage.value = page - (-1);
	requestXMLDoc_f();
}



/**
* ��ҳ�����һҳ
* ����Ϊ�ύ����ҳ�ı�����form,�Լ����е���������:
* ��ǰҳcurrentpage����,���ҳmaxpage����
*/

function endClick(form,currentpage,maxpage){
	var page = currentpage.value;
	var mpage = maxpage.value;
	if(page==mpage) return;
	else currentpage.value = mpage;
	requestXMLDoc_f();
}

/**
 *����¼�Ƿ��Ѿ����������ݿ���
 *
 *
 *For example:
 *	requestPage = "/haitai?classname=bms.customer.CustomerCtrl&method=checkRecord&code="+code+"&name="+name;
 *
 *
 */
 // *ע��÷���ֻ���ں�̨���ͻ���������Ϊ��<root>success</root>�����ǣ�<root>failure</root>ʱ��,����ʹ������ķ���

 function checkRecord(requestPage,alertmessage)
{
	var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");//����http�������
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
			return true;//�����¼û�д���,�򷵻�true
		}else
		{
			alert(alertmessage);
			return false;//�����¼�Ѿ�����,����false
		}
	}
}

/*--------------------------------------------------------------------------------------------------------*/