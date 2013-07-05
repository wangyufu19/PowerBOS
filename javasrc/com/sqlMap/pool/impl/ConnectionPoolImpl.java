package com.sqlMap.pool.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.sqlMap.log.LogFactory;
import com.sqlMap.log.Logger;
import com.sqlMap.pool.ConnectionPool;

/**
 * 连接池实现类
 * @author youfu.wang
 * @version 1.0
 */
public class ConnectionPoolImpl implements ConnectionPool {
	private static Logger log=LogFactory.getInstance();	
	public static Queue<Connection> freeConnections = new LinkedBlockingQueue<Connection>();

	public ConnectionPoolImpl() {

	}

	/**
	 * 新建一个数据库连接
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 */
	public void newConnection(String url, String username, String password) {
		try {
			Connection connection = DriverManager.getConnection(url, username,
					password);
			freeConnections.add(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回一个数据库连接
	 * @return
	 */
	public Connection getConnection() {
		Connection connection = null;
		connection = freeConnections.poll();
		return connection;
	}

	/**
	 * 返回空闲连接数量
	 * @return
	 */
	public int getFreeConnections() {
		return freeConnections.size();
	}
	/**
	 * 释放一个连接内存
	 * @return
	 */
	public void freeConnectionPool(){
		freeConnections.poll();
	}
	/**
	 * 释放一个连接内存
	 * @param connection 
	 * @return
	 */
	public void freeConnectionPool(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		freeConnections.remove(connection);
	}

	/**
	 * 释放全部无效连接内存
	 * @return
	 */
	public void freeInvalidConnectionPools() {
		for (Connection connection : freeConnections) {			
			try {				
				if (connection.isClosed()) {
					freeConnections.remove(connection);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 释放全部连接内存
	 * @return
	 */
	public void freeConnectionPools() {
		for (Connection connection : freeConnections) {			
			try {
				connection.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
			freeConnections.remove(connection);						
		}
	}

	/**
	 * 回收一个数据库连接
	 * @param connection
	 */
	public void recycleConnectionPool(Connection connection) {
		try {
			if (!connection.isClosed())
				freeConnections.add(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
