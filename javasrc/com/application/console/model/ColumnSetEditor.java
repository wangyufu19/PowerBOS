package com.application.console.model;
/**
 * 字段编辑属性实体类
 * @author wangyf
 * @version 1.0
 */
public class ColumnSetEditor {	
	private String editorDocType;
	private String editorDataType;
	private String editorDocCss;
	private String editorRefFormula;
	
	public String getEditorDocType() {
		return editorDocType;
	}
	public void setEditorDocType(String editorDocType) {
		this.editorDocType = editorDocType;
	}
	public String getEditorDataType() {
		return editorDataType;
	}
	public void setEditorDataType(String editorDataType) {
		this.editorDataType = editorDataType;
	}
	public String getEditorDocCss() {
		return editorDocCss;
	}
	public void setEditorDocCss(String editorDocCss) {
		this.editorDocCss = editorDocCss;
	}
	public String getEditorRefFormula() {
		return editorRefFormula;
	}
	public void setEditorRefFormula(String editorRefFormula) {
		this.editorRefFormula = editorRefFormula;
	}	
}
