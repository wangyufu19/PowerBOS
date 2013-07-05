
function SelectStartWithTotal(ObjName,formName)
{ 

	for ( var objID=0; objID < formName.elements.length; objID++ ){
	
		
		
		
      		if (formName.elements[objID].name.indexOf(ObjName)!=-1 )
      		{  
			
			for (i=0; i<formName.elements[objID].length; i++)
      			formName.elements[objID].options[i].selected = true;
				
      		}
  	}
}

function GetObjID(ObjName,formName)
{ 
	for ( var objID=0; objID < formName.elements.length; objID++ ){
	
      		if (formName.elements[objID].name == ObjName )
      		{  
								return(objID);
      		}
  }
  return(-1);
  
}	


function getPrevObj(formName,el){

	//alert("&&&&&"+el.name);

	for ( var objID=0; objID < formName.elements.length; objID++ ){
	
			alert(formName.elements[objID].name);


      		if (formName.elements[objID].id == el.id )
      		{  
				 return(formName.elements[objID-1]);
      		}else{
			}
    }
}



function getNextObj(formName,el){
	for ( var objID=0; objID < formName.elements.length; objID++ ){
	
      		if (formName.elements[objID] == el )
      		{  
				 return(formName.elements[objID+1]);
      		}
    }
}





function SelectTotal(ObjName,formName)
{ 
	objID = GetObjID(ObjName,formName);
  	if (objID != -1)
  	{ 
		for (i=0; i<formName.elements[objID].length; i++)
      		formName.elements[objID].options[i].selected = true;
  	}
}




function IsSelected(ID, Value,formName)
{ 
	if (ID != -1 && Value != "")85
  	{  
		for (var cc=0; cc<formName.elements[ID].length; cc++ )
     		{   
			if (formName.elements[ID].options[cc].value == Value)
            		return true;
     		}
     		return false;
  	}
  	return false;
}


function HasTotal(ID,formName)
{ 
	if (ID != -1 )
  	{
		for (var cc=0; cc<formName.elements[ID].length; cc++ )
     		{   
			if ( formName.elements[ID].options[cc].selected )
			{
				if (formName.elements[ID].options[cc].value == "0000")
            			{  
					return true;
        			}
			}
     		}
     		return false;
  	}
  	return false;
}









function getSelectedText(obj,selectedKey){

  var ret="";
  for (i=0;i<obj.options.length;i++){
    var current = obj.options[i];
    
    if(current.text==selectedKey){
    	ret=current.value;
    	break;
     }
  }
  
  return ret;	
	
}


function selectedOption(obj,selectedKey){
  var flag=false; 
  for (i=0;i<obj.options.length;i++){
    var current = obj.options[i];
    if(current.text==selectedKey){
    	current.selected=true;
    	flag=true;
    	break;
     }
  }
  
  if(!flag){
  	obj.options[0].selected=true;
  }
	
}



function MoveItem(object,direction)
{
	obj = eval('document.'+object);
	len = obj.options.length-1;
  if (obj.options.length < 2 )
  {
    alert('你不能移动这个项目!');
	return;
  }
  if (obj.options[0].selected && direction == 'up')
  {
    alert('你不能移动这个项目!');
	return;
  }
  if (obj.options[len].selected && direction == 'down')
  {
    alert('你不能移动这个项目!');
	return;
  }
  
  var sel = false;
  for (i=0;i<obj.options.length;i++)
  {
    var current = obj.options[i];
    if (current.selected)
    {
      sel = true;
      txtcurrent = current.text;
      valcurrent = current.value;

	  if (direction == 'up')
	  {
	  upitem = obj.options[i-1];
	  txtup = upitem.text;
	  valup = upitem.value;
	    txt = txtup;
		val = valup;
		upitem.text = txtcurrent;
		upitem.value = valcurrent;
		current.text = txt;
		current.value = val;

		upitem.selected = true;
		current.selected = false;
		break;
	  }
	  if (direction == 'down')
	  {
	  downitem = obj.options[i+1];
	  txtdown = downitem.text;
	  valdown = downitem.value;
	    txt = txtdown;
		val = valdown;
		downitem.text = txtcurrent;
		downitem.value = valcurrent;
		current.text = txt;
		current.value = val;

		downitem.selected = true;
		current.selected = false;
		break;
	  }
    }
  }
  if (!sel) alert ('你还没有选择任何项目');
}


	
function AppendItem(ObjName, DesName,formName)
{ 
	objID    = GetObjID(ObjName,formName);
  	DesObjID = GetObjID(DesName,formName);
  	if (objID != -1 && DesObjID != -1)
  	{  
		if ( IsSelected(DesObjID, "0000",formName) )
        		window.alert("已包含");
     		else
     		{  
			if ( HasTotal(objID,formName) )
        {  formName.elements[DesObjID].length = 0;
           formName.elements[DesObjID].options[0]= new Option("---请选择相关人员---", "0000");

        }
        else
        {  if (formName.elements[DesObjID].length == 10000)
              window.alert("最多选10000项。");
           else
           {  //GET SELECTED ITEM NUMBER
              SelNum = 0;
              for (var j=0; j<formName.elements[objID].length; j++)
              {   if ( formName.elements[objID].options[j].selected)
                  SelNum ++;
              }
              if ((SelNum + formName.elements[DesObjID].length) > 10000)
                 window.alert("最多选10000项。");
              else
              {  //add
                 for (j=0; j<formName.elements[objID].length; j++)
                 {   if (formName.elements[objID].options[j].selected)
                     {  //GET VALUE
                        dd = formName.elements[objID].options[j].value;
                        if (!IsSelected(DesObjID, dd,formName))
                        {  //GET LENGTH
                           DesLen = formName.elements[DesObjID].length;
                           // NEW OPTION
                           formName.elements[DesObjID].options[DesLen]= new Option(formName.elements[objID].options[j].text, formName.elements[objID].options[j].value);
                        }
                        //else
                           //window.alert("此选项已选择。");

                     }
                 }
              }
           }
        }
	   datechanged=true;
     }
     //CLEAR
     for (j=0; j<formName.elements[objID].length; j++)
          formName.elements[objID].options[j].selected = false;
  }
}






function AppendAllItem(box,ObjName, DesName,formName)
{ 
	  objID    = GetObjID(ObjName,formName);
  	DesObjID = GetObjID(DesName,formName);
  	if (objID == -1 || DesObjID == -1) return;
  	
  	formName.elements[DesObjID].length=0;
  	
  	
  	var ob=formName.elements[objID];
  	
  	
  	if(boxChecked(box)){
  	 	for (j=0; j<formName.elements[objID].length; j++){
  	 		formName.elements[DesObjID].options[j]= new Option(formName.elements[objID].options[j].text, formName.elements[objID].options[j].value);
  		}
  	}
}





function RemoveItem(ObjName,formName)
{ objID = GetObjID(ObjName,formName);
  datechanged=true;
  if ( objID != -1 )
  {  var  check_index = new Array();
     for (i=formName.elements[objID].length-1; i>=0; i--)
     {   if (formName.elements[objID].options[i].selected)
         {  check_index[i] = true;
            formName.elements[objID].options[i].selected = false;
         }
         else
            check_index[i] = false;
     }
     for (i=formName.elements[objID].length-1; i>=0; i--)
     {   if (check_index[i])
             formName.elements[objID].options[i] = null
     }
  }
}



//------------------------------------------

function MoveItemID(objectID,direction)
{
	
	
	var obj=document.getElementById(objectID);
	
	len = obj.options.length-1;
  if (obj.options.length < 2 )
  {
    alert('你不能移动这个项目!');
	return;
  }
  if (obj.options[0].selected && direction == 'up')
  {
    alert('你不能移动这个项目!');
	return;
  }
  if (obj.options[len].selected && direction == 'down')
  {
    alert('你不能移动这个项目!');
	return;
  }
  
  var sel = false;
  for (i=0;i<obj.options.length;i++)
  {
    var current = obj.options[i];
    if (current.selected)
    {
      sel = true;
      txtcurrent = current.text;
      valcurrent = current.value;

	  if (direction == 'up')
	  {
	  upitem = obj.options[i-1];
	  txtup = upitem.text;
	  valup = upitem.value;
	    txt = txtup;
		val = valup;
		upitem.text = txtcurrent;
		upitem.value = valcurrent;
		current.text = txt;
		current.value = val;

		upitem.selected = true;
		current.selected = false;
		break;
	  }
	  if (direction == 'down')
	  {
	  downitem = obj.options[i+1];
	  txtdown = downitem.text;
	  valdown = downitem.value;
	    txt = txtdown;
		val = valdown;
		downitem.text = txtcurrent;
		downitem.value = valcurrent;
		current.text = txt;
		current.value = val;

		downitem.selected = true;
		current.selected = false;
		break;
	  }
    }
  }
  if (!sel) alert ('你还没有选择任何项目');
}





	
function AppendItemID(objID, DesObjID,formName)
{ 
	if (objID == -1 || DesObjID == -1) return;
	var obj=document.getElementById(objID);
	var desObj=document.getElementById(DesObjID);



	if ( IsSelected(DesObjID, "0000",formName) )
			window.alert("已包含");
		else
		{  
		if ( HasTotal(objID,formName) )
	{ desObj.length = 0;
	   desObj.options[0]= new Option("---请选择相关人员---", "0000");

	}
	else
	{  if (desObj.length == 10000)
		  window.alert("最多选10000项。");
	   else
	   {  //GET SELECTED ITEM NUMBER
		  SelNum = 0;
		  for (var j=0; j<obj.length; j++)
		  {   if ( obj.options[j].selected)
			  SelNum ++;
		  }
		  if ((SelNum + desObj.length) > 10000)
			 window.alert("最多选10000项。");
		  else
		  {  //add
			 for (j=0; j<obj.length; j++)
			 {   if (obj.options[j].selected)
				 {  //GET VALUE
					dd = obj.options[j].value;
					if (!IsSelected(DesObjID, dd,formName))
					{  //GET LENGTH
					   DesLen = desObj.length;
					   // NEW OPTION
					   desObj.options[DesLen]= new Option(obj.options[j].text, obj.options[j].value);
					}
					//else
					   //window.alert("此选项已选择。");

				 }
			 }
		  }
	   }
	}
   datechanged=true;

     //CLEAR
     for (j=0; j<formName.elements[objID].length; j++)
          formName.elements[objID].options[j].selected = false;
	}
}






function AppendAllItemID(box,objID, DesObjID,formName)
{ 

  	if (objID == -1 || DesObjID == -1) return;

	var obj=document.getElementById(objID);
	var desObj=document.getElementById(DesObjID);
  	
  	desObj.length=0;

  	if(boxChecked(box)){
  	 	for (j=0; j<obj.length; j++){
  	 		desObj.options[j]= new Option(obj.options[j].text, obj.options[j].value);
  		}
  	}
}

 /*
 * 
 */

    function boxChecked(box){
        var choosed=false;
        if (typeof(box)=="undefined"){
            return false;
        }
        if (typeof(box.length)=="undefined"){
            if (box.checked){
                choosed=true;
            }
        }else{
            for (var i=0;i<box.length;i++) {
                if (box[i].checked) {
                    choosed=true;
                    break;
                }
            }
        }
        return choosed;
    }



function RemoveItemID(objID,formName){ 

  var obj=document.getElementById(objID);
  datechanged=true;
  if ( objID != -1 )
  {  var  check_index = new Array();
     for (i=obj.length-1; i>=0; i--)
     {   if (obj.options[i].selected)
         {  check_index[i] = true;
            obj.options[i].selected = false;
         }
         else
            check_index[i] = false;
     }
     for (i=obj.length-1; i>=0; i--)
     {   if (check_index[i])
             obj.options[i] = null
     }
  }
}