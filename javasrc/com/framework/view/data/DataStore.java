package com.framework.view.data;
import java.sql.SQLException;
import java.util.List;
import org.jdom.Element;

import com.framework.common.servlet.http.RequestHash;
import com.framework.view.data.FormData;
import com.framework.view.data.GridData;
import com.framework.view.util.StringFormat;
import com.framework.view.util.WidgetException;
import com.powerbosframework.log.LogFactory;
import com.sqlMap.QueryParam;

/**
 * 数据存储类
 * @author wangyf
 * @version 1.0
 */
public class DataStore {	
	private RequestHash reh;	
	private QueryParam queryParam;
	
	public DataStore(RequestHash reh){
		this.reh=reh;
	}
	
	public QueryParam getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(QueryParam queryParam) {
		this.queryParam = queryParam;
	}
	public List loadGridData(Element gridE,String loadDataSetStyle,String loadPageToolbar,String pageFetchSize) throws WidgetException{
		List list=null;
		if(gridE==null) return list;
		Element dataSetE=gridE.getChild("DATASET");
		if(dataSetE==null) return list;					
		if("statement".equals(loadDataSetStyle)){
			Element statementE=dataSetE.getChild("STATEMENT");		
			GridData gridData=new GridData(reh);
			gridData.setPagingFetchData(loadPageToolbar);
			gridData.setPagingFetchSize(pageFetchSize);
			try {
				list=gridData.loadGridData(statementE);
			} catch (SQLException e) {				
				throw new WidgetException("网格面板加载数据集失败",e);
			}
			this.setQueryParam(gridData.getQueryParam());
		}else if("interface".equals(loadDataSetStyle)){
			Element interfaceE=dataSetE.getChild("INTERFACE");
		}
		return list;
	}		
	public Object loadFormData(Element formE,String loadDataSetStyle) throws WidgetException{
		Object obj=null;
		if(formE==null) return null;
		Element dataSetE=formE.getChild("DATASET");
		if(dataSetE==null) return null;		
		if("statement".equals(loadDataSetStyle)){
			Element statementE=dataSetE.getChild("STATEMENT");		
			if(statementE==null) return null;
			FormData formData=new FormData(reh);
			formData.setParameterClass(StringFormat.replaceNull(statementE.getAttributeValue("parameterClass")));
			formData.setReturnClass(StringFormat.replaceNull(statementE.getAttributeValue("returnClass")));
			formData.setResultMap(StringFormat.replaceNull(statementE.getAttributeValue("resultMap")));				
			try {
				obj=formData.loadFormData(statementE);
			} catch (SQLException e) {							
				throw new WidgetException("表单面板加载数据集失败",e);				
			}			
		}else if("interface".equals(loadDataSetStyle)){
			Element interfaceE=dataSetE.getChild("INTERFACE");
		}
		
		return obj;
	}
}
