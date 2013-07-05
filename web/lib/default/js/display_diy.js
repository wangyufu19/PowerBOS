YAHOO.namespace("ECIM.container");
YAHOO.util.Event.addListener(window, "load", init);

function init() {
	YAHOO.ECIM.container.myPanel = new YAHOO.widget.Panel("diyDisplay", { width:"250px", fixedcenter:true, visible:false, constraintoviewport:true } );
	YAHOO.ECIM.container.myPanel.render();	
}

dealGetAllColumns = function(reply){
	var result = reply.getResult();
	var msg= result.ajaxReturnMsg;	
	var str=result.ajaxReturnDiyDisplay;	
	displayContent.innerHTML=str;
	YAHOO.ECIM.container.myPanel.show();
}

dealDiyColumn = function(reply){	
	var result = reply.getResult();
	var msg= result.ajaxReturnMsg;		
	if(msg="成功"){
	  alert(msg);	 
	  document.DIY_DISPLAY_FORM.tmpCode.value="";
	  document.DIY_DISPLAY_FORM.diySort.value="";
	  YAHOO.ECIM.container.myPanel.hide();	
	  var form=result.ajaxReturnCode+"_FORM";	 
	  document.forms[form].submit();	  
	}
	if(msg=="�ɹ�"){
		document.DIY_DISPLAY_FORM.tmpCode.value="";
		document.DIY_DISPLAY_FORM.diySort.value="";
		YAHOO.ECIM.container.myPanel.hide();
		search_f();
	}
}

function saveDiyColumn_f(pageCode){   
	var box=document.DIY_DISPLAY_FORM.colId;	
	var diySort=document.DIY_DISPLAY_FORM.diySort.value;
	var colStr="";	
	for(i=0;i<box.length;i++){	 
		if(box[i].checked){		  
			colStr+=box[i].value+",";		
		}				
	}			
	if(diySort=="diyDisplay"){	   
		buffalo.remoteCall("diyDisplayAjax.saveDiyColumn", [pageCode,colStr],dealDiyColumn);
	}
	if(diySort=="diySort"){		
		buffalo.remoteCall("diySortAjax.saveSortColumn", [pageCode,colStr],dealDiyColumn);
	}
	
}
function showDiyColumn_f(pageCode,code){
 	document.DIY_DISPLAY_FORM.tmpCode.value=code;
 	document.DIY_DISPLAY_FORM.diySort.value="diyDisplay";
	buffalo.remoteCall("diyDisplayAjax.getDiyColumn", [pageCode,code], dealGetAllColumns);
}
function showSortColumn_f(pageCode,code){
 	document.DIY_DISPLAY_FORM.tmpCode.value=code;
 	document.DIY_DISPLAY_FORM.diySort.value="diySort";
	buffalo.remoteCall("diySortAjax.getSortColumn", [pageCode,code], dealGetAllColumns);
}
function closeDiyColumn_f(){
 YAHOO.ECIM.container.myPanel.hide();
}