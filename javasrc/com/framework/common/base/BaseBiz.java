package com.framework.common.base;
import java.sql.SQLException;

import com.controller.bean.BeanFactory;
import com.controller.property.PageParam;
import com.framework.common.servlet.http.RequestHash;
import com.sqlMap.SqlMapClient;
import com.sqlMap.jdbc.JdbcTmplt;
/**
 * 功能说明:基础业务组件类
 * @author wangyf
 * @version 1.0
 */
public class BaseBiz {
	protected RequestHash reh;
	protected PageParam pageParam=BeanFactory.getPageParamInstance();	
	private SqlMapClient sqlMapClient=null;	
	private JdbcTmplt jdbcTmplt=null;
	
	public void setReh(RequestHash reh){
		this.reh=reh;
	}
	public RequestHash getReh(){
		return this.reh;
	}
	public PageParam getPageParam(){
		return pageParam;
	}	
	public SqlMapClient getSqlMapClient() {
		if(sqlMapClient==null){
			sqlMapClient=reh.getSqlMapClient();
		}
		return sqlMapClient;
	}
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	public JdbcTmplt getJdbcTmplt(){
		if(jdbcTmplt==null){
			jdbcTmplt=reh.getJdbcTmplt();	
		}
		return jdbcTmplt;		
	}
	public void setJdbcTmplt(JdbcTmplt jdbcTmplt){
		this.jdbcTmplt=jdbcTmplt;
	}
	
}
