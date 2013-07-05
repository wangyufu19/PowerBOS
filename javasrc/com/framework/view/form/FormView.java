package com.framework.view.form;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;

import com.framework.common.util.XMLUtil;
import com.framework.view.util.StringFormat;
/**
 * 表单组件类
 * @author wangyf
 * @version 1.0
 */
public class FormView {
	private String columnDoc;
	private String loadColumnSetStyle;
	
	public FormView(){
		
	}	
	public String getColumnDoc() {
		return columnDoc;
	}
	public void setColumnDoc(String columnDoc) {
		this.columnDoc = columnDoc;
	}
	public String getLoadColumnSetStyle() {
		return loadColumnSetStyle;
	}
	public void setLoadColumnSetStyle(String loadColumnSetStyle) {
		this.loadColumnSetStyle = loadColumnSetStyle;
	}
	public String render(){
		StringBuilder buf=new StringBuilder();
		buf.append("<table ");
		buf.append("class="+"\"tlistbody\" ");
		buf.append("width="+"\"100%\" ");
		buf.append("border="+"\"0\" ");
		buf.append("align="+"\"center\" ");
		buf.append("cellPadding="+"\"0\" ");
		buf.append("cellSpacing="+"\"1\">\n");
		buf.append(this.renderFormRow());
		buf.append("</table>\n");			
		return buf.toString();
	}
	public String renderFormRow(){
		StringBuilder buf=new StringBuilder();
		if(columnDoc==null) return "表单组件列文档格式出错!";
		XMLUtil XMLUtil=new XMLUtil();
		Document doc=null;				
		doc=XMLUtil.parse(columnDoc);
		if(doc==null) return "表单组件列文档格式出错!";
		Element rootE=doc.getRootElement();
		if(rootE==null) return "表单组件列文档格式出错!";
		List list=null;		
		list=rootE.getChildren("column");
		if(list==null) return "表单组件列文档格式出错!";
		if(list.size()<1) return "表单组件单元格文档为空!";		
		int no=1;					
		for(int i=0;i<list.size();i++){
			Element colE=(Element)list.get(i);		
			if("single".equals(this.loadColumnSetStyle)){
				buf.append("<tr>\n");
			    buf.append(this.renderFormCell(colE));
				buf.append("</tr>\n");
			}else if("double".equals(this.loadColumnSetStyle)){
				if("1".equals(StringFormat.replaceNull(colE.getAttributeValue("rowspan")))){
					buf.append("<tr>\n");
					buf.append(this.renderFormCell(colE));	
					buf.append("</tr>\n");	
					no=1;
				}else{
					if(no%2==0){							
						buf.append(this.renderFormCell(colE));
						buf.append("</tr>\n");							
						no=1;
					}else{			
						buf.append("<tr>\n");
						buf.append(this.renderFormCell(colE));		
						no++;		
					}
				}					
			}						
		}
		return buf.toString();
	}
	public String renderFormCell(Element colE){
		StringBuilder buf=new StringBuilder();		
		String labelName=StringFormat.replaceNull(colE.getAttributeValue("name"));
		buf.append("<td ");	
		if(!"".equals(StringFormat.replaceNull(colE.getAttributeValue("rowspan")))){
			buf.append("rowspan=\""+StringFormat.replaceNull(colE.getAttributeValue("rowspan"))+"\" ");	
		}
		buf.append("class="+"\"tabletitle\"");
		buf.append(">");
		String isnull=StringFormat.replaceNull(colE.getAttributeValue("isnull"));
		if("true".equals(isnull)){
			buf.append("<span class=\"star\">*");
		    buf.append("</span>");
		}
		buf.append(labelName+"&nbsp;");
	    buf.append("</td>");
		buf.append("\n");			
		buf.append("<td ");
		if(!"".equals(StringFormat.replaceNull(colE.getAttributeValue("colspan")))){
			buf.append("colspan=\""+StringFormat.replaceNull(colE.getAttributeValue("colspan"))+"\" ");		
		}else if(!"".equals(StringFormat.replaceNull(colE.getAttributeValue("rowspan")))){
			buf.append("rowspan=\""+StringFormat.replaceNull(colE.getAttributeValue("rowspan"))+"\" ");	
		}									
		buf.append("class="+"\"tabletxt\"");
		buf.append(">");	
		buf.append(StringFormat.replaceNull(colE.getText()));
		buf.append("</td>");
		buf.append("\n");		
		return buf.toString();
	}	
}
