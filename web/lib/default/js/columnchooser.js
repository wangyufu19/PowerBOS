function moveUp(form)
{
    
   var temptext = document.tableview.ON_DISPLAY.options[0].text; 
   
   var i=1 ,opt,canmove="false",j=0;
   var tempvalue = document.tableview.ON_DISPLAY.options[0].value;
   while(opt = document.tableview.ON_DISPLAY.options[i])
   {
    if (opt.selected)
    {
        j=i-1;
        while(j>=0)
        {
            if(!document.tableview.ON_DISPLAY.options[j].selected)
            {
                canmove="true";
                break;
            }
            j--;
        }
        if(canmove == "true")
        {
            document.tableview.ON_DISPLAY.options[i-1].value = document.tableview.ON_DISPLAY.options[i].value;
            document.tableview.ON_DISPLAY.options[i-1].text = document.tableview.ON_DISPLAY.options[i].text;
            document.tableview.ON_DISPLAY.options[i].value = tempvalue;
            document.tableview.ON_DISPLAY.options[i].text = temptext;
            document.tableview.ON_DISPLAY.options[i-1].selected = true;
            document.tableview.ON_DISPLAY.options[i].selected = false;
        }
    }
    temptext = document.tableview.ON_DISPLAY.options[i].text;
    tempvalue = document.tableview.ON_DISPLAY.options[i++].value;
    canmove="false";
  }
}
function moveDown(form)
{
    var temptext = document.tableview.ON_DISPLAY.options[document.tableview.ON_DISPLAY.options.length-1].text; 
    var tempvalue = document.tableview.ON_DISPLAY.options[document.tableview.ON_DISPLAY.options.length-1].value
    var i=document.tableview.ON_DISPLAY.options.length-2 ,opt,j,canmove="false";

    while(opt = document.tableview.ON_DISPLAY.options[i])
    {
        if (opt.selected)
        {
            j=i+1;
            while(j<=document.tableview.ON_DISPLAY.options.length-1)
            {
                if(!document.tableview.ON_DISPLAY.options[j].selected)
                {
                    canmove="true";
                    break;
                }
                j++;
            }
            if(canmove == "true")
            {
                document.tableview.ON_DISPLAY.options[i+1].value = document.tableview.ON_DISPLAY.options[i].value;
                document.tableview.ON_DISPLAY.options[i+1].text = document.tableview.ON_DISPLAY.options[i].text;
                document.tableview.ON_DISPLAY.options[i].value = tempvalue;
                document.tableview.ON_DISPLAY.options[i].text = temptext;
                document.tableview.ON_DISPLAY.options[i+1].selected = true;
                document.tableview.ON_DISPLAY.options[i].selected = false;
            }
        }
        temptext = document.tableview.ON_DISPLAY.options[i].text;
        tempvalue = document.tableview.ON_DISPLAY.options[i--].value;
        canmove="false";
    }
}


function updateList(mode)
{
      
       if(mode == "add")
       {
            var opt,i=0;
            while(opt = document.tableview.ON_DATASOURCE.options[i++] )
            {
                if(opt.selected &&(opt.index >=0))
                {
                    
                    document.tableview.ON_DISPLAY.options[document.tableview.ON_DISPLAY.options.length] = new Option(opt.value, opt.value, true, false);
                    document.tableview.ON_DATASOURCE.options[i-1] = null;
                    i--;
                }
            }
        }
        else if(mode == "remove")
        {
          var opt,i=0;
            while(opt = document.tableview.ON_DISPLAY.options[i++])
            {
                if(opt.selected &&(opt.index >=0))
                {
                    document.tableview.ON_DATASOURCE.options[document.tableview.ON_DATASOURCE.options.length] = new Option(opt.value, opt.value, true, false);
                    document.tableview.ON_DISPLAY.options[i-1] = null;
                    i--
                }
            }
       }
}

function moveAll(destination)
{
    var opt,i=0;
    if(destination == "ON_DISPLAY")
    {
        while(opt = document.tableview.ON_DATASOURCE.options[i])
        {
            document.tableview.ON_DISPLAY.options[document.tableview.ON_DISPLAY.options.length] = new Option(opt.value, opt.value, true, false);
            document.tableview.ON_DATASOURCE.options[i]=null;
        }
    }
    else if(destination == "ON_DATASOURCE")
    {
        while(opt = document.tableview.ON_DISPLAY.options[i])
        {
            
            document.tableview.ON_DATASOURCE.options[document.tableview.ON_DATASOURCE.options.length] = new Option(opt.value, opt.value, true, false);
            document.tableview.ON_DISPLAY.options[i]=null;
        }
    }
}

function MM_findObj(n, d) {
 //v4.01
  var p,i,x;
  if(!d)
    d=document;

 if((p=n.indexOf("?"))>0&&parent.frames.length)
 {
    d=parent.frames[n.substring(p+1)].document;
    n=n.substring(0,p);
  }
  if(!(x=d[n])&&d.all) 
    x=d.all[n];
    
 for (i=0;!x&&i<d.forms.length;i++)
    x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++)
    x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) 
    x=d.getElementById(n);
 
    return x;
}

function MM_changeProp() { //v6.0
  var i;
  var allcolObj = MM_findObj('allcol');

  if (allcolObj.checked==false)
  {
	  for (i=1;i<=document.tableview.columnCount.value;i++)
	  {
		var obj = MM_findObj('col'+i);
		obj.checked=false
	  }
  }
  else
  {
	  for (i=1;i<=document.tableview.columnCount.value;i++)
	  {
		var obj = MM_findObj('col'+i);
		obj.checked=true
	  }
  }
}
var viewselected=false
function MM_viewSelected() { //v6.0
  var i;
  var swapviewObj=MM_findObj('swapview');

  if (viewselected==false)
  {
	  for (i=1;i<=document.tableview.columnCount.value;i++)
	  {
		var rowobj = MM_findObj('row'+i);
		var colobj = MM_findObj('col'+i);

		if (colobj.checked==false)
			rowobj.style.display='none';

		viewselected=true;
		swapviewObj.src="lib/default/images/view.png";
		swapviewObj.tooltip="View All";
	  }
  }
  else
  {
  	  for (i=1;i<=document.tableview.columnCount.value;i++)
	  {
		var rowobj = MM_findObj('row'+i);
		var colobj = MM_findObj('col'+i);

		if (colobj.checked==false)
			rowobj.style.display='block';

		viewselected=false;
		swapviewObj.src="lib/default/images/viewsel.png";
		swapviewObj.tooltip="View Selected";
	  }	
  }
}

var currcol=''
function MM_rowsel(id)
{
        
        
	var rowobj=MM_findObj('row'+id)
	var colobj=MM_findObj('col'+id)
	var colvalobj=MM_findObj('colval'+id)

        var columnobj=MM_findObj('column'+id);
        var columnvalobj=MM_findObj('columnval'+id);

	//rowobj.className='tboxstyleselected';        

        columnobj.className='tboxstyleselected';
        columnvalobj.className='tboxstyleselected';        
        colvalobj.className='tboxstyleselected';


	//rowobj.bgColor="#0A246A"
	//colvalobj.style.backgroundColor="#0A246A"
	//colvalobj.style.color="#FFFFFF"
	
	currcol=id;

	
	for (i=1;i<=document.tableview.columnCount.value;i++)
	{
		if (i!=id)
		{
			var otherrowobj=MM_findObj('row'+i)
			var othercolobj=MM_findObj('col'+i)
			var othercolvalobj=MM_findObj('colval'+i)
                        var othercolumnobj=MM_findObj('column'+i)
			var othercolumnvalobj=MM_findObj('columnval'+i)
                        othercolumnvalobj.className='tbottom';
                        othercolumnobj.className='tbottom';
                        othercolvalobj.className='tboxstyle';
			//otherrowobj.bgColor="#FFFFFF"
			//othercolvalobj.style.backgroundColor="#FFFFFF"
			//othercolvalobj.style.color="#000000"
		}
	}
}

function MM_moveUp()
{
	if(currcol!="")
	{        
		if (currcol!=1)
		{
			var up=currcol	
			var down=parseInt(currcol)-1
			
			var uprowobj=MM_findObj('row'+up)
			var upcolobj=MM_findObj('col'+up)
			var upcolvalobj=MM_findObj('colval'+up)
					var upcolumnobj=MM_findObj('column'+up)
			var upcolumnvalobj=MM_findObj('columnval'+up)
			
			var downrowobj=MM_findObj('row'+down)
			var downcolobj=MM_findObj('col'+down)
			var downcolvalobj=MM_findObj('colval'+down)
					var downcolumnobj=MM_findObj('column'+down)
			var downcolumnvalobj=MM_findObj('columnval'+down)
		
			var tempbgcolor,tempchecked,tempbackgroundColor,tempcolor,temptext,tempclassname;
			
			tempbgcolor=uprowobj.bgColor
			uprowobj.bgColor=downrowobj.bgColor
			downrowobj.bgColor=tempbgcolor	

			tempchecked=upcolobj.checked
			upcolobj.checked=downcolobj.checked
			downcolobj.checked=tempchecked

					tempclassname=upcolumnobj.className
			upcolumnobj.className=downcolumnobj.className
			downcolumnobj.className=tempclassname		
			
			tempclassname=upcolumnvalobj.className
			upcolumnvalobj.className=downcolumnvalobj.className
			downcolumnvalobj.className=tempclassname

					tempclassname=upcolvalobj.className
			upcolvalobj.className=downcolvalobj.className
			downcolvalobj.className=tempclassname    


			temptext=upcolvalobj.value
			upcolvalobj.value=downcolvalobj.value
			downcolvalobj.value=temptext
			
			currcol=downcolobj.id.substring(3,downcolobj.id.length)
		}
	}
}

function MM_moveDown()
{
	if(currcol!="")
	{
		if (currcol!=document.tableview.columnCount.value)
		{
			var up=currcol	
			var down=parseInt(currcol)+1
			
			var uprowobj=MM_findObj('row'+up)
			var upcolobj=MM_findObj('col'+up)
			var upcolvalobj=MM_findObj('colval'+up)
					var upcolumnobj=MM_findObj('column'+up)
			var upcolumnvalobj=MM_findObj('columnval'+up)
			
			
			var downrowobj=MM_findObj('row'+down)
			var downcolobj=MM_findObj('col'+down)
			var downcolvalobj=MM_findObj('colval'+down)
					var downcolumnobj=MM_findObj('column'+down)
			var downcolumnvalobj=MM_findObj('columnval'+down)
		
			var tempbgcolor,tempchecked,tempbackgroundColor,tempcolor,temptext,tempclassname;
			
			tempbgcolor=uprowobj.bgColor
			uprowobj.bgColor=downrowobj.bgColor
			downrowobj.bgColor=tempbgcolor
		
			tempchecked=upcolobj.checked
			upcolobj.checked=downcolobj.checked
			downcolobj.checked=tempchecked
					
					tempclassname=upcolumnobj.className
			upcolumnobj.className=downcolumnobj.className
			downcolumnobj.className=tempclassname		
			
			tempclassname=upcolumnvalobj.className
			upcolumnvalobj.className=downcolumnvalobj.className
			downcolumnvalobj.className=tempclassname

					tempclassname=upcolvalobj.className
			upcolvalobj.className=downcolvalobj.className
			downcolvalobj.className=tempclassname        		

			temptext=upcolvalobj.value
			upcolvalobj.value=downcolvalobj.value
			downcolvalobj.value=temptext
			
			currcol=downcolobj.id.substring(3,downcolobj.id.length)
		}
	}
}