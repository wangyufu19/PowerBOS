package com.sqlMap.pool;
import java.sql.Connection;
import java.sql.SQLException;

import com.sqlMap.log.LogFactory;
import com.sqlMap.log.Logger;
import com.sqlMap.pool.ConnectionPool;
import com.sqlMap.pool.ConnectionPoolFactory;


/**
 * 连接池管理
 * @author youfu.wang
 * @version 1.0
 */
public class ConnectionPoolManager {
	private static Logger log=LogFactory.getInstance();	
	private String driver="";
	private String url="";
	private String username="";
	private String password="";
	private int minConnections=5;
	private int maxConnections=15;
	
	private static ConnectionPoolManager instance=null;
	private ConnectionPool connectionPools=ConnectionPoolFactory.getInstance();
	

	
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMinConnections() {
		return minConnections;
	}

	public void setMinConnections(int minConnections) {
		this.minConnections = minConnections;
	}

	public int getMaxConnections() {
		return maxConnections;
	}

	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	/**
	 * 返回一个连接池管理实例
	 * @return ConnectionPoolManager
	 */
	public static ConnectionPoolManager getInstance(){
		if(instance==null){
			instance=new ConnectionPoolManager();			
		}
		return instance;
	}
	
	/**
	 * 初始化一个连接池
	 * @param name
	 * @return
	 */
	public void init() {				
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}		
		for(int i=0;i<minConnections;i++){
			connectionPools.newConnection(url,username,password);
		}				
	}
	/**
	 * 
	 */
	public void newConnection(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}			
		connectionPools.newConnection(url,username,password);
				
	}
	/**
	 * 从连接池返回一个空闲连接
	 * @return
	 * @throws SQLException 
	 */
	public synchronized Connection getConnection(){
		Connection connection=null;
		connection=connectionPools.getConnection();
		try {
			if(connection.isClosed()){
				connectionPools.freeConnectionPool(connection);
				connection=connectionPools.getConnection();
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return connection;
	}
	/**
	 * 回收一个连接到池中
	 */
	public synchronized void freeConnection(Connection connection){
		connectionPools.recycleConnectionPool(connection);
	}
	

}
