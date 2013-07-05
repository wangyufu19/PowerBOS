package com.framework.common.util;

public class ArrayRow {
	private String[][] strArray;
	private int row;
	public ArrayRow(String[][] strArray) {
		if (strArray == null) {
			this.strArray=new String[0][0];
		} else
			this.strArray = strArray;
	}	
	public int getNum() {
		if (strArray == null) {
			return 0;
		} else
			return strArray.length;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getRow(int col) {
		String value = "";
		value = getString(col);
		return value;

	}

	public int getCol() {
		return strArray[0].length;
	}

	public String getString(int col) {		
		String value = "";		
		if (row < 0 || strArray == null||strArray.length<1) {
			return "";
		}		
		if(col>=strArray[0].length){
			return "";
		}		
		value = strArray[row][col];
		if (value == null) {
			return "";
		}		
		return value;
	}

}
