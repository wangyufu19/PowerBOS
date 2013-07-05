package com.sqlMap.jdbc;
import java.util.Map;
import com.sqlMap.cfg.Configuration;
import com.sqlMap.cfg.Environment;
import com.sqlMap.pool.ConnectionPoolManager;
import com.sqlMap.util.StringUtil;
import com.sqlMap.pool.ConnectionPoolLinstener;
import com.sqlMap.log.Logger;
import com.sqlMap.log.LogFactory;
/**
 * 连接池供应商
 * @author youfu.wang
 * @version 1.0
 */
public class ConnectionProvider {
	private static Logger log=LogFactory.getInstance();	
	private ConnectionPoolManager connectionPoolManager=ConnectionPoolManager.getInstance();
	private String driver="";
	private String url="";
	private String username="";
	private String password="";
	private int minConnections=5;
	private int maxConnections=15;
	
	/**
	 * 初始化一个连接池
	 * @param name
	 * @return
	 */
	public void init(String name) {			
		
		Map datasource=Environment.getDatasource(name);
		
		driver=StringUtil.replaceNull(String.valueOf(datasource.get(Configuration.DRIVER)));		
		url=StringUtil.replaceNull(String.valueOf(datasource.get(Configuration.URL)));
		username=StringUtil.replaceNull(String.valueOf(datasource.get(Configuration.USERNAME)));
		password=StringUtil.replaceNull(String.valueOf(datasource.get(Configuration.PASSWORD)));	
		if(!"".equals(StringUtil.replaceNull(String.valueOf(datasource.get(Configuration.CONNECTION_POOL_MIN_CONNECTIONS))))){
			minConnections=Integer.parseInt(String.valueOf(datasource.get(Configuration.CONNECTION_POOL_MIN_CONNECTIONS)));
		}
		if(!"".equals(StringUtil.replaceNull(String.valueOf(datasource.get(Configuration.CONNECTION_POOL_MAX_CONNECTIONS))))){
			maxConnections=Integer.parseInt(String.valueOf(datasource.get(Configuration.CONNECTION_POOL_MAX_CONNECTIONS)));
		}
		
		connectionPoolManager.setDriver(driver);
		connectionPoolManager.setUrl(url);
		connectionPoolManager.setUsername(username);
		connectionPoolManager.setPassword(password);
		connectionPoolManager.setMinConnections(minConnections);
		connectionPoolManager.setMaxConnections(maxConnections);
		connectionPoolManager.init();
		//启动连接池监听器
		ConnectionPoolLinstener connectionPoolLinstener=new ConnectionPoolLinstener();
		connectionPoolLinstener.start();
	}
}
