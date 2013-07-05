/* add by zonghua,zhang at 20061009*/
function XMLRPCProvider () {	
  var status = null;
  var param  = null;
  var url    = null;	
  var req    = null;
  var msgCount   = 0;	
  var inProgress = false;
  var isComplete = false;		
  var oThis = this;	
  var internalCanMsg = function(){
 	msgCount++;
	return msgCount < 100;
  }
  var internalOnLog = function(msg){
  	if(oThis.onLog && internalCanMsg()) {
		oThis.onLog(msg);
	}
  }	
  var internalOnError = function(msg){
 	if(oThis.onError && internalCanMsg()) {
      oThis.onError(msg);
	 }
  }	
  var internalIsBusy = function(){
	return inProgress && !isComplete;
  }	
  var internalRequestComplete = function() {				
	var STATE_COMPLETED = 4;
	var STATUS_200 = 200;
	if (req.readyState == STATE_COMPLETED) {
		status = req.status;
		inProgress = false;
		isComplete = true;			
		if (status == STATUS_200) {
			if(oThis.onComplete) {
				oThis.onComplete(req.responseText, req.responseXML);
				req.onreadystatechange = function() {};	
				//oThis.clearCookie();
			}				 
		}
	} 
  }	
  this.isBusy = function(){
	return internalIsBusy();
  }		
  this.submit = function(_url){	
	msgCount = 0;
	url = _url;	
	status = null;
	inProgress = true;
	isComplete = false;
    if (window.ActiveXObject) {	    		
	    req = new ActiveXObject("Microsoft.XMLHTTP");
	    if (req){
	      req.onreadystatechange = internalRequestComplete;
	      req.open("get", url, true);
		  req.setrequestheader("Pragma","no-cache");
	   	  req.setrequestheader("Cache-control","no-cache");
		  req.setrequestheader("Content-Type","text/html;charset=UTF-8");
		  //req.setRequestHeader("If-Modified-Since","0"); 
	      req.send(oThis.getParam());
		  
	   }
	} 
 }
	
 this.abort = function(){
   onComplete = null;		
   req.abort();
  }	
 this.getUrl = function(){
	return url;
 }
 this.setParam = function(_param){
   param = _param
 }
 this.getParam = function(){
   return param;
 }
 this.getStatus = function(){
   return status;
 }	
 this.onError = function(msg){
 } 	
 this.onLog = function(msg) {
 }
 /* 需要实现 */
 this.onComplete = function(responseText, responseXML){
 }
this.clearCookie = function(){
  var aCookie = document.cookie.split("; ");
    for (var i=0; i < aCookie.length; i++){
      var aCrumb = aCookie[i].split("=");
	  this.delCookie(aCrumb[0]);
    }
  }
 
 this.delCookie = function (sName){
    var date = new Date();
    document.cookie = sName + "= ; expires=" + date.toGMTString();
 }
}
