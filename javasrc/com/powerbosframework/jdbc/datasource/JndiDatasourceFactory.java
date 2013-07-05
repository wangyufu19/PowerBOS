package com.powerbosframework.jdbc.datasource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 * Jndi数据源实现类
 * @author youfu.wang
 * @version 1.0
 */
public class JndiDatasourceFactory {
	private Connection connection=null;
	private String jndiName;
	private String showSql;
	public String getJndiName() {
		return jndiName;
	}
	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}
	public String getShowSql() {
		return showSql;
	}
	public void setShowSql(String showSql) {
		this.showSql = showSql;
	}
	public Connection getConnection() throws SQLException{
		if(connection!=null){
			Context initCtx=null;
			DataSource dataSource=null;
			try {
				initCtx = new InitialContext();
				dataSource=(DataSource) initCtx.lookup(jndiName);
			} catch (NamingException e) {				
				e.printStackTrace();
			};		
			if(dataSource!=null){
				connection=dataSource.getConnection();
				return connection;
			}			
		}
		return null;
	}
}
