package com.sqlMap.pool;
import java.util.Timer;

import com.sqlMap.log.LogFactory;
import com.sqlMap.log.Logger;
import com.sqlMap.pool.ConnectionPoolTask;

/**
 * 连接池监听器
 * @author youfu.wang
 * @version 1.0
 */
public class ConnectionPoolLinstener{
	private static Logger log=LogFactory.getInstance();	
	private long period=1000;//执行连接池定时器任务的时间间隔，单位是毫秒。
	private Timer timer=new Timer();
	/**
	 * 启动连接池定时器任务
	 * @return
	 */
	public void start(){		
		timer.scheduleAtFixedRate(new ConnectionPoolTask(), 0, period);
	}
	
}
