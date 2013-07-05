/*
 * Sinobpo Edit Grid JS 1.0
 * author:wangyf
 * email:youfu.wang@sinobpo.com.cn
 */
var pre=null;
var docFomate=null;
var docType=null;
var id=null;
var name=null;
var value=null;
var width=null;

$(function(){    
    $("#L0").click(function(e){    	
        stopEvent(e);		
        var cur = e.target;
        if ($(cur).is("td")){        	
            var isEditor=$(cur).attr("isEditor");
            if(isEditor=="true"){
            	docFomate=null;
                getGridEditor(cur);
            }
        }
    });    
   
    $("#L0").find("tr").each(function() {
        $(this).find("td").each(function(i) {
            $(this).data("i", i);
        });
    });        
});
function stopEvent(event){
    event.stopPropagation();
}
function blurEvent(obj) {
    if (!obj){ 
    	return;
    }
    docFomate=null;
    id=$(obj).attr("id");
    name=$(obj).attr("name");
    if(docType=="text"){
       value=$(obj).attr("value");
       docFomate="<input id=\'"+id+"\' name=\'"+name+"\' type='hidden' value=\'"+value+"\'/>"+value;
    }else if(docType=="select"){       
       if(obj.selectedIndex!=-1){
          value=obj.options[obj.selectedIndex].value;      
          docFomate="<input id=\'"+id+"\' name=\'"+name+"\' type='hidden' value=\'"+value+"\'/>"+obj.options[obj.selectedIndex].text;
       }
    }else{
       value=$(obj).attr("value");
       docFomate="<input id=\'"+id+"\' name=\'"+name+"\' type='hidden' value=\'"+value+"\'/>"+value;
    }         
    $(obj).parent().html(docFomate);  
    pre = null;
}
function getGridEditor(obj){
    if(pre){
   		blurEvent(pre);
   	}
    docType=$(obj).attr("docType");
    id=$(obj).find('input').attr("id");
    name=$(obj).find('input').attr("name");
    value=$.trim($(obj).text());
    width=$(obj).width()-5;     
    if(docType=="text"){
       getTextEditor(obj);
    }else if(docType=="select"){
       getSelectEditor(obj);
    }else{
       getTextEditor(obj);
    }
}
function getTextEditor(obj){
    docFomate="<input id=\'"+id+"\' name=\'"+name+"\' type='text' value=\'"+value+"\'/>";
    $(obj).html(docFomate);
    pre=$(obj).find('input').css("border", "1px solid red");
    $(obj).find('input').width(width);  
    $(obj).find('input').css("background-color","#0FFFFF");    
    $(obj).find('input').trigger("focus").trigger("select");
    $(obj).find('input').click(function(event){
        stopEvent(event);
        return false;
    }).blur(function(event){
        stopEvent(event);            
        blurEvent(this);
    });
}
function getSelectEditor(obj){       
    var key=$(obj).attr("key");    
    var xmlResource=$("#xmlResource").val();   
    $.ajax({
		type:"post",
		url:contextPath+"/jsp/support/loadSelectEditor.action", 	
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		data:{xmlResource:xmlResource,key:key,value:value},	
		success:function(data){ 													   
		    $(obj).html("<select id=\'"+id+"\' name=\'"+name+"\'>"+data+"</select>");	
		    pre=$(obj).find('select').css("border", "1px solid red");  	 
		    $(obj).find('select').css("background-color","#0FFFFF");   
		    $(obj).find('select').css("width","152px");      
		    $(obj).find('select').trigger("focus").trigger("select");
		    $(obj).find('select').click(function(event){
		        stopEvent(event);
		        return false;
		    }).blur(function(event){
		        stopEvent(event);            
		        blurEvent(this);
		    });  
		}
	});	    
}
    