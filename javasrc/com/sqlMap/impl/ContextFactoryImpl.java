package com.sqlMap.impl;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.sqlMap.ContextFactory;
import com.sqlMap.SqlMapClient;
import com.sqlMap.cfg.Environment;
import com.sqlMap.log.DBLogger;
import com.sqlMap.log.Logger;
import com.sqlMap.log.LogFactory;
import com.sqlMap.pool.ConnectionPoolManager;
import com.sqlMap.exception.ConfigurationException;
import com.sqlMap.impl.SqlMapClientImpl;
import com.sqlMap.jdbc.JdbcTmplt;
import com.sqlMap.jdbc.impl.JdbcTmpltImpl;
import com.sqlMap.util.StringUtil;
/**
 * 上下文工厂
 * @author wangyf
 * @version 1.0
 */
public class ContextFactoryImpl implements ContextFactory{
	private static Connection connection=null;
	private static SqlMapClient sqlMapCliient=null;
	private static JdbcTmplt jdbcTmplt=null;
	private ConnectionPoolManager connectionPoolManager=ConnectionPoolManager.getInstance();
	private static final Logger log=LogFactory.getInstance();

	public ContextFactoryImpl(){
		
	}	
	/**
	 * 返回一个数据库连接
	 * @return
	 */
	public Connection getConnection(){
		return connection;
	}
	/**
	 * 构建一个数据库连接
	 * @param datasource
	 */
	private void buildConnection(Map datasource){
		connection=null;
		Context initCtx=null;
		DataSource dataSource=null;		
		String connectionType=StringUtil.replaceNull(String.valueOf(datasource.get("connection-type")));
		Environment.dialect=StringUtil.replaceNull(String.valueOf(datasource.get("dialect")));
		Environment.dialect_version=StringUtil.replaceNull(String.valueOf(datasource.get("dialect_version")));
		DBLogger.show_sql=StringUtil.replaceNull(String.valueOf(datasource.get("show_sql")));
		DBLogger.sql_log=StringUtil.replaceNull(String.valueOf(datasource.get("sql_log")));
		if("jndi".equals(connectionType.toLowerCase())){
			try {
				initCtx =new InitialContext();
			} catch (NamingException e) {				
				e.printStackTrace();
			}						
			try {
				dataSource=(DataSource) initCtx.lookup(StringUtil.replaceNull(String.valueOf(datasource.get("jndi_name"))));
			} catch (NamingException e) {				
				e.printStackTrace();
			}
			try {
				connection=dataSource.getConnection();
			} catch (SQLException e) {				
				e.printStackTrace();
			}			
		}else if("jdbc".equals(connectionType.toLowerCase())){		
			connection= connectionPoolManager.getConnection();
			if(connection==null){
				try {
					Class.forName(StringUtil.replaceNull(String.valueOf(datasource.get("driver"))));
				} catch (ClassNotFoundException e) {				
					e.printStackTrace();
				}
				try {
					String url=StringUtil.replaceNull(String.valueOf(datasource.get("url")));
					String username=StringUtil.replaceNull(String.valueOf(datasource.get("username")));
					String password=StringUtil.replaceNull(String.valueOf(datasource.get("password")));	
					connection= DriverManager.getConnection(url,username,password);
				} catch (SQLException e) {				
					e.printStackTrace();
				}	
			}
		}				
	}
	/**
	 * 返回一个SqlMapClient对象
	 * @return
	 * @throws ConfigurationException
	 */
	public SqlMapClient getSqlMapClient() throws ConfigurationException{
		this.buildConnection(Environment.getDefaultDatasource());
		if(connection==null) throw new ConfigurationException("创建数据库连接失败");
		sqlMapCliient=new SqlMapClientImpl(connection);
		return sqlMapCliient;
	}	
	/**
	 * 返回一个SqlMapClient对象
	 * @param identifer
	 * @return
	 * @throws ConfigurationException
	 */
	public SqlMapClient getSqlMapClient(String identifer) throws ConfigurationException{
		Map datasource=Environment.getDatasource(identifer);
		if(datasource==null) throw new ConfigurationException("持久化组件环境没有找到"+identifer+"数据源配置");
		this.buildConnection(datasource);	
		if(connection==null) throw new ConfigurationException("创建数据库连接失败");
		sqlMapCliient=new SqlMapClientImpl(connection);
		return sqlMapCliient;
	}
	/**
	 * 返回一个JDBC模板对象
	 * @return
	 * @throws ConfigurationException
	 */
	public JdbcTmplt getJdbcTmplt() throws ConfigurationException{
		this.buildConnection(Environment.getDefaultDatasource());	
		if(connection==null) throw new ConfigurationException("创建数据库连接失败");
		jdbcTmplt=new JdbcTmpltImpl(connection);
		return jdbcTmplt;
	}
	/**
	 * 返回一个JDBC模板对象
	 * @param identifer
	 * @return
	 * @throws ConfigurationException
	 */
	public JdbcTmplt getJdbcTmplt(String identifer) throws ConfigurationException{
		Map datasource=Environment.getDatasource(identifer);
		if(datasource==null) throw new ConfigurationException("持久化组件环境没有找到"+identifer+"数据源配置");
		this.buildConnection(datasource);	
		if(connection==null) throw new ConfigurationException("创建数据库连接失败");
		jdbcTmplt=new JdbcTmpltImpl(connection);
		return jdbcTmplt;
	}
}
