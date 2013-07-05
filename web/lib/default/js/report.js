function excel_export(id,name,expF){
	if(typeof(expF)=="undefined"){
		expF=document.expF;
	}
	if(typeof(name)=="undefined"){
		name="导出";
	}
	var obj=document.getElementById(id);
	expF.inHtml.value=obj.outerHTML;
	expF.type.value="xls";
	expF.reportName.value=name;
	expF.submit();
}


function word_export(id,name,expF){
	if(typeof(expF)=="undefined"){
		expF=document.expF;
	}
	if(typeof(name)=="undefined"){
		name="导出";
	}
	var obj=document.getElementById(id);
	expF.inHtml.value=obj.outerHTML;
	expF.reportName.value=name;
	expF.type.value="doc";
	expF.submit();
}


function pdf_export(id,name,expF){
	if(typeof(expF)=="undefined"){
		expF=document.expF;
	}
	if(typeof(name)=="undefined"){
		name="导出";
	}
	var obj=document.getElementById(id);
	expF.inHtml.value=obj.outerHTML;
	expF.reportName.value=name;
	expF.type.value="pdf";
	expF.submit();
}