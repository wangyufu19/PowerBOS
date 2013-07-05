package com.application.console.model;
/**
 * 字段属性实体类
 * @author wangyf
 * @version 1.0
 */
public class ColumnSet {
	private String key;
	//基本属性
	private String name;
	private String chineseName;
	private String value;
	private String dataType;
	private String docType;
	private String docCss;
	private String docWidth;
	private String docHeight;
	private String docMaxlength;
	//高级属性
	private String loadCondition;
	private String refFormula;
	private String docExtend;
	private String isDisabled;
	private String isReadonly;
	private String isNull;
	private String isEditor;
	//事件属性
	private String docHref;
	private String docOnclick;
	private String docOnblur;	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getDocCss() {
		return docCss;
	}
	public void setDocCss(String docCss) {
		this.docCss = docCss;
	}
	public String getDocWidth() {
		return docWidth;
	}
	public void setDocWidth(String docWidth) {
		this.docWidth = docWidth;
	}
	public String getDocHeight() {
		return docHeight;
	}
	public void setDocHeight(String docHeight) {
		this.docHeight = docHeight;
	}
	public String getDocMaxlength() {
		return docMaxlength;
	}
	public void setDocMaxlength(String docMaxlength) {
		this.docMaxlength = docMaxlength;
	}
	
	public String getLoadCondition() {
		return loadCondition;
	}
	public void setLoadCondition(String loadCondition) {
		this.loadCondition = loadCondition;
	}
	public String getRefFormula() {
		return refFormula;
	}
	public void setRefFormula(String refFormula) {
		this.refFormula = refFormula;
	}
	public String getDocExtend() {
		return docExtend;
	}
	public void setDocExtend(String docExtend) {
		this.docExtend = docExtend;
	}
	public String getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(String isDisabled) {
		this.isDisabled = isDisabled;
	}	
	public String getIsReadonly() {
		return isReadonly;
	}
	public void setIsReadonly(String isReadonly) {
		this.isReadonly = isReadonly;
	}
	public String getIsNull() {
		return isNull;
	}
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	public String getDocHref() {
		return docHref;
	}
	public void setDocHref(String docHref) {
		this.docHref = docHref;
	}
	public String getDocOnclick() {
		return docOnclick;
	}
	public void setDocOnclick(String docOnclick) {
		this.docOnclick = docOnclick;
	}
	public String getDocOnblur() {
		return docOnblur;
	}
	public void setDocOnblur(String docOnblur) {
		this.docOnblur = docOnblur;
	}
	public String getIsEditor() {
		return isEditor;
	}
	public void setIsEditor(String isEditor) {
		this.isEditor = isEditor;
	}
}
