package com.framework.view.form;
import java.util.Map;
import org.jdom.Element;
import com.framework.common.servlet.http.RequestHash;
import com.framework.common.util.SysConstants;
import com.framework.view.Panel;
import com.framework.view.data.ColumnDataFilter;
import com.framework.view.data.DataDocument;
import com.framework.view.data.DataStore;
import com.framework.view.form.FormView;
import com.framework.view.toolbar.ButtonToolbar;
import com.framework.view.util.WidgetException;
import com.powerbosframework.util.StringUtil;
/**
 * 表单面板组件类
 * @author wangyf
 * @version 1.0
 */
public class FormPanel extends Panel{
	private RequestHash reh;
	private FormView formView;
	private DataDocument dataDocument;
	private Map columnModels;
	private ButtonToolbar buttonToolbar;
	
	public FormPanel(RequestHash reh){
		this.reh=reh;
	}
	public Map getColumnModels() {
		return columnModels;
	}

	public void setColumnModels(Map columnModels) {
		this.columnModels = columnModels;
	}
	public void load(Element formE) throws WidgetException{
		//实例化网格数据模型		
		dataDocument=new DataDocument(reh);
		ColumnDataFilter columnDataFilter=new ColumnDataFilter(reh);
		columnDataFilter.setIsFilter("false");		
		columnModels=columnDataFilter.getFilterColumn(formE,reh.get("CODE"));
		//实例化表单视图对象
		formView=new FormView();
		//加载表单数据集
		DataStore dataStore=new DataStore(reh);
		String SHOW_TYPE=StringUtil.replaceNull(reh.getSessionHash().get(SysConstants.show_type));		
		if(SHOW_TYPE.equals(SysConstants.exce_add))
			formView.setColumnDoc(dataDocument.getFormColumn(columnModels, null));	
		else
			formView.setColumnDoc(dataDocument.getFormColumn(columnModels, dataStore.loadFormData(formE,this.loadDataSetStyle)));	
		//设置加载字段集方式
		formView.setLoadColumnSetStyle(this.loadColumnSetStyle);
		//实体化表单按钮工具条
		buttonToolbar=new ButtonToolbar(dataDocument.getButtonToolbar(formE));
	}
	public String render(){
		StringBuilder buf=new StringBuilder();
		buf.append("<div class=\"tview\">\n")
		   .append("<table onclick=\"javascript:showHideLayer(this);\" align=center border=0 cellSpacing=0 cellPadding=0 width=\"100%\" class=\"ptitle\">\n")
		   .append("<tbody>\n")
		   .append("<tr>\n")
		   .append("<td class=\"ptitleL\"></td>\n")
		   .append("<td class=\"ptitleC\"><span class=\"ptitletxt\">"+this.getTitle()+"</span></td>\n")
		   .append("<td class=\"ptitleC\" align=\"right\"></td>\n")
		   .append("<td class=\"ptitleR\">&nbsp;</td>\n")
		   .append("</tr>\n")			 
		   .append("</tbody>\n")
		   .append("</table>\n")
		   .append("<div class=\"tbody\">\n");
		if(formView!=null) 
			buf.append(formView.render());
		if(buttonToolbar!=null)
			buf.append(buttonToolbar.render());
		 buf.append("</div>\n")
		   .append("</div>\n");		
		return buf.toString();
	}
}
