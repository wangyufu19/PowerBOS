package com.framework.view.adapter;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import org.jdom.Element;
import com.framework.common.servlet.http.RequestHash;
import com.framework.view.adapter.ColumnEditorObject;
import com.framework.view.adapter.ColumnObject;
import com.framework.view.util.RequestUtil;
import com.framework.view.util.StringFormat;
/**
 * 列模型读取器类
 * @author wangyf
 * @version 1.0
 */
public class ColumnReader {	
	private RequestHash reh;
	
	public ColumnReader(RequestHash reh){
		this.reh=reh;
	}
	public ColumnObject readColumnObject(Element colE){
		ColumnObject column=new ColumnObject(); 
		if(colE==null) return column;
		column.setKey(StringFormat.replaceNull(colE.getAttributeValue("EID")));				
		//基本属性
		column.setId(StringFormat.replaceNull(colE.getAttributeValue("name")));
		column.setName(StringFormat.replaceNull(colE.getAttributeValue("name")));
		column.setChineseName(StringFormat.replaceNull(colE.getAttributeValue("chineseName")));
		column.setValue(StringFormat.replaceNull(colE.getAttributeValue("value")));
		column.setDataType(StringFormat.replaceNull(colE.getAttributeValue("dataType")));
		column.setDocType(StringFormat.replaceNull(colE.getAttributeValue("docType")));
		column.setDocCss(StringFormat.replaceNull(colE.getAttributeValue("docCss")));
		column.setDocWidth(StringFormat.replaceNull(colE.getAttributeValue("docWidth")));
		column.setDocHeight(StringFormat.replaceNull(colE.getAttributeValue("docHeight")));
		column.setDocMaxlength(StringFormat.replaceNull(colE.getAttributeValue("docMaxlength")));
		//高级属性
		column.setLoadCondition(StringFormat.replaceNull(colE.getAttributeValue("loadCondition")));
		column.setRefFormula(StringFormat.replaceNull(colE.getAttributeValue("refFormula")));
		column.setDocExtend(StringFormat.replaceNull(colE.getAttributeValue("docExtend")));
		column.setIsDisabled(StringFormat.replaceNull(colE.getAttributeValue("isDisabled")));
		column.setIsReadonly(StringFormat.replaceNull(colE.getAttributeValue("isReadonly")));
		column.setIsNull(StringFormat.replaceNull(colE.getAttributeValue("isNull")));
		column.setIsEditor(StringFormat.replaceNull(colE.getAttributeValue("isEditor")));
		//事件属性
		column.setDocHref(StringFormat.replaceNull(colE.getAttributeValue("docHref")));
		column.setDocOnclick(StringFormat.replaceNull(colE.getAttributeValue("docOnclick")));
		column.setDocOnblur(StringFormat.replaceNull(colE.getAttributeValue("docOnblur")));
		//编辑属性
		Element editorE=colE.getChild("EDITOR");
		ColumnEditorObject columnEditorObject=new ColumnEditorObject();		
		if(editorE!=null){						
			columnEditorObject.setEditorDocType(StringFormat.replaceNull(editorE.getAttributeValue("editorDocType")));
			columnEditorObject.setEditorDataType(StringFormat.replaceNull(editorE.getAttributeValue("editorDataType")));
			columnEditorObject.setEditorDocCss(StringFormat.replaceNull(editorE.getAttributeValue("editorDocCss")));
			columnEditorObject.setEditorRefFormula(StringFormat.replaceNull(editorE.getAttributeValue("editorRefFormula")));			
		}
		column.setColumnEditorObject(columnEditorObject);		
		return column;
	}
	public Map read(Element columnSetE){		
		Map columnModels=new LinkedHashMap();
		if(columnSetE==null) return columnModels;
		List list=columnSetE.getChildren("COLUMN");
		if(list==null) return columnModels;		
		boolean bool=true;
		for(int i=0;i<list.size();i++){
			Element colE=(Element)list.get(i);		
			ColumnObject column=this.readColumnObject(colE);
			//解析加载条件
			String loadCondition=StringFormat.replaceNull(column.getLoadCondition());
			if(loadCondition.indexOf("fun.get(")!=-1){
				String replacement=RequestUtil.getRequestParameterName(loadCondition);
			    String target=RequestUtil.getRequestParamNameStr(loadCondition);	 
		        replacement=reh.get(replacement);  
		        loadCondition=StringFormat.replace(loadCondition,target, replacement);	
			}
			if(loadCondition.indexOf("fun.getSession")!=-1){
				loadCondition=RequestUtil.formatSession(reh.getSessionHash(), loadCondition);			
			}			
	        bool=StringFormat.convertOfBoolean(StringFormat.replaceNull(loadCondition)); 	  	      
			if("true".equals(column.getIsDisabled())&&bool)
				columnModels.put(column.getId(), column);	
		}
		return columnModels;
	}
}
