package com.framework.common.base;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.sqlMap.QueryParam;
import com.sqlMap.jdbc.JdbcFactory;
import com.sqlMap.jdbc.JdbcTmplt;
import com.sqlMap.jdbc.QueryTmplt;

public class JdbcBaseDao {
	private JdbcTmplt jdbcTmplt=null;
	protected QueryParam queryParam=new QueryParam();
	
	public JdbcBaseDao(){
		
	}
	public JdbcTmplt getJdbcTmplt() throws SQLException {
		if(jdbcTmplt==null)
			jdbcTmplt=JdbcFactory.buildJdbcTmplt();
		return jdbcTmplt;
	}

	public void setJdbcTmplt(JdbcTmplt jdbcTmplt) {
		this.jdbcTmplt = jdbcTmplt;
	}
	public Object[] getArray(String sql) throws SQLException{
		if(jdbcTmplt==null)
			jdbcTmplt=JdbcFactory.buildJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		return queryTmplt.getArray(sql);
	}
	public Object getObject(String sql,Map params,String returnClass,String resultMap) throws SQLException{
		if(jdbcTmplt==null)
			jdbcTmplt=JdbcFactory.buildJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		return queryTmplt.getObject(sql, params, returnClass, resultMap);
	}
	public Object getObject(String sql,Object parameterClass,String returnClass,String resultMap) throws SQLException{
		if(jdbcTmplt==null)
			jdbcTmplt=JdbcFactory.buildJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		return queryTmplt.getObject(sql, parameterClass, returnClass, resultMap);
	}
	public List getList(String sql) throws SQLException{
		if(jdbcTmplt==null)
			jdbcTmplt=JdbcFactory.buildJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		List list=queryTmplt.iterator(sql);
		return list;
	}
	public List getList(String sql,String returnClass,String resultMap) throws SQLException{
		if(jdbcTmplt==null)
			jdbcTmplt=JdbcFactory.buildJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		List list=queryTmplt.iterator(sql, returnClass, resultMap);
		return list;
	}
	public List getList(String sql,Map params,String returnClass,String resultMap) throws SQLException{
		if(jdbcTmplt==null)
			jdbcTmplt=JdbcFactory.buildJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		List list=queryTmplt.iterator(sql, params, returnClass, resultMap);		
		return list;
	}
	public List getList(String sql,Object parameterClass,String returnClass,String resultMap) throws SQLException{
		if(jdbcTmplt==null)
			jdbcTmplt=JdbcFactory.buildJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		List list=queryTmplt.iterator(sql, parameterClass, returnClass, resultMap);
		return list;
	}
	public List getQueryForList(String sql,Object obj,String returnClass,String resultMap,int firstResult,int maxResult,int pageSize) throws SQLException{
		if(jdbcTmplt==null)
			jdbcTmplt=JdbcFactory.buildJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();				
		queryParam.setFirstResult(firstResult);
		queryParam.setMaxResult(maxResult);
		queryParam.setPageSize(pageSize);
		queryTmplt.setQueryParam(queryParam);
		List list=queryTmplt.queryForList(sql, obj, returnClass, resultMap);
		queryParam=queryTmplt.getQueryParam();
		return list;
	}	
	public void execute(String sql) throws SQLException{
		if(jdbcTmplt==null)
			jdbcTmplt=JdbcFactory.buildJdbcTmplt();
		jdbcTmplt.execute(sql);
	}
	public void execute(String sql,Map params,String resultMap) throws SQLException{
		if(jdbcTmplt==null)
			jdbcTmplt=JdbcFactory.buildJdbcTmplt();
		jdbcTmplt.execute(sql, params, resultMap);
	}
	public void execute(String sql,Object parameterClass,String resultMap) throws SQLException{
		if(jdbcTmplt==null)
			jdbcTmplt=JdbcFactory.buildJdbcTmplt();
		jdbcTmplt.execute(sql, parameterClass, resultMap);		
	}
	public void close() throws SQLException{
		this.jdbcTmplt.close();
	}
}
