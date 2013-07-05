package com.framework.common.base;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.sqlMap.Query;
import com.sqlMap.QueryPage;
import com.sqlMap.QueryParam;

import com.sqlMap.SqlMapClient;
import com.sqlMap.SqlMapFactory;
/**
 * 功能说明:基础DAO组件类
 * @author wangyf
 * @version 1.0
 */
public class SqlMapBaseDao {	
	private SqlMapClient sqlMapClient=null;
	protected QueryParam queryParam=new QueryParam();
	public SqlMapBaseDao(){
		
	}
	public SqlMapClient getSqlMapClient() {
		if(sqlMapClient==null)
			sqlMapClient=SqlMapFactory.buildSqlMapClient();
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	public List getIterator(String sqlMapId) throws SQLException{
		if(sqlMapClient==null)
			sqlMapClient=SqlMapFactory.buildSqlMapClient();
		List list=new ArrayList();
		Query query=sqlMapClient.createQuery();
		list=query.iterator(sqlMapId);
		return list;
	}
	public List getList(String sqlMapId) throws SQLException{	
		if(sqlMapClient==null)
			sqlMapClient=SqlMapFactory.buildSqlMapClient();
		List list=new ArrayList();
		Query query=sqlMapClient.createQuery();
		list=query.list(sqlMapId);	
		return list;
	}
	public List getList(Map params,String sqlMapId) throws SQLException{		
		List list=new ArrayList();
		if(sqlMapClient==null)
			sqlMapClient=SqlMapFactory.buildSqlMapClient();
		Query query=sqlMapClient.createQuery();
		list=query.list(params,sqlMapId);	
		return list;
	}
	public List getList(Object obj,String sqlMapId) throws SQLException{
		if(sqlMapClient==null)
			sqlMapClient=SqlMapFactory.buildSqlMapClient();
		List list=new ArrayList();
		Query query=sqlMapClient.createQuery();
		list=query.list(obj, sqlMapId);		
		return list;
	}	
	
	public List getQueryForList(Object obj,String sqlMapId,int firstResult,int maxResult,int pageSize) throws SQLException{
		List list=new ArrayList();
		if(sqlMapClient==null)
			sqlMapClient=SqlMapFactory.buildSqlMapClient();		
		QueryPage queryPage=sqlMapClient.createQueryPage();
		queryParam.setFirstResult(firstResult);
		queryParam.setMaxResult(maxResult);
		queryParam.setPageSize(pageSize);
		queryPage.setQueryParam(queryParam);
		list=queryPage.queryForList(obj, sqlMapId);
		queryParam=queryPage.getQueryParam();
		return list;
	}
	public Object get(Object obj,String sqlMapId) throws SQLException{		
		if(sqlMapClient==null)
			sqlMapClient=SqlMapFactory.buildSqlMapClient();
		Query query=sqlMapClient.createQuery();
		return query.getUniqueResult(obj, sqlMapId);		
	}
	public Object get(Map params,String sqlMapId) throws SQLException{		
		if(sqlMapClient==null)
			sqlMapClient=SqlMapFactory.buildSqlMapClient();
		Query query=sqlMapClient.createQuery();
		return query.getUniqueResult(params, sqlMapId);
	}
	public QueryParam getQueryParam(){
		return queryParam;
	}
	public void execute(Object obj,String sqlMapId) throws SQLException{		
		if(sqlMapClient==null)
			sqlMapClient=SqlMapFactory.buildSqlMapClient();
		sqlMapClient.execute(obj, sqlMapId);		
	}
	public void execute(Map params,String sqlMapId) throws SQLException{		
		if(sqlMapClient==null)
			sqlMapClient=SqlMapFactory.buildSqlMapClient();
		sqlMapClient.execute(params, sqlMapId);
	}
	public void namedExecute(Object obj,String sqlMapId) throws SQLException{
		if(sqlMapClient==null)
			sqlMapClient=SqlMapFactory.buildSqlMapClient();
		sqlMapClient.namedExecute(obj, sqlMapId);
	}
	public void namedExecute(Map params,String sqlMapId) throws SQLException{
		if(sqlMapClient==null)
			sqlMapClient=SqlMapFactory.buildSqlMapClient();
		sqlMapClient.namedExecute(params, sqlMapId);
	}
	public void close() throws SQLException{		
		if(sqlMapClient!=null)
			sqlMapClient.close();
	}
}
