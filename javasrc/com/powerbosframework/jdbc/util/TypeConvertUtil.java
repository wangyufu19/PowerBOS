package com.powerbosframework.jdbc.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;

/**
 * 数据类型转换类
 * 
 * @author youfu.wang
 * @version 1.0
 */
public class TypeConvertUtil {
	/**
	 * 转换日期对象为"yyyy-MM-dd"格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String convertOfDate(Date date) {
		if (date == null)
			return null;
		DateFormat dtFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		return dtFmt.format(date);
	}

	/**
	 * 转换日期对象为指定格式的字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String convertOfDate(Date date, String pattern) {
		if (date == null)
			return null;
		DateFormat dtFmt = new SimpleDateFormat(pattern, Locale.CHINA);
		return dtFmt.format(date);
	}

	/**
	 * 转换时间对象为"yyyy-MM-dd HH:mm:ss"格式的字符串
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String convertOfTimestamp(Timestamp timestamp) {
		if (timestamp == null)
			return null;
		DateFormat dtFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		return dtFmt.format(timestamp);
	}

	/**
	 * 转换时间对象为指定格式的字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String convertOfTimestamp(Timestamp timestamp, String pattern) {
		if (timestamp == null)
			return null;
		DateFormat dtFmt = new SimpleDateFormat(pattern, Locale.CHINA);
		return dtFmt.format(timestamp);
	}
}
