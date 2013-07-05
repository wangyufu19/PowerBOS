
/*
	* ɾ������� 
	* @param box 
	*        Ҫɾ�������ĵ�Ԫ��ID
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
	 * �ȽϺ���������  
	 *   
	 * @param iCol  
	 *            ��������  
	 * @param sDataType  
	 *            ���е���������  
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
	 * ����������ֶ�����  
	 *   
	 * @param sValue  
	 *            �ֶ�ֵ Ĭ��Ϊ�ַ����ͼ��Ƚ�ASCII��  
	 * @param sDataType  
	 *            �ֶ����� ����dateֻ֧�ָ�ʽΪmm/dd/yyyy��mmmm dd,yyyy(January 12,2004)  
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
	 * ͨ����ͷ�Ա��н�������  
	 *   
	 * @param sTableID  
	 *            Ҫ����ı�ID<table id=''>  
	 * @param iCol  
	 *            �ֶ���id eg: 0 1 2 3 ...  
	 * @param sDataType  
	 *            ���ֶ��������� int,float,date ȱʡ����µ��ַ�������  
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
	*  ��������������ݵ�HTML����
	*  @param row ��ǰ��������
	*  @param rowPN ������������
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
	*  �����ƶ�������	
	*  @param obj ��ǰ����ж���
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
		//�����ǰ������ʼ��,�ղ�������
	if (_row.rowIndex == 1) {
		return;
	}
	_rowPN = _row.rowIndex - 1;
	swapNode(_row.rowIndex, _rowPN);
}
/**
	*  �����ƶ�������	
	*  @param obj ��ǰ����ж���
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
		//�����ǰ���������,��������
	if (_row.nextSibling) {
	} else {
		return;
	}
	_rowPN = _row.rowIndex + 1;
	swapNode(_row.rowIndex, _rowPN);
}

