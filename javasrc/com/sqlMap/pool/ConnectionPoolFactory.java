package com.sqlMap.pool;
import com.sqlMap.pool.ConnectionPool;
import com.sqlMap.pool.impl.ConnectionPoolImpl;
/**
 * 连接池工厂类
 * @author youfu.wang
 * @version 1.0
 */
public class ConnectionPoolFactory {
	private static ConnectionPool instance=null;
	
	/**
	 * 返回一个连接池实例
	 * @return
	 */
	public static ConnectionPool getInstance(){
		if(instance==null){
			instance=new ConnectionPoolImpl();
		}
		return instance;
	}

}
