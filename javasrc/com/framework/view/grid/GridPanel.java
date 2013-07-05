package com.framework.view.grid;
import java.util.List;
import java.util.Map;
import org.jdom.Element;
import com.framework.common.servlet.http.RequestHash;
import com.framework.view.Panel;
import com.framework.view.data.ColumnDataFilter;
import com.framework.view.data.DataDocument;
import com.framework.view.data.DataStore;
import com.framework.view.toolbar.LinkToolBar;
import com.framework.view.toolbar.PagingToolBar;
import com.framework.view.util.WidgetException;
import com.sqlMap.QueryParam;
/**
 * 网格面板组件类
 * @author wangyf
 * @version 1.0
 */
public class GridPanel extends Panel{		
	private RequestHash reh;
	private LinkToolBar linkToolBar;
	public GridView gridView;
	private DataDocument dataDocument;
	private PagingToolBar pagingToolBar;
	private Map columnModels;
	
	public GridPanel(RequestHash reh){
		this.reh=reh;
	}
	public void load(Element gridE) throws WidgetException{			
		//实例化数据模型		
		dataDocument=new DataDocument(reh);		
		//实例化链接工具条		
		linkToolBar=new LinkToolBar(dataDocument.getLinkToolbar(gridE,this.loadLinkToolbar));	
		//实例化网格对象
		gridView=new GridView(reh);		
		//加载网格列文档数据
		ColumnDataFilter columnDataFilter=new ColumnDataFilter(reh);
		columnDataFilter.setIsFilter(this.getFilterable());		
		columnModels=columnDataFilter.getFilterColumn(gridE,reh.get("CODE"));
		gridView.setColumnDoc(dataDocument.getGridColumn(columnModels));		
		//加载网格数据集
		DataStore dataStore=new DataStore(reh);
		List results=dataStore.loadGridData(gridE,this.loadDataSetStyle,this.loadPageToolbar,this.pagingFetchSize);
		//加载网格行文档数据
		gridView.setRowDoc(dataDocument.getGridRow(columnModels,results));
		//加载分页工具条		
		if("true".equals(this.loadPageToolbar)){
			QueryParam queryParam=dataStore.getQueryParam();
			pagingToolBar=new PagingToolBar(reh);			
			if(queryParam!=null){				
				pagingToolBar.setCurrentPage(queryParam.getCurrentPage());
				pagingToolBar.setMaxPage(queryParam.getMaxPage());
				pagingToolBar.setFirstResult(queryParam.getFirstResult());
				pagingToolBar.setMaxResult(queryParam.getMaxResult());
			}	
		}	    
	}	
	public Map getColumnModels(){
		return this.columnModels;
	}	
	public String render(){
		StringBuilder buf=new StringBuilder();
		buf.append("<div class=\"tlist\">\n");   
		buf.append("<table onclick=\"javascript:showHideLayer(this);\" cellSpacing=0 cellPadding=0 width=\"100%\" align=center border=0 class=\"ptitle\">\n")
		   .append("<tbody>\n")
		   .append("<tr>\n")
		   .append("<td class=\"ptitleL\">&nbsp;</td>\n")
		   .append("<td class=\"ptitleC\"><span class=\"ptitletxt\">"+this.getTitle()+"</span></td>\n")
		   .append("<td class=\"ptitleR\" align=right></td>\n")	
		   .append("</tr>\n")
		   .append("</tbody>\n")
		   .append("</table>\n");
		buf.append("<div class=\"tbody\">\n");	
		if(linkToolBar!=null) 
			buf.append(linkToolBar.render());
		if(gridView!=null)
			buf.append(gridView.render());			
		if(pagingToolBar!=null)
			buf.append(pagingToolBar.render());
		buf.append("</div>\n");
		buf.append("</div>\n");
		return buf.toString();
	}
}
