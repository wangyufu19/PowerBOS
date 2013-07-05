package com.powerbosframework.log;
import java.util.Calendar;

import com.framework.common.util.SysConstants;
/**
 * 日志输出类
 * @author wangyf
 * @version 1.0
 */
public class Logger {	
	
	public void println(String msg) {
		String timestamp="";
		Calendar calendar=Calendar.getInstance();		
		calendar.setTimeInMillis(System.currentTimeMillis());		
		int month = calendar.get(2) + 1;
		timestamp += calendar.get(1) + "-" + month + "-" + calendar.get(5) + " "
				+ calendar.get(11) + ":" + calendar.get(12) + ":"
				+ calendar.get(13);	
		System.out.println(timestamp+" "+msg);
	}
	public void println(Object obj) {
		String timestamp="";
		Calendar calendar=Calendar.getInstance();		
		calendar.setTimeInMillis(System.currentTimeMillis());		
		int month = calendar.get(2) + 1;
		timestamp += calendar.get(1) + "-" + month + "-" + calendar.get(5) + " "
				+ calendar.get(11) + ":" + calendar.get(12) + ":"
				+ calendar.get(13);	
		System.out.println(timestamp+" "+obj);
	}
    public void info(String msg){
    	String timestamp="";
		Calendar calendar=Calendar.getInstance();		
		calendar.setTimeInMillis(System.currentTimeMillis());		
		int month = calendar.get(2) + 1;
		timestamp += calendar.get(1) + "-" + month + "-" + calendar.get(5) + " "
				+ calendar.get(11) + ":" + calendar.get(12) + ":"
				+ calendar.get(13);	    	
    	System.out.println("信息: "+timestamp+" "+msg);    	
    }
    public void info(Object obj){
    	String timestamp="";
		Calendar calendar=Calendar.getInstance();		
		calendar.setTimeInMillis(System.currentTimeMillis());		
		int month = calendar.get(2) + 1;
		timestamp += calendar.get(1) + "-" + month + "-" + calendar.get(5) + " "
				+ calendar.get(11) + ":" + calendar.get(12) + ":"
				+ calendar.get(13);	    	
    	System.out.println("信息: "+timestamp+" "+obj);    	
    }
    public void addLogFile(Exception e){	
		if(!"true".equals(SysConstants.debug_log)) return;
		String message=e.getMessage();		
		if(message==null)
			message="";		
		else
			message="异常:"+message+System.getProperty("line.separator");
		StackTraceElement traceList[] = e.getStackTrace();
		if(traceList!=null){
			Calendar calendar = Calendar.getInstance();
			String time="";
			int sysMonth = calendar.get(2) + 1;
			time += calendar.get(1) + "-" + sysMonth + "-" + calendar.get(5) + " "
					+ calendar.get(11) + ":" + calendar.get(12) + ":"
					+ calendar.get(13);
			StringBuilder buf=new StringBuilder();
			buf.append(time+" "+message);
			for(int i=0;i<traceList.length;i++){
				buf.append(time+" "+traceList[i].toString()+System.getProperty("line.separator"));				
			}
			message=buf.toString();
		}		
		LogFile logFile=new LogFile();
		logFile.log(message);
	}
}
