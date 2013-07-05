package com.framework.common.util;
import java.util.Calendar;
import java.util.Date;
/**
 * 日期帮助类
 * @author wangyf
 * @version 1.0
 */
public class DateUtil {
	// 得到时间的年
	public static String getYear(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return String.valueOf(calendar.get(1));
	}
	// 得到时间的月
	public static String getMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return String.valueOf(calendar.get(2) + 1);		
	}
	// 得到时间的日
	public static String getDay(Date date){	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return String.valueOf(calendar.get(5));
	}
	// 得到系统时间的年
	public static String getYear(){
		Calendar calendar = Calendar.getInstance();
		return String.valueOf(calendar.get(1));
	}
	// 得到系统时间的月
	public static String getMonth(){
		Calendar calendar = Calendar.getInstance();
		return String.valueOf(calendar.get(2) + 1);		
	}
	// 得到系统时间的日
	public static String getDay(){
		Calendar calendar = Calendar.getInstance();
		return String.valueOf(calendar.get(5));
	}
	// 得到一个HH:MM:SS样式的时间信息
	public static String getHHMMSS() {
		String ret = " ";
		Calendar calendar = Calendar.getInstance();
		ret += calendar.get(11) + ":" + calendar.get(12) + ":"
				+ calendar.get(13) + " ";
		return ret;
	}

	// 得到一个YYYY-MM-DD样式的时间信息
	public static String getYYYYMMDD() {
		String ret = "";
		Calendar calendar = Calendar.getInstance();
		int sysMonth = calendar.get(2) + 1;
		ret += calendar.get(1) + "-" + sysMonth + "-" + calendar.get(5);
		return ret;
	}

	// 得到一个YYYY-MM-DD HH:MM:SS样式的时间信息
	public static String getYYYYMMDDHHMMSS() {
		String ret = "";
		Calendar calendar = Calendar.getInstance();
		int sysMonth = calendar.get(2) + 1;
		ret += calendar.get(1) + "-" + sysMonth + "-" + calendar.get(5) + " "
				+ calendar.get(11) + ":" + calendar.get(12) + ":"
				+ calendar.get(13);
		return ret;
	}
	// 通过传入一个DATE得到一个YYYY-MM-DD HH:MM:SS样式的时间信息
	public static String getYYYYMMDD(Date date) {
		String ret = "";
		if(date==null) return "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int sysMonth = calendar.get(2) + 1;
		ret += calendar.get(1) + "-" + sysMonth + "-" + calendar.get(5);
		return ret;
	}
	// 通过传入一个DATE得到一个YYYY-MM-DD HH:MM:SS样式的时间信息
	public static String getYYYYMMDDHHMMSS(Date date) {
		String ret = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int sysMonth = calendar.get(2) + 1;
		ret += calendar.get(1) + "-" + sysMonth + "-" + calendar.get(5) + " "
				+ calendar.get(11) + ":" + calendar.get(12) + ":"
				+ calendar.get(13);
		return ret;
	}
    //将LONG时间转换成YYYY-MM-DD HH:MM:SS样式的时间信息
	public static String getYYYYMMDDHHMMSS(long millis){
		String ret = "";
		Calendar calendar=Calendar.getInstance();		
		calendar.setTimeInMillis(millis);		
		int sysMonth=calendar.get(2)+1;
		ret += calendar.get(1) + "-" + sysMonth + "-" + calendar.get(5) + " "
		+ calendar.get(11) + ":" + calendar.get(12) + ":"
		+ calendar.get(13);		
		return ret;		
	}	
	//将long时间转换成Data对象 
	public static Date convertLongToDate(long millis){
		Calendar calendar=Calendar.getInstance();		
		calendar.setTimeInMillis(millis);		
		return calendar.getTime();
	}
	//将long时间转换成Timestamp对象 
	public static java.sql.Timestamp convertLongToTimestamp(long millis){
		return new java.sql.Timestamp(millis);
	}
	//返回当前的时间
	public static long getCurrentSysTime(){
		return new Date().getTime();
	}	
	//返回当前日期
	public static Date getCurrentDate(){
		return new Date();
	}
	//比较两个CALENDAR,返回一个相差值
	public static int getCalendarCompareTo(long arg1,long arg2){
		int ret=0;
		Calendar calendar1=Calendar.getInstance();
		Calendar calendar2=Calendar.getInstance();
		calendar1.setTimeInMillis(arg1);
		calendar2.setTimeInMillis(arg2);
		ret=calendar1.compareTo(calendar2);
		return ret;	
	}	
}
