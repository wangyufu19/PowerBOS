/*******for the left menu***********/
function initMenu(){
	$("#leftSide .item2 .title a").click(function(){
		if($(this)[0].className == "iconUp"){
			$(this).removeClass("iconUp");
			$(this).addClass("iconDown");
		}else{
			$(this).removeClass("iconDown");
			$(this).addClass("iconUp");
		}
		$(this).parent().next().toggle();
	});
}

/*******for the welcome page************/
function initBox(){
	$("#menuBox .list dl").hide();
	$("#menuBox .list li a").click(function(){
		var obj = $(this).next();
		obj.toggle("normal");
	}) 
}
/*******for the list form order************/
function initOrder(path){
	$(".listform table .order").click(function(){
		var obj = $(this).find("img");
		var str = path + "up.gif";
		if(obj.attr("src") == str){
			obj.attr("src",path+"down.gif");
		}else{
			obj.attr("src",path+"up.gif");
		}
	})
}
/*******for the tab************/
function initTab(){
	$("#tabmenu li").click(function(){
		clearTab();
		$(this).addClass("select");
		var objId = $(this).attr("id");
		$("#"+objId+"_content").show();
	});
}
function clearTab(){
	$("#tabmenu li").removeClass("select");
	$(".tabContent .innerBox").hide();
}


/*********showHideLayer**********/
function showHideLayer(objId){
	$("#"+objId).toggle()
}