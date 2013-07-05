
/*
	* 删除表格行 
	* @param box 
	*        要删除的行文档元素ID
	*/
function deleteLine_f(box) {
	
	var table = document.getElementById("L0");
	var rows = table.rows.length;
	if (rows > 1) {
		if (typeof (box) == "undefined") {
			return;
		}
		if (typeof (box.length) == "undefined") {
			if (box.checked) {
				if (!window.confirm("\u60a8\u786e\u5b9a\u8981\u5220\u9664\u5417\uff1f")) {
					return;
				}
				table.deleteRow(1);
				rows--;
			} else {
				alert("\u8bf7\u9009\u62e9\u64cd\u4f5c\u884c!");
			}
		} else {
			var len = box.length;
			var i = 0;
			j = 0;
			var tipFlag = true;
			while (i < len - j) {
				if (box[i].checked) {
					if (tipFlag == true) {
						if (!window.confirm("\u60a8\u786e\u5b9a\u8981\u5220\u9664\u5417\uff1f")) {
							return;
						}
						tipFlag = false;
					}
					table.deleteRow(i + 1);
					rows--;
					j++;
				} else {
					i++;
				}
			}
			if (i == len) {
				alert("\u8bf7\u9009\u62e9\u64cd\u4f5c\u884c!");
			}
		}
	}
}
/**  
	 * 比较函数生成器  
	 *   
	 * @param iCol  
	 *            数据行数  
	 * @param sDataType  
	 *            该行的数据类型  
	 * @return  
	 */
function generateCompareTRs(iCol, sDataType) {
	return function compareTRs(oTR1, oTR2) {
		vValue1 = convert(oTR1.cells[iCol].firstChild.nodeValue, sDataType);
		vValue2 = convert(oTR2.cells[iCol].firstChild.nodeValue, sDataType);
		if (vValue1 < vValue2) {
			return -1;
		} else {
			if (vValue1 > vValue2) {
				return 1;
			} else {
				return 0;
			}
		}
	};
}
/**  
	 * 处理排序的字段类型  
	 *   
	 * @param sValue  
	 *            字段值 默认为字符类型即比较ASCII码  
	 * @param sDataType  
	 *            字段类型 对于date只支持格式为mm/dd/yyyy或mmmm dd,yyyy(January 12,2004)  
	 * @return  
	 */
function convert(sValue, sDataType) {
	if (sValue == null) {
		return sValue;
	}
	switch (sDataType) {
	  case "int":
		return parseInt(sValue);
	  case "float":
		return parseFloat(sValue);
	  case "date":
		return new Date(Date.parse(sValue));
	  default:
		return sValue.toString();
	}
}
/**  
	 * 通过表头对表列进行排序  
	 *   
	 * @param sTableID  
	 *            要处理的表ID<table id=''>  
	 * @param iCol  
	 *            字段列id eg: 0 1 2 3 ...  
	 * @param sDataType  
	 *            该字段数据类型 int,float,date 缺省情况下当字符串处理  
	 */
function sortTable(sTableID, iCol, sDataType) {
	var oTable = document.getElementById(sTableID);
	var oTBody = oTable.tBodies[0];
	var colDataRows = oTBody.rows;
	var aTRs = new Array;
	for (var i = 0; i < colDataRows.length; i++) {
		aTRs[i] = colDataRows[i];
	}
	if (oTable.sortCol == iCol) {
		aTRs.reverse();
	} else {
		aTRs.sort(generateCompareTRs(iCol, sDataType));
	}
	var oFragment = document.createDocumentFragment();
	for (var j = 0; j < aTRs.length; j++) {
		oFragment.appendChild(aTRs[j]);
	}
	oTBody.appendChild(oFragment);
	oTable.sortCol = iCol;
}
var _obj;
var _row;
var _rowPN;
/**
	*  交换表格两行数据的HTML内容
	*  @param row 当前行索引号
	*  @param rowPN 交换行索引号
	*/
function swapNode(row, rowPN) {
	var table = document.getElementById("L0");
	for (var i = 0; i < table.rows(0).cells.length; i++) {
		var td1;
		var td2;
		td1 = table.rows[row].cells(i).innerHTML;
		td2 = table.rows[rowPN].cells(i).innerHTML;
		table.rows[row].cells(i).innerHTML = td2;
		table.rows[rowPN].cells(i).innerHTML = td1;
	}
}
/**
	*  向上移动行数据	
	*  @param obj 当前表格行对象
	*/
function moveUp() {

	var obj = document.getElementsByName("id");
	if (typeof (obj) == "undefined") {
		return;
	}
	for (var i = 0; i < obj.length; i++) {
		if (obj[i].checked) {
			_obj = obj[i];
			break;
		}
	}
	if (typeof (_obj) == "undefined") {
		alert("\u8bf7\u9009\u62e9\u64cd\u4f5c\u884c!");
		return;
	}
	_row = _obj.parentNode.parentNode;	
		//如果当前行是起始行,刚不能上移
	if (_row.rowIndex == 1) {
		return;
	}
	_rowPN = _row.rowIndex - 1;
	swapNode(_row.rowIndex, _rowPN);
}
/**
	*  向下移动行数据	
	*  @param obj 当前表格行对象
	*/
function moveDown() {

	var obj = document.getElementsByName("id");
	if (typeof (obj) == "undefined") {
		return;
	}
	for (var i = 0; i < obj.length; i++) {
		if (obj[i].checked) {
			_obj = obj[i];
			break;
		}
	}
	if (typeof (_obj) == "undefined") {
		alert("\u8bf7\u9009\u62e9\u64cd\u4f5c\u884c!");
		return;
	}
	_row = _obj.parentNode.parentNode;	
		//如果当前行是最后行,则不能下移
	if (_row.nextSibling) {
	} else {
		return;
	}
	_rowPN = _row.rowIndex + 1;
	swapNode(_row.rowIndex, _rowPN);
}

