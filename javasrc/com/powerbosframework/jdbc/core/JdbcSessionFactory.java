package com.powerbosframework.jdbc.core;
import com.powerbosframework.jdbc.core.JdbcTmplt;
import com.powerbosframework.jdbc.core.JdbcTmpltManager;
import com.powerbosframework.jdbc.datasource.DataSource;
import com.powerbosframework.jdbc.datasource.DriverManagerDatasource;
/**
 * JDBC会话工厂类
 * @author youfu.wang
 * @version 1.0
 */
public class JdbcSessionFactory {
	private DataSource dataSource;
	private JdbcTmplt jdbcTmplt=null;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public JdbcTmplt getJdbcTmplt(){
		if(jdbcTmplt!=null){
			jdbcTmplt=new JdbcTmpltManager(dataSource);
		}
		return jdbcTmplt;
	}
}
