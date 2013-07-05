package com.framework.view.data;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.sqlMap.QueryParam;
import com.sqlMap.jdbc.JdbcTmplt;
import com.sqlMap.jdbc.QueryTmplt;

/**
 * 数据集加载类
 * @author wangyf
 * @version 1.0
 */
public class DataResult {
	private JdbcTmplt jdbcTmplt;
	private QueryParam queryParam=new QueryParam();
	
	public DataResult(JdbcTmplt jdbcTmplt){
		this.jdbcTmplt=jdbcTmplt;
	}	
	public QueryParam getQueryParam() {
		return queryParam;
	}
//	public void setQueryParam(QueryParam queryParam) {
//		this.queryParam = queryParam;
//	}
	/**
	 * 得到查询数据集
	 * @param fetchDataStyle
	 * @param currentPage
	 * @param fetchDataSize
	 * @param sql
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws SQLException 
	 */
	public List getDataResults(String pagingFetchData,String currentPage,String pagingFetchSize,String sql,String returnClass,String resultMap) throws SQLException{
		List results=new ArrayList();
		if("".equals(currentPage))
			currentPage="1";	
		int firstResult=0;
		int maxResult=0;
		if(currentPage.equals("1"))
			firstResult=1;
		else
			firstResult=Integer.parseInt(pagingFetchSize)*(Integer.parseInt(currentPage)-1)+1;
		maxResult=Integer.parseInt(pagingFetchSize)*Integer.parseInt(currentPage);			
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		if("true".equals(pagingFetchData)){
			queryParam.setFirstResult(firstResult);
			queryParam.setMaxResult(maxResult);
			queryParam.setPageSize(Integer.parseInt(pagingFetchSize));	
			queryTmplt.setQueryParam(queryParam);
			results=queryTmplt.queryForList(sql,returnClass,resultMap);
			queryParam=queryTmplt.getQueryParam();
			queryParam.setCurrentPage(Integer.parseInt(currentPage));
			if(currentPage.equals("1"))
				queryParam.setMaxResult(results.size());
			else
				queryParam.setMaxResult((queryParam.getCurrentPage()-1)*queryParam.getPageSize()+results.size());	
			if(results.size()==0){
				queryParam.setFirstResult(0);
			}				
		}else
			results=queryTmplt.iterator(sql, returnClass, resultMap);		
		return results;
	}
	/**
	 * 得到查询数据
	 * @param sql
	 * @param parameterClass
	 * @param returnClass
	 * @param resultMap
	 * @return
	 * @throws SQLException
	 */
	public Object getDataResult(String sql,Object parameterClass,String returnClass,String resultMap) throws SQLException{
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();	
		return queryTmplt.getObject(sql, parameterClass, returnClass, resultMap);					
	}	
}
