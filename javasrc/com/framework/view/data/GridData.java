package com.framework.view.data;
import java.sql.SQLException;
import java.util.List;
import org.jdom.Element;

import com.framework.common.servlet.http.RequestHash;
import com.framework.common.util.SysConstants;
import com.framework.view.adapter.ParameterClassReader;
import com.framework.view.adapter.StatementReader;
import com.framework.view.data.DataResult;
import com.framework.view.util.StringFormat;
import com.framework.view.util.WidgetException;
import com.sqlMap.QueryParam;
/**
 * 网络数据类
 * @author wangyf
 * @version 1.0
 */
public class GridData {
	private RequestHash reh;
	private QueryParam queryParam;
	private String sortable="false";//可否排序列模型
	private String pagingFetchData="false";//分页提取记录
	private String pagingFetchSize;//分页提取记录大小	
	
	public GridData(RequestHash reh){
		this.reh=reh;
	}
	public QueryParam getQueryParam() {
		return queryParam;
	}
	public void setQueryParam(QueryParam queryParam) {
		this.queryParam = queryParam;
	}
	
	public String getSortable() {
		return sortable;
	}
	public void setSortable(String sortable) {
		if("".equals(sortable)) return;
		this.sortable = sortable;
	}
	public String getPagingFetchData() {
		return pagingFetchData;
	}
	public void setPagingFetchData(String pagingFetchData) {
		if("".equals(pagingFetchData)||pagingFetchData==null) return;
		this.pagingFetchData = pagingFetchData;
	}
	public String getPagingFetchSize() {
		return pagingFetchSize;
	}
	public void setPagingFetchSize(String pagingFetchSize) {
		if("".equals(pagingFetchSize)||pagingFetchSize==null) return;
		this.pagingFetchSize = pagingFetchSize;
	}	

	public List loadGridData(Element statementE) throws SQLException, WidgetException{
		List list=null;
		StatementReader statementReader=new StatementReader(reh);
		DataResult dataQuery=new DataResult(reh.getJdbcTmplt());
		String parameterClass=StringFormat.replaceNull(statementE.getAttributeValue("parameterClass"));
		String returnClass=StringFormat.replaceNull(statementE.getAttributeValue("returnClass"));
		String resultMap=StringFormat.replaceNull(statementE.getAttributeValue("resultMap"));		
		if("".equals(parameterClass)){
			String sql=statementReader.getStatement(statementE,sortable);	
			list=dataQuery.getDataResults(pagingFetchData, reh.get("currentPage"), pagingFetchSize, sql, returnClass, resultMap);
		}else{
			ParameterClassReader parameterClassReader=new ParameterClassReader(reh);
			Object obj=null;			
			try {
				obj=parameterClassReader.read(statementE);
			} catch (Exception e) {
				throw new WidgetException("网格对象的数据集加载参数类失败!",e);
			}			
			String sql=statementReader.getStatement(statementE, obj, resultMap,sortable);
			list=dataQuery.getDataResults(pagingFetchData, reh.get("currentPage"), pagingFetchSize, sql, returnClass, resultMap);
		}
		this.setQueryParam(dataQuery.getQueryParam());
		return list;
	}	
}
