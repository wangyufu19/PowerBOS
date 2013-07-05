package com.sqlMap.pool;
import java.util.TimerTask;

import com.sqlMap.log.LogFactory;
import com.sqlMap.log.Logger;
import com.sqlMap.pool.ConnectionPoolManager;
/**
 * 连接池定时器任务
 * @author youfu.wang
 * @version 1.0
 */
public class ConnectionPoolTask extends TimerTask{
	private static Logger log=LogFactory.getInstance();	
	private ConnectionPoolManager connectionPoolManager=ConnectionPoolManager.getInstance();
	private ConnectionPool connectionPools=ConnectionPoolFactory.getInstance();

	@Override
	/**
	 * 运行连接池定时器任务
	 * @return
	 */
	public void run() {				
		//如果空闲连接数少到最小连接数，就相应的递增新的数据连接
		if(connectionPools.getFreeConnections()<connectionPoolManager.getMinConnections()){	
			connectionPoolManager.newConnection();
		}
		//如果空闲连接数达到最大连接数，就相应的递减数据连接为最小连接
		if(connectionPools.getFreeConnections()>connectionPoolManager.getMaxConnections()){
			connectionPools.freeConnectionPool();
		}
		//清理或释放连接池中空闲连接的无效连接
		connectionPools.freeInvalidConnectionPools();
	}	

	
}
