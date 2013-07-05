package com.framework.view.data;
import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Iterator;
import org.jdom.Element;
import com.framework.common.servlet.http.RequestHash;
import com.framework.common.util.SysConstants;
import com.framework.view.adapter.ColumnDataReader;
import com.framework.view.adapter.ColumnEditorObject;
import com.framework.view.adapter.ColumnObject;
import com.framework.view.adapter.ToolbarObject;
import com.framework.view.adapter.ToolbarReader;
import com.framework.view.form.CheckBoxTag;
import com.framework.view.form.HiddenTag;
import com.framework.view.form.LinkTag;
import com.framework.view.form.PasswordTag;
import com.framework.view.form.SelectTag;
import com.framework.view.form.TextAreaTag;
import com.framework.view.form.TextTag;
import com.framework.view.form.property.EventProperty;
import com.framework.view.form.property.ValidateProperty;
import com.framework.view.toolbar.HandleToolBar;
import com.framework.view.util.DataFormat;
import com.framework.view.util.StringFormat;
import com.framework.view.util.WidgetException;
/**
 * 数据文档类
 * @author wangyf
 * @version 1.0
 */
public class DataDocument {
	private RequestHash reh;
	
	public DataDocument(RequestHash reh){
		this.reh=reh;
	}	
	
	public String getLinkToolbar(Element gridE,String loadLinkToolbar){
		StringBuilder buf=new StringBuilder();
		if(gridE==null) return buf.toString();
		buf.append("<?xml version=\"1.0\" encoding=\""+SysConstants.charset_code+"\"?>\n");
		buf.append("<toolbars>\n");		
		if("true".equals(loadLinkToolbar)){
			ToolbarReader reader=new ToolbarReader(reh);
			List list=reader.readToolbar(gridE);			
			if(list==null) return buf.append("</toolbars>").toString();
			for(int i=0;i<list.size();i++){
				ToolbarObject toolbar=(ToolbarObject)list.get(i);
				buf.append("<toolbar name=\""+toolbar.getName()+"\" href=\""+DataFormat.xmlEncode(toolbar.getHref())+"\">");
				buf.append("</toolbar>\n");
			}			
		}		
		buf.append("</toolbars>\n");			
		return buf.toString();
	}
	public String getHandleToolbar(Element gridE,String loadHandleToolbar){
		StringBuilder buf=new StringBuilder();
		if(gridE==null) return buf.toString();
		buf.append("<?xml version=\"1.0\" encoding=\""+SysConstants.charset_code+"\"?>\n");
		buf.append("<toolbars>\n");		
		if("true".equals(loadHandleToolbar)){
			ToolbarReader reader=new ToolbarReader(reh);	
			List list=reader.readHandleToolbar(gridE);			
			if(list==null) return buf.append("</toolbars>").toString();		
			for(int i=0;i<list.size();i++){
				ToolbarObject toolbar=(ToolbarObject)list.get(i);
				buf.append("<toolbar name=\""+toolbar.getName()+"\" value=\""+DataFormat.xmlEncode(toolbar.getValue())+"\" href=\""+DataFormat.xmlEncode(toolbar.getHref())+"\">");
				buf.append("</toolbar>\n");
			}
		}		
		buf.append("</toolbars>\n");			
		return buf.toString();
	}
	public String getButtonToolbar(Element formE){
		StringBuilder buf=new StringBuilder();
		if(formE==null) return buf.toString();
		ToolbarReader reader=new ToolbarReader(reh);
		List list=reader.readToolbar(formE);	
		buf.append("<?xml version=\"1.0\" encoding=\""+SysConstants.charset_code+"\"?>\n");
		buf.append("<toolbars>\n");
		if(list==null) return buf.append("</toolbars>").toString();
		for(int i=0;i<list.size();i++){
			ToolbarObject toolbar=(ToolbarObject)list.get(i);
			buf.append("<toolbar name=\""+toolbar.getName()+"\" onclick=\""+DataFormat.xmlEncode(toolbar.getOnclick())+"\">");
			buf.append("</toolbar>\n");
		}
		buf.append("</toolbars>\n");			
		return buf.toString();
	}
	public String getSearchButtonToolbar(Element searchE){
		StringBuilder buf=new StringBuilder();
		if(searchE==null) return buf.toString();
		buf.append("<?xml version=\"1.0\" encoding=\""+SysConstants.charset_code+"\"?>\n");
		buf.append("<toolbars>\n");
		buf.append("<toolbar name=\"查询\" onclick=\"javascript:queryData("+reh.getSessionHash().get("FORM")+")\">");
		buf.append("</toolbar>\n");
		buf.append("<toolbar name=\"重置\" onclick=\"javascript:"+reh.getSessionHash().get("FORM")+".reset();\">");
		buf.append("</toolbar>\n");
		buf.append("</toolbars>\n");	
		return buf.toString();
	}
	public String getGridColumn(Map columnModels){
		StringBuilder buf=new StringBuilder();		
		buf.append("<?xml version=\"1.0\" encoding=\""+SysConstants.charset_code+"\"?>\n");
		buf.append("<columnSet>\n");		
		if(columnModels==null) return buf.append("</columnSet>").toString();
		for(Iterator it=columnModels.keySet().iterator();it.hasNext();){
			String key=it.next().toString();
			ColumnObject column=(ColumnObject)columnModels.get(key);	
			if("checkbox".equals(column.getDocType())){
				String checkboxDoc="";
				StringBuilder tmpBuf=new StringBuilder();
				tmpBuf.append("<input ");
				tmpBuf.append("name="+"\""+column.getName()+"_all"+"\" ");
				tmpBuf.append("type="+"\""+"checkbox"+"\" ");
				tmpBuf.append("id="+"\""+column.getId()+"_all"+"\" ");
				tmpBuf.append("class="+"\""+"checkbox"+"\" ");			           
				tmpBuf.append("onClick="+"\""+"javascript:selectAllbox("+reh.getCurrentFormName()+"."+column.getId()+"_all,"+reh.getCurrentFormName()+"."+column.getId()+")\" ");
				tmpBuf.append("title="+"\""+"全选"+"\" ");
				tmpBuf.append("value="+"\""+""+"\">\n");
				checkboxDoc=tmpBuf.toString();
				buf.append("<column name=\""+DataFormat.xmlEncode(checkboxDoc)+"\" width=\""+column.getDocWidth()+"\">");
				buf.append("</column>\n");
			}else{
				buf.append("<column name=\""+column.getChineseName()+"\" width=\""+column.getDocWidth()+"\">");
				buf.append("</column>\n");
			}		
		}		
		buf.append("</columnSet>\n");	
		return buf.toString();
	}
	public String getGridColumn(Map columnModels,String loadHandleToolbar){
		StringBuilder buf=new StringBuilder();		
		buf.append("<?xml version=\"1.0\" encoding=\""+SysConstants.charset_code+"\"?>\n");
		buf.append("<columnSet>\n");		
		if(columnModels==null) return buf.append("</columnSet>").toString();
		for(Iterator it=columnModels.keySet().iterator();it.hasNext();){
			String key=it.next().toString();
			ColumnObject column=(ColumnObject)columnModels.get(key);	
			if("checkbox".equals(column.getDocType())){
				String checkboxDoc="";
				StringBuilder tmpBuf=new StringBuilder();
				tmpBuf.append("<input ");
				tmpBuf.append("name="+"\""+column.getName()+"_all"+"\" ");
				tmpBuf.append("type="+"\""+"checkbox"+"\" ");
				tmpBuf.append("id="+"\""+column.getId()+"_all"+"\" ");
				tmpBuf.append("class="+"\""+"checkbox"+"\" ");			           
				tmpBuf.append("onClick="+"\""+"javascript:selectAllbox("+reh.getCurrentFormName()+"."+column.getId()+"_all,"+reh.getCurrentFormName()+"."+column.getId()+")\" ");
				tmpBuf.append("title="+"\""+"全选"+"\" ");
				tmpBuf.append("value="+"\""+""+"\">\n");
				checkboxDoc=tmpBuf.toString();
				buf.append("<column name=\""+DataFormat.xmlEncode(checkboxDoc)+"\" width=\""+column.getDocWidth()+"\">");
				buf.append("</column>\n");
			}else{
				buf.append("<column name=\""+column.getChineseName()+"\" width=\""+column.getDocWidth()+"\">");
				buf.append("</column>\n");
			}		
		}
		if("true".equals(loadHandleToolbar)){
			buf.append("<column name=\"操作\">");
			buf.append("</column>\n");
		}
		buf.append("</columnSet>\n");			
		return buf.toString();
	}
	public String getGridRow(Map columnModels,List results) throws WidgetException{		
		StringBuilder buf=new StringBuilder();	
		buf.append("<?xml version=\"1.0\" encoding=\""+SysConstants.charset_code+"\"?>\n");
		buf.append("<rowSet>\n");	
		if(columnModels==null) return buf.append("</rowSet>\n").toString();		
		if(results==null) return buf.append("</rowSet>\n").toString();
		ColumnDataReader columnDataReader=new ColumnDataReader(reh);		
		try {
			for(int i=0;i<results.size();i++){
				Object obj=results.get(i);
				buf.append("<row>\n");
				for(Iterator it=columnModels.keySet().iterator();it.hasNext();){
					String key=it.next().toString();
					ColumnObject column=(ColumnObject)columnModels.get(key);	
					column=columnDataReader.read(column, obj);				
					ColumnEditorObject columnEditorObject=column.getColumnEditorObject();				
					buf.append("<column key=\""+column.getKey()+"\" isEditor=\""+column.getIsEditor()+"\">");
					if("true".equals(column.getIsEditor())){						
						buf.append("<editor editorDocType=\""+columnEditorObject.getEditorDocType()+"\"/>");
					}										
					buf.append(this.getCellDataTag(column, obj));
					if("checkbox".equals(column.getDocType())){		
						column.setId("pid");
						column.setName("pid");
						column.setDocType("hidden");		
						HiddenTag hiddenTag=new HiddenTag();
						hiddenTag.setId(column.getId());
						hiddenTag.setName(column.getName());
						hiddenTag.setValue(column.getValue());
						buf.append(DataFormat.xmlEncode(hiddenTag.render()));									
					}
					buf.append("</column>\n");						
				}						
				buf.append("</row>\n");
			}
		} catch (Exception e) {					
			throw new WidgetException("网格面板加载单元格文档数据失败",e);
		}	
		buf.append("</rowSet>\n");				
		return buf.toString();
	}
	public String getGridRow(Map columnModels,List results,String gridHandleToolbarDoc,String loadHandleToolbar) throws SQLException{		
		StringBuilder buf=new StringBuilder();	
		buf.append("<?xml version=\"1.0\" encoding=\""+SysConstants.charset_code+"\"?>\n");
		buf.append("<rowSet>\n");	
		if(columnModels==null) return buf.append("</rowSet>\n").toString();		
		if(results==null) return buf.append("</rowSet>\n").toString();
		ColumnDataReader columnDataReader=new ColumnDataReader(reh);		
		HandleToolBar handleToolBar=new HandleToolBar(gridHandleToolbarDoc);
		gridHandleToolbarDoc=handleToolBar.render();				
		for(int i=0;i<results.size();i++){
			Object obj=results.get(i);
			buf.append("<row>\n");
			for(Iterator it=columnModels.keySet().iterator();it.hasNext();){
				String key=it.next().toString();
				ColumnObject column=(ColumnObject)columnModels.get(key);	
				column=columnDataReader.read(column, obj);
				buf.append("<column key=\""+column.getKey()+"\" isnull=\""+column.getIsNull()+"\">");
				buf.append(this.getCellDataTag(column, obj));
				buf.append("</column>\n");			
			}			
			if("true".equals(loadHandleToolbar)){
				buf.append("<column>");
				buf.append(DataFormat.xmlEncode(columnDataReader.formatRequestValue(columnDataReader.formatBeanValue(obj, gridHandleToolbarDoc))));
				buf.append("</column>\n");	
			}			
			buf.append("</row>\n");
		}
		buf.append("</rowSet>\n");				
		return buf.toString();
	}
	public String getFormColumn(Map columnModels,Object obj) throws WidgetException{
		StringBuilder buf=new StringBuilder();	
		buf.append("<?xml version=\"1.0\" encoding=\""+SysConstants.charset_code+"\"?>\n");
		buf.append("<columnSet>\n");	
		if(columnModels==null) return buf.append("</columnSet>\n").toString();			
		ColumnDataReader columnDataReader=new ColumnDataReader(reh);		
		try {
			for(Iterator it=columnModels.keySet().iterator();it.hasNext();){
				String key=it.next().toString();
				ColumnObject column=(ColumnObject)columnModels.get(key);		
				column=columnDataReader.read(column, obj);
				buf.append("<column name=\""+column.getChineseName()+"\" ");
				buf.append("isnull=\""+column.getIsNull()+"\" ");
				buf.append("rolspan=\""+column.getRowspan()+"\" ");
				buf.append("colspan=\""+column.getColspan()+"\">");				
				buf.append(this.getCellDataTag(column, obj));				
				buf.append("</column>\n");			
			}	
		} catch (Exception e) {
			throw new WidgetException("表单面板加载单元格文档数据失败",e);
		}
		buf.append("</columnSet>\n");			
		return buf.toString();
	}
	public String getCellDataTag(ColumnObject column,Object obj) throws SQLException{
		StringBuilder buf=new StringBuilder();				
		if("hidden".equals(column.getDocType())){
			HiddenTag hiddenTag=new HiddenTag();
			hiddenTag.setId(column.getId());
			hiddenTag.setName(column.getName());
			hiddenTag.setValue(column.getValue());		
			if(!"".equals(StringFormat.replaceNull(column.getDocExtend()))){
				buf.append(DataFormat.xmlEncode(hiddenTag.render()+StringFormat.replaceNull(column.getDocExtend())+"&nbsp;"));
			}else{
				buf.append(DataFormat.xmlEncode(hiddenTag.render()+column.getValue()+"&nbsp;"));
			}			
		}else if("checkbox".equals(column.getDocType())){
			CheckBoxTag checkBoxTag=new CheckBoxTag();
			checkBoxTag.setId(column.getId());
			checkBoxTag.setName(column.getName());
			checkBoxTag.setValue(column.getValue());
			buf.append(DataFormat.xmlEncode(checkBoxTag.render()));
		}else if("text".equals(column.getDocType())){
			TextTag textTag=new TextTag();
			textTag.setId(column.getId());
			textTag.setName(column.getName());
			textTag.setValue(column.getValue());
			textTag.setWidth(column.getDocWidth());
			textTag.setReadonly(column.getIsReadonly());
			textTag.setExtend(column.getDocExtend());
			ValidateProperty validatePro=new ValidateProperty();
			validatePro.setDataType(column.getDataType());
			validatePro.setIsNull(column.getIsNull());
			validatePro.setMaxLength(column.getDocMaxlength());
			validatePro.setAlterMsg(column.getChineseName());
			textTag.setValidateProperty(validatePro);
			EventProperty eventPro=new EventProperty();
			eventPro.setOnclick(column.getDocOnclick());
			eventPro.setOnblur(column.getDocOnblur());
			textTag.setEventProperty(eventPro);
			buf.append(DataFormat.xmlEncode(textTag.render()));
		}else if("password".equals(column.getDocType())){
			PasswordTag passwordTag=new PasswordTag();			
			passwordTag.setId(column.getId());
			passwordTag.setName(column.getName());
			passwordTag.setValue(column.getValue());
			passwordTag.setClassStyle(column.getDocCss());
			passwordTag.setExtend(column.getDocExtend());
			ValidateProperty validatePro=new ValidateProperty();
			validatePro.setDataType(column.getDataType());
			validatePro.setIsNull(column.getIsNull());
			validatePro.setMaxLength(column.getDocMaxlength());
			validatePro.setAlterMsg(column.getChineseName());
			passwordTag.setValidateProperty(validatePro);
			EventProperty eventPro=new EventProperty();
			eventPro.setOnclick(column.getDocOnclick());
			eventPro.setOnblur(column.getDocOnblur());
			passwordTag.setEventProperty(eventPro);
			buf.append(DataFormat.xmlEncode(passwordTag.render()));
		}else if("select".equals(column.getDocType())){
			SelectTag selectTag=new SelectTag();
			selectTag.setId(column.getId());
			selectTag.setName(column.getName());
			selectTag.setValue(column.getValue());
			selectTag.setDataset(column.getOption());
			selectTag.setExtend(column.getDocExtend());
			ValidateProperty validatePro=new ValidateProperty();
			validatePro.setDataType(column.getDataType());
			validatePro.setIsNull(column.getIsNull());
			validatePro.setMaxLength(column.getDocMaxlength());
			validatePro.setAlterMsg(column.getChineseName());
			selectTag.setValidateProperty(validatePro);
			buf.append(DataFormat.xmlEncode(selectTag.render()));
		}else if("textarea".equals(column.getDocType())){
			TextAreaTag textAreaTag=new TextAreaTag();
			textAreaTag.setId(column.getId());
			textAreaTag.setName(column.getName());
			textAreaTag.setValue(column.getValue());
			textAreaTag.setRows(column.getDocHeight());
			textAreaTag.setExtend(column.getDocExtend());
			buf.append(DataFormat.xmlEncode(textAreaTag.render()));
		}else if("href".equals(column.getDocType())){
			LinkTag linkTag=new LinkTag();
			linkTag.setId(column.getId());			
			linkTag.setValue(column.getValue());
			linkTag.setHref(column.getDocHref());
			EventProperty eventPro=new EventProperty();
			eventPro.setOnclick(column.getDocOnclick());
			buf.append(DataFormat.xmlEncode(linkTag.render()));
		}
		return buf.toString();
	}
}
