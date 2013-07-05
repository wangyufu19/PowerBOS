package com.sqlMap.pool;
import java.sql.Connection;

/**
 * 连接池
 * @author youfu.wang
 * @version 1.0
 */
public abstract interface ConnectionPool {

	/**
	 * 新建一个数据库连接
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 */
	public abstract void newConnection(String url,String username,String password);	
	/**
	 * 返回一个数据库连接
	 * @return
	 */
	public abstract Connection getConnection();
	/**
	 * 返回空闲连接数量
	 * @return
	 */
	public abstract int getFreeConnections();
	/**
	 * 释放一个连接内存
	 * @return
	 */
	public abstract void freeConnectionPool();
	/**
	 * 释放一个连接内存
	 * @return
	 */
	public abstract void freeConnectionPool(Connection connection);
	/**
	 * 释放全部无效连接内存
	 * @return
	 */
	public abstract void freeInvalidConnectionPools();
	/**
	 * 释放全部连接内存
	 * @return
	 */
	public abstract void freeConnectionPools();
	/**
	 * 回收一个数据库连接
	 * @param connection
	 */
	public abstract void recycleConnectionPool(Connection connection);

}
