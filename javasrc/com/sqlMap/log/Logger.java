package com.sqlMap.log;

import java.util.Calendar;
/**
 * 日志输出类
 * @author youfu.wang
 * @version 1.0
 */
public class Logger {
	/**
	 * 输出一个日志消息
	 * @param msg
	 */
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
	/**
	 * 输出一个日志消息
	 * @param obj
	 */
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
	/**
	 * 输出一个日志消息
	 * @param msg
	 */
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
    /**
     * 输出一个日志消息
     * @param obj
     */
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

}
