	/**************************************
	 * ��̬ҳ�溯��
	 * author      :wangyf
	 * create-date :2011-12-01
	 *************************************/ 
	/*
	* ִ�б��¼�
	* @param form ������
	* @param action �¼�����
	* @param msg �¼�������Ϣ
	* @param objValue checkbox��radio����ֵ
	* @param objName checkbox��radio����
	* @return
	*/
	function doWith_f(form,action,msg,objValue,objName){
		if(action!="delete" && action!="deleteOne"&&action!="back"){
			if(!check_f(form)){
			 	return;
			} 
		}else{
			if(typeof(objName)!="undefined" && objName!=""){
				if(!isChecked(objName)){
					alert("�Բ���,��ѡ�����������");
					return;
				}			  
			}
			if(typeof(objValue)!="undefined" && objValue!=""){
		   		form.SELECTEDID.value=objValue;
			}
		}
		if(typeof(msg)!="undefined" && msg!=""){
			if(!window.confirm(msg)){
				return;
			}
		}			
		if(typeof(action)!="undefined" && action!=""){
			form.action.value=action;
		}				
		submitDynPageAction(form,action);		
	}
	/*
	 * �ύ���¼�
	 * @param form �����Բ����ķ�װ
	 * @param action �¼�����
	 * @return
	 */	 
	function submitDynPageAction(form,action){ 		
		var param=$(form).formSerialize();     
		var contextPath=$("#contextPath").val();	   
		$.ajax({
			type:"post",
			url:contextPath+"/jsp/support/submitDynPageAction.action",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:param, 		
			success:function(successMsg){  		   			
			var obj = jQuery.parseJSON(successMsg); 
			var status=obj.status;					
			var msg=obj.msg;			
			var result="true";				
				if(status!=result){
					alert(msg);
					return;
				}else if(status==result){
					alert(msg); 	
		 			if(action!="delete" && action!="deleteOne"){
		 				var freshOpener=obj.freshOpener;
						var closeWindow=obj.closeWindow;			
									
		 				if(freshOpener=="true"){		 					
							if(typeof(window.opener)!="undefined"){		
								window.opener.document.forms(0).submit();							
							}
		 				}		 				
			 			if(closeWindow=="true"){
			 				window.close();			 				
			 			}	
		 			}else{	 		 					
		 				loadData(contextPath,param);		 				
		 			}
				} 	 					
			}
		});
	}	
	/*
	 * ���ر�����
	 * @param contxtPath ������·��
	 * @param param �������м���
	 * @return 
	 */
	function loadData(contextPath,param){ 				
		$.ajax({
			type:"post",
			url:contextPath+"/jsp/support/loadDynPageData.action", 	
			data:param,	
			success:function(data){ 	 					
				$("div#maincontent").empty();
		 		$("div#maincontent").html(data); 		
		 		$("div#maincontent").trigger("change"); 	
		 		$("#L0").bind("click",function(e){
 					stopEvent(e);		
			        var cur = e.target;
			        if ($(cur).is("td")){
			            var isEditor=$(cur).attr("isEditor");
			            if(isEditor=="true"){
			               getGridEditor(cur);
			            }
			        }
 				});		 		 			
			}
		});	
	} 
	/*
	 * ��ѯ��ҳ����
	 * @param form ������
	 */
	function queryData(form){
		var param=$(form).formSerialize();     
		var contextPath=$("#contextPath").val();
		$.ajax({
			type:"post",
			url:contextPath+"/jsp/support/loadDynPageData.action", 	
			data:param,	
			success:function(data){ 		
				$("div#maincontent").empty();
		 		$("div#maincontent").html(data); 
		 		$("div#maincontent").trigger("change"); 		
		 		$("#L0").bind("click",function(e){
 					stopEvent(e);		
			        var cur = e.target;
			        if ($(cur).is("td")){
			            var isEditor=$(cur).attr("isEditor");
			            if(isEditor=="true"){
			               getGridEditor(cur);
			            }
			        }
 				});	 			 			
			}
		});	
	} 
	/*
	 * ������ҳ����
	 * @param form ������
	 * @param cpage ��ǰҳ������
	 * @param mpage ���ҳ������  
	 */
	function firstClick(form,cpage,mpage,ob){
		if(cpage==1||cpage==0) return;
		ob.value = 1;
		queryData(form);
	}
	/*
	 * ������ҳ����
	 * @param form ������
	 * @param cpage ��ǰҳ������
	 * @param mpage ���ҳ������  
	 */
	function previousClick(form,cpage,mpage,ob){
		if(cpage==1||cpage==0) return;
		else ob.value = cpage - 1;
		queryData(form);
	}	
	/*
	 * ������ҳ����
	 * @param form ������
	 * @param cpage ��ǰҳ������
	 * @param mpage ���ҳ������  
	 */
	function nextClick(form,cpage,mpage,ob){
		var tempv = parseInt(mpage);
		if(mpage==1||mpage==0) return;
		if(cpage==mpage) return;
		else ob.value = cpage - (-1); 
		queryData(form);
	}
	/*
	 * ����ĩҳ����
	 * @param form ������
	 * @param cpage ��ǰҳ������
	 * @param mpage ���ҳ������  
	 */
	function endClick(form,cpage,mpage,ob){
		var tempv = parseInt(mpage);
		if(mpage==1||mpage==0) return;
		if(cpage==mpage) return;
		else ob.value = mpage;
		queryData(form);
	}
	function toClick(form,mpage){
	    var tmpv=parseInt(form.jumpPage.value);      
	    if(isNaN(tmpv)) return;   
		if(tmpv==0) return;
		if(mpage==1||mpage==0) return;	
		if(tmpv>parseInt(mpage)) return;
		form.currentPage.value =""+tmpv;		
		queryData(form);
	}
 