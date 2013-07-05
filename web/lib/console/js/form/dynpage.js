/**
* 功能说明:用于翻页事件
*/

function firstClick(form,cpage,mpage,ob){
  if(cpage==1||cpage==0) return;
  ob.value = 1;
  form.submit();
}


function previousClick(form,cpage,mpage,ob){
  if(cpage==1||cpage==0) return;
  else ob.value = cpage - 1;
  form.submit();
}

function nextClick(form,cpage,mpage,ob){
	var tempv = parseInt(mpage);
	if(mpage==1||mpage==0) return;
	if(cpage==mpage) return;
	else ob.value = cpage - (-1); 
	form.submit();
}

function endClick(form,cpage,mpage,ob){
	var tempv = parseInt(mpage);
	if(mpage==1||mpage==0) return;
	if(cpage==mpage) return;
	else ob.value = mpage;
	form.submit();
}

function toClick(form,tpage,mpage,ob){
  if(tpage.value=="") return;
  ob.value = ""+tpage.value;  
  if(mpage==1||tpage.value==0) return;
  if(tpage.value>mpage) return;
  form.submit();
}
    
function query_f() {	
	var query=document.query;
	form.currentPage.value = document.to.currentPage.value;
	query.submit();
}