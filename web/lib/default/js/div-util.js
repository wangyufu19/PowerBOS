/*
  $Id: div-util.js,v 1.5 2006/02/24 03:08:56 tmani Exp $
  */

function showHideCombos(obj, x, y, sty)
{
	//alert("TMK X : " + dropmenuobj.x + " Y : " + dropmenuobj.y + " LEFT : " + dropmenuobj.style.left + " TOP : " + dropmenuobj.style.top + " WIDTH : " + dropmenuobj.widthobj.width + " HEIGHT : " + dropmenuobj.offsetHeight);
	if(!obj)  return;
	var menuLeftTopX = x;
	var menuLeftTopY = y;
	var menuLeftBottomX = x;
	var menuLeftBottomY = y + obj.offsetHeight;

	var menuRightTopX = x + obj.offsetWidth;
	var menuRightTopY = y;
	var menuRightBottomX = menuRightTopX;
	var menuRightBottomY = y + obj.offsetHeight;

	var selectObjs = document.getElementsByTagName('select');
	for(var i=0; i<selectObjs.length;i++)	
	{
		if(sty == 'hidden')
		{
			var comLeftTopX = ylib_getPageX(selectObjs[i]);
			var comLeftTopY = ylib_getPageY(selectObjs[i]); 
			var comLeftBottomX = comLeftTopX;
			var comLeftBottomY = comLeftTopY + selectObjs[i].offsetHeight;

			var comRightTopX = comLeftTopX + selectObjs[i].offsetWidth;
			var comRightTopY = comLeftTopY;
			var comRightBottomX = comLeftTopX + selectObjs[i].offsetWidth;
			var comRightBottomY = comLeftBottomY ;


			//alert(" COMP : (" + comLeftTopX + ", " + comLeftTopY + "), (" + comRightBottomX + ", " + comRightBottomY + ") --------- MENU : (" + menuLeftTopX + ", " + menuLeftTopY + "), (" + menuRightBottomX + ", " + menuRightBottomY + ") ");

			if(
			   (comLeftTopX>menuLeftTopX && comLeftTopX<menuRightBottomX && comLeftTopY>menuLeftTopY && comLeftTopY<menuRightBottomY) ||
			   (comRightBottomX>menuLeftTopX && comRightBottomX<menuRightBottomX && comRightBottomY>menuLeftTopY && comRightBottomY<menuRightBottomY) ||
			   (comRightTopX<menuRightTopX && comRightTopX>menuLeftBottomX && comRightTopY>menuRightTopY && comRightTopY<menuLeftBottomY) ||
			   (comLeftBottomX>menuLeftBottomX && comLeftBottomX<menuRightTopX && comLeftBottomY>menuRightTopY && comLeftBottomY<menuLeftBottomY) ||

			   (menuLeftTopX>comLeftTopX && menuLeftTopX<comRightBottomX && menuLeftTopY>comLeftTopY && menuLeftTopY<comRightBottomY) ||
			   (menuRightBottomX>comLeftTopX && menuRightBottomX<comRightBottomX && menuRightBottomY>comLeftTopY && menuRightBottomY<comRightBottomY) || 
			   (menuRightTopX>comRightTopX && menuRightTopX<comLeftBottomX && menuRightTopY>comRightTopY && menuRightTopY<comLeftBottomY) ||
			   (menuLeftBottomX>comLeftTopX && menuLeftBottomX<comRightBottomX && menuLeftBottomY>comRightTopY && menuLeftBottomY<comLeftBottomY) ||


			   (comLeftTopX>menuLeftTopX && comRightBottomX<menuRightBottomX&& comLeftTopY<comRightBottomY&& comRightBottomY>menuRightBottomY) ||
			   (menuLeftTopX>comLeftTopX && menuRightBottomX<comRightBottomX && menuLeftTopY<comLeftTopY && menuRightBottomY>comRightBottomY)
			  )	
			
			{
				selectObjs[i].style.visibility = 'hidden';
			}
			else
			{
				selectObjs[i].style.visibility = 'visible';
			}
		}
		else
		{
			selectObjs[i].style.visibility = sty;
		}
	}
}
