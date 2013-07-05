package com.application.console.biz;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import org.jdom.Element;

import com.application.console.model.ColumnSet;
import com.application.console.model.ColumnSetEditor;
import com.framework.common.util.SysConstants;
import com.framework.common.xmldoc.DocFactory;
import com.framework.common.xmldoc.DocObject;
import com.framework.view.util.StringFormat;
/**
 * 字段集业务类
 * @author wangyf
 * @version 1.0
 */
public class ColumnSetBiz {
	
	public List getColumnSetList(String resourceName,String columnSetKey){
		List columnSets=new ArrayList();
		if("".equals(resourceName)||"".equals(columnSetKey)) return columnSets;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return columnSets;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element columnSetE=doc.getDocElement(columnSetKey);			
		List list=columnSetE.getChildren("COLUMN");
		if(list==null) return columnSets;
		for(int i=0;i<list.size();i++){
			Element colE=(Element)list.get(i);			
			ColumnSet columnSet=new ColumnSet();			
			columnSet.setKey(StringFormat.replaceNull(colE.getAttributeValue("EID")));			
			columnSet.setName(StringFormat.replaceNull(colE.getAttributeValue("name")));
			columnSet.setChineseName(StringFormat.replaceNull(colE.getAttributeValue("chineseName")));
			columnSet.setValue(StringFormat.replaceNull(colE.getAttributeValue("value")));
			columnSet.setDataType(StringFormat.replaceNull(colE.getAttributeValue("dataType")));
			columnSet.setDocWidth(StringFormat.replaceNull(colE.getAttributeValue("docType")));
			columnSet.setDocCss(StringFormat.replaceNull(colE.getAttributeValue("docCss")));
			columnSet.setDocWidth(StringFormat.replaceNull(colE.getAttributeValue("docWidth")));
			columnSet.setDocHeight(StringFormat.replaceNull(colE.getAttributeValue("docHeight")));
			columnSet.setDocMaxlength(StringFormat.replaceNull(colE.getAttributeValue("docMaxlength")));
			columnSets.add(columnSet);
		}
		return columnSets;
	}
	
	public void saveColumnSetList(String resourceName,String columnSetKey,String[] columnkeyArr,String[] nameArr,String[] chineseNameArr,String[] valueArr,String[] dataTypeArr){
		if("".equals(resourceName)||"".equals(columnSetKey)) return;			
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element columnSetE=doc.getDocElement(columnSetKey);	
		if(columnSetE==null) return;		
		List list=new ArrayList();
		if(nameArr!=null){			
			for(int i=0;i<nameArr.length;i++){							
				Element colE=doc.getDocElement(columnkeyArr[i]);				
				if(colE!=null){
					colE.setAttribute("name", nameArr[i]);
					colE.setAttribute("chineseName", chineseNameArr[i]);
					colE.setAttribute("value", valueArr[i]);
					if(dataTypeArr!=null){
						colE.setAttribute("dataType", dataTypeArr[i]);		
					}									
					list.add(colE);
				}else{
					colE=new Element("COLUMN");
					colE.setAttribute("name", nameArr[i]);
					colE.setAttribute("chineseName", chineseNameArr[i]);
					colE.setAttribute("value", valueArr[i]);
					if(dataTypeArr!=null){
						colE.setAttribute("dataType", dataTypeArr[i]);		
					}		
				    list.add(colE);
				}				
			}		
			columnSetE.removeContent();
			for(int i=0;i<list.size();i++){
				Element colE=(Element)list.get(i);
				doc.addDocElement(columnSetE, colE);
			}			
			doc.saveDoc();	
		}				
	}
	public ColumnSet getGridColumnSetAtt(String resourceName,String columnKey){
		ColumnSet columnSet=new ColumnSet();
		if("".equals(resourceName)||"".equals(columnKey)) return columnSet;	
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return columnSet;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element colE=doc.getDocElement(columnKey);	
		if(colE==null) return columnSet;
		columnSet.setLoadCondition(StringFormat.replaceNull(colE.getAttributeValue("loadCondition")));
		columnSet.setDocType(StringFormat.replaceNull(colE.getAttributeValue("docType")));	
		columnSet.setDocWidth(StringFormat.replaceNull(colE.getAttributeValue("docWidth")));
		columnSet.setIsDisabled(StringFormat.replaceNull(colE.getAttributeValue("isDisabled")));
		columnSet.setIsEditor(StringFormat.replaceNull(colE.getAttributeValue("isEditor")));
		columnSet.setRefFormula(StringFormat.replaceNull(colE.getAttributeValue("refFormula")));
		columnSet.setDocExtend(StringFormat.replaceNull(colE.getAttributeValue("docExtend")));
		columnSet.setDocHref(StringFormat.replaceNull(colE.getAttributeValue("docHref")));
		columnSet.setDocOnclick(StringFormat.replaceNull(colE.getAttributeValue("docOnclick")));
		return columnSet;
	} 
	public ColumnSet getFormColumnSetAtt(String resourceName,String columnKey){
		ColumnSet columnSet=new ColumnSet();
		if("".equals(resourceName)||"".equals(columnKey)) return columnSet;	
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return columnSet;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element colE=doc.getDocElement(columnKey);	
		if(colE==null) return columnSet;
		columnSet.setLoadCondition(StringFormat.replaceNull(colE.getAttributeValue("loadCondition")));
		columnSet.setDocType(StringFormat.replaceNull(colE.getAttributeValue("docType")));
		columnSet.setDocCss(StringFormat.replaceNull(colE.getAttributeValue("docCss")));
		columnSet.setDocWidth(StringFormat.replaceNull(colE.getAttributeValue("docWidth")));
		columnSet.setDocHeight(StringFormat.replaceNull(colE.getAttributeValue("docHeight")));
		columnSet.setDocMaxlength(StringFormat.replaceNull(colE.getAttributeValue("docMaxlength")));
		columnSet.setRefFormula(StringFormat.replaceNull(colE.getAttributeValue("refFormula")));
		columnSet.setDocExtend(StringFormat.replaceNull(colE.getAttributeValue("docExtend")));
		columnSet.setIsDisabled(StringFormat.replaceNull(colE.getAttributeValue("isDisabled")));
		columnSet.setIsReadonly(StringFormat.replaceNull(colE.getAttributeValue("isReadonly")));
		columnSet.setIsNull(StringFormat.replaceNull(colE.getAttributeValue("isNull")));
		columnSet.setDocOnclick(StringFormat.replaceNull(colE.getAttributeValue("docOnclick")));
		columnSet.setDocOnblur(StringFormat.replaceNull(colE.getAttributeValue("docOnblur")));
		return columnSet;
	}
	public void saveGridColumnSetAtt(String resourceName,String columnKey,ColumnSet columnSet){
		if("".equals(resourceName)||"".equals(columnKey)||columnSet==null) return;	
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element colE=doc.getDocElement(columnKey);	
		if(colE==null) return;
		colE.setAttribute("loadCondition", StringFormat.replaceNull(columnSet.getLoadCondition()));
		colE.setAttribute("docType", StringFormat.replaceNull(columnSet.getDocType()));
		colE.setAttribute("docWidth", StringFormat.replaceNull(columnSet.getDocWidth()));
		colE.setAttribute("isDisabled", StringFormat.replaceNull(columnSet.getIsDisabled()));
		colE.setAttribute("isEditor", StringFormat.replaceNull(columnSet.getIsEditor()));
		colE.setAttribute("refFormula", StringFormat.replaceNull(columnSet.getRefFormula()));
		colE.setAttribute("docExtend", StringFormat.replaceNull(columnSet.getDocExtend()));
		colE.setAttribute("docHref", StringFormat.replaceNull(columnSet.getDocHref()));
		colE.setAttribute("docOnclick", StringFormat.replaceNull(columnSet.getDocOnclick()));
		doc.saveDoc();
	}
	public void saveFormColumnSetAtt(String resourceName,String columnKey,ColumnSet columnSet){
		if("".equals(resourceName)||"".equals(columnKey)||columnSet==null) return;	
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element colE=doc.getDocElement(columnKey);	
		if(colE==null) return;
		colE.setAttribute("loadCondition", StringFormat.replaceNull(columnSet.getLoadCondition()));
		colE.setAttribute("docType", StringFormat.replaceNull(columnSet.getDocType()));
		colE.setAttribute("docCss", StringFormat.replaceNull(columnSet.getDocCss()));
		colE.setAttribute("docWidth", StringFormat.replaceNull(columnSet.getDocWidth()));
		colE.setAttribute("docHeight", StringFormat.replaceNull(columnSet.getDocHeight()));
		colE.setAttribute("docMaxlength", StringFormat.replaceNull(columnSet.getDocMaxlength()));
		colE.setAttribute("refFormula", StringFormat.replaceNull(columnSet.getRefFormula()));
		colE.setAttribute("docExtend", StringFormat.replaceNull(columnSet.getDocExtend()));
		colE.setAttribute("isDisabled", StringFormat.replaceNull(columnSet.getIsDisabled()));
		colE.setAttribute("isReadonly", StringFormat.replaceNull(columnSet.getIsReadonly()));
		colE.setAttribute("isNull", StringFormat.replaceNull(columnSet.getIsNull()));
		colE.setAttribute("docOnclick", StringFormat.replaceNull(columnSet.getDocOnclick()));
		colE.setAttribute("docOnblur", StringFormat.replaceNull(columnSet.getDocOnblur()));
		doc.saveDoc();
	}   
    public ColumnSetEditor getColumnSetEditor(String resourceName,String columnKey){
    	ColumnSetEditor columnSetEditor=new ColumnSetEditor();
    	if("".equals(resourceName)||"".equals(columnKey)) return columnSetEditor;	
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return columnSetEditor;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element colE=doc.getDocElement(columnKey);	
		if(colE==null) return columnSetEditor;
		Element editorE=colE.getChild("EDITOR");
		if(editorE==null) return columnSetEditor;
		columnSetEditor.setEditorDocType(StringFormat.replaceNull(editorE.getAttributeValue("editorDocType")));
		columnSetEditor.setEditorDataType(StringFormat.replaceNull(editorE.getAttributeValue("editorDataType")));
		columnSetEditor.setEditorDocCss(StringFormat.replaceNull(editorE.getAttributeValue("editorDocCss")));		
		columnSetEditor.setEditorRefFormula(StringFormat.replaceNull(editorE.getAttributeValue("editorRefFormula")));		
		return columnSetEditor;
    }
    public void saveColumnSetEditor(String resourceName,String columnKey,ColumnSetEditor columnSetEditor){
    	if("".equals(resourceName)||"".equals(columnKey)||columnSetEditor==null) return;	
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element colE=doc.getDocElement(columnKey);	
		if(colE==null) return;
		Element editorE=colE.getChild("EDITOR");
		if(editorE==null){
			editorE=new Element("EDITOR");
			editorE.setAttribute("editorDocType", StringFormat.replaceNull(columnSetEditor.getEditorDocType()));
			editorE.setAttribute("editorDataType", StringFormat.replaceNull(columnSetEditor.getEditorDataType()));
			editorE.setAttribute("editorDocCss", StringFormat.replaceNull(columnSetEditor.getEditorDocCss()));
			editorE.setAttribute("editorRefFormula", StringFormat.replaceNull(columnSetEditor.getEditorRefFormula()));
			doc.addDocElement(colE, editorE);
		}else{
			editorE.setAttribute("editorDocType", StringFormat.replaceNull(columnSetEditor.getEditorDocType()));
			editorE.setAttribute("editorDataType", StringFormat.replaceNull(columnSetEditor.getEditorDataType()));
			editorE.setAttribute("editorDocCss", StringFormat.replaceNull(columnSetEditor.getEditorDocCss()));
			editorE.setAttribute("editorRefFormula", StringFormat.replaceNull(columnSetEditor.getEditorRefFormula()));
			doc.saveDoc();
		}	
    }
}
