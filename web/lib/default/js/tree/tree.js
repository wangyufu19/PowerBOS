var lastObj = null;

function initFolderTree(){
	var i;
	var sidebar	= document.getElementById("sidebar");
	var uls = sidebar.getElementsByTagName("ul");
	var tree	= uls[0];
	if(!tree) return;
	var uls		= tree.getElementsByTagName("ul");
	if(!uls) return;
	for(i=0;i<uls.length;i++){
			uls[i].style.display = "none";
	}
	var lis		= tree.getElementsByTagName("li");
	if(!lis) return;
	for(i=0;i<lis.length;i++){
		if(lis[i].className == "Open"){
			var openNode	= lis[i];
			lastObj = openNode.parentNode.parentNode;
			lastObj.className = "FolderOpen";
			for(;openNode.parentNode != tree;openNode=openNode.parentNode){
				if(openNode.tagName =="ul" || openNode.tagName =="UL"){
					openNode.style.display = "";
				}
			}
			break;
		}
	}
	for(i=0;i<lis.length;i++){
		setNodeStyle(lis[i]);
	}
	for(i=0;i<tree.childNodes.length;i++){
		if(tree.childNodes[i].tagName =="li" || tree.childNodes[i].tagName =="LI"){
			tree.childNodes[i].onclick = openCloseFolder;
			for(j=0;j<tree.childNodes[i].childNodes.length;j++){
				if(tree.childNodes[i].childNodes[j].tagName =="ul" || tree.childNodes[i].childNodes[j].tagName =="UL"){
					tree.childNodes[i].childNodes[j].style.display = "";
				}		
			}
		}
	}
}

function hasChildNode(node){
	var x	= node.childNodes;
	for(var i=0;i<x.length;i++){
		if(x[i].tagName == "ul" || x[i].tagName == "UL"){
			return true;
		}
	}
	return false;
}

function setNodeStyle(node){
	if(hasChildNode(node) && node.className != "Folder" && node.className != "FolderOpen"){
		node.className = "FolderClose";
	}
}

function openCloseFolder(evnt){
	if(getTriggerNode(evnt).tagName == "a" || getTriggerNode(evnt).tagname == "A" || getTriggerNode(evnt).tagName == "span" || getTriggerNode(evnt).tagname == "SPAN" || getTriggerNode(evnt).className == "Folder"){
		return true;
	}
	if(doOpenCloseFolder(getTriggerNode(evnt))){
		if(lastObj && lastObj != getTriggerNode(evnt) && isFolderOpen(getChildFolder(lastObj))){
			doOpenCloseFolder(lastObj);
		}
		lastObj = getTriggerNode(evnt);
	}
}

function doOpenCloseFolder(x){
	x = getChildFolder(x);
	if(!x)return false;
	if(isFolderOpen(x)){
		closeFolder(x);
		return true;
	}else{
		openFolder(x);
		return true;
	}
	return false;
}

function getChildFolder(x){
	if(x.tagName == "span" || x.tagName == "SPAN"){
		x = x.parentNode.parentNode;
	}
	x	= x.childNodes;
	for(var i=0;i<x.length;i++){
		if(x[i].tagName == "ul" || x[i].tagName == "UL"){
			return x[i];
		}
	}
	return false;
}

function isFolderOpen(x){
	if(x.style.display == "none"){
		return false;
	}else{
		return true;
	}	
}

function closeFolder(x){
	x.style.display = "none";
	x.parentNode.className = "FolderClose";
}

function openFolder(x){
	x.style.display = "";
	x.parentNode.className = "FolderOpen";
}

function getTriggerNode(e) {
	var obj;
	if (isIE()) {
		obj = event.srcElement;
	}else {
		obj = e.target;
	}
	return obj;
}

function isIE(){
	if(document.all){
		return true;
	}else{
		return false;
	}
}