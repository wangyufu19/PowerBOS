package com.framework.common.util;
/**
 * 功能说明:数据库服务线程
 * @author wangyf
 * @version 1.0
 */
public class DBServiceThread extends SysThread{
	private Thread thread;
	private boolean status;
	public static int sleep=1*1000;
	
	public DBServiceThread(){
		thread=null;
		status=false;
	}
	public boolean getStatus() {		
		return status;
	}

	
	public void setParam(ThreadParam vo) {		
		if(vo.getSleep()!=-1) sleep=vo.getSleep()*1000;
	}
	public void run() {
		try{		
		
			thread.sleep(3000L);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void startRun() {
		try{
			if(!status){				
				DBServiceThread DBService=new DBServiceThread();
				if(thread==null){
					thread=new Thread(DBService,"DBServiceThread");
				}
				thread.start();						
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
	}

	
	public void startStop() {
		try{
			if(status){				
				thread.destroy();
				thread=null;
				status=false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
}
