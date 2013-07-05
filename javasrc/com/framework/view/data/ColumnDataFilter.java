package com.framework.view.data;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jdom.Element;
import com.framework.common.servlet.http.RequestHash;
import com.framework.view.adapter.ColumnReader;
import com.framework.view.util.WidgetException;
import com.sqlMap.jdbc.JdbcTmplt;
import com.sqlMap.jdbc.QueryTmplt;
/**
 * 列数据过滤器
 * @author wangyf
 * @version 1.0
 */
public class ColumnDataFilter {	
	private RequestHash reh;
	private String isFilter;
	private String isSort;
	
	public ColumnDataFilter(RequestHash reh){
		this.reh=reh;
	}
	public String getIsFilter() {
		return isFilter;
	}
	public void setIsFilter(String isFilter) {
		this.isFilter = isFilter;
	}
	public String getIsSort() {
		return isSort;
	}
	public void setIsSort(String isSort) {
		this.isSort = isSort;
	}
	public Map getFilterColumn(Element e1,String diyCode) throws WidgetException{
		if(e1==null) return null;
		Element columnSetE=e1.getChild("COLUMNSET");
		if(columnSetE==null) return null;					
		ColumnReader reader=new ColumnReader(reh);
		Map columns=reader.read(columnSetE);	
		if(isFilter==null) return columns;
		if("".equals(isFilter)) return columns;
		if("false".equals(isFilter)) return columns;
		if(columns==null) return columns;
		if(columns.size()<1) return columns;		
		Map diycolumns=new LinkedHashMap();
//		String sql="select * from bos_t_diy_display where user_id=$userId$ and diy_code=$diyCode$ order by id";
//		String returnClass="com.powerbos.application.support.domain.DiyDisplay";
//		String resultMap="diyDisplayResult";
//		DiyDisplay vo=new DiyDisplay();
//		vo.setUserId(Long.valueOf(reh.getLoginInfo().getUserId()));
//		vo.setDiyCode(diyCode);
//		JdbcTmplt jdbcTmplt=reh.getJdbcTmplt();
//		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
//		List list=null;
//		try {
//			list = queryTmplt.iterator(sql, vo, returnClass, resultMap);
//		} catch (SQLException e) {			
//			throw new WidgetException("加载列文档数据失败",e);
//		}
//		if(list==null) return columns;	
//		if(list.size()<1) return columns;	
//		for(int i=0;i<list.size();i++){
//			DiyDisplay diyDisplay=(DiyDisplay)list.get(i);			
//			if(columns.containsKey(diyDisplay.getDiyColumn())){
//				diycolumns.put(diyDisplay.getDiyColumn(), columns.get(diyDisplay.getDiyColumn()));				
//			}
//		}
		return diycolumns;
	}
//	public void getDiySortColumn(String diyCode) throws SQLException{
//		if(isSort==null) return;
//		if("".equals(isSort)) return;
//		if("false".equals(isSort)) return;
//		String sortCol=null;
//		String sql="select * from bos_t_diy_sort where user_id=$userId$ and diy_code=$diyCode$";
//		String returnClass="com.powerbos.application.support.domain.DiySort";
//		String resultMap="diySortResult";
//		DiySort vo=new DiySort();
//		vo.setUserId(Long.valueOf(reh.getLoginInfo().getUserId()));
//		vo.setDiyCode(diyCode);
//		JdbcTmplt jdbcTmplt=reh.getJdbcTmplt();
//		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
//		Object obj=queryTmplt.getObject(sql, vo, returnClass, resultMap);
//		if(obj==null) return;
//		sortCol=String.valueOf(((DiySort)obj).getSortColumn());
//		reh.getSessionHash().put("ORDERBY", sortCol);
//	}
	
	
}
