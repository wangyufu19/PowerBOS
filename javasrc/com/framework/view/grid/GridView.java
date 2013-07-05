package com.framework.view.grid;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import com.framework.common.servlet.http.RequestHash;
import com.framework.common.util.XMLUtil;
import com.framework.view.util.StringFormat;
/**
 * 网格组件类
 * @author wangyf
 * @version 1.0
 */
public class GridView {	
	private RequestHash reh;
	private String columnDoc;	
	private String rowDoc;
	
	public GridView(RequestHash reh){
		this.reh=reh;	
	}	
	public String getColumnDoc() {
		return columnDoc;
	}
	public void setColumnDoc(String columnDoc) {
		this.columnDoc = columnDoc;
	}	
	public String getRowDoc() {
		return rowDoc;
	}
	public void setRowDoc(String rowDoc) {
		this.rowDoc = rowDoc;
	}
	public String render(){
		StringBuilder buf=new StringBuilder();	
		buf.append("<table ");
		buf.append("id="+"\"L0\" ");
		buf.append("class="+"\"tlistbody\" ");
		buf.append("width="+"\"100%\" ");
		buf.append("border="+"\"0\" ");
		buf.append("align="+"\"center\" ");
		buf.append("cellPadding="+"\"0\" ");
		buf.append("cellSpacing="+"\"1\">\n");			
		buf.append(this.getGridColumn());
		buf.append(this.getGridRow());
		buf.append("</table>\n");	
		return buf.toString();
	}
	public String getGridColumn(){
		StringBuilder buf=new StringBuilder();		
		if(columnDoc==null) return "网格组件列文档格式出错!";
		XMLUtil XMLUtil=new XMLUtil();
		Document doc=null;
		doc=XMLUtil.parse(columnDoc);
		if(doc==null) return "网格组件列文档格式出错!";
		Element rootE=doc.getRootElement();
		if(rootE==null) return "网格组件列文档格式出错!";
		List list=null;
		list=rootE.getChildren("column");
		if(list==null) return "网格组件列文档格式出错!";
		if(list.size()<1) return "网格组件单元格文档为空!";
		buf.append("<thead>\n");	
		buf.append("<tr class="+"\"tablehead\">\n");	
		for(int i=0;i<list.size();i++){
			Element headE=(Element)list.get(i);
			if("".equals(StringFormat.replaceNull(headE.getAttributeValue("width")))){
				buf.append("<th>");			
			}else{
				buf.append("<th width=\""+StringFormat.replaceNull(headE.getAttributeValue("width"))+"\">");			
			}			
			buf.append(StringFormat.replaceNull(headE.getAttributeValue("name")));
			buf.append("</th>\n");	
		}
		buf.append("</tr>\n");
		buf.append("</thead>\n");
		return buf.toString();
	}
	public String getGridRow(){
		StringBuilder buf=new StringBuilder();		
		if(rowDoc==null) return "网格组件行文档格式出错!";
		XMLUtil XMLUtil=new XMLUtil();
		Document doc=null;
		doc=XMLUtil.parse(rowDoc);
		if(doc==null) return "网格组件行文档格式出错!";
		Element rootE=doc.getRootElement();
		if(rootE==null) return "网格组件行文档格式出错!";
		List list=null;
		List list1=null;
		list=rootE.getChildren("row");
		if(list==null) return "网格组件行文档格式出错!";
		buf.append("<tbody>\n");		
		for(int i=0;i<list.size();i++){			
			Element rowE=(Element)list.get(i);
			String css="rowOdd";
			if(i%2==1){
				css="rowEven";
			}
			buf.append("<tr ");
			buf.append("class="+"\""+css+"\" ");
			buf.append("onMouseOver="+"\""+"this.className="+"\'"+"rowHover"+"\'"+"\" ");
			buf.append("onmouseout="+"\""+"this.className="+"\'"+css+"\'"+"\"");
			buf.append(">\n");	
			list1=rowE.getChildren();			
			if(list1==null) list1=new ArrayList();		
			for(int j=0;j<list1.size();j++){		
				Element cellE=(Element)list1.get(j);		
				String key=StringFormat.replaceNull(cellE.getAttributeValue("key"));		
				String isEditor=StringFormat.replaceNull(cellE.getAttributeValue("isEditor"));	
				Element editorE=cellE.getChild("editor");
				if(editorE!=null){
					String editorDocType=StringFormat.replaceNull(editorE.getAttributeValue("editorDocType"));					
					buf.append("<td key=\""+key+"\" isEditor=\""+isEditor+"\" docType=\""+editorDocType+"\">");			
				}else{
					buf.append("<td>");			
				}							
				buf.append(cellE.getText());				
				buf.append("</td>\n");				
			}
			buf.append("</tr>\n");	
		}
		buf.append("</tbody>\n");	
		return buf.toString();
	}	
}
